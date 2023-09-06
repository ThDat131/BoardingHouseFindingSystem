<%--
  Created by IntelliJ IDEA.
  User: truon
  Date: 04/08/2023
  Time: 3:40 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
  <c:url value="/user" var="action" />
  <form:form modelAttribute="user" method="post" action="${action}"  enctype="multipart/form-data" >
    <div class="form-floating my-3">
      <form:input type="text" cssClass="form-control" id="username" placeholder="Username" name="username"  path="username" readonly="true"/>
      <label for="username">Username</label>
      <form:errors path="username" element="div" cssClass="alert alert-danger mt-2" />
    </div>
    <div class="form-floating my-3">
      <form:input type="password" cssClass="form-control" id="password" placeholder="Password" name="password"  path="password"/>
      <label for="password">Mật khẩu</label>
      <form:errors path="password" element="div" cssClass="alert alert-danger mt-2" />
    </div>
    <div class="form-floating my-3">
      <form:input type="file" cssClass="form-control" id="avatar" path="imgUrl"/>
      <label for="avatar">avatar</label>
      <c:choose>
        <c:when test="${user.avatar != null}">
          <img class="my-3" src="${user.avatar}" alt="avatar" id="avatar-preview" width="120">
        </c:when>
        <c:otherwise>
          <img class="my-3" src="#" alt="avatar" id="avatar-preview" width="120">
        </c:otherwise>
      </c:choose>

    </div>
    <div class="form-floating my-3">
      <div class="form-check">
        <form:radiobutton class="form-check-input" name="active" id="active" path="isActive" value="1" />
        <label class="form-check-label" for="active">
          Kích hoạt
        </label>
      </div>
      <div class="form-check">
        <form:radiobutton class="form-check-input" name="inactive" id="inactive" path="isActive" value="0" />
        <label class="form-check-label" for="inactive">
          Không kích hoạt
        </label>
      </div>
    </div>
    <div class="form-floating my-3">
      <form:select cssClass="form-select" id="role" name="role" path="role">
        <c:choose>
          <c:when test="${user.role == 2}">
            <option value="2" selected>Super Admin</option>
            <option value="1">Admin</option>
            <option value="0">Chủ trọ</option>
            <option value="-1">Người thuê trọ</option>
          </c:when>
          <c:when test="${user.role == 1}">
            <option value="1" selected>Admin</option>
            <option value="0">Chủ trọ</option>
            <option value="-1">Người thuê trọ</option>
          </c:when>
          <c:when test="${user.role == 0}">
            <option value="1">Admin</option>
            <option value="0" selected>Chủ trọ</option>
            <option value="-1">Người thuê trọ</option>
          </c:when>
          <c:when test="${user.role == -1}">
            <option value="1">Admin</option>
            <option value="0">Chủ trọ</option>
            <option value="-1" selected>Người thuê trọ</option>
          </c:when>
        </c:choose>

      </form:select>
      <label for="role">Vai trò</label>
    </div>
    <div class="form-floating my-3">

      <button type="submit" class="btn btn-info">
        <c:choose>
          <c:when test="${user.createdDate != null}">Cập nhật user</c:when>
          <c:otherwise>Thêm user</c:otherwise>
        </c:choose>
      </button>
    </div>
  </form:form>

</div>
<script>
  let avatarUploadFile = document.getElementById("avatar")
  let imagePreview = document.getElementById("avatar-preview")
  avatarUploadFile.onchange = (ev) => {
    if(ev.target.files && ev.target.files[0]) {
      imagePreview.src = URL.createObjectURL(ev.target.files[0])
    }
  }
</script>
