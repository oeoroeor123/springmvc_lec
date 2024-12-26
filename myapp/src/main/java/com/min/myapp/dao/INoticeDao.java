package com.min.myapp.dao;

import java.util.List;
import java.util.Map;

import com.min.myapp.dto.AttachDto;
import com.min.myapp.dto.NoticeDto;

public interface INoticeDao {
  List<NoticeDto> selectNoticeList(Map<String, Object> map);
  int selectNoticeCount();
  NoticeDto selectNoticeById(int noticeId);
  List<AttachDto> selectAttachListByNoticeId(int noticeId);
  AttachDto selectAttachById(int attachId);
  int insertNotice(NoticeDto noticeDto);
  int insertAttach(AttachDto attachDto);
  int deleteNotice(int noticeId);
  int updateAttachDownloadCount(int attachId);
  List<NoticeDto> selectSearchList(Map<String, Object> map);
  int selectSearchCount(Map<String, Object> map);
}
