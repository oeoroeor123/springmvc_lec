/**
 * 파일명 : main.js
 * 작성자 : 홍길동
 */

function getContextPath() {
  const url = location.href;                     /* http://localhost:8080/app09/main.do */
  const host = location.host;                    /* localhost:8080 */
  const begin = url.indexOf(host) + host.length; /* 7 + 14 = 21 : ContextPath의 시작 인덱스 */
  const end = url.indexOf('/', begin + 1);       /* 27          : Mapping의 시작 인덱스 */
  const contextPath = url.substring(begin, end); /* 인덱스 begin부터 인덱스 end 이전까지 */
  return contextPath;
}

function toMain() {
  const logo = document.getElementById('logo');
  logo.addEventListener('click', (event) => {
    location.href = getContextPath() + '/main.do';
  })
}

onload = () => {
  toMain();
}