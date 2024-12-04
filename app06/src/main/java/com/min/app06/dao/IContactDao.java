package com.min.app06.dao;

import java.util.List;

import com.min.app06.dto.ContactDto;

public interface IContactDao { // 행(Row)은 ContactDto 객체에 저장한다.

  /* DAO : DataBase Access Object
  데이터베이스에 접속해서 DB 처리를 수행하는 객체를 의미
  기본적으로 Singleton Pattern으로 생성 (스프링은 Spring Container에 bean을 만들 때 기본적으로 Singleton 으로 만든다.)
  class 이름 규칙 : I(인터페이스) Contact(table명) Dao
  */
  
  // 반환 타입
  // 타입 1) List<ContactDto> : ContactDto 여러개를 저장하고 있는 List
  // 타입 2) ContactDto : ContactDto 상세 중 한 개
  List<ContactDto> getContactList(); // 모든 행(Row)을 반환한다. 
  ContactDto getContactById(int contact_id); // PK인 contact_id를 쿼리에 전달해서 해당 행(Row)을 반환한다.
  int getContactCount(); // 모든 행(Row)의 갯 수를 반환한다.
  int register(ContactDto contactDto); // 등록할 정보를 ContactDto 객체로 만들어서 쿼리에 전달하고 등록된 행(Row)의 갯 수를 반환한다. (성공하면 1, 실패하면 0)
  int modify(ContactDto contactDto); // 수정할 정보를 ContactDto 객체로 만들어서 쿼리에 전달하고 수정된 행(Row)의 갯 수를 반환한다.
  int remove(int contact_id); // 삭제할 행(Row)의 식별자(PK)를 쿼리에 전달하고 삭제된 행(Row)의 갯 수를 반환한다. 
   
}

