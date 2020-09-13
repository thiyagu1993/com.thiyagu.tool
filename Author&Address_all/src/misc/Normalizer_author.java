package misc;

public class Normalizer_author {

	public static String normal_author(String s){
		
		String[] Symbols= {"‐","’","-","Jr","Sr","III","II","  ",".","–"};
		
		String[] rep_symbols={"-","'","-","","","","",""," ","-"};
		
		for(int i=0;i<Symbols.length;i++){
	
		s=s.replace(Symbols[i], rep_symbols[i]);	
		
		}
	
		return s.replace("  ", " ");
	}
		
}
