package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.author.support.Initial_Bump_splitter;

import misc.Normalizer_author;

public class Test_author {
	
	public static void main(String[] args) throws IOException {
	
		FileInputStream test_fis = new FileInputStream(new File("C:\\Address_test_all\\Author&Address_all\\testing\\test_file.xlsx"));
		XSSFWorkbook test_xwb = new XSSFWorkbook(test_fis);
		XSSFSheet test_xsheet = test_xwb.getSheetAt(0);
		
		
		String tcol1="";
		String tcol2="";
		
		
		for(int j=0;j<test_xsheet.getLastRowNum()+1;j++){
		
			Row r = test_xsheet.getRow(j);	
			Cell ce = r.getCell(0);
			Cell c2 = r.getCell(1);
			
			tcol1 = ce.toString();
			tcol2 = c2.toString();
			
			author_testing(tcol1,tcol2);
			
			int icount=0;
			Pattern in1 = Pattern.compile("\\b[A-Z][A-Z]. \\w+\\b|\\b[A-Z][A-Z] \\w+\\b");
			Pattern in2 = Pattern.compile("\\b[A-Z]\\w+[A-Z]\\w+\\b");
			
		Matcher m = in2.matcher(tcol1);
	
		}
		}
	

public static void author_testing(String col1, String col2){
	
	String[] symbols = {"á","À","â","ă","ā","ã","å","ą","Â","æ","ć","č","ç","ď","Đ","ð","Ɖ","é","è","ė","ê","ë","ě","ę","ȩ","ǧ","ğ","ı","í","ì","İ","î","ï","ĭ","ị","Ľ","ł","ń","ň","ñ","Ó","ò","ô","õ","ő","Ø","œ","Ř","ś","Š","ş","ș","ß","ť","ţ","Þ","ú","ù","û","ū","ů","ű","ý","ÿ","ź","ż","Ž","β","А","К","Ä","à","ó","ø","Ö","Ü","ç","É","Å","Ñ","Ç","ž","Ş","Ś","Á","Č","š","ř","Ż","Ł","Ú","ӧ","Ć","ț","ľ","ō","đ","ó","í","ũ","Ș","ř","a̧","ī","ļ","ņ","Ē","Ť","ģ","Ź","ẚ","ǖ","Ť","Ď","þ","Ð","ӑ","ē","ấ","ư","ờ","ǵ"};
	String[] alphabet= {"a","A","a","a","a","a","a","a","A","ae","c","c","c","d","D","d","D","e","e","e","e","e","e","e","e","g","g","i","i","i","I","i","i","i","i","L","l","n","n","n","O","o","o","o","o","O","oe","R","s","S","s","s","ss","t","t","th","u","u","u","u","u","u","y","y","z","z","Z","ss","A","K","A","a","o","o","O","U","c","E","A","N","C","z","S","S","A","C","s","r","Z","L","U","o","C","t","l","o","d","o","i","u","S","r","a","i","l","n","E","T","g","Z","a","u","T","D","th","D","a","e","a","u","o","g"};
	
	String ar_author_symbol_removed=col1;

	for(int dia=0;dia<symbols.length;dia++){
		ar_author_symbol_removed = ar_author_symbol_removed.replace(symbols[dia], alphabet[dia]);
	}
	
	String ar_casing_corrected = ar_author_symbol_removed;
	
	if(StringUtils.isAllUpperCase(ar_author_symbol_removed.replace(" ", "").replace(",", "").replace(".", "").replace("-", ""))==true){
		
			ar_casing_corrected = WordUtils.capitalizeFully(ar_casing_corrected);
	}
	
	String[] xdocauthor_size = Normalizer_author.normal_author(ar_author_symbol_removed).trim().split(", ");	
	
	
	String[] docauthor = Normalizer_author.normal_author(col2).split(", ");
	System.out.println(xdocauthor_size.length+" <-Size-> "+docauthor.length);
		
	if(xdocauthor_size.length==docauthor.length){
	
		int author_total_count=0;
		int total_docauthor=docauthor.length;
		
		for(int ii=0;ii<docauthor.length;ii++){
					
	System.out.println(docauthor[ii]+" -:- "+xdocauthor_size[ii]);
	
	if(docauthor[ii].equalsIgnoreCase(xdocauthor_size[ii].replace("ä", "a").replace("ö", "o").replace("ü", "u"))){

		author_total_count++;
	
	}else{
		
		String[] transpose_doc  = Initial_Bump_splitter.bump_splitter(docauthor[ii]).trim().split(" ");
		String[] transpose_docx = Initial_Bump_splitter.bump_splitter(xdocauthor_size[ii]).trim().split(" ");
		System.out.println(docauthor[ii]+" "+xdocauthor_size[ii]);
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, transpose_doc);
		
		Set<String> set1 = new HashSet<String>();
		Collections.addAll(set1, transpose_docx);
		
		int count=0;
			
		System.out.println(transpose_doc.length+" size "+transpose_docx.length);
	
		if(transpose_doc.length==transpose_docx.length){
		
		for(String namedoc:set){
			
			for(String namedocx:set1){	
			
				if(namedoc.trim().equalsIgnoreCase(namedocx.trim().replace("ä", "a").replace("ö", "o").replace("ü", "u"))){
//					System.err.println(namedocx+" "+namedoc);
					count++;										
				}else{
				
					
					if(umlaut_check(namedoc,namedocx,docauthor[ii])==true){
						count++;
					}
						
				}
			}
			
		}
		

		System.out.println(count+" "+set.size());
		if(count==set.size()){
			System.out.println("Name parts Match"+count);
			author_total_count++;
		}else{
			System.out.println("Name parts mismatch"+count);
		}
		
	}//TODO ELSE not equal parts
		
	}
	
	}
		
		System.out.println(author_total_count+" match count "+total_docauthor);
		
		if(author_total_count==total_docauthor){
		System.out.println("AUTHOR MATCH");	
		}else if(author_total_count!=total_docauthor){
		
			System.out.println("MISMATCH");
		
		}
		
	}else{
	


	}
	
	
}


private static boolean umlaut_check(String namedoc, String namedocx,String docauthor){
	
	String[] umlauts = {"ä","ö","ü"};
	
	for(int i=0;i<umlauts.length;i++){
		
		if(namedocx.contains(umlauts[i])){
					
			int um_char_pos = namedocx.indexOf(umlauts[i]);
			
			if(!(docauthor.charAt(um_char_pos)>='a'&&'z'<=docauthor.charAt(um_char_pos))){
		
				if(namedoc.charAt(um_char_pos+1)=='e'){
				
//					System.err.println(um_char_pos+"true "+namedoc.charAt(um_char_pos+1));
				
					return true;
				
				}
				
			}
		}
				
	}
	
		
	return false;
}
}
