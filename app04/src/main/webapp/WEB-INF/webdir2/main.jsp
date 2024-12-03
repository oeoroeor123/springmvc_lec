<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title>Redirect</title>
</head>
<body>

  <form action="<%=request.getContextPath()%>/redirect/insert.do" method="post">
    <input type="text" name="name" placeholder="이름"><br/>
    <input type="number" name="age" placeholder="나이"><br/>
    <button type="submit">입력하기</button>
  </form>
  
</body>
</html>