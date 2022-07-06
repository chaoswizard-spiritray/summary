package edu.xhu_05;
/*
 * 线程实现方法2：
 * 1、写一个类，实现Runnable接口
 * 2、实现run()方法
 */

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<50;i++)
			System.out.println(Thread.currentThread().getName()+":"+i);

	}

}
