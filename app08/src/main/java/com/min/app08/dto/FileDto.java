package com.min.app08.dto;

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
public class FileDto {

  private int fileId;
  private String writer;
  private String filePath;
  private String originalFilename;
  private String filesystemName;  
  
}
