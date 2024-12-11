package com.min.app07.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.app07.dto.BoardDto;


public interface IBoardService {

  // 서비스는 컨트롤러가 넘겨주는 작업들을 수행한다.
  
  Map<String, Object> getBoardList(HttpServletRequest request);
  BoardDto getBoardById(int boardId);
  String mobifyBoard(BoardDto boardDto);
  String removeBoard(int boardId);
  String removeBoardList(String[] numbers);
  String registerBoard(BoardDto boardDto);
  Map<String, Object> getSearchList(HttpServletRequest request);

}
