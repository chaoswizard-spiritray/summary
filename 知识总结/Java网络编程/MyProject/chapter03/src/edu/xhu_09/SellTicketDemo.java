package edu.xhu_09;

/*
 * 上一个实例中出现的问题称为线程安全问题。
 * 判断是否会出现安全问题的标准：
 * A: 是否是多线程环境？
 * B: 是否有共享数据？
 * C: 操作由多条语句操作共享数据？
 * 上一个实例满足上面3个标准，必然会出现安全问题。
 * 如何解决上面的问题呢？
 * A: B:不是我们能解决的，只能在C:上想办法解决。
 * 能否将多条操作语句封装为一个整体，使它具有原子性，即在执行这几条语句时不能被其他线程抢走执行权。
 * Java提供了一个叫同步的机制可以坚决该问题，用法如下：
 * 		synchronized(对象){
 * 			需要同步的代码块；
 * 		}
 * 		A:对象是什么？可以随便创建一个对象试试
 * 		B: 需要同步的代码是哪些？就是共享数据操作的那些代码
 * 同步解决了线程安全问题，但同步降低了程序执行效率，当线程很多时，每个线程都要判断同步是否上锁，
 * 很耗费资源。
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
