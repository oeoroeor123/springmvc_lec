package com.min.app04.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.min.app04.vo.Person;

@Controller
public class MvcController1 {
  
  /*
   * MvcController1 에서 요청 주소를 만드는 규칙
   * 1. Forward는 중간 주소 /forward를 쓴다.
   * 2. 모든 요청 주소는 Suffix=".do"값을 가진다.
   */
  
  /*
   * Forward
   * 1. 요청을 그대로 전달하는 방식
   * 2. 요청에 포함된 요청 파라미터들도 함께 전달된다.
   * 3. Spring MVC Project의 기본 이동 방식이다.
   * 4. SELECT문의 결과를 전달할 때 사용한다. 
   */
  
  @RequestMapping(value="/forward/main.do")
  public String main() {
    return "webdir1/main"; // ViewResolver에 의해서 "/WEB-INF/webdir1/main.jsp" 경로로 이동한다. (forward 한다.)  
  }
  
  @RequestMapping(value="/forward/link.do")
  public String forward(
      HttpServletRequest request // HttpServletRequest : 모든 요청을 처리하는 인터페이스
    , Model model                // Model : Spring에서 사용하는 속성(Attribute) 저장용 인터페이스 (* forward할 데이터 저장 시 사용)
      ) {
    
    // HttpServletRequest request에 전달할 데이터를 속성(Attribute)의 형식으로 저장할 수 있다.
    request.setAttribute("email", "user@naver.com");
    
    // Model model에 전달할 데이터를 속성(Attribute)의 형식으로 저장할 수 있다. (* Spring은 Model 사용을 권장한다.)
    model.addAttribute("address", "서울특별시 강남구 강남대로");
    
    // Map을 전달하기
    model.addAttribute("contact", Map.of("tel","02-123-1234","mobile","010-1234-1234"));

    // Person 인스턴스를 전달하기
    Person person = new Person();
    person.setName("이수정");
    person.setAge(29);
    model.addAttribute("person", person);
    
    // List<String> 전달하기
    model.addAttribute("hobbies", Arrays.asList("독서","운동","잠"));
    
    // List<Person> 전달하기
    Person p1 = new Person();
    p1.setName("유저1");
    p1.setAge(20);
    
    Person p2 = new Person();
    p2.setName("유저2");
    p2.setAge(30);
    
    model.addAttribute("people", Arrays.asList(p1, p2));
    
    return "webdir1/forward"; // "/WEB-INF/webdir1/forward.jsp" 경로로 요청이 forward (전달)된다.
  }
  
}
