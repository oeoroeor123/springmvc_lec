<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title>Person List</title>
<script>

  // Model에 저장된 속성(Attribute) msg1 확인하기, 확인이 되면 전달 완료 / 확인이 안되면 전달 실패
  // >> 전달 실패 (redirect는 데이터 전달이 아니기 때문에 Model 사용 불가)
  const msg1 = '${msg1}';
  alert('msg1 : ' + msg1);
  
  // redirectAttributes에 저장된 플래시 속성 msg2 확인하기
  const msg2 = '${msg2}';
  alert('msg2 : ' + msg2);
  
  
</script>
</head>
<body>
  <!-- 목록을 출력하는 JSP -->
  
  <%-- contextPath가 속성(Attribute)으로 저장되는 태그 --%>
  <%-- var 태그 속성 : 속성 이름 작성 / value 태그 속성 : 속성 값 작성 --%>
  <c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

  <%-- 위에서 contextPath 속성 태그를 만들었기 때문에 ${contextPath}만 작성하면 됨 --%>
  <a href="${contextPath}/redirect/main.do">작성하러가기</a>
  
  <%-- 속성 표현법을 "EL" 이라고 부른다. : ${속성이름} --%>
  <div>${contextPath}</div>
  
  <%-- 속성 people 반복 태그로 출력하기 --%>
  <c:forEach var="person" items="${people}">
    <div>
      <div>${person.name}</div> <%-- 실제론 person.getName()이 동작한다. --%>
      <div>${person.age}</div>  <%-- 실제론 person.getAge()이 동작한다. --%>
    </div>
  </c:forEach>
  


</body>
</html>