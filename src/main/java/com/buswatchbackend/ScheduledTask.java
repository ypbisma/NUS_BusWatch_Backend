package com.buswatchbackend;
import java.util.TimerTask;
import java.util.Date;


// Create a class extends with TimerTask
public class ScheduledTask extends TimerTask {

	Date now; // to display current time
	Integer busNumber = 2058;
	// Add your task here
	public void run(){
		try{
		now = new Date(); // initialize date
		System.out.println("Time is :" + now); // Display current time
		JobBusLocation jobBusLocation = new JobBusLocation(busNumber);
		//JobBusSession jobBusSession = new JobBusSession(busNumber);
		
		jobBusLocation.execute();
		//jobBusSession.execute();
		}
		catch(Exception e){
			System.out.println(e);
		}
		finally{
			//
		}
	}
}
