package com.min.semiProject.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IBlogService {

  Map<String, Object> getBlogList(HttpServletRequest request);
}
