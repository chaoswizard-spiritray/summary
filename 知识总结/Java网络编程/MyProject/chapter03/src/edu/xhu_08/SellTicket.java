package edu.xhu_08;
/* 
 * ��ʽ2��
 * Ϊ�˸���ģ����Ʊϵͳ����Ҫ��һ����ʱ��������Ҫ�������ݡ�
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //����100��Ʊ
	@Override
	public void run() {
		while(true)
		{
			if(tickets>0){
				//������ʱ
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
			}
		}
	}
}
