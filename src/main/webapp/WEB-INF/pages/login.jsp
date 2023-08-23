<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 23/08/2023
  Time: 4:29 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/login" var="action" />
<div class="container">
    <h1 class="text-center text-info my-2">Đăng nhập</h1>
    <form method="post" action="${action}" >
        <div class="form-floating mb-3 mt-3">
            <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
            <label for="username">Tên đăng nhập</label>
        </div>

        <div class="form-floating mt-3 mb-3">
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
            <label for="pwd">Mật khẩu</label>
        </div>
        <div class="form-floating mt-3 mb-3">
            <input type="submit" value="Đăng nhập" class="btn btn-info" />
        </div>
    </form>
</div>

