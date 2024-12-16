package com.min.app10.dao;

import java.util.List;
import java.util.Map;

import com.min.app10.dto.UserDto;

public interface IUserDao {
  // 리스트 페이징 처리를 위한 변수 처리 요소(sort, offset, display)들을 Map으로 묶음
  List<UserDto> selectUserList(Map<String, Object> map);
  
  // 전체 갯수 구하기
  int selectUserCount();

 
}
