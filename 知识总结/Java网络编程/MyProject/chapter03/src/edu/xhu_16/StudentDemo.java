package edu.xhu_16;
/*
 * 在上面的两个线程中，通过学生对象的flag标志，确定线程是否等待，并直接访问了数据成员。
 * 若对象数据成员为私有属性，线程是无法访问的。那么程序该如何写呢？
 * 解决方法：在共享的资源类里我们需要编写方法接口，供线程调用。
 * 方法能编写成普通方法吗？
 * 		设置对象的值和获取对象的值要求同步，将同步代码块变成方法完成，该方法应该为同步方法。
 */
public class StudentDemo {

	public static void main(String[] args) {
		
		Student s=new Student();
		
		SetThread st=new SetThread(s);
		GetThread gt=new GetThread(s);
		
		Thread t1=new Thread(st);
		Thread t2=new Thread(gt);
		t2.start();
		t1.start();   
		

	}

}
