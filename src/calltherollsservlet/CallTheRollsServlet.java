package calltherollsservlet;

import stud.Student;
import teac.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import distance.Distance;
import tools.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.userDAO;

/**
 * 点名时访问，Map<String,List<Student>> 为当前点名的同学名单； 每次有老师请求，则将名单返回并清空；
 * 当老师密码为空时，返回数据并将对应Map中的项删除
 */
@WebServlet("/Calltherolls")
public class CallTheRollsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ConcurrentHashMap<String, ArrayList<Student>> chm = new ConcurrentHashMap<String, ArrayList<Student>>();
	private static ConcurrentHashMap<String, Teacher> chm2 = new ConcurrentHashMap<String, Teacher>();

	public CallTheRollsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		double x = Double.valueOf(req.getParameter("x"));
		double y = Double.valueOf(req.getParameter("y"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		System.out.println(x + " " + y);
		int ss = Integer.parseInt(username);
		int time = Time.timeCalculate();
		Distance cd = new Distance();

		PrintWriter out = resp.getWriter();
		userDAO user = new userDAO();
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			Connection mConn = user.getConn();
			String sql = "select tId from course where sId=" + username
					+ " and cTime=1";// /cTime=t
			Statement stmt = mConn.createStatement();
			rs = stmt.executeQuery(sql);
			String sql2 = "select sName from student where sId=" + username
					+ "";
			Statement stmt2 = mConn.createStatement();
			rs2 = stmt2.executeQuery(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		char[] c = username.toCharArray();
		// 判断用户名密码
		try {
			System.out.println("点名开始");
			if (c[0] == '2') {
				// 根据学生id和time找到老师id，找到chm中的对应项，加进去
				System.out.println("学生点名");
				double sx = 0, sy = 0;
				sx = x;
				sy = y;
				System.out.println("学生坐标第一次"+sx+" "+sy);

				int i = 2;
				if (rs.next()) {
					String tId = null;
					String sId = null;

					sId = username;
					tId = rs.getString("tId");
					sId = new String(sId.getBytes("ISO-8859-1"), "gb2312");
					tId = new String(tId.getBytes("ISO-8859-1"), "gb2312");
					Student s = new Student(username, sId, x, y);
					double gx = 0, gy = 0;

					// PrintWriter out = resp.getWriter();
					if (chm.containsKey(tId)) {
						Teacher temp = chm2.get(tId);
						//Teacher temp = list2.get(0);
						gx = temp.getx();
						gy = temp.gety();
						System.out.println("获取老师坐标"+gx + " " + gy);
						boolean ifdis = cd.caculateDistance(gx, gy, sx, sy);
					
						if (ifdis) {
							chm.get(tId).add(s);
							i = 1;
							System.out.println("点名成功");
						}
					} else {
						i = 0;
						System.out.println("点名shibai");
					}

					System.out.println(s);
				}

				out.write(i);
				out.flush();
				out.close();

			} else { // teacher
				System.out.println("老师点名");
				Gson gson = new Gson();
				double tx = 0, ty = 0;
				tx = x;
				ty = y;
				System.out.println(tx + " " + ty);
				Teacher t = new Teacher(username, username, tx, ty);

				chm2.put(username, t);
				Teacher tqwqweTeacher =chm2.get(username);
				System.out.println(tqwqweTeacher.getx());
				if (!chm.containsKey(username)) {
					chm.put(username, new ArrayList<Student>());
				}

				ArrayList<Student> list = chm.get(username);

				if ("".equals(password)) {
					chm.remove(username);
					chm2.remove(username);
					x = 0;
					y = 0;
					System.out.println("老师点名结束!!!!!!");
				} else {
					chm.put(username, new ArrayList<Student>());
					System.out.println("老师请求数据");
				}
				String data = gson.toJson(list);
				data = URLEncoder.encode(data, "UTF-8");
				out.print(data);
				out.flush();
				out.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}