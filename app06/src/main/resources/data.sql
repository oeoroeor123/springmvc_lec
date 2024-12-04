/*스키마 만들기*/
DROP DATABASE IF EXISTS db_app06;
CREATE DATABASE IF NOT EXISTS db_app06;

USE db_app06;

/*테이블 만들기*/
DROP TABLE IF EXISTS tbl_contact;
CREATE TABLE IF NOT EXISTS tbl_contact
(
    contact_id INT AUTO_INCREMENT,
    last_name VARCHAR(100),
    first_name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(20),
    create_dt DATE,
    CONSTRAINT pk_contact PRIMARY KEY(contact_id)
);

INSERT INTO tbl_contact VALUES(null, 'james', 'dean', 'user@naver.com', '010-1111-1111', '2024-12-01');
INSERT INTO tbl_contact VALUES(null, 'jungkook', 'jimin', 'admin@naver.com','010-2222-2222', '2024-12-02');
INSERT INTO tbl_contact VALUES(null, 'hoseak', 'jin', 'a@naver.com', '010-3333-3333', '2024-12-03');

COMMIT;

SELECT contact_id, last_name, first_name, email, mobile, create_dt FROM tbl_contact;

