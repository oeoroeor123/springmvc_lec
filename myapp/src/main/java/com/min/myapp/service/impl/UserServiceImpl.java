package com.min.myapp.service.impl;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.min.myapp.dao.IUserDao;
import com.min.myapp.dto.UserDto;
import com.min.myapp.service.IUserService;
import com.min.myapp.util.FileUtil;
import com.min.myapp.util.SecureUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final IUserDao userDao;
  private final SecureUtil secureUtil;
  private final FileUtil fileUtil;
  
  @Override
  public String signup(UserDto userDto) {
    
    // 비밀번호 암호화
    userDto.setUserPw(secureUtil.getSHA256(userDto.getUserPw()));
    
    // 이름 XSS 공격 방지
    userDto.setUserName(secureUtil.getPreventXSS(userDto.getUserName()));
    
    return userDao.insertUser(userDto) == 1 ? "회원 가입 성공" : "회원 가입 실패";
    
  }
  
  @Override
  public boolean login(HttpServletRequest request) {
    
    // userEmail과 userPw
    String userEmail = request.getParameter("userEmail");
    String userPw = secureUtil.getSHA256(request.getParameter("userPw"));
    
    // 접속 IP, 접속 브라우저 등 정보가 필요하면 request를 활용하세요.
    // String ip = request.getRemoteAddr();
    // String userAgent = request.getHeader("User-Agent");
    
    // DB로 보낼 Map을 만든 뒤, 해당 회원 정보 가져오기
    UserDto userDto = userDao.selectUserByMap(Map.of("userEmail", userEmail, "userPw", userPw));
    
    // 회원 존재 여부 확인
    boolean exists = userDto != null;
    
    // 회원이 존재하면 세션에 회원 정보를 저장하기
    if(exists) {
      HttpSession session = request.getSession();
      session.setMaxInactiveInterval(60 * 60);     // 1시간 동안 세션 정보가 유지됩니다.
      session.setAttribute("loginUser", userDto);  // 세션에 loginUser 값이 있으면 로그인 상태입니다.
      // 여기에서 추가로 접속 기록도 DB에 남겨야 합니다. 
    }
    
    return exists;
    
  }
  
  @Override
  public void logout(HttpSession session) {
    session.invalidate();  // 세션 초기화 작업
  }
  
  @Override
  public UserDto mypage(int userId) {
    return userDao.selectUserByMap(Map.of("userId", userId));
  }

  @Override
  public String modifyInfo(UserDto userDto) throws Exception {
    
    // 만약 userEmail과 userName을 모두 공백으로 수정하려고 하면 쿼리문 실행 시 구문에러가 발생하여 예외가 발생합니다.
    
    return userDao.updateUserInfo(userDto) == 1 ? "회원 정보 변경 완료" : "회원 정보 변경 실패";
    
  }
  
  @Override
  public String modifyProfile(MultipartFile profile, int userId) throws Exception {
    
    // 프로필 이미지 저장 경로
    String profilePath = fileUtil.getProfilePath();
    File dir = new File(profilePath);
    if(!dir.exists())
      dir.mkdir();
    
    // 프로필 이미지 저장 이름
    String profileName = fileUtil.getFilesystemName(profile.getOriginalFilename());
    
    // 프로필 이미지 HDD에 저장 (저장이 실패하는 경우 예외가 발생합니다. 즉 파일이 저장되지 않으면 여기서 코드 실행이 멈추기 때문에 DB도 수정되지 않습니다.)
    profile.transferTo(new File(dir, profileName));
   
    // DB로 보낼 UserDto 객체
    UserDto userDto = UserDto.builder()
                        .profileImg(profilePath + "/" + profileName)  //  /profile/2024/12/19/ed8...72abe32.jpg 형식으로 구성
                        .userId(userId)
                        .build();
    
    // DB 처리 및 처리 결과 반환  
    return userDao.updateUserProfile(userDto) == 1 ? "프로필 변경 완료" : "프로필 변경 실패";
    
  }
  
  @Override
  public String modifyPw(UserDto userDto) {
    
    // 비밀번호 암호화
    userDto.setUserPw(secureUtil.getSHA256(userDto.getUserPw()));
    
    return userDao.updateUserPassword(userDto) == 1 ? "비밀번호 변경 성공" : "비밀번호 변경 실패";
    
  }
  
  @Override
  public String deleteAccount(int userId) {
    
    // 프로필 이미지가 존재하는 경우 HDD에서 프로필 이미지를 삭제합니다.
    UserDto userDto = userDao.selectUserByMap(Map.of("userId", userId));
    File profile = new File(userDto.getProfileImg());
    if(profile.exists())
      profile.delete();
    
    // DB 정보를 삭제하고 그 결과를 반환합니다.
    return userDao.deleteUser(userId) == 1 ? "회원 탈퇴 성공" : "회원 탈퇴 실패";
    
  }
  
}
