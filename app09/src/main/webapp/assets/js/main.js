/**
 * 파일 명 : main.js
 * 작성자 : 이수정
 */
 
function getContextPath() {
  const url = location.href;                      /* http://localhost:8080/app09/main.do */
  const host = location.host;                     /* localhost:8080 */
  const begin = url.indexOf(host) + host.length;  /*  7 + 14 = 21 : ContextPath의 시작 인덱스 */
  const end = url.indexOf('/', begin + 1);        /* end (27) : Mapping(/main.do)의 시작 인덱스 contextPath를 건너 뛴 다음부터라 + 1을 함 */
  const contextPath = url.substring(begin, end);  /* 인덱스 begin 포함 부터 end 불포함까지 */
  return contextPath;
 }
 
  
  function toMain() {
  const logo = document.getElementById('logo');
  logo.addEventListener('click', (event) => {
    location.href=getContextPath() + '/main.do'
  })
}

/* onload : script를 위에서 사용하기 위해 쓰는 load Event 코드 */
onload = () => {
toMain();
}