package com.crud.service;

import com.crud.entity.Employee;
import com.crud.exception.CustomException;

// This is EmployeeService interface which is use to define service methods.
public interface SkillService {

	// update the skill
	public void updateSkill(Employee employee) throws CustomException;
}
