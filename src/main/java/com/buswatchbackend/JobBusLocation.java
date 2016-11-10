package com.buswatchbackend;

import java.util.Vector;

public class JobBusLocation {
	private static String token;
	private static Integer busNumber;
	private BusLocationManager busLocationManager;
	private Vector<BusLocation> desiredBusLocationList = new Vector<BusLocation>();

	
	public JobBusLocation(Integer inputBusNumber, String token) throws Exception{
		busNumber = inputBusNumber;
		
		//feed token to get BusInfo
		LoginManager loginManager = new LoginManager();
		loginManager.login();
		token = loginManager.getToken();
		busLocationManager = new BusLocationManager(token);
	}
	
	public void execute() throws Exception{
		
		busLocationManager.syncBusInfo();
		
		printBusInfoList(busLocationManager.getBusLocationList());
	}
	
	public void printBusInfoList(BusLocation[] busInfoList){
		String Output;

		BusLocation lastBusLocation = new BusLocation();
		
		for (BusLocation busLocation : busInfoList) {
			int localBusNumber = busNumber;
			
			if(busLocation.getNodeId()==localBusNumber) {
				lastBusLocation = busLocation;
			}

		}
		
		desiredBusLocationList.add(lastBusLocation);
		
		for(BusLocation e : desiredBusLocationList){
			Output = e.getNodeId().toString() + " \t" 
					+ e.getVehicleSerial().toString() + " \t"
					+ e.getLatitude().toString() + " \t"
					+ e.getLongitude().toString() + " \t"
					+ e.getSpeed().toString()+ "\t"
					+ e.getGpsTime();
				
			System.out.println(Output);
		}
		}
}
	

