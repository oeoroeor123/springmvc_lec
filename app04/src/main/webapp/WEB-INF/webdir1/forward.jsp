<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL의 사용은 JSTL 라이브러리를 프로젝트에 포함해야만 가능하다. (이 작업은 pom.xml 파일에서 미리 처리 완료) --%>
<%-- 사용할 JSTL 라이브러리의 태그 라이브러리 디렉티브 작업이 필요하다. --%>
<%-- core 라이브러리에 속한 태그들은 c:으로 시작한다. --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title>Forward 결과 확인</title>
</head>
<body>
  
  <%-- 요청 파라미터가 전달되었는지 확인하기 --%>
  <%-- 요청 파라미터 id는 ${param.id} 방식으로 확인할 수 있다. --%>
  <div>${param.id}</div>
  
  <%-- 요청에 저장된 속성(Attribute) 확인하기 --%>
  <%-- 요청에 포함된 속성 email은 ${email} 처럼 속성 이름만 명시하는 방식으로 확인할 수 있다. --%>
  <div>${email}</div>
  <div>${address}</div>
    
  <%-- Map 확인하기 --%> 
  <%-- Map의 정보 확인은 ${map.key}방식으로 확인할 수 있다. --%>  
  <div>${contact.tel}</div>
  <div>${contact.mobile}</div>
  
  <%-- Person 인스턴스 확인하기 --%>
  <%-- 인스턴스는 ${인스턴스.필드} 방식으로 확인할 수 있다. --%>
  <div>${person.name}</div> <%-- 실제로는 person.getName() 메소드를 호출한다. --%>
  <div>${person.age}</div> <%-- 실제로는 person.getAge() 메소드를 호출한다. --%>
  
  <%-- List<String> 확인하기 --%>
  <%-- 반복 태그를 이용해서 확인할 수 있다. 반복 태그는 JSTL의 Core 라이브러리에 저장되어 있다. (화면 가장 위로 올라가 봅니다.) --%>
  <%-- items 속성에 List가 ${list} 형태로 입력된다. --%>
  <%-- var 속성에 List의 각 요소를 저장할 속성(Attribute)을 입력한다. --%>
  <c:forEach var="hobby" items="${hobbies}">
    <div>${hobby}</div> <%-- hobbies 리스트 길이에 맞춰 3번 돌아감 --%>
  </c:forEach>
  
  <%-- List<Person> 확인하기 --%>
  <c:forEach var="person" items="${people}">
    <div>${person.name}</div>
    <div>${person.age}</div>
  </c:forEach>
  
  
</body>
</html>