package edu.xhu_01;
/*
 * Java 多线程第一种方法：继承Thread类，重写run()方法
 */
public class MyThread1 extends Thread {
	//该类允许其他方法成员和数据成员，
	@Override
	//需要被线程执行的代码，需要写在run方法里。一般来说，执行某项耗时任务时，需要线程来完成。或者处理并发任务。
	public void run() {
		for(int x=0;x<100;x++){
			System.out.println(getName()+":"+x);
		}
	}
}
