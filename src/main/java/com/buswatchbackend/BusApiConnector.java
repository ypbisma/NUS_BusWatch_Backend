package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;


import com.google.gson.Gson;

public class BusApiConnector	
{
	public static final String API_URL = "https://api.ami-lab.org";
	public static final String VENIAM_BRANCH = "/api/v1/veniam/location?token=";
	public static final String LOGIN_BRANCH = "/api/v1/user/login";
	
	private BusInfo[] busInfoList;
	private static String token;
	
	public static void main (String[] args) throws Exception{
		BusApiConnector apiConnector = new BusApiConnector();
		LoginManager loginManager = new LoginManager(API_URL+LOGIN_BRANCH);
		//apiConnector.login();
		loginManager.login();
		token = loginManager.getToken();
		apiConnector.populateList();
		apiConnector.printBusInfoList();
		//databaseConnector.save(test);
	}
	
	
	public void populateList () {
		try
		{
			String query = API_URL+VENIAM_BRANCH+token;
			String results = getInfoFromApi(query);

			Gson gson = new Gson();
			this.busInfoList = gson.fromJson(results, BusInfo[].class);
		}
		catch (UnknownHostException e) {
			System.out.println("No connection dude!");
		}	
		catch (Exception e){
			e.printStackTrace();
		} 
	}


private String getInfoFromApi(String desiredUrl)
throws Exception
{
	URL url = null;
	BufferedReader reader = null;
	StringBuilder stringBuilder;
	try
	{
		url = new URL(desiredUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);

		connection.setReadTimeout(15*1000);
		connection.connect();

		reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      	stringBuilder = new StringBuilder();

      	String line = null;
      	while((line = reader.readLine()) != null)
      	{
      		stringBuilder.append(line + "\n");

      	}
      	return stringBuilder.toString();
   
	}
	catch (Exception e)
	{
		e.printStackTrace();
		throw e;
	}
	finally
	{
		if(reader!= null)
		{
			try
			{
				reader.close();
			}
			catch(IOException ioe)
			{
				ioe.printStackTrace();
				}
			}
		}
	}

private void printBusInfoList(){
	String output;
	for (BusInfo busInfo : this.busInfoList) {
		//if(busInfo.getNodeId()==2052) {
			output = busInfo.getNodeId().toString() + " " 
					+ busInfo.getLatitude().toString() + " "
					+ busInfo.getLongitude().toString();
			System.out.println(output);
	//}
			}
	}
}