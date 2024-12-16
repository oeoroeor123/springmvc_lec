package com.min.app10.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.app10.service.IUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
  
  private final IUserService userService;  
  
  @RequestMapping(value="/user/list.do")
  public String list(HttpServletRequest request, Model model) { // HttpServletRequest : 파라미터 처리를 위한 요청 처리용
    
    Map<String, Object> map = userService.getUserList(request);
    model.addAttribute("users", map.get("users"));
    model.addAttribute("total", map.get("total"));
    model.addAttribute("paging", map.get("paging"));
    model.addAttribute("offset", map.get("offset"));
    return "user/list";
  }
  
}
