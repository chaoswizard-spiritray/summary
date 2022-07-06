package edu.xhu_10;

/*
 *1、同步锁对象可以是任意对象
 *2、将同步代码块作为一个方法，该方法称为同步方法
 *	格式：在方法头上加synchronized关键字
 *		private synchronized void sellTicket()
 *		方法体中去掉同步锁
 *	那么方法里用的是什么锁对象呢？
 *		this对象
 *  静态同步方法的锁对象是：当前类字节文件即：class文件对象，对于本例，锁对象为SellTicket.class
 *  静态方法是随着类的加载而加载，加载后唯一存在的该类的class对象
 *  类的class对象在反射时会使用到。
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell=new SellTicket();  
		Thread sell1=new Thread(sell);
		Thread sell2=new Thread(sell);
		Thread sell3=new Thread(sell);
		
		sell1.setName("窗口1");
		sell2.setName("窗口2");
		sell3.setName("窗口3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
