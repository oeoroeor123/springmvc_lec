package com.min.semiProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {

  @RequestMapping(value={"/", "/main.do"})
  public String main() {
    return "main";
  }
  
}
