package com.min.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// "/standard" 로 시작하는 모든 요청 주소는 이 컨트롤러가 처리한다.
@RequestMapping(value="/standard")

@Controller
public class MvcController2 {

  @RequestMapping(value="/update") // 컨트롤러에 주소 @RequestMapping(value="/standard")가 있으면 먼저 작성한 뒤, 메소드 주소를 함께 작성한다.
  public String method1() {
    return "webdir2/update";
  }
  
}
