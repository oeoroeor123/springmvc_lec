package com.min.myapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.min.myapp.dao.IBlogDao;
import com.min.myapp.dto.BlogDto;

@SpringJUnitConfig(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
class BlogDaoTest {

  @Autowired
  private IBlogDao blogDao;
  
  @Test
  void 목록테스트() {
    // 첫 번째 블로그의 조회 수가 100인지 테스트
    assertEquals(100, blogDao.selectBlogList(Map.of("offset", 0, "display", 5)).get(0).getHit());
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
    assertEquals("테스트제목", blogDao.selectBlogList(Map.of("offset", 0, "display", 5)).get(0).getTitle());
  }
  
  @Test
  void 수정테스트() {
    // 수정 후 제목이 일치하는지 테스트
    int blog_id = blogDao.selectBlogList(Map.of("offset", 0, "display", 5)).get(0).getBlog_id();
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
    int blog_id = blogDao.selectBlogList(Map.of("offset", 0, "display", 5)).get(0).getBlog_id();
    blogDao.updateHit(blog_id);
    assertEquals(1, blogDao.selectBlogById(blog_id).getHit());
  }
  
  @Test
  void 삭제테스트() {
    // 삭제 결과가 1인지 테스트
    int blog_id = blogDao.selectBlogList(Map.of("offset", 0, "display", 5)).get(0).getBlog_id();
    assertEquals(1, blogDao.deleteBlog(blog_id));
  }

}