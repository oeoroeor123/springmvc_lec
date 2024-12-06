package com.min.myapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.myapp.dao.IBlogDao;
import com.min.myapp.dto.BlogDto;

// BlogDaoImpl에서 repository로 만들어 Spring Container에 저장된 bean을 꺼내 쓰기 위해
// 환경 설정 파일로 servlet-context.xml 파일의 위치를 등록한다.
@SpringJUnitConfig(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

class BlogDaoTest {

  @Autowired // DI 필드 주입
  private IBlogDao blogDao; // 구현 클래스는 타입으로 인터페이스를 사용
  
  /*
   * 테스트 수행 메소드
   * @Test
   * void 반환타입
   * 메소드 이름 자유
   * 매개변수 없음
   */
  
  @Test
  void 목록테스트1() {
    assertEquals(2, blogDao.selectBlogList().size());
  }
  
  @Test
  void 상세테스트1() {
    int blog_id = 1;
    assertEquals("라면 끓이기", blogDao.selectBlogById(blog_id).getTitle());
  }
  
  @Test
  void 전체행갯수테스트() {
    assertEquals(2, blogDao.selectBlogCount());
  }
  
  @Test
  void 등록테스트() {
    // 등록할 blogDto 타입 객체 생성
    BlogDto blogDto = BlogDto.builder()
                        .title("호박죽 끓이기")
                        .contents("호박죽은 건강에 좋고 맛도 좋다!")
                        .user_email("pumkin@user.com")
                        .hit(200)
                        .build();
    assertEquals(1, blogDao.insertBlog(blogDto));
  }
  
  @Test
  void 수정테스트1() {
    // 수정할 blogDto 타입 객체 생성
    BlogDto blogDto = BlogDto.builder()
                          .blog_id(2)
                          .title("표고버섯 데치기")
                          .contents("끓는 물에 1분만 데쳐요.")
                          .user_email("cooking@naver.com")
                          .hit(30)
                          .build();
    
    assertEquals(1, blogDao.updateBlog(blogDto));
  }
  
  @Test
  void 삭제테스트1() {
    // 삭제할 blog_id 
    int blog_id = 1;
    assertEquals(1,blogDao.deleteBlog(blog_id));
  }
  
  @Test
  void 목록테스트() {
    // 첫 번째 블로그의 조회 수가 100인지 테스트
    assertEquals(100, blogDao.selectBlogList().get(0).getHit());
  }
  
  @Test
  void 전체개수테스트() {
    // 전체 블로그 개수가 2개인지 테스트
    assertEquals(2, blogDao.selectBlogCount());
  }
  
  @Test
  void 상세테스트() {
    // blog_id = 1인 블로그의 조회 수가 5인지 테스트
    int blog_id = 1;
    assertEquals(5, blogDao.selectBlogById(blog_id).getHit());
  }
  
  @Test
  void 삽입테스트() {
    // 삽입 후 제목이 일치하는지 테스트
    BlogDto blogDto = BlogDto.builder()
        .title("테스트제목")
        .contents("테스트내용")
        .user_email("테스트이메일")
        .build();
    blogDao.insertBlog(blogDto);
    assertEquals("테스트제목", blogDao.selectBlogList().get(0).getTitle());
  }
  
  @Test
  void 수정테스트() {
    // 수정 후 제목이 일치하는지 테스트
    int blog_id = blogDao.selectBlogList().get(0).getBlog_id();
    BlogDto blogDto = BlogDto.builder()
        .title("수정_테스트제목")
        .contents("수정_테스트내용")
        .blog_id(blog_id)
        .build();
    blogDao.updateBlog(blogDto);
    assertEquals("수정_테스트제목", blogDao.selectBlogById(blog_id).getTitle());
  }
  
  @Test
  void 조회수테스트() {
    // 조회수가 0에서 1으로 증가하는지 테스트
    int blog_id = blogDao.selectBlogList().get(0).getBlog_id();
    blogDao.updateHit(blog_id);
    assertEquals(1, blogDao.selectBlogById(blog_id).getHit());
  }
  
  @Test
  void 삭제테스트() {
    // 삭제 결과가 1인지 테스트
    int blog_id = blogDao.selectBlogList().get(0).getBlog_id();
    assertEquals(1, blogDao.deleteBlog(blog_id));
  }
  
  
  
  
}
