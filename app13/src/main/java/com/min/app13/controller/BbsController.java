package com.min.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app13.dto.BbsDto;
import com.min.app13.service.IBbsService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/bbs")
@RequiredArgsConstructor
@Controller
public class BbsController {

  private final IBbsService bbsService;
  
  @RequestMapping(value="/regist.do", method=RequestMethod.POST)
  public String regist(BbsDto bbsDto, RedirectAttributes redirectAttributes) {
   
    // 삽입 서비스로부터 삽입 결과를 받아온 뒤, 해당 정보를 리다이렉트 할 수 있도록 redirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", bbsService.registBbs(bbsDto));
    return "redirect:/bbs/list.do";
  }
  
  @RequestMapping(value="/list.do")
  public String list(HttpServletRequest request, Model model) {
    Map<String, Object> map = bbsService.getBbsList(request);
    model.addAttribute("offset", map.get("offset"));
    model.addAttribute("count", map.get("count"));
    model.addAttribute("bbsList", map.get("bbsList"));
    model.addAttribute("paging", map.get("paging"));
    
    return "bbs/list";
  }
  
  
  
}
