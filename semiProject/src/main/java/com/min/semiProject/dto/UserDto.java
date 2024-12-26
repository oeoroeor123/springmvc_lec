package com.min.semiProject.dto;

import javax.servlet.http.HttpSession;

import com.google.protobuf.Timestamp;

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
    private int userId;
    private String userPw;
    private String userEmail;
    private String userName;
    private Timestamp changeDt;
    private HttpSession sessionId;
  }