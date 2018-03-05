package com.simpolor.app.common.util;

import java.util.regex.Pattern;

public class ValidateUtil {
	
	public final static String PATTERN_EMAIL = "";
	public final static String PATTERN_IDENTITY = "";
	public final static String PATTERN_NAME = "";
	public final static String PATTERN_NICKNAME = "";
	public final static String PATTERN_URL = "";
	
	public final static String PATTERN_PASSWORD_NUMBER = "[0-9]{1}";
	public final static String PATTERN_PASSWORD_CHAR = "[a-zA-Z]{1}";
	public final static String PATTERN_PASSWORD_SPECIAL = "[a-zA-Z]{1}";
	
	public static boolean isEmpty(String str){
		if(str == null || str.trim().length() <= 0){
			return true;
		}
		return false;
	}
	
	public static boolean isIdentify(String id) {
		if (isEmpty(id)){
			return false;
		}
		return Pattern.matches(PATTERN_IDENTITY, id);
	}
	
	public static boolean isPassword(String password) {
		if (isEmpty(password)){
			return false;
		}
		return Pattern.matches(PATTERN_PASSWORD_NUMBER, password);
	}
	
	public static boolean isName(String name) {
		if (isEmpty(name)){
			return false;
		}
		return Pattern.matches(PATTERN_NAME, name);
	}
	
	public static boolean isNickName(String nickname) {
		if (isEmpty(nickname)){
			return false;
		}
		return Pattern.matches(PATTERN_NICKNAME, nickname);
	}
	
	public static boolean isEmail(String email) {
		if (isEmpty(email)){
			return false;
		}
		return Pattern.matches(PATTERN_EMAIL, email);
	}
	
	public static boolean isUrl(String url) {
		if (isEmpty(url)){
			return false;
		}
		return Pattern.matches(PATTERN_URL, url);
	}
	
	
}
