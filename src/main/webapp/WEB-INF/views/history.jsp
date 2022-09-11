<%--
  Created by IntelliJ IDEA.
  User: jwt27
  Date: 2022-08-18-(018)
  Time: 오후 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>와이파이 정보 구하기</h1>
<div class="menu">
    <a href="/" class="menu-item menu-item-first">홈</a>
    <a href="history" class="menu-item">위치 히스토리 목록</a>
    <a href="load_data" class="menu-item menu-item-last">Open API 와이파이 정보 가져오기</a>
</div>
<div class="coordinate centered">
    <form action="result" method="get">
        <div class="latitude">
            <label for="latitude">LAT:</label>
            <input type="text" name="lat" id="latitude">
        </div>
        <div class="longitude">
            <label for="longitude">LNT:</label>
            <input type="text" name="lnt" id="longitude">
        </div>
        <input type="button" value="내 위치 가져오기" id="get-my-location">
        <input type="submit" value="근처 WIFI 정보 보기">
    </form>
</div>
<div class="content">
    <form action="" method="post" name="id" class="tg">
        <table>
            <thead>
            <tr>
                <th class="tg-baqh">ID</th>
                <th class="tg-baqh">X좌표</th>
                <th class="tg-baqh">Y좌표</th>
                <th class="tg-baqh">조회일자</th>
                <th class="tg-baqh">비고</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="history" items="${HistoryList}" varStatus="status">
                <c:choose>
                    <c:when test="${status.index % 2 != 0}">
                        <tr>
                            <td class="tg-dg7a">${history.id}</td>
                            <td class="tg-dg7a">${history.lat}</td>
                            <td class="tg-dg7a">${history.lnt}</td>
                            <td class="tg-dg7a">${history.historyDateTime}</td>
                            <td class="tg-dg7a">
                                <button type="submit" name="deleteRow" value="${history.id}" >삭제
                                </button>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td class="tg-0lax">${history.id}</td>
                            <td class="tg-0lax">${history.lat}</td>
                            <td class="tg-0lax">${history.lnt}</td>
                            <td class="tg-0lax">${history.historyDateTime}</td>
                            <td class="tg-0lax">
                                <button type="submit" name="deleteRow" value="${history.id}">삭제
                                </button>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </form>
</div>
<script src="${pageContext.request.contextPath}/javascript/index.js"></script>
<script src="${pageContext.request.contextPath}/javascript/history.js"></script>
</body>

</html>