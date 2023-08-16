<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 04/08/2023
  Time: 11:52 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1 class="text-center text-info">Quản lý user</h1>
    <div class="my-3">
        <a class="btn btn-info" href="<c:url value="/add-user" />">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Avatar</th>
            <th scope="col">Created Date</th>
            <th scope="col">Active</th>
            <th scope="col">Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="u">
            <tr>
                <td>${u.username}</td>
                <td>
                    <img src="${u.avatar}" alt="${u.username}'s avatar" width="120">
                </td>
                <td>${u.createdDate}</td>
                <td>${u.isActive}</td>
                <td>${u.role}</td>
                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <c:url value="/user/${u.username}" var="api"> </c:url>
                        <a href="${api}" class="btn btn-info me-3">Cập nhật</a>
                        <button class="btn btn-danger">Xoá</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

