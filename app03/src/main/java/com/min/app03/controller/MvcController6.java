package com.min.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController6 {

  @RequestMapping(value="webdir6/main")
  public void main() {
    
  }
  
  // 요청 URL : /webdir6/123
  @RequestMapping(value="/webdir6/{number}") // 경로에 포함된 변수를 number로 지칭한다.
  public String req1(@PathVariable(value="number") int number) { // 경로 변수 number를 int number에 저장한다.
    System.out.println(number);
    return "webdir6/main";
  }
  
  // 요청 URL : /webdir6/sort/ASC/page/1
  @RequestMapping(value="/webdir6/sort/{sort}/page/{page}")
  public String req2(@PathVariable(value="sort") String sort, @PathVariable(value="page") int page) {
    System.out.println(sort);
    System.out.println(page);
    return "webdir6/main";
  }
  
  
  
}
