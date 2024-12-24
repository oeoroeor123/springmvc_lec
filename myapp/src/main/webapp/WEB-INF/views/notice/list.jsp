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
    <a href="${contextPath}/notice/search.form">검색</a>
  </div>
  
  <div>
    <select id="sort-column">
      <option value="notice_id">작성일자</option>
      <option value="notice_title">제목</option>
    </select>
    <button type="button" class="btn-sort" data-sort="ASC">오름차순</button>
    <button type="button" class="btn-sort" data-sort="DESC">내림차순</button>
  </div>
  
  <div>
    <div>전체 공지 ${total}개</div>
    <c:forEach items="${noticeList}" var="n" varStatus="vs">
    <div class="notices" data-notice-id="${n.noticeId}">
      ${offset + vs.count} | ${n.noticeTitle} (${n.attachCount})... <fmt:formatDate value="${n.createdAt}" pattern="yyyy.MM.dd HH:mm:ss"/>
    </div>
  </c:forEach>
  <div>${paging}</div>
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

    function sortList() {
      const btnSort = document.getElementByClassName('btn-sort');
      for(const btn of btnSort) {
        btn.addEventListener('click', (event) => {
          location.href  = '${contextPath}/notice/list.do?page=1&sort=' + event.target.dataset.sort + '&column=' + document.getElementById('sort-column').value;
        })
      }
    }
    
    function selectOption() {
      
    }
    
    
    detailHandle();
    sortList();
  
  </script>

</div>

</body>
</html>