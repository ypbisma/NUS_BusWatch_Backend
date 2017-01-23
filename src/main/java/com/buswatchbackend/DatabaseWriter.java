package com.buswatchbackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 
	 public void insertBusInformation(String nodeId, String vehicleSerial, String gpsTime, String latitude, String longitude, String heading) {
	        String sql = "INSERT INTO BusInformation (nodeId, vehicleSerial, gpsTime, latitude, longitude, heading)"
	        		+ " VALUES(?,?,?,?,?,?)";
	 
	        try (Connection conn = this.connect();
	           	PreparedStatement pstmt = conn.prepareStatement(sql);)
	        {
	        	pstmt.setString(1, nodeId);
	        	pstmt.setString(2, vehicleSerial);
	        	pstmt.setString(3, gpsTime);
	        	pstmt.setString(4, latitude);
	        	pstmt.setString(5, longitude);
	        	pstmt.setString(6, heading);
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
	 
	 public void insertLastBusInformation(String nodeId, String vehicleSerial, String gpsTime, String latitude, String longitude, String heading){
		 String sql = "INSERT INTO LastBusInformation (nodeId, vehicleSerial, gpsTime, latitude, longitude, heading)"
	        		+ " VALUES(?,?,?,?,?,?)";
		 
		 
	        try (Connection conn = this.connect();
	           	PreparedStatement pstmt = conn.prepareStatement(sql);)
	        {
	        	pstmt.setString(1, nodeId);
	        	pstmt.setString(2, vehicleSerial);
	        	pstmt.setString(3, gpsTime);
	        	pstmt.setString(4, latitude);
	        	pstmt.setString(5, longitude);
	        	pstmt.setString(6, heading);
	        	pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		 
		 
		 System.out.println("here");
		 String updateSql = "UPDATE LastBusInformation set gpsTime = ?, latitude = ? where nodeId = ?";
		 String querySql = "Select count(*) from LastBusInformation WHERE nodeId = ?";
		 try(Connection conn = this.connect();
				 PreparedStatement pstmt = conn.prepareStatement(updateSql);
				 PreparedStatement qstmt = conn.prepareStatement(querySql))
		  {
			 int count = 0;
			 qstmt.setString(1, "2021");
			 ResultSet resultSet = qstmt.executeQuery();
			 if(resultSet.next()){
				 count = resultSet.getInt(1);
			 }
			 
			 if(count>0){
				// set the preparedstatement parameters
				 pstmt.setString(1,gpsTime);
				 pstmt.setString(2,latitude);
				 pstmt.setString(3, nodeId);
			    // call executeUpdate to execute our sql update statement
			    pstmt.executeUpdate();
			    pstmt.close();
			 }
		  }
		 catch (Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
}

