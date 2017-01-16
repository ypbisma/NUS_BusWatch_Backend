package com.buswatchbackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter {
	 private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:/Users/Bisma/AndroidStudioProjects/serverapp/nuswatch.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println("problem here");
	        }
	        return conn;
	       
	    }
	 public void insertBusInformation(String nodeId, String gpsTime, String latitude, String longitude, String heading) {
	        String sql = "INSERT INTO BusInformation (nodeId, gpsTime, latitude, longitude, heading)"
	        		+ " VALUES(?,?,?,?,?)";
	 
	        try (Connection conn = this.connect();
	           	PreparedStatement pstmt = conn.prepareStatement(sql);)
	        {
	        	pstmt.setString(1, nodeId);
	        	pstmt.setString(2, gpsTime);
	        	pstmt.setString(3, latitude);
	        	pstmt.setString(4, longitude);
	        	pstmt.setString(5, heading);
	        	pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public void insertMacAddressLocation(String macAddress, String latitude, String longitude, String floor){
		 String sql = "INSERT INTO MacLocation (macAddress, latitude, longitude, floor)"
	        		+ " VALUES(?,?,?,?)";
		 
		 try(Connection conn = this.connect();
				 PreparedStatement pstmt = conn.prepareStatement(sql);)
		{
				pstmt.setString(1, macAddress);
	        	pstmt.setString(2, latitude);
	        	pstmt.setString(3, longitude);
	        	pstmt.setString(4, floor);
	        	pstmt.executeUpdate();
		}
		 catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	 }
}
