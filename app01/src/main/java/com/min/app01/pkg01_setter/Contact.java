package com.min.app01.pkg01_setter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
  private String mobile;
  private String email;

 /* 
 * singleton pattern
 * contact 인스턴스를 하나만 사용하는 패턴

 private static Contact contact = new Contact();
 // private Contact 생성자를 내부에서만 호출할 수 있게 처리
 private Contact() {
   
 }
 public static Contact getInstance() {
   return contact;
 }
*/

}
