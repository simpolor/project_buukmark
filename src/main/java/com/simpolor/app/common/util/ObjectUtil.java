package com.simpolor.app.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ObjectUtil {
	
	/***
	 * VO객체를 Map객체로 치환
	 * @param Object
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> ConverObjectToMap(Object obj){

		try{
		  Field[] fields = obj.getClass().getDeclaredFields(); // obj객체의 필드 정보를 가져옵니다.
		  Map<String, Object> resultMap = new HashMap<String, Object>(); // Map 객체를 생성합니다.
		  
		  // obj객체의 필드명과 필드값을 Map객체 담습니다.
		  for(int i=0; i<=fields.length-1;i++){ 
		    fields[i].setAccessible(true);
            resultMap.put(fields[i].getName(), fields[i].get(obj));
		  }
		  return resultMap;
		  
		}catch(IllegalArgumentException e){
		  e.printStackTrace();
		}catch (IllegalAccessException e){
		  e.printStackTrace();
		}
		return null;
	}
	
	/***
	 * Map객체를 VO객체로 치환
	 * @param Map<String, Object>
	 * @return Object
	 */
	public static Object convertMapToObject(Map<String, Object> map, Object objClass){

		String keyAttribute = null;
		String setMethodString = "set";
		String methodString = null;
		Iterator<String> iter = map.keySet().iterator(); // Map객체의 keySet을 iterator에 치환하여 담습니다.
		
		//System.out.println(map);
		// Iterator의 값을 objClass의 필드와 일치할 objClass의 필드의 값을 추가합니다.
		while(iter.hasNext()){
		  keyAttribute = (String) iter.next();
		  //System.out.println("key name :: "+keyAttribute);
		  //System.out.println("value info :: "+map.get(keyAttribute));
		  methodString = setMethodString+keyAttribute.substring(0,1).toUpperCase()+keyAttribute.substring(1);
		  
		  try{ // objClass의 필드를 정보를 가져옵니다.
		    Method[] methods = objClass.getClass().getDeclaredMethods();
            for(int i=0;i<=methods.length-1;i++){
              if(methodString.equals(methods[i].getName())){
                methods[i].invoke(objClass, map.get(keyAttribute));
              }
            }
	      }catch(SecurityException se){
	        se.printStackTrace();
		  }catch(IllegalAccessException iae){
			iae.printStackTrace();
          }catch(IllegalArgumentException iae){
        	iae.printStackTrace();
          }catch(InvocationTargetException ite){
        	ite.printStackTrace();
          }
		}
		return objClass;
	}

}
