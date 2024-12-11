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
  .form-wrap {
    width: 500px;
    margin: 0 auto;
  }
</style>
</head>
<body>

  <div class="form-wrap">
    <h1>Board Write</h1>
    <form action="${contextPath}/board/register.do" method="post">
      <div class="usr-wrap">
        <label for="usrId">작성자</label>
        <input type="text" id="usrId" name="userDto.usrId" placeholder="번호">
      </div>
      <div class="title-wrap">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" autofocus>
      </div>
      <div class="contents-wrap">
        <label for="contents">내용</label><br/>
        <textarea rows="5" cols="30" id="contents" name="contents"></textarea>
      </div>
      <div class="btn-wrap">
        <button type="submit">작성완료</button>
        <button type="button" onclick="fnCancel()">작성취소</button>
      </div>
    </form>
  </div>

  <script>
    function fnCancel() {
      location.href = '${contextPath}/board/list.do';
    }
  </script>

</body>
</html>