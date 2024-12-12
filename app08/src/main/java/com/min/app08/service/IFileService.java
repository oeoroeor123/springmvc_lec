package com.min.app08.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.app08.dto.FileDto;

public interface IFileService {
  
  // 목록 반환
  List<FileDto> getFileList();
  
  // 업로드 실패/성공 메시지 반환
  String uploadFile(MultipartHttpServletRequest multipartRequest);
}
