package database;

import tools.Time;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.org.apache.bcel.internal.generic.RETURN;

//import entity.UserInfo;

public class userDAO {

	Connection mConn;// mConn
	PreparedStatement mPs;// mPs;
	ResultSet mRs;// mRs;

	/**
	 * �������ݿ�ķ���
	 */
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/fdianming";// ����ʵ������仯
	private String dbUser = "root";
	private String dbPass = "5580031";

	public Connection getConn() {
		Connection conn = null;

		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);// ע������������
			System.out.println("���ݿ����ӳɹ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/*
	 * ��ѯ���ݿ����ķ���
	 */

	public int QuerySql(String s1, String s2) {
		int id = Integer.parseInt(s1);
		int password = Integer.parseInt(s2);

		Connection cnn = getConn();// �˴�Ϊͨ���Լ�д�ķ���getConn()�������
		System.out.println(cnn);

		String sql = "select * from student where sId=" + id + " and sPwd="
				+ password + "";

		try {
			Statement stmt = cnn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				return 1;
			} else {
				if (id < 20000000)

				{
					String sql2 = "select * from teacher where tId=" + id
							+ " and tPwd=" + password + "";

					try {
						Statement stmt2 = cnn.createStatement();
						ResultSet rs2 = stmt2.executeQuery(sql2);

						if (rs2.next()) {

							int t;
							Time gettime = new Time();
							t = gettime.timeCalculate();

							String sql3 = "select cName from course where tId="
									+ id + " and cTime=1"; // //**********
							Statement stmt3 = cnn.createStatement();
							ResultSet rs3 = stmt3.executeQuery(sql3);

							if (rs3.next()) {

								return 2;

							} else {
								return 3;
							}

						}
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} else {
					return 0;
				}
				// ���Խ����ҵ���ֵд���࣬Ȼ�󷵻���Ӧ�Ķ���
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����");
		}

		return 0;

	}

}

