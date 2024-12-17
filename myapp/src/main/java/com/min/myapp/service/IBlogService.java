package com.min.myapp.service;

import java.util.Map;

import com.min.myapp.dto.BlogDto;

public interface IBlogService {
  Map<String, Object> getBlogList();
  int increseBlogHit(int blog_id);
  BlogDto getBlogById(int blog_id);
  String registerBlog(BlogDto blogDto);
  String modifyBlog(BlogDto blogDto);
  String removeBlog(int blog_id);
  void txTest();
}
