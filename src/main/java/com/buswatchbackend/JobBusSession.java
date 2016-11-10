package com.buswatchbackend;

public class JobBusSession {
	
	private String token;
	private Integer busNumber;
	
	public JobBusSession(Integer busNumber){
		this.busNumber = busNumber;
	}
	
	public void execute() throws Exception{
		LoginManager loginManager = new LoginManager();
		
		//login and obtain token
		loginManager.login();
		token = loginManager.getToken();
		
		BusSessionManager busSessionManager = new BusSessionManager(token);
		busSessionManager.syncSessions();
		
		this.printBusSessionList(busSessionManager.getBusSessionList());

	}
	private void printBusSessionList(BusSession[] busSessionList){
		String output;
		for (BusSession busSession : busSessionList) {
			if(busSession.getGwId()==busNumber) {
				output = busSession.getGwId().toString() + " \t" 
						+ busSession.getMacHash().toString() + " \t"
						+ busSession.getLatitudeBegin().toString() + " \t"
						+ busSession.getLongitudeBegin().toString();
				System.out.println(output);
		}
				}
		System.out.println(token);
		}
}
