package edu.xhu_07;

/*
 * 需求：某电影院在上演一部电影，有100张票，有3个售票窗口，编写程序模拟售票过程
 * 分析：
 * A: 编写售票线程，用方式2实现
 * B: 创建1个线程对象
 * C: 启动3个线程对象
 * 问题：有重票售出
 * 原因：线程执行时抢夺CPU，你执行到一部分的时候执行权被抢走了，这是tickets还没有被减去
 * 资源共享了，代码共享了。
 * 
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell=new SellTicket();  //
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
