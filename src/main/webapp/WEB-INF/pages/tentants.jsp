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
    <h1 class="text-center text-info">Quản lý người thuê trọ</h1>
    <div class="my-3">
        <a class="btn btn-info" href="<c:url value="/add-tentant" />">Thêm</a>
    </div>
    <table class="table">
        <thead>
        <tr>
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
                <td>${t.fullName}</td>
                <td>${t.email}</td>
                <td>${t.phone}</td>
                <td>${t.personalId}</td>
                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <c:url value="/landlord/${t.username.username}" var="api"> </c:url>
                        <a href="${api}" class="btn btn-info me-3">Cập nhật</a>
                        <button class="btn btn-danger">Xoá</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
