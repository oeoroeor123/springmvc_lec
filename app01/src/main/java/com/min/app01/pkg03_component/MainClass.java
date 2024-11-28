package com.min.app01.pkg03_component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext("com.min.app01.pkg03_component");
    
    Contact contact = ctx.getBean("contact", Contact.class);
    System.out.println(contact.getMobile());
    System.out.println(contact.getEmail());
    
    Person person = ctx.getBean("p", Person.class);
    System.out.println(person.getName());
    System.out.println(person.getContact().getMobile());
    System.out.println(person.getContact().getEmail());
    
    ctx.close();

  }

}
