<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student Datahdj</title>
</head>
<body>

<div class="form-container">

<c:if test="${not empty message}">
<div class="alert success">${message}></div>
</c:if>

<c:if test="${not empty errorMessage}">
<div class="alert error">${errorMessage}></div>
</c:if>


<form modelAttribute="student"
action="${pageContext.request.contextPath}/students/edit"
method="post">

<form:hidden path="id" />

<label>Name:</label>
<form:input path="name" required="true"/>
<form: errors path="name" cssClass="error-text" /><br><br>
</form>

</div>
</body>
</html>