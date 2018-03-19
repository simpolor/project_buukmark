CREATE TABLE `bookmark_report` (
	`report_seq` INT(11) NOT NULL AUTO_INCREMENT COMMENT '신고 번호',
	`bookmark_seq` INT(11) NOT NULL COMMENT '즐겨찾기 번호',
	`report_content` VARCHAR(500) NOT NULL COMMENT '신고 내용',
	`report_yn` CHAR(1) NULL DEFAULT 'N' COMMENT '신고 처리유무',
	`reg_id` VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
	`reg_name` VARCHAR(50) NOT NULL COMMENT '등록자 이름',
	`reg_nickname` VARCHAR(50) NOT NULL COMMENT '등록자 닉네임',
	`reg_date` DATETIME NOT NULL COMMENT '등록일',
	`mod_id` VARCHAR(20) NULL DEFAULT NULL COMMENT '수정자 아이디',
	`mod_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 이름',
	`mod_nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 닉네임',
	`mod_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
	`del_yn` CHAR(1) NOT NULL DEFAULT 'N',
	PRIMARY KEY (`report_seq`),
	INDEX `FK_buukmark_report` (`bookmark_seq`),
	CONSTRAINT `FK_buukmark_report` FOREIGN KEY (`bookmark_seq`) REFERENCES `bookmark` (`bookmark_seq`)
);