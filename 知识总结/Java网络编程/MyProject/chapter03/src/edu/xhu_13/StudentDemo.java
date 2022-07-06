package edu.xhu_13;
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
