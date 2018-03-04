package com.simpolor.app.common.util;

import java.util.regex.Pattern;

public class ValidateUtil {
	
	public static boolean isEmail(String email) {
		if (email == null){
			return false;
		}
		return Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email);
	}
	
	public static boolean isPassword(String password) {
		if (password == null){
			return false;
		}
		return Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", password);
	}
	
	public static boolean isIdentify(String id) {
		if (id == null){
			return false;
		}
		return Pattern.matches("[a-z]+[a-z0-9])+", id);
	}
}
