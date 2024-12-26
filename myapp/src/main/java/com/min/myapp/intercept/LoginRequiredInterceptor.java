package com.min.myapp.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인이 안 된 상태에서는<br>
 * 공지사항 작성 화면, 블로그 작성 화면으로 이동할 수 없도록 제어하는 인터셉터
 * @author Administrator
 */
public class LoginRequiredInterceptor implements HandlerInterceptor {

  /**
   * 요청을 처리하는 HttpServletRequest와 <br>
   * 응답을 처리하는 HttpServletResponse를 사용
   * @return 요청을 그대로 진행하는 경우 true, 요청을 취소하는 경우 false
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    // 세션에 loginUser 값이 없으면 로그인 화면으로 이동하는 기능 구현하기
    
    HttpSession session = request.getSession();
    if(session.getAttribute("loginUser") == null) {
      
      // HTML 태그로 응답을 만듭니다.
      response.setContentType("text/html; charset=UTF-8");
      
      // 응답을 위해서 문자 출력 스트림(Writer)을 만듭니다.
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 할까요?')) {");
      out.println("  location.href = '" + request.getContextPath() + "/user/login.form?url=" + request.getRequestURL() + "'");
      out.println("} else {");
      out.println("  history.back()");
      out.println("}");
      out.println("</script>");
      out.close();
      
      // 기존 요청을 처리하지 않습니다.
      return false;
      
    }  // if
    
    // 기존 요청을 그대로 처리합니다.
    return true;
    
  }  // preHandle()
  
}
