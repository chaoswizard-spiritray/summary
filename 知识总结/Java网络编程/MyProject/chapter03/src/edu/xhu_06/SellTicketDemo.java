package edu.xhu_06;

/*
 * ����ĳ��ӰԺ������һ����Ӱ����100��Ʊ����3����Ʊ���ڣ���д����ģ����Ʊ����
 * ������
 * A: ��д��Ʊ�̣߳��÷�ʽ1ʵ��
 * B: ����3���̶߳���
 * C: �����̶߳���
 * ���⣺����Ʊ�۳�
 * ԭ���߳�ִ��ʱ����CPU����ִ�е�һ���ֵ�ʱ��ִ��Ȩ�������ˣ�����tickets��û�б���ȥ
 * ע��÷�ʽ������3��������Դ�ķ�
 */
public class SellTicketDemo {

	public static void main(String[] args) {
		SellTicket sell1=new SellTicket();  //
		SellTicket sell2=new SellTicket();
		SellTicket sell3=new SellTicket();
		
		sell1.setName("����1");
		sell2.setName("����2");
		sell3.setName("����3");
		sell1.start();
		sell2.start();
		sell3.start();

	}

}
