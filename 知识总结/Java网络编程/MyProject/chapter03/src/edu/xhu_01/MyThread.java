package edu.xhu_01;
/*
 * ����ΪʲôҪ��дrun()�����أ�
 * һ������ܻ��кܶ෽�������������еķ�������Ҫ�߳�ȥִ�У�ֻ��run()������Ĵ���ű��߳�ִ�С�
 */
public class MyThread extends Thread {
	@Override
	public void run() {
		//һ���Ǻ�ʱ�Ĵ������Ҫ���߳���ִ�У���ѭ��ģ��һ��
		for(int i=0;i<100;i++)
		{
			System.out.println(i);
		}
	}
}
