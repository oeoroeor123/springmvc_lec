package com.min.app03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // 나는 요청과 응답을 처리하는 컨트롤러 입니다.
public class MvcController1 {
  
   /* 
   * 메소드 == 하나의 요청과 응답을 처리하는 단위
   * 반환 타입
   *  1) String : 사용자에게 보여 줄 (응답 할) JSP의 경로와 이름을 작성
   *  2) void   : 요청한 주소를 JSP 경로와 이름으로 인식
   *  3) ModelAndView : 사용자에게 보여 줄 JSP의 경로와 이름은 setViewName() 메소드로 저장하고, 전달할 값은 addObject() 메소드로 저장
   *  4) ... 등등
   *  
   * 메소드 이름
   *  의미가 없음 (자유 지정)
   *  
   * 매개 변수
   *  1) 요청과 응답을 처리하기 위한 각종 클래스나 변수를 선언한다.
   *  2) 주요 매개 변수
   *    (1) HttpServletRequest 인터페이스 : 요청을 처리할 수 있는 인터페이스
   *    (2) HttpServletResponse 인터페이스 : 응답을 처리할 수 있는 인터페이스
   *    (3) Model 인터페이스 : 응답할 데이터를 저장할 수 있는 인터페이스
   *    (4) HttpSession 인터페이스 : 세션을 처리할 수 있는 인터페이스
   */
  
   /* 
    * @RequestMapping
    *   1. 요청 주소와 요청 메소드를 작성하는 Annotation = 대표 주소로 접속했을때 실행할 메소드
    *   2. 요청 주소 (value)
    *     1) value="/"                  : Context Path 요청을 의미 (대표 주소) ex_http://127.0.0.1:8080/app03
    *     2) value="/list"              : /list 요청을 의미 ex_http://127.0.0.1:8080/app03/list
    *     3) value={"/list","/list.do"} : 2개 이상의 요청을 의미 (주소가 달라도 처리하는 요청은 같음) 
    *                                     ex_http://127.0.0.1:8080/app03/list / ex_http://127.0.0.1:8080/app03/list.do
    *   3. 요청 메소드 (method)
    *     1) method=RequestMethod.GET   : GET 방식을 의미 (디폴트)
    *     2) method=RequestMethod.POST  : POST 방식을 의미
    */

  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String welcome() {
    return "webdir1/index"; // return "/webdir1/index";로 작성해도 스프링이 올바르게 해석해 동작 가능
    
     /* return "webdir1/index"; 해석해 보기
     * 1. 리턴 값 "index"는 ViewResolver에게 전달 된다.
     *    (DispatcherServlet : servlet-context.xml에 ViewResolver가 정의되어 있다.)
     * 2. ViewResolver는 "index" 앞에 "/WEB-INF/views/" 문자열을 추가한다. (prefix
     * 3. ViewResolver는 "index" 뒤에 ".jsp" 문자열을 추가한다. (suffix)
     * 4. ViewResolver는 완성된 최종 view로 이동한다.
     *    최종 view의 모습 : "/WEB-INF/views/webdir1/index.jsp"
     */
  }
  
  @RequestMapping(value="/webdir1/list", method=RequestMethod.GET) // http://localhost:8080/app03 (대표 주소) + 뒤에 주소 작성 value 
  public void letsgo() {
   // 반환 타입이 void 임으로 요청 주소 /webdir1/list를 사용자에게 보여 줄 JSP 경로와 이름으로 해석한다.
   // ViewResolver에 의해서 최종 경로는 "/WEB-INF/views/webdir1/list.jsp"가 된다.
  }
  
  @RequestMapping(value="/board/view") // 메소드를 생략하면 get 방식으로 동작
  public ModelAndView gogogo() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("webdir1/detail"); // 사용자에게 보여줄 JSP의 경로와 이름 > ViewResolver에 최종 경로는 "/WEB-INF/views/webdir1/detail.jsp"가 된다.
    mav.addObject("number", 10);       // JSP로 number = 10 이라는 값을 전달한다.
    return mav;
  }
  
  
  
  
  
  
}
