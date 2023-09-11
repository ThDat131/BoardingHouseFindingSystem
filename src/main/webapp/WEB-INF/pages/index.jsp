<%-- 
    Document   : index
    Created on : Aug 2, 2023, 4:29:50 PM
    Author     : truon
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="api/admin/statsNumOfTentant" var="endpointTentant"/>
<c:url value="api/admin/statsNumOfLandLord" var="endpointLandlord" />
<h1 class="text-info text-center">Chào mừng đến với trang quản trị</h1>
<div class="container">
    <h3 class="text-danger">Thống kê người dùng</h3>
    <div class="d-flex align-items-center gap-5 my-3">
        <div class="d-flex gap-2 ">
            <p class="mb-0">Năm bắt đầu</p>
            <input id="yf" type="date"/>
        </div>
        <div class="d-flex gap-2 ">
            <p class="mb-0">Năm kết thúc</p>
            <input id="yt" type="date" />
        </div>
        <button class="btn btn-info" onclick="handleShowUserStas(`${endpointTentant}`, `${endpointLandlord}`)">Thống kê</button>
        <div class="d-flex align-items-center justify-content-center gap-3">
            <button class="btn btn-info" onclick="generateChart('day')">Theo ngày</button>
            <button class="btn btn-info" onclick="generateChart('month')">Theo tháng</button>
            <button class="btn btn-info" onclick="generateChart('quarter')">Theo quý</button>
            <button class="btn btn-info" onclick="generateChart('year')">Theo năm</button>
        </div>
    </div>
    <div class="row my-3">
        <div class="col-6">
            <h4 class="text-center text-info">Số chủ trọ</h4>
            <div>
                <canvas id="landlordsStats"></canvas>
            </div>
        </div>
        <div class="col-6">
            <h4 class="text-center text-info">Số người thuê trọ</h4>
            <div>
                <canvas id="tentantsStats"></canvas>
            </div>
        </div>
    </div>
</div>


<script>
    <%--const ctx = document.getElementById('tentantsStats');--%>
    <%--let label = []--%>
    <%--let value = []--%>

    <%--const statsNumTentant = () => {--%>
    <%--    const dateFrom = document.getElementById("yf").value--%>
    <%--    const dateTo = document.getElementById("yt").value--%>
    <%--    console.log(`${dateFrom}+${dateTo}`)--%>
    <%--    fetch(`${endpointTentant}?f=${dateFrom}&t=${dateTo}`, {--%>
    <%--        method: "GET"--%>
    <%--}).then((res) => {--%>
    <%--    return res.json()--%>
    <%--}).then((data) => {--%>
    <%--    console.log(data)--%>
    <%--        data.forEach((item) => {--%>
    <%--            label.push(item[0])--%>
    <%--            value.push(item[1])--%>
    <%--        })--%>
    <%--        new Chart(ctx, {--%>
    <%--            type: 'bar',--%>
    <%--            data: {--%>
    <%--                labels: label,--%>
    <%--                datasets: [{--%>
    <%--                    label: '# of Votes',--%>
    <%--                    data: value,--%>
    <%--                    borderWidth: 1--%>
    <%--                }]--%>
    <%--            },--%>
    <%--            options: {--%>
    <%--                scales: {--%>
    <%--                    y: {--%>
    <%--                        beginAtZero: true--%>
    <%--                    }--%>
    <%--                }--%>
    <%--            }--%>
    <%--        });--%>

    <%--    })--%>
    <%--}--%>


</script>