package process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.author.support.Initial_Bump_splitter;

public class Author_check2 {

	public static boolean Author_check_two(ArrayList<String> ar1, ArrayList<String> ar2){
		
		int author_total_count=0;
		int total_author_count=0;
		for(int i=0;i<ar1.size();i++){
			
			for(int j=0;j<ar2.size();j++){
				
				
				String[] transpose_doc  = Initial_Bump_splitter.bump_splitter(ar1.get(i)).trim().split(" ");
				String[] transpose_docx = Initial_Bump_splitter.bump_splitter(ar2.get(j)).trim().split(" ");
				total_author_count = transpose_doc.length;
				
				Set<String> set = new HashSet<String>();
				Collections.addAll(set, transpose_doc);
				
				Set<String> set1 = new HashSet<String>();
				Collections.addAll(set1, transpose_docx);
				
				if(set.size()!=set1.size()){
					
					return false;
				}
				
				int count = 0;
				for(String namedoc:set){
					
					for(String namedocx: set1){
										
						if(namedoc.trim().equalsIgnoreCase(namedocx.trim().replace("ä", "a").replace("ö", "o").replace("ü", "u"))){
//							System.err.println(namedocx+" "+namedoc);
							count++;										
						}else{
						}
						
					}
					
				}
				
				
				if(count==set.size()){
//					System.out.println("Name parts Match"+count);
					author_total_count++;
				}else{
//					System.out.println("Name parts mismatch"+count);
				}
				
				
				
			}

		}
		if(author_total_count==total_author_count){
		System.out.println("AUTHOR "+author_total_count);
		return true;
		}
		return false;	
	}
	
	
}
