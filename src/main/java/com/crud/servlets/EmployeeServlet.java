package com.crud.servlets;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.dao.EmployeeDao;
import com.crud.entity.Employee;
import com.crud.service.EmployeeService;
import com.crud.service.EmployeeServiceImpl;
@WebServlet("/save")
public class EmployeeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private EmployeeService empService;

    public void init() {
    	empService = new EmployeeServiceImpl();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		
		String name = request.getParameter("name");
        String skills = request.getParameter("skills");
        int age = Integer.parseInt(request.getParameter("age"));
        String salary = request.getParameter("salary");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        
        Employee employee = new Employee(name, salary, age, skills, birthDate);
        empService.saveEmployee(employee);
		
		try {
			response.sendRedirect("employeedetails.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
