package resource;

public class Word_endings {

	public static void main(String[] args){
		
		String s = "Zoology";
		
		String alphabetics[] = {"z","t","y","u"};
		
		for(String d:alphabetics){
			
		if(d.equals(s.toLowerCase().substring(0,1))){
	
			switch(d){
			
			case "z":
				String[] letter_z = {"Zoology","Zentrum"};
				String[] letter_z1={"Zool","Zent"};
		
				
				for(int w=0;w<letter_z.length;w++){
					if(letter_z[w].toLowerCase().equals(s.toLowerCase())){
						System.out.println(letter_z[w]+" found for "+letter_z1[w]);
					}
					
				}
				
			break;
			
			case "u":
				System.out.println("correct");
			break;
			
			}

		}
	}
	}
}
