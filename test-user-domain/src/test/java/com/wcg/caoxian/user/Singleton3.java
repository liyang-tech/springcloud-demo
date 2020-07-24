package com.wcg.caoxian.user;

import java.util.ArrayList;
import java.util.List;

public class Singleton3 {//懒汉式 线程安全

	private Singleton3(){};//私有构造函数
	
	private static Singleton3 singleton3;//私有静态变量
	
	public static Singleton3 getInstance(){//公共静态构造方法
		if(singleton3 == null){
			synchronized (singleton3) {
				if(singleton3 == null){
					singleton3 = new Singleton3();
				}
			}
		}
		return singleton3;
	}
	
	
	public static void main(String[] args) {
		
	    List<Integer> list = new ArrayList<Integer>();
	    list.add(1);
	    list.add(2);
	    list.add(3);
	    System.out.println(list.toString().substring(1, list.toString().length()-1));
	    
	    /*String str = String.join(",", (String[])list.ToArray(Typeo));
	    
	    StringBuilder sb = new StringBuilder(); 
	    for (Integer integer : list) {
	    	sb = sb.append(integer);
		}
	    System.out.println(sb);*/
	    /*StringBuilder sb = new StringBuilder(); 
	    for (Map<String, Object> m : list){
	      for (String k : m.keySet()) {
	    	sb = sb.append(m.get(k));
	      }  
	    }  
	    System.out.println(sb); */ 
	    
	}
	
}
