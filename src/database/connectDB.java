package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connectDB {
	

	
	public static void addDataToDB(int ID, String NamePlayer, int score, String times) {}
	public static void main(String []args) {
		String url = "jdbc:sqlserver://NGUYEN-VIET-HUNG\\SQLEXPRESS:1433;databaseName=player;trustServerCertificate=true";
		String USERNAME = "sa";
	    String PASSWORD = "102004";
	    try {
			Connection conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
			System.out.println("khe ");
		} catch (SQLException e) {
			System.out.println("kheeee ");
			e.printStackTrace();
		}
	}
}

