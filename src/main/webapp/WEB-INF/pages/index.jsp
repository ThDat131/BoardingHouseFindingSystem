<%-- 
    Document   : index
    Created on : Aug 2, 2023, 4:29:50 PM
    Author     : truon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Users Report</h1>
<table>
    <tr>
        <th>Name</th>
        <th>Age</th>
    </tr>
    <c:forEach var="u" items="${usersFilter}">
        <tr>
            <td>${u.username}</td>
            <td>${u.createDate}</td>
        </tr>
    </c:forEach>
</table>
