package com.min.myapp.service.impl;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.myapp.dao.INoticeDao;
import com.min.myapp.dto.AttachDto;
import com.min.myapp.dto.NoticeDto;
import com.min.myapp.dto.UserDto;
import com.min.myapp.service.INoticeService;
import com.min.myapp.util.FileUtil;
import com.min.myapp.util.PageUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements INoticeService {

  private final INoticeDao noticeDao;
  private final FileUtil fileUtil;
  private final PageUtil pageUtil;
  
  @Override
  public Map<String, Object> getNoticeList(HttpServletRequest request) {
    
    // 페이징 처리를 위한 파라미터 page, display
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));

    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("5"));

    // 페이징 처리를 위한 전체 공지 개수
    int total = noticeDao.selectNoticeCount();
    
    // 페이징 처리에 필요한 모든 변수 처리하기
    pageUtil.setPaging(page, display, total);
    int offset = pageUtil.getOffset();
    
    // 정렬을 위한 파라미터 sort, column
    Optional<String> optSort = Optional.ofNullable(request.getParameter("sort"));
    String sort = optSort.orElse("DESC");

    Optional<String> optColumn = Optional.ofNullable(request.getParameter("column"));
    String column = optColumn.orElse("notice_id");
    
    // 목록 가져오기
    List<NoticeDto> noticeList = noticeDao.selectNoticeList(Map.of("offset", offset, "display", display, "sort", sort, "column", column));
    
    // 페이지 이동 링크 가져오기
    String paging = pageUtil.getPaging(request.getContextPath() + "/notice/list.do", sort, column);
    
    // 결과 반환
    return Map.of("noticeList", noticeList
                , "total", total
                , "paging", paging
                , "offset", offset);  // offset 으로 순번 생성
    
  }
  
  @Override
  public String registNotice(MultipartHttpServletRequest multipartRequest) {
    
    // 공지사항 제목과 내용과 작성자
    String noticeTitle = multipartRequest.getParameter("noticeTitle");
    String noticeContents = multipartRequest.getParameter("noticeContents");
    int userId = Integer.parseInt(multipartRequest.getParameter("userId"));
    
    NoticeDto noticeDto = NoticeDto.builder()
                              .noticeTitle(noticeTitle)
                              .noticeContents(noticeContents)
                              .userDto(UserDto.builder()
                                          .userId(userId)
                                          .build())
                              .build();
    
    // DB에 공지사항 등록하기
    int result = noticeDao.insertNotice(noticeDto);  // 매퍼의 <selectKey>에 의해서 noticeDto의 noticeId필드에 값이 채워집니다.
    if(result == 0)
      return "공지사항 등록 실패";
    
    // 첨부 파일 목록 받아서 하나씩 확인하기
    List<MultipartFile> files = multipartRequest.getFiles("files");
    for(MultipartFile multipartFile : files) {
      
      // 첨부 파일 존재 여부 확인
      if(!multipartFile.isEmpty()) {
        
        // 첨부 파일의 원래 이름
        String originalFilename = multipartFile.getOriginalFilename();
        
        // 첨부 파일의 저장 이름
        String filesystemName = fileUtil.getFilesystemName(originalFilename);
        
        // 첨부 파일의 저장 경로
        String filePath = fileUtil.getFilePath();
        File dir = new File(filePath);
        if(!dir.exists())
          dir.mkdirs();
        
        // 첨부 파일을 HDD에 저장
        try {
          multipartFile.transferTo(new File(dir, filesystemName));
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        // 첨부 파일 정보를 DB에 저장
        AttachDto attachDto = AttachDto.builder()
                                  .noticeId(noticeDto.getNoticeId())
                                  .filePath(filePath)
                                  .originalFilename(originalFilename)
                                  .filesystemName(filesystemName)
                                  .build();
        int attachResult = noticeDao.insertAttach(attachDto);
        if(attachResult == 0)
          return "첨부 파일 등록 실패";
        
      }  // for
      
    }
    
    return "공지사항 등록 성공";
    
  }
  
  @Override
  public Map<String, Object> getNoticeById(int noticeId) {
    return Map.of("n", noticeDao.selectNoticeById(noticeId)
                , "attachList", noticeDao.selectAttachListByNoticeId(noticeId));
  }
  
  @Override
  public String removeNotice(int noticeId) {
    
    // 첨부된 파일 삭제
    for(AttachDto attachDto : noticeDao.selectAttachListByNoticeId(noticeId)) {
      File file = new File(attachDto.getFilePath(), attachDto.getFilesystemName());
      if(file.exists())
        file.delete();
    }
    
    // DB에서 공지사항 삭제(관련 첨부 파일은 ON DELETE CASCADE 외래키 옵션에 의해서 함께 삭제)
    int result = noticeDao.deleteNotice(noticeId);
    
    return result == 1 ? "공지사항 삭제 성공" : "공지사항 삭제 실패";
    
  }
  
  @Override
  public ResponseEntity<Resource> download(int attachId, String userAgent) {
    
    // 다운로드 할 첨부 파일의 정보를 DB에서 가져오기
    AttachDto attachDto =  noticeDao.selectAttachById(attachId);
    
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
  
  @Override
  public Map<String, Object> getSearchList(HttpServletRequest request) {
    
    // 검색에 필요한 파라미터
    String noticeTitle = request.getParameter("noticeTitle");
    String noticeContents = request.getParameter("noticeContents");
    String beginDt = request.getParameter("beginDt");
    String endDt = request.getParameter("endDt");
    
    // 페이징 처리에 필요한 파라미터
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));

    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("5"));

    // 검색 키워드들을 Map으로 만듬
    Map<String, Object> map = new HashMap<String, Object>();  // Map.of()는 나중에 값을 추가할 수 없으므로 new HashMap()을 활용합니다.
    map.put("noticeTitle", noticeTitle);
    map.put("noticeContents", noticeContents);
    map.put("beginDt", beginDt);
    map.put("endDt", endDt);

    // 검색 결과 개수
    int searchCount = noticeDao.selectSearchCount(map);
    
    // 페이징 처리에 필요한 모든 변수 처리하기
    pageUtil.setPaging(page, display, searchCount);
    int offset = pageUtil.getOffset();
    
    // 검색키워드 Map에 페이징 처리에 필요한 변수를 추가
    map.put("offset", offset);
    map.put("display", display);
    
    // 검색 목록 가져오기
    List<NoticeDto> searchList = noticeDao.selectSearchList(map);
    
    // 페이지 이동 링크 가져오기
    String paging = pageUtil.getSearchPaging(request.getContextPath() + "/notice/search.do", "noticeTitle=" + noticeTitle + "&noticeContents=" + noticeContents + "&beginDt=" + beginDt + "&endDt=" + endDt);
    
    // 결과 반환
    return Map.of("searchList", searchList
                , "searchCount", searchCount
                , "paging", paging
                , "offset", offset);  // offset 으로 순번 생성;
    
  }

}
