package com.min.app01.pkg01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    // GenericXmlApplicationContext 클래스
    // XML로 생성된 bean을 관리하는 클래스
    
    AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context.xml");
    
    // 가져오는 bean의 타입을 명시
    Contact c = ctx.getBean("contact", Contact.class); // Contact c = (Contact) ctx.getBean("contact");
    System.out.println(c.getMobile());
    System.out.println(c.getEmail());
    
    ctx.close();

  }

}
