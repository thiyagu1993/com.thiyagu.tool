package misc;

public class Error_spotter {

	
	public static String error_spot(String a, String b){
		
		
		
		System.out.println(char_check(a,b));
		
		StringBuilder sb = new StringBuilder(a);
		sb.insert(char_check(b,a), "-->|");
		
		System.out.println(sb.toString());
		return sb.toString();
			
	}
	
	
public static int char_check(String c, String c1){
	
	char[] chars = c.toCharArray();
	char[] check = c1.toCharArray();
		try{
		for(int i=0;i<chars.length;i++){
		
			if(chars[i]!=check[i]){
//				System.out.println(chars[i]+" - "+check[i]);
				return i;
			}else{
				
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Not able to get Error character");
		}
		
		return 0;
		
	}

}
