package com.author.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Initial_Bump_splitter {

	public static String bump_splitter(String s) {
		
		Pattern in1 = Pattern.compile("\\b[A-Z][A-Z][A-Z]. \\w+\\b|\\b[A-Z][A-Z][A-Z] \\w+\\b");
		Pattern in = Pattern.compile("\\b[A-Z][A-Z]. \\w+\\b|\\b[A-Z][A-Z] \\w+\\b");
		Pattern in2 = Pattern.compile("\\b[A-Z]\\{3,}[A-Z]\\w+\\b");
		Pattern in3 = Pattern.compile("\\b\\w+[A-Z]{1} ");
		//
		Matcher m = in.matcher(s);
		Matcher m1 = in1.matcher(s);
		Matcher m2 = in2.matcher(s);
		Matcher m3 = in3.matcher(s);
		
		if(m1.find()){
			
			int icount=1;
			StringBuilder sb = new StringBuilder(s);
			
			sb.insert(icount, ' ');
			sb.insert(icount+2, ' ');
			
			
			return sb.toString();
				
		}
				
		if(m.find()){
			
			int icount=1;
			StringBuilder sb = new StringBuilder(s);
			System.out.println("m"+m.group());
			sb.insert(icount, ' ');
			System.out.println("entering 2"+sb.toString());
			return sb.toString();
				
		}
		
		if(m2.find()){
			int icount=0;
			StringBuilder sb = new StringBuilder(s);
			char[] jj = s.toCharArray();
			
			for(int h=0;h<s.indexOf(" ");h++){
				System.out.println(m2.group());
				if(Character.isUpperCase(jj[h+1])){
			
					icount = h+1;
								
				}
			}
			
			sb.insert(icount, ' ');
			System.out.println("entering m2"+sb.toString());
			return sb.toString();
		}
		
		
		if(m3.find()){
			try{
			int icount=0;
			StringBuilder sb = new StringBuilder(s);
			
			icount = s.indexOf(" ")-1;
			System.out.println(m3.group());
			sb.insert(icount, ' ');
			System.out.println("entering m3"+sb.toString());
			return sb.toString();
			
			}catch(Exception e){
				e.printStackTrace();
			}

		
		}
		
		return s;
	}
	
}
