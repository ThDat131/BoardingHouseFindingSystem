<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 21/08/2023
  Time: 10:15 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1 class="text-center text-info">Quản lý chủ trọ</h1>
    <div class="my-3">
        <a class="btn btn-info" href="<c:url value="/add-landlord" />">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Tên đầy đủ</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Chứng minh nhân dân</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${landlords}" var="l">
            <tr>
                <td>${l.username.username}</td>
                <td>${l.fullName}</td>
                <td>${l.address}</td>
                <td>${l.phone}</td>
                <td>${l.personalId}</td>
                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <c:url value="/landlord/${l.username.username}" var="api"> </c:url>
                        <a href="${api}" class="btn btn-info me-3">Cập nhật</a>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
