<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta main="viewport" content="width=device-width, initial-scale=1.0">
<title>Location</title>
    <script>
    window.onload = () => { // window 객체는 항상 생략 가능하다. onload = () => {} 로도 작성 가능
      const btn1 = document.getElementById("btn1");
      btn1.addEventListener('click', (event) => {
        
        // location 객체의 href 속성을 이용해서 요청할 수 있음
        
        // Java 변수는 JavaScript 내부에서 사용 가능(하지만, 반대는 불가)
        const contextPath = '<%=request.getContextPath()%>';
        
        // JSP에서는 템플릿 문자열(Template Strings)을 사용할 때 \${}을 사용하고, ``으로 묶는다.
        location.href = `\${contextPath}/webdir5/req1?param=\${event.currentTarget.textContent}`; // 클릭한 요소(event.currentTarget)의 내부 텍스트(textContent)    
      });
      
      
      const btn2 = document.getElementById("btn2");
      btn2.addEventListener('click', (event) => {
        
        // data- 속성(Attribute)의 값은 dataset 속성(Property)에 저장된다.
        const contextPath = '<%=request.getContextPath()%>';
        location.href = `\${contextPath}/webdir5/req2?param=\${event.currentTarget.dataset.id}`; // 요청 시 지정한 data-id 값 불러오기
      });
      
      const btnDo = document.getElementsByClassName("btn-do");
      for(const btn of btnDo) { // of : btnDo에서 값을 하나씩 빼서 btn에 넘겨줌
        btn.addEventListener('click', (event) => {
          
          // Node    : Element, Text 등을 지칭하는 상위 개념의 DOM 객체
          // Element : 한 마디로 태그(HTMLElement)를 의미하는 Node의 하위 개념 DOM 객체
          
          // 형제 관계 (Sibling) : 같은 레벨의 요소(Element)
          // 이전 형제 요소 : previousElementSibling / 다음 형제 요소 : nextElementSibling
          
          // value 속성(Attribute)은 value 속성(Property)과 같다.
          const contextPath='<%=request.getContextPath()%>';
          console.log(event.currentTarget.nextElementSibling);
          location.href = `\${contextPath}/webdir5/req3?param=\${event.currentTarget.nextElementSibling.value}`; // ==  <input type="hidden" value="1">의 value 값
          
        });
      }
      
      
      const btns = document.getElementsByClassName("btns");
      for(const btn of btns) {
        btn.addEventListener('click', (event) => {
          
          // 부모 노드(Node)    : parentNode
          // 부모 요소(Element) : parentElement
          
          const contextPath = '<%=request.getContextPath()%>';
          location.href = `\${contextPath}/webdir5/req4?param=\${event.currentTarget.parentElement.dataset.no}`;          
        });
      }
      
    } // end onload
    
      function fnMove(anchor) { // anchor : 클릭한 <a> 태그 요소
       alert('이동합니다.');
       location.href= anchor.dataset.url;
    }
      
    </script>
</head>
<body>

  <%-- 요청 만들기 3 : JavaScript의 Location 객체 활용하기 (<a> 태그와 동일한 요청) --%>
    
    <%-- 내가 가진 값 불러오기 --%>
    <button type="button" id="btn1">요청1</button>
    <br/>
    
    <button type="button" id="btn2" data-id="1">요청2</button>
    <br/>
    
    
    <%-- 형제 관계 값 불러오기 --%>
    <button type="button" class="btn-do">요청Do1</button> <%-- nextSibling : 줄 바꿈 --%>
    <input type="hidden" value="1"><br/>
    <button type="button" class="btn-do">요청Do2</button>
    <input type="hidden" value="2"><br/>
     
     
    <%-- 부모 관계 값 불러오기 --%>
    <div data-no="1">
      <button type="button" class="btns">요청하기1</button>
    </div>
    <div data-no="2">
      <button type="button" class="btns">요청하기2</button>
    </div>
    <br/>
    
    
    <%-- <a>태그를 클릭하면 javascript의 fnMove() 함수가 호출된다. --%>
    <%-- this : 클릭한 요소를 의미 (이벤트 타겟), 어떤 <a> 태그를 클릭했는지 함수에 인자로 전달한다. --%>
    <a onclick="fnMove(this)" data-url ="https://www.naver.com">네이버</a><br/>
    <a onclick="fnMove(this)" data-url ="https://www.kakao.com">카카오</a><br/>
    
    
</body>
</html>