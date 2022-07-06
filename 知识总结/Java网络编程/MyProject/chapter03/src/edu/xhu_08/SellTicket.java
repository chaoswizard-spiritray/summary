package edu.xhu_08;
/* 
 * 方式2：
 * 为了更好模拟售票系统，需要有一点延时，网络需要传输数据。
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //定义100张票
	@Override
	public void run() {
		while(true)
		{
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
