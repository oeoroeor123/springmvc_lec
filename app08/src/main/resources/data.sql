DROP DATABASE IF EXISTS db_app08;
CREATE DATABASE IF NOT EXISTS db_app08;

USE db_app08;

DROP TABLE IF EXISTS tbl_file;
CREATE TABLE IF NOT EXISTS tbl_file
(
    file_id INT AUTO_INCREMENT COMMENT '파일아이디',
    writer VARCHAR(100) COMMENT '작성자',
    file_path VARCHAR(300) COMMENT '파일저장경로',
    original_filename VARCHAR(300) COMMENT '파일원래이름',
    filesystem_name VARCHAR(40) COMMENT '파일저장이름',
    CONSTRAINT pk_file PRIMARY KEY(file_id)
)ENGINE=InnoDB COMMENT='파일';