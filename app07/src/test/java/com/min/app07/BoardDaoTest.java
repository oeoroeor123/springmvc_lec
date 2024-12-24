package com.min.app07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.app07.dao.IBoardDao;
import com.min.app07.dto.BoardDto;
import com.min.app07.dto.UserDto;

/*
 * IBoardDao 타입의 구현체 BoardDaoImpl bean은 @repository를 이용해 생성하였으므로 Component Scan이 정의되어 있는 servlet-context.xml 파일이 필요하다.
 * BoardImpl 클래스의 필드인 SqlSessionTemplate bean은 root-context.xml 파일에 <bean> 태그를 이용해 생성하였다.
 */
@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"
                               ,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) 

class BoardDaoTest {

  // Spring Container에 저장된 IBaordDao 타입의 bean을 가져온다.
  @Autowired
  private IBoardDao boardDao;
  
  
  @Test
  void 목록테스트() {
    // 두 번째 항목의 contents="마라탕, 마라샹궈"인지 테스트
    // assertEquals(true, boardDao.selectBoardList().get(1).getContents().startsWith("마라탕"));
    String sort = "DESC";
    assertEquals(true, boardDao.selectBoardList(sort).get(2).getContents().startsWith("김치찌개"));
    
    // 두 번째 항목의 usr_name="james"인지 테스트
    // assertEquals("james", boardDao.selectBoardList().get(1).getUserDto().getUsrName());
    // assertEquals("tom", boardDao.selectBoardById(3).getUserDto().getUsrName());
    // assertEquals("james",boardDao.selectBoardById(2).getUserDto().getUsrName());
  }
  
  @Test
  void 검색테스트() {
    // title에 10이 포함되는지 테스트
    // String query = "10";
    // assertEquals("20241210_식단", boardDao.selectBoardSearchList(query).get(0).getTitle());
    
    // title에 10이 포함되는지 테스트 (Map.of(key,value) 순서)
    Map<String, Object> map = Map.of("column", "title", "query", "10");
    assertEquals("20241210_식단",boardDao.selectBoardSearchList(map).get(0).getTitle());
  }
  
  
  @Test
  void 기간테스트() {
    String beginDt = "2024-12-05";
    String endDt = "2024-12-06";
    Map<String, Object> map = Map.of("beginDt", beginDt, "endDt", endDt);
    assertEquals(2, boardDao.selectBoardPeriodList(map).size());
  }
  
  
  @Test
  void 통합검색테스트() {
    String title=""; // 빈 문자열 (NULL이 아님, NULL이 필요한 경우 "" 대신 NULL을 넣는다.
    String usrEmail="@";
    String usrName="";
    String beginDt="";
    String endDt="";
    Map<String, Object> map = Map.of("title",title, "usrEmail", usrEmail, "usrName", usrName, "beginDt", beginDt, "endDt", endDt);
     assertEquals(3, boardDao.selectBoardIntegratedSearch(map).size());
  }
  
  @Test
  void 수정테스트() {
    BoardDto boardDto = new BoardDto();
    // boardDto.setTitle("20241210_식단(2)");
    boardDto.setContents("짜장면, 탕수육, 난자완스");
    boardDto.setBoardId(2);   
    
    assertEquals(1, boardDao.updateBoard(boardDto));
  }
  
  @Test
  void 선택항목삭제테스트() {
   String[] numbers = {"1","2","3"};
   assertEquals(numbers.length, boardDao.deleteSelectedBoard(numbers)); // 삭제된 행의 갯수를 확인한다.
  }
  
  @Test
  void 삽입테스트() {
    // 삽입 이전 BoardDto 객체는 title, contents, usrId 값을 가진다.
    BoardDto boardDto = new BoardDto();
    boardDto.setTitle("20241212_식단");
    boardDto.setContents("스시, 냉모밀, 텐동");
    boardDto.setUserDto(new UserDto(3, null, null));
    System.out.println("삽입 이전 : " + boardDto);
    
    // 삽입
    assertEquals(1, boardDao.insertBoard(boardDto));
    
    // 삽입 이후 BoardDto 객체는 title, contents, usrId, boardId 값을 가진다.
    // boardId 값은 매퍼의 <selectKey> 태그가 넣어준 것이다.
    System.out.println("삽입 이후 : " + boardDto);
  }
  
}
