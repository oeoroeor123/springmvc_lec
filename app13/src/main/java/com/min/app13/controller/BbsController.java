package com.min.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app13.dto.BbsDto;
import com.min.app13.service.IBbsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BbsController {

  private final IBbsService bbsService;
  
  @RequestMapping(value="/bbs/registBbs.do", method=RequestMethod.POST)
  public String registBbs(BbsDto bbsDto, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", bbsService.registBbs(bbsDto));
    return "redirect:/bbs/list.do";
  }
  
  @RequestMapping(value="/bbs/list.do")
  public String list(HttpServletRequest request, Model model) {
    Map<String, Object> map = bbsService.getBbsList(request);
    model.addAttribute("offset", map.get("offset"));
    model.addAttribute("count", map.get("count"));
    model.addAttribute("bbsList", map.get("bbsList"));
    model.addAttribute("paging", map.get("paging"));
    return "bbs/list";
  }
  
  
  @RequestMapping(value="/bbs/registBbsReply.do", method=RequestMethod.POST)
  public String registBbsReply(BbsDto bbsDto, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", bbsService.registBbsReply(bbsDto));
    return "redirect:/bbs/list.do";
  }
  
  @RequestMapping(value="/bbs/delete.do")
  public String delete(@RequestParam(name="bbsId", required=false, defaultValue="0") int bbsId, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", bbsService.deleteBbs(bbsId));
    return "redirect:/bbs/list.do";  
  }
  
 
}
