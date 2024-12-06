package com.min.app06.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.app06.dao.IContactDao;
import com.min.app06.dto.ContactDto;

/* 
 * 작업 순서
 * 1. 요청
 * controller > 필요한 요청과 응답을 작성
 * service > 컨트롤러에서 필요한 작업을 하기위한 코드를 작성 (Dao를 먼저 작업했기때문에, Dao는 호출만 하면 됌)
 * 
 * 2. 응답
 * controller에서 응답 필요한 jsp 파일을 열어서 작업
 * 
 */


@Service // Service가 사용하는 전용 @Component (Spring Container에 bean으로 등록된다.)
         // 비즈니스 계정 (Business Layer)에서 사용
         // Controller가 진행하는 요청과 응답 처리를 뒤에서 받쳐주는 역할을 수행(Controller는 최대한 간략하게 코드 작성하며, 필요한 작업은 여기서 함)
public class ContactServiceImpl implements IContactService {

  // 인터페이스 구현 클래스
  
  @Autowired // DI 필드 주입 (bean 연결) > Dao에 있는 bean을 Service에서 가져다 쓴다.
  // 서비스는 Dao를 가지고 있어야 메소드 호출이 가능
  private IContactDao contactDao;
  
  @Override
  public Map<String, Object> getAllContact() {
    // Dao로부터 연락처 목록 받아오기
    List<ContactDto> contacts = contactDao.getContactList();
    
    // Dao로부터 전체 연락처 갯수 받아오기
    int count = contactDao.getContactCount();
    
    // 연락처 목록과 전체 연락처 갯수 반환하기
    return Map.of("contacts",contacts,"count",count);
  }

  @Override
  public ContactDto getContact(int contact_id) {
    // Dao로부터 연락처 정보 가져오기
    ContactDto contactDto = contactDao.getContactById(contact_id);
    // 가져온 연락처 정보를 반환하기
    return contactDto;
  }

  @Override
  public String register(HttpServletRequest request) {
    // 요청 파라미터 4개를 이용해서 이를 ContactDto로 만들기
    ContactDto contactDto = ContactDto.builder()
                                .last_name(request.getParameter("last_name"))
                                .first_name(request.getParameter("first_name"))
                                .email(request.getParameter("email"))
                                .mobile(request.getParameter("mobile"))
                                .build();
    // ContactDto를 Dao로 전달하고 등록 성공/실패 메시지 만들어서 반환하기
    return contactDao.register(contactDto) == 1? "등록 성공" : "등록 실패";
  }

  @Override
  public String modify(HttpServletRequest request) {
    // 요청 파라미터 5개를 이용해서 이를 ContactDto로 만들기
    ContactDto contactDto = ContactDto.builder()
                                .contact_id(Integer.parseInt(request.getParameter("contact_id")))
                                .last_name(request.getParameter("last_name"))
                                .first_name(request.getParameter("first_name"))
                                .email(request.getParameter("email"))
                                .mobile(request.getParameter("mobile"))
                                .build();
    
    // ContactDto를 Dao로 전달하고 수정 성공/실패 메시지 만들기
    String modifyMsg = contactDao.modify(contactDto) == 1? "수정 성공" : "수정 실패";
    // 수정 성공/실패 메시지 반환하기
    return modifyMsg;
  }

  @Override
  public String remove(HttpServletRequest request) {
    // 요청 파라미터 contact_id를 꺼낸다.
    // contact_id는 <form>태그에 포함되어 있기 때문에 반드시 전달된다. (== null 체크가 필요없다.)
    // 대신 contact_id에 입력된 값이 없는 상태로 전달되면 빈 문자열("")이 전달된다. (== 공백 체크가 필요하다.)
    String str_contact_id = request.getParameter("contact_id");
    int contact_id = 0; // 초기 값은 삭제가 실패할 값을 저장해둔다.
    if(!str_contact_id.isEmpty()) { // 비어있지 않다면 (성공했다면)
     contact_id = Integer.parseInt(str_contact_id); 
    }
    // contact_id를 전달해서 삭제한 뒤, 성공/실패 메시지를 반환한다.
    return contactDao.remove(contact_id) == 1? "삭제 성공" : "삭제 실패";
    
  }

}
