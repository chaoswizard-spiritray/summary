package edu.xhu_06;

public class SellTicket extends Thread {
	private static int tickets=100;   //定义100张票
	@Override
	public void run() {
		while(true)
		{
			if(tickets>0){
				System.out.println(getName()+"正在出售第"+tickets--+"张票");
			}
		}
	}
}
