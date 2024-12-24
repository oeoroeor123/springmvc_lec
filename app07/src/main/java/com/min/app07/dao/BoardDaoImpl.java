package com.min.app07.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.app07.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 필드에 final 처리 후 초기화 할 때, 반드시 값을 받아와서 채워줘야 한다는 의미의 해당 annotation을 필수로 사용한다.
@Repository // DAO에서 사용하는 @component > Spring Container에 IBoardDao 타입의 bean이 1개 (디폴트 : scope = singleton)생성된다.
public class BoardDaoImpl implements IBoardDao {

  // root-context에서 만든 SqlSessionTemplate 타입으로 사용
  private final SqlSessionTemplate template;
  
    /* 생성자 주입은 매개변수 타입으로 bean이 주입되고, 필드로 전달된다.
     * @Autowired 생략 가능 > 생성자 주입 시 반드시 매개변수 값을 받아와서 채워줘야 하기에, @NonNull이 들어가야 안전하다.
     ** ************************ **
     ** @RequiredArgsConstructor **
     ** ************************ **
      @Autowired
      public BoardDaoImpl(@NonNull SqlSessionTemplate template) {
        super();
        this.template = template;
      }
    */
  
    /*
     * 매퍼의 SQL 구문 호출하는 방법
     * 모든 메소드는 SQL 아이디가 첫 번째로 불러지며, Parameter 존재 여부에 따라 선택적으로 부른다.
     * selectList(SQL's id [, Parameter])
     *  selectOne(SQL's id [, Parameter])
     *     insert(SQL's id [, Parameter])
     *     update(SQL's id [, Parameter])
     *     delete(SQL's id [, Parameter])
     */
  
     // boardMapper의 각 쿼리문과 비교하면서 코드 확인 필요 !
     // template = 매퍼의 SQL문을 실행하는 Java 객체
     // 쿼리문 아이디 = 메소드명 (동일)
     // Mapper에서 지정한 namespace.(+ SQL 아이디)를 넣어 id 간의 충돌을 방지한다.

  @Override
  public List<BoardDto> selectBoardList(String sort) {
    List<BoardDto> boardList = template.selectList("mybatis.mappers.boardMapper.selectBoardList", sort); // 쿼리문 아이디 = 메소드명 (동일)
    return boardList;
  }

  @Override
  public int selectBoardCount() {
    int boardCount = template.selectOne("mybatis.mappers.boardMapper.selectBoardCount"); // 모든 집계 함수(Count)의 결과는 하나다.
    return boardCount;
  }

  @Override
  public BoardDto selectBoardById(int boardId) {
    BoardDto boardDto = template.selectOne("mybatis.mappers.boardMapper.selectBoardById", boardId); // pk의 동등 조건으로 결과는 있거나/ 없거나 하나만 반환된다.
    return boardDto;
  }
  
  @Override
  public List<BoardDto> selectBoardSearchList(Map<String, Object> map) {
    List<BoardDto> searchList = template.selectList("mybatis.mappers.boardMapper.selectBoardSearchList", map);
    return searchList;
  }
  
  @Override
  public List<BoardDto> selectBoardPeriodList(Map<String, Object> map) {
    List<BoardDto> periodList = template.selectList("mybatis.mappers.boardMapper.selectBoardPeriodList", map);
    return periodList;
  }
  
  @Override
  public List<BoardDto> selectBoardIntegratedSearch(Map<String, Object> map) {
    List<BoardDto> list = template.selectList("mybatis.mappers.boardMapper.selectBoardIntegratedSearch", map);
    return list;
  }
  
  @Override
  public int selectBoardIntegratedSearchCount(Map<String, Object> map) {
    int boardCount = template.selectOne("mybatis.mappers.boardMapper.selectBoardIntegratedSearchCount", map);
    return boardCount;
  }

  @Override
  public int insertBoard(BoardDto boardDto) {
    int result = template.insert("mybatis.mappers.boardMapper.insertBoard", boardDto);
    return result;
  }

  @Override
  public int updateBoard(BoardDto boardDto) {
    int result = template.update("mybatis.mappers.boardMapper.updateBoard", boardDto);
    return result;
  }

  @Override
  public int deleteBoard(int boardId) {
    int result = template.delete("mybatis.mappers.boardMapper.deleteBoard", boardId);
    return result;
  }
  
  @Override
  public int deleteSelectedBoard(String[] numbers) {
    int result = template.delete("mybatis.mappers.boardMapper.deleteSelectedBoard", numbers);
    return result;
  }

}
