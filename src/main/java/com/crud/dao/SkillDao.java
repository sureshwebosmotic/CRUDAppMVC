package com.crud.dao;

import java.util.List;

import com.crud.entity.Employee;
import com.crud.entity.Skill;

//This is a SkillDao Interface which defines Dao methods to perform database related operations.
public interface SkillDao {

	// save the skill.
	int insertSkill(Skill skill);

	// retrieve all the skills.
	List<Skill> getAllSkills();

	// retrieve the skills by EmployeeId.
	public List<Skill> selectSkillsEmployeeId(int employeeId);

	// update the skill
	boolean updateSkill(Skill skill);

	// delete the Skill by SkillId
	boolean deleteSkill(int id);

	// delete the Skill by SkillId
	boolean deleteSkillByEmployeeId(int employeeId);
}
