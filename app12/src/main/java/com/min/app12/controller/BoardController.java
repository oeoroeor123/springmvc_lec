package com.min.app12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

  @RequestMapping(value="/board/write.do")
  public String write(HttpServletRequest request) {

    return "board/write";
  }
}
