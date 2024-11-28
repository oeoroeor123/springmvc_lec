package com.min.app01.pkg01_setter;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    // GenericXmlApplicationContext 클래스
    // XML로 생성된 bean을 관리하는 클래스
    
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("pkg01_stter/app-context.xml");
    
    // container로 부터 가져오는 bean의 타입을 명시
    Contact c = ctx.getBean("contactBean", Contact.class); // Contact c = (Contact) ctx.getBean("contactBean");
    System.out.println(c.getMobile());
    System.out.println(c.getEmail());
    
    Person p = ctx.getBean("personBean", Person.class);
    System.out.println(p.getName());
    System.out.println(p.getContact().getMobile());
    System.out.println(p.getContact().getEmail());
    
    // scope(singleton) 동작 확인
    Person p2 = ctx.getBean("personBean",Person.class);
    System.out.println(p == p2);
    
    ctx.close();
    

    /* singleton pattern 확인
    클래스.메소드()
    Contact c1 = Contact.getInstance();
    Contact c2 = Contact.getInstance();
    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c1 == c2);
    */
    
  }

}
