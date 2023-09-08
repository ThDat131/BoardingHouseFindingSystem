<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 06/09/2023
  Time: 1:45 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info">Danh sách user chủ trọ chưa kích hoạt</h1>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Tên đầy đủ</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Chứng minh nhân dân</th>
            <th scope="col">Hình ảnh phòng trọ</th>

            <th></th>
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
                <td class="d-flex justify-content-center align-items-center gap-3">
                    <c:forEach items="${l.imageSet}" var="i">
                        <a href="${i.url}" data-toggle="lightbox" data-gallery="example-gallery">
                            <img src="${i.url}" width="100" height="50"/>
                        </a>
                    </c:forEach>
                </td>
                <td>
                    <div class="d-flex justify-content-center align-items-center">
                        <c:url value="/api/activate/${l.username.username}" var="endpoint"> </c:url>
                        <button class="btn btn-info me-3" onclick="activateUser('${endpoint}')">Kích hoạt</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <script src="https://cdn.jsdelivr.net/npm/bs5-lightbox@1.8.3/dist/index.bundle.min.js"></script>

</div>


