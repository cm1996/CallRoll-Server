package userinfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Time;
import database.userDAO;

/**
 * Servlet implementation class UserInfoservlet
 */
@WebServlet("/Userinfo")
public class UserInfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInfoservlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("99");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int k;
		String cName = "error";
		PrintWriter out = response.getWriter();
		// 登陆成功标志
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 获得客户端提交用户名密码
		// request.getContentType();

		// out.println(username);
		// out.println(password);
		int id = Integer.parseInt(username);
		int pwd = Integer.parseInt(password);
		userDAO user = new userDAO();
		user.getConn();

		k = user.QuerySql(username, password);

		Time gettime = new Time();
		int t;
		t = gettime.timeCalculate();

		try {

			Connection mConn = user.getConn();

			String sql2 = "select tName from teacher where tId=" + id
					+ " and tPwd=" + pwd + "";
			// String sql2="select tName from teacher where id=1";
			// String sql2="select tName from teacher where id=1";
			// String sql4 =
			// "select cName,sId from course where tId="+username+"and cTime=1 ";
			String sql4 = "select cName,sId from course where tId = " + id
					+ " and cTime=1";
			// String sql =
			// "select sName from student where sId="+id+"and sPwd="+pwd+" ";
			String sql = "select sName from student where sId =" + id
					+ " and sPwd = " + pwd + "";
			String sql5 = "select cName from course where sId=" + id
					+ " and cTime =1";
			Statement stmt4 = mConn.createStatement();
			ResultSet rs4 = stmt4.executeQuery(sql4);
			Statement stmt3 = mConn.createStatement();
			ResultSet rs3 = stmt3.executeQuery(sql2);
			Statement stmt = mConn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Statement stmt5 = mConn.createStatement();
			ResultSet rs5 = stmt5.executeQuery(sql5);
			String sName = null, csName = null, gName = null, heName0 = null, heName1 = null, heName2 = null;
			if (k == 1) {
				if (rs.next()) {
					System.out.println("90");
					sName = rs.getString("sName");
					System.out.println("学生名输出成功");
				}
				if (rs5.next()) {
					csName = null;
					csName = rs5.getString("cName");
					heName0 = sName + "," + csName;
					heName0 = new String(heName0.getBytes("ISO-8859-1"),
							"gb2312");
					heName0 = URLEncoder.encode(heName0, "UTF-8");
					out.println(heName0);
					out.flush();
					System.out.println(heName0);
					System.out.println("课程名输出成功");
				}
			}

			if (k == 3) {
				if (rs3.next()) {
					String tName = rs3.getString("tName");
					tName = URLEncoder.encode(tName, "UTF-8");
					heName1 = tName + "," + "null";
					heName1 = new String(heName1.getBytes("ISO-8859-1"),
							"gb2312");
					heName1 = URLEncoder.encode(heName1, "UTF-8");
					out.println(heName1);
					out.flush();
					System.out.println("教师名输出成功");
				}

			}

			if (k == 2) {

				if (rs3.next()) {
					cName = rs3.getString("tName");
					System.out.println("教师名输出成功");
				}

				if (rs4.next()) {
					System.out.println("00");
					gName = rs4.getString("cName");
					heName2 = cName + "," + gName;
					heName2 = new String(heName2.getBytes("ISO-8859-1"),
							"gb2312");
					heName2 = URLEncoder.encode(heName2, "UTF-8");
					out.println(heName2);
					out.flush();
					System.out.println("课程名输出成功");
					System.out.println(heName2);

				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

// System.out.println(new String(testString.getBytes("ISO-8859-1〃),"gb2312〃));
// System.out.println(new String(testString.getBytes("UTF8〃),"gb2312〃));
// System.out.println(new String(testString.getBytes("GB2312〃),"gb2312〃));
// System.out.println(new String(testString.getBytes("GBK"),"gb2312〃));
// System.out.println(new String(testString.getBytes("BIG5〃),"gb2312〃));

