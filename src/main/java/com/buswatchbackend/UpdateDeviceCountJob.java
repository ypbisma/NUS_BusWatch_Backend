package com.buswatchbackend;

import java.util.Date;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class UpdateDeviceCountJob implements org.quartz.Job {
	String token;
	Date now; // to display current time

	public UpdateDeviceCountJob() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {

			// login and obtain token
			LoginManager loginManager = new LoginManager();
			loginManager.login();
			token = loginManager.getToken();

			JobDeviceCount jobDeviceCount = new JobDeviceCount(token);
			jobDeviceCount.execute();

		} catch (NullPointerException e) {
			System.out.println("Data does not exist");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//
		}
	}
}
