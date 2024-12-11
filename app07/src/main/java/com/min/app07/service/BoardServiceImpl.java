package com.min.app07.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.min.app07.dao.IBoardDao;
import com.min.app07.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 필드로 선언된 IBoardDao boardDao에 자동으로 bean을 주입하기 위한 생성자
@Service // 서비스에서 사용하는 @component이다, Spring Container에 IBoardService 타입의 bean이 생성된다.
public class BoardServiceImpl implements IBoardService {
  
  // 필드 선언 시, 타입은 인터페이스 타입을 사용한다. 
  // Dao에서 만든 bean을 service에서 사용한다.
  private final IBoardDao boardDao;
  
  @Override
  public Map<String, Object> getBoardList(HttpServletRequest request) {
   
    // 요청 파라미터 (sort)가 전달되지 않으면 DESC를 디폴트로 사용한다.
    Optional<String> opt = Optional.ofNullable(request.getParameter("sort"));
    String sort = opt.orElse("DESC");
   
    // 목록 가져오기
    List<BoardDto> boardList = boardDao.selectBoardList(sort);
    
    // 전체 목록의 갯 수 가져오기
    int boardCount = boardDao.selectBoardCount();
    
    // 목록과 전체 목록의 갯 수는 Map으로 반환하기    
    return Map.of("boardList",boardList,"boardCount",boardCount);
  }
  
  @Override
  public BoardDto getBoardById(int boardId) {
    
    // 상세 정보를 받아와서 controller로 반환한다.
    return boardDao.selectBoardById(boardId);
  }
  
  @Override
  public String mobifyBoard(BoardDto boardDto) {
    
    // 수정한 뒤 수정 결과를 텍스트로 반환한다. 
    return boardDao.updateBoard(boardDto) == 1 ? "수정 성공" : "수정 실패";
  }
  
  @Override
  public String removeBoard(int boardId) {
    
    // 삭제한 뒤 삭제 결과를 텍스트로 반환한다.
    return boardDao.deleteBoard(boardId) == 1 ? "삭제 성공" : "삭제 실패";
  }
  
  @Override
  public String removeBoardList(String[] numbers) {
    
    // 삭제한 뒤 삭제 결과를 텍스트로 반환한다.
    return boardDao.deleteSelectedBoard(numbers) == numbers.length ? "선택 삭제 성공" : "선택 삭제 실패";
  }
  
  @Override
  public String registerBoard(BoardDto boardDto) {
    
    // 삽입한 뒤 삽입 결과를 텍스트로 반환한다.
    return boardDao.insertBoard(boardDto) == 1 ? "삽입 성공" : "삽입 실패";
  }
  
  @Override
  public Map<String, Object> getSearchList(HttpServletRequest request) {
    
    // 요청 파라미터를 map으로 만든다.
    Map<String, Object> param = Map.of("title", request.getParameter("title"),
                                       "usrEmail", request.getParameter("usrEmail"),
                                       "usrName", request.getParameter("usrName"),
                                       "beginDt", request.getParameter("beginDt"),
                                       "endDt", request.getParameter("endDt"));
    
    // 검색 결과 목록을 가져온다.
    List<BoardDto> boardList = boardDao.selectBoardIntegratedSearch(param);
    
    // 검색 결과 갯수를 가져온다.
    int boardCount = boardDao.selectBoardIntegratedSearchCount(param);
    
    // 검색 결과 목록과 갯수를 반환한다.
    return Map.of("boardList", boardList, "boardCount", boardCount);
  }
  
  

}
