package com.crud.service;

import java.sql.SQLException;
import java.util.List;

import com.crud.entity.Employee;

public interface EmployeeService {
	public Employee insertEmployee(Employee employee) throws Exception;
	
	public List<Employee> getAllEmployee();
	
	public Employee selectEmployee(Integer employeeId);
	
	public boolean deleteEmployee(Integer employeeId) throws SQLException;
	
	public boolean updateEmployee(Employee employee) throws SQLException;
}
