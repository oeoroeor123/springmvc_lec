package com.min.myapp.service;

import java.util.Map;

import com.min.myapp.dto.BlogDto;

public interface IBlogService {

  // 목록 조회 서비스
  Map<String, Object> getBlogList();
  
  // 조회수 늘리기 서비스
  int increseBlogHit(int blog_id);
    
  // 상세 조회 서비스
  BlogDto getBlogById(int blog_id);
  
  // 등록,수정,삭제 서비스
  String registerBlog(BlogDto blogDto);
  String modifyBlog(BlogDto blogDto);
  String removeBlog(int blog_id);
  
}
