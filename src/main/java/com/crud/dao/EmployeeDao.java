package com.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.crud.entity.Employee;

public class EmployeeDao {

	public static Connection getConnection(){  
	    Connection con=null;  
	    try{  
	        Class.forName("com.mysql.jdbc.Driver");  
	        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root","root");  
	    }catch(Exception e){System.out.println(e);}  
	    return con;  
	}  
	
	public static List<Employee> getAllRecords(){  
	    List<Employee> list=new ArrayList<>();  
	      
	    try{  
	        Connection con=getConnection();  
	        PreparedStatement ps=con.prepareStatement("select * from employee");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Employee emp =new Employee();  
	            emp.setEmployeeId(Integer.parseInt((rs.getString("employee_id")))); 
	            emp.setName(rs.getString("name"));  
	            emp.setSalary(rs.getString("salary"));  
	            emp.setSkills(rs.getString("skills"));  
	            emp.setBirthDate(rs.getDate("birth_date").toLocalDate());  
	            list.add(emp);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}  
	
	public int saveEmployee(Employee employee) {
		String INSERT_EMPLOYEES_SQL = "INSERT INTO employee" +
	            "  (employee_id, name, age, salary, birth_date, skills) VALUES " +
	            " (?, ?, ?, ?, ?, ?);";
        int result = 0;

		try{  
	        Connection con=getConnection();  
	        PreparedStatement preparedStatement=con.prepareStatement(INSERT_EMPLOYEES_SQL); 
            preparedStatement.setInt(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getSalary());
            preparedStatement.setDate(5, Date.valueOf(employee.getBirthDate()));
            preparedStatement.setString(6, employee.getSkills());

            result =preparedStatement.executeUpdate();  
	        
	    }catch(Exception e){System.out.println(e);}  
	    return result;  
	}
}
