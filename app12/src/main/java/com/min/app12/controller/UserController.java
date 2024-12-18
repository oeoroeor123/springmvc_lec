package com.min.app12.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app12.dto.UserDto;
import com.min.app12.service.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
  
  private final IUserService userService;
  
  @RequestMapping(value="/user/signup.form")
  public String signupForm() {
    return "user/signup";
  }
  
  @RequestMapping(value="/user/signup.do", method=RequestMethod.POST)
  public String signup(UserDto userDto, RedirectAttributes redirectAttributes) { // 커맨드 객체 이용하여 요소 받아오기
    redirectAttributes.addFlashAttribute("msg", userService.signup(userDto));
    return "redirect:/main.do"; // 완료 후 welcome 페이지로 넘어감
  }
  
  @RequestMapping(value="/user/login.form")
  public String loginForm(@RequestParam(name="url", required=false, defaultValue = "http://localhost:8080.app12/" )String url, Model model) {
    model.addAttribute("url", url);
    return "user/login";
  }
  
  @RequestMapping(value="/user/login.do", method=RequestMethod.POST)
  public String login(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    
   boolean loginSuccess = userService.login(request);
   
   // 동일한 메소드 호출 방지용으로 변수 처리
   String url = request.getParameter("url");
   
   if(!loginSuccess) { // 로그인 실패 시
     redirectAttributes.addFlashAttribute("msg", "일치하는 회원 정보가 없습니다.");
     return "redirect:/user/login.form?url=" + url; // 로그인 화면으로 redirect하여 되돌아 감
   }
   return "redirect:" + url; // 로그인 성공 시, 지정해둔 url로 돌아간다.
  }
  
  @RequestMapping(value="/user/logout.do")
  public String logout(HttpSession session) {
    userService.logout(session);
    return "redirect:/"; // main으로 이동
  }
}
