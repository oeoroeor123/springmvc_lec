package com.min.app10.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.app10.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements IUserDao {

  // root-context에서 만든 SqlSessionTemplate 활용
  private final SqlSessionTemplate template;
  
  @Override
  public List<UserDto> selectUserList(Map<String, Object> map) {  
    return template.selectList("mybatis.mappers.userMapper.selectUserList", map);
  }

  @Override
  public int selectUserCount() {
    return template.selectOne("mybatis.mappers.userMapper.selectUserCount");
  }

}
