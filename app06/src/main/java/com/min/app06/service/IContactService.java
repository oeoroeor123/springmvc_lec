package com.min.app06.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.app06.dto.ContactDto;

public interface IContactService {

  // 하나의 서비스에서 여러가지 Dao를 해결할 수 있음 (서비스와 Dao는 1대1 관계가 아님)
  // 목록 서비스
  Map<String, Object> getAllContact();
  
  // 상세 조회 서비스 (contact_id를 넘겨야 SELECT 후 데이터를 가져옴)
  ContactDto getContact(int contact_id);
  
  // 등록,수정,삭제 서비스 (request 타입으로 데이터를 받고, String 타입으로 반환 하기)
  String register(HttpServletRequest request);
  String modify(HttpServletRequest request);
  String remove(HttpServletRequest request);
   
}
