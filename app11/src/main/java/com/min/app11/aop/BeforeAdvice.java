package com.min.app11.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // AOP 방식 동작을 위한 Aspect 선언 (종단 관심사를 가진 클래스)
public class BeforeAdvice {

  /**
   * PointCut 등록 메소드 작성 방법
   * 1. 반환 타입 : void만 사용 가능
   * 2. 메소드 명 : 자유 (이름만 제공하는 메소드)
   * 3. 매개변수  : 없음
   * 4. 본문      : 없음
   */
  
  // 이름이 Controller로 끝나는 모든 클래스의 모든 Controller 메소드를 포인트컷으로 등록한다.
  // 모든 컨트롤러(*Controller)에 모든 메소드(.*)에 모든 매개변수(..)에 모든 반환 타입 (맨 앞에 * )
  @Pointcut("execution(* com.min.app11.controller.*Controller.*(..))")
  public void preparePointCut() {
    
  }
  
  /**
   * Before Advice 메소드 작성 방법 (After Advice 작성 방법도 동일)
   * 1. 반환 타입 : void만 사용 가능
   * 2. 메소드 명 : 자유
   * 3. 매개변수  : JoinPoint만 사용 가능
   * @param joinPoint
   */
  
  // value=포인트컷의 execution 내용이 들어가야함, 위에 메소드로 만들어 놨기에 메소드 명()으로 호출한다.
  // preparePointCut 메소드가 실행되기 전에 동작한다.
  @Before(value="preparePointCut()")
  public void beforeAdvice(JoinPoint joinPoint) {
    
    // Advice 작성 : 동작할 코드를 작성하는 곳
    System.out.println("Before Advice가 동작합니다.");
  }
  
}
