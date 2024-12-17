package com.min.app11.config;

// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.min.app11.aop.AroundAdvice;
import com.min.app11.aop.BeforeAdvice;

/**
 * AOP 동작 확인을 위해서는
 * <code>@EnableAspectJAutoProxy</code> 와
 * <code>@Bean</code> 주석 해제 후 확인해야 한다.
 */

// @EnableAspectJAutoProxy // AOP 동작을 위해 @Configuration과 함께 사용
@Configuration // spring container에 bean을 만들어두는 클래스
public class AppConfig {

  // @Bean // bean을 만드는 메소드 (타입 : BeforeAdvice / 이름 : before)
  BeforeAdvice before() {
    return new BeforeAdvice();
  }
  
  // @Bean
  AroundAdvice around() {
    return new AroundAdvice();
  }
  
}
