<!DOCTYPE html>  
  
<%@page import="com.crud.dao.EmployeeDao"%>
<%@page import="com.crud.entity.Employee"%>
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>View Users</title>  
</head>  
<body>  
  
<%@page import="java.util.*"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
  
<h1>Users List</h1>  
  
<%  
List<Employee> list= EmployeeDao.getAllRecords();  
request.setAttribute("list",list);  
%>  
  
<table border="1" width="90%">  
<tr><th>Id</th><th>Name</th><th>salary</th><th>age</th>  
<th>skills</th><th>BirthDate</th></tr>  
<c:forEach items="${list}" var="emp">  
<tr><td>${emp.getEmployeeId()}</td><td>${emp.getName()}</td><td>${emp.getSalary()}</td> <td>${emp.getAge()}</td>  
<td>${emp.getSkills()}</td><td>${emp.getBirthDate()}</td>  

</c:forEach>  
</table>  
  
</body>  
</html>  