package com.crud.entity;

import java.time.LocalDate;

public class Employee {
	
	private Integer employeeId;
	
	private String name;
	
	private String skills;
	
	private Integer age;
	
	private Double salary;
	
	private LocalDate birthDate;
	
	public Employee() {
		
	}
	
	public Employee(String name, String skills, Integer age, Double salary, LocalDate birthDate) {
		super();
		this.name = name;
		this.skills = skills;
		this.age = age;
		this.salary = salary;
		this.birthDate = birthDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", skills=" + skills + ", age=" + age
				+ ", salary=" + salary + ", birthDate=" + birthDate + "]";
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

}
