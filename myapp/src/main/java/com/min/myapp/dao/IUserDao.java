package com.min.myapp.dao;

import java.util.Map;

import com.min.myapp.dto.UserDto;

public interface IUserDao {
  
  // Dao에서는 실제 해야하는 작업의 이름으로 적는다. (사용자가 실제로 사용하는 서비스 이름은 service에서 적어서 사용한다.)
  
  // 회원 가입
  int insertUser(UserDto userDto);
  
  // 로그인 (Map으로 아이디와 비밀번호를 함께 받음)
  UserDto selectUserByMap(Map<String, Object> map);
  
  // 개인정보 업데이트
  int updateUserInfo(UserDto userDto) throws Exception;
  
  // 프로필 이미지 업데이트
  int updateUserProfile(UserDto userDto);
  
  // 비밀번호 변경
  int updateUserPassword(UserDto userDto);
  
  // 회원 탈퇴
  int deleteUser(int userId);
  
}
