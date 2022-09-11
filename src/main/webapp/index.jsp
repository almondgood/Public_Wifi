<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">

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
<div class="content content-table">
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
        <tr>
            <td class="tg-yq6s" colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
        </tr>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/javascript/index.js"></script>
</body>

</html>