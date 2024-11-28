package com.min.app01.pkg02_bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// bean을 만들기 위해선, 클래스 / 메소드 2가지가 들어가야함

@Configuration // bean을 만드는 클래스
public class AppConfig {
  
  /*
   * Configuration 클래스 사용 시, 메소드 == bean
   * 반환 타입 : bean의 타입. <bean class="">
   * 메소드 이름 : bean의 이름. <bean id="">
   * scope = singleton (디폴트)
   */
  
  // @Bean을 붙인 메소드 == Bean을 만드는 메소드 
  @Bean
  Contact contact() {
    Contact contact = new Contact();
    contact.setMobile("010-1111-1111");
    contact.setEmail("user@min.com");
    return contact;
  }
  
  
  @Bean(name = "person") // bean의 이름은 person입니다.
  Person aa() {          // 메소드 이름은 더이상 bean의 이름이 아닙니다. (위에서 이름을 지정했기 때문에)   
    Person person = new Person();
    person.setName("유저");
    person.setContact(contact()); // contact 메소드 호출
    return person;
  }
  
  
}
