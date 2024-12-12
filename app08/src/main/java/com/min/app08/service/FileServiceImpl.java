package com.min.app08.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.app08.dao.IFileDao;
import com.min.app08.dto.FileDto;
import com.min.app08.util.FileUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // controller가 가져다 쓸 수 있도록 service 전용 component 지정
public class FileServiceImpl implements IFileService {

  private final IFileDao fileDao;
  private final FileUtil fileUtil;
  
  @Override
  public List<FileDto> getFileList() {
    return fileDao.selectFileList();
  }
 
  @Override
  public String uploadFile(MultipartHttpServletRequest multipartRequest) {
    
    // list.jsp 파일에서 만든 form 태그 내 input 태그들의 name을 파라미터 명으로 준다.
    // 일반 요청은 getParameter로 동일하게 주고, 첨부 파일은 getFile로 지정한다.
    
    // 일반 요청 파라미터
    String writer = multipartRequest.getParameter("writer");
    
    // 데이터베이스로 전달하기 위해 DB로 보낼 파일 FileDto 객체 생성
    FileDto fileDto = new FileDto();
    fileDto.setWriter(writer); // 작성자 정보 DB로 전송

    // 첨부 파일 파라미터 (MultipartFile 타입으로 받는다.)
    MultipartFile multipartFile = multipartRequest.getFile("file");

    // 첨부 파일이 존재하는지 확인
    if(!multipartFile.isEmpty()) { // 파일이 존재한다.
      
      // 첨부 파일의 원래 이름
      String originalFilename = multipartFile.getOriginalFilename();
      
      // 첨부 파일이 저장 이름
      String filesystemName=fileUtil.getFilesystemName(originalFilename);
      
      // 첨부 파일의 저장 경로
      String filepath = fileUtil.getFilePath();
      // 파일이 없으면 만드는 코드
      File dir = new File(filepath);
      if(!dir.exists())
        dir.mkdirs();
    
      // 첨부 파일을 하드디스크(HDD)에 저장
      try {
        multipartFile.transferTo(new File(dir, filesystemName)); // new File(저장할 경로, 저장할 이름)
      } catch (Exception e) {
        e.printStackTrace();
      }
      
      // DB로 보낼 FileDto에 파일 정보 추가하기
      fileDto.setFilePath(filepath);
      fileDto.setOriginalFilename(originalFilename);
      fileDto.setFilesystemName(filesystemName);  
    }
    
      // DB로 FileDto 보내서 저장한 뒤, 결과 메시지 반환하기    
    return fileDao.insertFile(fileDto) == 1? "삽입 성공" : "삽입 실패";
  }

}
