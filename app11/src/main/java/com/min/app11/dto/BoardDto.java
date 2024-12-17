package com.min.app11.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BoardDto {
  private int boardId;
  private String title;
  private String contents;
  private Timestamp createDt;
  // private int usrId;
  private UserDto userDto; // 쿼리문에서 INNER JOIN을 통해 UserDto의 칼럼을 사용하여 필드로 지정 (usr_id, usr_email, usr_name)
}
