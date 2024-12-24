package com.min.app13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app13.dao.IBbsDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringJUnitConfig(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
                                 ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // controller 테스트용으로 WebConfig로 만든다.
public class BbsDaotests {

  @Autowired
  private IBbsDao bbsDao;
  
  @Autowired
  private SqlSessionTemplate template;
  
  @Test
  void 빈_테스트() {
    // Assertions.assertNotNull(bbsDao);
    Assertions.assertNotNull(template);
  }
  
  @Test
  void 개수_테스트() {
    Assertions.assertEquals(1, bbsDao.selectBbsCount());
  }
  
}
