package edu.xhu_15;
/*
 * 线程间通信：不同线程针对同一资源的操作，比较典型的案例就是生产线程和消费线程对产品的操作
 * 需求：通过设置线程（生产者）和获取线程（消费者）对同一个学生对象进行操作
 * 分析：
 * 		A:资源类   Student
 * 		B:设置线程   SetThread  (生产者)
 * 		C:获取线程  GetThread  (消费者)
 * 		D:测试类  StudentDemo
 * 问题1：每个线程都创建一个学生对象，这不是对同一个资源操作。
 * 应该在外界创建一个资源，将资源传递给线程
 * 问题2：出现数据混乱的情况
 * 原因：线程安全问题
 * 解决办法：加锁，一定是同一把锁。把外界传进来的学生对象作锁就可以了。
 * 问题3：若消费者线程先抢到CPU的执行权，而生产线程还没有设置好学生信息，消费者输出的数据会存在问题。
 * 		若生产者线程先抢到CPU的执行权，会产生学生对象，若继续拥有执行权，则会继续生产，
 * 		而前面生产的数据还没有消费，就被扔掉了。这不符合实际情况。
 * 解决办法：使用等待/唤醒机制
 * Object类提供了3个方法：wait()等待，notify()唤醒单个线程，notifyAll()唤醒所有线程
 * 这些方法必须通过锁对象调用，而锁对象可以是任意对象，因此由Object类提供这些方法可以用在任意对象上
 */
public class StudentDemo {

	public static void main(String[] args) {
		
		Student s=new Student();
		
		SetThread st=new SetThread(s);
		GetThread gt=new GetThread(s);
		
		Thread t1=new Thread(st);
		Thread t2=new Thread(gt);
		t2.start();
		t1.start();   //假设t2线程（消费线程）先于t1线程（生产线程）执行的话，输出的结果可能为null--0
		

	}

}
