package edu.xhu_10;

/*
 *1��ͬ��������������������
 *2����ͬ���������Ϊһ���������÷�����Ϊͬ������
 *	��ʽ���ڷ���ͷ�ϼ�synchronized�ؼ���
 *		private synchronized void sellTicket()
 *		��������ȥ��ͬ����
 *	��ô�������õ���ʲô�������أ�
 *		this����
 *  ��̬ͬ���������������ǣ���ǰ���ֽ��ļ�����class�ļ����󣬶��ڱ�����������ΪSellTicket.class
 *  ��̬������������ļ��ض����أ����غ�Ψһ���ڵĸ����class����
 *  ���class�����ڷ���ʱ��ʹ�õ���
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
