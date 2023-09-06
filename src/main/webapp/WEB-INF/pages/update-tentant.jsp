<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 06/09/2023
  Time: 8:50 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/tentant-update" var="action" />
<div class="container">
    <h1 class="text-center text-info mt-2">Cập nhật người thuê trọ</h1>
    <form:form modelAttribute="tentant" method="post" action="${action}">
        <form:hidden path="id" />
        <form:hidden path="username" />
        <div class="form-floating my-3">
            <form:input type="text" cssClass="form-control" id="fullname" placeholder="Fullname" name="fullname" path="fullName"/>
            <label for="fullname">Họ tên</label>
            <form:errors path="fullName" element="div" cssClass="alert alert-danger mt-2" />
        </div>
        <div class="form-floating my-3">
            <form:input type="email" cssClass="form-control" id="email" placeholder="Email" name="email" path="email" />
            <label for="email">Email</label>
            <form:errors path="email" element="div" cssClass="alert alert-danger mt-2" />
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
        <button type="submit" class="btn btn-info">Cập nhật</button>
    </form:form>
</div>
