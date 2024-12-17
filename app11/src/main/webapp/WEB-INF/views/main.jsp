<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome</title>
<style>
  .gate {
    width: 200px;
    height: 200px;
    text-align: center;
    line-height: 200px;
    background-color: beige;
    border-radius: 3px;
  }
  .gate:hover {
    background-color: yellowgreen;
    cursor: pointer;
  }
</style>
</head>
<body>

  <div id="gate" class="gate">
    입장
  </div>

  <script>
  
    document.getElementById('gate').addEventListener('click', (event) => {
      alert('입장합니다.');
      location.href = '${contextPath}/board/list.do';
    })
  
  </script>  

</body>
</html>