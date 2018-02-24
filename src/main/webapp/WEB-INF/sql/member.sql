CREATE TABLE `member` (
	`member_id` VARCHAR(20) NOT NULL COMMENT '사용자 아이디',
	`member_pw` VARCHAR(50) NULL DEFAULT NULL COMMENT '사용자 비밀번호',
	`member_name` VARCHAR(50) NULL DEFAULT NULL COMMENT '사용자 이름',
	`member_nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '사용자 닉네임',
	`member_email` VARCHAR(50) NULL DEFAULT NULL COMMENT '사용자 이메일',
	`reg_date` DATETIME NULL DEFAULT NULL COMMENT '등록일',
	`mod_date` DATETIME NULL DEFAULT NULL COMMENT '수정일',
	`level` CHAR(2) NOT NULL DEFAULT '1' COMMENT '사용자 등급',
	`del_yn` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '탈퇴유무',
	PRIMARY KEY (`member_id`)
);
