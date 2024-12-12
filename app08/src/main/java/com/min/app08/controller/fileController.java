package com.min.app08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app08.service.IFileService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class fileController {
  
  //service에서 만든 bean을 controller에서 사용한다.
  private final IFileService fileService;

  // list.jsp에 전달할 정보 2가지 (fileList, msg)
  // main.jsp에서 지정한 단일 파일 업로드 연결 경로
  // 포워드 : fileList 전달용 (select 문으로 포워드 사용)
  @RequestMapping(value="/single/list.do")
  public String list(Model model) {
    model.addAttribute("fileList", fileService.getFileList());
    return "list";
  }
  
  // list.jsp에서 지정한 파일 첨부를 위한 form 태그의 url 경로
  // 리다이렉트 : 데이터를 삭제/수정/추가 할 때 사용 (insert문으로 리다이렉트 사용)
  @RequestMapping(value="/single/upload.do", method=RequestMethod.POST)
  // 파일 첨부용 request : MultipartHttpServletRequest
  public String upload(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", fileService.uploadFile(multipartRequest));
    return "redirect:/single/list.do";
  }
  
  
  
  
  
}
