package edu.xhu_10;

public class SellTicket implements Runnable {
	private  int tickets=100;   //����100��Ʊ
	private Object obj=new Object();  //����ͬһ����
	private int x=0;
	@Override
	public void run() {
		
		while(true)
		{
			if(x%2==0){  //Ϊ�˽���ͬ�������������⣬���Ǽ���ͬ������飬Ҳ��ͬ��������ʾ
						//�������obj�������г���������⣬����ԭ��϶����������⣬
						//�����Ǵ���������ͬ��������������ͬһ��������ôͬ����������
				        //��ʲô�������أ����ǰ�obj���󻻳�this�����������ˡ�
						//˵��ͬ�������õ���������this����
						//��ͬ��������֤��̬ͬ�������õĸ�����ֽ��ļ�����SellTicket.class
				synchronized(obj){ 
					if(tickets>0){
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
			else
			{
				sellTicket();   //ͬ������
			}
			x++;
		}
	}


	private synchronized void sellTicket() {
		
			if(tickets>0){
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
