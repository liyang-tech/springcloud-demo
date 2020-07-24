package com.wcg.caoxian.cust;

import java.util.ArrayList;
import java.util.List;

public class SourceA {
	private List<String> list = new ArrayList<String>();
    public synchronized void getSource(){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public synchronized void setSource(String id){
        list.add(id);
    }
}
