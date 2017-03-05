package getstudentlist;

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

import net.sf.json.JSONArray;

import com.google.gson.Gson;

import stud.Student;
import tools.Time;
import database.userDAO;



/**
 * Servlet implementation class GetStudentList
 */
@WebServlet("/Getstudentlist")
public class GetStudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStudentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding ("UTF-8");
		userDAO user = new userDAO();
        user.getConn();
		PrintWriter out = response.getWriter();
        //登陆成功标志
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
  
        try {
        	Connection mConn=user.getConn();
        	Time gettime = new Time();
            int t;
            t=gettime.timeCalculate();
            String id=null;
            String name=null;
        	
        	 String sql4="select sId,sName from course where tId="+username+" and cTime=1 ";///cTime=t
        	 Statement stmt4 = mConn.createStatement();  
             ResultSet rs4 = stmt4.executeQuery(sql4);
        	
        	   ArrayList<Student> resultList =new ArrayList<Student>();	
//        	   JSONArray jsonArray = new JSONArray(); 
               while(rs4.next())
               {
            	   Student s=new Student("error", "error",0,0);
            	   id = rs4.getString("sId");
                	   name = rs4.getString("sName");
            	   id=new String(id.getBytes("ISO-8859-1"),"gb2312");
            	   System.out.println(id);
            	   name=new String(name.getBytes("ISO-8859-1"),"gb2312");
            	   System.out.println(name);
            	   s.setsId(id);
            	   s.setsName(name);
            	   resultList.add(s);

               }
               Gson gson = new Gson();
               System.out.println(resultList);
               
               String result = gson.toJson(resultList);
//               result=new String(result.getBytes("ISO-8859-1"),"gb2312");
			   result = URLEncoder.encode(result, "UTF-8");
               out.print(result);
               out.flush();
               out.close();
        	
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
      
		
		
		
		
		
		
		
		
	}

}
