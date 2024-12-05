<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Contact Write Form</title>
</head>
<body>

  <h1>Contact Register</h1>

  <form action="${contextPath}/contact/register.do" method="post">
  
    <div>
      <label for="last_name">LAST NAME</label>
      <input type="text" name="last_name" id="last_name">
    </div>
    
    <div>
      <label for="first_name">FIRST NAME</label>
      <input type="text" name="first_name" id="first_name">
    </div>
    
    <div>
      <label for="email">EMAIL</label>
      <input type="text" name="email" id="email">
    </div>
    
    <div>
      <label for="mobile">MOBILE</label>
      <input type="text" name="mobile" id="mobile">
    </div>
    
    <div>
      <button type="submit">등록 완료</button>
      <button type="reset">입력 초기화</button>
      <button type="button" id="btn-list">연락처 목록</button>
    </div>
  
  </form>
  
  <script>
    document.getElementById('btn-list').addEventListener('click', (event) => {
      location.href='${contextPath}/contact/list.do';
    })
  </script>

</body>
</html>