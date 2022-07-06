package edu.xhu_10;

public class SellTicket implements Runnable {
	private  int tickets=100;   //定义100张票
	private Object obj=new Object();  //定义同一把锁
	private int x=0;
	@Override
	public void run() {
		
		while(true)
		{
			if(x%2==0){  //为了解释同步方法的锁问题，我们既用同步代码块，也用同步方法演示
						//代码块用obj锁，运行程序存在问题，问题原因肯定是锁有问题，
						//可能是代码块的锁与同步方法的锁不是同一把锁。那么同步方法的锁
				        //是什么对象锁呢？我们把obj对象换成this对象，问题解决了。
						//说明同步方法用的锁对象是this对象。
						//用同样方法验证静态同步方法用的该类的字节文件对象。SellTicket.class
				synchronized(obj){ 
					if(tickets>0){
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
			else
			{
				sellTicket();   //同步方法
			}
			x++;
		}
	}


	private synchronized void sellTicket() {
		
			if(tickets>0){
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
