package com.min.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.min.app13.dto.BbsDto;

public interface IBbsService {
 
 // 게시글 작성
 String registBbs(BbsDto bbsDto);
 
 // select문은 메소드 이름을 get으로 지정한다. (추후 트렌젝션에서 get으로 시작하는 메소드는 제외하고 실행하기 위해)
 Map<String, Object> getBbsList(HttpServletRequest request);
 
 // 댓글 업데이트 및 추가
 String registBbsReply(BbsDto bbsDto);
 
 // 게시글 삭제
 String deleteBbs(int bbsId);
}
