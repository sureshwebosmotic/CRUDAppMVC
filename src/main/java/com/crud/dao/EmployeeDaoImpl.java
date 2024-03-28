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

import javax.servlet.http.HttpServletRequest;

import com.crud.entity.Employee;
import com.crud.entity.Skill;

//This is a EmployeeDaoImpl class which implements the methods to perform database related operations using jdbc.
public class EmployeeDaoImpl implements EmployeeDao {

	private SkillDao skillDao = new SkillDaoImpl();
	private Integer employeeId = null;

	protected Employee setEmployeeFields(ResultSet resultSet) throws NumberFormatException, SQLException {
		Employee employee = new Employee();
		Integer employeeId = Integer.parseInt((resultSet.getString("employee_id")));
		employee.setEmployeeId(employeeId);
		employee.setName(resultSet.getString("name"));
		employee.setAge(resultSet.getInt("age"));
		employee.setSalary(resultSet.getDouble("salary"));
		employee.setSkills(skillDao.selectSkillsEmployeeId(employeeId));
		employee.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
		return employee;
	}

	protected void setPreparedStatement(PreparedStatement preparedStatement, Employee employee) throws SQLException {
		preparedStatement.setString(1, employee.getName());
		preparedStatement.setInt(2, employee.getAge());
		preparedStatement.setDouble(3, employee.getSalary());
		preparedStatement.setDate(4, Date.valueOf(employee.getBirthDate()));

		if (employee.getEmployeeId() != null) {
			preparedStatement.setInt(5, employee.getEmployeeId());
		}
	}

	// Below Static method is use to get the database connection so we can access it
	// and do the necessary operations.
	public static Connection getConnection() {
		Connection connection = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		} catch (Exception e) 
		{
			e.printStackTrace();
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
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);) 
		{
			preparedStatement.setInt(1, employeeId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet resultSet = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (resultSet.next()) {
				employee = setEmployeeFields(resultSet);
			}
			return employee;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
			return employee;

	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();

		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("select * from employee");) 
		{
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = setEmployeeFields(resultSet);
				list.add(employee);
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
			return list;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" + "  (name, age, salary, birth_date) VALUES (?, ?, ?, ?);";
		boolean rowInserted = false;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL, Statement.RETURN_GENERATED_KEYS);) 
		{
			setPreparedStatement(preparedStatement, employee);
			rowInserted = preparedStatement.executeUpdate() > 0;
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				employeeId = resultSet.getInt(1);
			}

			employee.getSkills().forEach(s -> {
				Skill skill = new Skill(s.getName(), employeeId);
				Integer res = skillDao.insertSkill(skill);
			});
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
			return rowInserted;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		String UPDATE_EMPLOYEE_SQL = "update employee set name = ?, age= ?, salary =?, birth_date=? where employee_id = ?;";
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) 
		{
			setPreparedStatement(preparedStatement, employee);

			rowUpdated = preparedStatement.executeUpdate() > 0;

			return rowUpdated;

		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
			return rowUpdated;
	}

	@Override
	public boolean deleteEmployee(int id) {
		String DELETE_EMPLOYEE_SQL = "delete from employee where employee_id = ?;";

		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) 
		{
			rowDeleted = skillDao.deleteSkillByEmployeeId(id);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
			return rowDeleted;
	}
}
