package com.min.app04.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.app04.vo.Person;

@Controller
public class MvcController2 {
  
  private List<Person> people = new ArrayList<Person>();

  @RequestMapping(value="/redirect/main.do")
  public String main() {
    return "webdir2/main";
  }
  // 데이터를 삭제/수정/추가 할 때 사용 > redirect
  @RequestMapping(value="/redirect/insert.do", method=RequestMethod.POST)
  public String insert(
      Person person // 사용자가 입력한 정보가 커맨드 객체 Person person에 저장됨
    , Model model   // 모델에 저장된 속성(Attribute)은 forward할 때만 전달됨 (redirect 시에는 전달되지 않음)
    , RedirectAttributes redirectAttributes  // redirect용 Model (redirect 시 데이터 전달이 필요할 때 사용)
                                             // RedirectAttributes에 저장된 플래시 속성(Flash Attribute)은 redirect 할 때 전달됨 
  ) {
    
   // 사용자가 입력한 정보를 List에 저장함
   boolean result = people.add(person);
   
   // 입력 성공 유무를 Model에 속성(Attribute)으로 저장해본다. (동작 불가)
   // Model은 redirect에서 동작하지 않는다. (redirect는 데이터 전달이 아니기 때문에 Model 사용 불가)
   model.addAttribute("msg1",result ? "등록 성공" : "등록 실패");

   // 입력 성공 유무 redirectAttributes에 플래시 속성으로 저장해본다. (동작 가능)
   redirectAttributes.addFlashAttribute("msg2", result ? "등록 성공" : "등록 실패");
    
   return "redirect:/redirect/list.do"; // redirect (people 목록을 보여주는 새로운 요청을 수행) == 주소로 이동
  }
  
  // 데이터를 전달 할 때 사용 > forward
  @RequestMapping(value="/redirect/list.do")
  public String list(Model model) { // people 리스트를 list.jsp로 전달(forward)하기 위해 Model 선언 후 저장한다.
    model.addAttribute("people", people);
    return "webdir2/list"; // forward (Model에 저장한 people 속성을 list.jsp로 전달) == JSP 파일로 이동
  }
  
}
