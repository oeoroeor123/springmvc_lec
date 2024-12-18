package com.min.myapp.util;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component // bean 사용 > Service에서 사용할 수 있도록 추가
public class SecureUtil {
  
  /**
   * 전달 받은 문자열을 SHA-256 방식의 해시 알고리즘으로 변환한 결과를 반환하는 메소드
   * SHA-256 방식은 암호화는 가능하고 복호화는 불가능한 단방향 알고리즘
   * java.security 패키지의 API를 사용
   * @param original 문자열 원본
   * @return SHA-256 방식으로 암호화 된 문자열
   */
  public String getSHA256(String original) {
    StringBuilder builder = new StringBuilder();
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256"); // MessageDigest : 암호화 해시 기능을 제공하는 클래스, .getInstance("SHA-256")를 호출해 알고리즘 객체를 생성
      md.update(original.getBytes()); // String을 byte 타입으로 변환하고, 변환한 바이트 데이터를 "SHA-256" 알고리즘에 전달하여 처리
      byte[] b = md.digest(); // byte 배열로 별도 지정하고 결과 반환
      for(int i = 0; i < b.length; i++) {
        builder.append(String.format("%02x",b[i])); // 16진수 표현값 : 소문자로 사용
      }
     } catch (Exception e) {
      e.printStackTrace();
    }
    return builder.toString(); // StringBuilder에 저장된 16진수 문자열을 String 타입으로 변환하여 반환
    
    // 실행 결과 : 256비트(32바이트) 크기의 결과를 반환하며, 16진수 문자열로 반환하면 64글자(1바이트마다 2글자씩 나옴)가 된다.
    //             이는 암호화된 문자열로, 원본 데이터를 복원할 수 없는 단방향 해시이다.
  }
  
  
  /**
   * 스크립트 코드를 입력해서 시스템을 공격하는 크로스 사이트 스크립팅
   * 공격을 무력화하기 위한 메소드
   * <code><script></code> 태그 입력을 무력화하기 위해 &lt;과 &gt;으로 인식하여 변환함
   * @param original 전달된 원본 문자열
   * @return entity 코드(&lt;과 &gt;)로 변환된 문자열
   */
  public String getPreventXSS(String original) {
    // 공격 무력화를 위한 문자열 변환 (original > entity)
    return original.replace("<script>", "&lt;script&gt;")
                   .replace("</script>", "&lt;script&gt;");
  }
}
