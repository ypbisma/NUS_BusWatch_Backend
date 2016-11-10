package com.buswatchbackend;
import java.util.TimerTask;
import java.util.Date;


// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	Date now; // to display current time
	Integer busNumber = 2028;
	String token;
	
	public void run(){
		try{
			
		//login and obtain token
		LoginManager loginManager = new LoginManager();
		loginManager.login();
		token = loginManager.getToken();
		
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
		JobBusLocation jobBusLocation = new JobBusLocation(busNumber, token);
		JobBusSession jobBusSession = new JobBusSession(busNumber, token);
		JobMacAddressLocation jobMacAddressLocation = new JobMacAddressLocation(token);
		
		jobBusLocation.execute();
		jobBusSession.execute();
		//jobMacAddressLocation.execute();
		System.out.println(token);

		}
		catch(NullPointerException e){
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			//
		}
	}
	
	//open http://www.darrinward.com/lat-long/?id=2400046 for longitude langitude plot
}
