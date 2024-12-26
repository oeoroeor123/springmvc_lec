DROP DATABASE IF EXISTS db_semiProject;
CREATE DATABASE IF NOT EXISTS db_semiProject;

USE db_semiProject;

DROP TABLE IF EXISTS tbl_comment;
DROP TABLE IF EXISTS tbl_blog;
DROP TABLE IF EXISTS tbl_user;

CREATE TABLE IF NOT EXISTS tbl_user
(
 user_id INT AUTO_INCREMENT NOT NULL,
 user_pw VARCHAR(10),
 user_email VARCHAR(100) NOT NULL UNIQUE,
 user_name   VARCHAR(100),
 change_dt DATETIME,
 session_id VARCHAR(100),
 CONSTRAINT pk_user PRIMARY KEY(user_id)
)ENGINE=InnoDB COMMENT '유저';

CREATE TABLE IF NOT EXISTS tbl_blog
(
blog_id INT AUTO_INCREMENT NOT NULL COMMENT '블로그아이디',
user_id INT NOT NULL COMMENT '유저아이디',
title VARCHAR(100) COMMENT '제목',
hit INT COMMENT '조회수',
contents TEXT COMMENT '내용',
create_dt DATETIME COMMENT '작성일자',
modify_dt DATETIME COMMENT '수정일자',
CONSTRAINT pk_blog PRIMARY KEY (blog_id),
CONSTRAINT fk_user_blog FOREIGN KEY (user_id)
REFERENCES tbl_user (user_id) ON DELETE CASCADE
)ENGINE=InnoDB COMMENT '블로그';


CREATE TABLE IF NOT EXISTS tbl_comment
(
comment_id INT AUTO_INCREMENT NOT NULL COMMENT '댓글아이디',
user_id INT NOT NULL COMMENT '유저아이디',
blog_id INT NOT NULL COMMENT '블로그아이디',
contents VARCHAR(1000) COMMENT '내용',
CONSTRAINT pk_comment PRIMARY KEY (comment_id),
CONSTRAINT fk_user_comment FOREIGN KEY (user_id)
REFERENCES tbl_user (user_id) ON DELETE CASCADE,
CONSTRAINT fk_blog_comment FOREIGN KEY (blog_id)
REFERENCES tbl_blog (blog_id) ON DELETE CASCADE
)ENGINE=InnoDB COMMENT '댓글';

INSERT INTO tbl_user VALUES (NULL, '1234', 'admin@naver.com', '관리자', now(), null);
COMMIT;