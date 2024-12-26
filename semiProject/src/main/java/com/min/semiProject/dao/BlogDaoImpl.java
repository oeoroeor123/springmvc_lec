package com.min.semiProject.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.semiProject.dto.BlogDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BlogDaoImpl implements IBlogDao {
  
  // SqlSession 활용
  private SqlSessionTemplate template;
 
  @Override
  public List<BlogDto> selectBlogList(Map<String, Object> map) {
    return template.selectList("mybatis.mappers.blogMapper.selectBlogList", map);
  }
  
  @Override
  public int selectBlogCount() {
    return template.selectOne("mybatis.mappers.blogMapper.selectBlogCount");
  }
  
  
}
