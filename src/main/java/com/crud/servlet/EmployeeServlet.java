package com.crud.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.entity.Employee;
import com.crud.entity.Skill;
import com.crud.exception.CustomException;
import com.crud.service.EmployeeService;
import com.crud.service.EmployeeServiceImpl;

//This is a Servlet class which takes the request, process it, generate the response and forward it to the Jsp page.
public class EmployeeServlet extends HttpServlet {

	private EmployeeService empService;

	public void init() {
		empService = new EmployeeServiceImpl();
	}

	protected Employee setEmployeeFields(HttpServletRequest request) {
		Integer employeeId = null;
		if (request.getParameter("employeeId") != null) {
			employeeId = Integer.parseInt(request.getParameter("employeeId"));
		}
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		Double salary = Double.valueOf(request.getParameter("salary"));
		LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
		List<Skill> skills = new ArrayList<>();
		
		if (request.getParameterValues("skills") != null) {
			String[] arraySkills = request.getParameterValues("skills");
			String arraySkillsId = request.getParameter("skillId");
			System.out.println(arraySkillsId);
//			for (int i = 0; i < arraySkillsId.length; i++) {
//				System.out.println(arraySkillsId[i]);
//			}
			for (int i = 0; i < arraySkills.length; i++) {
				Skill skill = new Skill(arraySkills[i], employeeId);
				skills.add(skill);
			}
		}
		Employee employee = new Employee(name, skills, age, salary, birthDate);
		employee.setEmployeeId(employeeId);
		return employee;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

	// This method is use to call the appropriate method based on path.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		try 
		{
			switch (action) 
			{
			case "/insert":
				insertEmployee(request, response);
				break;
			case "/update":
				updateEmployee(request, response);
				break;
			case "/edit":
				selectEmployee(request, response);
				break;
			case "/delete":
				deleteEmployee(request, response);
				break;
			case "/new":
				showEmployeeForm(request, response);
				break;
			default:
				getAllEmployees(request, response);
				break;
			}
		} catch (ServletException e) 
		{
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	private void showEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
		dispatcher.forward(request, response);
	}

	protected void getAllEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employees = empService.getAllEmployee();
		request.setAttribute("employees", employees);
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-list.jsp");
		dispatcher.forward(request, response);
	}

	protected void insertEmployee(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		Employee employee = setEmployeeFields(request);
		try 
		{
			empService.insertEmployee(employee);
			response.sendRedirect("getAll");
		} catch (Exception e) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
			request.setAttribute("employee", employee);
			request.setAttribute("errorMessage", e.getMessage());
			dispatcher.forward(request, response);
		}
	}

	protected void updateEmployee(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, SQLException {
		Employee employee = setEmployeeFields(request);
		try 
		{
			empService.updateEmployee(employee);
			response.sendRedirect("getAll");
		} catch (CustomException e) 
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
			request.setAttribute("employee", employee);
			request.setAttribute("errorMessage", e.getMessage());
			dispatcher.forward(request, response);
		}

	}

	protected void selectEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
		Employee retrievedemployee = empService.selectEmployee(employeeId);
		System.out.println(retrievedemployee.toString());
		RequestDispatcher dispatcher = request.getRequestDispatcher("employee-form.jsp");
		request.setAttribute("employee", retrievedemployee);
		request.setAttribute("list", retrievedemployee.getSkills());

		dispatcher.forward(request, response);

	}

	protected void deleteEmployee(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException, SQLException {
		Integer employeeId = Integer.parseInt(request.getParameter("employeeId"));
		empService.deleteEmployee(employeeId);
		response.sendRedirect("getAll");
	}
}
