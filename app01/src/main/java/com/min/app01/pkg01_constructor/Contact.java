package com.min.app01.pkg01_constructor;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 필드 모두를 받아 생성자를 만든다.
@AllArgsConstructor
@Getter
public class Contact {

  private String mobile;
  private String email;
}
