package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controller.PlayerInformation;

public class connectDB {
    private String connectionUrl;

    public connectDB() {
        this.connectionUrl = "jdbc:sqlserver://NGUYEN-VIET-HUNG\\SQLEXPRESS:1433;databaseName=player;user=sa;password=102004;trustServerCertificate=true";
    }

    public void addDataToDB(int ID,String UserName,  int Score,String Times ) {
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL =
 "INSERT INTO player (ID, UserName, Score, Times) VALUES ("+ ID  + ", '" + UserName + "', " + Score + ", '" + Times + "')";
            stmt.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PlayerInformation> getDataFromDB() {
    	ArrayList<PlayerInformation> arrInformation = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL = "SELECT * FROM player";
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
            	arrInformation.add(new PlayerInformation(rs.getString("ID"),rs.getString("UserName"),rs.getString("Score"),rs.getString("Times")));
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
		return arrInformation;
    }

}