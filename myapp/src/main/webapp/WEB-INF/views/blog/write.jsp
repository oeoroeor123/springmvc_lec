<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="블로그 작성"/>
</jsp:include>

<style>
  #contents {
    width: 200px;
    min-height: 200px;
  }
</style>

  <h1>Blog Write</h1>

  <form id="form-write" action="${contextPath}/blog/register.do" method="post">
  
    <div>
      <label for="user_email">작성자 이메일</label>
      <input type="text" name="user_email" id="user_email" value="${sessionScope.loginUser.userEmail}" readonly>
    </div>
    
    <div>
      <label for="title">제목</label>
      <input type="text" name="title" id="title">
    </div>
  
    <div>
      <textarea name="contents" id="contents" placeholder="내용"></textarea>
    </div>
    
    <div>
      <button type="submit">작성 완료</button>
      <button type="reset">입력 초기화</button>
    </div>
    
  </form>
  
  <script>
  
    function submitForm() {    
      const formWrite = document.getElementById('form-write');
      const userEmail = document.getElementById('user_email');
      const title = document.getElementById('title');
      formWrite.addEventListener('submit', (event) => {
        if(userEmail.value === '') {
          alert('작성자 이메일은 필수입니다.');
          userEmail.focus();
          event.preventDefault();  // submit 을 취소합니다.
          return;                  // 바로 아래 if 문을 수행하지 않고 바로 이벤트 핸들러를 종료합니다.
        }
        if(title.value === '') {
          alert('제목은 필수입니다.');
          title.focus();
          event.preventDefault();  // submit 을 취소합니다.
          return;
        }
      })
    }
    submitForm();
  </script>

</div>

</body>
</html>