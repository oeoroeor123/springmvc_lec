package com.min.app02.domain;

import org.springframework.stereotype.Component;

import lombok.ToString;

@Component // Spring Container에 address 이름의 bean을 만든다.
@ToString // toString() 메소드 오버라이드
public class Address {

  private String postcode = "1004";
  private String roadAddr = "서울특별시 강남구 언주로 81길 26";
}
