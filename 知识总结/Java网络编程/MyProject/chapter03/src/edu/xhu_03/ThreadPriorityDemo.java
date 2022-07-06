package edu.xhu_03;
/*
 * 线程调度：
 * 		假设计算机只有一个CPU，在某一时刻只能执行一条指令，线程只有在得到CPU执行权时才能被执行。
 * 		Java是如何对线程进行调度的呢？
 * 两种调度模型：
 * 		1、分时调度模型：所有线程轮流使用CPU执行权，平均分配每个线程占用CPU的执行时间
 * 		2、抢占式调度模型：让优先级高的线程先得到CPU执行权，相同优先级的随机调度。
 * Java使用抢占式调度模型
 * 如何让线程优先执行呢？
 * 		设置线程的优先级 public final void setPriority(int);
 * 		获取线程优先级    public final int getPriority();
 * Java线程优先级为1-10,值越大优先级越高，默认优先级为5
 * 线程优先级仅仅表示获取CPU的时间片稍微多一点，而且要在执行次数较多时才能体现出来
 * 并不表示它一定会优先执行，它的执行同样具有随机性。
 * 
 * 
 */
public class ThreadPriorityDemo {

	public static void main(String[] args) {
		PriorityThread pt1=new PriorityThread();
		PriorityThread pt2=new PriorityThread();
		PriorityThread pt3=new PriorityThread();
		pt1.setName("线程1");
		pt1.setPriority(10);
		pt2.setName("线程2");
		pt3.setName("线程3");
		pt3.setPriority(1);
		pt1.start();
		pt2.start();
		pt3.start();
		

	}

}
