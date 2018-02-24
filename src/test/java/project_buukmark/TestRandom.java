package project_buukmark;

import java.util.Random;

public class TestRandom {
	
	public static void main(String[] args) {
		System.out.println("Hello Random!");
		
		Random random = new Random();
		StringBuffer buffer =new StringBuffer();
		
		for(int i=0;i<20;i++){
		    if(random.nextBoolean()){
		    	buffer.append((char)((int)(random.nextInt(26))+97));
		    }else{
		    	buffer.append((random.nextInt(10))); 
		    }
		}
		System.out.println("buffer : "+buffer.toString());
		
		StringBuffer buffer2 = new StringBuffer();
		String chars = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9";
		String splitChars[] = chars.split(",");
		for (int i = 0; i < 20; i++) {
			buffer2.append(splitChars[random.nextInt(splitChars.length)]);
		}
		System.out.println("buffer2 : "+buffer2.toString());
		
		
		// 특수기호 포함
		StringBuffer strPwd = new StringBuffer();
		char str[] = new char[1];
		for (int i = 0; i < 20; i++) {
			str[0] = (char) ((Math.random() * 94) + 33);
			strPwd.append(str);
		}
		System.out.println("strPwd : "+strPwd.toString());
		
		// 대문자로만
		StringBuffer strPwd2 = new StringBuffer();
		char str2[] = new char[1];
		for (int i = 0; i < 20; i++) {
			str2[0] = (char) ((Math.random() * 26) + 65);
			strPwd2.append(str2);
		}
		System.out.println("strPwd2 : "+strPwd2.toString());
		
		
		// 소문자로만
		StringBuffer strPwd3 = new StringBuffer();
		char str3[] = new char[1];
		for (int i = 0; i < 20; i++) {
			str3[0] = (char) ((Math.random() * 26) + 97);
			strPwd3.append(str3);
		}
		System.out.println("strPwd3 : "+strPwd3.toString());
		
		// 소문자로만
		StringBuffer strPwd4 = new StringBuffer();
		int str4[] = new int[1];
		for (int i = 0; i < 20; i++) {
			str4[0] = (int) ((Math.random() * 9));
			strPwd4.append(str4[0]);
		}
		System.out.println("strPwd4 : "+strPwd4.toString());
		
		// 소문자 + 숫자
		StringBuffer strPwd5 = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			if (random.nextBoolean()) {
				strPwd5.append((char) ((int) (random.nextInt(26)) + 97));
			} else {
				strPwd5.append((random.nextInt(10)));
			}
		}
		System.out.println("strPwd5 : "+strPwd5.toString());
	}
}
