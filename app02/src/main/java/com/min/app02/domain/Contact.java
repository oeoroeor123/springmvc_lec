package com.min.app02.domain;

import org.springframework.stereotype.Component;

import lombok.ToString;

@Component // Spring Container에 contact 이름의 bean을 만든다.
@ToString // toString() 메소드 오버라이드
public class Contact {

  private String mobile = "010-1234-5678";
  private String email = "user@min.co.kr";
}
