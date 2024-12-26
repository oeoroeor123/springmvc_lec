<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="${n.noticeTitle}"/>
</jsp:include>

  <h1>Notice Detail</h1>

  <c:if test="${empty n.userDto.userEmail}">
    <div>작성자 정보 없음</div>
  </c:if>
  <c:if test="${not empty n.userDto.userEmail}">
    <div>작성자 ${n.userDto.userName}(${n.userDto.userEmail})</div>
  </c:if>
  <div>작성일시 <fmt:formatDate value="${n.createdAt}" pattern="yyyy-MM-dd a hh:mm:ss"/></div>

  <div style="background-color: beige;">
    <h4>첨부 파일</h4>
    <c:forEach items="${attachList}" var="a">
      <div>
        <a href="${contextPath}/notice/download.do?attachId=${a.attachId}" class="download-link">${a.originalFilename}</a>
        <span>Download Count(${a.downloadCount})</span>
      </div>
    </c:forEach>
  </div>

  <h1>${n.noticeTitle}</h1>
  <pre>${n.noticeContents}</pre>
  
  <div><a href="${contextPath}/notice/remove.do?noticeId=${n.noticeId}">삭제</a></div>

  <script>
  
    function confirmDownload() {
      const downloadLink = document.getElementsByClassName('download-link');
      for(const link of downloadLink) {
        link.addEventListener('click', (event) => {
          if(!confirm('해당 첨부 파일을 다운로드 할까요?')) {
            event.preventDefault();  // <a> 태그의 href 이동을 막는 코드
            return;
          }
        })
      }
    }
    
    confirmDownload();
  
  </script>

</div>

</body>
</html>