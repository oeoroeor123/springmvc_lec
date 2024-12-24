package com.min.app13.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.min.app13.dao.IBbsDao;
import com.min.app13.dto.BbsDto;
import com.min.app13.util.PageUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BbsServiceImpl implements IBbsService {
  
  private final IBbsDao bbsDao;
  private final PageUtil pageUtil;
 
  @Override
  public String registBbs(BbsDto bbsDto) {
    return bbsDao.insertBbs(bbsDto) == 1 ? "작성 성공" : "작성 실패";
  }
  
  @Override
  public Map<String, Object> getBbsList(HttpServletRequest request) {
    
    // 페이징 처리를 위해 필요한 3가지 (page, display, offset)
    // page, display : 파라미터로 가져오기 offset: 변수 처리로 가져오기
     
    
    // page 초기값은 1 (파라미터로 가져오기)
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    // display 초기값은 20 (파라미터로 가져오기)
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));    
    
    // 개수 구하기
    int count = bbsDao.selectBbsCount();

    // pageUtil에 있는 값들 가져오기
    pageUtil.setPaging(page, display, count);
    
    // offset 변수처리 
    int offset = pageUtil.getOffset();
    
    List<BbsDto> bbsList = bbsDao.selectBbsList(Map.of("offset", offset, "display", display));
    
    // 페이지 가져오기
    String paging = pageUtil.getPaging(request.getContextPath() + "/bbs/list.do", "");
    
    return Map.of("offset", offset, "count", count, "bbsList", bbsList, "paging", paging);
    
  }

}
