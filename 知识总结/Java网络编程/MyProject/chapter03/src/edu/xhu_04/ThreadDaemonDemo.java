package edu.xhu_04;
/*
 *������Ϸ���������ˣ�����ı���Ҳû�д��ڵ������ˣ�����Ҳ�������������ȥ�ˡ�
 *		�������ڵ��������ػ�����
 *	�����̣߳����̣߳��������ˣ�������ŷ��̣߳����̣߳�������� 
 *������
 *	�������ŷ��߳�����Ϊ�ػ��̼߳���
 *	�����������߳�֮ǰ���á�
 *������
 *	public void setDaemon(boolean on);
 * 
 *����Ҫע����ǣ�
 *	���߳̽�����ʱ�������߳̿��ܻ��������У��������һ������ 
 */
public class ThreadDaemonDemo {

	public static void main(String[] args) {
		ThreadDaemon td1=new ThreadDaemon();
		ThreadDaemon td2=new ThreadDaemon();
		
		td1.setName("����");
		td2.setName("�ŷ�");
		
		td1.setDaemon(true);
		td2.setDaemon(true);
		
		td1.start();
		td2.start();
		Thread.currentThread().setName("����");
		for(int i=0;i<30;i++){
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}

}
