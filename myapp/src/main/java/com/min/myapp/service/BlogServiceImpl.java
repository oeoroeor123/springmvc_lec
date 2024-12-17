package com.min.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.myapp.dao.IBlogDao;
import com.min.myapp.dto.BlogDto;

@Service
public class BlogServiceImpl implements IBlogService {

  private IBlogDao blogDao;
  
  @Autowired  // Setter 형식의 메소드를 이용한 DI 방식입니다. 매개변수로 bean이 주입되면 필드로 전달됩니다.
  public void prepare(IBlogDao blogDao) {
    this.blogDao = blogDao;
  }
  
  @Override
  public Map<String, Object> getBlogList() {
    List<BlogDto> blogList = blogDao.selectBlogList();
    int count = blogDao.selectBlogCount();
    return Map.of("blogList", blogList, "count", count);
  }

  @Override
  public int increseBlogHit(int blog_id) {
    return blogDao.updateHit(blog_id);
  }

  @Override
  public BlogDto getBlogById(int blog_id) {
    return blogDao.selectBlogById(blog_id);
  }

  @Override
  public String registerBlog(BlogDto blogDto) {
    return blogDao.insertBlog(blogDto) == 1 ? "블로그 삽입 성공" : "블로그 삽입 실패";
  }

  @Override
  public String modifyBlog(BlogDto blogDto) {
    return blogDao.updateBlog(blogDto) == 1 ? "블로그 수정 성공" : "블로그 수정 실패";
  }

  @Override
  public String removeBlog(int blog_id) {
    return blogDao.deleteBlog(blog_id) == 1 ? "블로그 삭제 성공" : "블로그 삭제 실패";
  }
  
  @Override
  public void txTest() {
   
    /*
     * 트랜잭션은 서비스에서 구현한다 !
     * 트랜잭션 처리가 필요한 경우 ? 
     * 
     * INSERT/UPDATE/DELETE 처리가 2개 이상으로 이루어지는 서비스
     * 모든 INSERT/UPDATE/DELETE를 성공시키거나, 모두 실패시킨다.
     */
    
    // BlogDto1 : 등록 가능한 게시글 정보를 가지고 있다.
    BlogDto blogDto1 = BlogDto.builder()
                          .title("테스트제목1")
                          .contents("테스트콘텐츠1")
                          .user_email("james@gmail.com") // james@gmail.com
                          .build();
    
    // BlogDto2 : 등록할 수 없는 게시글 정보를 가지고 있다.
    BlogDto blogDto2 = BlogDto.builder()
                          .title("테스트제목2")
                          .contents("테스트콘텐츠2")
                          .user_email("") // x
                          .build();
    
    // DB에 등록
    blogDao.insertBlog(blogDto1); // 등록 성공
    blogDao.insertBlog(blogDto2); // 등록 실패
    
    // 확인해야 하는 정보
    // boardDto1과 boardDto2가 모두 등록 실패하면, 트랜잭션 매니저가 정상적으로 동작한 것이다.
  }
}  