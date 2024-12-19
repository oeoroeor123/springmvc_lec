<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="비밀번호 변경"/>
</jsp:include>

  <h1>Password Change</h1>
  
  <%-- 비밀번호 변경 시, 비밀번호 확인이 일치하는지 체크하기, 맞지 않으면 방지 --%>
  
  <form id="form-repw" action="${contextPath}/user/repw.do" method="post">
    <input type="hidden" name="userId" value="${sessionScope.loginUser.userId}"> <%-- 숨은 필드로 사용자의 userId 값을 서버로 전송 --%>
    <input type="password" name="userPw" id="userPw" placeholder="신규 비밀번호"><br/>
    <input type="password" id="userPw2"  placeholder="비밀번호 확인"><br/>
    <button type="submit">비밀번호변경하기</button>
  </form>

  <script>
  <%-- form-repw에서 확인할 필드 3가지 하나씩 가져와서 확인하고, 맞지 않으면 함수 종료 --%>
  
    function submitForm() {
      const formRepw = document.getElementById('form-repw');
      const userPw = document.getElementById('userPw');
      const userPw2 = document.getElementById('userPw2');
      formRepw.addEventListener('submit', (event) => {
        if(userPw.value !== userPw2.value) {
          alert('입력한 비밀번호를 확인하세요.');
          event.preventDefault(); <%-- 비밀번호가 일치하지 않으면 폼 제출 막기 --%>
          return;
        }
      })
    }
    submitForm();
  </script>
  
</body>
</html>