package com.min.app01.ex02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainClass {

  public static void main(String[] args) {
    
    
 // AnnotationApplicationContext
    
    AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    
    Calculator calculator = ctx.getBean("calc", Calculator.class);
    System.out.println(calculator.getBrand());
    System.out.println(calculator.getModule1().add(1,2,3,4,5));
    System.out.println(calculator.getModule2().subtract(5, 10));
    System.out.println(calculator.getModule3().multiply(1,2,3,4,5));
    System.out.println(calculator.getModule4().divide(5, 2));
   
    
    ctx.close();
    
  }

}
