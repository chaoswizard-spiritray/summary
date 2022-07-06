package edu.xhu_07;
/* 
 * 方式2：
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //定义100张票
	@Override
	public void run() {
		while(true)
		{
			if(tickets>0){
				System.out.println(Thread.currentThread().getName()+"正在出售第"+tickets--+"张票");
			}
		}
	}
}
