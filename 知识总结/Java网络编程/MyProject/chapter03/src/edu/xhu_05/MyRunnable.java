package edu.xhu_05;
/*
 * �߳�ʵ�ַ���2��
 * 1��дһ���࣬ʵ��Runnable�ӿ�
 * 2��ʵ��run()����
 */

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		for(int i=0;i<50;i++)
			System.out.println(Thread.currentThread().getName()+":"+i);

	}

}
