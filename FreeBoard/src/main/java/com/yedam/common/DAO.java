package com.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	//필드
	public static Connection conn = null;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	//1.연결설정 메소드 (void)
		public static void getConn() {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@192.168.0.37:1521:xe",
						//"jdbc:oracle:thin:@localhost:1521:xe",
						"java",
						"1234"
						);
				//System.out.println("연결 성공");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//2.연결끊기 메소드 (void)
		public static void getClose() {
			if(conn != null) {
				try {
					conn.close();
					//System.out.println("연결 종료");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
}