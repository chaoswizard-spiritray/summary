package edu.xhu_06;

/*
 * 需求：某电影院在上演一部电影，有100张票，有3个售票窗口，编写程序模拟售票过程
 * 分析：
 * A: 编写售票线程，用方式1实现
 * B: 创建3个线程对象
 * C: 启动线程对象
 * 问题：有重票售出
 * 原因：线程执行时抢夺CPU，你执行到一部分的时候执行权被抢走了，这是tickets还没有被减去
 * 注意该方式生成了3个对象，资源耗费
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell1=new SellTicket();  //
		SellTicket sell2=new SellTicket();
		SellTicket sell3=new SellTicket();
		
		sell1.setName("窗口1");
		sell2.setName("窗口2");
		sell3.setName("窗口3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
