package com.min.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.myapp.dto.BlogDto;
import com.min.myapp.service.IBlogService;

@RequestMapping("/blog")
@Controller
public class BlogController {

  private IBlogService blogService;
  
  @Autowired  // Setter 형식의 메소드를 이용한 DI 방식입니다. 매개변수로 bean이 주입되면 필드로 전달됩니다.
  public void prepare(IBlogService blogService) {
    this.blogService = blogService;
  }
  
  @RequestMapping(value="/list.do")
  public String list(HttpServletRequest request, Model model) {
    // 블로그 목록(blogList)과 전체 블로그 개수(count)를 list.jsp로 전달합니다.
    Map<String, Object> map = blogService.getBlogList(request);
    model.addAttribute("blogList", map.get("blogList"));
    model.addAttribute("count", map.get("count"));
    model.addAttribute("blogs", map.get("blogs"));
    model.addAttribute("total", map.get("total"));
    model.addAttribute("paging", map.get("paging"));
    model.addAttribute("offset", map.get("offset"));
    return "blog/list";
  }
  
  @RequestMapping(value="/increseHit.do")
  public String increseHit(
      @RequestParam(value="blog_id", required=false, defaultValue="0") int blog_id) {
    // 조회수 증가가 성공하면 /detail.do 요청하고, 실패하면 /list.do 요청합니다.
    String path = blogService.increseBlogHit(blog_id) == 1 ? "/detail.do?blog_id=" + blog_id : "/list.do";
    return "redirect:/blog" + path;
  }
  
  @RequestMapping(value="/detail.do")
  public String detail(
      @RequestParam(value="blog_id") int blog_id
    , Model model) {
    // 블로그 상세 내용(blog)을 detail.jsp에 전달합니다.
    model.addAttribute("blog", blogService.getBlogById(blog_id));
    return "blog/detail";
  }
  
  @RequestMapping(value="/modify.do", method=RequestMethod.POST)
  public String modify(
      BlogDto blogDto
    , RedirectAttributes redirectAttributes) {
    // 수정 뒤 결과 메시지(msg)를 /list.do 요청하면서 전달합니다.
    redirectAttributes.addFlashAttribute("msg", blogService.modifyBlog(blogDto));
    return "redirect:/blog/list.do";
  }
  
  @RequestMapping(value="/remove.do", method=RequestMethod.POST)
  public String remove(
      @RequestParam(value="blog_id") int blog_id
    , RedirectAttributes redirectAttributes) {
    // 삭제 뒤 결과 메시지(msg)를 /list.do 요청하면서 전달합니다.
    redirectAttributes.addFlashAttribute("msg", blogService.removeBlog(blog_id));
    return "redirect:/blog/list.do";
  }
  
  @RequestMapping(value="/write.do")
  public String write() {
    return "blog/write";
  }
  
  @RequestMapping(value="/register.do", method=RequestMethod.POST)
  public String register(
      BlogDto blogDto
    , RedirectAttributes redirectAttributes) {
    // 삽입 뒤 결과 메시지(msg)를 /list.do 요청하면서 전달합니다.
    redirectAttributes.addFlashAttribute("msg", blogService.registerBlog(blogDto));
    return "redirect:/blog/list.do";
  } 

  
}
