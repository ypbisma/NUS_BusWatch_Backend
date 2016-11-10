package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


import com.google.gson.Gson;

public class BusLocationManager {
	private static final String API_URL = "https://api.ami-lab.org";
	private static final String VENIAM_LOCATION_BRANCH = "/api/v1/veniam/location?token=";
	private String busLocationUrl;
	private String busInfo;
	private BusLocation[] busLocationList;
	
	public BusLocationManager(String token){
		this.busLocationUrl = API_URL+VENIAM_LOCATION_BRANCH+token;
		
	}
	
	public void syncBusInfo() throws Exception{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(this.busLocationUrl);
		
		HttpResponse response = client.execute(request);
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		
		String line = "";
		
		while ((line = reader.readLine())!= null){
			result.append(line);
		}
		
		this.setBusInfo(result.toString());
		
		Gson gson = new Gson();
			this.setBusLocationList(gson.fromJson(result.toString(), BusLocation[].class));
	}

	//setters and getters
	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	public String getBusInfo() {
		return busInfo;
	}

	public BusLocation[] getBusLocationList() {
		return busLocationList;
	}

	public void setBusLocationList(BusLocation[] busInfoList) {
		this.busLocationList = busInfoList;
	}
}
