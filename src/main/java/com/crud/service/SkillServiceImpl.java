package com.crud.service;

import java.util.List;
import java.util.Set;

import com.crud.dao.SkillDao;
import com.crud.dao.SkillDaoImpl;
import com.crud.entity.Employee;
import com.crud.entity.Skill;
import com.crud.exception.CustomException;

//This is SkillServiceImpl interface which implements the service method and business logic to perform necessary operations.
public class SkillServiceImpl implements SkillService {

	private SkillDao skillDao = new SkillDaoImpl();

	@Override
	public void updateSkill(Employee employee) throws CustomException {
		Set<Skill> retrievedSkills = skillDao.selectSkillsEmployeeId(employee.getEmployeeId());
		Set<Skill> employeeSkills = employee.getSkills();
		// when new skill is added during edit
		for (Skill skill : employeeSkills) {
			if (!retrievedSkills.contains(skill)) {
				skillDao.insertSkill(skill);
			}
		}

		// when any skill is removed during edit
		for (Skill skill : retrievedSkills) {
			if (!employeeSkills.contains(skill)) {
				skillDao.deleteSkill(skill.getId());
			}
		}
	}

}
