package edu.xhu_07;
/* 
 * ��ʽ2��
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //����100��Ʊ
	@Override
	public void run() {
		while(true)
		{
			if(tickets>0){
				System.out.println(Thread.currentThread().getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
			}
		}
	}
}
