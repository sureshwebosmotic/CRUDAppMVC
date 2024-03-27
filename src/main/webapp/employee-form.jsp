<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import ="java.util.List.*"%>

<html>
<head>
<title>Employee Management App</title>
</head>
<body>
	<div>
		<a href="<%=request.getContextPath()%>/getAll">View Users</a>
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
		<h2>
			<c:if test="${employee != null && (employee.employeeId == null)}"> Add User </c:if>
			<c:if test="${employee != null && (employee.employeeId != null)}"> Edit User </c:if>
			<c:if test="${employee == null}"> Add New User </c:if>
		</h2>
		<fieldset>
			<label>Name</label> <input type="text"
				value="<c:out value='${employee.name}' />" name="name"
				required="required" pattern="[A-Za-z]+"> <br> <br>
			<label>Skills</label> <br>
			<c:if test="${employee != null}">
				<input type="hidden" name="employeeId"
					value="<c:out value='${employee.employeeId}' />" />
			</c:if>

<%
List<String> masterSkills = new ArrayList<>();
masterSkills.add("Java");
masterSkills.add("Spring");
masterSkills.add("React");
masterSkills.add("Angular");
masterSkills.add("Node");
masterSkills.add("PHP");

%>
	
               
       
        <c:forEach var="masterskill" items="<%=masterSkills %>">  
        <% boolean hasSkill = false; %>
       
        	<c:forEach var="skill" items="${employee.skills}">
        	<c:if test="${masterskill == skill}"> <% hasSkill = true; %> </c:if>
        		
    		</c:forEach>
    		<c:choose>
				<c:when test="<%=hasSkill%>">
					<input type="checkbox" value="<c:out value="${masterskill}" />" name="skills" checked="checked"> ${masterskill}
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="${masterskill}" />" name="skills"> ${masterskill}
                 </c:otherwise>
			</c:choose>
			<br>
    	</c:forEach>
    



			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('Java')}">
					<input type="checkbox" value="<c:out value="Java" />" name="skills"
						checked="checked"> Java
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Java" />" name="skills"> Java
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('Spring')}">
					<input type="checkbox" value="<c:out value="Spring" />"
						name="skills" checked="checked"> Spring
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Spring" />"
						name="skills"> Spring
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('React')}">
					<input type="checkbox" value="<c:out value="React" />"
						name="skills" checked="checked"> React
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="React" />"
						name="skills"> React
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('Angular')}">
					<input type="checkbox" value="<c:out value="Angular" />"
						name="skills" checked="checked"> Angular
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Angular" />"
						name="skills"> Angular
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('Node')}">
					<input type="checkbox" value="<c:out value="Node" />" name="skills"
						checked="checked"> Node
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="Node" />" name="skills"> Node
                 </c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when
					test="${employee != null && employee.responseSkill.contains('PHP')}">
					<input type="checkbox" value="<c:out value="PHP" />" name="skills"
						checked="checked"> PHP
                 </c:when>
				<c:otherwise>
					<input type="checkbox" value="<c:out value="PHP" />" name="skills"> PHP
                 </c:otherwise>
			</c:choose>
			<br> <br> <label>Age</label> <input type="number"
				value="<c:out value='${employee.age}' />" name="age"
				required="required"> <br> <br> <label>Salary</label>
			<input type="number" value="<c:out value='${employee.salary}' />"
				name="salary" required="required"> <br> <br> <label>BirthDate</label>
			<input type="date"
				onfocus="this.max=new Date().toISOString().split('T')[0]"
				value="<c:out value='${employee.birthDate}' />" name="birthDate"
				required="required"> <br> <br> <input
				type="submit" value="Submit" />
		</fieldset>
		</form>
	</div>
</body>
</html>