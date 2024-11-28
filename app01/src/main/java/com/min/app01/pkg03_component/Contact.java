package com.min.app01.pkg03_component;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component 
// 이 Contact 클래스는 이름이 contact인 bean으로 만들어집니다.
// 디폴트 형식의 생성자를 사용함

@NoArgsConstructor // Contact() 생성자
@AllArgsConstructor // Contact(String,String) 생성자 
@Getter
public class Contact {

  private String mobile = "010-1111-1111";
  private String email = "user@min.com";
  
}
