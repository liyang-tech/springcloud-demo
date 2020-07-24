package com.wcg.caoxian.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListSetTest {

	public static void main(String[] args) {
		//Set<String> orgCds = new HashSet<String>();
		
		
		
		List<String> orgCds = new ArrayList<String>();
		orgCds.add("123");
		orgCds.add("456");
		orgCds.add("789");
		orgCds.add("234");
		orgCds.add("234");
		orgCds.add("123");
		System.out.println("111111="+orgCds);
		//new ArrayList<String>(new TreeSet<String>(orgCds));
		System.out.println("222222="+new ArrayList<String>(new TreeSet<String>(orgCds)));
		System.out.println("333333="+new TreeSet<String>(orgCds));
		
		
		/*
		List<String> orgCdList = new ArrayList<String>();
		Set<String> newSet = new HashSet<String>();
		for (String orgcd : orgCds) {
			if(newSet.add(orgcd)){
				orgCdList.add(orgcd);
            }
		}
		System.out.println(orgCdList);*/
	}
	
}
