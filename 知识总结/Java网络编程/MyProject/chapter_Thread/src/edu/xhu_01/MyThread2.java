package edu.xhu_01;
/*
 * ���߳���һ������
 */
public class MyThread2 extends Thread {
	public MyThread2(){
		
	}
	public MyThread2(String name){
		this.setName(name);
	}
	@Override
	public void run() {
		for(int x=0;x<100;x++){
			System.out.println(getName()+":"+x);
		}
	}
}
