CREATE TABLE `board_reply` (
	`reply_seq` INT(11) NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
	`board_seq` INT(11) NOT NULL COMMENT '게시판 번호',
	`reply_content` TEXT NOT NULL COMMENT '댓글 내용',
	`reg_id` VARCHAR(20) NOT NULL COMMENT '등록자 아이디',
	`reg_name` VARCHAR(50) NOT NULL COMMENT '등록자 이름',
	`reg_nickname` VARCHAR(50) NOT NULL COMMENT '등록자 닉네임',
	`reg_date` DATETIME NOT NULL COMMENT '등록일',
	`mod_id` VARCHAR(20) NULL DEFAULT NULL COMMENT '수정자 아이디',
	`mod_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 이름',
	`mod_nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '수정자 닉네임',
	`mod_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
	`del_yn` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제유무',
	PRIMARY KEY (`reply_seq`),
	INDEX `FK_board_reply` (`board_seq`),
	CONSTRAINT `FK_board_reply` FOREIGN KEY (`board_seq`) REFERENCES `board` (`board_seq`) ON UPDATE NO ACTION ON DELETE NO ACTION
);