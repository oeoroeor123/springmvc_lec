package com.min.app06.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component // Spring Container에 JdbcConnection 객체(bean)를 저장해둔다.
public class JdbcConnection {
   
  // JDBC는 자바 언어로 데이터베이스 프로그래밍을 하기 위한 라이브러리이다.
  /**
   * 접속 메소드
   * 1. MySQL Driver (Driver 클래스의 이름)
   *  com.mysql.cj.jdbc.Driver
   *  
   * 2. MySQL URL
   *  jdbc:mysql://127.0.0.1:3306/db_app06?serverTimezone=Asia/Seoul
   *
   * 3. User
   *  greenit / greenit
   * @return java.sql.Connection > DataBase 접속 정보를 처리하는 인터페이스
   */
  
  public Connection getConnection() {
    Connection conn = null;
    try {
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://127.0.0.1:3306/db_app06?serverTimezone=Asia/Seoul";
      String username = "greenit";
      String password = "greenit";
      
      // 드라이버 클래스 로드 (forName : 클래스 로드를 위한 메소드, 매개변수에 MySQL Driver을 넣는다.)
      Class.forName(driver);
      
      // 접속 (DriverManager 클래스의 getConnection() 메소드가 접속을 수행한다.)
      conn = DriverManager.getConnection(url,username,password);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return conn;
  }
  
 
  
  
  /**
   * 접속 해제 메소드
   * @param java.sql.Connection : DataBase 접속 정보를 처리하는 인터페이스
   * @param java.sql.PreparedStatement : 쿼리문을 실행하고 그 결과를 반환하는 인터페이스
   * @param java.sql.ResultSet : 테이블의 행(Row) 정보를 가리키는 cursor(행을 옮겨다니는 행위)를 제공하는 인터페이스
   */
  
  public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
    try {
      if(conn != null) conn.close();
      if(ps != null)ps.close();
      if(rs != null)rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
