package edu.xhu_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class SynchronizedCollection {
	public static void main(String[] args) {
		//java提供的集合里，下列类是线程安全的
		StringBuffer sb=new StringBuffer();
		Vector<String> v=new Vector<String>();
		Hashtable<String,String> ht=new Hashtable<String,String>();
		//另外Collections类里还提供了各种线程安全的容器类
		//public static <T> List<T> synchronizedList(List<T> list);
		List<String> list=new ArrayList<String>();//线程不安全
		List<String> list1=Collections.synchronizedList(new ArrayList<String>());
		//线程安全的List
	}
}
