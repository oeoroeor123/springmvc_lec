package com.min.myapp.dao;

import java.util.List;

import com.min.myapp.dto.AttachDto;
import com.min.myapp.dto.NoticeDto;

public interface INoticeDao {
  
  // 목록 보기
  List<NoticeDto> selectNoticeList();
  
  // 상세 보기
  NoticeDto selectNoticeById(int noticeId);
  
  // 공지 번호에 의한 첨부 목록 보기
  List<AttachDto> selectAttachListByNoticeId(int noticeId);
  
  // 파일 다운로드
  AttachDto selectAttachById(int attachId);
  
  // NoticeDto에 첨부 파일 관련 칼럼이 없기 때문에 AttacthDto도 함께 활용함
  int insertNotice(NoticeDto noticeDto);
  int insertAttach(AttachDto attachDto);
  
  // delete on cascade가 mysql에서 실행되기 때문에 삭제시엔 noticeDto만 활용하면 됌
  int deleteNotice(int noticeId);
  
  // downloadCount : 다운로드 할 때마다 하나씩 숫자 올리기
  int updateAttachDownloadCount(int attachId);
  
}
