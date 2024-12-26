<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="공지사항"/>
</jsp:include>

  <h1>Search</h1>

  <div>
    <form action="${contextPath}/notice/search.do">
      <div><input type="text" name="noticeTitle" placeholder="제목검색"></div>
      <div><input type="text" name="noticeContents" placeholder="내용검색"></div>
      <div><input type="date" name="beginDt"> - <input type="date" name="endDt"></div>
      <div><button type="submit">검색</button></div>
    </form>
  </div>
  
  <div>
    <c:if test="${empty searchList}">
      <div>검색 결과 없음</div>
    </c:if>
    <c:if test="${not empty searchList}">    
      <div>검색 결과 ${searchCount}개</div>
      <c:forEach items="${searchList}" var="n" varStatus="vs">
        <div class="notices" data-notice-id="${n.noticeId}">
          ${offset + vs.count} | ${n.noticeTitle}(${n.attachCount}) ... <fmt:formatDate value="${n.createdAt}" pattern="yyyy.MM.dd HH:mm:ss"/>
        </div>
      </c:forEach>
      <div>${paging}</div>
    </c:if>
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