package com.min.myapp.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface INoticeService {
  Map<String, Object> getNoticeList(HttpServletRequest request);
  String registNotice(MultipartHttpServletRequest multipartRequest);
  Map<String, Object> getNoticeById(int noticeId);
  String removeNotice(int noticeId);
  ResponseEntity<Resource> download(int attachId, String userAgent);
  Map<String, Object> getSearchList(HttpServletRequest request);
}
