package misc;

public class Normalizer_author {

	public static String normal_author(String s){
	
		String[] Symbols= {".-"," -","‐","’","-","  ",".","–","‑"," ","Mª"};
		
		String[] rep_symbols={"-","-","-","'","-"," "," ","-","-"," ","Ma"};

		
		for(int i=0;i<Symbols.length;i++){
	
		s=s.replace(Symbols[i], rep_symbols[i]);	
		
		}
		System.out.println("normal "+s.replace("  ", " "));
		return s.replace("  ", " ");
	}
		
}
