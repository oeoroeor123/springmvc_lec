package com.min.app12.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 인터셉터
 * 1. Controller로 이동하는 요청을 가로챈다. 
 * 2. Controller로 이동하는 요청을 그대로 유지하거나 취소할 수 있다.
 * 3. 생성 방법
 *  1) HandlerInterceptor 인터페이스를 구현한다. (추천 방법)
 *  2) HandlerInterceptorAdaptor 클래스를 상속한다.
 * 4. 구현할 수 있는 추상 메소드
 *  1) preHandle()       : 요청 이전에 동작할 코드를 작성한다. 이 메소드를 이용해 요청을 취소할 수 있다.
 *  2) postHandel()      : 요청 이후에 동작할 코드를 작성한다.
 *  3) afterCompletion() : View 처리가 끝난 이후에 동작할 코드를 작성한다.  
 */

/*
 * 인터셉터 동작 순서
 * 
 * View   -  Filter  -   DispatcherServlet   - Interceptor - @Controller - @service - @repository(Dao)
 *          (web.xml)   (servlet-context.xml)
 *                      언제 어떤 인터셉터가    어떤 일을
 *                      동작하는가?             수행하는가?
 */

public class LoginConfirmInterceptor implements HandlerInterceptor {
  
  /**
   * 요청을 처리하는 HttpServletRequest와
   * 응답을 처리하는 HttpServletResponse를 사용
   * @return 요청을 그대로 진행하는 경우 true, 요청을 취소하는 경우 false를 반환 (boolean 타입)
   */
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    // 세션에 loginUser 값이 없으면 로그인 화면으로 이동하는 기능 구현하기
    HttpSession session = request.getSession();
    if(session.getAttribute("loginUser") == null) { // loginUser가 null이면, (로그인이 안되었다면)
      
      // HTML 태그로 응답을 만든다.
      response.setContentType("text/html; charset=UTF-8");
      
      // 응답을 위해서 문자 출력 스트림(Writer)을 만든다.
      PrintWriter out = response.getWriter();
      out.println("<script>"); // 줄 바꿈
      out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 할까요 ?')) {");
      out.println("location.href = '" + request.getContextPath() + "/user/login.form?url="+ request.getRequestURL() +"'"); // 이동할 위치
      out.println("} else {");
      out.println("history.back()"); // 전 페이지로 이동
      out.println("}");
      out.println("</script>");
      out.close();
      
      // 기존 요청을 처리하지 않는다.
      return false;
    } // if 끝
    
    // 기존 요청을 처리한다.
    return true;
    
  } // preHandle() 끝
}
