package com.min.app01.ex01;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // Calculrator()
@AllArgsConstructor // Calculrator(String, Adder, Subtractor, Multiplier, Divider)
@Getter
@Setter
public class Calculator {
  private String brand;
  private Adder module1;
  private Subtractor module2;
  private Multiplier module3;
  private Divider module4;
  
  
}
