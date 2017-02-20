package com.buswatchbackend;

import java.util.Vector;

public class JobBusLocation {
	private BusLocationManager busLocationManager;
	private Vector<BusLocation> desiredBusLocationList = new Vector<BusLocation>();
	private String output;
	private DatabaseWriter busLocationWriter = new DatabaseWriter();

	public JobBusLocation(String token) throws Exception {

		// feed token to get BusInfo
		busLocationManager = new BusLocationManager(token);
	}

	public void execute() throws Exception {

		busLocationManager.syncBusInfo();

		writeBusInfoList(busLocationManager.getBusLocationList());
	}

	public void writeBusInfoList(BusLocation[] busInfoList) {
		String Output;

		BusLocation lastBusLocation = new BusLocation();

		for (BusLocation busLocation : busInfoList) {
			lastBusLocation = busLocation;
		}

		desiredBusLocationList.add(lastBusLocation);

		for (BusLocation e : desiredBusLocationList) {

			busLocationWriter.insertBusInformation(e.getNodeId().toString(), e.getVehicleSerial().toString(),
					e.getGpsTime().toString(), e.getLatitude().toString(), e.getLongitude().toString(),
					e.getHeading().toString());

			busLocationWriter.insertLastBusInformation(e.getNodeId().toString(), e.getVehicleSerial().toString(),
					e.getGpsTime().toString(), e.getLatitude().toString(), e.getLongitude().toString(),
					e.getHeading().toString());
		}
	}
}
