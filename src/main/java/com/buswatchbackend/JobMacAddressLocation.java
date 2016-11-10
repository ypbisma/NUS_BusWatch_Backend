package com.buswatchbackend;

public class JobMacAddressLocation {
	private MacAddressLocationManager macAddressLocationManager;
	
	public JobMacAddressLocation(String token) throws Exception{
		
		macAddressLocationManager = new MacAddressLocationManager(token);	
	}
	
	public void execute() throws Exception{
		macAddressLocationManager.syncMacAddressLocation();
		MacAddressLocation desiredLocation = macAddressLocationManager.getMacAddressLocation();
		System.out.println(desiredLocation.getFloor() + "\t"
				+ desiredLocation.getGeoCoordinate().getLatitude() + "\t"
				+ desiredLocation.getGeoCoordinate().getLongitude());
	}
}
