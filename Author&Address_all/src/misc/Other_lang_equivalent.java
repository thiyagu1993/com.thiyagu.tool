package misc;

import country_policy_conv.Convert_germany;
import country_policy_conv.Convert_mexico;
import country_policy_conv.Convert_spain;
import resource.Excel_resource;

public class Other_lang_equivalent {

	public static String lang_equivalent(String s){

		String ret_string="";
		String country="";
		
		try{
			country = s.toLowerCase().substring(s.lastIndexOf(" ")).trim();
		}catch(Exception e){
			e.printStackTrace();
			return s;
		}
		
		for(int i=0;i<Excel_resource.country_list.size();i++){
		
			if(country.equals(Excel_resource.country_list.get(i).toLowerCase())){
			System.out.println(country);
			switch(country){

			case "germany":
		
			return ret_string = Convert_germany.conv(s);
			

			case "spain":
				
			return ret_string = Convert_spain.conv(s);

			
			case "mexico":
				
			return ret_string = Convert_mexico.conv(s);
			
			
					
			
			
			
			
			
			
			}
			}
		}
			
			return s;
	}


}

