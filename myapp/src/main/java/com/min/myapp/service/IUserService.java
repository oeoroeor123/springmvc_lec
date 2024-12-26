package com.min.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.min.myapp.dto.UserDto;

public interface IUserService {
  String signup(UserDto userDto);
  boolean login(HttpServletRequest request);
  void logout(HttpSession session);
  UserDto mypage(int userId);
  String modifyInfo(UserDto userDto) throws Exception;
  String modifyProfile(MultipartFile profile, int userId) throws Exception;
  String modifyPw(UserDto userDto);
  String deleteAccount(int userId);
}
