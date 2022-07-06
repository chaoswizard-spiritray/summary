package edu.xhu_12;
/*
 * JDK5里提供一个Lock接口及它的实现类ReentranLock类，该类有两个方法lock() 获取锁,unlock()释放锁
 * 它的使用更方便
 * 
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		SellTicket st=new SellTicket();
		Thread sell1=new Thread(st,"窗口1");
		Thread sell2=new Thread(st,"窗口2");
		Thread sell3=new Thread(st,"窗口3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
