package com.min.myapp.dao;

import java.sql.SQLException;
import java.util.List;

import com.min.myapp.dto.BlogDto;

public interface IBlogDao {
  void connect() throws ClassNotFoundException, SQLException;
  void close() throws SQLException;
  List<BlogDto> selectBlogList();
  int selectBlogCount();
  BlogDto selectBlogById(int blog_id);
  int insertBlog(BlogDto blogDto);
  int updateBlog(BlogDto blogDto);
  int updateHit(int blog_id);
  int deleteBlog(int blog_id);
}