package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BusInfoManager {
	
	private String busInfoUrl;
	private String busInfo;
	
	public BusInfoManager(String url){
		this.busInfoUrl = url;
		
	}
	
	public void syncBusInfo() throws Exception{
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;
		
		url = new URL(this.busInfoUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		
		reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      	stringBuilder = new StringBuilder();

      	String line = null;
      	while((line = reader.readLine()) != null)
      	{
      		stringBuilder.append(line + "\n");

      	}
      	
      	this.setBusInfo(stringBuilder.toString());
	}

	public void setBusInfo(String busInfo) {
		this.busInfo = busInfo;
	}
	public String getBusInfo() {
		return busInfo;
	}
}
