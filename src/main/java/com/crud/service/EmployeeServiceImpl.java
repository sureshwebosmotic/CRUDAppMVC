package com.crud.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

	@Override
	public boolean insertEmployee(Employee employee) throws CustomException {
		return employeeDao.insertEmployee(employee);
	}

	@Override
	public Set<Employee> getAllEmployee() {
		return employeeDao.getAllEmployees();
	}

	@Override
	public Employee selectEmployee(Integer employeeId) {
		return employeeDao.selectEmployee(employeeId);
	}

	@Override
	public boolean deleteEmployee(Integer employeeId) {
		return employeeDao.deleteEmployee(employeeId);
	}

	@Override
	public void updateEmployee(Employee employee) throws CustomException {
		employeeDao.updateEmployee(employee);
		skillService.updateSkill(employee);
	}

}
