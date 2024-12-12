<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${contextPath}/resources/css/list.css?dt=<%=System.currentTimeMillis()%>"> <!-- resources 폴더의 css 파일 사용하기 -->
<%--
?dt=<%=System.currentTimeMillis()%> : css 파일 내용이 바뀌어도 jsp에선 변화가 없을 수 있으니 변화 코드를 추가로 넣어줘야 함
브라우저의 캐싱 작업을 위해, 코드 작업 중엔 해당 코드를 넣어주는 것이 좋고, 추후 코드 작업이 끝나면 ver1.0 를 넣어주는 것이 일반적임
--%>
<title>Single File Upload</title>
</head>
<body>

  <img src="${contextPath}/resources/animal10.jpg" width="200px">
  
  <hr/>
  
   <div class="form-wrap">
   <h1>File Upload</h1>
    <!-- 파일 첨부를 위한 form 태그는 메소드 속성과 enctype 속성이 아래와 같이 정해져있다. -->
    <form action="${contextPath}/single/upload.do" method="post" enctype="multipart/form-data">
      <input type="text" name="writer" placeholder="작성자"><br/>
      <input type="file" name="file" id="file" accept="image/*"><br/>
      <button type="submit">제출</button>
    </form>
   </div>
 
  <hr>
    
    <%-- 업로드한 파일들 리스트 확인하는 코드 --%>
    <div>
      <h1>Upload File List</h1>
      <c:forEach items="${fileList}" var="f">
      <div class="file">
        <img src="${contextPath}${f.filePath}/${f.filesystemName}" width="50px">
        작성자 : ${f.writer} | 파일 명 : ${f.originalFilename} | 저장된 이름 : ${f.filePath}/${f.filesystemName} <!-- 저장된 이름 : 파일 경로 / 파일 명 -->
      </div>
      </c:forEach>
    </div>
  
   
    <script>
      const msg = '${msg}';
      if(msg !== '')
        alert(msg);
      
      // 첨부 파일 크기 10 MB로 제한하는 코드
      document.getElementById('file').addEventListener('change', (event) => {
        const limit = 1024 * 1024 * 10;
        const size = event.currentTarget.files[0].size;
        if(size > limit) {
          alert('첨부 파일의 크기는 최대 10MB까지만 가능합니다.');
          event.currentTarget.value = '';
        }
      })
    </script>
</body>
</html>