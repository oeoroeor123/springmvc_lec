<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="로그인"/>
</jsp:include>

  <h1>로그인</h1>
  
  <form action="${contextPath}/user/login.do" method="post">
    <input type="hidden" name="url" value="${url}"> <!-- 로그인 후 사용자가 모르게(hidden) model에서 지정한 url로 넘겨줌 -->
    <input type="text" name="userEmail" placeholder="이메일"><br/>
    <input type="password" name="userPw" placeholder="비밀번호"><br/>
    <button type="submit">로그인하기</button>
  </form>


</body>
</html>