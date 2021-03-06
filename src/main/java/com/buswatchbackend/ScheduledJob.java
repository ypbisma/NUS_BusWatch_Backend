package com.buswatchbackend;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduledJob implements org.quartz.Job {
	String token;
	Date now; // to display current time

	public ScheduledJob() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {

			// login and obtain token
			LoginManager loginManager = new LoginManager();
			loginManager.login();
			token = loginManager.getToken();

			JobBusLocation jobBusLocation = new JobBusLocation(token);
			// JobBusSession jobBusSession = new JobBusSession(busNumber,
			// token);
			JobMacAddressLocation jobMacAddressLocation = new JobMacAddressLocation(token);

			jobBusLocation.execute();
			// jobBusSession.execute();
			jobMacAddressLocation.execute();

		} catch (NullPointerException e) {
			System.out.println("Data does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}
	}
}
