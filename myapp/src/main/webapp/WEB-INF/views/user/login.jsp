<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
</head>
<body>

  <h1>로그인</h1>
  
  <form action="${contextPath}/main/login.do" method="post">
    <input type="hidden" name="url" value="${url}"> <!-- 로그인 후 사용자가 모르게(hidden) model에서 지정한 url로 넘겨줌 -->
    <input type="text" name="userEmail" placeholder="이메일"><br/>
    <input type="password" name="userPw" placeholder="비밀번호"><br/>
    <button type="submit">로그인하기</button>
  </form>
  
  <script>
  // 로그인 시 메세지 노출
  function displayMsg() {
    const msg = '${msg}';
    if(msg !== '')
      alert(msg);
  }
  
  displayMsg();
  </script>

</body>
</html>