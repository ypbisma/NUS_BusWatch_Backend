package com.buswatchbackend;

import com.google.gson.annotations.SerializedName;

public class BusInfo {
	
	@SerializedName("node_id")
	private Integer nodeId;
	
	@SerializedName("vehicle_serial")
	private String vehicleSerial;
	
	@SerializedName("gps_time")
	private String gpsTime;
	
	private Double latitude;
	private Double longitude;
	private Double altitude;
	
	private Integer speed;
	private Double heading;
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getVehicleSerial() {
		return vehicleSerial;
	}
	public void setVehicleSerial(String vehicleSerial) {
		this.vehicleSerial = vehicleSerial;
	}
	public String getGpsTime() {
		return gpsTime;
	}
	public void setGpsTime(String gpsTime) {
		this.gpsTime = gpsTime;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Double getHeading() {
		return heading;
	}
	public void setHeading(Double heading) {
		this.heading = heading;
	}
}
