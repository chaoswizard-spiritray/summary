package edu.xhu_02;

public class ThreadDemo {

	public static void main(String[] args) {
//		MyThread mt1=new MyThread();
//		MyThread mt2=new MyThread();
//		mt1.setName("�߳�1");
//		mt2.setName("�߳�2");
//		mt1.start();
//		mt2.start();
		MyThread mt1=new MyThread("�߳�1");
		MyThread mt2=new MyThread("�߳�2");
		mt1.start();
		mt2.start();
		for(int i=0;i<100;i++)
			System.out.println(Thread.currentThread().getName()+":"+i);
		//Thread�ྲ̬���� currentThread()�����ص�ǰ�̣߳�����getName()�õ��߳�����
	}

}
