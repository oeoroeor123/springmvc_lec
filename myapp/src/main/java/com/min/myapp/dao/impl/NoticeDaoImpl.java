package com.min.myapp.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.min.myapp.dao.INoticeDao;
import com.min.myapp.dto.AttachDto;
import com.min.myapp.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드의 경우, 생성자 주입만 가능하기에 해당 annotation을 넣음
@Repository // 서비스가 가져다 쓸 수 있도록 해당 annotation을 넣음
public class NoticeDaoImpl implements INoticeDao {

  // root-context에서 만든 SqlSessionTemplate bean을 활용
  private final SqlSessionTemplate template;
  
  // 모든 메소드는 return만 있으면 된다.
  // return 뒤에 template.을 먼저 부른다.
  // 각 메소드에 맞는 함수를 호출 한다.
  // mapper에 있는 namespace + 메소드 이름을 넣고, 파라미터 여부에 따라 뒤에 넣어준다.
  
  @Override
  public List<NoticeDto> selectNoticeList() {
    return template.selectList("mybatis.mappers.noticeMapper.selectNoticeList");
  }
  
  @Override
  public NoticeDto selectNoticeById(int noticeId) {
    return template.selectOne("mybatis.mappers.noticeMapper.selectNoticeById", noticeId);
  }

  @Override
  public List<AttachDto> selectAttachListByNoticeId(int noticeId) {
    return template.selectList("mybatis.mappers.noticeMapper.selectAttachListByNoticeId", noticeId);
  }
  
  @Override
  public AttachDto selectAttachById(int attachId) {
    return template.selectOne("mybatis.mappers.noticeMapper.selectAttachById", attachId);
  }

  @Override
  public int insertNotice(NoticeDto noticeDto) {
    return template.insert("mybatis.mappers.noticeMapper.insertNotice", noticeDto);
  }

  @Override
  public int insertAttach(AttachDto attachDto) {
    return template.insert("mybatis.mappers.noticeMapper.insertAttach", attachDto);
  }

  @Override
  public int deleteNotice(int noticeId) {
    return template.delete("mybatis.mappers.noticeMapper.deleteNotice",noticeId);
  }
  
  @Override
  public int updateAttachDownloadCount(int attachId) {
    return template.update("mybatis.mappers.noticeMapper.updateAttachDownloadCount", attachId);
  }

}
