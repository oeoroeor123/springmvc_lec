package com.min.myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component // spring container에 jdbcConnection 객체(bean)를 저장
public class JdbcConnection {

  /*
   * 접속 메소드
   * 
   * 1. MySQL Driver
   * 
   * 2. MySQL URL(ServerTimeZone)
   * 
   * 3. User (name/password)
   * @return Connection
   */
  
 public Connection getConnection() {
   Connection conn = null;
   try {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://127.0.0.1:3306/db_myapp?serverTimezone=Asia/Seoul";
    String username = "greenit";
    String password = "greenit";
     
    // 드라이버 클래스 로드(forName() : 클래스 로드를 위한 메소드)
    Class.forName(driver);
    
    // 접속 (DriverManager클래스의 getConnection() 메소드가 접속을 수행)
    conn = DriverManager.getConnection(url,username,password);
  
     
  } catch (Exception e) {
    e.printStackTrace();
  }
   return conn;
 }
 
 
 /*
  * 접속 해제 메소드 (아래 세가지 인터페이스 사용)
  * 1. Connection > 접속 수행
  * 2. PreparedStatement > 쿼리문 실행 후 결과 반환 
  * 3. ResultSet > 테이블 행 정보를 가리키는 위치를 제공
  */
 
 public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
   try {
    if(conn != null) conn.close();
    if(ps != null) ps.close();
    if(rs != null)rs.close();
     
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
 
 
 
}
