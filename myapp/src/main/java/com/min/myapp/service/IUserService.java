package com.min.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.min.myapp.dto.UserDto;

public interface IUserService {
  
  String signup(UserDto userDto);
  
  // 로그인 : true/false 값 넘기기 > 컨트롤러에서 request를 받아와서 처리
  boolean login(HttpServletRequest request);
  
  // 로그아웃
  void logout(HttpSession session);
  
  // 마이페이지
  UserDto mypage(int userId);
  
  // 개인정보 수정
  String modifyInfo(UserDto userDto) throws Exception;
  
  // 프로필 이미지 수정
  String modifyProfile(MultipartFile profile, int userId) throws Exception;
  
  // 비밀번호 변경
  String modifyPw(UserDto userDto);
  
  // 회원 탈퇴
  String deleteAccount(int userId);
}
