package com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.crud.entity.Employee;

public class EmployeeDao {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public Employee selectEmployee(int employeeId) {
		String SELECT_USER_BY_ID = "select * from employee where employee_id =?";

		Employee employee = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
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
				String skills = rs.getString("skills");
				LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
				employee = new Employee(name, skills, age, salary, birthDate);
				employee.setEmployeeId(employeeId);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return employee;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();

		try {
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement("select * from employee");
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(Integer.parseInt((rs.getString("employee_id"))));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setSkills(rs.getString("skills"));
				emp.setBirthDate(rs.getDate("birth_date").toLocalDate());
				list.add(emp);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public int insertEmployee(Employee employee) {
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (name, age, salary, birth_date, skills) VALUES "
				+ " (?, ?, ?, ?, ?);";
		int result = 0;
		try {
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_EMPLOYEES_SQL);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));
			preparedStatement.setString(5, employee.getSkills());

			result = preparedStatement.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public boolean updateEmployee(Employee employee) throws SQLException {
		String UPDATE_USERS_SQL = "update employee set name = ?,age= ?, salary =?, birth_date=?, skills=? where employee_id = ?;";

		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAge());
			preparedStatement.setDouble(3, employee.getSalary());
			preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));
			preparedStatement.setString(5, employee.getSkills());
			preparedStatement.setInt(6, employee.getEmployeeId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean deleteEmployee(int id) throws SQLException {
		String DELETE_USERS_SQL = "delete from employee where employee_id = ?;";

		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}
