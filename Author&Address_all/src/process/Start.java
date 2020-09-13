package process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import misc.Normalizer_author;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ddf.EscherColorRef.SysIndexProcedure;
import org.apache.poi.ss.formula.functions.Match;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.JSONParser;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import com.author.support.Initial_Bump_splitter;

import application.Index;
import resource.Abbreviation;
import resource.Excel_resource;
import resource.Foreign_insignificant;
import resource.Insignificant_words;
import resource.Word_endings;
import resource.Word_endings2;

public class Start {
//first commit and received and here is the response
	public static String acc = "";
	private static String name="";
	private static String itemno="";
	private static PrintWriter out;
	private static ArrayList<String> ar;
	private static ArrayList<String> ar_acc;
	private static ArrayList<String> ar_itemno;
	private static ArrayList<String> ar_Name;
	private static ArrayList<String> ar_cat;
	private static ArrayList<String> ar_type;
	public static HashSet<String> we_collect_list;
	
	public static void Start_1() throws IOException, NoSuchFieldException, SecurityException{
		
		Excel_resource.new_resource();
		we_collect_list = new HashSet<String>();
		FileWriter out_we =new FileWriter("C:\\Address_test_all\\Author&Address_all\\DATA\\new_wordendings.txt",true);
		out = new PrintWriter(new File("C:\\Address_test_all\\Author&Address_all\\OUTPUT\\output.html"));	
		ar = new ArrayList<String>();
		ar_acc = new ArrayList<String>();
		ar_itemno = new ArrayList<String>();
		ar_Name = new ArrayList<String>();
		ar_type=new ArrayList<String>();
		ar_cat = new ArrayList<String>();
		out.write("<html><body bgcolor=\"CCF7FF\"><table border=\"1\" style=\"width:50%\" bgcolor=\"9999FF\">");
		FileInputStream f = new FileInputStream(new File("C:\\Address_test_all\\Author&Address_all\\Excel\\input.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(f);
		XSSFSheet sheet = wb.getSheetAt(0);

		FileInputStream fis = new FileInputStream(new File("C:\\Address_test_all\\Author&Address_all\\Excel\\stats.xlsx"));
		XSSFWorkbook xwb = new XSSFWorkbook(fis);
		XSSFSheet xsheet = xwb.getSheetAt(0);
		
		String col1="";
		String col2="";
		String col3="";
		
		for(int j=0;j<sheet.getLastRowNum()+1;j++){
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String x_content="";
			String content ="";
			
			boolean xml_status=true;
			boolean br_item=false;
			
			Row r = sheet.getRow(j);	
			Cell ce = r.getCell(0);
			Cell c2 = r.getCell(1);
			Cell c3 = r.getCell(2);
			
			col1 = ce.toString();
			col2 = c2.toString();
			col3 = c3.toString();
			System.out.println(col2);
			
//			String finalcol2 = String.format("%03d", Math.round(Float.parseFloat(col3))+1);					
//			String xmlfilename = "U:\\"+col2+"\\ITEMS\\E0000"+finalcol2+".IDT";
//			String xmlfilename = "C:\\Author_test\\thiyagu\\xml\\"+col2+"\\ITEMS\\E0000"+finalcol2+".IDT";	
			
			String finalcol2idt = String.format("%03d", Math.round(Float.parseFloat(col3)));
//			String aidt= "C:\\Author_test\\thiyagu\\Files\\"+col2+"\\ITEMS\\"+finalcol2idt+"A.IDT";
			String aidt = "W:\\"+col2+"\\ITEMS\\"+finalcol2idt+"A.IDT";
			
			String filename = "W:\\"+col2+"\\ITEMS\\"+finalcol2idt+"D.IDT";
//			String filename = "C:\\Author_test\\thiyagu\\Files\\"+col2+"\\ITEMS\\"+finalcol2idt+"D.IDT";
			File file = new File(filename);
			File filex = new File(aidt);
			
			name = col1;
			acc= col2;
			try{
			content = FileUtils.readFileToString(file,"UTF-8");
			}catch(FileNotFoundException fg){
				
				continue;
			}
			
			try{
			x_content = FileUtils.readFileToString(filex,"UTF-8");
			}catch(FileNotFoundException fg1){
				
				xml_status=false;
			}
			
			itemno = file.getName().replace("D.IDT", "");
			
			Element doc = Jsoup.parse(content);
			Element docx = Jsoup.parse(x_content);
		
			if(doc.getElementsByTag("itemtype").text().equals("BR")|doc.getElementsByTag("itemtype").text().equals("RV")){
				
				br_item = true;
				
			}
			
			
			
			
			
			String mail="";
			String maile[] = {".en",".corn","l26.com","l63.com","l62.com","uniromal.com"};

			for(int i=0;i<doc.getElementsByTag("email").size();i++){

				mail = doc.getElementsByTag("email").get(i).text();
				
				for(int em =0;em<maile.length;em++){

					if(mail.endsWith(maile[em])){
//						System.out.println("Mail spelling Error at"+mail);
						ar.add("Mail Spell Error : "+mail);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("E-mail");
						ar_type.add("Author");
					}
				}

				char g=mail.charAt(mail.length()-1);
				if(g <= 47 && g >= 32 ){
//					System.out.println("Punctuation at end of Email");
					ar.add("Punctuation at end of Email ID : "+mail);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("E-mail");
					ar_type.add("Author");	
				}
			}
			
	//============================================================================================================TODO
					
			String val[]={"prime","subsup","currency","bullet","section","paragraph","dagger","indicators","Diaeresis","degree"};
						
			for(int junk=0;junk<val.length;junk++){
				
			for(int author_junk=0;author_junk<doc.getElementsByTag("author").size();author_junk++){
				
				String author=doc.getElementsByTag("author").get(author_junk).attr("imtxt");
				if(author.contains(val[junk])){
//					System.out.println("error");
				ar.add("Junk values present : "+val[junk]);
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_type.add("Author");
				ar_cat.add("Junk Values");
				}
				
				if(doc.getElementsByTag("author").get(author_junk).text().contains(val[junk])){
					ar.add("Junk values present : "+val[junk]);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Junk Values");
					ar_type.add("Author");
				}
			}
}
			
		//============================================================================================================TODO
			
			
			Pattern nam_init = Pattern.compile("[a-z][A-Z]\\. |[A-Z]\\.[A-Z]\\. |[A-Z]\\.[A-Z]\\.[A-Z]\\. |[A-Z][A-Z] \\[|[A-Z][A-Z][A-Z] \\[|[A-Z][A-Z]. \\[|[A-Z][A-Z][A-Z]. \\[");

			for(int ini_bump=0;ini_bump<doc.getElementsByTag("author").size();ini_bump++){
				String author = doc.getElementsByTag("author").get(ini_bump).attr("imtxt");
				String wauthor = doc.getElementsByTag("author").get(ini_bump).text();
				
				if(author.trim().contains("  ")){
//					System.out.println("Double Space Present in Fsa Author : "+author);
					ar.add("Double Space : "+author);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_type.add("Author");
					ar_cat.add("Double Space");
			
				}
				
				if(wauthor.trim().contains("  ")){
//					System.out.println("Double Space Present in Wos Author : "+wauthor);	
		
					ar.add("Double Space : "+wauthor);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_type.add("Author");
					ar_cat.add("Double Space");
			
				}

				
				
				Matcher nam_initm = nam_init.matcher(author);
				if(nam_initm.find()){
					ar.add("Initial Bump : "+nam_initm.group());
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_type.add("Author");
					ar_cat.add("Initial Bump");
				}
			
			//--------TODO
				Pattern vand = Pattern.compile("[V]an [D][e]n\\s{1,2}|[V]an [D][e]r\\s{1,2}|[V]an [D][e]\\s{1,2}");
				Matcher mv1 = vand.matcher(author);	
				Matcher mv2 = vand.matcher(wauthor);

				if(mv1.find()){
//					System.out.println("Van De Present pls check FSA :"+mv1.group()+acc);
					ar.add("Van De Present pls check FSA :");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Vande Casing");
					ar_type.add("Author");
				}
				if(mv2.find()){
//					System.out.println("Van De Present pls check WOS : "+mv2.group()+acc);
					ar.add("Van De Present pls check WOS : ");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Vande Casing");
					ar_type.add("Author");
				}
			
			//----TODO
			
				String wos_initial = wauthor.substring(wauthor.indexOf(",")+1).trim();

				if(author.contains("-ichi")){

					String initial_fsa = author.charAt(0)+""+author.toUpperCase().charAt(author.indexOf("-")+1);
					if(wos_initial.equals(initial_fsa)){
//						System.out.println("please check japanese author : "+author);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Japanese Casing");
						ar_type.add("Author");
					}
				}
				
			}		
			
			chinese(doc);
			group_author(doc);
//			linking(doc);
			//					umlauts(doc); throws exception
			//					particle
			//					turkish
			//					umlauts expansion
			
			Address(doc, acc, itemno);
			
			if(xml_status==true){
				if(br_item==false){
				try{
					if(doi(docx)==null){
						ar.add("DOI not Found");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("DOI Not Found");
						ar_type.add("Author_check");
						continue;
					}
					
					String url = "https://api.crossref.org/v1/works/"+doi(docx);
					Object odoc =  Jsoup.connect(url).ignoreContentType(true).get().body().text();
					
					Author_check(odoc,doc);
					System.out.println(url.toString());					
					String a = Address_check.address_check(odoc,doc,docx,we_collect_list,out,col2,col3);	
					
					if(a.equals("N/A")){
						ar.add("N/A");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("N/A");
						ar_type.add("Address_check");
						
					}else if(a.equals("N/A (CR)")){
					
						ar.add("N/A (CR)");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("N/A");
						ar_type.add("Address_check");
						
					}else if(a.equals("Address Mismatch")){
						
						ar.add("Address count mismatch");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Address Mismatch");
						ar_type.add("Address_check");
						
					}else if(a.equals("Address Correct")){
						
						ar.add("Address Correct");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Address Match");
						ar_type.add("Address_check");
						
					}else{
						ar.add("Address Incorrect");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Address Mismatch");
						ar_type.add("Address_check");
					}
			}catch(HttpStatusException sdn){
//				sdn.printStackTrace();
//				System.out.println("DOI not found");
				ar.add("CR not Found");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("CR Not Found");
				ar_type.add("Author_check");
				continue;
			}catch (IndexOutOfBoundsException ie) {
				ie.printStackTrace();
//				System.out.println("Cross Reference not found");
				ar.add("Cross Reference not found");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Cross Reference not found");
				ar_type.add("Author_check");
				continue;
			}catch (NullPointerException nes) {
				nes.printStackTrace();
				continue;
			}catch(Exception ste){
				ste.printStackTrace();
				continue;
			}
			}
			}
		}
		System.out.println("Size "+ar.size());
		wb.close();
	
		for(int err_row=0;err_row<ar.size();err_row++){
			Row s = xsheet.createRow(err_row+1);
			
			s.createCell(0);
			s.createCell(1);
			s.createCell(2);
			s.createCell(3);
			s.createCell(4);
			s.createCell(5);
			
			Cell c = s.getCell(0);
			Cell c1 = s.getCell(1);
			Cell c2 = s.getCell(2);
			Cell c3 = s.getCell(3);
			Cell c4 = s.getCell(4);
			Cell c5 = s.getCell(5);
			
		try{
			c.setCellValue(ar_Name.get(err_row));
			c1.setCellValue(ar_acc.get(err_row));
			c2.setCellValue(ar_itemno.get(err_row));
			c3.setCellValue(ar_type.get(err_row));
			c4.setCellValue(ar_cat.get(err_row));
			c5.setCellValue(ar.get(err_row));
		}catch(IllegalArgumentException iae){
			iae.printStackTrace();
			continue;
		}
		}
		
		FileOutputStream fos = new FileOutputStream(new File("C:\\Address_test_all\\Author&Address_all\\Excel\\stats.xlsx"));
		xwb.write(fos);
		xwb.close();
		
		//TODO----------------------------------------------------------

		WE_collect_driver.we_col_driver(we_collect_list,out_we);

		out.write("</table></body></html>");
		out.close();
		out_we.close();
		System.out.println("Completed.");

	}


	private static void Author_check(Object odoc, Element doc) throws IOException{
		
		ArrayList<String> ar_author = new ArrayList<String>();
		ArrayList<String> document_author = new ArrayList<String>();
				
		for(int ii=0;ii<doc.getElementsByTag("author").size();ii++){
			
			String docauthor = doc.getElementsByTag("author").get(ii).attr("imtxt").replace("$A-O$", "").replace("[", "").replace("]", "");
			document_author.add(docauthor);
		}
				
		try{
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(odoc.toString());
			
			org.json.simple.JSONObject job = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONObject arr = (org.json.simple.JSONObject) job.get("message");
			org.json.simple.JSONArray arr1 = (org.json.simple.JSONArray) arr.get("author");
			
			for(int i=0;i<arr.size();i++){
			
				try{
							
				org.json.simple.JSONObject au2 = (org.json.simple.JSONObject) arr1.get(i);
				
				String author="";
				String fauthor="";
				
				
				if(au2.get("given")==null){
					
				}else{
					author = (String) au2.get("given");
				}
				
				if(au2.get("family")==null){
					
				}else{
					fauthor = (String) au2.get("family");	
				}	
				
				
				System.out.println("(given) "+author+" (family) "+fauthor);
				ar_author.add(author.trim()+" "+fauthor.trim());
				
				}catch(Exception e){
				
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		
				System.out.println("Preview crossref:"+ar_author.toString().replace("[", "").replace("]", "").replace("  ", " "));
				
				String[] symbols = {"á","À","â","ă","ā","ã","å","ą","Â","æ","ć","č","ç","ď","Đ","ð","Ɖ","é","è","ė","ê","ë","ě","ę","ȩ","ǧ","ğ","ı","í","ì","İ","î","ï","ĭ","ị","Ľ","ł","ń","ň","ñ","Ó","ò","ô","õ","ő","Ø","œ","Ř","ś","Š","ş","ș","ß","ť","ţ","Þ","ú","ù","û","ū","ů","ű","ý","ÿ","ź","ż","Ž","β","А","К","Ä","à","ó","ø","Ö","Ü","ç","É","Å","Ñ","Ç","ž","Ş","Ś","Á","Č","š","ř","Ż","Ł","Ú","ӧ","Ć","ț","ľ","ō","đ","ó","í","ũ"};
				String[] alphabet= {"a","A","a","a","a","a","a","a","A","ae","c","c","c","d","D","d","D","e","e","e","e","e","e","e","e","g","g","i","i","i","I","i","i","i","i","L","l","n","n","n","O","o","o","o","o","O","oe","R","s","S","s","s","ss","t","t","th","u","u","u","u","u","u","y","y","z","z","Z","ss","A","K","A","a","o","o","O","U","c","E","A","N","C","z","S","S","A","C","s","r","Z","L","U","o","C","t","l","o","d","o","i","u"};
				
//				String[] symbols = {"Ä","ă","ą","ã","á","à","â","ę","ě","è","é","ê","ë","ė","İ","ï","í","ì","î","ı","ł","ñ","ò","ó","ô","ø","Ø","ù","ú","û","ÿ","ý","Ö","Ü","ç","å","É","Å","Ñ","ń","Ç","ğ","ż","ž","ś","ş","Ş","Ś","æ","Á","ţ","Č","ć","č","ß","Š","š","ř","Ž","Ż","Ł"};
//				String[] alphabet= {"A","a","a","a","a","a","a","e","e","e","e","e","e","e","I","i","i","i","i","i","l","n","o","o","o","o","O","u","u","u","y","y","O","U","c","a","E","A","N","n","C","g","z","z","s","s","S","S","ae","A","t","C","c","c","ss","S","s","r","Z","Z","L"};
				
				// ū , œ, Ó , ź, ð
				
				String ar_author_symbol_removed=ar_author.toString().replace("[", "").replace("]", "").replace("  ", " ");
				
				for(int dia=0;dia<symbols.length;dia++){
					
					ar_author_symbol_removed = ar_author_symbol_removed.replace(symbols[dia], alphabet[dia]);
				
				}
				
				//TODO Casing
				
				String ar_casing_corrected = ar_author_symbol_removed;
				
				if(StringUtils.isAllUpperCase(ar_author_symbol_removed.replace(" ", "").replace(",", "").replace(".", "").replace("-", ""))==true){
					
						ar_casing_corrected = WordUtils.capitalizeFully(ar_casing_corrected);
				}
				
							
				System.out.println("replaced: "+ar_casing_corrected);
				String[] xdocauthor_size = Normalizer_author.normal_author(ar_author_symbol_removed).trim().split(", ");
				
				
				int aut_size = doc.getElementsByTag("author").size();	
				System.out.println(xdocauthor_size.length+" <-Size-> "+aut_size);
				
				if(xdocauthor_size.length==aut_size){
					
					int author_total_count=0;
					int total_docauthor=aut_size;
					
					for(int ii=0;ii<aut_size;ii++){
			
				String docauthor = doc.getElementsByTag("author").get(ii).attr("imtxt").replace("$A-O$", "").replace("[", "").replace("]", "").replace(".", " ").replace("Jr", "").replace("III", "").replace("Sr", "").replace("II", "").replace("  ", " ");
								
				System.out.println(docauthor+" -:- "+xdocauthor_size[ii]);
				
				if(docauthor.equalsIgnoreCase(xdocauthor_size[ii].replace("ä", "a").replace("ö", "o").replace("ü", "u"))){

					author_total_count++;
				
				}else{
					System.out.println(docauthor+" "+xdocauthor_size[ii]);
				

					String[] transpose_doc  = Initial_Bump_splitter.bump_splitter(docauthor).trim().split(" ");
					String[] transpose_docx = Initial_Bump_splitter.bump_splitter(xdocauthor_size[ii]).trim().replace(" ", " ").split(" ");

					Set<String> set = new HashSet<String>();
					Collections.addAll(set, transpose_doc);
					
					Set<String> set1 = new HashSet<String>();
					Collections.addAll(set1, transpose_docx);
					
					int count=0;
					System.out.println(transpose_doc.length+" size "+transpose_docx.length);
				if(transpose_doc.length==transpose_docx.length){
					
					for(String namedoc:set){
						
						for(String namedocx:set1){	
//						System.err.println(namedocx+" "+namedoc);
							if(namedoc.trim().equalsIgnoreCase(namedocx.trim().replace("ä", "a").replace("ö", "o").replace("ü", "u"))){
								count++;										
							}else{
							
								if(umlaut_check(namedoc,namedocx,docauthor)==true){
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
					
					if(ar_author.isEmpty()==true){
						ar.add("Author Not found");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Author Not found");
						ar_type.add("Author_check");
					
					}else if(author_total_count==total_docauthor){
						ar.add("Author Spell correct"+ar_author_symbol_removed.replace("Authors:", "").replace("ä", "a").replace("ö", "o").replace("ü", "u")+"$"+document_author);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Author Match");
						ar_type.add("Author_check");
						}else if(place_match(doc, ar_author_symbol_removed)==true){
							

							ar.add("Author Spell correct"+ar_author_symbol_removed.replace("Authors:", "").replace("ä", "a").replace("ö", "o").replace("ü", "u")+"$"+document_author);
							ar_itemno.add(itemno);
							ar_acc.add(acc);
							ar_Name.add(name);
							ar_cat.add("Author Match");
							ar_type.add("Author_check");			
							
						}else{
							
							ar.add(ar_author_symbol_removed.replace("Authors:", "").replace("ä", "a").replace("ö", "o").replace("ü", "u")+"$"+document_author);
							ar_itemno.add(itemno);
							ar_acc.add(acc);
							ar_Name.add(name);
							ar_cat.add("Author Mismatch");
							ar_type.add("Author_check");
							
						}
					
				}else{
				
					ar.add(ar_author_symbol_removed.replace("Authors:", "").replace("ä", "a").replace("ö", "o").replace("ü", "u")+":"+document_author);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Author Missing");
					ar_type.add("Author_check");
				
					}//
				
	}
	
	
	
	private static boolean place_match(Element doc,String ar_symbol_removed){
		
		Set<String> set_p = new HashSet<String>();
		Set<String> set_xp = new HashSet<String>();
		
		int aut_size = doc.getElementsByTag("author").size();
		
	
		Collections.addAll(set_xp, ar_symbol_removed.replace("Authors:", "").replace("Jr", "").replace("III", "").replace("Sr", "").replace("II", "").replace(",", "").replace("-", " ").replace("  ", "").trim().split(" "));
		
		for(int i=0;i<aut_size;i++){
			
			Collections.addAll(set_p, doc.getElementsByTag("author").get(i).attr("imtxt").replace("$A-O$", "").replace("[", "").replace("]", "").replace(".", " ").replace("Jr", "").replace("-", " ").replace("III", "").replace("Sr", "").replace("II", "").replace("  ", " ").split(" "));
		}
		
	int count=0;
		if(set_p.size()==set_xp.size()){
			
			for(String m: set_p){
				
				for(String m1:set_xp){
					if(m.equalsIgnoreCase(m1)){
				count++;		
					}
					
				}
				
				
			}
	
		}
		
	if(set_p.size()==count){
//		System.out.println("Placement author match");
	return true;
	}
		
		
		
		
		return false;
	}
	
	
	private static boolean umlaut_check(String namedoc, String namedocx,String docauthor){
		
		String[] umlauts = {"ä","ö","ü"};
		
		for(int i=0;i<umlauts.length;i++){
			
			if(namedocx.contains(umlauts[i])){
						
				int um_char_pos = namedocx.indexOf(umlauts[i]);
				
				if(!(docauthor.charAt(um_char_pos)>='a'&&'z'<=docauthor.charAt(um_char_pos))){
			
					if(namedoc.charAt(um_char_pos+1)=='e'){
					
//						System.err.println(um_char_pos+"true "+namedoc.charAt(um_char_pos+1));
					
						return true;
					
					}
					
				}
			}
					
		}
		
		
		
		
		
		return false;
	}
	
	
	
	private static void group_author(Element doc) throws FileNotFoundException, UnsupportedEncodingException, NoSuchFieldException, SecurityException{

		int author_size = doc.getElementsByTag("author").size();

		for(int i=0;i<author_size;i++){

			if(doc.getElementsByTag("author").get(i).text().contains("(G)")){
				
				String[] eng = doc.getElementsByTag("author").get(i).text().trim().split(" ");

				for(String m:eng){

					for(int j=0;j<Abbreviation.abbr.length;j++){		
						if(m.equals(Abbreviation.abbr[j])){
							
//							System.out.println(m+" - "+Abbreviation.abb[j]);			
							ar_itemno.add(itemno);
							ar_acc.add(acc);
							ar_Name.add(name);
							ar.add("Group Author Abbreviation missing :"+m+" - "+Abbreviation.abb[j]);
							ar_cat.add("Abbreviation");
							ar_type.add("Author");
	
						}
					}

					for(int k=0;k<Insignificant_words.insig_list.length;k++){

						if(m.equals(Insignificant_words.insig_list[k])){
//							System.out.println(m+" - "+Insignificant_words.insig_list[k]);					
							ar_itemno.add(itemno);
							ar_acc.add(acc);
							ar_Name.add(name);
							ar.add("Group Author Insignificant found :"+m+" - "+Abbreviation.abb[k]);
							ar_cat.add("Insignificant Word");
							ar_type.add("Author");

						}							
					}

					for(int l=0;l<Foreign_insignificant.finsig_list.length;l++){

						if(m.equals(Foreign_insignificant.finsig_list[l])){
//							System.out.println(m+" - "+Foreign_insignificant.finsig_list[l]);
							ar_itemno.add(itemno);
							ar_acc.add(acc);
							ar_Name.add(name);
							ar_cat.add("Foreign Insignificant Word");
							ar_type.add("Author");
							ar.add("Group Author Foreign Insignificant found :"+m+" - "+Abbreviation.abb[l]);
						}		
					}		
				}
			}
		}
	}

	

	private static void chinese(Element doc){

		int gks = doc.getElementsByTag("author").size();

		for(int gi=0;gi<gks;gi++){

			String g1 = doc.getElementsByTag("author").get(gi).text();
			String g2 = doc.getElementsByTag("author").get(gi).attr("imtxt").replace("$A-O$", "").replace("[", "").replace("]", "");
		
			String[] collect = doc.getElementsByTag("author").get(gi).attr("addrid").split(",");	
			
			try{
				if(check_korean(collect,doc)==true){
			
				continue;
			}
			}catch(NumberFormatException e){
				e.printStackTrace();
				continue;
			}catch(NullPointerException ne){
				ne.printStackTrace();continue;
			}
			
			
			if(g1.contains(",")){

				if(g2.contains(".")){

					continue;

				}

				int ni = g1.indexOf(",")+1;

				String initg1 = g1.substring(ni).replace("Jr", "").replace("Sr", "").trim();	

				String a[] = {"Gen","Gang","Hao","Zheng","Sa","Shuao","Zuo","Zu","Zou","Zong-Zheng","Zong","Zi","Zhuo","Zhu-Ge","Zhuang","Zhuan","Zhu","Zhou","Zhong","Zhing-Sun","Zhing-Li","Zhi","Zhen","Zhang","Zhan","Zeng","Ze","Zang","Zan","Zai","Yung","Yun","Yueh","Yue","Yu-Ch'iu","Yu-Chi","Yuan","Yu","You","Yong","Ying","Yin","Yi","Yen","Yeh","Ye","Yao","Yang","Yan","Ya","Xun","Xue","Xuan-Yuan","Xuan","Xu","Xiu","Xiong","Xing","Xin","Xie","Xiao","Xiang","Xian","Xia-Hou","Xia","Xi","Wu","Wo","Wen-Ren","Wen-Jen","Weng","Wen","Wei","Wang","Wan","Tsan","Ts'ai","Tsai","Tou","Tong","Ting","T'ien","Tiao","Tian","Ti","Teng","Te","T'ao","Tao","Tan-T'ai","Tan-Tai","T'ang","Tang","T'an","Tan","Tai-Shu","T'ai","Tai","Szu","Suo","Sung","Sun","Su","Song","So","Si-Tu","Si-Ma","Si-Kong","Si","Shun","Shui","Shuang","Shuai","Shu","Shou","Shih","Shi","Shen-T'u","Shen-Tu","Sheng","Shen","She","Shao","Shan-Yu","Shang-Kuan","Shang-Guan","Shang","Shan","Sha","Sang","Rui","Ruan","Ru","Rong","Ren","Rao","Ran","Que","Quan","Qiu","Qing","Qin","Qiao","Qiang","Qian","Qi","Qia","P'u-Yung","Pu-Yung","Pu-Yang","P'u","Pu","Po","Ping","Pien","Pieh","P'i","Pi","P'eng","Peng","Pen","P'ei","Pei","Pao","P'ang","Pang","P'an","Pan","Pai","Pa","Ouyang","Ou-Pu","Ou-Bo","Ou","Nung","Nong","Niu","Ning","Nieh","Nie","Nian","Ni","Neng","Na","Mu-Rong","Mu-Jung","Mu","Mo-Qi","Mo","Miu","Ming","Min","Mieh","Mie","Miao","Mian","Mi","Meng","Mei","Mao","Man","Ma","Luo","Lung","Luan","Lu","Lou","Long","Lo","Liu","Ling-Hu","Ling","Lin","Lien","Liao","Liang","Lian","Li","Leng","Lei","Lao","Lang","Lan","Lai","Kuo","Kung-Yeh","Kung-Yang","Kung-Sun","K'ung","Kung","Kui","K'uei","Kuei","Kuang","Kuan","K'uai","Kuai","Ku","K'ou","Kou","Kong","K'o","Ko","Keng","K'e","Ke","Kao","K'ang","Kang","K'an","Kan","Kai","Jung","Jun","Jui","Juan","Jun","Ju","Jing","Jin","Jie","Jiao","Jiang","Jian","Jia","Ji","Jen","Jao","Jan","Huo","Hung","Hui","Huang-P'u","Huang-Pu","Huang","Huan","Huai","Hua","Hu","Hsun","Hsueh","Hsuan-Yuan","Hsuan","Hsu","Hsiung","Hsing","Hsin","Hsien","Hsieh","Hsiao","Hsiang","Hsia-Hou","Hsia","Hsi","Hou","Hong","Ho-Lien","Ho","Heng","He-Lian","He","Hao","Hang","Han","Hai","Guo","Gui","Guang","Guan","Gu","Gou","Gong-Yang","Gong-SunGong-Ye","Gong","Geng","Ge","Gao","Gan","Gai","Fu","Feng","Fei","Fang","Fan","Duan","Du","Dou","Dong","Ding","Diao","Di","Deng","De","Dang","Dai","Cui","Cong","Ch'un-Yu","Chun-Yu","Chung-Sun","Chung-Li","Ch'ung","Chung","Chun","Chu-Ko","Chueh","Chuang","Ch'uan","Chuan","Ch'u","Chu","Chou","Chong","Cho","Ch'iu","Ching","Ch'in","Chih","Ch'ien","Chien","Ch'iao","Chiao","Ch'iang","Chiang","Chia","Ch'i","Chi","Ch'eng","Cheng","Ch'en","Chen","Ch'e","Che","Ch'ao","Chao","Ch'ang","Chang","Chan","Chai","Ch'a","Cha","Cen","Cao","Cang","Can","Cai","Bu","Bo","Bin","Bie","Bian","Bi","Ben","Bei","Bao","Ban","Bai","Ba","An"};

				for(int gj=0;gj<a.length;gj++){


					if(g1.substring(0, ni-1).equals(a[gj])){
						String g3="";
						String g4="";
						try{
						
						g3 = g2.replace("$A-O$", "");
						g4 = g3.substring(0,g3.indexOf(" "));
						}catch(StringIndexOutOfBoundsException ser){
							ser.printStackTrace();
						continue;
						}
						
						if(doc.getElementsByTag("author").get(gi).attr("imtxt").replace("$A-O$", "").startsWith("[")){
							g4 = g3.substring(g3.indexOf(" ")+1,g3.length());
						}

						String eng[]=g3.split(" ");

						if(eng.length>2){

							continue;
						}
						int si=0,sj1=0,sk=0;

						while(!g4.equals(a[sk])){	
							sk++;
							if(sk==a.length)
								break;

						}
						if(sk>=a.length){

							for(;si<a.length;si++){

								if(g4.startsWith(a[si])){

									String s1 = g4.substring(a[si].length());

									for(sj1=0;sj1<a.length;sj1++){

										if(s1.equals(a[sj1].toLowerCase())){

											break;

										}if(sj1==a.length-1)
											break;

									}

									if(g4.equals(a[si]+a[sj1].toLowerCase())){

										/*				for(int krn=0;krn<1;krn++){
						if(chechkorean(a[si])){
						continue;
						}
						if(chechkorean(a[sj1])){
						continue;
						}
						}
										 */	

										String str = a[si].charAt(0)+""+s1.charAt(0);
										str=str.toUpperCase().trim();

//														System.out.println(str.toUpperCase()+" - "+s1+" - "+a[si]);
														

										if(!str.equals(initg1)){

//											System.out.println(str.length()+" not equal "+initg1.length());
//											System.out.println("chinese syllable missing: suggest: "+str+" present: "+initg1+" ("+g3+")");
											
											ar_itemno.add(itemno);
											ar_acc.add(acc);
											ar_Name.add(name);
											ar.add("Chinese syllable missing, suggest: "+str+" present: "+initg1+" ("+g3+")");
											ar_cat.add("Chinese Syllable");
											ar_type.add("Author");

											
											break;
										}
									
									}	
								}
							}		
						}
					}
				}
			}
		}
	}

	
		public static void Address(Element doc,String acc, String itemno) throws NoSuchFieldException, SecurityException, IOException{

			int research_author_link = doc.getElementsByTag("author").size();
			int address = doc.select("rsaddr").size();
			int linking_count=0;
			String unlink_author = "";
			if(doc.getElementsByTag("itemtype").text().equals("ED")){
				
			}else{
			for(int i=0;i<research_author_link;i++){

				if(doc.getElementsByTag("author").get(i).text().contains("(B)")){
					continue;
				}
				if(doc.getElementsByTag("author").get(i).text().contains("(G)")){
					continue;
				}
				
				String[] collect = doc.getElementsByTag("author").get(i).attr("addrid").trim().split(",");
				
				String[][] collect1 = {collect};
				try{
//					System.out.println(collect1[0][1]);
				}catch(ArrayIndexOutOfBoundsException e){
//					e.printStackTrace();
				}
				if(doc.getElementsByTag("author").get(i).attr("addrid")==""&&doc.outerHtml().toString().contains("rsaddr")){
				
					if(doc.getElementsByTag("author").get(i).attr("rpaddrid")!=""){
						continue;
					}
				
					linking_count++;
					
					unlink_author = doc.getElementsByTag("author").get(i).text();
				}			
		
			
			}
			if(linking_count==1){
				System.out.println("Linking Missed AuthorID: "+unlink_author);
				ar.add("Linking Missed AuthorID: "+unlink_author);
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Linking");
				ar_type.add("Address");
			}	
			}
		
		//---------------------------------------------------------------------------------------------------------------------

		Itemtype(doc,acc,itemno);

		String suborg1="";
		String suborg2="";
		String suborg3="";
		String mainorg ="";
		String street = "";
		String city="";
		String country="";
		String pcodeap;
		String pcodeac;	
		String state ="";
		String rsaddr="";
		String pcodebc;
		
		
		String[] arr = new String[address];
		
			
		
		for(int j=0;j<address;j++){	
			
		boolean rp = doc.getElementsByTag("rsaddr").get(j).attr("rpAddr").equals("Y");	
			
			try{
				rsaddr = doc.select("rsaddr").get(j).text();	
			}catch(IndexOutOfBoundsException e){
				
				rsaddr="";
			}
				try{
				mainorg = doc.select("rsaddr").get(j).getElementsByTag("mainorg").text();
			}catch(IndexOutOfBoundsException e){
				
				mainorg="";
			}
			try{
				suborg1 = doc.select("rsaddr").get(j).getElementsByTag("suborg1").text();
				
			}catch(IndexOutOfBoundsException e){
				
			suborg1="";
			}
			
			try{
				suborg2 = doc.select("rsaddr").get(j).getElementsByTag("suborg2").text();
			}catch(IndexOutOfBoundsException e){
				
			suborg2="";
			}
			try{
			suborg3 = doc.select("rsaddr").get(j).getElementsByTag("suborg3").text();
			}catch(IndexOutOfBoundsException e){
				
			suborg3="";
			}
			
			try{
				street = doc.select("rsaddr").get(j).getElementsByTag("street").text();
			}catch(IndexOutOfBoundsException e){
				
			street="";
			}
			try{
				city = doc.select("rsaddr").get(j).getElementsByTag("city").text();
			}catch(IndexOutOfBoundsException e){
				
			city="";
			}
			try{
				state = doc.select("rsaddr").get(j).getElementsByTag("state").text();
			}catch(IndexOutOfBoundsException e){
				
			state="";
			}
			try{
				country = doc.select("rsaddr").get(j).getElementsByTag("country").text();
			}catch(IndexOutOfBoundsException e){
				
			country="";
			}
			try{
				pcodeap = doc.select("rsaddr").get(j).getElementsByTag("pcodeap").text();
			}catch(IndexOutOfBoundsException e){
				
			pcodeap="";
			}
			try{
				pcodeac = doc.select("rsaddr").get(j).getElementsByTag("pcodeac").text();
			}catch(IndexOutOfBoundsException e){
				
			pcodeac="";
			}
			try{
				pcodebc = doc.select("rsaddr").get(j).getElementsByTag("pcodebc").text();
			}catch(IndexOutOfBoundsException e){
				pcodebc="";
				
			}
			
			
//			System.out.println(mainorg+suborg1+suborg2+suborg3+city+state+country+pcodeap+pcodeac+pcodebc);
			
			
			
			
			
			
			
			//Duplicate Address
				
			arr[j] = mainorg+suborg1+suborg2+suborg3+street+city+state+country+pcodeap+pcodeac+pcodebc;
			
		
			//TODO USA Zip codes
			
			
			if(country.equals("USA")&&rp==true){
//				System.out.println("true usa rp");
			if(pcodeap.length()<5&&pcodeap!=""){
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("USA Address Zipcode does not match Length : "+pcodeap);
				ar_cat.add("Zipcode");
				ar_type.add("Address");

//				System.out.println("USA Address Zipcode does not match Length");
			}
			}	
				
			if(country.equals("USA")){
				if(pcodeap.length()>0){
			try{
				 Integer.parseInt(pcodeap);
			}catch(java.lang.NumberFormatException v){
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("USA Address Zipcode contains Alphabet");
				ar_cat.add("Zipcode");
				ar_type.add("Address");
				v.printStackTrace();
//				System.out.println("USA Address Zipcode contains Alphabet : "+pcodeap);
			
			}
			}
			}
			//TODO two word university
	
			
			if(mainorg.equals("Washington Univ")&&country.equals("USA")){
				
				if(state.equals("MO")){
					
				}else {
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Check 2 word University Mismatch : "+mainorg);
					ar_cat.add("2 word University");
					ar_type.add("Address");

				}
				
			}else if(mainorg.equals("Univ Washington")&&country.equals("USA")){
				
				if(state.equals("WA")){
					
				}else {
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("2 Word University");
					ar_type.add("Address");

					ar.add("Check 2 word University Mismatch : "+mainorg);
				
				}
				
			}
			
			
			if(mainorg.equals("Trent Univ")){
				
			if(country.equals("Canada")){
				
			}else if(country.equals("Italy")){
//				System.out.println("Check 2 word University Mismatch : "+mainorg);
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Check 2 word University Mismatch : "+mainorg);
				ar_cat.add("2 Word University");
				ar_type.add("Address");
			}
			}
			
			if(mainorg.equals("Univ Trent")){
				
				if(country.equals("Italy")){
					
				}else if(country.equals("Canada")){
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Check 2 word University Mismatch : "+mainorg);
					ar_cat.add("2 Word University");
					ar_type.add("Address");
				}
				}
				
			
			
			if(mainorg.equals("Miami Univ")&&country.equals("USA")){
				if(state.equals("OH")){
					
				}else if(state.equals("FL")){
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Check 2 word University Mismatch : "+mainorg);
					ar_cat.add("2 Word University");
					ar_type.add("Address");
				}
				
			}else if(mainorg.equals("Univ Miami")&&country.equals("USA")){
				if(state.equals("FL")){
					
				}else if(state.equals("OH")){
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("2 Word University");
					ar_type.add("Address");
					ar.add("Check 2 word University Mismatch : "+mainorg);
				}
				
			}
			


			if(mainorg.equals("Valparaiso Univ")){
				
				if(country.equals("USA")){
					
				}else if(country.equals("Chile")){
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("2 Word University");
					ar_type.add("Address");
					ar.add("Check 2 word University Mismatch : "+mainorg);
				}
			}	
				
			if(mainorg.equals("Univ Valparasio")){
				
				if(country.equals("Chile")){
					
				}else if(country.equals("USA")){
//					System.out.println("Check 2 word University Mismatch : "+mainorg);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("2 Word University");
					ar_type.add("Address");
					ar.add("Check 2 word University Mismatch : "+mainorg);
					
			}
			}	
			
			
			
			//TODO the Faculties
			
			
			if(mainorg.equals("Australian Natl Univ")&&country.equals("Australia")){
				if(rsaddr.contains("The Faculties")){
					
					if(suborg1.equals("The Faculties")){
						
					}else if(suborg2.equals("The Faculties")){
						
					}else if (suborg3.equals("The Faculties")) {
						
					}else{
//						System.out.println("Check (The Faculties) not present in Sub Org: "+rsaddr); 
					
//						System.out.println("Check (The Faculties) not present in Sub Org: "+rsaddr);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar.add("Check (The Faculties) not present in Sub Org: "+rsaddr);
						ar_cat.add("Faculties");
						ar_type.add("Address");
					}
						
						
					
				}
			}
			
			//TODO Chinese Provinces/Major cities
			
			
			String[] china_prov={"Anhwei","Chekiang","Fukien","Heilungkiang","Honan","Hopeh(Hopei)","Hunan","Hupeh","Kansu","Kiangsi","Kiangsu","Kirin","Kwangtung","Kweichow","Liaoing","Shansi","Shantung","Shensi","Szechwan (Szechuan)","Tsinghai (Chinghai)","Yunnan","Hainan"};			
			String[] china_prov1={"Anhui","Zhejiang","Fujian","Heilongjiang","Henan","Hebei","Hunan","Hubei","Gansu","Jiangxi","Jiangsu","Jilin","Guangdong","Guizhou","Liaoing","“,”Shanxi","Shandong","Shaanxi","Sichuan","Qinghai","Yunnan"};


	
			if(country.equals("Peoples R China")){
				
				for(int chi=0;chi<china_prov1.length;chi++){				
	
				if(city.equals(china_prov1[chi])){
					
					if(city.equals("Jilin")&&state.equals("Jilin")){
						continue;
					}
					
//					System.out.println("Chinese Province processed as city : "+china_prov1[chi]); 
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Chinese Province processed as city : "+china_prov1[chi]);
					ar_cat.add("Chinese Province");
					ar_type.add("Address");
				}
				
			}	
			}
			//TODO APO and FPO
			
			if(country.equals("USA")&&rsaddr.contains("APO")){
				if(city.equals("APO")){
					
				}else{
					System.out.println("APO present in USA not processed as City : "+rsaddr);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("APO present in USA not processed as City : "+rsaddr);
					ar_cat.add("APO & FPO");
					ar_type.add("Address");
				}
				
			}
			
			if(country.equals("USA")&&rsaddr.contains("FPO")){
				if(city.equals("FPO")){
						
				}else{
//					System.out.println("FPO present in USA not processed as City : "+rsaddr);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("FPO present in USA not processed as City : "+rsaddr);
					ar_cat.add("APO & FPO");
					ar_type.add("Address");
				}
				
			}
			
		
			//TODO country codes
			String[] foreign_zone_country={"Argentina","Belgium","Brazil","Cyprus","Estonia","France","Hungary","Iceland","Israel","Latvia","Liechtenstein","Lithuania","Luxembourg","Monaco","Netherlands","Poland","Republic of Georgia","South Africa","Switzerland","Turkey","Ukraine","Vatican","Yugoslaviav"};
			String [] country_codes ={"RA","B","BR","CY","EE","F","H","IS","IL","LV","FL","LT","L","MC","NL","PL","GE","ZA","CH","TR","UA","V","YU"};
			
			for(int fz_count=0;fz_count<foreign_zone_country.length;fz_count++){
				
			
				if(country.trim().equals(foreign_zone_country[fz_count])){				
						
						if(pcodebc==null){
//							System.out.println("Zipcode missing");
						
						}else if(pcodebc.contains("-")){
							
							char[] r = pcodebc.toCharArray();
							int r_count = 0;
							for(char r1:r){
							
								if(r1=='-'){
									r_count++;
								}
								
							}
							if(r_count>1){
								
								continue;
								
							}				
							
							String pcodebc_c = pcodebc.substring(0, pcodebc.indexOf("-"));
//							System.out.println(pcodebc_c);
						if(!pcodebc_c.equals(country_codes[fz_count])){
							
					ar.add("Country code missing "+foreign_zone_country[fz_count]+" Processed : "+pcodebc+", Expected: "+country_codes[fz_count]);
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
//					System.out.println("Country code missing "+foreign_zone_country[fz_count]+" Processed : "+pcodebc+", Expected: "+country_codes[fz_count]);	
					ar_cat.add("Country Code");
					ar_type.add("Address");
					
						}
						}else{
							System.out.println("country code missing");
						}
			}
			}
			
			char[] r = pcodebc.toCharArray();
			int r_count = 0;
			for(char r1:r){
			
				if(r1=='-'){
					r_count++;
				}
				
			}
			if(r_count>1){
				System.out.println("Zipcode has more than 1 hyphen "+pcodebc);
				ar.add("Zipcode has more than 1 Hyphen "+pcodebc);
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);	
				ar_cat.add("Country Code");
				ar_type.add("Address");
				
			}		
			
			
			if(pcodebc==""){
//				System.out.println("zipcode incorrect for bulgaria");
			}else if(pcodebc.contains("-")){
		
				if(country.equals("Bulgaria")){
				
				if(pcodebc.contains("BG")){
					
				}else if(pcodebc.contains("BU")){
					
				}else{
					System.out.println("Country code incorrect for Bulgaria");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Bulgaria");
					}	
			}
			
				
				if(country.equals("Italy")){
					String pcodebc_italy = pcodebc.substring(0,pcodebc.indexOf("-"));
				if(pcodebc_italy.equals("I")){
					
				}else if(pcodebc_italy.equals("IT")){
					
				}else{
					System.out.println("Country code incorrect for Italy");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Italy");
				
				}
				}
				
				if(country.equals("Germany")){
					String pcodebc_germany = pcodebc.substring(0,pcodebc.indexOf("-"));
				if(pcodebc_germany.equals("D")){
					
				}else if(pcodebc_germany.equals("DE")){
					
				}else{
					System.out.println("Country code incorrect for Germany");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Germany");
				}
				}
				
				if(country.equals("Norway")){
					String pcodebc_norway = pcodebc.substring(0,pcodebc.indexOf("-"));
				if(pcodebc_norway.equals("N")){
					
				}else if(pcodebc_norway.equals("NO")){
					
				}else{
					System.out.println("Country code incorrect for Norway");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Norway");
				}
				}
				
				if(country.equals("Austria")){
					String pcodebc_austria = pcodebc.substring(0,pcodebc.indexOf("-"));
				if(pcodebc_austria.equals("A")){
					
				}else if(pcodebc_austria.equals("AT")){
					
				}else{
					System.out.println("Country code incorrect for Austria");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Austria");
				}
				}
				
				if(country.equals("Portugal")){
					String pcodebc_portugal = pcodebc.substring(0,pcodebc.indexOf("-"));
				if(pcodebc_portugal.equals("P")){
					
				}else if(pcodebc_portugal.equals("PT")){
					
				}else{
					System.out.println("Country code incorrect for Portugal");
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar_cat.add("Country Code");
					ar_type.add("Address");
					ar.add("Country code incorrect Portugal");
				}
				}
				
				if(country.equals("Sweden")){
					
					String pcodebc_sweden = pcodebc.substring(0,pcodebc.indexOf("-"));
					if(pcodebc_sweden.equals("S")){
						
					}else if(pcodebc_sweden.equals("SE")){
						
					}else{
						
						System.out.println("Country code incorrect for Sweden");
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Country Code");
						ar_type.add("Address");
						ar.add("Country code incorrect Sweden");
							
					}		
				}
			
				
			if(country.equals("Croatia")){
				
				if(pcodebc.contains("HR")){
					
				}else if(pcodebc.contains("CRO")){
					
				}else{
					
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Croatia");				
					ar_cat.add("Country Code");
					ar_type.add("Address");
					System.out.println("Country code missing Croatia");
				}
				
			}
			
			if(country.equals("Czech Republic")){
				
			if(pcodebc.contains("C")){
				
			}else if(pcodebc.contains("CR")){
				
			}else if(pcodebc.contains("CS")){
				
			}else if(pcodebc.contains("CZ")){
				
			}else{
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Country code missing Czech Republic");
				ar_cat.add("Country Code");
				ar_type.add("Address");
			}
			}
			
			if(country.equals("Finland")){
				if(pcodebc.contains("FI")){
					
				}else if(pcodebc.contains("FIN")){
					
				}else if (pcodebc.contains("SF")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Finland");
					ar_cat.add("Country Code");
					ar_type.add("Address");
				}
				
			}
			
			if(country.equals("Greece")){
				if(pcodebc.contains("EL")){
					
				}else if(pcodebc.contains("GR")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Greece");
					ar_cat.add("Country Code");
					ar_type.add("Address");
				}
				
			}
			
			
			if(country.equals("Romania")){
				
				if(pcodebc.contains("R")){
					
				}else if(pcodebc.contains("RO")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Romania");
					ar_cat.add("Country Code");
					ar_type.add("Address");
				}
			}
			
			if(country.equals("Russia")){
				if(pcodebc.contains("R")){
					
				}else if(pcodebc.contains("RU")){
					
				}else if(pcodebc.contains("RUS")){
					
				}else if(pcodebc.contains("SU")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Russia");
					ar_cat.add("Country Code");
					ar_type.add("Address");
				}
				
			}
			
			if(country.equals("Slovakia")){
				if(pcodebc.contains("SK")){
					
				}else if(pcodebc.contains("SKO")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Slovakia");
					ar_cat.add("Country Code");
					ar_type.add("Address");
				}
			}
			
			if(country.equals("Slovenia")){
				if(pcodebc.contains("SL")){
					
				}else if(pcodebc.contains("SLO")){
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Slovenia");
					ar_cat.add("Country Code");
					ar_type.add("Address");
					System.out.println("Country code missing Slovenia");
				}
				
			}
		
			if(country.equals("Spain")){
				if(pcodebc.contains("E")){
					
				}else if(pcodebc.contains("ES")) {
					
				}else{
					ar_itemno.add(itemno);
					ar_acc.add(acc);
					ar_Name.add(name);
					ar.add("Country code missing Spain");
					ar_cat.add("Country Code");
					ar_type.add("Address");
					System.out.println("country code missing for Spain");
				}
				
			}
			}
		
			//TODO Numbers in Address
			
			
			
			
			
			
			
			//TODO Letters in Sub org
			
			Pattern digits_letterswop = Pattern.compile("^[A-Z]{1}\\s");
			
			
			Matcher digits_letterswop_m = digits_letterswop.matcher(suborg1);
			Matcher digits_letterswop_m1 = digits_letterswop.matcher(suborg2);
			Matcher digits_letterswop_m2 = digits_letterswop.matcher(suborg3);
			
			
			if(digits_letterswop_m.find()){
			System.out.println("Alphabet Letter present start of suborg1 :"+digits_letterswop_m.group());	
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar.add("Alphabet Letter present start of suborg1 :"+digits_letterswop_m.group());
			ar_cat.add("General Policy Error");
			ar_type.add("Address");
			}
			
			if(digits_letterswop_m1.find()){
				System.out.println("Alphabet Letter present start of suborg2 :"+digits_letterswop_m1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Alphabet Letter present start of suborg2 :"+digits_letterswop_m1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(digits_letterswop_m2.find()){
				System.out.println("Alphabet Letter present start of suborg3 :"+digits_letterswop_m2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Alphabet Letter present start of suborg3 :"+digits_letterswop_m2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			
			//TODO Letters and Numbers in Sub Org	
			
			
			Pattern letters_digits_com= Pattern.compile("^[0-9]+[A-Z]+\\s|^[A-Z]+[0-9]+\\s|\\s[0-9]+[A-Z]+$");
			
			Matcher letters_digits_com1 = letters_digits_com.matcher(suborg1);
			Matcher letters_digits_com2 = letters_digits_com.matcher(suborg2);
			Matcher letters_digits_com3 = letters_digits_com.matcher(suborg3);
			
			if(country.equals("France")){
				
			}else{	
			if(letters_digits_com1.find()){
				System.out.println("Letter Number Combination at start of suborg1 : "+letters_digits_com1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Letter Number Combination at start of suborg1 : "+letters_digits_com1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			if(letters_digits_com2.find()){
				System.out.println("Letter Number Combination at start of suborg2 : "+letters_digits_com2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Letter Number Combination at start of suborg2 : "+letters_digits_com2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			if(letters_digits_com3.find()){
				System.out.println("Letter Number Combination at start of suborg3 : "+letters_digits_com3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Letter Number Combination at start of suborg3 : "+letters_digits_com3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			}
			
			//TODO 	Period between numbers and in street
			
			Pattern pn = Pattern.compile("[0-9]\\.[0-9]");
			
			Matcher period_num = pn.matcher(mainorg); 
			Matcher period_num1 = pn.matcher(suborg1);
			Matcher period_num2 = pn.matcher(suborg2);
			Matcher period_num3 = pn.matcher(suborg3);
			Matcher period_num4 = pn.matcher(street);
			
			if(period_num.find()){
				System.out.println("Period present inbetween Mainorg : "+period_num.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present inbetween Number - Mainorg : "+period_num.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			if(period_num1.find()){
				System.out.println("Period present inbetween Number - Suborg1 : "+period_num1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present inbetween Number - Suborg1 : "+period_num1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(period_num2.find()){
				System.out.println("Period present inbetween Number - Suborg2 : "+period_num2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present inbetween Number - Suborg2 : "+period_num2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");	
			}
			if(period_num3.find()){
				System.out.println("Period present inbetween Number - Suborg3 : "+period_num3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present inbetween Number - Suborg3 : "+period_num3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			
			if(period_num4.find()){
				System.out.println("Period present inbetween Numbers - Street : "+period_num4.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present inbetween Numbers - Street : "+period_num4.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			//TODO Numers and letter period present inbetween
			
			Pattern letters_digits_period= Pattern.compile("^[0-9]+\\.[A-Z]+\\s|^[A-Z]+\\.[0-9]+\\s|\\s[0-9]+\\.[A-Z]+$");
			
			Matcher letters_digits_period_m = letters_digits_period.matcher(mainorg);
			Matcher letters_digits_period_m1 = letters_digits_period.matcher(suborg1);
			Matcher letters_digits_period_m2 = letters_digits_period.matcher(suborg2);
			Matcher letters_digits_period_m3 = letters_digits_period.matcher(suborg3);
			Matcher letters_digits_period_m4 = letters_digits_period.matcher(street);
			
			
			if(letters_digits_period_m.find()){
				System.out.println("Period present between Num&Let - Mainorg : "+letters_digits_period_m.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present between Num&Let - Mainorg : "+letters_digits_period_m.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}

			if(letters_digits_period_m1.find()){

				System.out.println("Period present between Num&Let - Suborg1 : "+letters_digits_period_m1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present between Num&Let - Suborg1 : "+letters_digits_period_m1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
				
			}	
			if(letters_digits_period_m2.find()){

				System.out.println("Period present between Num&Let - Suborg2 : "+letters_digits_period_m2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present between Num&Let - Suborg2 : "+letters_digits_period_m2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(letters_digits_period_m3.find()){
				System.out.println("Period present between Num&Let - Suborg3 : "+letters_digits_period_m3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present between Num&Let - Suborg3 : "+letters_digits_period_m3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}		
		
			if(letters_digits_period_m4.find()){
				System.out.println("Period present between Num&Let - Street : "+letters_digits_period_m4.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Period present between Num&Let - Suborg4 : "+letters_digits_period_m4.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			//TODO hyphen presented with an org. that is separating numbers 	
			
			Pattern ph= Pattern.compile("[0-9]-[0-9]");
			Matcher Number_hyphen = ph.matcher(mainorg);
			Matcher Number_hyphen1 = ph.matcher(suborg1);
			Matcher Number_hyphen2 = ph.matcher(suborg2);
			Matcher Number_hyphen3 = ph.matcher(suborg3);
			
			if(Number_hyphen.find()){
				System.out.println("Hyphen present between Number - Mainorg : "+Number_hyphen.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present between Number - Mainorg : "+Number_hyphen.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen1.find()){
				System.out.println("Hyphen present between Number - Suborg1 : "+Number_hyphen1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present between Number - Suborg1 : "+Number_hyphen1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen2.find()){
				System.out.println("Hyphen present between Number - Suborg2 : "+Number_hyphen2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present between Number - Suborg2 : "+Number_hyphen2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen3.find()){
				System.out.println("Hyphen present between Number - Suborg3 : "+Number_hyphen3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present between Number - Suborg3 : "+Number_hyphen3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			
			//TODO Number Letter Hyphen
			
			Pattern nh = Pattern.compile("[0-9]\\-[A-z]{1,2}\\s|[0-9]\\-[0-9]{1,2}\\s|[A-z]\\-[A-z]{1,2}\\s|[A-z]\\-[0-9]{1,2}\\s");
			Matcher Number_hyphen_m = nh.matcher(mainorg);
			Matcher Number_hyphen_m1 = nh.matcher(suborg1);
			Matcher Number_hyphen_m2 = nh.matcher(suborg2);
			Matcher Number_hyphen_m3 = nh.matcher(suborg3);
			
			
			if(Number_hyphen_m.find()){
				System.out.println("Hyphen present in Num&Let : "+Number_hyphen_m.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present in Num&Let : "+Number_hyphen_m.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen_m1.find()){
				System.out.println("Hyphen present in Num&Let : "+Number_hyphen_m1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present in Num&Let : "+Number_hyphen_m1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen_m2.find()){
				System.out.println("Hyphen present in Num&Let : "+Number_hyphen_m2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present in Num&Let : "+Number_hyphen_m2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(Number_hyphen_m3.find()){
				System.out.println("Hyphen present in Num&Let : "+Number_hyphen_m3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present in Num&Let : "+Number_hyphen_m3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			//TODO street hyphen policy
			
			Pattern shp = Pattern.compile("[0-9]{1}-[A-z]{3,}+|[0-9]{1}\\/[A-z]{3,}+|[A-Z]{1}-[A-z]{3,}+|[A-Z]{1}\\/[A-z]{3,}+");
			Matcher street_hyp_m = shp.matcher(street);
			
			if(street_hyp_m.find()){
				System.out.println("Hyphen present before word :"+street_hyp_m.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Hyphen present before word :"+street_hyp_m.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			//TODO A slash presented with an org. that is separating a number from a letter OR letters from letters will be bumped. With a street, the slash is changed to a hyphen.  
			
			Pattern snp = Pattern.compile("[0-9]{1}\\/[A-z]{1}|[0-9]{1}\\/[0-9]{1}|[A-Z]{1}\\/[0-9]{1}|[A-Z]{1}\\/[A-Z]{1}");
			
			Matcher org_slash_m = snp.matcher(mainorg);
			Matcher org_slash_m1 = snp.matcher(suborg1);
			Matcher org_slash_m2 = snp.matcher(suborg2);
			Matcher org_slash_m3 = snp.matcher(suborg3);
			Matcher org_slash_m4 = snp.matcher(street);
			
			if(org_slash_m.find()){
				System.out.println("Slash present in Mainorg : "+org_slash_m.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Slash present in Mainorg : "+org_slash_m.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(org_slash_m1.find()){
				System.out.println("Slash present in Suborg1 : "+org_slash_m1.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Slash present in Suborg1 : "+org_slash_m1.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			if(org_slash_m2.find()){
				System.out.println("Slash present in Suborg2 : "+org_slash_m2.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Slash present in Suborg2 : "+org_slash_m2.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			if(org_slash_m3.find()){
				System.out.println("Slash present in Suborg3 : "+org_slash_m3.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Slash present in Suborg3 : "+org_slash_m3.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			if(org_slash_m4.find()){
				System.out.println("Slash present in Street : "+org_slash_m4.group());
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar.add("Slash present in Street : "+org_slash_m4.group());
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
			}
			
			
			
			
			
			
			
			
			
			
			//TODO
			/*if(mainorg.contains(".")){
				System.out.println(mainorg);
				System.out.println("Period present in mainorg");
			}
			if(mainorg.contains("-")){
				System.out.println("Hyphen present in mainorg");
			}
			if(mainorg.contains("/")){
				System.out.println("Slash present in mainorg");
			}
			if(mainorg.contains("'")){
				System.out.println("Apostrophe present in mainorg");
			}

			if(suborg1.contains(".")){
				System.out.println("Period present in suborg1");
			}
			if(suborg1.contains("-")){
				System.out.println("Hyphen present in suborg1");
			}
			if(suborg1.contains("/")){
				System.out.println("Slash present in suborg1");
			}
			if(suborg1.contains("'")){
				System.out.println("Apostrophe present in suborg1");
			}


			if(suborg2.contains(".")){
				System.out.println("Period present in suborg2");
			}
			if(suborg2.contains("-")){
				System.out.println("Hyphen present in suborg2");
			}
			if(suborg2.contains("/")){
				System.out.println("Slash present in suborg2");
			}
			if(suborg2.contains("'")){
				System.out.println("Apostrophe present in suborg2");
			}


			if(suborg3.contains(".")){
				System.out.println("Period present in suborg3");
			}
			if(suborg3.contains("-")){
				System.out.println("Hyphen present in suborg3");
			}
			if(suborg3.contains("/")){
				System.out.println("Slash present in suborg3");
			}
			if(street.contains("/")){
				System.out.println("Slash present in street");
			}
			if(street.contains("'")){
				System.out.println("Apostrophe present in street");
			}*/

			Unique_policies(mainorg,suborg1,suborg2,suborg3, street, rsaddr);
			Street(street,acc,itemno);

			//---------------------------------------------------------------------------------------------------------//				
			String[] eng = mainorg.trim().split(" ");
			String[] eng1 = suborg1.trim().split(" ");		
			String[] eng2 = suborg2.trim().split(" ");
			String[] eng3 = suborg3.trim().split(" ");
			String[] eng4 = street.trim().split(" ");


			for(String m : eng){
				
				
				/*if(!Word_endings.wd(m).equals("")&&!!Word_endings2.wd1(m).equals("")){
							
					System.out.println(Word_endings.wd(m));
					System.out.println(Word_endings2.wd1(m));
				}*/
				
				for(String a1 : Insignificant_words.insig_list){	
					if(a1.equals(m)){
						System.out.println("Insignificant word Missed: "+m);
						ar.add("Insignificant word Missed: "+m);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Insignificant Word");
						ar_type.add("Address");
						}
				}

				for(int l=0;l<Abbreviation.abbr.length;l++){	
					if(m.equals(Abbreviation.abbr[l])){
						System.out.println("Abbreviation Missede: "+m+" - "+Abbreviation.abb[l]);
						ar.add("Abbreviation Missed: "+m+" - "+Abbreviation.abb[l]);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Abbreviation");
						ar_type.add("Address");
					}
				}
			}

			for(String m1:eng1){
				
				for(String a1 : Insignificant_words.insig_list){

					if(a1.equals(m1)){
						System.out.println("Insignificant word Missed: "+m1);
						ar.add("Insignificant word Missed: "+m1);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Insignificant Word");
						ar_type.add("Address");
					}
				}

				for(int l=0;l<Abbreviation.abbr.length;l++){	
					if(m1.equals(Abbreviation.abbr[l])){
						System.out.println("Abbreviation Missed: "+m1+" - "+Abbreviation.abb[l]);
						ar.add("Abbreviation Missed: "+m1+" - "+Abbreviation.abb[l]);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Abbreviation");
						ar_type.add("Address");
						}
				}	
			}

			for(String m2:eng2){
				for(int l=0;l<Abbreviation.abbr.length;l++){	
					if(m2.equals(Abbreviation.abbr[l])){
						System.out.println("Abbreviation Missed: "+m2+" - "+Abbreviation.abb[l]);
						ar.add("Abbreviation Missed: "+m2+" - "+Abbreviation.abb[l]);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Abbreviation");
						ar_type.add("Address");
					}
				}	
			}

			for(String m3:eng3){
				for(int l=0;l<Abbreviation.abbr.length;l++){	
					if(m3.equals(Abbreviation.abbr[l])){
						System.out.println("Abbreviation Missed: "+m3+" - "+Abbreviation.abb[l]);
						ar.add("Abbreviation Missed: "+m3+" - "+Abbreviation.abb[l]);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Abbreviation");
						ar_type.add("Address");	
					}
				}	
			}

			for(String m4:eng4){
				for(int l=0;l<Abbreviation.abbr.length;l++){	
					if(m4.equals(Abbreviation.abbr[l])){
						System.out.println("Abbreviation Missed: "+m4+" - "+Abbreviation.abb[l]);
						ar.add("Abbreviation Missed: "+m4+" - "+Abbreviation.abb[l]);
						ar_itemno.add(itemno);
						ar_acc.add(acc);
						ar_Name.add(name);
						ar_cat.add("Abbreviation");
						ar_type.add("Address");
						}
				}	
			}
			
		}

		java.util.List<String> rs_li = Arrays.asList(arr);
		
		Set<String> set = new HashSet<String>();
		
		for(String str : rs_li){
			
			boolean flagfordup = set.add(str);	
			
			if(!flagfordup){
				System.out.println(str +" is duplicate research address");		
				ar.add("Duplicate Research Address");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Duplicate Res Address");
				ar_type.add("Address");
			
			}
			
		}
			
	}

	public static void Street(String street, String acc, String itemno){

		if(street.toLowerCase().contains(" at ")){
			System.out.println("At present in Street Information :"+acc+itemno);
			ar.add("At present in Street Information :"+acc+itemno);
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("General Policy Error");
			ar_type.add("Address");
		}

		Pattern p = Pattern.compile("\\s[A-Z]{1}\\s&\\s[A-Z]{1}\\s"); 	//Initial ampersand bump
		Pattern p1 = Pattern.compile("\\s[A-z]+&[A-z]+\\s"); 			//words ampersand bump
		Matcher m = p.matcher(street);
		Matcher m1 = p1.matcher(street);


		if(m1.find()){
			System.out.println("Words ampersand Bump :"+m1.group().trim());
			ar.add("Words ampersand Bump :"+m1.group().trim());
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("General Policy Error");
			ar_type.add("Address");
		}
		if(m.find()){
			System.out.println("Initial ampersand Bump :"+m.group().trim());
			ar.add("Initial ampersand Bump :"+m.group().trim());
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("General Policy Error");
			ar_type.add("Address");
		}
		if(street.contains("'")){
			System.out.println("Apostrophe present :"+street);
			ar.add("Apostrophe present :"+street);
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("General Policy Error");
			ar_type.add("Address");
			}	
	}

	public static void Itemtype(Element doc,String acc, String itemno){

		String itemtype="";

		itemtype = doc.getElementsByTag("itemtype").first().text();

		if(itemtype.equals("MA")){


			if(doc.getElementsByTag("rpauthor").size()>0){

				System.out.println("Reprint Indicated in MA(Meeting Abstracts) Item");
			
				ar.add("Reprint Indicated in MA(Meeting Abstracts) Item :");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("General Policy Error");
				ar_type.add("Address");
				}

		}

	}


	public static void Unique_policies(String mainorg,String suborg1, String suborg2, String suborg3,String street,String all) throws NoSuchFieldException, SecurityException, IOException{

		if(mainorg.equals("Paul Scherrer Inst")){
			if(all.contains("PSI")){
				System.out.println("Drop PSI for Paul Scherrer Institut");
				ar.add("PSI : Drop PSI for Paul Scherrer Institut");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Unique Policy Error");
				ar_type.add("Address");
			}
		}

		String[] kfa_policy = {"KFA Julich","Forschungszentrum Julich GmbH","KFA","KFA Julich GmbH","KFA Julich GmbH, Forschungszentrum"};

		for(String k:kfa_policy){

			if(mainorg.equals(k)){
				System.out.println(k+" : present Process Forschungszentrum Julich");
				ar.add(k+" : present Process Forschungszentrum Julich");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Unique Policy Error");
				ar_type.add("Address");
			}

			if(suborg1.equals(k)){
				System.out.println(k+"present Process Forschungszentrum Julich");
				ar.add(k+"present Process Forschungszentrum Julich");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Unique Policy Error");
				ar_type.add("Address");
			}	

			if(suborg2.equals(k)){
				System.out.println(k+"present Process Forschungszentrum Julich");
				ar.add(k+"present Process Forschungszentrum Julich");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Unique Policy Error");
				ar_type.add("Address");
			}

			if(suborg3.equals(k)){
				System.out.println(k+"present Process Forschungszentrum Julich");
				ar.add(k+"present Process Forschungszentrum Julich");
				ar_itemno.add(itemno);
				ar_acc.add(acc);
				ar_Name.add(name);
				ar_cat.add("Unique Policy Error");
				ar_type.add("Address");
				}

		}

		if(street.contains("ISREEM")){
			System.out.println("ISREEM present in street pls check");
			ar.add("ISREEM present in street pls check");
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("Unique Policy Error");
			ar_type.add("Address");
		}

		if(street.contains("Agrocampus")){
			System.out.println("Agrocampus present in street pls check");
			ar.add("Agrocampus present in street pls check");
			ar_itemno.add(itemno);
			ar_acc.add(acc);
			ar_Name.add(name);
			ar_cat.add("Unique Policy Error");
			ar_type.add("Address");
		}
		
	}
	
	public static boolean check_korean (String[] collect,Element doc){
		
		
		for(String m: collect){
		
			if(doc.select("rsaddr").get(Integer.parseInt(m)).getElementsByTag("country").last().text().contains("Korea")){
				
				return true;
			
			}
		
		}
		
		
		return false;
	}
	
	
	public static String check_space(String cross){
		
		
		String s1=cross.replace(" 	", " ");
		String s2 = s1.replace(" ", " ");
		String s3 = s2.replace(" ", " ");
		String s4 = s3.replace(" ", " ");
		String s5 = s4.replace(" ", " ");
		String s6 = s5.replace(" ", " ");
		String s7 = s6.replace(" ", " ");
		String s8 = s7.replace(" ", " ");
		String s9 = s8.replace(" ", " ");
		String s10 = s9.replace(" ", " ");
		String s11 = s10.replace(" ", " ");
		
		String s12 = s11.replace(".", " ");
		
		System.out.println(s12.replace("  ", " ").replace("‐", "-").replace("’", "'"));
		
		
		return s12.replace("  ", " ").replace("‐", "-").replace("’", "'");
	}

public static String doi(Element doca){
		
		String doi="";
		if(doca.getElementsByTag("citation").outerHtml().length()>0){		
			try{
			if(doca.getElementsByAttributeValue("type", "DOI").last().text().length()>0){
	
			doi=doca.getElementsByAttributeValue("type", "DOI").last().text();
			
			
			}else{
				
				}
			}catch(NullPointerException e){
			return null;
			}
			
		}else{
				try{
				if(doca.getElementsByAttributeValue("TYPE", "DOI").first().text().length()>0){
					
			doi=doca.getElementsByAttributeValue("TYPE", "DOI").first().text();
		
			
			}else{
			
		}
		}catch(NullPointerException ee){
					return null;
				}
				}
		
		
		return doi;
	}


}