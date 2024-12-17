package com.min.myapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component // Spring Container에 bean 생성
public class FileUtil {

  // field : 현재 날짜
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
   * @return 파일의 저장 이름. 중복 방지를 위해서 난수 처리된 이름을 사용하고, (UUID) 파일의 원래 확장자를 사용한다.
   */
  
  public String getFilesystemName(String originalFilename) {
   String extensionName = "";
   if(originalFilename.endsWith(".tar.gz"))
     extensionName = ".tar.gz";
   else
       extensionName = originalFilename.substring(originalFilename.lastIndexOf(".")); // 마지막 인덱스 . 부터 끝까지
   return UUID.randomUUID().toString() + extensionName;
  }
  
}
