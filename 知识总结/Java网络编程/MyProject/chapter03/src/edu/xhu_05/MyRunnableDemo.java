package edu.xhu_05;
/*
 * �߳�ʵ�ַ���2��
 * ���裺
 * A:дһ���࣬ʵ��Runnable�ӿ�
 * B:ʵ��run()����
 * C:�����������
 * D:����Thread���󣬽�C�������Ķ�����ΪThread�Ĺ������
 * E:ִ��Thread�����start()�����������߳�
 */
public class MyRunnableDemo {

	public static void main(String[] args) {
		//C:
		MyRunnable mr=new MyRunnable();
		//D:
//		Thread td1=new Thread(mr);
//		Thread td2=new Thread(mr);
//		//E:
//		td1.setName("�߳�1");
//		td2.setName("�߳�2");
		//Thread(Runnable target, String name) 	����һ���µ� Thread����
		Thread td1=new Thread(mr,"�߳�1");
		Thread td2=new Thread(mr,"�߳�2");
		td1.start();
		td2.start();

	}

}
