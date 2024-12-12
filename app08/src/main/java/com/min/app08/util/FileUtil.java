package com.min.app08.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component // Spring Container에 bean 생성
public class FileUtil {
  
  // 파일을 업로드 하기 위해 java.util에서 지원하는 두가지 코드를 계산하기위한 코드 

  // field : 현재 날짜
  // 파일 인스턴스를 호출하는 날짜가 현재 날짜로 지정됨
  private LocalDate today = LocalDate.now();
  
  /* 
   * 파일 업로드 경로를 반환하는 메소드
   * @return 현재 날짜를 경로로 사용. 예를 들어 2024-12-12일에 실행하는 경우 반환되는 경로는 /upload/2024/12/12 이다.
   *         경로 구분자는 슬래시(/)를 사용한다.(linux, mac, mindows 모두 사용 가능)
   */
  public String getFilePath() {
    return "/upload" + DateTimeFormatter.ofPattern("/yyyy/MM/dd").format(today);
  }
  
  /* 
   * 파일 저장 이름을 반환하는 메소드
   * @param 파일의 원래 이름
   * @return 파일의 저장 이름. 중복 방지를 위해서 중복이 없는 난수 이름인 UUID을 사용하고, 확장자는 파일의 원래 확장자를 사용한다.
   */
  
  // extensionName = 확장자
  public String getFilesystemName(String originalFilename) {
   String extensionName = "";
   if(originalFilename.endsWith(".tar.gz"))
     extensionName = ".tar.gz";
   else
       extensionName = originalFilename.substring(originalFilename.lastIndexOf(".")); // 마지막 마침표(.)의 인덱스부터 끝까지
   return UUID.randomUUID().toString() + extensionName;
  }  
}
