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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
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
    <table class="tg">
        <thead>
        <tr>
            <th class="tg-baqh">거리<br>(Km)</th>
            <th class="tg-baqh">관리번호</th>
            <th class="tg-baqh">자치구</th>
            <th class="tg-baqh">와이파이명</th>
            <th class="tg-baqh">도로명주소</th>
            <th class="tg-baqh">상세주소</th>
            <th class="tg-baqh">설치위치<br>(층)</th>
            <th class="tg-baqh">설치유형</th>
            <th class="tg-baqh">설치기관</th>
            <th class="tg-baqh">서비스구분</th>
            <th class="tg-baqh">망종류</th>
            <th class="tg-baqh">설치년도</th>
            <th class="tg-baqh">실내외구분</th>
            <th class="tg-baqh">WIFI접속환경</th>
            <th class="tg-baqh">X좌표</th>
            <th class="tg-baqh">Y좌표</th>
            <th class="tg-baqh">작업일자</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="entry" items="${NearWifiMap}" >
            <fmt:formatNumber var="key" value="${entry.key}" pattern="0.0000"/>
            <c:forEach var="list" items="${entry.value}">
              <c:set var="count" value="${count + 1}" />
                <c:choose>
                    <c:when test="${count % 2 != 0}">
                        <tr>
                            <td class="tg-dg7a">${key}</td>
                            <td class="tg-dg7a">${list.mgrNo}</td>
                            <td class="tg-dg7a">${list.gu}</td>
                            <td class="tg-dg7a">${list.wifiName}</td>
                            <td class="tg-dg7a">${list.adres1}</td>
                            <td class="tg-dg7a">${list.adres2}</td>
                            <td class="tg-dg7a">${list.instlFloor}</td>
                            <td class="tg-dg7a">${list.instlTY}</td>
                            <td class="tg-dg7a">${list.instlMBY}</td>
                            <td class="tg-dg7a">${list.svc}</td>
                            <td class="tg-dg7a">${list.networkType}</td>
                            <td class="tg-dg7a">${list.instlYear}</td>
                            <td class="tg-dg7a">${list.inOutDoor}</td>
                            <td class="tg-dg7a">${list.connEnvironment}</td>
                            <td class="tg-dg7a">${list.lat}</td>
                            <td class="tg-dg7a">${list.lnt}</td>
                            <td class="tg-dg7a">${list.workDateTime}</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td class="tg-0lax">${key}</td>
                            <td class="tg-0lax">${list.mgrNo}</td>
                            <td class="tg-0lax">${list.gu}</td>
                            <td class="tg-0lax">${list.wifiName}</td>
                            <td class="tg-0lax">${list.adres1}</td>
                            <td class="tg-0lax">${list.adres2}</td>
                            <td class="tg-0lax">${list.instlFloor}</td>
                            <td class="tg-0lax">${list.instlTY}</td>
                            <td class="tg-0lax">${list.instlMBY}</td>
                            <td class="tg-0lax">${list.svc}</td>
                            <td class="tg-0lax">${list.networkType}</td>
                            <td class="tg-0lax">${list.instlYear}</td>
                            <td class="tg-0lax">${list.inOutDoor}</td>
                            <td class="tg-0lax">${list.connEnvironment}</td>
                            <td class="tg-0lax">${list.lat}</td>
                            <td class="tg-0lax">${list.lnt}</td>
                            <td class="tg-0lax">${list.workDateTime}</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/javascript/index.js"></script>
</body>

</html>


<%--

.tg-dg7a 색칠
.tg-0lax 노색칠

<tbody>
<tr>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
  <td class="tg-dg7a"></td>
</tr>
<tr>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
  <td class="tg-0lax"></td>
</tr>
</tbody>
--%>