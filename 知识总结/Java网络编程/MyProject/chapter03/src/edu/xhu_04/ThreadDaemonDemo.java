package edu.xhu_04;
/*
 *需求：游戏里王被擒了，其余的兵将也没有存在的意义了，它们也不会继续再玩下去了。
 *		兵将存在的意义是守护王的
 *	刘备线程（主线程）若结束了，关羽和张飞线程（子线程）必须结束 
 *分析：
 *	将关羽、张飞线程设置为守护线程即可
 *	必须在启动线程之前设置。
 *方法：
 *	public void setDaemon(boolean on);
 * 
 *但需要注意的是：
 *	主线程结束的时候，其他线程可能还在运行中，还有最后一口气。 
 */
public class ThreadDaemonDemo {

	public static void main(String[] args) {
		ThreadDaemon td1=new ThreadDaemon();
		ThreadDaemon td2=new ThreadDaemon();
		
		td1.setName("关羽");
		td2.setName("张飞");
		
		td1.setDaemon(true);
		td2.setDaemon(true);
		
		td1.start();
		td2.start();
		Thread.currentThread().setName("刘备");
		for(int i=0;i<30;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}

}
