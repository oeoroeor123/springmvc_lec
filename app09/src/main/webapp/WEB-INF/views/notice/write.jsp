<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Notice Write</title>
</head>
<body>

  <h1>Notice Write</h1>

  <div>
    <!-- 파일 첨부 form을 만들기 위해 method / enctype 필수 작성! -->
    <form action="${contextPath}/notice/regist.do" method="post" enctype="multipart/form-data">
    
      <input type="text" name="noticeTitle" placeholder="제목"><br/>
      <textarea rows="5" cols="30" name="noticeContents" placeholder="내용"></textarea><br/>
      <!-- 파일 다중 첨부를 위해 아래와 같이 세팅 (multiple 필수) -->
      <input type="file" name="files" id="files" multiple>
      
      <button type="submit">작성 완료</button>
    </form>
  </div>
  
    <script>
  
  function attachCheck() {
    
    const files = document.getElementById('files');
    
    // 개별 파일의 크기 제한 (10MB)
    const limitPerSize = 1024 * 1024 * 10;
    
    // 전체 파일의 크기 제한 (100MB)
    const limetTotalSize = 1024 * 1024 * 100;
    
    // 전체 파일 크기 저장할 변수
    let totalSize = 0;
    
    // 첨부 이벤트 (files : EventcurrentTarget)
    files.addEventListener('change', (event) => {
      
      for(const file of event.currentTarget.files) { // files 객체(event.currentTarget)의 files 프로퍼티
        
        // 개별 파일 첨부 체크
        if(file.size > limitPerSize) {
          alert('각 첨부 파일의 크기는 최대 10MB 입니다. 첨부 불가!');
          event.currentTarget.value=''; // value를 빈 문자열로 바꾸면 첨부된 파일이 지워진다.
          return;
        }

        // 다중 파일 첨부 체크
        totalSize += file.size; // 개별 파일의 크기를 토털에 누적한다.
        if(tatalSize > limetTotalSize) {
          alert('전체 첨부 파일의 크기는 최대 100MB 입니다. 첨부 불가!');
          event.currentTarget.value='';
          return;
        }
      }
    })
    
  }
  
  attachCheck();
   
  </script>

</body>
</html>