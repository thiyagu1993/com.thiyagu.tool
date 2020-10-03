package process;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import misc.General_conversion;
import misc.Normalizer;
import misc.Other_lang_equivalent;
import misc.WE_collector;

import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import output.Html_output;
import resource.Abbreviation;
import resource.Acronyms;
import resource.Excel_resource;
import resource.Foreign_insignificant;
import resource.Insignificant_words;
import resource.Word_ending_3;
import resource.Word_ending_4;

public class Address_check {

	public static String address_check (Object odoc,Element doc, Element docx,	HashSet<String> we_collect_list, PrintWriter out,String col1, String col2) throws IOException{
		LinkedHashSet<String> address_list = new LinkedHashSet<String>();
		ArrayList<String> we_html_idt = new ArrayList<String>();
		ArrayList<String> we_html_info = new ArrayList<String>();
		try{
			JSONParser parser = new JSONParser();
			
			Object obj = parser.parse(odoc.toString());
			
			org.json.simple.JSONObject job = (org.json.simple.JSONObject) obj;
			org.json.simple.JSONObject arr = (org.json.simple.JSONObject) job.get("message");
			org.json.simple.JSONArray arr1 = (org.json.simple.JSONArray) arr.get("author");
			
			for(int i=0;i<arr1.size();i++){
					
				try{
							
					org.json.simple.JSONObject arr2 = (org.json.simple.JSONObject) arr1.get(i);
					org.json.simple.JSONArray arr3 = (org.json.simple.JSONArray) arr2.get("affiliation");
				
			for(int j=0;j<arr3.size();j++){
				
				org.json.simple.JSONObject arr4 = (org.json.simple.JSONObject) arr3.get(j);
				
			String address = (String) arr4.get("name");
				
				address_list.add(address);
				if(arr4.isEmpty()){
					continue;
				}
			}
				}catch(Exception e){
				
				}
			}
			
			
//		//-------------------------------------------------------
			
			String[] we_info = WE_collector.we_collect(address_list.toString());
//			String[] we_html_info = address_list.toArray(new String[address_list.size()]);
			for(String we_info_str:we_info){
			
				we_collect_list.add(we_info_str);
			
			}
			
			
			
			
			
			if(address_list.size()==0){
				return "N/A (CR)";
			}
			System.out.println(doc.getElementsByTag("rsaddr").size()+" "+address_list.size());
			if(doc.getElementsByTag("rsaddr").size()!=address_list.size()){
				return "Address Mismatch";
			}
			
			String suborg1="";
			String suborg2="";
			String suborg3="";
			String mainorg ="";
			
			int elemnt_cnt=0;			
			int elemnt_crt=0;
			
			
			for(int j=0;j<doc.getElementsByTag("rsaddr").size();j++){
				
				
				try{
					mainorg = doc.select("rsaddr").get(j).getElementsByTag("mainorg").text().replace(",", " ");
				}catch(IndexOutOfBoundsException e){
					
					mainorg="";
				}
				try{
					suborg1 = doc.select("rsaddr").get(j).getElementsByTag("suborg1").text().replace(",", " ");
					
				}catch(IndexOutOfBoundsException e){
					
				suborg1="";
				}
				
				try{
					suborg2 = doc.select("rsaddr").get(j).getElementsByTag("suborg2").text().replace(",", " ");
				}catch(IndexOutOfBoundsException e){
					
				suborg2="";
				}
				try{
				suborg3 = doc.select("rsaddr").get(j).getElementsByTag("suborg3").text().replace(",", " ");
				}catch(IndexOutOfBoundsException e){
					
				suborg3="";
				}

				we_html_idt.add(mainorg+" "+suborg1+" "+suborg2+" "+suborg3);
				
				Iterator<String> it = address_list.iterator();
				
				while(it.hasNext()){
				int i=0;
				int k=0;
				String general_conv_completed = General_conversion.gen_conversion(it.next());
				
				String normalizer_completed = Normalizer.normal(general_conv_completed);
				
				String acronym_completed = Acronyms.Acronym(normalizer_completed);
				
				String alter_space_completed = alter_space(acronym_completed);
				
				String remove_nonalphabetics_completed = remove_nonalphabetics(alter_space_completed);
				
				String other_language_completed = Other_lang_equivalent.lang_equivalent(remove_nonalphabetics_completed);
				
				String policy_completed = policy(other_language_completed);
				
					String cur_add = policy_completed;
					we_html_info.add(cur_add);
					System.out.println(cur_add);
					
					if(mainorg.isEmpty()==false){
						i++;
						if(cur_add.contains(mainorg)){
							k++;
							
						}else{
							
						}
			
					}
						
						if(suborg1.isEmpty()==false){
							i++;
							if(cur_add.contains(suborg1)){
							k++;
							
							}else{
							
							}
						}
						
						if(suborg2.isEmpty()==false){
							i++;
							if(cur_add.contains(suborg2)){
								
							k++;
							}else{
								
							
							}
						}
						
						if(suborg3.isEmpty()==false){
							i++;
							if(cur_add.contains(suborg3)){
								
							k++;
							}else{
								
							}
						}
			
						if(k>=i){
							elemnt_crt++;
						}else if(k>i){
							elemnt_crt = doc.getElementsByTag("rsaddr").size();
						}
				}
				System.out.println("element correct "+elemnt_crt);
						elemnt_cnt=elemnt_crt;
			}
			System.out.println(doc.getElementsByTag("rsaddr").size()+" >< "+elemnt_cnt);
			if(doc.getElementsByTag("rsaddr").size()==elemnt_cnt){
			
				
				return "Address Correct";
			
			}else{
				if(doc.getElementsByTag("rsaddr").size()==address_list.size()){
					out.write("<thead><tr><th width=\"50%\">IDT</th><th width=\"50%\">CR</th></tr></thead>");		
					out.write("<tr><td><b>"+col1+"</b></td><td><b>"+col2+"</b></td></tr>");
					for(int id=0;id<we_html_idt.size();id++){
					out.write("<tr><td>"+we_html_idt.get(id)+"</td><td>"+policy(remove_nonalphabetics(alter_space(Normalizer.normal(Acronyms.Acronym(we_html_info.get(id))))))+"</td></tr>");
					
				}
				}			
				return "Address Incorrect";
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

	
		return "N/A";
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
		
		}
		
	}else{
			try{
			if(doca.getElementsByAttributeValue("TYPE", "DOI").first().text().length()>0){
				
		doi=doca.getElementsByAttributeValue("TYPE", "DOI").first().text();
	
		
		}else{
		
	}
	}catch(NullPointerException ee){
				
			}
			}
		
	return doi;
}

public static String policy(String it){
	
	String abb_sentence=it;
	
	for(int hl=0;hl<Excel_resource.hold1.size();hl++){
		
		if(abb_sentence.contains(Excel_resource.hold1.get(hl))){
		if(abbreviation_check(abb_sentence,Excel_resource.hold1.get(hl))==true){
			continue;
		}
			abb_sentence = abb_sentence.replace(Excel_resource.hold1.get(hl),Excel_resource.hold2.get(hl));	
		
		}
		
	
	}
	
	String insig_sentence = abb_sentence.replace(" and ", " & ");
	for(int k=0;k<Insignificant_words.insig_list.length;k++){
		
		insig_sentence = insig_sentence.replace(Insignificant_words.insig_list[k], " ").replace("  ", " ");
	}
	
	String wd5 = insig_sentence;
	
	for(int l1=0;l1<Foreign_insignificant.finsig_list.length;l1++){
		
		
		wd5 = wd5.replace(Foreign_insignificant.finsig_list[l1], " ").replace("  ", " ");
		
		}

	return wd5;
}

public static String remove_nonalphabetics(String s){
	
	String[] symbols = {"á","À","â","ă","ā","ã","å","ą","æ","ć","č","ç","ď","Đ","ð","Ɖ","é","è","ė","ê","ë","ě","ę","ȩ","ǧ","ğ","ı","í","ì","İ","î","ï","ĭ","ị","Ľ","ł","ń","ň","ñ","Ó","ò","ô","õ","ő","Ø","œ","Ř","ś","Š","ş","ș","ß","ť","ţ","Þ","ú","ù","û","ū","ů","ű","ý","ÿ","ź","ż","Ž","β","А","К","Ä","à","ó","ø","Ö","Ü","ç","É","Å","Ñ","Ç","ž","Ş","Ś","Á","Č","š","ř","Ż","Ł"};
	String[] alphabet= {"a","A","a","a","a","a","a","a","ae","c","c","c","d","D","d","D","e","e","e","e","e","e","e","e","g","g","i","i","i","I","i","i","i","i","L","l","n","n","n","O","o","o","o","o","O","oe","R","s","S","s","s","ss","t","t","th","u","u","u","u","u","u","y","y","z","z","Z","ss","A","K","A","a","o","o","O","U","c","E","A","N","C","z","S","S","A","C","s","r","Z","L"};
	
	String ar_author_symbol_removed1=s.replace(symbols[0], alphabet[0]);
	String ar_author_symbol_removed2=ar_author_symbol_removed1.replace(symbols[1], alphabet[1]);
	String ar_author_symbol_removed3=ar_author_symbol_removed2.replace(symbols[2], alphabet[2]);
	String ar_author_symbol_removed4=ar_author_symbol_removed3.replace(symbols[3], alphabet[3]);
	String ar_author_symbol_removed5=ar_author_symbol_removed4.replace(symbols[4], alphabet[4]);
	String ar_author_symbol_removed6=ar_author_symbol_removed5.replace(symbols[5], alphabet[5]);
	String ar_author_symbol_removed7=ar_author_symbol_removed6.replace(symbols[6], alphabet[6]);
	String ar_author_symbol_removed8=ar_author_symbol_removed7.replace(symbols[7], alphabet[7]);
	String ar_author_symbol_removed9=ar_author_symbol_removed8.replace(symbols[8], alphabet[8]);
	String ar_author_symbol_removed10=ar_author_symbol_removed9.replace(symbols[9], alphabet[9]);
	String ar_author_symbol_removed11=ar_author_symbol_removed10.replace(symbols[10], alphabet[10]);
	String ar_author_symbol_removed12=ar_author_symbol_removed11.replace(symbols[11], alphabet[11]);
	String ar_author_symbol_removed13=ar_author_symbol_removed12.replace(symbols[12], alphabet[12]);
	String ar_author_symbol_removed14=ar_author_symbol_removed13.replace(symbols[13], alphabet[13]);
	String ar_author_symbol_removed15=ar_author_symbol_removed14.replace(symbols[14], alphabet[14]);
	String ar_author_symbol_removed16=ar_author_symbol_removed15.replace(symbols[15], alphabet[15]);
	String ar_author_symbol_removed17=ar_author_symbol_removed16.replace(symbols[16], alphabet[16]);
	String ar_author_symbol_removed18=ar_author_symbol_removed17.replace(symbols[17], alphabet[17]);
	String ar_author_symbol_removed19=ar_author_symbol_removed18.replace(symbols[18], alphabet[18]);
	String ar_author_symbol_removed20=ar_author_symbol_removed19.replace(symbols[19], alphabet[19]);
	String ar_author_symbol_removed21=ar_author_symbol_removed20.replace(symbols[20], alphabet[20]);
	String ar_author_symbol_removed22=ar_author_symbol_removed21.replace(symbols[21], alphabet[21]);
	String ar_author_symbol_removed23=ar_author_symbol_removed22.replace(symbols[22], alphabet[22]);
	String ar_author_symbol_removed24=ar_author_symbol_removed23.replace(symbols[23], alphabet[23]);
	String ar_author_symbol_removed25=ar_author_symbol_removed24.replace(symbols[24], alphabet[24]);
	String ar_author_symbol_removed26=ar_author_symbol_removed25.replace(symbols[25], alphabet[25]);
	String ar_author_symbol_removed27=ar_author_symbol_removed26.replace(symbols[26], alphabet[26]);
	String ar_author_symbol_removed28=ar_author_symbol_removed27.replace(symbols[27], alphabet[27]);
	String ar_author_symbol_removed29=ar_author_symbol_removed28.replace(symbols[28], alphabet[28]);
	String ar_author_symbol_removed30=ar_author_symbol_removed29.replace(symbols[29], alphabet[29]);
	String ar_author_symbol_removed31=ar_author_symbol_removed30.replace(symbols[30], alphabet[30]);
	String ar_author_symbol_removed32=ar_author_symbol_removed31.replace(symbols[31], alphabet[31]);
	String ar_author_symbol_removed33=ar_author_symbol_removed32.replace(symbols[32], alphabet[32]);
	String ar_author_symbol_removed34=ar_author_symbol_removed33.replace(symbols[33], alphabet[33]);
	String ar_author_symbol_removed35=ar_author_symbol_removed34.replace(symbols[34], alphabet[34]);
	String ar_author_symbol_removed36=ar_author_symbol_removed35.replace(symbols[35], alphabet[35]);
	String ar_author_symbol_removed37=ar_author_symbol_removed36.replace(symbols[36], alphabet[36]);
	String ar_author_symbol_removed38=ar_author_symbol_removed37.replace(symbols[37], alphabet[37]);
	String ar_author_symbol_removed39=ar_author_symbol_removed38.replace(symbols[38], alphabet[38]);
	String ar_author_symbol_removed40=ar_author_symbol_removed39.replace(symbols[39], alphabet[39]);
	String ar_author_symbol_removed41=ar_author_symbol_removed40.replace(symbols[40], alphabet[40]);
	String ar_author_symbol_removed42=ar_author_symbol_removed41.replace(symbols[41], alphabet[41]);
	String ar_author_symbol_removed43=ar_author_symbol_removed42.replace(symbols[42], alphabet[42]);
	String ar_author_symbol_removed44=ar_author_symbol_removed43.replace(symbols[43], alphabet[43]);
	String ar_author_symbol_removed45=ar_author_symbol_removed44.replace(symbols[44], alphabet[44]);
	String ar_author_symbol_removed46=ar_author_symbol_removed45.replace(symbols[45], alphabet[45]);
	String ar_author_symbol_removed47=ar_author_symbol_removed46.replace(symbols[46], alphabet[46]);
	String ar_author_symbol_removed48=ar_author_symbol_removed47.replace(symbols[47], alphabet[47]);			
	String ar_author_symbol_removed49=ar_author_symbol_removed48.replace(symbols[48], alphabet[48]);
	String ar_author_symbol_removed50=ar_author_symbol_removed49.replace(symbols[49], alphabet[49]);
	String ar_author_symbol_removed51=ar_author_symbol_removed50.replace(symbols[50], alphabet[50]);
	String ar_author_symbol_removed52=ar_author_symbol_removed51.replace(symbols[51], alphabet[51]);
	String ar_author_symbol_removed53=ar_author_symbol_removed52.replace(symbols[52], alphabet[52]);
	String ar_author_symbol_removed54=ar_author_symbol_removed53.replace(symbols[53], alphabet[53]);
	String ar_author_symbol_removed55=ar_author_symbol_removed54.replace(symbols[54], alphabet[54]);
	String ar_author_symbol_removed56=ar_author_symbol_removed55.replace(symbols[55], alphabet[55]);
	String ar_author_symbol_removed57=ar_author_symbol_removed56.replace(symbols[56], alphabet[56]);
	String ar_author_symbol_removed58=ar_author_symbol_removed57.replace(symbols[57], alphabet[57]);
	String ar_author_symbol_removed59=ar_author_symbol_removed58.replace(symbols[58], alphabet[58]);
	String ar_author_symbol_removed60=ar_author_symbol_removed59.replace(symbols[59], alphabet[59]);
	String ar_author_symbol_removed61=ar_author_symbol_removed60.replace(symbols[60], alphabet[60]);
	String ar_author_symbol_removed62=ar_author_symbol_removed61.replace(symbols[61], alphabet[61]);
	String ar_author_symbol_removed63=ar_author_symbol_removed62.replace(symbols[62], alphabet[62]);
	String ar_author_symbol_removed64=ar_author_symbol_removed63.replace(symbols[63], alphabet[63]);
	String ar_author_symbol_removed65=ar_author_symbol_removed64.replace(symbols[64], alphabet[64]);
	String ar_author_symbol_removed66=ar_author_symbol_removed65.replace(symbols[65], alphabet[65]);
	String ar_author_symbol_removed67=ar_author_symbol_removed66.replace(symbols[66], alphabet[66]);
	String ar_author_symbol_removed68=ar_author_symbol_removed67.replace(symbols[67], alphabet[67]);
	String ar_author_symbol_removed69=ar_author_symbol_removed68.replace(symbols[68], alphabet[68]);
	String ar_author_symbol_removed70=ar_author_symbol_removed69.replace(symbols[69], alphabet[69]);
	String ar_author_symbol_removed71=ar_author_symbol_removed70.replace(symbols[70], alphabet[70]);
	String ar_author_symbol_removed72=ar_author_symbol_removed71.replace(symbols[71], alphabet[71]);
	String ar_author_symbol_removed73=ar_author_symbol_removed72.replace(symbols[72], alphabet[72]);
	String ar_author_symbol_removed74=ar_author_symbol_removed73.replace(symbols[73], alphabet[73]);
	String ar_author_symbol_removed75=ar_author_symbol_removed74.replace(symbols[74], alphabet[74]);
	String ar_author_symbol_removed76=ar_author_symbol_removed75.replace(symbols[75], alphabet[75]);
	String ar_author_symbol_removed77=ar_author_symbol_removed76.replace(symbols[76], alphabet[76]);
	String ar_author_symbol_removed78=ar_author_symbol_removed77.replace(symbols[77], alphabet[77]);
	String ar_author_symbol_removed79=ar_author_symbol_removed78.replace(symbols[78], alphabet[78]);
	String ar_author_symbol_removed80=ar_author_symbol_removed79.replace(symbols[79], alphabet[79]);
	String ar_author_symbol_removed81=ar_author_symbol_removed80.replace(symbols[80], alphabet[80]);
	String ar_author_symbol_removed82=ar_author_symbol_removed81.replace(symbols[81], alphabet[81]);
	String ar_author_symbol_removed83=ar_author_symbol_removed82.replace(symbols[82], alphabet[82]);
	String ar_author_symbol_removed84=ar_author_symbol_removed83.replace(symbols[83], alphabet[83]);
	String ar_author_symbol_removed85=ar_author_symbol_removed84.replace(symbols[84], alphabet[84]);
	String ar_author_symbol_removed86=ar_author_symbol_removed85.replace(symbols[85], alphabet[85]);
	String ar_author_symbol_removed87=ar_author_symbol_removed86.replace(symbols[86], alphabet[86]);
	String ar_author_symbol_removed88=ar_author_symbol_removed87.replace(symbols[87], alphabet[87]);
	String ar_author_symbol_removed=ar_author_symbol_removed88.replace(symbols[88], alphabet[88]);
	
	
	return ar_author_symbol_removed.replace("ä", "a").replace("ö", "o").replace("ü", "u");

}
public static String alter_space(String s){
	
	Pattern p = Pattern.compile("[a-z][a-z][A-Z][a-z]");
	
	Matcher m = p.matcher(s);
	int i=0;
	if(m.find()){
		
		i = (s.indexOf(m.group())+2);
	}
	
	StringBuilder s1 = new StringBuilder(s);
		
	s1.insert(i, " ");
	
	return s1.toString();
}

public static boolean abbreviation_check(String s, String s1){
	
	try{
	int index = s.indexOf(s1);

	if(Character.isAlphabetic(index+s1.length())){
		
		return true;
		
	}
	}catch(Exception e){
		e.printStackTrace();
		
		return false;
	}
	
		
	
	return false;
}
}