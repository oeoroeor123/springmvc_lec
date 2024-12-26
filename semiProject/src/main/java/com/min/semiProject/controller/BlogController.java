package com.min.semiProject.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.semiProject.service.IBlogService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BlogController {

  private final IBlogService blogService;

  
  @RequestMapping(value="/blog/list.do")
  public String list(HttpServletRequest request, Model model) {
    Map<String, Object> map = blogService.getBlogList(request);
    model.addAttribute("blogList", map.get("blogList"));
    model.addAttribute("total", map.get("total"));
    model.addAttribute("paging", map.get("paging"));
    model.addAttribute("offset", map.get("offset"));
    
    return "blog/list";
  }
}
