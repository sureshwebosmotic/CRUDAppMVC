package com.crud.dao;

import java.util.Set;

import com.crud.entity.Employee;

// This is a employeeDao Interface which defines Dao methods to perform database related operations.
public interface EmployeeDao {

	// retrieve the employee by employeeId
	Employee selectEmployee(int employeeId);

	// retrieve all the employees.
	Set<Employee> getAllEmployees();

	// save the employee.
	boolean insertEmployee(Employee employee);

	// update the emplooyee
	boolean updateEmployee(Employee employee);

	// delete the employee by employeeId
	boolean deleteEmployee(int id);

}
