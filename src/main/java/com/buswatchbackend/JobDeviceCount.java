package com.buswatchbackend;

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

	private DatabaseWriter deviceCountWriter = new DatabaseWriter();

	public JobDeviceCount(String token) throws Exception {
		// feed token to get BusInfo
		deviceCountManager = new DeviceCountManager(token);
	}

	public void execute() throws Exception {
		deviceCountManager.syncZoneList();
		allZones = deviceCountManager.getZoneList();
		
		for (i = 0; i < 2; i++) {
			zoneName = allZones[3];
			
			deviceCountManager.syncBuildingList(zoneName);
			allBuildings = deviceCountManager.getBuildingList();
			for (j = 0; j < allBuildings.length; j++) {
				buildingName = allBuildings[j];
				deviceCountManager.syncFloorList(zoneName, buildingName);
				allFloors = deviceCountManager.getFloorList();
				for (k = 0; k < allFloors.length; k++) {
					floorName = allFloors[k];
					
					deviceCountManager.setZoneName(zoneName);
					deviceCountManager.setBuildingName(buildingName);
					deviceCountManager.setFloorName(floorName);
					System.out.println(zoneName + buildingName + floorName);
					deviceCountManager.syncDeviceCount();
					writeDeviceCount();
				}
			}
			
		}
	}

	public void writeDeviceCount() {
		deviceCountWriter.insertDeviceCount(deviceCountManager.getZoneName(), deviceCountManager.getBuildingName(),
				deviceCountManager.getFloorName(), deviceCountManager.getDeviceCount().getCount().toString());
	}

}
