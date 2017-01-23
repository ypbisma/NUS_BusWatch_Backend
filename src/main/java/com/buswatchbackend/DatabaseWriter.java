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
		 String updateSql = "UPDATE LastBusInformation set gpsTime = ?, latitude = ?, longitude = ?, heading = ? where nodeId = ?";
		 String insertSql = "INSERT INTO LastBusInformation (nodeId, vehicleSerial, gpsTime, latitude, longitude, heading)"
	        		+ " VALUES(?,?,?,?,?,?)";
		 String selectSql = "Select count(*) from LastBusInformation WHERE nodeId = ?";
		 
		 try(Connection conn = this.connect();
				 PreparedStatement updateStatement = conn.prepareStatement(updateSql);
				 PreparedStatement selectStatement = conn.prepareStatement(selectSql);
				 PreparedStatement insertStatement = conn.prepareStatement(insertSql);)
		  {
			 int count = 0;
			 selectStatement.setString(1, nodeId);
			 ResultSet resultSet = selectStatement.executeQuery();
			 if(resultSet.next()){
				 count = resultSet.getInt(1);
			 }
			 
			 if(count>0){
				// set the preparedstatement parameters
				 updateStatement.setString(1,gpsTime);
				 updateStatement.setString(2,latitude);
				 updateStatement.setString(3, longitude);
				 updateStatement.setString(4, heading);
				 updateStatement.setString(5, nodeId);
			    // call executeUpdate to execute our sql update statement
			    updateStatement.executeUpdate();
			    updateStatement.close();
			 }
			 else {
				insertStatement.setString(1, nodeId);
		        insertStatement.setString(2, vehicleSerial);
		        insertStatement.setString(3, gpsTime);
		        insertStatement.setString(4, latitude);
		        insertStatement.setString(5, longitude);
		        insertStatement.setString(6, heading);
		        insertStatement.executeUpdate();
			 }
		  }
		 catch (Exception e){
			 System.out.println(e.getMessage());
		 }
	 }
}

