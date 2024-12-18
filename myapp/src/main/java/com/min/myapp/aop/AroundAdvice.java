package com.min.myapp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // AOP 방식 동작을 위한 Aspect 선언 (종단 관심사를 가진 클래스)
public class AroundAdvice {

  //이름이 service로 끝나는 모든 클래스의 모든 Impl 메소드를 포인트컷으로 등록한다.
  @Pointcut("execution(* com.min.myapp.service.*Impl.*(..))")
  public void preparePointCut() {
    
  }
  
  /**
   * Around Advice 메소드 작성 방법
   * 1. 반환 타입 : Object만 사용 가능
   * 2. 메소드 명 : 자유
   * 3. 매개변수  : proceedingJoinPoint만 사용 가능
   * @param proceedingJoinPoint
   * @return proceedingJoinPoint의 proceed() 메소드 호출 결과를 반환
   */
  
  // value=포인트컷의 execution 내용이 들어가야함, 위에 메소드로 만들어 놨기에 메소드 명()으로 호출한다.
  @Around(value="preparePointCut()")
  public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    
    System.out.println("서비스 메소드 동작 이전 시점");
    
    // 서비스 메소드가 실행하는 시점을 의미 (around를 사용하기 때문에 동작 이전/이후를 넣어줘야 함)
    Object obj = proceedingJoinPoint.proceed();
    
    System.out.println("서비스 메소드 동작 이후 시점");

    return obj;
  }
  
  
}
