<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 03/08/2023
  Time: 9:44 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">Trang chủ</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbar_manage_user" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý người dùng
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbar_manage_user">
                        <li><a class="dropdown-item" href="<c:url value="/users" />">User</a></li>
                        <li><a class="dropdown-item" href="#">Người thuê trọ</a></li>
                        <li><a class="dropdown-item" href="#">Chủ trọ</a></li>
                        <li><a class="dropdown-item" href="#">Follow</a></li>

                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbar_manage" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý bài viết
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbar_manage">
                        <li><a class="dropdown-item" href="#">Bài viết</a></li>
                        <li><a class="dropdown-item" href="#">Bình luận</a></li>
                        <li><a class="dropdown-item" href="#">Follow</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý phòng trọ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý hình ảnh</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Quản lý thông báo</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
