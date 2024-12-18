package com.min.app12.dto;

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
  private UserDto userDto;
  // UserDto 타입을 포함하여 해당 테이블 칼럼들 가져오기

}
