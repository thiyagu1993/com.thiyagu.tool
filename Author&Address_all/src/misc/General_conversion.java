package misc;

public class General_conversion {

	public static String gen_conversion(String s){
	
		String country="";
		try{
		country = s.toLowerCase().trim().substring(s.lastIndexOf(" "));
		}catch(Exception e){
			return s;
		}
		if(country=="italy"){
		
			return s.replace(" studi ", "");
	
		}

	if(s.contains("University of California")){
	System.out.println("returning calif "+univ_calif(s));
		return univ_calif(s);
	}
	
	if(s.contains("University of Pennsylvania")&&s.contains("Philadelphia")){
		if(country=="USA"){
		return s.replace("University of Pennsylvania", "Univ Penn");
		}
		}

	if(s.contains("The First Affiliated Hospital")){
		return s.replace("The First Affiliated Hospital", "Affiliated Hosp 1");
	}
	
	if(s.contains("First Affiliated Hospital")){
		return s.replace("First Affiliated Hospital", "Affiliated Hosp 1");
	}
	
	if(s.contains("The Second Affiliated Hospital")){
		return s.replace("The Second Affiliated Hospital", "Affiliated Hosp 2");
	}
	
	if(s.contains("Second Affiliated Hospital")){
		return s.replace("Second Affiliated Hospital", "Affiliated Hosp 2");
	}
	
	if(s.contains("The Third Affiliated Hospital")){
		return s.replace("Third Affiliated Hospital", "Affiliated Hosp 3");
	}
	
	if(s.contains("Third Affiliated Hospital")){
		return s.replace("Third Affiliated Hospital", "Affiliated Hosp 3");
	}
	
	String common[]={"Baustoffforschung"};
	String common_rep[]={"Baustoffforsch"};
	
	for(int i=0;i<common.length;i++){
	
		s=s.replace(common[i],common_rep[i]);
	
	}
	
	
	return s;
	}
	
public static String univ_calif(String c){
	
	String [] full_calif = {"University of California Berkeley","University of California Davis","University of California Irvine","University of California Los Angeles","University of California Riverside","University of California San Diego","University of California San Francisco","University of California Santa Barbara","University of California Santa Cruz"};
	String [] abb_calif = {"Univ Calif Berkeley","Univ Calif Davis","Univ Calif Irvine","Univ Calif Los Angeles","Univ Calif Riverside","Univ Calif San Diego","Univ Calif San Francisco","Univ Calif Santa Barbara","Univ Calif Santa Cruz"};
	String c_ret = c.replace(",", "");
	
	for (int c1 = 0;c1<full_calif.length;c1++){
		
	if(c_ret.contains(full_calif[c1])){
		return c_ret.replace(full_calif[c1], abb_calif[c1]);
	}		
	}
	
	return c;
			
}

}
