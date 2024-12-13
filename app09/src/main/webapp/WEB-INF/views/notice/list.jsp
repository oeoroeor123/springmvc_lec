<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notice List</title>
</head>
<body>

  <h1>Notice List</h1>

  <div>
    <a href="${contextPath}/notice/write.do">새 공지사항 작성하기</a>
  </div>
  
  <div>
    <!-- 목록 보여주기 : 컨트롤러에서 model로 저장한 noticeList 값 지정하기 -->
    <c:forEach items="${noticeList}" var="n">
      <div class="notices" data-notice-id="${n.noticeId}">
        공지번호 : ${n.noticeId} | 공지 제목 : ${n.noticeTitle}(${n.attachCount}) | 공지 일자 : <fmt:formatDate value="${n.createdAt}" pattern="yyyy.MM.dd HH:mm:ss"/>
      </div>
    </c:forEach>
  </div>
  
  <script>
      
    function detailHandle() {
      const notices = document.getElementsByClassName('notices');
      for(const notice of notices) {
        notice.addEventListener('click', (event) => {
          location.href = '${contextPath}/notice/detail.do?noticeId=' + event.currentTarget.dataset.noticeId;          
        })
      }
    }
    
    function msgHandle() {
      const msg = '${msg}';
      if(msg !== '')
        alert(msg);
    }
    
    detailHandle();
    msgHandle();
  
  </script>
  
</body>
</html>