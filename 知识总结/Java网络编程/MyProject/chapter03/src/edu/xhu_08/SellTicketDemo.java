package edu.xhu_08;

/*
 * ��ʵģ����Ʊ���̣���һ����ʱ
 * ������֣�
 * 1��һ��Ʊ�����۶�Ρ�  
 * 2�������ܳ���-1��-2������
 * ԭ��
 * һ��������Ʊ���������м�����ɣ�
 * 		a:ȡtickets�ĵ�ǰֵ
 * 		b:�ж�tickets��ǰֵ�Ƿ����0
 * 		c:�۳���ǰƱ
 * 		d:��ǰticketsֵ��1
 * 		ע�⣺�̵߳�ִ�й��������ǿռCPUִ��Ȩ��������4����ִ�й����У���ĳһ���ᱻ�����߳�����ִ��Ȩ��
 * 			��������һ��Ʊ�̡߳�
 * 		�ٶ�����1��Ʊʱtickets��ֵ=100���������ǰ���۵�100��Ʊ�󣬱�����ִ��Ȩ��d��δִ�С�
 * 		��ô����һ���߳��У������tickets��ֵ��û�б���1��Ҳ��100����Ʊ��Ҫ������һ�Σ���˻����
 * 		��100��Ʊ�ᱻ���۶�Ρ�
 * ����tickets=1�ǣ�ĳ�߳��ж�tickets����0����������ִ��Ȩ�����������߳�����ִ��Ȩ��
 * �ж�tickets��Ȼ����0,����������tickets�Ѿ���1���ٻص���һ���߳�ʱ��tickets��ֵ�Ѿ������仯��
 * �����ڸ�ִ��c����d����tickets��ֵ�����ʹ�ã�������1�������-1��-2�����������������̻߳�ûִ���꣬
 * �����ִ����������⡣
 * 
 * 
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell=new SellTicket();  
		Thread sell1=new Thread(sell);
		Thread sell2=new Thread(sell);
		Thread sell3=new Thread(sell);
		
		sell1.setName("����1");
		sell2.setName("����2");
		sell3.setName("����3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
