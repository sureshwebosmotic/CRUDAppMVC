<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Employee Management App</title>
</head>
<body>
	<div>
		<h1>Employee Register Form</h1>
		<c:if test="${employee != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${employee == null}">
			<form action="insert" method="post">
		</c:if>
		<caption>
			<h2>
				<c:if test="${employee != null}">
                                    Edit User
                                </c:if>
				<c:if test="${employee == null}">
                                    Add New User
                                </c:if>
			</h2>
		</caption>
		<c:if test="${employee != null}">
			<input type="hidden" name="employeeId" value="<c:out value='${employee.employeeId}' />" />
		</c:if>
		<fieldset class="form-group">
			<label>Name</label> <input type="text"
				value="<c:out value='${employee.name}' />" class="form-control"
				name="name" required="required">
		</fieldset>
		<fieldset class="form-group">
		
			<label>Skills</label> <br><input type="checkbox"
				value="<c:out value="Java" />" class="form-control"
				name="skills" > Java <br>
				<input type="checkbox"
				value="<c:out value="Spring" />" class="form-control"
				name="skills" > Spring <br>
		</fieldset>
		<fieldset class="form-group">
			<label>Age</label> <input type="text"
				value="<c:out value='${employee.age}' />" class="form-control"
				name="age" required="required">
		</fieldset>
		<fieldset class="form-group">
			<label>Salary</label> <input type="text"
				value="<c:out value='${employee.salary}' />" class="form-control"
				name="salary" required="required">
		</fieldset>
		<fieldset class="form-group">
			<label>BirthDate</label> <input type="date"
				value="<c:out value='${employee.birthDate}' />" class="form-control"
				name="birthDate" required="required">
		</fieldset>
		<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>
