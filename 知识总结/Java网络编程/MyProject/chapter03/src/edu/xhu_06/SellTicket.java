package edu.xhu_06;

public class SellTicket extends Thread {
	private static int tickets=100;   //����100��Ʊ
	@Override
	public void run() {
		while(true)
		{
			if(tickets>0){
				System.out.println(getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
			}
		}
	}
}
