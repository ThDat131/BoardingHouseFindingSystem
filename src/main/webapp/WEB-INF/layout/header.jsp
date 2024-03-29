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
        <a class="navbar-brand" href="<c:url value="/" />">
            <img src="https://res.cloudinary.com/dgyytgkae/image/upload/v1694272180/Phong_Tro_MD-logos_white_zwkfhe.png" width="60" height="60"/>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Trang chủ</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbar_manage_user" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý người dùng
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbar_manage_user">
                        <li><a class="dropdown-item" href="<c:url value="/users" />">User</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tentants" />">Người thuê trọ</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/landlords" />">Chủ trọ</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/landlords-inactivate" />">Kích hoạt user chủ trọ</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/tentants-inactivate" />">Kích hoạt user thuê trọ</a></li>

                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbar_manage" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Quản lý bài viết
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbar_manage">
                        <li><a class="dropdown-item" href="<c:url value="/posts" />">Bài viết</a></li>
                        <li><a class="dropdown-item" href="#">Bình luận</a></li>
                        <li><a class="dropdown-item" href="#">Follow</a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <li class="nav-item">
                            <a class="nav-link text-success" href="<c:url value="/" />">Chào ${pageContext.request.userPrincipal.name} !</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-danger" href="<c:url value="/logout" />">Đăng xuất</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link text-danger" href="<c:url value="/login" />">Đăng nhập</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>
