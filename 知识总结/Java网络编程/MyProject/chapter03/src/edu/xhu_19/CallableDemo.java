package edu.xhu_19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*
 ��ʾ�ڶ����첽ִ�з�ʽ��
 ���󳡾�����׼�����˵Ĺ����У� ����ͬʱ׼�����ӣ�����ֻ��Ҫ�ȴ�3���ӡ�
 Java�ṩ��Future�ӿھ����첽ִ�з�ʽ��
 Future�ӿڵ�ʵ����FutureTask��
 
 ������
 A:��ʵ�������߳��� BumThread��ColdDishThread���ֱ�ȴ�3���1��
 B:ִ��BumThread�̣߳���ɺ���ִ��ColdDishThread�̣߳�Ҫ�ȴ��߳���ɣ���join��������
 C:����ʱ��
 */
public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start=System.currentTimeMillis();
		
		FutureTask<String> ft1=new FutureTask<String>(new BumThread());
		
		new Thread(ft1).start();
		
		
		FutureTask<String> ft2=new FutureTask<String>(new ColdDishThread());
		new Thread(ft2).start();
		
		System.out.println(ft1.get());
		System.out.println(ft2.get());
		
		long end = System.currentTimeMillis();
		System.out.println("׼�����ʱ�䣺"+(end-start));

	}

}
