package com.buswatchbackend;

import com.google.gson.annotations.SerializedName;

public class MacAddressLocation {

	private boolean isGuestUser;
	@SerializedName("GeoCoordinate")
	private GeoCoordinate geoCoordinate;
	private String floor;

	public boolean isGuestUser() {
		return isGuestUser;
	}

	public void setGuestUser(boolean isGuestUser) {
		this.isGuestUser = isGuestUser;
	}

	public GeoCoordinate getGeoCoordinate() {
		return geoCoordinate;
	}

	public void setGeoCoordinate(GeoCoordinate geoCoordinate) {
		this.geoCoordinate = geoCoordinate;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}
}
