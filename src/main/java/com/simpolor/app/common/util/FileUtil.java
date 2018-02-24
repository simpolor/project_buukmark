package com.simpolor.app.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class FileUtil {
	
	public static String getJsonFile(String filePath, String fileName){
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext();
		Resource resource = appContext.getResource("classpath:json/"+filePath+"/"+fileName);

		StringBuffer sb = new StringBuffer();
	    try{
	    	InputStream is = resource.getInputStream();
	    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    	
	    	String line;
	    	while ((line = br.readLine()) != null) {
	    		sb.append(line);
	    	}
	    	
	    	br.close();
	    	is.close();

	    }catch(IOException e){
	    	e.printStackTrace();
	    }
	    
	    return sb.toString();
	}

}
