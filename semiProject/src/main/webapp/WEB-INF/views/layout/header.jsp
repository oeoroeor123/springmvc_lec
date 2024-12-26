<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${contextPath}/assets/css/init.css?dt=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="${contextPath}/assets/css/header.css?dt=<%=System.currentTimeMillis()%>">
<script src="${contextPath}/assets/js/header.js?dt=<%=System.currentTimeMillis()%>"></script>
<script>
  function displayMsg() {
    const msg = '${msg}';
    if(msg !== '')
      alert(msg);
  }
  displayMsg();
</script>

<title>${param.title}</title>
</head>
<body>

  <h1 id="logo" class="logo">Naver</h1>

  <div class="user-info">
    <c:if test="${empty sessionScope.loginUser}">
      <button type="button" onclick="toLoginForm()"><i class="fa-solid fa-arrow-right-to-bracket"></i>로그인</button>
      <button type="button" onclick="toSignupForm()"><i class="fa-solid fa-user-plus"></i>회원가입</button>
    </c:if>
    <c:if test="${not empty sessionScope.loginUser}">
      <span><a style="color: red;" href="${contextPath}/user/mypage.do?userId=${sessionScope.loginUser.userId}">${sessionScope.loginUser.userName}</a>님 환영합니다.</span>
      <button type="button" onclick="doLogout()">로그아웃</button>
    </c:if>
  </div>

  <nav class="gnb-wrap">
    <ul class="gnb">
      <li><a href="${contextPath}/blog/list.do">블로그</a></li>
      <li><a href="${contextPath}/notice/list.do">공지사항</a></li>
      <li><a href="${contextPath}/notice/list.do">커뮤니티</a></li>
    </ul>
  </nav>
  
  <div class="wrap">