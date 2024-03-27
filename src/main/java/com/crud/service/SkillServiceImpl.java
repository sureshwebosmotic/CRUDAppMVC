package com.crud.service;

import java.util.List;

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
		List<Skill> retrievedSkills = skillDao.selectSkillsEmployeeId(employee.getEmployeeId());

		// when new skill is added during edit
		for (int i = 0; i < employee.getSkills().size(); i++) {
			boolean isSkillAdded = true;
			for (int j = 0; j < retrievedSkills.size(); j++) {

				if (employee.getSkills().get(i).getName().equals(retrievedSkills.get(j).getName())) {
					isSkillAdded = false;
				}

			}

			if (isSkillAdded) {
				skillDao.insertSkill(employee.getSkills().get(i));
			}
		}

		// when any skill is removed during edit
		for (int i = 0; i < retrievedSkills.size(); i++) {
			boolean isSkillRemoved = true;
			for (int j = 0; j < employee.getSkills().size(); j++) {

				if (retrievedSkills.get(i).getName().equals(employee.getSkills().get(j).getName())) {
					isSkillRemoved = false;
				}

			}

			if (isSkillRemoved) {
				skillDao.deleteSkill(retrievedSkills.get(i).getId());
			}
		}
	}

}
