package com.min.app12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitWebConfig(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
                                 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // controller 테스트용으로 WebConfig로 만든다.
public class UserControllerMockTests {
  
  /* controller 테스트용
  1. @SpringJUnitWebConfig annotation
  2. 나머지는 기존 Dao 테스트와 동일 (예외 발생 체크 필요)
  */
  
  // 통합 테스트를 수행하는 객체
  private MockMvc mockMvc;
  
  @BeforeEach // @test 이전에 먼저 동작하는 메소드
  public void setUp(WebApplicationContext webApplicationContext) throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }
  
  @Test
  public void MockMvc_객체_생성_테스트() {
    Assertions.assertNotNull(mockMvc); // mockMvc 객체가 not null이면 통과
    log.debug("객체생성테스트");
  }
  
  @Test
  public void 회원가입_테스트() throws Exception {
    // mockMvc.perform : controller 요청을 생성하고, 요청 결과를 받아 온다.
    MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders
                              .post("/user/signup.do") // 요청 방식에 맞춰 작성
                              .param("userEmail", "test2@naver.com")
                              .param("userPw", "test2")
                              .param("userName", "테스트2")
                          ).andReturn(); // 요청하고 결과까지 받아서 넘겨라
    
    // RedirectAttributes의 FlashArrtibute를 이용해 결과를 생성하면 getFlahMap() 메소드를 호출해서 확인한다. (포워드)
    log.info(mvcResult.getFlashMap().toString()); // FlashArrtibute 확인
    
    // Model의 Attributes를 이용해 결과를 생성하면 getModelAndView() 메소드와 getModelMap() 메소드를 호출해서 확인한다. (리다이렉트)
    log.debug(mvcResult.getModelAndView().getModel().toString()); // Attribute 확인
    
  }
  
  @Test
  void 로그인_테스트() throws Exception {
    
    MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders
                              .get("/user/login.do")
                              .param("userEmail", "test@naver.com")
                              .param("userPw", "test")
                          ).andReturn();
    
    log.debug(mvcResult.getFlashMap().toString());
    
  }
  
}
