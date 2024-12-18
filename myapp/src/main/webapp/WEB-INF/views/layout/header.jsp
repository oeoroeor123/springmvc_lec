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
<script src="${contextPath}/assets/js/main.js?dt=<%=System.currentTimeMillis()%>"></script>
<title>${param.title}</title>
</head>
<body>

  
  <h1 id="logo" class="logo">Coupang</h1>

  <nav class="gnb-wrap">
    <ul class="gnb">
      <li><a href="${contextPath}/blog/list.do">블로그</a></li>
      <li><a href="${contextPath}/notice/list.do">공지사항</a></li>
      <li><a href="${contextPath}/notice/list.do">공지사항</a></li>
      <li><a href="${contextPath}/notice/list.do">공지사항</a></li>
      <li><a href="${contextPath}/notice/list.do">공지사항</a></li>
    </ul>
  </nav>
  
  
  
  <%-- /body 태그와 /html 태그 삭제 --%>