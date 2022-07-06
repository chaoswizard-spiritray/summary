package edu.xhu_08;

/*
 * 真实模拟售票过程，加一点延时
 * 问题出现：
 * 1、一个票被出售多次。  
 * 2、还可能出现-1，-2的问题
 * 原因：
 * 一个窗口售票过程由下列几步完成：
 * 		a:取tickets的当前值
 * 		b:判断tickets当前值是否大于0
 * 		c:售出当前票
 * 		d:当前tickets值减1
 * 		注意：线程的执行过程是随机强占CPU执行权，在上面4步的执行过程中，在某一步会被其他线程抢走执行权，
 * 			而进入另一售票线程。
 * 		假定窗口1售票时tickets的值=100，在输出当前出售第100张票后，被抢走执行权，d步未执行。
 * 		那么在另一个线程中，共享的tickets的值还没有被减1，也是100，该票又要被出售一次，因此会出现
 * 		第100张票会被出售多次。
 * 若在tickets=1是，某线程判断tickets大于0成立后被抢走执行权，而另两个线程抢到执行权，
 * 判断tickets仍然大于0,继续卖，将tickets已经减1，再回到第一个线程时，tickets的值已经发生变化，
 * 但由于该执行c步和d步，tickets的值会继续使用，继续减1。会出现-1或-2的情况。这个是由于线程还没执行完，
 * 会继续执行引起的问题。
 * 
 * 
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
