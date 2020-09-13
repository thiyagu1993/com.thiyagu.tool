package process;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import resource.Abbreviation;
import resource.Word_ending_3;
import resource.Word_ending_4;

public class WE_collect_driver {

	public static void we_col_driver(HashSet<String> hs,FileWriter out_we) throws IOException{
		
	
		for(String st : hs){
			
			int	i0_count=0;
			
			for(int i0=0;i0<Abbreviation.abbr.length;i0++){
				
				if(st.trim().equals(Abbreviation.abbr[i0])){
					i0_count++;
				}
				
			}	
			
				if(i0_count>=1){
					continue;
			}
			
			int i_count =0;
			for(int i=0;i<Word_ending_3.word_ending_3.length;i++){
				
				if(st.trim().equals(Word_ending_3.word_ending_3[i])){
					i_count++;
				}
				
			}
			if(i_count>=1){
				continue;
			}
							
			int i1_count=0;			
			for(int i1=0;i1<Word_ending_4.word_ending_4.length;i1++){

				if(st.trim().equals(Word_ending_4.word_ending_4[i1])){
					i1_count++;
				}
	
			}
			if(i1_count>=1){
			continue;	
			}else{
				out_we.write(st);
				out_we.write(System.lineSeparator());
			}
				
		}
	}
	
	
}
