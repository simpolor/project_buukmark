package com.simpolor.app.ask.vo;

public class AskVO {
	
	// default information
	private int ask_seq;				// 게시판 번호
	private String ask_title;		// 게시판 제목
	private String ask_content;		// 게시판 내용 
	private String reg_id;				// 등록자 아이디 
	private String reg_name;			// 등록자 이름
	private String reg_nickname;		// 등록자 닉네임
	private String reg_date;			// 등록일
	private String mod_id;				// 수정자 아이디
	private String mod_name;			// 수정자 이름
	private String mod_nickname;		// 수정자 닉네임
	private String mod_date;			// 수정일
	private String secret_yn;			// 비밀글여부
	private String del_yn;				// 삭제유무
	
	private String search_type;				
	private String search_condition;	// 검색조건
	private String search_word;			// 검색단어
	
	private String return_type;
	private String return_url;
	
	private int seq;
	private int page;
	private int offset;
	private int limit;
	
	
	public int getAsk_seq() {
		return ask_seq;
	}
	public void setAsk_seq(int ask_seq) {
		this.ask_seq = ask_seq;
	}
	public String getAsk_title() {
		return ask_title;
	}
	public void setAsk_title(String ask_title) {
		this.ask_title = ask_title;
	}
	public String getAsk_content() {
		return ask_content;
	}
	public void setAsk_content(String ask_content) {
		this.ask_content = ask_content;
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
	public String getMod_id() {
		return mod_id;
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
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public String getSearch_condition() {
		return search_condition;
	}
	public void setSearch_condition(String search_condition) {
		this.search_condition = search_condition;
	}
	public String getSearch_word() {
		return search_word;
	}
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String return_type) {
		this.return_type = return_type;
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
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getSecret_yn() {
		return secret_yn;
	}
	public void setSecret_yn(String secret_yn) {
		this.secret_yn = secret_yn;
	}
	
}
