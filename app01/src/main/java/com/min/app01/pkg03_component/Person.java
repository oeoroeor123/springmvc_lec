package com.min.app01.pkg03_component;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component(value="p") // 이 person 클래스는 이름이 p인 bean으로 만들어집니다. > 디폴트 형식의 생성자를 사용함
// 이름 지정 : value=""
// 이름 미지정 : 클래스 이름 전체 소문자

@Getter
public class Person {

  private String name = "유저";
  private Contact contact = new Contact("010-2222-2222", "user2@min.com"); // Contact 클래스에서 @AllArgsConstructor 생성자 지정하고, 새로운 Contact을 만들어줌
  
}
