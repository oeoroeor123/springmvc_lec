package com.min.app12.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.min.app12.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements IUserDao {

  private final SqlSessionTemplate template;
  
  // logger 지정 (아래 코드는 lombok에서 @Slf4j Annotation으로 지원한다.)
  // private final static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
  // AOP 방식을 이용해 log가 반복될때마다 실행될 수 있도록 사전에 세팅한다.
  
  @Override
  public int insertUser(UserDto userDto) {
    // sysout 대신 log를 사용해 확인한다, 인자값은 %msg로 표시된다.
    log.debug(userDto.toString()); 
    return template.insert("mybatis.mappers.userMapper.insertUser", userDto);
  }
  
  @Override
  public UserDto selectUserByMap(Map<String, Object> map) {
    log.debug(map.toString()); // 인자값은 %msg로 표시된다.
    return template.selectOne("mybatis.mappers.userMapper.selectUserByMap", map);
  }

}
