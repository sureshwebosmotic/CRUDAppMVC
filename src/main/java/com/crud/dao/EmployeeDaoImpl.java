package com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crud.entity.Employee;
import com.crud.entity.Skill;

//This is a EmployeeDaoImpl class which implements the methods to perform database related operations using jdbc.
public class EmployeeDaoImpl implements EmployeeDao {

	private SkillDao skillDao = new SkillDaoImpl();
	private Integer employeeId = null;

	// Below Static method is use to get the database connection so we can access it
	// and do the necessary operations.
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

	@Override
	public Employee selectEmployee(int employeeId) {
		String SELECT_EMPLOYEE_BY_ID = "select * from employee where employee_id =?";

		Employee employee = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) {
			preparedStatement.setInt(1, employeeId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				employeeId = rs.getInt("employee_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				Double salary = rs.getDouble("salary");
				List<Skill> skills = skillDao.selectSkillsEmployeeId(employeeId);
				LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
				employee = new Employee(name, skills, age, salary, birthDate);
				employee.setEmployeeId(employeeId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from employee");) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				Integer employeeId = Integer.parseInt((rs.getString("employee_id")));
				emp.setEmployeeId(employeeId);
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setSkills(skillDao.selectSkillsEmployeeId(employeeId));
				emp.setBirthDate(rs.getDate("birth_date").toLocalDate());
				emp.getSkills().forEach(e -> {
					System.out.println(e.getName());
				});
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertEmployee(Employee employee) {
		System.out.println("Hii");
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (name, age, salary, birth_date) VALUES "
				+ " (?, ?, ?, ?);";
		int result = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL,
						Statement.RETURN_GENERATED_KEYS);) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));

			result = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				employeeId = resultSet.getInt(1);
			}
			System.out.println(employeeId);

			employee.getSkills().forEach(s -> {
				Skill skill = new Skill(s.getName(), employeeId);
				Integer res = skillDao.insertSkill(skill);
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		String UPDATE_EMPLOYEE_SQL = "update employee set name = ?, age= ?, salary =?, birth_date=? where employee_id = ?;";
		System.out.println(employee.toString());
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));
			preparedStatement.setInt(5, employee.getEmployeeId());
			rowUpdated = preparedStatement.executeUpdate() > 0;

			return rowUpdated;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteEmployee(int id) {
		String DELETE_EMPLOYEE_SQL = "delete from employee where employee_id = ?;";

		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
			rowDeleted = skillDao.deleteSkillByEmployeeId(id);

			if (rowDeleted) {
				preparedStatement.setInt(1, id);
				rowDeleted = preparedStatement.executeUpdate() > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}
