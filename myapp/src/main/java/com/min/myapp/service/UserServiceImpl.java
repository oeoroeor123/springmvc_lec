package com.min.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.min.myapp.dao.IUserDao;
import com.min.myapp.dto.UserDto;
import com.min.myapp.util.SecureUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final IUserDao userDao;
  private final SecureUtil secureUtil;
    
  @Override
  public String signup(UserDto userDto) {
    
    // 비밀번호 암호화
    // 원본 비밀번호를 SHA256으로(암호화된 비밀번호로) 바꿔서 저장
    userDto.setUserPw(secureUtil.getSHA256(userDto.getUserPw()));
    
    // 이름 XSS 공격 무력화
    // 원본 이름을 XSS로 entity 코드로 바꿔서 저장
    userDto.setUserName(secureUtil.getPreventXSS(userDto.getUserName()));   
    return userDao.insertUser(userDto) == 1 ? "회원 가입 성공" : "회원 가입 실패";
  }
  
  @Override
  public boolean login(HttpServletRequest request) {
  
    // userEmail, userPw
    String userEmail = request.getParameter("userEmail");
    String userPw = secureUtil.getSHA256(request.getParameter("userPw"));

    // 접속 IP, 접속 브라우저 등 정보가 필요하면 request를 활용
    // String ip = request.getRemoteAddr();
    // String userAgent = request.getHeader("User-Agent");
    
    // DB로 보낼 Map을 만든 뒤, 해당 회원 정보 가져오기
    UserDto userDto = userDao.selectUserByMap(Map.of("userEmail", userEmail, "userPw", userPw));

    // 회원 존재 여부 확인 (null이 아니면 존재한다.)
    boolean exists = userDto != null;
    
    // 회원이 존재한다면 세션에 회원 정보를 저장하기
    if(exists) {
      HttpSession session = request.getSession();
      session.setMaxInactiveInterval(60 * 60); // 1시간동안 세션 정보가 유지된다. (로그인 유지 시간)
      session.setAttribute("loginUser", userDto); // 사용자 정보 저장 (세션에 loginUser 값이 있으면 로그인 상태)
      
      // 여기서 추가로, 접속 기록도 DB에 남기기 (insert 처리)
    }
      return exists;
  }
  
  @Override
  public void logout(HttpSession session) {
    session.invalidate(); // 세션에 저장되어있는 모든 정보를 초기화 (로그아웃)
  }
  
}
