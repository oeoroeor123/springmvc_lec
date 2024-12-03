package com.min.app03.controller;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.min.app03.vo.PageVo;

import lombok.Builder.Default;

@Controller
public class MvcController3 {

  @RequestMapping(value="/webdir3/main")
  public String main() {
    return "webdir3/main";
  }
  
  @RequestMapping(value="/webdir3/req1")
  public String req1() {
    return "webdir3/req1";
  }
  
   /*
   * Query String
   * Query String이란 URL의 ? 뒤에 추가된 파라미터를 의미한다.
   */
  
   /*
   * Query String 처리하기 1
   * HttpServletRequest 활용하기
   * 모든 요청에 관한 정보는 HttpServletRequest 인터페이스가 담당한다.
   * Query String 은 Parameter 형태로 HttpServletRequest 인터페이스 객체에 저장되어 있다.
   * getParameter() 또는 getParameterValues() 메소드를 이용해 Parameter 를 확인할 수 있다.
   * getParameter() 메소드는 String 을 반환하고, getParameterValues() 메소드는 String[] 을 반환한다.
   */
  
  // Query String : sort=ASC&page=1
  @RequestMapping(value="/webdir3/req2")
  public String req2(HttpServletRequest request) {
    String sort = request.getParameter("sort");
    int page = Integer.parseInt(request.getParameter("page"));
    System.out.println(sort + "," + page);
    return "webdir3/req2";
  }

  
  // Query String : flower=ROSE&flowers=TULIP
  @RequestMapping(value="webdir3/req3")
  public String req3(HttpServletRequest request) {
    String[] flowers = request.getParameterValues("flowers");
    System.out.println(Arrays.toString(flowers)); // 배열을 문자열로 반환
    return "webdir3/req3";
  }

  
  // Query String : 없음
  @RequestMapping(value="webdir3/req4")
  public String req4(HttpServletRequest request) {
    
    // 전달되지 않은 Parameter를 꺼내면 null이 반환된다. 
    String sort = request.getParameter("sort");
    
    // 전달되지 않은 Parameter에 디폴트 값을 적용할 수 있다.
    // Parameter page 가 전달되지 않는다면 "1"을 디폴트 값으로 사용하도록 설정해본다.
    
    // Parameter page를 Optional(값을 담는 쇼핑백)에 담는다. 
    // Parameter page는 ofNullable() 메소드로 담았기 때문에 값이 없어도 오류가 발생되지 않는다.
    // Optional > .of : 100% 값이 있다, .ofNullable : 값이 있을수도 있고 없을수도 있다.
    Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
   
    // Optional에 담은 Parameter page를 꺼낸다. 이때 Parameter page가 없으면 "디폴트 값 1"을 꺼낸다.
    int page = Integer.parseInt(opt.orElse("1"));
    System.out.println(sort + "," + page);
    return "webdir3/req4";
  }
  
  
  /*
   * Query String 처리하기 2
   * @RequestParam Annotation 활용하기
   * 각 Parameter 마다 @RequestParam Annotation 을 추가하여 Parameter를 직접 변수로 받을 수 있다.
   * Parameter의 필수 여부와 디폴트 값 설정을 할 수 있다.
   * @RequestParam Annotation은 생략도 가능하다. 생략하면 선언된 변수의 타입과 이름을 추론하여 값을 받는다.
   */
  
  // Query String : sort=ASC
  @RequestMapping(value="/webdir3/req5")
  public String req5(
      @RequestParam(value="sort") String sort // Parameter sort가 전달되지 않으면 잘못된 요청(400) 예외가 발생한다.
    , @RequestParam(value="page", required=false, defaultValue="1") int page
      // required=false : 필수가 아니라서, null이 가능하다.
      // Parameter page가 전달되지 않으면 기본 값으로 "1"을 사용한다.
  ) {
    System.out.println(sort + "," + page);
    return "webdir3/req5";
  }
  
  
  /*
   * Query String 처리하기 3
   * 커맨드 객체 활용하기
   * Parameter를 필드로 가지고 있는 클래스 타입의 객체(커맨드 객체)를 이용해서 Parameter를 받을 수 있다.
   * 커맨드 객체에는 Setter가 정의되어 있어야 한다.
   */
  
  // Query String : sort=ASC&page=1
  @RequestMapping(value="/webdir3/req6")
  public String req6(PageVo pageVo) {
    System.out.println(pageVo.getSort() + "," + pageVo.getPage());
    return "webdir3/req6";
   
  }

}