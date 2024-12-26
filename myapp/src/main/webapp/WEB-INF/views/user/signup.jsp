<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="회원가입"/>
</jsp:include>

  <h1>Sign Up</h1>
  
  <form action="${contextPath}/user/signup.do" method="post">
    <input type="text" name="userEmail" placeholder="이메일"><br/>
    <input type="password" name="userPw" placeholder="비밀번호"><br/>
    <input type="text"  name="userName" placeholder="성명"><br/>
    <button type="submit">가입</button>
  </form>

</div>

</body>
</html>