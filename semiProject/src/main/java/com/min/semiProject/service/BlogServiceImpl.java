package com.min.semiProject.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.min.semiProject.dao.IBlogDao;
import com.min.semiProject.dto.BlogDto;
import com.min.semiProject.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements IBlogService {

  private final IBlogDao blogDao;
  private final PageUtil pageUtil;
  
  @Override
  public Map<String, Object> getBlogList(HttpServletRequest request) {
    
    // 페이징 처리를 위한 각 요소 초기값 주기
    // page : 1
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
 
    // display : 10
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("10"));
    
    // total
    int total = blogDao.selectBlogCount();
    
    // 페이징을 위한 요소 정보 처리
    pageUtil.setPaging(page, display, total);
    
    // sort 파라미터 (디폴트 DESC)
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 목록 가져오기 (Map으로 페이지 처리를 위한 목록 부르기)
    List<BlogDto> users = blogDao.selectBlogList(Map.of("offset", pageUtil.getOffset(), "display", pageUtil.getDisplay(), "sort", sort));
    
    // 페이지 이동 링크 가져오기
    String paging = pageUtil.getPaging(request.getContextPath() + "/user/list.do", sort);
    
    // 결과 반환 (유저 목록, 전체 인원 수, 페이징 링크, 순번)
    return Map.of("users", users, "total", total, "paging", paging, "offset", pageUtil.getOffset());
  }

}
