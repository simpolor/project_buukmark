package com.simpolor.app.board.vo;

public class BoardReplyVO {
	
	// default information
	private int board_seq;				// 게시판 번호
	private int reply_seq;				// 댓글 번호
	private String reply_content;		// 댓글 내용
	private String reg_id;				// 등록자 아이디 
	private String reg_name;			// 등록자 이름
	private String reg_nickname;		// 등록자 닉네임
	private String reg_date;			// 등록일
	private String mod_id;				// 수정자 아이디
	private String mod_name;			// 수정자 이름
	private String mod_nickname;		// 수정자 닉네임
	private String mod_date;			// 수정일
	private String del_yn;				// 삭제유무
	
	private int seq;
	private int page;
	private int offset;
	private int limit;
	
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public int getReply_seq() {
		return reply_seq;
	}
	public void setReply_seq(int reply_seq) {
		this.reply_seq = reply_seq;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_nickname() {
		return reg_nickname;
	}
	public void setReg_nickname(String reg_nickname) {
		this.reg_nickname = reg_nickname;
	}
	public String getMod_nickname() {
		return mod_nickname;
	}
	public void setMod_nickname(String mod_nickname) {
		this.mod_nickname = mod_nickname;
	}
	public String getMod_id() {
		return mod_id;
	}
	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}
	public String getMod_name() {
		return mod_name;
	}
	public void setMod_name(String mod_name) {
		this.mod_name = mod_name;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
}
