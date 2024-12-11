package com.min.app07.dao;

import java.util.List;
import java.util.Map;

import com.min.app07.dto.BoardDto;

public interface IBoardDao {
  // 목록 보기
  List<BoardDto> selectBoardList(String sort);
 
  // 갯수 보기
  int selectBoardCount();
  
  // 상세 보기 (pk 값 하나씩 가져다 반환)
  BoardDto selectBoardById(int boardId);
  
  // 검색 기능1 (title, usr_email, usr_name)
  List<BoardDto> selectBoardSearchList(Map<String, Object> map);
  
  // 검색 기능2 (create_dt)
  List<BoardDto> selectBoardPeriodList(Map<String, Object> map);
  
  // 통합 검색
  List<BoardDto> selectBoardIntegratedSearch(Map<String, Object>map);
  int selectBoardIntegratedSearchCount(Map<String, Object> map); 
  
  // 추가, 수정, 삭제 
  int insertBoard(BoardDto boardDto);
  int updateBoard(BoardDto boardDto);
  int deleteBoard(int boardId);

  // array / list / key(Map의 key 값) / field(일반 객체) 전달 가능
  int deleteSelectedBoard(String[] numbers);
  

}
