package misc;

public class Country_field {

	public static boolean country_field_error(String country){
	
		
	char[] c = country.toCharArray();
	
	for(char n : c){
		if(n==38)
			continue;
		if(n<=64&&n>=33){
			
			if(n<=47&&n>=33){
				System.out.println("symbol "+n);
			return true;
			}else if (n<=64&&n>=58) {
				System.out.println("symbol "+n);
				return true;
			}

		}else if(n<=96&&n>=91) {
			
			System.out.println("symbol "+n);
			return true;
		}else if(n<=126&&n>=123){
			System.out.println("symbol "+n);
			return true;
		}

	}
				
	return false;	
	}
	
	
	
	
}
