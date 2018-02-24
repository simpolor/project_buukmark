package com.simpolor.app.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	public static String toString( Object obj ) {
		
    	if( obj == null || obj.equals( "" ) ){
    		return null;
    	}else{
    		SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM-dd");
    		SimpleDateFormat nSdf = new SimpleDateFormat("yyyyMMdd");
    		
    		Date oDate;
    		try {
    			oDate = oSdf.parse((String) obj);
    			String nDate = nSdf.format(oDate);
    			
    			return nDate;
    		} catch (ParseException e) {
    			//e.printStackTrace();
    			return null;
    		}
    	}
    }
	
	public static String toDate( Object obj, String format ) {
		
		if( obj == null || obj.equals( "" ) ){
    		return null;
    	}else{
    		SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM-dd");
    		SimpleDateFormat nSdf = new SimpleDateFormat(format);
    		
    		Date oDate;
    		try {
    			oDate = oSdf.parse((String) obj);
    			String nDate = nSdf.format(oDate);
    			
    			return nDate;
    		} catch (ParseException e) {
    			//e.printStackTrace();
    			return null;
    		}
    	}
    }
	
	public static Date toDate( Object obj ) {
		
    	if( obj == null || obj.equals( "" ) ){
    		return null;
    	}else{
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		Date date;
    		try {
    			date = sdf.parse((String) obj);
    			
    			return date;
    		} catch (ParseException e) {
    			//e.printStackTrace();
    			return null;
    		}
    	}
    }
	
	public static Date toDateOracl( Object obj ) {
		
    	if( obj == null || obj.equals( "" ) ){
    		return null;
    	}else{
    		
    		SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    		oSdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    		
    		Date toDate;
    		try {
    			toDate = oSdf.parse((String) obj);
    			
    			return toDate;
    		} catch (ParseException e) {
    			//e.printStackTrace();
    			return null;
    		}
    	}
    }
	
	public static Long toLong( Object obj ){
		
		if( obj == null || obj.equals( "" ) ){
    		return null;
    	}else{
    		
    		SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    		oSdf.setTimeZone(TimeZone.getTimeZone("GMT"));
    		
    		Date toDate;
    		try {
    			toDate = oSdf.parse((String) obj);
    			
    			return toDate.getTime();
    		} catch (ParseException e) {
    			//e.printStackTrace();
    			return null;
    		}
    	}
	}

}
