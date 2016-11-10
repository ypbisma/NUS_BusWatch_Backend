package com.buswatchbackend;

import java.util.Timer;


public class BusApiConnector	
{	

	
	public static void main (String[] args) throws Exception {
		try{
			
			Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask class
			time.schedule(st, 0, 500); // Create Repetitively task for every 1 secs

			for (int i = 0; i <= 1000; i++) {
				Thread.sleep(1000);
				if (i == 1000) {
					System.out.println("Application Terminates");
					System.exit(0);
				}
			}
		}
		catch(NullPointerException e){
			System.out.println("array is empty!");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}


