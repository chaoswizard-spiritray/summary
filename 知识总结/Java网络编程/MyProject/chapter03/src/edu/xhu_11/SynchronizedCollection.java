package edu.xhu_11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class SynchronizedCollection {
	public static void main(String[] args) {
		//java�ṩ�ļ�������������̰߳�ȫ��
		StringBuffer sb=new StringBuffer();
		Vector<String> v=new Vector<String>();
		Hashtable<String,String> ht=new Hashtable<String,String>();
		//����Collections���ﻹ�ṩ�˸����̰߳�ȫ��������
		//public static <T> List<T> synchronizedList(List<T> list);
		List<String> list=new ArrayList<String>();//�̲߳���ȫ
		List<String> list1=Collections.synchronizedList(new ArrayList<String>());
		//�̰߳�ȫ��List
	}
}
