<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>

<jsp:include page="../layout/header.jsp">
  <jsp:param name="title" value="마이페이지"/>
</jsp:include>

<style>
  .sub-title {
    margin-top: 50px;
  }
  .sub-title::before {
    content: '*';
  }
</style>
<script>
  function toRepw() {
    location.href = '${contextPath}/user/repw.form';
  }
  function deleteAccount() {
    if(confirm('모든 회원 정보가 삭제됩니다. 탈퇴하시겠습니까?')) {
      location.href = '${contextPath}/user/deleteAccount.do';
    }
  }
</script>


  <h1>My Page</h1>
  
  <h3 class="sub-title">개인정보변경</h3>
  <form action="${contextPath}/user/modifyInfo.do" method="post">
    <input type="hidden" name="userId" value="${u.userId}">
    <div>
      <label for="userEmail">이메일</label>
      <input type="text" name="userEmail" id="userEmail" value="${u.userEmail}">
    </div>
    <div>
      <label for="userName">성명</label>
      <input type="text" name="userName" id="userName" value="${u.userName}">
    </div>
    <div>
      <button type="submit">개인정보변경하기</button>
    </div>
  </form>
  
  <h3 class="sub-title">프로필이미지변경</h3>
  <form action="${contextPath}/user/modifyProfile.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="userId" value="${u.userId}">
    <div>
      <c:if test="${empty u.profileImg}"><img src="${contextPath}/assets/images/avatar.png" width="80px"></c:if>
      <c:if test="${not empty u.profileImg}"><img src="${contextPath}${u.profileImg}" width="80px"></c:if>  <%-- 이 경로를 해석하려면 servlet-context.xml 파일에 정적 파일의 경로 인식을 추가해 줘야 합니다. --%>
    </div>
    <div>
      <label for="profile">신규 프로필 선택</label>
      <input type="file" name="profile" id="profile">
    </div>
    <div>
      <button type="submit">프로필 변경하기</button>
    </div>
  </form>
  
  <h3 class="sub-title">비밀번호변경</h3>
  <div><button type="button" onclick="toRepw()">비밀번호변경페이지로이동하기</button></div>

  <h3 class="sub-title">회원탈퇴</h3>
  <div><button type="button" onclick="deleteAccount()">회원탈퇴하기</button></div>

</div>

</body>
</html>