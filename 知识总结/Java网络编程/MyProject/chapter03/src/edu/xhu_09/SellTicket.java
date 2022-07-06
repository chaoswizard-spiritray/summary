package edu.xhu_09;

public class SellTicket implements Runnable {
	private  int tickets=100;   //定义100张票
	private Object obj=new Object();  //定义同一把锁
	@Override
	public void run() {
		while(true)
		{
			synchronized (new Object()){ //这样写是每个线程都有一个自己的对象锁，而我们希望是所有线程使用一把锁
				if(tickets>0){
					//稍作延时
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"正在出售第"+tickets--+"张票");
				}
			}
		}
	}
//	public void run() {
//		while(true)
//		{
//			//工作原理：这里线程1、2、3均可以走到这里，但谁先进来，谁就锁门，其余线程再进来时发现有锁
//			//则进不去。
//		
//			synchronized (obj){ 
//				if(tickets>0){
//					//稍作延时
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()+"正在出售第"+tickets--+"张票");
//				}
//			}
//		}
//	}
}
