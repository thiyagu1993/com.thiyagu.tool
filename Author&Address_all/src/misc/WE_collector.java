package misc;

import java.util.ArrayList;

public class WE_collector {

	public static String[] we_collect(String s){
		
	String [] s1 = s.replace("[", "").replace("]", "").replace(",", "").split(" ");
	ArrayList<String> se = new ArrayList<String>();

	String[] w = {"raphy","raphie","raphical","raphic","raphia","rafie","rafico","rafia","rafi","os","omy","omie","omie","omics","omico","omical","omic","omia","omi","ogy","ogiczny","ogics","ogico","ogical","ogic","ogia","ivo","ivity","ivita","ives","ive","iva","iv","ity","ities","isk","isk","isches","ischer","ischen","ischem","ische","isch","is","iques","ique","ions","ionnelle","ione","ional","ion","ik","ics","icos","ico","ico","icky","ici","iche","icas","ical","ica","ic","ias","elvu","czny"};
	
	for(String m:s1){
		
			for(int i=0;i<w.length;i++){
				
				if(m.trim().endsWith(w[i])){
				se.add(m);	
				
				}
			}
			
	}
	System.out.println(se);
	String[] ret = se.toArray(new String[se.size()]);
	
	return ret;
	}	
}
