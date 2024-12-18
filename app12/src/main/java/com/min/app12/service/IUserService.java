package com.min.app12.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.min.app12.dto.UserDto;

public interface IUserService {
  String signup(UserDto userDto);
  
  // true/false 값 넘기기 > 컨트롤러에서 request를 받아와서 처리
  boolean login(HttpServletRequest request);
  
  // 로그아웃
  void logout(HttpSession session);
}
