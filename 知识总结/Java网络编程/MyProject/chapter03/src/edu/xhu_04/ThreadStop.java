package edu.xhu_04;

import java.util.Date;

public class ThreadStop extends Thread{
	@Override
	public void run() {
		System.out.println("��ʼ��"+new Date());
		//��Ϣ10��
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("�ҵ��̱߳��ж���");
		}
		System.out.println("������"+new Date());
	}
}
