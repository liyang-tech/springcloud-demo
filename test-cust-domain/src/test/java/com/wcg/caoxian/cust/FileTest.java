package com.wcg.caoxian.cust;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileTest {

	public static void main(String[] args) throws IOException {
		File f = new File("d:"+File.separator+"abc.txt");
		Writer out = new FileWriter(f);
		String str = "我爱小五";
		out.write(str);
		out.close();
		 String s1 = "Programming";
	        String s2 = new String("Programming");
	        String s3 = "Program" + "ming";
	        System.out.println(s1 == s2);
	        System.out.println(s1 == s3);
	        System.out.println(s1 == s1.intern());
	}
	
}
