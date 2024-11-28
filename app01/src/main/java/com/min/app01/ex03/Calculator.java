package com.min.app01.ex03;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component // 이 Calculator 클래스는 이름이 calculator인 bean으로 만들어집니다.

@Getter
public class Calculator {
  private String brand = "삼성";
  private Adder module1 = new Adder();
  private Subtractor module2 = new Subtractor();
  private Multiplier module3 = new Multiplier();
  private Divider module4 = new Divider();
  
  
}
