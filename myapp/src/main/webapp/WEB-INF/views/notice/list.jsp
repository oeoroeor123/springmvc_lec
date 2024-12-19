<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="공지사항"/>
</jsp:include>

  <h1>Notice List</h1>
  
  <div>
    <a href="${contextPath}/notice/write.do">새 공지사항 작성하기</a>
  </div>

  <div>
    <c:forEach items="${noticeList}" var="n">
      <div class="notices" data-notice-id="${n.noticeId}">
        공지번호 ${n.noticeId} | ${n.noticeTitle}(${n.attachCount}) ... <fmt:formatDate value="${n.createdAt}" pattern="yyyy.MM.dd HH:mm:ss"/>
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

    detailHandle();
  
  </script>

</div>

</body>
</html>