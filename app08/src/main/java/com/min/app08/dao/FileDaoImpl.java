package com.min.app08.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.app08.dto.FileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // SqlSessionTemplate 사용을 위한 annotation
@Repository // Dao에서 사용하는 Component, IFileDao 타입의 bean 생성
public class FileDaoImpl implements IFileDao {

  // root-context에서 만든 SqlSessionTemplate 타입으로 사용
  private final SqlSessionTemplate template;
  
  @Override
  public List<FileDto> selectFileList() {
    return template.selectList("mybatis.mappers.fileMapper.selectFileList"); // mapper namespace + 메소드명 (= 쿼리문의 id)
  }

  @Override
  public int insertFile(FileDto fileDto) {
    return template.insert("mybatis.mappers.fileMapper.insertFile", fileDto);
  }
 

}
