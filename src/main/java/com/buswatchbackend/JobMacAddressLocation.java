package com.buswatchbackend;

import java.util.Date;

public class JobMacAddressLocation {
	private MacAddressLocationManager macAddressLocationManager;
	private Date now;
	private DatabaseWriter macAddressLocationWriter = new DatabaseWriter();
	public JobMacAddressLocation(String token) throws Exception{
		macAddressLocationManager = new MacAddressLocationManager(token);	
	}
	
	public void execute() throws Exception{
		macAddressLocationManager.syncMacAddressLocation();
		MacAddressLocation desiredLocation = macAddressLocationManager.getMacAddressLocation();
		String locationInformation = desiredLocation.getFloor() + "\t"
				+ desiredLocation.getGeoCoordinate().getLatitude() + "\t"
				+ desiredLocation.getGeoCoordinate().getLongitude();
		System.out.println(locationInformation);
		
		macAddressLocationWriter.insertMacAddressLocation(MacAddressLocationManager.getMacaddress(),
				desiredLocation.getGeoCoordinate().getLatitude().toString(),
				desiredLocation.getGeoCoordinate().getLongitude().toString(),
				desiredLocation.getFloor());
	
	}
}
