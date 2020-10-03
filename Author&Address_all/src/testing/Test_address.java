package testing;

import java.io.IOException;

import misc.General_conversion;
import resource.Excel_resource;

public class Test_address {

	public static void main(String[] args) throws IOException {
		
		String s = "Mathematique";
		Excel_resource.new_resource();		
		for(int i=0;i<Excel_resource.hold1.size();i++){
			
			s=s.replace(Excel_resource.hold1.get(i), Excel_resource.hold2.get(i));
		}
		System.out.println(s);
		
	}
	
}
