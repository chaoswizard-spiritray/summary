package edu.xhu_01;
/*
 * Java ���̵߳�һ�ַ������̳�Thread�࣬��дrun()����
 */
public class MyThread1 extends Thread {
	//������������������Ա�����ݳ�Ա��
	@Override
	//��Ҫ���߳�ִ�еĴ��룬��Ҫд��run�����һ����˵��ִ��ĳ���ʱ����ʱ����Ҫ�߳�����ɡ����ߴ���������
	public void run() {
		for(int x=0;x<100;x++){
			System.out.println(getName()+":"+x);
		}
	}
}
