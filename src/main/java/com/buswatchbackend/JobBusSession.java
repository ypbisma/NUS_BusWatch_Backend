package com.buswatchbackend;

import java.util.Vector;

public class JobBusSession {
	
	private String token;
	private Integer busNumber;
	private BusSessionManager busSessionManager;
	private Vector<BusSession> desiredBusSessionList = new Vector<BusSession>();

	
	public JobBusSession(Integer busNumber)throws Exception{
		//login and obtain token
		LoginManager loginManager = new LoginManager();
		loginManager.login();
		token = loginManager.getToken();
		busSessionManager = new BusSessionManager(token);
		
		this.busNumber = busNumber;
	}
	
	public void execute() throws Exception{
		busSessionManager.syncSessions();
		this.printBusSessionList(busSessionManager.getBusSessionList());

	}
	
	private void printBusSessionList(BusSession[] busSessionList){
		String output;
		
		BusSession lastBusSession = new BusSession();
		
		for (BusSession busSession : busSessionList) {
			int localBusNumber = busNumber;
			output = busSession.getGwId().toString() + " \t"  
					+ busSession.getMacHash().toString() + " \t"
					+ busSession.getLatitudeBegin().toString() + " \t"
					+ busSession.getLongitudeBegin().toString();
			
			if(busSession.getGwId()==localBusNumber) {
				lastBusSession = busSession;
			}
		}
			
			desiredBusSessionList.add(lastBusSession);
			
//			for(BusSession e : desiredBusSessionList){
//				output = e.getGwId().toString() + " \t"  
//						+ e.getMacHash().toString() + " \t"
//						+ e.getLatitudeBegin().toString() + " \t"
//						+ e.getLongitudeBegin().toString();
//					
//				System.out.println(output);
//			}
		}
}


			
				