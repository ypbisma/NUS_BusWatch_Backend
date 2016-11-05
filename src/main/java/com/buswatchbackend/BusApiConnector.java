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
	public static final String TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE0NzgzNDI0MTQsImF1ZCI6Ik5VUyBEYXRhIEFQSSIsImlzcyI6IkFNSSBMYWIsIE5VUyIsInN1YiI6IkEwMTE1OTAyIiwiZXhwIjoxNDc4NDI4ODE0fQ.lAuOkDsEf4tgjc8vzi4G931xi5Md9u6i3e4gG9jvqa8fmUjN6B8qdnGdMNyMQbjZDYsX-vSN4xVQofHO8NBXFNdVESyIkr0yxCigjB6vNk7Ph9NwvkQ9lXlllgYUyyZjkpsIdVK6J2-Emn3VVF0KE4eRHzPdqFEiU27JVBniWN4xD0JWlrjynF6STX_o-k9UvI-etw9hNU1fs7Xu6A4-dKdZHSg0mLBTrWM7TTzWF6kl5kQlcG6QzB1mQANkfCeF3EAdAXeSCqg59u_5DruZkkIBgjCT1uGDvZACR9j4MUclxpVnjJGPkuT5eiDDQB0XOqrgp5yJcVAe7TBQ2TC6AA";
	public static final String VENIAM_BRANCH = "/api/v1/veniam/location?token=";
	
	private String apiUrl;
	private BusInfo[] busInfoList;
	
	public static void main (String[] args){
		BusApiConnector apiConnector = new BusApiConnector();
		//String token = apiConnector.login("/api/v1/...");
		apiConnector.connect();
		apiConnector.printBusInfoList();
		//databaseConnector.save(test);
	}
	
	public String login(String loginPathUrl) {
		// Do login
		return "";
	}
	
	public void connect () {
		try
		{
			String query = API_URL+VENIAM_BRANCH+TOKEN;
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