package edu.xhu_17;
/*
 * �����ڳ�����ֱ�ӿ�һ�߳���ִ��ĳһ�����񣬸��򵥵ķ�ʽ����ʹ�������ڲ���
 * �����ڲ�����̵߳�ʹ�÷�ʽ��
 * 1�������ڲ���ĸ�ʽ��		
 * 		new ���ӿ���(){
 * 			��д����;
 * 		};
 * 		���ʣ�����һ����������
 * 2�������ڲ�����߳�
 * 		new Thread(){
 * 			��дrun����;
 * 		}.start()
 * 
 * 		new Thread(new Runnable(){
 * 			ʵ��run����;
 * 		}){
 * 		}.start();
 * 
 */
public class ThreadDemo {

	public static void main(String[] args) {
		new Thread("�߳�1"){
			public void run() {
				for(int i=0;i<50;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			};
		}.start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<50;i++){
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		},"�߳�2"){}.start();

	}

}
