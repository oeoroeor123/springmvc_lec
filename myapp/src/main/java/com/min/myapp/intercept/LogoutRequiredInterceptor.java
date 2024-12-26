package com.min.myapp.intercept;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인이 된 상태에서는<br>
 * 로그인 화면, 회원가입 화면으로 이동할 수 없도록 제어하는 인터셉터
 * @author Administrator
 */
public class LogoutRequiredInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    
    HttpSession session = request.getSession();
    if(session.getAttribute("loginUser") != null) {
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      out.println("location.href = '" + request.getContextPath() + "'");
      out.println("</script>");
      out.close();
      return false;
    }
    return true;
    
  }
  
}
