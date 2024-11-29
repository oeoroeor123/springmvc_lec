<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>
  
  <!-- HTML 주석 : 페이지 소스 보기에서 주석이 보인다  -->
  <%-- JSP 주석 : 페이지 소스 보기에서 주석이 안보인다.(Java 코드는 이 주석만 사용할 수 있다.) --%>
  
  <%--     
    화면에서(JSP 파일에서) 요청 주소를 만들 때 context path를 작성해야 한다. ex_a href="/app03/webdir3/req1"
    context path는 변수로 처리하는 것이 좋다.(사용 유무, 내용 등이 달라질 수 있기 때문에)
  --%>
  
  <%--
    context path(/app03)를 변수 처리하는 방법
    
    1. 표현식 이용하기
       <%=request.getContextPath()%>
       
    2. JSTL과 표현 언어 이용하기
      <%@ taglib uri="c" prefix="http://java.sun.com/jsp/jstl/core" %>
      <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
      ${contextPath}
  --%>
  
  <%-- 요청 만들기 1 : <a> 태그 (<a> 태그는 항상 GET 방식으로 요청한다.) --%>
  <div>
    <a href="<%=request.getContextPath()%>/webdir3/req1">요청1</a>
    <br/>
    <a href="<%=request.getContextPath()%>/webdir3/req2?sort=ASC&page=1">요청2</a>
    <br/>
    <a href="<%=request.getContextPath()%>/webdir3/req3?flowers=ROSE&flowers=TULIP">요청3</a>
    <br/>
    <a href="<%=request.getContextPath()%>/webdir3/req4">요청4</a>
    <br/>
    <a href="<%=request.getContextPath()%>/webdir3/req5?sort=ASC">요청5</a>
    <br/>
    <a href="<%=request.getContextPath()%>/webdir3/req6?sort=ASC&page=1">요청6</a>
  </div>
  
  

</body>
</html>