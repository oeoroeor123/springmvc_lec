<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<jsp:include page="../layout/header.jsp">
   <jsp:param name="title" value="블로그"/>
</jsp:include>

<style>
  .wrap {
    width: 1060px;
    margin: 40 auto;
  }
  
  h1 {
    text-align: center;
  }
  
  .wrap table {
    border: 1px solid black;
    border-collapse: collapse;
    width: 100%;
  }
  
  .wrap thead td {
    text-align: center;
    font-weight: 800;
  }
  
  .wrap td {
    border: 2px solid black;
  }
  
  .number {
    border: 2px solid green;
  }
  
  .wrap td: nth-of-type(1) {width: 60px; }
  .wrap td: nth-of-type(2) {width: 60px; }
  .wrap td: nth-of-type(3) {width: 200px; }
  .wrap td: nth-of-type(4) {width: 60px; }
  .wrap td: nth-of-type(5) {width: 400px; }
  .wrap td: nth-of-type(6) {width: 100px; }
  .wrap td: nth-of-type(1) {width: 100px; }
</style>

<h1>Blog List</h1>

<div>
  <button type="button" id="btn-write">새 블로그 작성하기</button>
</div>

<div class="wrap">
  <div style="text-align: right;">
    <a href="${contextPath}/blog/list.do?page=1&sort=DESC">최신순</a> | 
    <a href="${contextPath}/blog/list.do?page=1&sort=ASC">과거순</a>
</div>


<table>
  <caption class="number">총 ${total}개 블로그</caption>

  <thead>
    <tr>
      <td>순번</td>
      <td>userId</td>
      <td>title</td>
      <td>hit</td>
      <td>contents</td>
      <td>createDt</td>
      <td>modifyDt</td>
   </tr>
   </thead>
   
   <tbody>
      <c:forEach items="${blogList}" var="blog" varStatus="vs">
      <tr class="blogs" data-user-id="${blog.user_id}" data-blog-id="${blog.blog_id}">
      <td>${offset + k.count}</td> <!-- count : index + 1 -->
      <td>${blog.userId}</td>
      <td>${blog.title}</td>
      <td>${blog.hit}</td>
      <td>${blog.contents}</td>
      <td><fmt:formatDate pattern="yyyy-MM-dd" value=" ${blog.createDt}"/></td>
      <td><fmt:formatDate pattern="yyyy-MM-dd" value=" ${blog.modifyDt}"/></td>
      </tr>
      </c:forEach>
   </tbody>
    
   <tfoot>
    <tr>
      <td colspan="7">${paging}</td>
      </tr>
   </tfoot>
</table>
</div>


</body>
</html>