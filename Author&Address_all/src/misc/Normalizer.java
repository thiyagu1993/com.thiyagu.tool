package misc;

import org.apache.commons.lang3.text.WordUtils;

public class Normalizer {

	
	public static String normal(String s){
		
	String[] Symbols_drop= {",","“","”","‘","’","(",")",".","'",";","ʼ","*"};
	
	for(String s1 :Symbols_drop){
		
		s = s.replace(s1, "");
	}
	
	String[] Symbols_change= {"-","‐","‑","–","/","—"};

	for(String s2 :Symbols_change){
		
		s = s.replace(s2, " ");
	}
	
	return s.replace("ibac", "Ibac").replace("Für", "");
	}
		
}
