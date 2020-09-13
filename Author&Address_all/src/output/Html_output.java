package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class Html_output {

	private static PrintWriter out;
	
	public static void html(String w) throws FileNotFoundException, UnsupportedEncodingException, NoSuchFieldException, SecurityException{
		
		out = new PrintWriter(new File("C:\\Author_test\\text.html"),"UTF-8");
		System.out.println(w);
	
		
		out.write("<html><head>d</head><body>dddd</body></html>");
		out.close();
		
	}
		
}
