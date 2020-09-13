package misc;

import country_policy_conv.Convert_argentina;
import country_policy_conv.Convert_austria;
import country_policy_conv.Convert_bolivia;
import country_policy_conv.Convert_brazil;
import country_policy_conv.Convert_chile;
import country_policy_conv.Convert_colombia;
import country_policy_conv.Convert_costa_rica;
import country_policy_conv.Convert_cuba;
import country_policy_conv.Convert_ecuador;
import country_policy_conv.Convert_france;
import country_policy_conv.Convert_germany;
import country_policy_conv.Convert_honduras;
import country_policy_conv.Convert_italy;
import country_policy_conv.Convert_luxembourg;
import country_policy_conv.Convert_mexico;
import country_policy_conv.Convert_mozabique;
import country_policy_conv.Convert_nicaragua;
import country_policy_conv.Convert_panama;
import country_policy_conv.Convert_paraguay;
import country_policy_conv.Convert_peru;
import country_policy_conv.Convert_poland;
import country_policy_conv.Convert_portugal;
import country_policy_conv.Convert_spain;
import country_policy_conv.Convert_uruguay;
import country_policy_conv.Convert_venezeula;
import resource.Excel_resource;

public class Other_lang_equivalent {

	public static String lang_equivalent(String s){

		String ret_string="";
		String country="";

		try{
			country = s.replace(".", "").toLowerCase().substring(s.lastIndexOf(" ")).trim();
		}catch(Exception e){
			e.printStackTrace();
			return s;
		}

		for(int i=0;i<Excel_resource.country_list.size();i++){

			if(country.equals(Excel_resource.country_list.get(i).toLowerCase())){
				
				switch(country){

				case "germany":

					return ret_string = Convert_germany.conv(s);


				case "spain":

					return ret_string = Convert_spain.conv(s);

				case "france":

					return ret_string = Convert_france.conv(s);
					
				case "mexico":

					return ret_string = Convert_mexico.conv(s);

				case "austia":

					return ret_string = Convert_austria.conv(s);

				case "luxembourg":

					return ret_string = Convert_luxembourg.conv(s);

				case "italy":

					return ret_string = Convert_italy.conv(s);

				case "poland":

					return ret_string = Convert_poland.conv(s);

				case "brazil":

					return ret_string = Convert_brazil.conv(s);

				case "mozabique":

					return ret_string = Convert_mozabique.conv(s);

				case "portugal":

					return ret_string = Convert_portugal.conv(s);

				case "argentina":

					return ret_string = Convert_argentina.conv(s);

				case "bolivia":

					return ret_string = Convert_bolivia.conv(s);

				case "chile":

					return ret_string = Convert_chile.conv(s);

				case "colombia":

					return ret_string = Convert_colombia.conv(s);

				case "costa rica":

					return ret_string = Convert_costa_rica.conv(s);

				case "cuba":

					return ret_string = Convert_cuba.conv(s);

				case "ecuador":

					return ret_string = Convert_ecuador.conv(s);

				case "honduras":

					return ret_string = Convert_honduras.conv(s);

				case "nicaragua":

					return ret_string = Convert_nicaragua.conv(s);

				case "panama":

					return ret_string = Convert_panama.conv(s);

				case "paraguay":

					return ret_string = Convert_paraguay.conv(s);

				case "peri":

					return ret_string = Convert_peru.conv(s);

				case "uruguay":

					return ret_string = Convert_uruguay.conv(s);

				case "venezeula":

					return ret_string = Convert_venezeula.conv(s);

				}
			}
		}

		return s;
	}


}

