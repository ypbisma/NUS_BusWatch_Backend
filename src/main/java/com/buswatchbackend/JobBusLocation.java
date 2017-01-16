package com.buswatchbackend;

import java.util.Vector;

public class JobBusLocation {
	private static String token;
	private static Integer busNumber;
	private BusLocationManager busLocationManager;
	private Vector<BusLocation> desiredBusLocationList = new Vector<BusLocation>();
	private String output;
	private DatabaseWriter busLocationWriter = new DatabaseWriter();

	
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
		
		writeBusInfoList(busLocationManager.getBusLocationList());
		
		// 1. Establish database connection
		// 2. Write busLocationList to database
		// 3. Close database connection
	}
	
	public void writeBusInfoList(BusLocation[] busInfoList){
		String Output;

		BusLocation lastBusLocation = new BusLocation();
		
		for (BusLocation busLocation : busInfoList) {
			int localBusNumber = busNumber;
				lastBusLocation = busLocation;
		}
		
		desiredBusLocationList.add(lastBusLocation);
		
		for(BusLocation e : desiredBusLocationList){
			Output = e.getNodeId().toString() + " \t" 
					+ e.getVehicleSerial().toString() + " \t"
					+ e.getLatitude().toString() + " \t"
					+ e.getLongitude().toString() + " \t"
					+ e.getSpeed().toString()+ "\t"
					+ e.getHeading().toString()+ "\t"
					+ e.getGpsTime();
				
			System.out.println(Output);
			
			busLocationWriter.insertBusInformation(e.getNodeId().toString(), 
					e.getGpsTime().toString(), e.getLatitude().toString(), 
					e.getLongitude().toString(), e.getHeading().toString());
		}
	}
}
	

