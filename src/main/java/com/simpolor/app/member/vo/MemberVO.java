package com.simpolor.app.member.vo;

public class MemberVO {
	
	// default information
	private String member_id;		// 사용자 아이디
	private String member_pw;		// 사용자 비밀번호
	private String member_pw2;		// 사용자 비빌먼호 확인
	private String member_pw_org;	// 사용자 기존 비밀번호
	private String member_name;		// 사용자 이름
	private String member_nickname;	// 사용자 닉네임
	private String member_email;	// 사용자 이메일
	private String reg_date;		// 가입일
	private String mod_date;		// 수정일
	private int level;				// 회원 권한
	private String del_yn;			// 탈퇴유무
	
	// additional information
	private String mobile;			// 핸드폰 번호
	private String sex;				// 성별
	private String birthday;		// 생년월일
	private String address;			// 주소
	
	private String search_type;				
	private String search_condition;	// 검색조건
	private String search_word;			// 검색단어
	
	private String return_type;
	private String return_url;
	
	private int seq;
	private int page;
	private int offset;
	private int limit;
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_pw2() {
		return member_pw2;
	}
	public void setMember_pw2(String member_pw2) {
		this.member_pw2 = member_pw2;
	}
	public String getMember_pw_org() {
		return member_pw_org;
	}
	public void setMember_pw_org(String member_pw_org) {
		this.member_pw_org = member_pw_org;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
}
