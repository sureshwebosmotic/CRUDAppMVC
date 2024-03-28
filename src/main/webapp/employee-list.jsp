<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html>

<head>
<title>Employee management App</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<body>
	<a href="employee-form.jsp">Add User</a>
	<div align="center">
		<h1>List of Employees</h1>
	</div>
	<br>
	<table border="1" width="90%" class="table table-bordered">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Salary</th>
			<th>BirthDate</th>
			<th>Skills</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<tbody>
			<c:forEach var="employee" items="${employees}">
			<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<td><c:out value="${count}" /></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.age}" /></td>
					<td><c:out value="${employee.salary}" /></td>
					<td><c:out value="${employee.birthDate}" /></td>
					<c:set var="modifiedSkill" value="${fn:replace(employee.skills,'[', '')}"/>
					<c:set var="modifiedSkill" value="${fn:replace(modifiedSkill,']', '')}"/>  
					<td>${modifiedSkill}</td>
					<td><a
						href="edit?employeeId=<c:out value='${employee.employeeId}' />">Edit</a></td>
					<td><a
						href="delete?employeeId=<c:out value='${employee.employeeId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>

</html>

