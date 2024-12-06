<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>블로그 목록</title>
<style>
  .blogs:hover {
    cursor: pointer;
    background-color: lightblue;
  }
</style>
</head>
<body>

  <div>
    <button type="button" id="btn-write">작성</button>
  </div>

  <table border="1">
    <thead>
      <tr>
        <td>번호</td>
        <td>제목</td>
        <td>조회수</td>
        <td>작성일자</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="blog" items="${blogList}">
        <tr class="blogs" data-blog-id="${blog.blog_id}">
          <td>${blog.blog_id}</td>
          <td>${blog.title}</td>
          <td>${blog.hit}</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.create_dt}"/></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
  <script>
  
    document.getElementById('btn-write').addEventListener('click', (event) => {
      location.href = '${contextPath}/blog/write.do';
    })
    
    const blogs = document.getElementsByClassName('blogs');
    for(const blog of blogs) {
      blog.addEventListener('click', (event) => {
        location.href = '${contextPath}/blog/increseHit.do?blog_id=' + event.currentTarget.dataset.blogId;  // data- 뒷 부분을 저장할 때 대시(-)는 camel case 로 변환되어 저장됩니다. 
      })
    }
    
    const msg = '${msg}';
    if(msg !== '') {
      alert(msg);
    }
    
  </script>

</body>
</html>