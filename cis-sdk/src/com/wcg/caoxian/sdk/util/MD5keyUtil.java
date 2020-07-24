package com.wcg.caoxian.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 * MD5加密工具类，
 */
public class MD5keyUtil {

    private static Logger log = LoggerFactory.getLogger(MD5keyUtil.class);

    /**
     * 返回加密的密文
     *
     * @param str 要加密的字符串
     * @return
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString().toUpperCase();
    }
    
    /*public static void main(String[] args) {      
    	 //测试      
    	 System.out.println(MD5keyUtil.getMD5Str("pass1234"));  
    } */
    /*public static void main(String[] args) {
		int[] arr = new int[]{8,5,3,1,0,7};
		int[] index = new int[]{3,0,1,3,0,1,4,1,2,5,1};
		String tel = "";
		for (int i : index) {
			tel += arr[i];
		}
		System.out.println(tel);
	}*/
    
    public static void main(String[] args) throws ParseException {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	try  
    	{   
    	    Date d1 = df.parse("2017-08-12 13:31:40");   
    	    Date d2 = df.parse("2017-08-12 14:00:41");   
    	    long diff = d2.getTime() - d1.getTime();   
    	    long days = diff / (1000 * 60 * 60 * 24);   
    	    long minutes = diff / (1000 * 60 ); 
    	    System.out.println(minutes);
    	}   
    	catch (Exception e)   
    	{   
    	}  
	}
    
/*    public static void main(String[] args) {
    	System.out.println("请输入...");
    	  Scanner in = new Scanner(System.in);
    	  String number = in.next();
    	  System.out.pri12ntln("您输入的是"+number);
    	  boolean flag = true;
    	  for(int i=0;i<number.trim().length()/2;i++){
    	   //把索引为i位置的字符和它对称索引位置的字符相比，看是否一样
    	    if(!String.valueOf(number.charAt(i)).equals(String.valueOf(number.charAt(number.trim().length()-i-1)))){
    	    flag = false;
    	    break;
    	   }
    	  }
    	  if(flag){
    	   System.out.println("它是回文数");
    	  }else{
    	   System.out.println("它不是回文数");
    	  }
    	Scanner sc = new Scanner(System.in);  
        System.out.println("请输入一个整数：");  
        int num = sc.nextInt();  
  
        String str1 = num + "";  
        // String str1 = Integer.toString(num);  
        // String str1 = String.valueOf(num);  
  
        StringBuilder str2 = new StringBuilder(str1);  
        str2.reverse();  
        int count = 0;  
  
        for (int i = 0; i < str1.length(); i++) {  
            if (str1.charAt(i) != str2.charAt(i)) {  
                System.out.println(str1 + "不是回文数");  
                break;  
            } else {  
                count++;  
            }  
        }  
        if (count == str1.length()) {  
            System.out.println(str1 + "是回文数");  
        }  
	}*/
    
   
    
}
