<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="검색 결과"/>
</jsp:include>

  <h1>Search Result</h1>

  <table>
    <thead>
      <tr>
        <td>타이틀</td>
        <td>내용</td>
        <td>작성자</td>
        <td>게시일자</td>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="blog" items="${blogList}">
        <tr class="blog" data-blog-id="${blog.blog_id}">
          <td>${blog.blog_title}</td>
          <td>${blog.blog_contents}</td>
          <td>${blog.user_email}</td>
          <td><fmt:formatDate pattern="yyyy-MM-dd" value="${blog.create_at}"/></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>

</body>
</html>