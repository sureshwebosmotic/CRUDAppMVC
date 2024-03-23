package com.crud.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.crud.dao.EmployeeDao;
import com.crud.entity.Employee;
import com.crud.exception.CustomException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao = new EmployeeDao();

//	private void init() {
//		employeeDao = 
//
//	}
	public static Integer getAge(LocalDate birthDate) {
		Integer age = LocalDate.now().getYear() - birthDate.getYear();
		int month = LocalDate.now().getMonth().getValue() - birthDate.getMonth().getValue();
		if (month < 0 || (month == 0 && LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth())) {
			age--;
		}
		return age;
	}

	@Override
	public Employee insertEmployee(Employee employee) throws Exception {

		if (employee.getSkills() == "") {
			throw new CustomException("please add at least one skill.");
		}
		if (employee.getAge() < 18 || employee.getAge() > 60) {
			throw new CustomException("Age should not be less than 18 and greater than 60");

		}
		if (getAge(employee.getBirthDate()) < 18) {

			throw new CustomException("please provide valid birth Date.");
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
		return employeeDao.selectEmployee(employeeId);
	}

	@Override
	public boolean deleteEmployee(Integer employeeId) throws SQLException {
		return employeeDao.deleteEmployee(employeeId);
	}

	@Override
	public boolean updateEmployee(Employee employee) throws SQLException, CustomException {
		if (employee.getSkills() == "") {
			throw new CustomException("please add at least one skill.");
		}
		if (employee.getAge() < 18 || employee.getAge() > 60) {
			throw new CustomException("Age should not be less than 18 and greater than 60");

		}
		if (getAge(employee.getBirthDate()) < 18) {

			throw new CustomException("please provide valid birth Date.");
		}
		return employeeDao.updateEmployee(employee);
	}

}
