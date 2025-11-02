<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Students List</title>
    <link rel="stylesheet"  type="text/css" href="${pageContext.request.contextPath}/css/list.css" />
</head>
<body>
    <h2>All Students</h2>

    <a href="${pageContext.request.contextPath}/students/add">➕ Add New Student</a>
    <br><br>

    <c:if test="${not empty message}">
        <p style="color: green;">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Registration Number</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="stu" items="${students}">
            <tr>
                <td>${stu.id}</td>
                <td>${stu.name}</td>
                <td>${stu.email}</td>
                <td>${stu.registrationNumber}</td>
                <td>${stu.role}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/students/edit/${stu.id}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/students/delete/${stu.id}"
                       onclick="return confirm('Are you sure you want to delete this student?');">
                       Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
