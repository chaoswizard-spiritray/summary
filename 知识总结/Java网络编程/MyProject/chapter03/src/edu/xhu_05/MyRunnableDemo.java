package edu.xhu_05;
/*
 * 线程实现方法2：
 * 步骤：
 * A:写一个类，实现Runnable接口
 * B:实现run()方法
 * C:创建该类对象
 * D:创建Thread对象，将C步创建的对象作为Thread的构造参数
 * E:执行Thread对象的start()方法，启动线程
 */
public class MyRunnableDemo {

	public static void main(String[] args) {
		//C:
		MyRunnable mr=new MyRunnable();
		//D:
//		Thread td1=new Thread(mr);
//		Thread td2=new Thread(mr);
//		//E:
//		td1.setName("线程1");
//		td2.setName("线程2");
		//Thread(Runnable target, String name) 	分配一个新的 Thread对象
		Thread td1=new Thread(mr,"线程1");
		Thread td2=new Thread(mr,"线程2");
		td1.start();
		td2.start();

	}

}
