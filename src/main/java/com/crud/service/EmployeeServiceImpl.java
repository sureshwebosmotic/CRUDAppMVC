package com.crud.service;

import java.sql.SQLException;
import java.util.List;

import com.crud.dao.EmployeeDao;
import com.crud.entity.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDao employeeDao=new EmployeeDao();
	
//	private void init() {
//		employeeDao = 
//
//	}
	@Override
	public Employee insertEmployee(Employee employee) throws Exception {
		System.out.println("Hii");
		if(employee.getAge()<18 || employee.getAge()>60) {
			throw new Exception();
		}
		 employeeDao.insertEmployee(employee);
		 return employee;
	}
	@Override
	public List<Employee> getAllEmployee() {
		
		return employeeDao.getAllEmployees();
	}
	@Override
	public Employee selectEmployee(Integer employeeId) {
		// TODO Auto-generated method stub
		return employeeDao.selectEmployee(employeeId);
	}
	@Override
	public boolean deleteEmployee(Integer employeeId) throws SQLException {
		return employeeDao.deleteEmployee(employeeId);
	}
	@Override
	public boolean updateEmployee(Employee employee) throws SQLException {
		return employeeDao.updateEmployee(employee);
	}

}
