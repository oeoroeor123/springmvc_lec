<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board</title>
<style>
  .list-wrap {
    width: 500px;
    margin: 0 auto;
  }
</style>
</head>
<body>

  <div class="list-wrap">
    <h1>Board List</h1>
    <div>
      <form action="${contextPath}/board/search.do">
        <input type="text" name="title" placeholder="제목 검색"><br/>
        <input type="text" name="usrEmail" placeholder="사용자 이메일 검색"><br/>
        <input type="text" name="usrName" placeholder="사용자 이름 검색"><br/>
        <input type="date" name="beginDt">-<input type="date" name="endDt"><br/>
        <button type="submit">검색</button>
      </form>
    </div>
    <div>
      <a href="${contextPath}/board/list.do?sort=DESC">최신순</a> | <a href="${contextPath}/board/list.do?sort=ASC">과거순</a>
    </div>
    <div>
      <button type="button" onclick="fnWrite()">새 게시글 작성</button>
    </div>
  <form id="form-list" action="${contextPath}/board/removes.do" method="post">
    <div class="btn-wrap">
      <button type="submit">선택 삭제</button>
    </div>
    <table>
    <thead>
      <tr>
        <td><input type="checkbox"></td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일자</td>
      </tr>
    </thead>
      <tbody>
        <c:forEach items="${boardList}" var="b"> <!-- ${boardList} = List<boardDto> -->
          <tr>
            <td><input type="checkbox" name="numbers" value="${b.boardId}"></td>
            <td><a href="${contextPath}/board/detail.do?boardId=${b.boardId}">${b.title}</a></td>
            <td>${b.userDto.usrName}</td> <!-- userDto의 usrName 필드 -->
            <td><fmt:formatDate value="${b.createDt}" pattern="yyyy-MM-dd"/></td>
          </tr>
        </c:forEach>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="4">전체 ${boardCount}개 게시글</td>
        </tr>
      </tfoot>
    </table>
   </form>
  </div>
  
  <script>
  
  function fnWrite() {
    location.href = '${contextPath}/board/write.do';
  }

  const formList = document.getElementById('form-list');
  formList.addEventListener('submit', (event) => {
    if(!confirm("선택한 게시글을 삭제할까요?")) {
      event.preventDefault();  // 이벤트 취소
      return;                  // 이벤트 핸들러 실행 종료
    }
  })

  const msg = '${msg}';
  if(msg !== '') {
    alert(msg);
  }

  </script>


</body>
</html>