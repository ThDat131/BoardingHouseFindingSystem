<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 07/09/2023
  Time: 2:25 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info">Danh sách user thuê trọ chưa kích hoạt</h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Tên đầy đủ</th>
            <th scope="col">Email</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Chứng minh nhân dân</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tentants}" var="t">
            <tr>
                <td>${t.username.username}</td>
                <td>${t.fullName}</td>
                <td>${t.email}</td>
                <td>${t.phone}</td>
                <td>${t.personalId}</td>

                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <c:url value="/api/activate/${t.username.username}" var="endpoint"> </c:url>
                        <button class="btn btn-info me-3" onclick="activateUser('${endpoint}')">Kích hoạt</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
