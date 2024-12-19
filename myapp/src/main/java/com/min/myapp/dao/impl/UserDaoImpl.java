package com.min.myapp.dao.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.myapp.dao.IUserDao;
import com.min.myapp.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements IUserDao {

  private final SqlSessionTemplate template;
  
  @Override
  public int insertUser(UserDto userDto) {
    return template.insert("mybatis.mappers.userMapper.insertUser", userDto);
  }
  
  @Override
  public UserDto selectUserByMap(Map<String, Object> map) {
    return template.selectOne("mybatis.mappers.userMapper.selectUserByMap", map);
  }
  
  @Override
  public int updateUserInfo(UserDto userDto) throws Exception {
    return template.update("mybatis.mappers.userMapper.updateUserInfo", userDto);
  }
  
  @Override
  public int updateUserProfile(UserDto userDto) {
    return template.update("mybatis.mappers.userMapper.updateUserProfile", userDto);
  }
  
  @Override
  public int updateUserPassword(UserDto userDto) {
    return template.update("mybatis.mappers.userMapper.updateUserPassword", userDto);
  }
  
  @Override
  public int deleteUser(int userId) {
    return template.delete("mybatis.mappers.userMapper.deleteUser", userId);
  }
 

}
