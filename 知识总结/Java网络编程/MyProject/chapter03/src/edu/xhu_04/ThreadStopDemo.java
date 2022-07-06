package edu.xhu_04;
/*
 * �����̱߳�����Ҫִ��10�룬̫��������2�룬����������ֹ������ȴ���
 * ʹ�ã�
 * stop()��������ʱ�ˣ�����ֹͣһ���̡߳�������ʹ�á�����ʹ��interrupt()����
 * public void interrupt()���ж�����̡߳�
 * 1�� ������߳������ĵ���wait() �� wait(long) ����wait(long, int)�ķ���Object�࣬������join() ��
 *  join(long) �� join(long, int) �� sleep(long) ����sleep(long, int) �������ķ�����
 *  ��ô�����ж�״̬������������ҽ��յ�һ��InterruptedException ��
 * 2�� ������߳���ֹ��I / O����InterruptibleChannel,���ŵ������رգ����̵߳��ж�״̬�������ã�
 *  ���ҽ��յ�һ��ClosedByInterruptException ��
 * 3�� ������߳���Selector�б����������̵߳��ж�״̬�������ã�����������ѡ������������أ����ܾ��з���ֵ
 *  ���������ѡ������wakeup����һ����
 * 4�� ��������������������������̵߳��ж�״̬�������á� 
 *  
 */
public class ThreadStopDemo {

	public static void main(String[] args)  {
		ThreadStop ts=new ThreadStop();
		ts.start();
		try {
			Thread.currentThread().sleep(2000);
			//ts.stop();  //��ʱ�˵ķ����������ã������ᳫ
			ts.interrupt();    //�ᷢ��һ��Interrupt�쳣���󣬱��жϵ��̻߳��յ����쳣��
							//���߳��п��Բ�������쳣�������Ĵ���
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
