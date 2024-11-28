package com.min.app02.pkg03_constructor;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.min.app02.domain.Address;
import com.min.app02.domain.Contact;

// import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/*
 * DI 방식 > 3. 생성자 주입
 *  - Spring Container에 있는 bean을 Constructor 매개변수에 주입(전달)하는 방식
 *  - Constructor 매개변수에 @Autowired를 한 번만 추가하면 된다.
 *  - (Spring Framework 4.3 이후 @Autowired를 생략할 수 있다.)
 *  - 생성자 주입을 이용하면 필드에 final 키워드를 추가하여 좀 더 안전한 코드를 작성할 수 있다.
 *  - final 키워드가 추가된 필드의 초기화를 위한 생성자는 @AllArgsConstructor가 아니다.
 *  - (필드에 final 키워드를 추가한 뒤, @RequiredArgsConstructor를 사용한다.)
 */

@RequiredArgsConstructor // Person(Address, Contact) + @NonNull
// @AllArgsConstructor // Person(Address, Contact) 생성자 만들기
@Component // Spring Container에 person 이름의 bean을 만든다.
@ToString // person 타입의 bean을 sysout으로 곧바로 확인할 수 있다.
public class Person {

  // field
  private final Address address;
  private final Contact contact;

  
  // constructor
  /* @Autowired > 생성자의 @Autowired는 생략이 가능하다.
  public Person(Address address, Contact contact) {
    this.address = address;
    this.contact = contact;
  }
  */
  
  
}
