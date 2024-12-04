package com.min.app05.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.app05.vo.UserVo;

// @Controller
public class MvcController1 {

  @RequestMapping(value="/") // contextPath
  public String main() {
    return "main"; // views 바로 밑에 파일이 있으니 main만 적어줌
  }
  
  @RequestMapping(value="/user/login.do", method=RequestMethod.POST)
  public String login(HttpServletRequest request) { // 모든 요청 정보 (요청 파라미터, 세션) 처리하기
    
    // 요청 파라미터 받기
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    
    // 세션 꺼내기 (세션 : 서버 측에 존재하는 저장소)
    HttpSession session = request.getSession();

    // 세션 아이디 확인하기 (브라우저 쿠키 : F12 - Application - cookies 에서 JSESSIONID 쿠키로 사용자에게 전달되어 저장된다.)
    // System.out.println(session.getId());
    
    // 세션 유효시간 설정하기 (초 단위로 설정, 시간이 지나면 새로운 세션을 발급 받는다.)
    // 세션의 기본 유효시간은 1800초 입니다.(== 30분)
    // session.setMaxInactiveInterval(5); >> 5초 후에 소멸되는 세션

    // id와 pw를 이용해서 UserVo 인스턴스 생성하기
    UserVo userVo = new UserVo();
    userVo.setId(id);
    userVo.setPw(pw);
    
    // 로그인 처리 시나리오
    // id와 pw가 동일하면 로그인 시켜주기
    // 로그인 할 때, 사용자 정보가 담긴 userVo 인스턴스를 세션에 저장한다.
    if(id.equals(pw)) {
      // 세션에 저장할때도 속성(Attribute)으로 저장한다.
      session.setAttribute("userVo", userVo);
    } 
    
    // 로그인이 완료된 후 다시 main.jsp로 이동
    return "redirect:/"; 
  }
  
  
  // 로그아웃 처리
  @RequestMapping(value="/user/logout.do")
  public String logout(
      HttpSession session  // 스프링에서는 세션이 필요하면 매개변수로 선언하고 사용하면 된다.
  ) {
    
    // 방법1. 세션에 저장된 userVo 속성 제거하기
    session.removeAttribute("userVo");

    // 방법2. 세션을 초기화하기 (이 방법을 추천합니다.)
    session.invalidate();
    
    // welcome 화면으로 이동하기
    return "redirect:/";
    
  }
  
  
  
  
}
