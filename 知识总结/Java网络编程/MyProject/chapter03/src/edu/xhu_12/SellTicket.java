package edu.xhu_12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
 *用ReentrantLock
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //定义100张票
	private Lock lock=new ReentrantLock();  //创建锁对象 
//	@Override
//	public void run() {
//		while(true)
//		{
//			lock.lock();  //获取锁
//			if(tickets>0){
//				//稍作延时
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName()+"正在出售第"+tickets--+"张票");
//			}
//			lock.unlock();//释放锁   
//		}
//	}
	//规范用法
	@Override
	public void run() {
		while(true)
		{
			try{
				lock.lock();  //获取锁
				if(tickets>0){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"正在出售第"+tickets--+"张票");
				}
			}finally{
				lock.unlock();//释放锁   
			}
		}
	}
}
