package com.min.myapp.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.myapp.dao.IBlogDao;
import com.min.myapp.dto.BlogDto;
import com.min.myapp.service.IBlogService;
import com.min.myapp.util.PageUtil;

@Service
public class BlogServiceImpl implements IBlogService {

  private IBlogDao blogDao;
  private PageUtil pageUtil;
  
  @Autowired  // Setter 형식의 메소드를 이용한 DI 방식입니다. 매개변수로 bean이 주입되면 필드로 전달됩니다.
  public void prepare(IBlogDao blogDao, PageUtil pageUtil) {
    this.blogDao = blogDao;
    this.pageUtil = pageUtil;
  }
  
  @Override
  public Map<String, Object> getBlogList(HttpServletRequest request) {
    
    // 페이징 처리를 위한 파라미터 page, display
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));

    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("5"));

    // 페이징 처리를 위한 전체 공지 개수
    int total = blogDao.selectBlogCount();
    
    // 페이징 처리에 필요한 모든 변수 처리하기
    pageUtil.setPaging(page, display, total);
    int offset = pageUtil.getOffset();
    
    // 정렬을 위한 파라미터 sort
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");
    
    // 목록 가져오기
    List<BlogDto> blogList = blogDao.selectBlogList(Map.of("offset", offset, "display", display, "sort", sort));

    // 페이지 이동 링크 가져오기
    String paging = pageUtil.getPaging(request.getContextPath() + "/blog/list.do", sort);
    
    // 결과 반환
    return Map.of("blogList", blogList
                , "total", total
                , "paging", paging
                , "offset", offset);  // offset 으로 순번 생성
    
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
