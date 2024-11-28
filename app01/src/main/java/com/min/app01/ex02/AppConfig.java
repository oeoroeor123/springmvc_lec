package com.min.app01.ex02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  
  @Bean
  Adder adder() {
    Adder adder = new Adder();
    return adder;
  }
  
  @Bean
  Subtractor subtractor() {
    Subtractor subtractor = new Subtractor();
    return subtractor;
  }
  
  @Bean
  Multiplier multiplier() {
    Multiplier multiplier = new Multiplier();
    return multiplier;
  }
  
  @Bean
  Divider divider() {
    Divider divider = new Divider();
    return divider;
  }
  
  @Bean(name = "calc")
  Calculator calculator() {
    Calculator calculator = new Calculator();
    calculator.setBrand("삼성");
    calculator.setModule1(adder());
    calculator.setModule2(subtractor());
    calculator.setModule3(multiplier());
    calculator.setModule4(divider());
    return calculator;
  }
  
  
  
}
