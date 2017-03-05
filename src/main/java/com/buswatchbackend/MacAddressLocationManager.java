package com.buswatchbackend;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

public class MacAddressLocationManager {

	private static final String API_URL = "https://api.ami-lab.org";
	private static final String MAC_ADDRESS_DIRECTORY = "/api/v1/cisco/device/mac/";
	 private static final String macAddress = "10:A5:D0:24:1A:9F";//
	// BismaSmartphone
//	private static final String macAddress = "a8:bb:cf:07:f2:20";// Nia
//	 private static final String macAddress = "a8:bb:cf:05:07:76";// Bisma

	private String macAddressUrl;
	private String macAddressData;
	private MacAddressLocation macAddressLocation;

	public MacAddressLocationManager(String token) {
		String newMacAddress = macAddress.replaceAll(":", "%3A");
		this.macAddressUrl = API_URL + MAC_ADDRESS_DIRECTORY + newMacAddress + "?token=" + token;
	}

	public void syncMacAddressLocation() throws Exception {
		try {

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(this.macAddressUrl);
			HttpResponse response = client.execute(request);

			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();

			String line = "";

			while ((line = reader.readLine()) != null) {
				result.append(line);
			}

			this.setMacAddressData(result.toString());

			Gson gson = new Gson();
			if (gson.toString().equals("{}")){
				System.out.println("object is empty");
			}
			this.setMacAddressLocation(gson.fromJson(result.toString(), MacAddressLocation.class));
		} finally {
			//
		}
	}

	public void setMacAddressData(String macAddressData) {
		this.macAddressData = macAddressData;
	}

	public void setMacAddressLocation(MacAddressLocation macAddressLocation) {
		this.macAddressLocation = macAddressLocation;
	}

	public String getMacAddressUrl() {
		return macAddressUrl;
	}

	public void setMacAddressUrl(String macAddressUrl) {
		this.macAddressUrl = macAddressUrl;
	}

	public static String getMacaddress() {
		return macAddress;
	}

	public String getMacAddressData() {
		return macAddressData;
	}

	public MacAddressLocation getMacAddressLocation() {
		return macAddressLocation;
	}
}
