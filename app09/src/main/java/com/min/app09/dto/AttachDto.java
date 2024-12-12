package com.min.app09.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AttachDto {

  private int attachId;
  private int noticeId;
  private String filePath;
  private String originalFilename;
  private String filesystemName;
}
