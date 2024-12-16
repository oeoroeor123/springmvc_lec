package com.min.app10.dto;

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
public class UserDto {

  private int id;
  private String firstName;
  private String lastName;
  private String email;
  private String gender;
  private String ipAddress;
  
}
