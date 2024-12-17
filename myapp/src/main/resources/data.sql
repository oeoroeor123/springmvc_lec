DROP DATABASE IF EXISTS db_myapp;
CREATE DATABASE IF NOT EXISTS db_myapp;

USE db_myapp;

DROP TABLE IF EXISTS tbl_attach;
DROP TABLE IF EXISTS tbl_notice;
DROP TABLE IF EXISTS tbl_blog;

CREATE TABLE IF NOT EXISTS tbl_blog
(
    blog_id INT AUTO_INCREMENT COMMENT '블로그아이디',
    title VARCHAR(1000) NOT NULL COMMENT '제목',
    contents TEXT COMMENT '내용',
    user_email VARCHAR(100) NOT NULL COMMENT '작성자이메일',
    hit INT COMMENT '조회수',
    modify_dt DATETIME COMMENT '수정일시',
    create_dt DATETIME COMMENT '작성일시',
    CONSTRAINT pk_blog PRIMARY KEY(blog_id)
)ENGINE=InnoDB COMMENT '블로그';

CREATE TABLE IF NOT EXISTS tbl_notice(
    notice_id INT AUTO_INCREMENT,
    notice_title VARCHAR(1000) NOT NULL,
    notice_contents VARCHAR(1000),
    created_at DATETIME,
    CONSTRAINT pk_notice PRIMARY KEY(notice_id)
)ENGINE=InnoDB COMMENT '공지사항';

CREATE TABLE IF NOT EXISTS tbl_attach(
    attach_id INT AUTO_INCREMENT,
    notice_id INT,
    file_path VARCHAR(300),
    original_filename VARCHAR(300),
    filesystem_name VARCHAR(40),
    download_count INT,
    CONSTRAINT pk_attach PRIMARY KEY(attach_id),
    CONSTRAINT fk_notice_attach FOREIGN KEY(notice_id)
               REFERENCES tbl_notice(notice_id) ON DELETE CASCADE
)ENGINE=InnoDB COMMENT '첨부파일';

INSERT INTO tbl_blog VALUES (NULL, '라면 끓이기', '저는 물 많이 넣은 한강 라면을 좋아합니다.', 'james@gmail.com', 5, NULL, '2024-12-05 10:00:30');
INSERT INTO tbl_blog VALUES (NULL, '표고버섯구이', '저는 표고버섯을 프라이팬에 구워 먹는 걸 좋아합니다.', 'alice@gmail.com', 100, NULL, '2024-12-06 12:10:30');

SELECT blog_id, title, contents, user_email, hit, modify_dt, create_dt FROM tbl_blog; 
SELECT notice_id, notice_title, notice_contents, created_at FROM tbl_notice;
SELECT attach_id, notice_id, file_path, original_filename, filesystem_name, download_count FROM tbl_attach;

COMMIT;