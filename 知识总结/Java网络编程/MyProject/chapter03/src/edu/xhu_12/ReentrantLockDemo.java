package edu.xhu_12;
/*
 * JDK5���ṩһ��Lock�ӿڼ�����ʵ����ReentranLock�࣬��������������lock() ��ȡ��,unlock()�ͷ���
 * ����ʹ�ø�����
 * 
 */
public class ReentrantLockDemo {

	public static void main(String[] args) {
		SellTicket st=new SellTicket();
		Thread sell1=new Thread(st,"����1");
		Thread sell2=new Thread(st,"����2");
		Thread sell3=new Thread(st,"����3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
