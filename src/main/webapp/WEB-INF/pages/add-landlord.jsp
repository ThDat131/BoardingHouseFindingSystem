<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 21/08/2023
  Time: 10:45 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/landlord" var="action" />

<div class="container">
    <h1 class="text-center text-info mt-2">Thêm chủ trọ</h1>
    <form:form modelAttribute="landlorduser" method="post" action="${action}"  enctype="multipart/form-data">
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="username" placeholder="Username" name="username" path="username" />
            <label for="username">Username</label>
            <form:errors path="username" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <form:input type="password" cssClass="form-control" id="password" placeholder="Mật khẩu" name="password" path="password" />
            <label for="username">Mật khẩu</label>
            <form:errors path="password" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <input type="password" class="form-control" id="repassword" placeholder="Mật khẩu" name="password" />
            <label for="repassword">Nhập lại mật khẩu</label>
        </div>
        <div class="form-floating my-3">
            <form:input type="file" cssClass="form-control" id="avatar-file" path="file"/>
            <label for="avatar-file">avatar</label>
        </div>
        <div class="form-floating my-3">
            <div class="form-check">
                <form:radiobutton class="form-check-input" name="active" id="active" path="active" value="1" />
                <label class="form-check-label" for="active">
                    Kích hoạt
                </label>
            </div>
            <div class="form-check">
                <form:radiobutton class="form-check-input" name="active" id="inactive" path="active" value="0" />
                <label class="form-check-label" for="inactive">
                    Không kích hoạt
                </label>
            </div>
        </div>
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="fullname" placeholder="Fullname" name="fullname" path="fullName" />
            <label for="fullname">Họ tên</label>
            <form:errors path="fullName" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="address" placeholder="Address" name="address" path="address" />
            <label for="address">Địa chỉ</label>
            <form:errors path="address" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="phone" placeholder="Phone" name="phone" path="phone" />
            <label for="phone">Số điện thoại</label>
            <form:errors path="phone" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="personalId" placeholder="personalId" name="personalId" path="personalId" />
            <label for="personalId">Chứng minh nhân dân</label>
            <form:errors path="personalId" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <button type="submit" class="btn btn-info">Thêm</button>
    </form:form>
</div>
