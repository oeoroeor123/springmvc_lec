package com.min.app06.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app06.dto.ContactDto;
import com.min.app06.service.IContactService;

@RequestMapping(value="/contact")
@Controller // 컨트롤러가 사용하는 @Component
public class ContactController {

  @Autowired // DI 필드 주입 (dean 연결)
  private IContactService contactService;
  
  @RequestMapping(value="/list.do")
  public String list(Model model) {
    // 서비스로부터 연락처 목록과 전체 연락처 갯수를 받아온다.
    Map<String, Object> map = contactService.getAllContact();
    // JSP로 전달 (forward)하기 위해서 Model에 저장한다.
    model.addAttribute("contacts", map.get("contacts"));
    model.addAttribute("count", map.get("count"));
   
    // JSP 경로를 작성한다.
    return "contact/list"; // == "/WEB-INF/views/contact/list.jsp"
  }
  
  @RequestMapping(value="/detail.do")
  public String detail(HttpServletRequest request, Model model) {
    // 요청 파라미터 받기 > Integer.parseInt() 처리를 하고 있으므로 요청 파라미터의 null 체크 필요
    Optional<String> opt = Optional.ofNullable(request.getParameter("contact_id"));
    // 요청 파라미터 contact_id가 null일 경우, 디폴트 값 0으로 처리
    int contact_id = Integer.parseInt(opt.orElse("0"));
    // 요청 파라미터를 contact_id로 가진 연락처 정보를 가져오기
    ContactDto contactDto = contactService.getContact(contact_id);
    // JSP로 전달할 연락처 정보를 Model에 저장하기
    model.addAttribute("contact", contactDto);
    // JSP로 전달하기
    return "/contact/detail";
  }
  
  @RequestMapping(value="/modify.do", method=RequestMethod.POST)
  public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    // 요청을 그대로 서비스로 전달하고 수정 성공/실패 메시지를 받아온다.
    String modifyMsg = contactService.modify(request);
    // 수정 성공/실패 메시지를 RedirectAttributes에 저장한다. (리다이렉트시, Model에 저장하면 전달되지 않음)
    redirectAttributes.addFlashAttribute("msg", modifyMsg);
    // 연락처 목록 보기로 리다이렉트 한다. (삽입/수정/삭제 이후에는 반드시 리다이렉트 한다.)
    return "redirect:/contact/list.do";
  }
  
  @RequestMapping(value="/remove.do", method=RequestMethod.POST)
  public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    // 요청을 그대로 서비스로 전달하고 삭제 성공/실패 메시지를 받아온다.
    String removeMsg = contactService.remove(request);
    // 삭제 성공/실패 메시지를 RedirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", removeMsg);
    // 연락처 목록 보기로 리다이렉트한다.
    return "redirect:/contact/list.do";
  }
  
  @RequestMapping(value="/write.do")
  public void write() {
    
  }
  
  @RequestMapping(value="/register.do", method=RequestMethod.POST)
  public String register(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    // 요청을 등록 서비스에 전달한 뒤 등록 성공/실패 메시지를 받아와서 RedirectAttributes에 저장한다.
    redirectAttributes.addFlashAttribute("msg", contactService.register(request));
    // 목록 보기로 리다이렉트한다.
    return "redirect:/contact/list.do";
  }

}
