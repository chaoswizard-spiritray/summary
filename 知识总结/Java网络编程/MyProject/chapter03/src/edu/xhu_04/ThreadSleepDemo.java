package edu.xhu_04;

public class ThreadSleepDemo {

	public static void main(String[] args) {
		ThreadSleep sleep1=new ThreadSleep();
		ThreadSleep sleep2=new ThreadSleep();
		ThreadSleep sleep3=new ThreadSleep();
		sleep1.setName("�߳�1");
		sleep2.setName("�߳�2");
		sleep3.setName("�߳�3");
		sleep1.start();
		sleep2.start();
		sleep3.start();

	}

}
