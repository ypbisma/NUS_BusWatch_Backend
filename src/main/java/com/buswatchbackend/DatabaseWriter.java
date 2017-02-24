package com.buswatchbackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


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

	// BUS INFORMATION
	public void insertBusInformation(String nodeId, String vehicleSerial, String gpsTime, String latitude,
			String longitude, String heading) {
		Integer lastRow = 0;

		String insertSql = "INSERT INTO BusInformation (nodeId, vehicleSerial, gpsTime, latitude, longitude, heading)"
				+ " VALUES(?,?,?,?,?,?)";
		String lastNodeId = null;
		String lastVehicleSerial = null;
		String lastLatitude = null;
		String lastLongitude = null;
		String lastHeading = null;

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(insertSql);) {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM BusInformation");

			while (res.next()) {

				lastNodeId = res.getString("nodeId");
				lastVehicleSerial = res.getString("vehicleSerial");
				lastLatitude = res.getString("Latitude");
				lastLongitude = res.getString("Longitude");
				lastHeading = res.getString("Heading");
				lastRow = lastRow + 1;
			}

			String updateSql = "UPDATE BusInformation set gpsTime = ? where rowid = ?";
			PreparedStatement updateStatement = conn.prepareStatement(updateSql);

			if (lastRow != 0 && lastNodeId.equalsIgnoreCase(nodeId) && lastVehicleSerial.equalsIgnoreCase(vehicleSerial)
					&& lastLatitude.equalsIgnoreCase(latitude) && lastLongitude.equalsIgnoreCase(longitude)
					&& lastHeading.equalsIgnoreCase(heading)) {

				updateStatement.setString(1, gpsTime);
				updateStatement.setString(2, lastRow.toString());

				updateStatement.executeUpdate();

			}

			else {

				pstmt.setString(1, nodeId);
				pstmt.setString(2, vehicleSerial);
				pstmt.setString(3, gpsTime);
				pstmt.setString(4, latitude);
				pstmt.setString(5, longitude);
				pstmt.setString(6, heading);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertLastBusInformation(String nodeId, String vehicleSerial, String gpsTime, String latitude,
			String longitude, String heading) {
		String updateSql = "UPDATE LastBusInformation set gpsTime = ?, latitude = ?, longitude = ?, heading = ? where nodeId = ?";
		String insertSql = "INSERT INTO LastBusInformation (nodeId, vehicleSerial, gpsTime, latitude, longitude, heading)"
				+ " VALUES(?,?,?,?,?,?)";
		String selectSql = "Select count(*) from LastBusInformation WHERE nodeId = ?";

		try (Connection conn = this.connect();
				PreparedStatement updateStatement = conn.prepareStatement(updateSql);
				PreparedStatement selectStatement = conn.prepareStatement(selectSql);
				PreparedStatement insertStatement = conn.prepareStatement(insertSql);) {
			int count = 0;
			selectStatement.setString(1, nodeId);
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			if (count > 0) {
				// set the preparedstatement parameters
				updateStatement.setString(1, gpsTime);
				updateStatement.setString(2, latitude);
				updateStatement.setString(3, longitude);
				updateStatement.setString(4, heading);
				updateStatement.setString(5, nodeId);
				// call executeUpdate to execute our sql update statement
				updateStatement.executeUpdate();
				// updateStatement.close();
			} else {
				insertStatement.setString(1, nodeId);
				insertStatement.setString(2, vehicleSerial);
				insertStatement.setString(3, gpsTime);
				insertStatement.setString(4, latitude);
				insertStatement.setString(5, longitude);
				insertStatement.setString(6, heading);
				insertStatement.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// MACADRESS

	public void insertMacAddressLocation(String macAddress, String latitude, String longitude, String zone,
			String building, String floor) {
		String sql = "INSERT INTO MacLocation (macAddress, latitude, longitude, zone, building, floor)"
				+ " VALUES(?,?,?,?,?,?)";
		String lastMacAddress = null;
		String lastLatitude = null;
		String lastLongitude = null;
		String lastZone = null;
		String lastBuilding = null;
		String lastFloor = null;
		int lastRow = 0;

		try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			Statement statement = conn.createStatement();
			ResultSet res = statement.executeQuery("SELECT * FROM MacLocation");

			while (res.next()) {

				lastMacAddress = res.getString("macAddress");
				lastLatitude = res.getString("latitude");
				lastLongitude = res.getString("longitude");
				lastZone = res.getString("zone");
				lastBuilding = res.getString("building");
				lastFloor = res.getString("floor");
				lastRow = lastRow + 1;
			}

			if (lastRow == 0 || !(lastLatitude.equalsIgnoreCase(latitude) && lastLongitude.equalsIgnoreCase(longitude)
					&& lastZone.equalsIgnoreCase(zone) && lastBuilding.equalsIgnoreCase(building)
					&& lastFloor.equalsIgnoreCase(floor))) {

				pstmt.setString(1, macAddress);
				pstmt.setString(2, latitude);
				pstmt.setString(3, longitude);
				pstmt.setString(4, zone);
				pstmt.setString(5, building);
				pstmt.setString(6, floor);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertLastMacAddressLocation(String macAddress, String latitude, String longitude, String zone,
			String building, String floor) {
		String insertSql = "INSERT INTO LastMacLocation (macAddress, latitude, longitude, zone, building, floor)"
				+ " VALUES(?,?,?,?,?,?)";
		String updateSql = "UPDATE LastMacLocation set latitude = ?, longitude = ?, zone = ?, building = ?, floor = ? where macAddress = ?";
		String selectSql = "Select count(*) from LastMacLocation WHERE macAddress = ?";

		try (Connection conn = this.connect();
				PreparedStatement updateStatement = conn.prepareStatement(updateSql);
				PreparedStatement selectStatement = conn.prepareStatement(selectSql);
				PreparedStatement insertStatement = conn.prepareStatement(insertSql);) {
			int count = 0;
			selectStatement.setString(1, macAddress);
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			if (count > 0) {
				// set the preparedstatement parameters
				updateStatement.setString(1, latitude);
				updateStatement.setString(2, longitude);
				updateStatement.setString(3, zone);
				updateStatement.setString(4, building);
				updateStatement.setString(5, floor);
				updateStatement.setString(6, macAddress);
				updateStatement.executeUpdate();
			} else {
				insertStatement.setString(1, macAddress);
				insertStatement.setString(2, latitude);
				insertStatement.setString(3, longitude);
				insertStatement.setString(4, zone);
				insertStatement.setString(5, building);
				insertStatement.setString(6, floor);
				insertStatement.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// DEVICE COUNT

	public void insertDeviceCount(String zone, String building, String floor, String deviceCount) {
		String insertSql = "INSERT INTO DeviceCount (zone, building, floor, count)" + " VALUES(?,?,?,?)";
		String updateSql = "UPDATE DeviceCount set count = ? WHERE floor = ?";
		String selectSql = "Select count(*) from DeviceCount WHERE floor = ?";

		try (Connection conn = this.connect();
				PreparedStatement updateStatement = conn.prepareStatement(updateSql);
				PreparedStatement selectStatement = conn.prepareStatement(selectSql);
				PreparedStatement insertStatement = conn.prepareStatement(insertSql);) {

			int count = 0;
			selectStatement.setString(1, floor);
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}

			if (count > 0) {
				// set the preparedstatement parameters
				updateStatement.setString(1, deviceCount);
				updateStatement.setString(2, floor);
				updateStatement.executeUpdate();
			} else {

				insertStatement.setString(1, zone);
				insertStatement.setString(2, building);
				insertStatement.setString(3, floor);
				insertStatement.setString(4, deviceCount);
				insertStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertZoneBuildingFloor(String zone, String building, String floor) {
		String insertSql = "INSERT INTO ZoneBuildingFloor (zone, building, floor)" + " VALUES(?,?,?)";
		String selectSql = "Select count(*) from ZoneBuildingFloor WHERE floor = ?";

		try (Connection conn = this.connect();
				PreparedStatement insertStatement = conn.prepareStatement(insertSql);
				PreparedStatement selectStatement = conn.prepareStatement(selectSql);) {
			int count = 0;
			selectStatement.setString(1, floor);
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			if (count <= 0) {
				insertStatement.setString(1, zone);
				insertStatement.setString(2, building);
				insertStatement.setString(3, floor);
				insertStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<ZoneBuildingFloor> getZoneBuildingFloor() {
		ArrayList<ZoneBuildingFloor> zoneBuildingFloorList = new ArrayList<>();
		try (Connection conn = this.connect();) {
			Statement stmt;
			stmt = conn.createStatement();
			String sql = "SELECT * from ZoneBuildingFloor";
			ResultSet res;
			res = stmt.executeQuery(sql);
			
			while (res.next()) {
				ZoneBuildingFloor zoneBuildingFloorItem = new ZoneBuildingFloor(res.getString("zone"),
						res.getString("building"), res.getString("floor"));
				zoneBuildingFloorList.add(zoneBuildingFloorItem);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return zoneBuildingFloorList;
	}
	
	public void  writeExcelFile(){
		try (Connection conn = this.connect();
				Statement st = conn.createStatement();){
			ResultSet rs = st.executeQuery("Select * from DeviceCount");
			
			//HSSFWorkbook wb = new HSSFWorkbook();
			
				
			
			
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
