package com.buswatchbackend;

import java.util.ArrayList;
import java.util.Vector;

public class JobDeviceCount {
	private static String token;
	private DeviceCountManager deviceCountManager;
	private String zoneName;
	private String buildingName;
	private String floorName;
	private String[] allZones;
	private String[] allBuildings;
	private String[] allFloors;
	private Integer i;
	private Integer j;
	private Integer k;

	private ArrayList<ZoneBuildingFloor> zoneBuildingFloorList = new ArrayList<>();

	private DatabaseWriter deviceCountWriter = new DatabaseWriter();

	public JobDeviceCount(String token) throws Exception {
		// feed token to get BusInfo
		deviceCountManager = new DeviceCountManager(token);
	}

	public void execute() throws Exception {

		zoneBuildingFloorList = deviceCountWriter.getZoneBuildingFloor();
		for (ZoneBuildingFloor item : zoneBuildingFloorList) {
			deviceCountManager.setZoneName(item.getZone());
			deviceCountManager.setBuildingName(item.getBuilding());
			deviceCountManager.setFloorName(item.getFloor());
			deviceCountManager.syncDeviceCount();
			writeDeviceCount();
		}

		// deviceCountManager.syncZoneList();
		// allZones = deviceCountManager.getZoneList();
		// for (String i: allZones) {
		// zoneName = i;
		// System.out.println("here");
		// deviceCountManager.syncBuildingList(zoneName);
		// allBuildings = deviceCountManager.getBuildingList();
		// for (String j: allBuildings) {
		// buildingName = j;
		// deviceCountManager.syncFloorList(zoneName, buildingName);
		// allFloors = deviceCountManager.getFloorList();
		//
		// for (String k : allFloors) {
		// writeZoneBuildingFloor(zoneName, buildingName, k);
		// }
		// }
	}

	public void writeDeviceCount() {
		deviceCountWriter.insertDeviceCount(deviceCountManager.getZoneName(), deviceCountManager.getBuildingName(),
				deviceCountManager.getFloorName(), deviceCountManager.getDeviceCount().getCount().toString());
		
		try {
			deviceCountWriter.writeExcelFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeZoneBuildingFloor(String zone, String building, String floor) {
		deviceCountWriter.insertZoneBuildingFloor(zone, building, floor);
	}
}
