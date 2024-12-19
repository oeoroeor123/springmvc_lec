
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="${blog.title}"/>
</jsp:include>

<style>
  #contents {
    width: 200px;
    min-height: 200px;
  }
</style>

  <h1>Blog Detail</h1>

  <form id="form-detail" method="post">
  
    <input type="hidden" name="blog_id" value="${blog.blog_id}">
  
    <div>작성자 ${blog.user_email}</div>
    <div>작성일시 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.create_dt}"/></div>
    <div>수정일시 <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${blog.modify_dt}"/></div>
  
    <div>
      <label for="title">제목</label>
      <input type="text" name="title" id="title" value="${blog.title}">
    </div>
  
    <div>
      <textarea name="contents" id="contents" placeholder="내용">${blog.contents}</textarea>
    </div>
    
    <div>
      <button type="reset">수정 초기화</button>
      <button type="button" id="btn-modify">수정 완료</button>
      <button type="button" id="btn-remove">블로그 삭제</button>
      <button type="button" id="btn-list">블로그 목록</button>
    </div>
    
  </form>
  
  <script>
  
    function submitForm() {
      const formDetail = document.getElementById('form-detail');
      const title = document.getElementById('title');
      document.getElementById('btn-modify').addEventListener('click', (event) => {
        if(title.value === '') {
          alert('제목은 필수입니다.');
          title.focus();
          return;
        }
        formDetail.action = '${contextPath}/blog/modify.do';
        formDetail.submit();
      })
    }
  
    function deleteBlog() {      
      document.getElementById('btn-remove').addEventListener('click', (event) => {
        if(confirm('현재 블로그를 삭제할까요?')) {        
          formDetail.action = '${contextPath}/blog/remove.do';
          formDetail.submit();
        }
      })
    }
    
    function toBlogList() {
      document.getElementById('btn-list').addEventListener('click', (event) => {
        location.href = '${contextPath}/blog/list.do';
      })
    }
    
    submitForm();
    deleteBlog();
    toBlogList();
    
  </script>

</div>

</body>
</html>
