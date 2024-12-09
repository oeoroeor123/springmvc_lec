package com.min.app07.dao;

import java.util.List;

import com.min.app07.dto.BoardDto;

public interface IBoardDao {
  // 목록 보기
  List<BoardDto> selectBoardList();
  
  // 갯수 보기
  int selectBoardCount();
  
  // 상세 보기 (pk 값 하나씩 가져다 반환)
  BoardDto selectBoardById(int boardId);
  
  // 추가, 수정, 삭제 
  int insertBoard(BoardDto boardDto);
  int updateBoard(BoardDto boardDto);
  int deleteBoard(int boardId);
  
  
  
  
}
