package com.min.app06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app06.dao.IContactDao;
import com.min.app06.dto.ContactDto;

/*
 * ContactDaoImpl bean을 만드는 방법에 따른 @SpringJUnitConfig 설정 방법
 * 1. <bean>
 *  @SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
 
 * 2. @bean
 *  @SpringJUnitConfig(classes = AppConfig.class) > @bean Annotation 만든 class

 * 3. @Component
 *  @SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
 */ 


/*
 * Spring Container에 저장된 ContactDaoImpl bean을 가져오기 위해서 
 * 환경 설정 파일로 servlet-context.xml 파일을 등록한다.
 * 
 * ContactDaoImpl bean은 @Component (사실은 repository)로 만든 bean임으로 Component Scan이 필요하다.
 * servlet-context.xml 파일에는 <context:component-scan base-package="com.min.app06" /> 태그가
 * Component Scan으로 등록되어 있다.
 */

@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

class ContactTest {
  
  @Autowired // 테스트 코드에서는 DI 방법 중 필드 주입이 적절하다.
  private IContactDao contactDao; // 인터페이스 구현 클래스는 타입으로 인터페이스를 사용한다. (ContactDaoImpl의 부모 = IContactDao를 사용)
  
  /* 
   * 테스트를 수행하는 메소드를 만드는 방법
   * @Test : 테스트를 수행하는 메소드
   * 반환타입 : void 사용
   * 메소드 명 : 자유 (한글 사용 가능)
   * 매개변수 : 없음 
   */

  @Test
  void 목록테스트() {
    // 목록의 첫 번째 요소의 last_name이 james이면 통과 !
    // assertEquals("james", contactDao.getContactList().get(0).getLast_name());
    
    // 목록의 갯 수가 3개이면 통과 !
       assertEquals(3, contactDao.getContactList().size());
  }
  
  @Test
  void 상세테스트() {
    // contact_id = 3인 행의 first_name이 jin이면 통과 !
    int contact_id = 3;
    assertEquals("jin", contactDao.getContactById(contact_id).getFirst_name());
  }
  
  @Test
  void 전체행개수테스트() {
    // 전체 행 개수가 3개이면 통과 !
    assertEquals(3, contactDao.getContactCount());
  }
  
  @Test
  void 등록테스트() {
    // 등록할 ContactDto 타입의 객체 생성
    ContactDto contactDto = ContactDto.builder()
                               .last_name("button")
                               .first_name("tim")
                               .email("user@daum.net")
                               .mobile("010-3333-3333")
                               .build();
    
    // 등록 결과가 1이면 통과 !
    assertEquals(1, contactDao.register(contactDto));
  }
 
  @Test
  void 수정테스트() {
    // 수정할 ContactDto 타입의 객체 생성
    ContactDto contactDto = ContactDto.builder()
                              .contact_id(1)
                              .last_name("sujeong")
                              .first_name("lee")
                              .email("oo@naver.com")
                              .mobile("010-5555-5555")
                              .build();
    
    // 수정 결과가 1이면 통과 !
    assertEquals(1, contactDao.modify(contactDto));
  }
  
  @Test
  void 삭제테스트() {
    // 삭제할 contact_id
    int contact_id = 2;
    
    // 삭제 결과가 1이면 통과 !
    assertEquals(1, contactDao.remove(contact_id));
  }

}
