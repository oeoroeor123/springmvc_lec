DROP DATABASE IF EXISTS db_myapp;
CREATE DATABASE IF NOT EXISTS db_myapp;
USE db_myapp;

DROP TABLE IF EXISTS tbl_attach;
DROP TABLE IF EXISTS tbl_notice;
DROP TABLE IF EXISTS tbl_blog;
DROP TABLE IF EXISTS tbl_user;

CREATE TABLE IF NOT EXISTS tbl_user
(
    user_id    INT AUTO_INCREMENT,
    user_email VARCHAR(100) NOT NULL UNIQUE,
    user_pw    VARCHAR(64) NOT NULL COMMENT 'SHA-256 암호화 비밀번호',
    user_name  VARCHAR(100),
    CONSTRAINT pk_user PRIMARY KEY (user_id)
) ENGINE='InnoDB' COMMENT='사용자';

CREATE TABLE IF NOT EXISTS tbl_blog
(
    blog_id    INT AUTO_INCREMENT COMMENT '블로그아이디',
    title      VARCHAR(1000) NOT NULL COMMENT '제목',
    contents   TEXT COMMENT '내용',
    user_email VARCHAR(100) NOT NULL COMMENT '작성자이메일',
    hit        INT COMMENT '조회수',
    modify_dt  DATETIME COMMENT '수정일시',
    create_dt  DATETIME COMMENT '작성일시',
    CONSTRAINT pk_blog PRIMARY KEY (blog_id),
    CONSTRAINT fk_user_blog FOREIGN KEY (user_email)
        REFERENCES tbl_user (user_email) ON DELETE CASCADE  -- 블로그 작성자가 없어지면 해당 블로그를 삭제한다.
) ENGINE=InnoDB COMMENT '블로그';

CREATE TABLE IF NOT EXISTS tbl_notice
(
    notice_id       INT AUTO_INCREMENT,
    user_id         INT,
    notice_title    VARCHAR(1000) NOT NULL,
    notice_contents VARCHAR(1000),
    created_at      DATETIME,
    CONSTRAINT pk_notice PRIMARY KEY (notice_id),
    CONSTRAINT fk_user_notice FOREIGN KEY (user_id)
        REFERENCES tbl_user (user_id) ON DELETE SET NULL  -- 공지사항 작성자가 없어지면 해당 작성자 정보를 null 처리한다.
) ENGINE=InnoDB COMMENT='공지사항';

CREATE TABLE IF NOT EXISTS tbl_attach
(
    attach_id INT AUTO_INCREMENT,
    notice_id INT,
    file_path         VARCHAR(300),
    original_filename VARCHAR(300),
    filesystem_name   VARCHAR(40),
    download_count    INT,
    CONSTRAINT pk_attach PRIMARY KEY (attach_id),
    CONSTRAINT fk_notice_attach FOREIGN KEY (notice_id)
        REFERENCES tbl_notice (notice_id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT='첨부파일';

INSERT INTO tbl_user VALUES (NULL, 'admin@naver.com', SHA2('admin', 256), '관리자');
COMMIT;