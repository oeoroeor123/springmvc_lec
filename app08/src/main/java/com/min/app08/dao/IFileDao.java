package com.min.app08.dao;

import java.util.List;

import com.min.app08.dto.FileDto;

public interface IFileDao {
  
  // 목록 보기
  List<FileDto> selectFileList();
  
  // 파일 추가
  int insertFile(FileDto fileDto);
  
}
