<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>계층형 게시판</title>
<style>
  .hidden {
    display: none;
  }
</style>
</head>
<body>

  <h1>게시글 작성하기(원글만 작성)</h1>
  <form action="${contextPath}/bbs/registBbs.do" method="post">
  
    <!-- 로그인 안 한 사용자가 textarea 태그를 클릭하면 로그인 여부는 묻는 대화상자 띄우기 -->
    <textarea rows="5" cols="30" name="contents" placeholder="작성하려면 로그인 해 주세요."></textarea><br/>
    <button type="submit">작성완료</button>
  </form>
  
  <hr/>
  
  <h1>게시글 목록</h1>
  <div>전체 게시글 개수 ${count}개</div>
  
  <div>${paging}</div>
  
 <div>
    <c:forEach items="${bbsList}" var="b" varStatus="k">
      <div>
        <span style="display: inline-block; width: 100px;">${offset + k.count}</span>
        <c:if test="${b.state == 1}">
          <span>삭제된 게시글입니다.</span>
        </c:if>
        <c:if test="${b.state == 0}">
          <!-- 댓글 수준 별 들여쓰기를 공백으로 구현합니다. -->
          <span style="display: inline-block;"><c:forEach begin="1" end="${b.depth}" step="1">&nbsp;&nbsp;</c:forEach></span>
          <!-- 댓글이나 대댓글은 내용 앞에 [Re]를 표시합니다. -->
          <c:if test="${b.depth > 0}">
            <span style="display: inline-block;">[Re]</span>
          </c:if>
          <pre style="display: inline-block; width: 500px;">${b.contents}</pre>
          <span style="display: inline-block;">${b.createdAt}</span>
          <button type="button" class="btn-form-reply" data-index="${k.index}">댓글달기</button>
          <button type="button" class="btn-delete" data-bbs-id="${b.bbsId}">삭제</button>
        </c:if>
      </div>
      <div class="form-reply hidden show${k.index}">
        <form action="${contextPath}/bbs/registBbsReply.do" method="post">
          <!-- 원글의 depth, group_id, group_order를 포함해야 합니다. -->
          <input type="hidden" name="depth" value="${b.depth}">
          <input type="hidden" name="groupId" value="${b.groupId}">
          <input type="hidden" name="groupOrder" value="${b.groupOrder}">
          <textarea rows="5" cols="30" name="contents" placeholder="작성하려면 로그인 해 주세요."></textarea><br/>
          <button type="submit">작성완료</button>
        </form>
      </div>
    </c:forEach>
  </div>

  <script>
    function displayMsg() {
      const msg = '${msg}';
      if(msg !== '')
        alert(msg);
    }
    
    function hiddenAllFormReply() {
      const formReply = document.getElementsByClassName('form-reply');
      for(const form of formReply) {
        form.classList.add('hidden');  // 모든 댓글 입력 폼에 class="hidden"을 추가합니다. 그러면 CSS에 의해서 화면에서 사라집니다.
      }
    }
    
    function displayFormReply() {
      const btnFormReply = document.getElementsByClassName('btn-form-reply');
      for(const btn of btnFormReply) {
        btn.addEventListener('click', (event) => {
          hiddenAllFormReply();  // 모든 댓글 입력 폼을 숨깁니다. 
          const target = event.currentTarget.parentElement.nextElementSibling;  // 화면에 표시할 댓글 입력 폼입니다.
          target.classList.remove('hidden');  // 화면에 표시할 댓글 입력 폼의 class="hidden" 속성을 없앱니다.
        })
      }
    }
    
    function deleteBbs() {
      const btnDelete = document.getElementsByClassName('btn-delete');
      for(const btn of btnDelete) {
        btn.addEventListener('click', (event) => {
          if(confirm('해당 게시글을 삭제할까요?')) {
            location.href = '${contextPath}/bbs/delete.do?bbsId=' + event.currentTarget.dataset.bbsId;
          }
        })
      }
    }
    
    
    displayMsg();
    displayFormReply();
    deleteBbs();
  </script>
  
  
</body>
</html>