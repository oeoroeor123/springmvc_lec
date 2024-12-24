package com.min.myapp.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.myapp.dao.IBlogDao;
import com.min.myapp.dto.BlogDto;

import lombok.RequiredArgsConstructor;

@Repository
public class BlogDaoImpl implements IBlogDao {

  private Connection conn;
  private PreparedStatement ps;
  private ResultSet rs;
  
  @Override
  public void connect() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_myapp?serverTimezone=Asia/Seoul", "greenit", "greenit");
  }

  @Override
  public void close() throws SQLException {
    if(conn != null) conn.close();
    if(ps != null) ps.close();
    if(rs != null) rs.close();
  }

  @Override
  public List<BlogDto> selectBlogList(Map<String, Object> map) {
    List<BlogDto> blogList = new ArrayList<BlogDto>();
    try {
      connect();
      String sql = "SELECT blog_id, title, contents, user_email, hit, modify_dt, create_dt FROM tbl_blog ORDER BY blog_id DESC";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, (int)map.get("offset"));
      ps.setInt(2, (int)map.get("display"));
      rs = ps.executeQuery();
      while(rs.next()) {
        BlogDto blogDto = BlogDto.builder()
            .blog_id(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .user_email(rs.getString(4))
            .hit(rs.getInt(5))
            .modify_dt(rs.getTimestamp(6))
            .create_dt(rs.getTimestamp(7))
            .build();
        blogList.add(blogDto);
      }
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blogList;
  }

  @Override
  public int selectBlogCount() {
    int count = 0;
    try {
      connect();
      String sql = "SELECT COUNT(*) FROM tbl_blog";
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();
      if(rs.next()) {
        count = rs.getInt(1);
      }
      close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return count;
  }

  @Override
  public BlogDto selectBlogById(int blog_id) {
    BlogDto blogDto = null;
    try {
      connect();
      String sql = "SELECT blog_id, title, contents, user_email, hit, modify_dt, create_dt FROM tbl_blog WHERE blog_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, blog_id);
      rs = ps.executeQuery();
      if(rs.next()) {
        blogDto = BlogDto.builder()
            .blog_id(rs.getInt(1))
            .title(rs.getString(2))
            .contents(rs.getString(3))
            .user_email(rs.getString(4))
            .hit(rs.getInt(5))
            .modify_dt(rs.getTimestamp(6))
            .create_dt(rs.getTimestamp(7))
            .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return blogDto;
  }

  @Override
  public int insertBlog(BlogDto blogDto) {
    int result = 0;
    try {
      connect();
      String sql = "INSERT INTO tbl_blog VALUES (NULL, ?, ?, ?, 0, NULL, NOW())";
      ps = conn.prepareStatement(sql);
      ps.setString(1, blogDto.getTitle());
      ps.setString(2, blogDto.getContents());
      ps.setString(3, blogDto.getUser_email());
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public int updateBlog(BlogDto blogDto) {
    int result = 0;
    try {
      connect();
      String sql = "UPDATE tbl_blog SET title = ?, contents = ?, modify_dt = NOW() WHERE blog_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, blogDto.getTitle());
      ps.setString(2, blogDto.getContents());
      ps.setInt(3, blogDto.getBlog_id());
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public int updateHit(int blog_id) {
    int result = 0;
    try {
      connect();
      String sql = "UPDATE tbl_blog SET hit = hit + 1 WHERE blog_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, blog_id);
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public int deleteBlog(int blog_id) {
    int result = 0;
    try {
      connect();
      String sql = "DELETE FROM tbl_blog WHERE blog_id = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, blog_id);
      result = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
  
  @Override
  public List<BlogDto> selectBlogList(Map<String, Object> map) {
    return template.selectList("mybatis.mappers.blogMapper.selectBlogList", map);
  }

}
