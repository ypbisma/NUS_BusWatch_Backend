package com.buswatchbackend;

import java.util.Vector;

public class JobDeviceCount {
	private static String token;
	private DeviceCountManager deviceCountManager;
	private String zoneName = "KR-AKIENG";
	private String buildingName = "AKIENG-E3";
	private String floorName = "AKIENG-E3-03";
	private String output;
	private DatabaseWriter deviceCountWriter = new DatabaseWriter();

	public JobDeviceCount(String token) throws Exception {
		// feed token to get BusInfo
		deviceCountManager = new DeviceCountManager(token);
	}

	public void execute() throws Exception {
		deviceCountManager.setZoneName(zoneName);
		deviceCountManager.setBuildingName(buildingName);
		deviceCountManager.setFloorName(floorName);
		deviceCountManager.syncDeviceCount();

		writeDeviceCount();
	}

	public void writeDeviceCount() {
		deviceCountWriter.insertDeviceCount(deviceCountManager.getZoneName(), deviceCountManager.getBuildingName(),
				deviceCountManager.getFloorName(), deviceCountManager.getDeviceCount().getCount().toString());
	}
}
