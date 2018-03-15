package com.simpolor.app.bookmark.vo;

public class BookmarkVO {
	
	private int bookmark_seq;				// 북마크 번호
	private String bookmark_type;			// 북마크 유형
	private String bookmark_category;		// 북마크 분류
	private String bookmark_description;	// 북마크 설명
	private String bookmark_url;			// 북마크 URL
	private String reg_id;					// 등록자 아이디
	private String reg_name;				// 등록자 이름
	private String reg_nickname;			// 등록자 닉네임
	private String reg_date;				// 등록일
	private String mod_id;					// 수정자 아이디
	private String mod_name;				// 수정자 이름
	private String mod_nickname;			// 수정자 닉네임
	private String mod_date;				// 수정일
	private String del_yn;					// 삭제유무
	
	private int my_seq;						// 나의 북마크 번호
	private int report_seq;					// 북마크 신고 번호
	private String report_content;			// 북마크 신고 내용
	
	private String search_type;				
	private String search_condition;		// 검색조건
	private String search_word;				// 검색단어
	
	private String return_type;
	
	private int seq;
	private int page;
	private int offset;
	private int limit;
	
	public int getBookmark_seq() {
		return bookmark_seq;
	}
	public void setBookmark_seq(int bookmark_seq) {
		this.bookmark_seq = bookmark_seq;
	}
	public String getBookmark_type() {
		return bookmark_type;
	}
	public void setBookmark_type(String bookmark_type) {
		this.bookmark_type = bookmark_type;
	}
	public String getBookmark_category() {
		return bookmark_category;
	}
	public void setBookmark_category(String bookmark_category) {
		this.bookmark_category = bookmark_category;
	}
	public String getBookmark_description() {
		return bookmark_description;
	}
	public void setBookmark_description(String bookmark_description) {
		this.bookmark_description = bookmark_description;
	}
	public String getBookmark_url() {
		return bookmark_url;
	}
	public void setBookmark_url(String bookmark_url) {
		this.bookmark_url = bookmark_url;
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
	public int getMy_seq() {
		return my_seq;
	}
	public void setMy_seq(int my_seq) {
		this.my_seq = my_seq;
	}
	public String getReturn_type() {
		return return_type;
	}
	public void setReturn_type(String return_type) {
		this.return_type = return_type;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getReport_seq() {
		return report_seq;
	}
	public void setReport_seq(int report_seq) {
		this.report_seq = report_seq;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	
}
