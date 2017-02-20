package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class BusSessionManager {
	
	private static final String API_URL = "https://api.ami-lab.org";
	private static final String VENIAM_SESSION_BRANCH = "/api/v1/veniam/sessions?token=";
	
	private BusSession[] busSessionList;
	private String busSessionUrl;
	private String busSession;
	
	
	public BusSessionManager(String token){
		this.busSessionUrl = API_URL+VENIAM_SESSION_BRANCH+token;
	}
	
	public void syncSessions () throws Exception {
		try{
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(this.busSessionUrl);
		
		HttpResponse response = client.execute(request);
		
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(response.getEntity().getContent()));
		
		StringBuffer result = new StringBuffer();
		
		String line = "";
		
		while ((line = reader.readLine())!= null){
			result.append(line);
		}
		
		this.setBusSession(result.toString());
		
		Gson gson = new Gson();
			this.setBusSessionList(gson.fromJson(result.toString(), BusSession[].class));
		}
		catch(UnknownHostException e){
			System.out.println("no internet connection!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setBusSession(String busSession) {
		this.busSession = busSession;
	}

	public String getBusSession() {
		return busSession;
	}

	public BusSession[] getBusSessionList() {
		return busSessionList;
	}

	public void setBusSessionList(BusSession[] busSessionList) {
		this.busSessionList = busSessionList;
	}
}
