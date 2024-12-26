package com.min.semiProject.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BlogDto {
  private int blogId;
  private String userId;
  private String title;
  private int hit;
  private String contents;
  private Timestamp createDt;
  private Timestamp modifyDt;
}
