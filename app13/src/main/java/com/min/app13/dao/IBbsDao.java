package com.min.app13.dao;

import java.util.List;
import java.util.Map;

import com.min.app13.dto.BbsDto;

public interface IBbsDao {

  // 게시글 추가
  int insertBbs (BbsDto bbsDto);
  
  // 게시글 리스트 만들기
  List<BbsDto> selectBbsList(Map<String, Object> map);
  
  // 페이징 처리를 위한 갯수 체크
  int selectBbsCount(); 
  
  // 댓글 업데이트 및 추가
  int updateGroupOrder(BbsDto bbsDto);
  
  int insertBbsReply(BbsDto bbsDto); 

  // 게시글 삭제
  int deleteBbs(int bbsId);
}
