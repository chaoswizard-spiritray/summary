package edu.xhu_07;

/*
 * ����ĳ��ӰԺ������һ����Ӱ����100��Ʊ����3����Ʊ���ڣ���д����ģ����Ʊ����
 * ������
 * A: ��д��Ʊ�̣߳��÷�ʽ2ʵ��
 * B: ����1���̶߳���
 * C: ����3���̶߳���
 * ���⣺����Ʊ�۳�
 * ԭ���߳�ִ��ʱ����CPU����ִ�е�һ���ֵ�ʱ��ִ��Ȩ�������ˣ�����tickets��û�б���ȥ
 * ��Դ�����ˣ����빲���ˡ�
 * 
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell=new SellTicket();  //
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
