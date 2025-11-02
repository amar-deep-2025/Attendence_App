<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Student</title>
   
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add.css" />
</head>
<body>
    <h2>Add New Student</h2>

    <div class="form-container">

        <!-- ✅ Success message -->
        <c:if test="${not empty message}">
            <div class="alert success">${message}</div>
        </c:if>

        <!-- ✅ Error message -->
        <c:if test="${not empty errorMessage}">
            <div class="alert error">${errorMessage}</div>
        </c:if>

        <form:form modelAttribute="student" 
                   action="${pageContext.request.contextPath}/students/add"
                   method="post">

            <label>Name:</label>
            <form:input path="name" required="true" />
            <form:errors path="name" cssClass="error-text" />
            <br><br>

            <label>Email:</label>
            <form:input path="email" type="email" required="true" />
            <form:errors path="email" cssClass="error-text" />
            <br><br>

            <label>Password:</label>
            <form:password path="password" required="true" />
            <form:errors path="password" cssClass="error-text" />
            <br><br>

            <label>Phone Number:</label>
            <form:input path="phoneNumber" required="true" />
            <form:errors path="phoneNumber" cssClass="error-text" />
            <br><br>

            <label>Registration Number:</label>
            <form:input path="registrationNumber" required="true" />
            <form:errors path="registrationNumber" cssClass="error-text" />
            <br><br>

            <label>Role:</label>
            <form:select path="role" required="true">
                <form:option value="">--Select--</form:option>
                <form:option value="ADMIN">ADMIN</form:option>
                <form:option value="TEACHER">TEACHER</form:option>
                <form:option value="STUDENT">STUDENT</form:option>
                <form:option value="USER">USER</form:option>
            </form:select>
            <form:errors path="role" cssClass="error-text" />
            <br><br>

            <button type="submit">Save</button>
            <a href="${pageContext.request.contextPath}/students">Cancel</a>
            <a href="${pageContext.request.contextPath}/students">View All</a>

        </form:form>
    </div>
</body>
</html>
