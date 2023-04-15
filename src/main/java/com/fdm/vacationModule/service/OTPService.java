package com.fdm.vacationModule.service;

import java.util.UUID;

public class OTPService {

	private static OTPService instance;
	
	private OTPService() {
	}
	
	public static OTPService getInstance() {
		if(instance == null)
			instance = new OTPService();
		return instance;
		
	}
	
	public String generateOTP() {
		return UUID.randomUUID().toString();
	}
}
