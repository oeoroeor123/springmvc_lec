
package com.min.myapp.dto;

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
public class BlogDto {
  private int blog_id;
  private String title;
  private String contents;
  private String user_email;
  private int hit;
  private Timestamp modify_dt;
  private Timestamp create_dt;
}
