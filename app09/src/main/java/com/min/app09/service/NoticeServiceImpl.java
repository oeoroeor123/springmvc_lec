package com.min.app09.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.app09.dao.INoticeDao;
import com.min.app09.dto.AttachDto;
import com.min.app09.dto.NoticeDto;
import com.min.app09.util.FileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // controller가 service bean을 주입(DI)해서 사용할 수 있도록 처리 (= @Component)
public class NoticeServiceImpl implements INoticeService {
  
  // service가 dao를 활용, fileUtil도 주입한다.
  private final INoticeDao noticeDao;
  private final FileUtil fileUtil;
  
  @Override
  public List<NoticeDto> getNoticeList() {
    return noticeDao.selectNoticeList(); // db로 부터 목록을 가져와서 그대로 반환한다.
  }
  
  @Override
  public String registNotice(MultipartHttpServletRequest multipartRequest) {
    
    // 1. 공지사항 제목과 내용
    String noticeTitle = multipartRequest.getParameter("noticeTitle");
    String noticeContents = multipartRequest.getParameter("noticeContents");
    
    // db로 보낼 Dto 작업
    NoticeDto noticeDto = NoticeDto.builder()
                               .noticeTitle(noticeTitle)
                               .noticeContents(noticeContents)
                               .build();
    
    // db에 공지사항 등록 (매퍼의 <selectkey>에 의해서 noticeDto의 noticeId 필드에 값이 채워진다.)
    int result = noticeDao.insertNotice(noticeDto);
    if(result == 0)
    return "공지사항 등록 실패";
  
    
    // 2. 첨부 파일 목록 받아서 하나씩 확인
    List<MultipartFile> files = multipartRequest.getFiles("files");
    
    for(MultipartFile multipartFile : files) {
      
      // 첨부 파일 존재 여부 확인
      if(!multipartFile.isEmpty()) { // 파일이 비어있지 않으면 (존재하면)
        
        // 첨부 파일의 원래 이름
        String originalFilename = multipartFile.getOriginalFilename();
        
        // 첨부 파일의 저장할 이름 (fileUtil의 메소드 활용)
        String filesystemName = fileUtil.getFilesystemName(originalFilename);
        
        // 첨부 파일의 저장 경로 (fileUtil의 메소드 활용)
        String filePath = fileUtil.getFilePath();
        // 첨부 파일 저장 경로에 파일이 존재하지 않으면 파일 만들기
        File dir = new File(filePath);
        if(! dir.exists())
          dir.mkdirs();
        
        // 첨부 파일을 하드디스크에 저장
        try {
          // 저장 경로와 이름을 파일 객체로 만들고, transferTo를 이용해 multipartFile로 보낸다.
          multipartFile.transferTo(new File(dir, filesystemName));
          
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        // 첨부 파일 정보를 DB에 저장
        AttachDto attchDto = AttachDto.builder()
                                .noticeId(noticeDto.getNoticeId())
                                .filePath(filePath)
                                .originalFilename(originalFilename)
                                .filesystemName(filesystemName)
                                .build();
        
        int attachResult = noticeDao.insertAttach(attchDto);
        if(attachResult == 0)
          return "첨부파일 등록 실패";
        
      } // for문 위치
    }
   
    // 공지사항 등록과 파일 첨부 모두 성공한 상황
    return "공지사항 등록 성공!";
  }
  
  @Override
  public Map<String, Object> getNoticeById(int noticeId) {
    return Map.of("n", noticeDao.selectNoticeById(noticeId)
                 ,"attachList", noticeDao.selectAttachListByNoticeId(noticeId));
  }
  
  @Override
  public String removeNotice(int noticeId) {
    
    // 첨부된 파일 삭제 (selectAttachListByNoticeId를 가져와야 파일 저장 경로와 이름 확인이 가능)
    for(AttachDto attachDto : noticeDao.selectAttachListByNoticeId(noticeId)) {
      File file = new File(attachDto.getFilePath(), attachDto.getFilesystemName());
      if(file.exists())
        file.delete();
    }
    
    // DB에서 공지사항 삭제 (관련 첨부 파일은 ON DELETE CASCADE 외래 옵션에 의해 함께 삭제된다.)
    int result = noticeDao.deleteNotice(noticeId);
    
    return result == 1 ? "공지사항 삭제 성공" : "공지사항 삭제 실패";
  }
  
  @Override
  public ResponseEntity<Resource> download(int attachId, String userAgent) {
    
    // 다운로드할 첨부 파일의 정보를 DB에서 가져온다.
    AttachDto attachDto = noticeDao.selectAttachById(attachId);
    
    // 다운로드 할 첨부 파일을 Resource 객체로 만들기
    Resource resource = new FileSystemResource(new File(attachDto.getFilePath(), attachDto.getFilesystemName()));
    
    // 다운로드 할 첨부 파일이 없으면 다운로드 취소
    if(!resource.exists())
      return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
    
    // 다운로드 할 첨부 파일의 이름 정하기 (originalFilename 기준, 브라우저마다 다른 인코딩 적용)
    String originalFilename = attachDto.getOriginalFilename();
    try {
      // Edge
      if(userAgent.contains("Edg")) {
        originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
      }
      // Other (IE는 제외)
      else {
        originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 다운로드를 위한 응답 헤더(Response Header) 설정
    HttpHeaders responseHeader = new HttpHeaders();
    try {      
      responseHeader.add("Content-Disposition", "attachment; filename=" + originalFilename);  // 다운로드 응답을 의미합니다. 다운로드 할 파일명을 originalFilename으로 결정합니다.
      responseHeader.add("Content-Type", "application/octet-stream");                         // 응답 데이터의 타입을 의미합니다. 일반 바이너리 파일을 의미합니다.
      responseHeader.add("Content-Length", resource.contentLength() + "");                    // 응답 데이터의 길이를 의미합니다. 바이트 단위로 작성합니다.
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // 다운로드 횟수 증가하기
    noticeDao.updateAttachDownloadCount(attachId);
    
    // 다운로드를 진행하는 ResponseEntity 반환
    return new ResponseEntity<Resource>(resource, responseHeader, HttpStatus.OK);
    
  }
}
