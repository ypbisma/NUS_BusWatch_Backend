package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;

public class JobMacAddressLocation {
	private MacAddressLocationManager macAddressLocationManager;
	private Date now;
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
		
		//read old file
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader("location_tracker.txt"));
		String line = br.readLine();
		
		while (line!=null){
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String previousLines = sb.toString();
		br.close();
		
		//write new file
		now = new Date();
		String timeNow = "Time is: " + now;

		PrintWriter outputfile = new PrintWriter("location_tracker.txt");
	    outputfile.print(previousLines + "\n"+ timeNow + "\n" + locationInformation + "\n");
	    outputfile.close();
	}
}
