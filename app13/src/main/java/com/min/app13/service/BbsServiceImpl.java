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
    return bbsDao.insertBbs(bbsDto) == 1 ? "게시글 작성 성공" : "게시글 작성 실패";
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
    
    // 댓글 업데이트 및 추가
    @Override
    public String registBbsReply(BbsDto bbsDto) {
      
      /*
       * 파라미터 BbsDto bbsDto는 아래 값을 가지고 있습니다.
       *   contents   : 댓글의 내용
       *   depth      : 원글의 depth
       *   groupId    : 원글의 groupId
       *   groupOrder : 원글의 groupOrder
       */
      
      // 1. 기존 댓글의 group_order 업데이트
      bbsDao.updateGroupOrder(bbsDto);
      
      // 2. 댓글 등록
      bbsDto.setDepth(bbsDto.getDepth() + 1); // 댓글의 depth = 원글의 depth + 1
      bbsDto.setGroupId(bbsDto.getGroupId()); // 댓글의 groupId = 원글의 groupId (설명을 위한 코드로, 실제 작성 x)
      bbsDto.setGroupOrder(bbsDto.getGroupOrder() + 1); // 댓글의 groupOrder = 원글의 groupOrder + 1

      return bbsDao.insertBbsReply(bbsDto) == 1 ? "댓글 작성 성공" : "댓글 작성 실패";
    }
    
    // 게시글 삭제하기
    @Override
    public String deleteBbs(int bbsId) {
      return bbsDao.deleteBbs(bbsId) == 1 ? "게시글 삭제 성공" : "게시글 삭제 실패";
    }
    
}
