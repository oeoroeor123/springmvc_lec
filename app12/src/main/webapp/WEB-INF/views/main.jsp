<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
</head>
<body>

  <h1>Welcome Page</h1>
  
  <!-- loginUser가 비어있다면, 로그인과 회원가입 버튼을 보여준다. -->
  <c:if test="${empty sessionScope.loginUser}"> 
  <button type="button" id="btn-login-form">로그인</button>
  <button type="button" id="btn-signup-form">회원가입</button>
  
  <script>
    // 로그인 화면 뒤에 현재 페이지 연결하기 (로그인 이후 보고 있던 현재 페이지로 되돌아가기 위함)
    function loginForm() {
      document.getElementById('btn-login-form').addEventListener('click', (event) => {
        location.href='${contextPath}/user/login.form?url=' + location.href;
      })
    }
    
    // 로그인 폼 만들기
    function signupForm() {
      document.getElementById('btn-signup-form').addEventListener('click', (event) => {
        location.href='${contextPath}/user/signup.form';
      })
    }
    
    loginForm();
    signupForm();
  </script>
  </c:if>
  
  <!-- loginUser가 들어있다면, userName을 보여준다. -->
  <c:if test="${not empty sessionScope.loginUser}">
  <!-- href="#" : 마이페이지용 링크 -->
  <div>
  <span><a href="#">${sessionScope.loginUser.userName}</a>님 환영합니다!</span>
  <button type=button id="btn-logout">로그아웃</button>
  </div>
  
  <script>
    //로그아웃 링크
    function logout() {
      document.getElementById('btn-logout').addEventListener('click', (event) => {
        location.href='${contextPath}/user/logout.do';
      })  
    }
    logout();
  </script>
  </c:if>
  
  <div>
    <button type="button" id="btn-board-form">게시글 작성</button>
  </div>
  
  <script>
    // 게시글 작성
    function boardForm() {
      document.getElementById('btn-board-form').addEventListener('click', (event) => {
        location.href='${contextPath}/board/write.do';
      })  
    }
  
    // 메시지 출력
    function displayMsg() {
      const msg = '${msg}';
      if(msg !== '')
        alert(msg);
    }
    
    boardForm();
    displayMsg();
  </script>

</body>
</html>