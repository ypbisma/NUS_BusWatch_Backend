package com.buswatchbackend;

import java.io.PrintWriter;
import java.util.Timer;


public class ProjectMain	
{	

	
	public static void main (String[] args) throws Exception {
		try{
			//Initialise text file
			PrintWriter writer = new PrintWriter("location_tracker.txt");
			writer.print("");
			writer.close();
			
			//Timer time = new Timer(); // Instantiate Timer Object
			ScheduledTask st = new ScheduledTask(); // Instantiate ScheduledTask class
			st.run();
			//time.schedule(st, 0, 500); // Create Repetitively task for every 1 secs

//			for (int i = 0; i <= 1000000000; i++) {
//				Thread.sleep(1000);
//				if (i == 1000) {
//					System.out.println("Application Terminates");
//					System.exit(0);
//				}
//			}
		}
		catch(NullPointerException e){
			System.out.println("array is empty!");
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}


