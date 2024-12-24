package com.min.myapp.util;

import org.springframework.stereotype.Component;

import lombok.Getter;

/*
 * 모든 목록 서비스에서 사용하는 PageUtil 클래스 
 * 서비스에서 호출하여 활용함
 */

@Component // Spring container에 bean 생성 (서비스가 가져다 쓸 수 있도록)
@Getter
public class PageUtil {

  
  private int page; // 페이지 번호 (요청할 때 전달되는 요청 파라미터, 디폴트 1)
  private int display; // 한 페이지에 표시할 목록의 갯수 (요청 파라미터, 디폴트 20)
  private int offset; // 각 페이지마다 표시할 게시글의 시작 번호 (page와 display에 의해서 계산되는 항목)

  private int count; // 전체 목록의 갯수 (SELECT 문 실행하여 확인 가능)
  private int pageCount; // 전체 페이지 갯수 (total과 display에 의해서 계산되는 항목) ex_total = 1000, display = 20 >> tatalPage = 50;
  private int beginPage; // 각 블록의 시작 페이지 번호 (page와 pagePerBlock에 의해서 계산되는 항목)
  private int endPage; // 각 블록의 종료 페이지 번호 (beginPage와 pagePerBlock과 totalPage에 의해서 계산되는 항목)

  /**
   * 서비스로부터 page와 display와 total의 정보를 받아온 뒤
   * 페이징 처리에 필요한 모든 값을 처리하는 메소드
   * @param page 현재 페이지 번호
   * @param display 현재 페이지에 표시할 목록의 갯수
   * @param total 전체 목록의 갯수
   */
  public void setPaging(int page, int display, int count) {
    
    this.page = page;
    this.display = display;

    // offset 구하는 코드 
    // offset : 데이터의 시작 지점
    // (page - 1) : 페이지가 1부터 시작하기때문에 -1를 해줌
    // * display  : 한 페이지에 노출될 데이터 갯수를 곱해줌
    // ex) offset = (1 - 1) * 10 = 0 >> 1 페이지는 0번 데이터(인덱스)부터 시작한다.
    offset = (page - 1) * display;

    // pagePerBlock : 한 블록당 표시할 페이지 수 (변수 선언 후 자유롭게 정하면 된다. 정해진 값 x)
    // totalPage : 전체 페이지 갯수
    // 1. total / display 계산 시, double 처리하여 실수(소수점까지)로 처리함
    // 2. 실수를 Math.ceil로 올림 처리하여 소수점 자리를 0으로 만듬 
    // 3. int로 캐스팅하여 0자리 소수점 없이 정수를 최종 저장함
    // ex_전체 갯수가 한명 늘어서 1001, 나누기 20하면 50.nnn이 나오게됨 > Math.ceil로 올림 처리하여 51.0로 만듬 > int로 캐스팅하여 최종적으로 51로 저장한다.
    int pagePerBlock = 10;
    pageCount = (int)Math.ceil((double)count / display);
    beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1; // 1페이지 : ((1-1= 0) / 10) * 10 + 1; >> 시작 페이지는 '1' 
    endPage = Math.min(beginPage + pagePerBlock -1, pageCount); // Math.min : 두개 중 작은 값을 endPage로 쓴다.
                                                                // 1페이지 : Math.min(1 + 10 -1 = 10, totalPage(10보다 작을 경우 이 값을 쓰도록 함))
                                                                // 1페이지의 경우, 블록 값은 1~10
  }

  /**
   * 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드
   * @param requestURI 서비스가 동작하는 요청 주소
   * @return 문자열 형식의 페이지 이동 링크
   */
  
