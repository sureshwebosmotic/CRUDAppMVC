package com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.entity.Skill;

//This is a SkillDaoImpl class which implements the methods to perform database related operations using jdbc.
public class SkillDaoImpl implements SkillDao {

	// Below Static method is use to get the database connection so we can access it
	// and do the necessary operations.
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// save the skill.
	public int insertSkill(Skill skill) {
		String INSERT_SKILL_SQL = "INSERT INTO skill" + "  (name, employee_fid) VALUES " + " (?, ?);";
		int result = 0;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SKILL_SQL);) {
			preparedStatement.setString(1, skill.getName());
			preparedStatement.setInt(2, skill.getEmployeeId());

			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Skill> getAllSkills() {
		List<Skill> list = new ArrayList<>();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("select * from skill");) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Skill skill = new Skill();
				skill.setId(Integer.parseInt((rs.getString("skill_id"))));
				skill.setName(rs.getString("name"));
				skill.setEmployeeId(rs.getInt("age"));
				;
				list.add(skill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	};

	@Override
	public List<Skill> selectSkillsEmployeeId(int employeeId) {
		String SELECT_SKILL_BY_ID = "select * from skill where employee_fid =?";

		List<Skill> skills = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SKILL_BY_ID);) {
			preparedStatement.setInt(1, employeeId);
			// System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int skillId = rs.getInt("skill_id");
				employeeId = rs.getInt("employee_fid");
				String name = rs.getString("name");
				Skill skill = new Skill(skillId, name, employeeId);
				skills.add(skill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return skills;
	}

	@Override
	public boolean updateSkill(Skill skill) {
		String UPDATE_SKILL_SQL = "update employee set name = ? where skill_id = ?;";

		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SKILL_SQL);) {
			preparedStatement.setString(1, skill.getName());
			rowUpdated = preparedStatement.executeUpdate() > 0;
			return rowUpdated;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteSkill(int id) {
		String DELETE_SKILL_SQL = "delete from skill where skill_id = ?;";

		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SKILL_SQL);) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	@Override
	public boolean deleteSkillByEmployeeId(int employeeId) {
		String DELETE_SKILL_SQL = "delete from skill where employee_fid = ?;";

		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SKILL_SQL);) {
			preparedStatement.setInt(1, employeeId);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}
}
