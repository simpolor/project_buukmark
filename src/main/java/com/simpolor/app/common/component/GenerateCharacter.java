package com.simpolor.app.common.component;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateCharacter {

    private final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                                            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                                            'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
    
    private final char[] passwordTable =  { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
            								'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            								'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            								'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
            								'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
            								'(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
    
    public String excuteGenerate(String generateType, int charLength) {
        Random random = new Random(System.currentTimeMillis());
        
        StringBuffer buffer = new StringBuffer();
        if("character".equals(generateType)){
        	for(int i = 0; i < charLength; i++) {
            	buffer.append(characterTable[random.nextInt(characterTable.length)]);
            }
        }else if("password".equals(generateType)){
        	for(int i = 0; i < charLength; i++) {
            	buffer.append(passwordTable[random.nextInt(passwordTable.length)]);
            }
        }

        return buffer.toString();
    }

}
