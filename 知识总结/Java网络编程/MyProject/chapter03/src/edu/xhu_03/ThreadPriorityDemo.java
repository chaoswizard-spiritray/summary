package edu.xhu_03;
/*
 * �̵߳��ȣ�
 * 		��������ֻ��һ��CPU����ĳһʱ��ֻ��ִ��һ��ָ��߳�ֻ���ڵõ�CPUִ��Ȩʱ���ܱ�ִ�С�
 * 		Java����ζ��߳̽��е��ȵ��أ�
 * ���ֵ���ģ�ͣ�
 * 		1����ʱ����ģ�ͣ������߳�����ʹ��CPUִ��Ȩ��ƽ������ÿ���߳�ռ��CPU��ִ��ʱ��
 * 		2����ռʽ����ģ�ͣ������ȼ��ߵ��߳��ȵõ�CPUִ��Ȩ����ͬ���ȼ���������ȡ�
 * Javaʹ����ռʽ����ģ��
 * ������߳�����ִ���أ�
 * 		�����̵߳����ȼ� public final void setPriority(int);
 * 		��ȡ�߳����ȼ�    public final int getPriority();
 * Java�߳����ȼ�Ϊ1-10,ֵԽ�����ȼ�Խ�ߣ�Ĭ�����ȼ�Ϊ5
 * �߳����ȼ�������ʾ��ȡCPU��ʱ��Ƭ��΢��һ�㣬����Ҫ��ִ�д����϶�ʱ�������ֳ���
 * ������ʾ��һ��������ִ�У�����ִ��ͬ����������ԡ�
 * 
 * 
 */
public class ThreadPriorityDemo {

	public static void main(String[] args) {
		PriorityThread pt1=new PriorityThread();
		PriorityThread pt2=new PriorityThread();
		PriorityThread pt3=new PriorityThread();
		pt1.setName("�߳�1");
		pt1.setPriority(10);
		pt2.setName("�߳�2");
		pt3.setName("�߳�3");
		pt3.setPriority(1);
		pt1.start();
		pt2.start();
		pt3.start();
		

	}

}
