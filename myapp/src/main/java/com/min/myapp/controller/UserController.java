package com.min.myapp.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.min.myapp.dto.UserDto;
import com.min.myapp.service.IUserService;

import lombok.RequiredArgsConstructor;

@RequestMapping(value="/user")
@RequiredArgsConstructor
@Controller
public class UserController {

  private final IUserService userService;
  
  @RequestMapping(value="/signup.form")
  public String signupForm() {
    return "user/signup";
  }
  
  @RequestMapping(value="/signup.do", method=RequestMethod.POST)
  public String signup(UserDto userDto, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", userService.signup(userDto));
    return "redirect:/";
  }
  
  @RequestMapping(value="/login.form")
  public String loginForm(HttpServletRequest request, Model model) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("url"));
    String url = opt.orElse("http://localhost:8080/" + request.getContextPath());
    model.addAttribute("url", url);
    return "user/login";
  }
  
  @RequestMapping(value="/login.do", method=RequestMethod.POST)
  public String login(HttpServletRequest request, RedirectAttributes redirectAttributes) {
    boolean loginSuccess = userService.login(request);
    String url = request.getParameter("url");
    if(!loginSuccess) {
      redirectAttributes.addFlashAttribute("msg", "일치하는 회원 정보가 없습니다.");
      return "redirect:/user/login.form?url=" + url;
    }
    return "redirect:" + url;
  }
  
  @RequestMapping(value="/logout.do")
  public String logout(HttpSession session) {
    userService.logout(session);
    return "redirect:/";
  }
  
  @RequestMapping(value="/mypage.do")
  public String mypage(@RequestParam(value="userId", required=false, defaultValue="0") int userId, Model model) {
    if(userId == 0) {
      return "redirect:/";
    }
    model.addAttribute("u", userService.mypage(userId));
    return "user/mypage";
  }
  
  @RequestMapping(value="/modifyInfo.do", method=RequestMethod.POST)
  public String modifyInfo(UserDto userDto, RedirectAttributes redirectAttributes) {
    try {
      redirectAttributes.addFlashAttribute("msg", userService.modifyInfo(userDto));
      return "redirect:/user/mypage.do?userId=" + userDto.getUserId();
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("msg", "회원 정보 변경 실패");
      return "redirect:/";
    }
  }

  @RequestMapping(value="/modifyProfile.do", method=RequestMethod.POST)
  public String modifyProfile(@RequestParam(name="profile") MultipartFile profile  // 첨부 파일은 MultipartFile 타입으로 곧바로 받을 수 있습니다.
                            , @RequestParam(name="userId") int userId
                            , RedirectAttributes redirectAttributes) {
    if(profile.isEmpty()) {  // 프로필을 첨부하지 않고 프로필 변경을 시도한 경우입니다.
      redirectAttributes.addFlashAttribute("msg", "프로필을 선택하세요.");
      return "redirect:/";
    } 
    try {
      redirectAttributes.addFlashAttribute("msg", userService.modifyProfile(profile, userId));
      return "redirect:/user/mypage.do?userId=" + userId;
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("msg", "프로필 변경 실패");
      return "redirect:/";
    }
  }
  
  @RequestMapping(value="/repw.form")
  public String repwForm() {
    return "user/repw";
  }

  @RequestMapping(value="/repw.do", method=RequestMethod.POST)
  public String repw(UserDto userDto, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", userService.modifyPw(userDto));
    return "redirect:/user/mypage.do?userId=" + userDto.getUserId();
  }
  
  @RequestMapping(value="/deleteAccount.do")
  public String deleteAccount(HttpSession session, RedirectAttributes redirectAttributes) {
    int userId = ((UserDto) session.getAttribute("loginUser")).getUserId();
    session.invalidate();
    redirectAttributes.addFlashAttribute("msg", userService.deleteAccount(userId));
    return "redirect:/";
  }
  
}
