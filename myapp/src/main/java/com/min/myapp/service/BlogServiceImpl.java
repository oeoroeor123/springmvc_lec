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

}