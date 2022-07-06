package edu.xhu_19;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*
 演示第二种异步执行方式：
 需求场景：在准备凉菜的过程中， 可以同时准备包子，这样只需要等待3分钟。
 Java提供的Future接口就是异步执行方式。
 Future接口的实现类FutureTask。
 
 分析：
 A:先实现两个线程类 BumThread和ColdDishThread，分别等待3秒和1秒
 B:执行BumThread线程，完成后再执行ColdDishThread线程，要等待线程完成，用join方法控制
 C:计算时间
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
		System.out.println("准备完毕时间："+(end-start));

	}

}