  public String getPaging(String requestURI, String sort) {
    // 문자열 연결을 위한 전용 클래스 사용 (StringBuilder)
    StringBuilder builder = new StringBuilder();
    
    // <style></style>
    builder.append("<style>");
    builder.append(".paging { justify-content: space-between; display: flex; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer }");
    builder.append(".paging .disabled-button { color: silver; cursor : auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    // <div class="paging">
    builder.append("<div class=\"paging\">");
    
    // 이전 블록 (<)
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (beginPage - 1) + "&sort=" + sort + "'\">&lt;</button>");
       
    // 1  2  3  4  5  6  7  8  9  10
    // <button type="button" onclick="location.href='/app10/user/list.do?page=1'" class="focus-page">1</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=2'">2</button>
    // <button type="button" onclick="location.href='/app10/user/list.do?page=3'">3</button>
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        // 현재 페이지 코드 값
        builder.append("<button type=\"button\" onclick=\"location.href='"+ requestURI +"?page="+ p + "&sort=" + sort +"'\" class=\"focus-page\">"+ p +"</button>");
      } else {
        builder.append("<button type=\"button\" onclick=\"location.href='"+ requestURI +"?page="+ p + "&sort=" + sort + "'\">"+ p +"</button>");
      }
    }
    
    
    // 다음 블록 (>)
    // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10'">&gt;</button>
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "'\">&gt;</button>");
  
    // </div>
    builder.append("</div>");
    
    return builder.toString();
  } 
  
  /**
   * 호출한 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드
   * @param requestURI  서비스가 동작하는 요청 주소
   * @param column  정렬할 칼럼
   * @param sort  정렬 방식 (ASC 또는 DESC)
   * @return  문자열 형식의 페이지 이동 링크
   */
  
  public String getPaging(String requestURI, String sort, String column) {
    
    StringBuilder builder = new StringBuilder();
    
    builder.append("<style>");
    builder.append(".paging { display: flex; justify-content: center; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer; }");
    builder.append(".paging .disabled-button { color: silver; cursor: auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    builder.append("<div class=\"paging\">");
    
    // 이전 블록
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10&sort=DESC&column=title'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page="  + (beginPage - 1) + "&sort=" + sort + "&column" + column + "'\">&lt;</button>");
  
  //1  2  3  4  5  6  7  8  9  10
  // <button type="button" onclick="location.href='/app10/user/list.do?page=1'" class="focus-page">1</button>
  // <button type="button" onclick="location.href='/app10/user/list.do?page=2'">2</button>
  // <button type="button" onclick="location.href='/app10/user/list.do?page=3'">3</button>
  for(int p = beginPage; p <= endPage; p++) {
    if(p == page) {
      // 현재 페이지 코드 값
      builder.append("<button type=\"button\" onclick=\"location.href='"+ requestURI +"?page="+ p + "&sort=" + sort +"'\" class=\"focus-page\">"+ p +"</button>");
    } else {
      builder.append("<button type=\"button\" onclick=\"location.href='"+ requestURI +"?page="+ p + "&sort=" + sort + "'\">"+ p +"</button>");
    }
  }
 
  // 다음 블록 (>)
  // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
  // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/list.do?page=10'">&gt;</button>
  if(endPage == pageCount)
    builder.append("<button type=\"button\" class=\"\">&gt;</button>");
  else
    builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&sort=" + sort + "'\">&gt;</button>");

  // </div>
  builder.append("</div>");
  
  return builder.toString();
} 
  
  /**
   * 호출한 서비스로 페이지 이동 링크를 문자열 형식으로 반환하는 메소드<br>
   * 검색 화면에서 사용
   * @param requestURI  서비스가 동작하는 요청 주소
   * @param queryString  검색 조건 쿼리 스트링
   * @return  문자열 형식의 페이지 이동 링크
   */
  public String getSearchPaging(String requestURI, String queryString) {
    
    StringBuilder builder = new StringBuilder();
    
    // <style></style>
    builder.append("<style>");
    builder.append(".paging { display: flex; justify-content: center; width: 400px; margin: 0 auto; }");
    builder.append(".paging button { display: block; border: none; background-color: #fff; text-align: center; width: 30px; height: 30px; line-height: 30px; cursor: pointer; }");
    builder.append(".paging .disabled-button { color: silver; cursor: auto; }");
    builder.append(".paging .focus-page { color: limegreen; }");
    builder.append("</style>");
    
    // <div class="paging">
    builder.append("<div class=\"paging\">");
    
    // 이전 블록 <
    // 1. 링크 없음 : <button type="button" class="disabled-button">&lt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/search.do?page=10&queryString'">&lt;</button>
    if(beginPage == 1)
      builder.append("<button type=\"button\" class=\"disabled-button\">&lt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (beginPage - 1) + "&" + queryString + "'\">&lt;</button>");
    
    // 1  2  3  4  5  6  7  8  9  10
    // <button type="button" onclick="location.href='/app10/user/search.do?page=1&queryString'" class="focus-page">1</button>
    // <button type="button" onclick="location.href='/app10/user/search.do?page=2&queryString'">2</button>
    // <button type="button" onclick="location.href='/app10/user/search.do?page=3&queryString'">3</button>
    for(int p = beginPage; p <= endPage; p++) {
      if(p == page) {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&" + queryString + "'\" class=\"focus-page\">" + p + "</button>");
      } else {
        builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + p + "&" + queryString + "'\">" + p + "</button>");
      }
    }
    
    // 다음 블록 >
    // 1. 링크 없음 : <button type="button" class="disabled-button">&gt;</button>
    // 2. 링크 있음 : <button type="button" onclick="location.href='/app10/user/search.do?page=11&queryString'">&gt;</button>
    if(endPage == pageCount)
      builder.append("<button type=\"button\" class=\"disabled-button\">&gt;</button>");
    else
      builder.append("<button type=\"button\" onclick=\"location.href='" + requestURI + "?page=" + (endPage + 1) + "&" + queryString + "'\">&gt;</button>");
    
    // </div>
    builder.append("</div>");
    
    return builder.toString();
    
  }

  
 }
