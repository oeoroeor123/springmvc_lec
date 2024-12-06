DROP DATABASE IF EXISTS db_myapp;
CREATE DATABASE IF NOT EXISTS db_myapp;

USE db_myapp;

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

INSERT INTO tbl_blog VALUES (NULL, '라면 끓이기', '저는 물 많이 넣은 한강 라면을 좋아합니다.', 'james@gmail.com', 5, NULL, '2024-12-05 10:00:30');
INSERT INTO tbl_blog VALUES (NULL, '표고버섯구이', '저는 표고버섯을 프라이팬에 구워 먹는 걸 좋아합니다.', 'alice@gmail.com', 100, NULL, '2024-12-06 12:10:30');

SELECT blog_id, title, contents, user_email, hit, modify_dt, create_dt FROM tbl_blog; 

COMMIT;