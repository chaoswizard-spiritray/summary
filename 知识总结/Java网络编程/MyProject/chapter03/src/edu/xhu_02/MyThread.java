package edu.xhu_02;
/*
 * ��λ�ȡ�̵߳������أ�
 */
public class MyThread extends Thread {
	public MyThread(){
	}
	public MyThread(String name){
		super(name);
	}
	@Override
	public void run() {
		for(int i=0;i<200;i++)
			System.out.println(getName()+":"+i);
	}
}
