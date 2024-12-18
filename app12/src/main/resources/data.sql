DROP DATABASE IF EXISTS db_app12;
CREATE DATABASE IF NOT EXISTS db_app12;

USE db_app12;

DROP TABLE IF EXISTS tbl_board;
DROP TABLE IF EXISTS tbl_user;

CREATE TABLE IF NOT EXISTS tbl_user
(
  user_id    INT AUTO_INCREMENT,
  user_email VARCHAR(100) NOT NULL UNIQUE,
  user_pw    VARCHAR(64) NOT NULL COMMENT 'SHA-256암호화 비밀번호',
  user_name  VARCHAR(100),
  CONSTRAINT pk_user PRIMARY KEY (user_id)
)ENGINE='InnoDB' COMMENT='사용자';

CREATE TABLE IF NOT EXISTS tbl_board
(
  board_id INT AUTO_INCREMENT,
  title    VARCHAR(100) NOT NULL,
  user_id  INT,
  CONSTRAINT pk_board PRIMARY KEY (board_id),
  CONSTRAINT fk_user_board FOREIGN KEY (user_id)
  REFERENCES tbl_user (user_id) ON DELETE CASCADE
)ENGINE='InnoDB' COMMENT='게시판';