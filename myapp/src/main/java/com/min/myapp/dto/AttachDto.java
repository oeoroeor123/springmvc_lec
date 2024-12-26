package com.min.myapp.dto;

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
public class AttachDto {
  private int attachId;
  private int noticeId;
  private String filePath;
  private String originalFilename;
  private String filesystemName;
  private int downloadCount;
}
