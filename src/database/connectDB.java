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
        this.connectionUrl = "jdbc:sqlserver://PHANDINHNGHIA\\SQLEXPRESS02:1433;databaseName=player;user=sa;password=nghia;trustServerCertificate=true";
    }

    public void addDataToDB(int ID,String NamePlayer,  int Score,String Times ) {
        try (Connection con = DriverManager.getConnection(this.connectionUrl);
             Statement stmt = con.createStatement()) {

            String SQL =
 "INSERT INTO player (ID, NamePlayer, Score, Times) VALUES ("+ ID  + ", '" + NamePlayer + "', " + Score + ", '" + Times + "')";
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
            	arrInformation.add(new PlayerInformation(rs.getString("ID"),rs.getString("NamePlayer"),rs.getString("Score"),rs.getString("Times")));
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
  
		return arrInformation;
    }

    public static void main(String[] args) {
    	ArrayList<PlayerInformation> arrInformation = new ArrayList<>();
        connectDB a = new connectDB();
        a.addDataToDB(5, "User5", 55, "2024-03-28 07:30:00");
        arrInformation = a.getDataFromDB();
        System.out.println(arrInformation.get(1).NamePlayer);
    }
}