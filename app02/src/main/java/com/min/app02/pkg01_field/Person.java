package com.min.app02.pkg01_field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;

import lombok.ToString;

/*
 * DI 방식 > 1. 필드 주입
 *  - Spring Container에 있는 bean을 field에 주입(전달)하는 방식, 필드마다 @Autowired 를 추가한다.
 *  - getBean 작업 없이 자동으로 bean을 가져와라.
 *  - 타입 이름으로 bean을 찾아 온다.
 */

@Component // Spring Container에 person 이름의 bean을 만든다.
@ToString  // person 타입의 bean을 sysout으로 곧바로 확인할 수 있다.
public class Person {

  // field
  @Autowired // Spring Container에서 타입이 Address인 bean을 가져옵니다.
  private Address address;
  
  @Autowired // Spring Container에서 타입이 Contact인 bean을 가져옵니다.
  private Contact contact;
  
  
}
