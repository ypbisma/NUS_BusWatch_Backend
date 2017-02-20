package com.buswatchbackend;

import com.google.gson.annotations.SerializedName;

public class BusSession {
	@SerializedName("session_id")
	private String sessionId;

	@SerializedName("mac_hash")
	private String macHash;

	@SerializedName("gw_id")
	private Integer gwId;

	@SerializedName("latitude_begin")
	private Double latitudeBegin;

	@SerializedName("longitude_begin")
	private Double longitudeBegin;

	public String getMacHash() {
		return macHash;
	}

	public void setMacHash(String macHash) {
		this.macHash = macHash;
	}

	public Double getLatitudeBegin() {
		return latitudeBegin;
	}

	public void setLatitudeBegin(Double latitudeBegin) {
		this.latitudeBegin = latitudeBegin;
	}

	public Double getLongitudeBegin() {
		return longitudeBegin;
	}

	public void setLongitudeBegin(Double longitudeBegin) {
		this.longitudeBegin = longitudeBegin;
	}

	public Integer getGwId() {
		return gwId;
	}

	public void setGwId(Integer gwId) {
		this.gwId = gwId;
	}
}
