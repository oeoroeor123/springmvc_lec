package com.min.app06.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.app06.dto.ContactDto;

/*
 * JDBC
 * 1. Java DataBase Connection
 * 2. Java에서 DataBase에 접근할 수 있도록 해주는 Programming API
 * 3. WebApplication - JDBC Interface - MySQL JDBC Driver - MySQL DBMS
 *        app06      - ContactDaoImpl - mysql-connector-j - MySQL Server
 */

@Repository // DAO를 구현한 클래스라는 의미를 가진 @Component (Spring Container에 bean으로 등록된다.)
            // Persistence Layer(영속 계층)에서 동작한다.
            // 데이터 액세스 예외 처리(Rollback) 기능을 가진다.
public class ContactDaoImpl implements IContactDao {
  
  @Autowired // Spring Container에서 JdbcConnection 클래스 타입에 bean을 가져와서 필드에 주입해라.
  private JdbcConnection jdbcConnection;
  
  private Connection conn;
  private PreparedStatement ps;
  private ResultSet rs;
  
  @Override
  public List<ContactDto> getContactList() {
    // SELECT 결과 목록을 저장할 List
    List<ContactDto> contacts = new ArrayList<ContactDto>();
    
    // 데이터베이스에 접속
    conn = jdbcConnection.getConnection();
    try {
      // 실행할 쿼리문
      String sql = "SELECT contact_id, last_name, first_name, email, mobile, create_dt FROM tbl_contact";
      // 쿼리문을 실행할 PreparedStatement 객체 만들기
      ps = conn.prepareStatement(sql);
      // SELECT 쿼리를 실행하는 방법
      rs = ps.executeQuery(); 
      // SELECT 결과 행(Row)이 있으면 순서대로 하나씩 반복한다.
      while(rs.next()) {
        // 결과 행(Row)의 각 열 (Column)값을 가져온다.
        int contact_id = rs.getInt("contact_id");
        String last_name = rs.getString("last_name");
        String first_name = rs.getString("first_name");
        String email = rs.getString("email");
        String mobile = rs.getString("mobile");
        Date create_dt = rs.getDate("create_dt");
        
       // 각 열(Column)의 값을 ContactDto 객체로 만든다. 
       // @Builder (빌더 패턴) 이용해보기
        ContactDto contactDto = ContactDto.builder()
                                    .contact_id(contact_id)
                                    .last_name(last_name)
                                    .first_name(first_name)
                                    .email(email)
                                    .mobile(mobile)
                                    .create_dt(create_dt)
                                    .build();
      // 완성된 ContactDto 객체를 List에 저장
        contacts.add(contactDto);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 사용한 자원 반납
    jdbcConnection.close(conn, ps, rs);
    // SELECT 결과 목록 반환
    return contacts;
  }

  @Override
  public ContactDto getContactById(int contact_id) {
    // 결과 행(Row) 1개를 저장할 ContactDto 객체를 만든다.
    // 결과 행이 없을수도 있으므로 null 값을 초기화로 준다.(선언만 하기)
    ContactDto contactDto = null;
    // 데이터베이스에 접속한다.
    conn = jdbcConnection.getConnection();
    try {
      // 실행할 쿼리문을 가장 먼저 만든다.
      String sql = "SELECT contact_id, last_name, first_name, email, mobile, create_dt FROM tbl_contact WHERE contact_id = ?";
      // PreparedStatement 객체를 만든다.
      ps = conn.prepareStatement(sql);
      // 쿼리문에 물음표(위치 홀더)가 존재하면 해당 자리에 값을 전달해야 한다.
      // 각 물음표(위치 홀더)의 구분은 숫자로 한다. 첫 번째 물음표의 위치는 1이다.
      ps.setInt(1, contact_id); // 첫 번째 물음표(위치 홀더)에 context_id를 전달한다.
      // 쿼리문을 실행하고 결과를 ResultSet으로 받는다.
      rs = ps.executeQuery();
      // 결과 행이 0 또는 1개이므로 if문으로 결과 행의 존재 여부를 파악한다.
      if(rs.next()) {
        // 결과 행(Row)의 각 열(Column)값을 가져와서 ContactDto 객체로 만든다.          
        // @Builder (빌더 패턴) 이용해보기
         contactDto = ContactDto.builder()
                                      .contact_id(rs.getInt("contact_id"))
                                      .last_name(rs.getString("last_name"))
                                      .first_name(rs.getString("first_name"))
                                      .email(rs.getString("email"))
                                      .mobile(rs.getString("mobile"))
                                      .create_dt(rs.getDate("create_dt"))
                                      .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    //  사용한 자원을 반납한다.
    jdbcConnection.close(conn, ps, rs);
    // 결과 행 1개를 저장한 ContactDto 객체를 반환한다.
    return contactDto;
  }

  @Override
  public int getContactCount() {
    // 0을 초기화 값으로 주기
    int amount = 0;
    conn = jdbcConnection.getConnection();
    try {
      String sql = "SELECT COUNT(*) AS amount FROM tbl_contact";
      // PrepareStatement 객체 만들기
      ps = conn.prepareStatement(sql);
      // 쿼리문을 실행하고 결과를 ResultSet으로 받는다.
      rs = ps.executeQuery();
      // SELECT 결과 행(Row)이 있으면 행 갯수를 가져온다.
      if(rs.next()) {
        amount = rs.getInt("amount");
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcConnection.close(conn, ps, rs);
    // 결과 행 갯수를 저장한 amount 객체를 반환한다.
    return amount;
  }

  @Override
  public int register(ContactDto contactDto) {
    // 등록 결과를 저장할 변수 (초기 상태 : 등록 실패)
    int result = 0;
   
    // 데이터베이스 접속
    conn = jdbcConnection.getConnection();
       
    try {
      // 실행할 쿼리문 (인자값은 ? 로 표시)
      String sql = "INSERT INTO tbl_contact VALUES(null, ?, ?, ?, ?, CURDATE())";
      // PrepareStatemenet 객체 생성
      ps = conn.prepareStatement(sql);
      // 쿼리문에 인자 값 전달 (getter로 각 요소 꺼낸 뒤 setter로 값 전달)
      ps.setString(1, contactDto.getLast_name());
      ps.setString(2, contactDto.getFirst_name());
      ps.setString(3, contactDto.getEmail());
      ps.setString(4, contactDto.getMobile());
      // 쿼리문을 실행하고 결과 받기, 결과가 0이면 등록 실패 / 1이면 등록 성공
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // 사용한 자원 반납
    jdbcConnection.close(conn, ps, rs);
    
    // 등록 결과 반환
    return result;
  }

  @Override
  public int modify(ContactDto contactDto) {
    int result = 0;    
    conn = jdbcConnection.getConnection();
    try {
      String sql = "UPDATE tbl_contact SET last_name = ?, first_name = ?, email = ?, mobile = ? WHERE contact_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, contactDto.getLast_name());
      ps.setString(2, contactDto.getFirst_name());
      ps.setString(3, contactDto.getEmail());
      ps.setString(4, contactDto.getMobile());
      ps.setInt(5, contactDto.getContact_id());
      result = ps.executeUpdate();  
    } catch (Exception e) {
      e.printStackTrace();
    }    
    jdbcConnection.close(conn, ps, rs);
    return result;
  }

  @Override
  public int remove(int contact_id) {
    int result = 0;
    conn = jdbcConnection.getConnection();
    try {
      String sql = "DELETE FROM tbl_contact WHERE contact_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, contact_id);
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    jdbcConnection.close(conn, ps, rs);
    return result;
  }

}
