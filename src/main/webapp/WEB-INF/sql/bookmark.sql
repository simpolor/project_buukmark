CREATE TABLE `bookmark` (
	`bookmark_seq` INT(11) NOT NULL AUTO_INCREMENT COMMENT '북마크 번호',
	`bookmark_type` VARCHAR(200) NOT NULL COMMENT '북마크 타입',
	`bookmark_category` VARCHAR(200) NOT NULL COMMENT '북마크 유형',
	`bookmark_description` VARCHAR(200) NOT NULL COMMENT '북마크 설명',
	`bookmark_url` VARCHAR(200) NOT NULL COMMENT '북마크 URL',
	`reg_id` VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
	`reg_name` VARCHAR(50) NOT NULL COMMENT '등록자 이름',
	`reg_nickname` VARCHAR(50) NOT NULL COMMENT '등록자 닉네임',
	`reg_date` DATETIME NOT NULL COMMENT '등록일',
	`mod_id` VARCHAR(20) NULL DEFAULT NULL COMMENT '수정자 아이디',
	`mod_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 이름',
	`mod_nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 닉네임',
	`mod_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
	`del_yn` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제유무',
	PRIMARY KEY (`bookmark_seq`)
);
