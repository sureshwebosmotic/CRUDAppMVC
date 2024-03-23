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
		<c:if test="${employee != null && (employee.employeeId == null)}">
			<form action="insert" method="post">
		</c:if>
		<c:if test="${employee != null && (employee.employeeId != null)}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${employee == null}">
			<form action="insert" method="post">
		</c:if>
		<c:if test="${errorMessage != null}">
    <script type="text/javascript"> 
         alert("${errorMessage}");
    </script>
</c:if>
		<caption>
			<h2>
				<c:if test="${employee != null && (employee.employeeId == null)}">
                                    Add User
                                </c:if>
				<c:if test="${employee != null && (employee.employeeId != null)}">
                                    Edit User
                                </c:if>
				<c:if test="${employee == null}">
                                    Add New User
                                </c:if>
			</h2>
		</caption>
		<c:if test="${employee != null}">
			<input type="hidden" name="employeeId"
				value="<c:out value='${employee.employeeId}' />" />
		</c:if>
		<fieldset class="form-group">
			<label>Name</label> <input type="text"
				value="<c:out value='${employee.name}' />" class="form-control"
				name="name" required="required" pattern="[A-Za-z]+">
		</fieldset>
		<fieldset class="form-group">

			<label>Skills</label> <br>
			<c:if test="${employee != null}">
				<input type="hidden" name="employeeId"
					value="<c:out value='${employee.employeeId}' />" />
			</c:if>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('Java')}">
					<input type="checkbox" value="<c:out value="Java" />"
						class="form-control" name="skills" checked="checked"> Java
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Java" />"
						class="form-control" name="skills"> Java
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('Spring')}">
					<input type="checkbox" value="<c:out value="Spring" />"
						class="form-control" name="skills" checked="checked"> Spring
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Spring" />"
						class="form-control" name="skills"> Spring
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('React')}">
					<input type="checkbox" value="<c:out value="React" />"
						class="form-control" name="skills" checked="checked"> React
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="React" />"
						class="form-control" name="skills"> React
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('Angular')}">
					<input type="checkbox" value="<c:out value="Angular" />"
						class="form-control" name="skills" checked="checked"> Angular
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Angular" />"
						class="form-control" name="skills"> Angular
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('Node')}">
					<input type="checkbox" value="<c:out value="Node" />"
						class="form-control" name="skills" checked="checked"> Node
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Node" />"
						class="form-control" name="skills"> Node
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.skills.contains('PHP')}">
					<input type="checkbox" value="<c:out value="PHP" />"
						class="form-control" name="skills" checked="checked"> PHP
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="PHP" />"
						class="form-control" name="skills"> PHP
                 </c:otherwise>
			</c:choose>

		</fieldset>
		<fieldset class="form-group">
			<label>Age</label> <input type="number"
				value="<c:out value='${employee.age}' />" class="form-control"
				name="age" required="required">
		</fieldset>
		<fieldset class="form-group">
			<label>Salary</label> <input type="number"
				value="<c:out value='${employee.salary}' />" class="form-control"
				name="salary" required="required">
		</fieldset>
		<fieldset class="form-group">
			<label>BirthDate</label> <input type="date"
				value="<c:out value='${employee.birthDate}' />" class="form-control"
				name="birthDate" required="required">
		</fieldset>
		<input type="submit" value="Submit"/>
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
	
</script>
