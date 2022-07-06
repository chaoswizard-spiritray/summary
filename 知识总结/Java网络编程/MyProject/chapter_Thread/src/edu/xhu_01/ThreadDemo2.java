package edu.xhu_01;

public class ThreadDemo2 {
	public static void main(String[] args) {
		//创建线程对象
		MyThread2 td1=new MyThread2();
		MyThread2 td2=new MyThread2();
		
		td1.setName("Thread1");
		td2.setName("Thread2");
		td1.start();
		td2.start();
		for(int x=0;x<100;x++){
			System.out.println(Thread.currentThread().getName()+":"+x);
		}
	}
	
	

}
