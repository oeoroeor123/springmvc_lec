package com.min.app02.pkg02_setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;

import lombok.ToString;

/*
 * DI 방식 > 2. Setter 주입 (Setter 형식의 메소드 생성)
 *  - Spring Container에 있는 bean을 Setter 형식 메소드의 매개변수에 주입(전달)하는 방식, Setter 형식 메소드에 @Autowired 를 한 번만 추가한다.
 */

@Component // Spring Container에 person 이름의 bean을 만든다.
@ToString  // person 타입의 bean을 sysout으로 곧바로 확인할 수 있다.
public class Person {

  // field
  private Address address;
  private Contact contact;
 
  // Setter 형식의 메소드
  @Autowired // 매개변수에 선언된 Address 타입과 Contact 타입의 bean이 매개변수에 자동으로 주입된다.
  public void setBeans(Address address, Contact contact) {
    this.address = address;
    this.contact = contact;
  }
  
  /* 실제 Setter에서도 작업 가능하지만, 이렇게 작업할 필요 x > 한개로 합쳐서 작업하기
  @Autowired
  public void setAddress(Address address) {
    this.address = address;
  }
  @Autowired
  public void setContact(Contact contact) {
    this.contact = contact;
  }
  */
  
  
}
