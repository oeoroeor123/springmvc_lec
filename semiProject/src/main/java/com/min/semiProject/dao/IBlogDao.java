package com.min.semiProject.dao;

import java.util.List;
import java.util.Map;

import com.min.semiProject.dto.BlogDto;

public interface IBlogDao {

  // 목록
  List<BlogDto> selectBlogList(Map<String, Object> map);
  
  // 목록 개수
  int selectBlogCount();
}
