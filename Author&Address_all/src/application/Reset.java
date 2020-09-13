package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

public class Reset {

	public static void Reset_void(){
		
		if(Files.isDirectory(Paths.get("C:\\Address_test_all\\"))){
			//Resetting method
				System.out.println("yes");
		
			try {
				FileUtils.cleanDirectory(new File("C:\\Address_test_all\\"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			File f1 = new File("C:\\Address_test_all\\Author&Address_all");
			File f2 = new File("C:\\Address_test_all\\Author&Address_all\\BINARY");
			File f3 = new File("C:\\Address_test_all\\Author&Address_all\\OUTPUT");
			File f4 = new File("C:\\Address_test_all\\Author&Address_all\\DATA");
			File f5 = new File("C:\\Address_test_all\\Author&Address_all\\Address_temp");
			
			if(!f1.exists()&&!f1.isDirectory()){
				System.out.println("true");
				f1.mkdir();	
			}
			if(!f2.exists()&&!f2.isDirectory()){
				System.out.println("true");
				f2.mkdir();	
			}
			if(!f3.exists()&&!f3.isDirectory()){
				System.out.println("true");
				f3.mkdir();	
			}
			if(!f4.exists()&&!f4.isDirectory()){
				System.out.println("true");
				f4.mkdir();	
			
				try{
					Data_reset();
					}catch(UnknownHostException u){
						
					}
			}
			
			if(!f5.exists()&&!f5.isDirectory()){
				System.out.println("true");
				f5.mkdir();	
			}

			}else{
				System.out.println("no");
				File f = new File("C:\\Address_test");
				File f1 = new File("C:\\Address_test_all\\Author&Address");
				File f2 = new File("C:\\Address_test_all\\Author&Address_all\\BINARY");
				File f3 = new File("C:\\Address_test_all\\Author&Address_all\\OUTPUT");
				File f4 = new File("C:\\Address_test_all\\Author&Address_all\\DATA");
				File f5 = new File("C:\\Address_test_all\\Author&Address_all\\Address_temp");
				
				if(!f.exists()&&!f.isDirectory()){
					System.out.println("true");
					f.mkdir();
				
					if(!f1.exists()&&!f1.isDirectory()){
						System.out.println("true");
						f1.mkdir();	
					
					if(!f2.exists()&&!f2.isDirectory()){
						System.out.println("true");
						f2.mkdir();	
					}
					if(!f3.exists()&&!f3.isDirectory()){
						System.out.println("true");
						f3.mkdir();	
					}
					if(!f4.exists()&&!f4.isDirectory()){
						System.out.println("true");
						f4.mkdir();	
						try{
						Data_reset();
						}catch(UnknownHostException u){
							
						}
						
						}
					if(!f5.exists()&&!f5.isDirectory()){
						System.out.println("true");
						f5.mkdir();	
					}
					
					}
				
				}
				
			}	
	}
	
@SuppressWarnings("unchecked")
private static void Data_reset() throws UnknownHostException{
	
	JSONObject jo = new JSONObject();
	jo.put("Username", System.getProperty("user.name"));
	jo.put("Hostname", Inet4Address.getLocalHost().getHostName());
	jo.put("Hostaddress", Inet4Address.getLocalHost().getHostAddress());
	JSONObject jo1 = new JSONObject();
	jo1.put("All", jo);

	
	try{
		
		FileWriter json_file = new FileWriter("C:\\Address_test_all\\Author&Address_all\\DATA\\user.json");
		json_file.write(jo.toJSONString());
		json_file.flush();
		json_file.close();
	}catch(IOException e){
		e.printStackTrace();
	}
	
}
	
}
