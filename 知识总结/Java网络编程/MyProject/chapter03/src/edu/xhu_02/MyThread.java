package edu.xhu_02;
/*
 * 如何获取线程的名称呢？
 */
public class MyThread extends Thread {
	public MyThread(){
	}
	public MyThread(String name){
		super(name);
	}
	@Override
	public void run() {
		for(int i=0;i<200;i++)
			System.out.println(getName()+":"+i);
	}
}
