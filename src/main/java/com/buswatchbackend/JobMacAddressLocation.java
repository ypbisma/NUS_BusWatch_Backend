package com.buswatchbackend;

import java.util.Date;

public class JobMacAddressLocation {
	private MacAddressLocationManager macAddressLocationManager;
	private Date now;
	private DatabaseWriter macAddressLocationWriter = new DatabaseWriter();
	private MacAddressLocationFloorConverter floorConverter = new MacAddressLocationFloorConverter();
	
	public JobMacAddressLocation(String token) throws Exception{
		macAddressLocationManager = new MacAddressLocationManager(token);	
	}
	
	public void execute() throws Exception{
		macAddressLocationManager.syncMacAddressLocation();
		MacAddressLocation desiredLocation = macAddressLocationManager.getMacAddressLocation();
		
		floorConverter.convert(desiredLocation.getFloor());
		
		macAddressLocationWriter.insertMacAddressLocation(MacAddressLocationManager.getMacaddress(),
				desiredLocation.getGeoCoordinate().getLatitude().toString(),
				desiredLocation.getGeoCoordinate().getLongitude().toString(),
				floorConverter.getConvertZone(),
				floorConverter.getConvertBuilding(),
				floorConverter.getConvertFloor());
		
		macAddressLocationWriter.insertLastMacAddressLocation(MacAddressLocationManager.getMacaddress(),
				desiredLocation.getGeoCoordinate().getLatitude().toString(),
				desiredLocation.getGeoCoordinate().getLongitude().toString(),
				floorConverter.getConvertZone(),
				floorConverter.getConvertBuilding(),
				floorConverter.getConvertFloor());
	}
}
