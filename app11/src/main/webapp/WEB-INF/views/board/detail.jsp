<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${b.title}</title>
<style>
  .form-wrap {
    width: 500px;
    margin: 0 auto;
  }
</style>
</head>
<body>

  <div class="form-wrap">
    <h1>Board Detail</h1>
    <form id="form-detail" method="post">
    <input type="hidden" name="boardId" value="${b.boardId}">
    <div>작성자 명 : ${b.userDto.usrName}</div>
    <div>작성 일시 : ${b.createDt}</div>
      <div class="title-wrap">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" value="${b.title}">
      </div>
      <div class="contents-wrap">
        <label for="contents">내용</label><br/>
        <textarea rows="5" cols="30" id="contents" name="contents">${b.contents}</textarea>
      </div>
      <div class="btn-wrap">
        <button type="button" onclick="fnModify()">수정완료</button>
        <button type="button" onclick="fnCancel()">수정취소</button>
        <button type="button" onclick="fnRemove()">삭제하기</button>        
      </div>
    </form>
  </div>

  <script>
  
   const formDetail = document.getElementById('form-detail');
   
   // 함수 표현식 : 호이스팅이 안된다.
   // const fnModify = () => { }
   
   // 함수 선언식 : 호이스팅이 된다. (코드가 어디에 있든지 항상 먼저 실행 된다.) 
   function fnModify() {
    formDetail.action='${contextPath}/board/modify.do';
    formDetail.submit();
   }
  
    function fnCancel() {
      location.href = '${contextPath}/board/list.do';
    }
    
    function fnRemove() {
      if(confirm('현재 게시물을 삭제하시겠습니까?')) {        
      formDetail.action='${contextPath}/board/remove.do'
      formDetail.submit();
      }
    }
    
    const msg = '${msg}';
    if(msg != '') {      
      alert(msg);
    }
  </script>

</body>
</html>