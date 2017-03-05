package login;

import database.userDAO;
import tools.Time;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class firstservlet
 */
@WebServlet("/Login")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		// this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int k;
		String cName = "error";
		PrintWriter out = response.getWriter();
		// 登陆成功标志
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 获得客户端提交用户名密码
		int id = Integer.parseInt(username);
		int pwd = Integer.parseInt(password);
		userDAO user = new userDAO();
		user.getConn();
		k = user.QuerySql(username, password);
		Time gettime = new Time();
		int t;
		t = gettime.timeCalculate();
		String s4 = k + "";
		System.out.println(s4);
		out.println(s4);
		out.flush();
	}

}
