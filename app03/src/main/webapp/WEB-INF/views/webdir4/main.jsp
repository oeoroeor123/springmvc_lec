<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title>form submit</title>
</head>
<body>

  <%-- 요청만들기 2 : <form> 태그의 submit(GET, POST 방식 중 선택 가능) --%>
  <%-- 
    action : 요청 서버 주소 작성
    method : 요청 메소드 작성
      1) 요청 메소드 작성
      2) get, post 방식을 사용 (디폴트 : GET 방식)
      3) 모든 요청은 Query String 방식의 요청으로 처리됨
      4) get 방식으로 처리하면 요청 주소에 요청 파라미터가 노출됨 (보안에 좋지 않지만 처리 속도가 빠름)
      5) post 방식으로 처리하면 요청 주소에 요청 파라미터가 노출되지 않음 (보안에 좋지만 처리 속도가 느림)  
    <%= %> : 자바 변수를 나타내는 표현법
  --%>
  
    <form action="<%=request.getContextPath()%>/webdir4/req1" method="get">  <!-- request.getContextPath() == contextpath(http://localhost:8080/app03/) -->
      
      <%-- 입력 상자(text, number, email, url, password --%>
      <%-- name : 백단에서 서버 이름을 구분하는 용도로 사용 / id : 프론트 단에서 태그를 구분하는 용도로 사용 (name, id 동일한 이름 사용 가능) --%>
      <input type="text" name="a" id="a"><br/>
      <input type="number" name="b"><br/>
      <input type="email" name="c"><br/>
      <input type="url" name="d"><br/>
      <input type="password" name="e"><br/>
      <input type="date" name="f"><br/>
      
      <%-- 서브밋 : action에 작성된 주소로 모든 입력 요소들을 보냄 --%>
      <button type="submit">서브밋</button>
      
    </form>
    
    
    <hr>
    
    
    <form action="<%=request.getContextPath()%>/webdir4/req2" method="get">
    
    <%-- 모든 선택 상자는 선택했을 경우, 디폴트 value = "on"을 가진다. --%>
    
      <%-- 선택 상자 : 다중 선택이 가능한 checkbox (같은 name으로 보내기) - 디폴트 value = "on" 사용 불가(디폴트 값 따로 지정 필요) --%>
      <input type="checkbox" name="flowers" id="rose" value="rose">
      <lable for="rose">장미</lable>
      <input type="checkbox" name="flowers" id="tulip" value="tulip">
      <lable for="tulip">튤립</lable>
      <br/>
      
      <%-- 선택 상자 : 다중 선택이 가능한 checkbox (다른 name으로 보내기) - 디폴트 value = "on" 사용 가능 --%>
      <input type="checkbox" name="kbs" id="kbs">
      <lable for="kbs">kbs</lable>
      <input type="checkbox" name="mbc" id="mbc">
      <lable for="mbc">mbc</lable>
      <input type="checkbox" name="sbs" id="sbs">
      <lable for="sbs">sbs</lable>
      <br/>
      
      <%-- 선택 상자 : 단일 선택만 가능한 radio - 디폴트 value = "on" 사용 불가 (디폴트 값 따로 지정 필요) --%>
      <input type="radio" name="choice" id="yes" value="y">
      <lable for="yes">yes</lable>
      <input type="radio" name="choice" id="no" value="n">
      <lable for="no">no</lable>
      
      <br/>
      
      <%-- 서브밋 : action에 작성된 주소로 모든 입력 요소들을 보냄 --%>
      <button type="submit">서브밋</button>

    </form>
    
    
    <hr>
    
    
    <form action="<%=request.getContextPath()%>/webdir4/req3" method="get">
    
    <%-- 모든 목록 요소는 <option> 태그의 내부 텍스트(textContent)가 value로 전달된다. --%>
    <%-- <option> 태그에 value 속성을 추가하면 value 속성 값이 전달된다. --%>
    
      <%-- 목록 요소 : <select> 태그와 <option> 태그 --%>
      <select name="city">
        <option>seoul</option>
        <option>jeju</option>      
      </select>
      
      <br/>
      
      <%-- 콤보 요소 : <input> 태그와 <datalist> 태그 --%>
      <input type="text" name="domain" list="domain-list">
        <datalist id="domain-list">
          <option value="naver">naver.com</option> <%-- value를 지정하면 텍스트가 아닌 value 값이 서버측으로 넘어옴 --%>
          <option value="kakao">kakao.com</option>
        </datalist>
        
       <br/>
      
      <%-- 다중 라인 입력 요소 : <textarea> 태그 --%>
      <textarea name="content"></textarea>
      
      <br/>
      
      <%-- 서브밋 : action에 작성된 주소로 모든 입력 요소들을 보냄 --%>
      <button type="submit">서브밋</button>
    
    </form>
    
    
  
  
</body>
</html>