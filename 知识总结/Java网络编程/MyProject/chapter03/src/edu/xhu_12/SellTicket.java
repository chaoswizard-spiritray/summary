package edu.xhu_12;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
 *��ReentrantLock
 */
public class SellTicket implements Runnable {
	private  int tickets=100;   //����100��Ʊ
	private Lock lock=new ReentrantLock();  //���������� 
//	@Override
//	public void run() {
//		while(true)
//		{
//			lock.lock();  //��ȡ��
//			if(tickets>0){
//				//������ʱ
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
//			}
//			lock.unlock();//�ͷ���   
//		}
//	}
	//�淶�÷�
	@Override
	public void run() {
		while(true)
		{
			try{
				lock.lock();  //��ȡ��
				if(tickets>0){
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"���ڳ��۵�"+tickets--+"��Ʊ");
				}
			}finally{
				lock.unlock();//�ͷ���   
			}
		}
	}
}
