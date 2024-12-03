package com.min.app05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.min.app05.vo.UserVo;

@SessionAttributes(names={"loginUser"}) // Model에 loginUser 속성이 저장되면 HttpSession에도 loginUser 속성으로 함께 저장해라.
                                        // 로그아웃 처리 시, (== 세션 정보 날릴때)
                                        // SessionAttributes Annotation으로 저장된 속성은 SessionStatus 인터페이스의 setComplete() 메소드를 호출하여 삭제할 수 있다.
                                        // HttpSession 인터페이스의 invalidate() 메소드는 동작하지 않는다.

@Controller
public class MvcController2 {

  @RequestMapping(value="/")
  public String main() {
    return "main2";
  }
  
  @RequestMapping(value="/user/login.do", method=RequestMethod.POST)
  public String login(
      
      Model model, // 로그인이 성공하면 Model에 loginUser 속성을 저장하기 위해 사용
      
      @ModelAttribute(name="user") // Model에 저장할 속성 이름을 user로 바꾸기 위해 사용
      UserVo user // 커맨드 객체는 자동으로 Model에 속성으로 저장된다.
                  // 클래스명을 CamelCase로 바꾼 이름으로 저장된다. (첫 글자만 대문자에서 소문자로 바꾸고, 나머지는 그대로)
                  // model.addAttribute("userVo",user);
     
      ) {
    
    // 로그인 처리하기 (아이디와 비밀번호가 동일하면 로그인 성공으로 처리)
    if(user.getId().equals(user.getPw())) {
      model.addAttribute("loginUser", user);
      
    }
    
    return "redirect:/";
  }
  
  @RequestMapping(value="/user/logout.do")
  public String loguout(
        
      SessionStatus sessionStatus // @SessionAttribute로 저장한 속성을 삭제하기 위해 사용      
      ) {
    
      sessionStatus.setComplete(); // 세션을 완료 처리 한다. (== 세션 정보를 지운다.)
    
    return "redirect:/";
  }

}
