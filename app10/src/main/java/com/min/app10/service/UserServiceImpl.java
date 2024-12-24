package com.min.app10.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.min.app10.dao.IUserDao;
import com.min.app10.dto.UserDto;
import com.min.app10.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final IUserDao userDao;
  private final PageUtil pageUtil;
  
  @Override
  public Map<String, Object> getUserList(HttpServletRequest request) {
    
    // page 파라미터 (디폴트 1) : null이 아니면 page 값을 반환하고, null이면 1을 반환해라.
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    // display 파라미터 (디폴트 20)
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));

    // total 파라미터 (DB에서 전체 목록의 갯수 가져오기)
    int total = userDao.selectUserCount();
    
    // 페이징 처리에 필요한 모든 정보를 처리
    pageUtil.setPaging(page, display, total);
    
    // sort 파라미터 (디폴트 DESC)
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 목록 가져오기 (페이지 처리를 위한 목록 요소 Map으로 부르기)
    List<UserDto> users = userDao.selectUserList(Map.of("offset", pageUtil.getOffset(),
                                                        "display", pageUtil.getDisplay(),
                                                        "sort", sort)); // sort : 쿼리문 자체 키워드로 부르지 않고 바로 사용한다.

    // 페이지 이동 링크 가져오기
    // request.getContextPath() : 자바 파일에서 contextPath 사용해야 할 경우 사용
    String paging = pageUtil.getPaging(request.getContextPath() + "/user/list.do", sort);
    
    // 결과 반환하기 (유저 목록, 전체 인원 수, 페이징 링크, 순번)
    return Map.of("users", users,
                  "total", total,
                  "paging", paging,
                  "offset", pageUtil.getOffset()); // offset = (page - 1) * display; (순번 생성 계산식)
  }

}
