package edu.xhu_09;

public class SellTicket implements Runnable {
	private  int tickets=100;   //����100��Ʊ
	private Object obj=new Object();  //����ͬһ����
	@Override
	public void run() {
		while(true)
		{
			synchronized (new Object()){ //����д��ÿ���̶߳���һ���Լ��Ķ�������������ϣ���������߳�ʹ��һ����
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
//	public void run() {
//		while(true)
//		{
//			//����ԭ�������߳�1��2��3�������ߵ������˭�Ƚ�����˭�����ţ������߳��ٽ���ʱ��������
//			//�����ȥ��
//		
//			synchronized (obj){ 
//				if(tickets>0){
//					//������ʱ
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println(Thread.currentThread().getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
//				}
//			}
//		}
//	}
}
