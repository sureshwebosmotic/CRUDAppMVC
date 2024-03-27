package com.crud.service;

import java.time.LocalDate;
import java.util.List;

import com.crud.dao.EmployeeDao;
import com.crud.dao.EmployeeDaoImpl;
import com.crud.dao.SkillDao;
import com.crud.dao.SkillDaoImpl;
import com.crud.entity.Employee;
import com.crud.entity.Skill;
import com.crud.exception.CustomException;

//This is EmployeeServiceImpl interface which implements the service method and business logic to perform necessary operations.
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao = new EmployeeDaoImpl();

	private SkillService skillService = new SkillServiceImpl();

	// Calculate the age based on given birth date to identify the employee is above
	// 18 or not.
	public static Integer getAge(LocalDate birthDate) {
		Integer age = LocalDate.now().getYear() - birthDate.getYear();
		int month = LocalDate.now().getMonth().getValue() - birthDate.getMonth().getValue();
		if (month < 0 || (month == 0 && LocalDate.now().getDayOfMonth() < birthDate.getDayOfMonth())) {
			age--;
		}
		return age;
	}

	@Override
	public Employee insertEmployee(Employee employee) throws CustomException {

		if (employee.getSkills().isEmpty()) {
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

		List<Employee> employees = employeeDao.getAllEmployees();

		employees.forEach(emp -> {
			StringBuilder responseSkill = new StringBuilder();
			emp.getSkills().forEach(skill -> {
				responseSkill.append(skill.getName());
				responseSkill.append(",");
			});
			if (responseSkill.length() > 1) {
				responseSkill.deleteCharAt(responseSkill.length() - 1);
			}
			emp.setResponseSkill(responseSkill.toString());
		});
		return employees;
	}

	@Override
	public Employee selectEmployee(Integer employeeId) {
		Employee employee = employeeDao.selectEmployee(employeeId);
		StringBuilder responseSkill = new StringBuilder();
		employee.getSkills().forEach(skill -> {
			responseSkill.append(skill.getName());
			responseSkill.append(",");
		});
		if (responseSkill.length() > 1) {
			responseSkill.deleteCharAt(responseSkill.length() - 1);
		}
		employee.setResponseSkill(responseSkill.toString());
		return employee;
	}

	@Override
	public boolean deleteEmployee(Integer employeeId) {
		return employeeDao.deleteEmployee(employeeId);
	}

	@Override
	public boolean updateEmployee(Employee employee) throws CustomException {
		if (employee.getSkills().isEmpty()) {
			throw new CustomException("please add at least one skill.");
		}
		if (employee.getAge() < 18 || employee.getAge() > 60) {
			throw new CustomException("Age should not be less than 18 and greater than 60");

		}
		if (getAge(employee.getBirthDate()) < 18) {

			throw new CustomException("please provide valid birth Date.");
		}

		boolean isEmployeeUpdated = employeeDao.updateEmployee(employee);

		skillService.updateSkill(employee);

		return isEmployeeUpdated;
	}

}
