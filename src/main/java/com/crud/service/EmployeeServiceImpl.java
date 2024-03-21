package com.crud.service;

import com.crud.dao.EmployeeDao;
import com.crud.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDao employeeDao;
	
	private void init() {
		employeeDao = new EmployeeDao();

	}
	@Override
	public Employee saveEmployee(Employee employee) {
		
		 employeeDao.saveEmployee(employee);
		 return employee;
	}

}
