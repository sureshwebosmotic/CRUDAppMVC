package com.crud.service;

import java.util.Set;

import com.crud.entity.Employee;
import com.crud.exception.CustomException;

// This is EmployeeService interface which is use to define service methods.
public interface EmployeeService {

	// save the employee.
	public boolean insertEmployee(Employee employee) throws CustomException;

	// retrieve all the employees.
	public Set<Employee> getAllEmployee();

	// retrieve the employee by employeeId
	public Employee selectEmployee(Integer employeeId);

	// delete the employee by employeeId
	public boolean deleteEmployee(Integer employeeId);

	// update the employee
	public void updateEmployee(Employee employee) throws CustomException;
}
