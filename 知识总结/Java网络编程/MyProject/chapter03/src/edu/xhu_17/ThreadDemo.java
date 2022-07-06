package edu.xhu_17;
/*
 * 若想在程序中直接开一线程来执行某一个任务，更简单的方式就是使用匿名内部类
 * 匿名内部类多线程的使用方式：
 * 1、匿名内部类的格式：		
 * 		new 类或接口名(){
 * 			重写方法;
 * 		};
 * 		本质：创建一个匿名对象
 * 2、匿名内部类多线程
 * 		new Thread(){
 * 			重写run方法;
 * 		}.start()
 * 
 * 		new Thread(new Runnable(){
 * 			实现run方法;
 * 		}){
 * 		}.start();
 * 
 */
public class ThreadDemo {

	public static void main(String[] args) {
		new Thread("线程1"){
			public void run() {
				for(int i=0;i<50;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			};
		}.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		},"线程2"){}.start();

	}

}
