package com.fdm.vacationModule.dao;

import com.fdm.vacationModule.dao.entities.VacationRequestEntity;

public interface VacationRequestDao {

	VacationRequestEntity save(VacationRequestEntity vacationRequestEntity);
	VacationRequestEntity update(VacationRequestEntity vacationRequestEntity, String otp);
	VacationRequestEntity findById(int id);
	
}
