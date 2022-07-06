package edu.xhu_01;
/*
 * 该类为什么要重写run()方法呢？
 * 一个类可能会有很多方法，但不是所有的方法都需要线程去执行，只有run()方法里的代码才被线程执行。
 */
public class MyThread extends Thread {
	@Override
	public void run() {
		//一般是耗时的代码才需要用线程来执行，用循环模拟一下
		for(int i=0;i<100;i++)
		{
			System.out.println(i);
		}
	}
}
