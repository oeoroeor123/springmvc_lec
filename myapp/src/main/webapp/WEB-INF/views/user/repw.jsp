<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="비밀번호 변경"/>
</jsp:include>

  <h1>Password Change</h1>
  
  <form id="form-repw" action="${contextPath}/user/repw.do" method="post">
    <input type="hidden" name="userId" value="${sessionScope.loginUser.userId}">
    <input type="password" name="userPw" id="userPw" placeholder="신규 비밀번호"><br/>
    <input type="password" id="userPw2"  placeholder="비밀번호 확인"><br/>
    <button type="submit">비밀번호변경하기</button>
  </form>

  <script>
    function submitForm() {
      const formRepw = document.getElementById('form-repw');
      const userPw = document.getElementById('userPw');
      const userPw2 = document.getElementById('userPw2');
      formRepw.addEventListener('submit', (event) => {
        if(userPw.value !== userPw2.value) {
          alert('입력한 비밀번호를 확인하세요.');
          event.preventDefault();
          return;
        }
      })
    }
    submitForm();
  </script>

</div>

</body>
</html>