package com.min.app12.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j // log 남기기용
@Aspect // @Aspect 사용용
@Component // bean 생성용 (bean을 생성해야 동작이 가능)
public class LoggingAspect {

  // 언제 어떤게 동작할건지
  @Pointcut("execution(* com.min.app12.controller.*Controller.*(..))")
  public void preparePointCut() {
  }
  
  // preparePointCut 메소드 동작 이전에 자동으로 동작 (@Before의 정해진 파라미터 : joinPoint)
  @Before("preparePointCut()")
  public void logging(JoinPoint joinPoint) { 
     
     // sysout 대신 log를 @Slf4j annotation을 지정하고, 사용한다.
     // log.debug("Before Advice 동작");
    
     // HttpServletRequest를 이용해 요청 메소드/요청 주소/요청 파라미터 확인
     // 타입이 안맞아 ServletRequestAttributes 타입으로 먼저 캐스팅 후 .getRequest (request를 꺼냄)
     HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
     
     String requestMethod = request.getMethod(); // 요청 메소드
     String requestURI = request.getRequestURI(); // 요청 주소
     Map<String, String[]> params = request.getParameterMap(); // 모든 요청 파라미터를 Map으로 반환
     String str = "{";
     if(!params.isEmpty()) {
       for(Entry<String, String[]> entry : params.entrySet()) // entrySet을 하나씩 entry에 넣어준다.
         str += entry.getKey() + ":" + Arrays.toString(entry.getValue()) + ", "; // Arrays.toString : 배열 출력 코드
       }
     str = str.substring(0, Math.max(1, str.length() - 2)) + "}";
     
     log.debug("Method: {}, URI: {}, Parameters: {}", requestMethod, requestURI, str);
  }
  
}
