package com.buswatchbackend;

import java.net.UnknownHostException;

import com.google.gson.Gson;

public class BusApiConnector	
{
	public static final String API_URL = "https://api.ami-lab.org";
	public static final String VENIAM_BRANCH = "/api/v1/veniam/location?token=";
	public static final String LOGIN_BRANCH = "/api/v1/user/login";
	
	private BusInfo[] busInfoList;
	private static String token;
	
	public static void main (String[] args) throws Exception {
		try{
		BusApiConnector apiConnector = new BusApiConnector();
		LoginManager loginManager = new LoginManager(API_URL+LOGIN_BRANCH);
		
		//login and obtain token
		loginManager.login();
		token = loginManager.getToken();
		
		//feed token to get BusInfo
		BusInfoManager busInfoManager = new BusInfoManager(API_URL+VENIAM_BRANCH+token);
		busInfoManager.syncBusInfo();
		
		
		apiConnector.populateList(busInfoManager.getBusInfo());
		apiConnector.printBusInfoList();
		//databaseConnector.save(test);
		}
		//when there is no internet connection
		catch(UnknownHostException e){
			System.out.println("No connection dude!");
		}
	}
	
	
	public void populateList (String results) {
		try
		{

			Gson gson = new Gson();
			this.busInfoList = gson.fromJson(results, BusInfo[].class);
		}
		catch (Exception e){
			e.printStackTrace();
		} 
	}


private void printBusInfoList(){
	String output;
	for (BusInfo busInfo : this.busInfoList) {
		if(busInfo.getNodeId()==2027) {
			output = busInfo.getNodeId().toString() + " " 
					+ busInfo.getLatitude().toString() + " "
					+ busInfo.getLongitude().toString() + " "
					+ busInfo.getSpeed().toString();
			System.out.println(output);
	}
			}
	}
}