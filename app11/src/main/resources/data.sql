DROP DATABASE IF EXISTS db_app11;
CREATE DATABASE IF NOT EXISTS db_app11;

USE db_app11;

DROP TABLE IF EXISTS tbl_board;
DROP TABLE IF EXISTS tbl_user;

-- 테이블 생성은 삭제의 역순으로 진행 --
CREATE TABLE IF NOT EXISTS tbl_user
(
    usr_id INT AUTO_INCREMENT,
    usr_email VARCHAR(100) NOT NULL UNIQUE,
    usr_name VARCHAR(100),
    CONSTRAINT pk_user PRIMARY KEY(usr_id)
) ENGINE=InnoDB COMMENT '사용자';

CREATE TABLE IF NOT EXISTS tbl_board
(
    board_id INT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    contents TEXT,
    create_dt DATETIME,
    usr_id INT,
    CONSTRAINT pk_board PRIMARY KEY(board_id),
    CONSTRAINT fk_user_board FOREIGN KEY(usr_id) REFERENCES tbl_user(usr_id) ON DELETE CASCADE
) ENGINE=InnoDB COMMENT '게시판';

INSERT INTO tbl_user VALUES (null, 'james@naver.com' ,'james');
INSERT INTO tbl_user VALUES (null, 'tom@google.com' ,'tom');
INSERT INTO tbl_user VALUES (null, 'charile@naver.com' ,'charile');

INSERT INTO tbl_board VALUES (null, '20241209_식단', '김치찌개, 된장찌개, 순두부찌개', '2024-12-05 10:00:00', 1);
INSERT INTO tbl_board VALUES (null, '20241210_식단', '마라탕, 마라샹궈', '2024-12-06 10:00:00', 1);
INSERT INTO tbl_board VALUES (null, '20241211_식단', '피자, 치킨, 햄버거', '2024-12-07 10:00:00', 2);

COMMIT;

SELECT board_id, title, contents, b.usr_id, usr_email, usr_name, create_dt
  FROM tbl_board b INNER JOIN tbl_user u
    ON b.usr_id = u.usr_id
ORDER BY board_id DESC;

-- 마지막으로 삽입된 PK값 추출하기 --
SELECT LAST_INSERT_ID();
