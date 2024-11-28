package com.min.app01.pkg03_component;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component // 이 person 클래스는 이름이 person인 bean으로 만들어집니다.


@Getter
public class Person {

  private String name = "유저";
  private Contact contact = new Contact("010-2222-2222", "user2@min.com"); // Contact 클래스에서 @AllArgsConstructor 생성자 지정하고, 새로운 Contact을 만들어줌
  
}
