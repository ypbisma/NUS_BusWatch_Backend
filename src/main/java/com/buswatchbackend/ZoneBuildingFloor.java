package com.buswatchbackend;

public class ZoneBuildingFloor {

	private String zone;
	private String building;
	private String floor;
	
	public ZoneBuildingFloor(String zone, String building, String floor){
		this.zone = zone;
		this.building = building;
		this.floor = floor;
	}
	
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
}
