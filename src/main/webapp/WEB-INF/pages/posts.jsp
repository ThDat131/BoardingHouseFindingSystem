<%--
  Created by IntelliJ IDEA.
  User: Minh Tran
  Date: 9/5/2023
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <h1 class="text-center text-info">Quản lý bài viết</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Tựa đề</th>
            <th scope="col">Nội dung</th>
            <th scope="col">Người đăng</th>
            <th scope="col">Ngày tạo</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${posts}" var="p">
            <tr>
                <td>${p.name}</td>
                <td>${p.content}</td>
                <td>${p.username}</td>
                <td>${p.createdDate}</td>
                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <button class="btn btn-danger">Xóa</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
