package edu.xhu_16;
/*
 * �޸����ݳ�ԱΪ˽�г�Ա
 * �ṩͬ������
*/
public class Student {
	private String name;
	private int age;
	private boolean flag; //���һ����ǣ�Ϊfalse��ʾû������,true��ʾ������
	public synchronized void set(String name,int age){
		//ͬ������ʹ��this������Ϊ������
		if(this.flag){  //������
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//û�����ݣ�����������
		this.name=name;
		this.age=age;
		//�޸�flag���
		this.flag=true;  //�Ѿ�׼��������
		//������this���ϵȴ����߳�
		this.notify();
	}
	public synchronized void get(){
		if(!this.flag){  //û������
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�����ݣ��������
		System.out.println(this.name+"-----"+this.age);
		//�޸ı��
		this.flag=false;
		//���ѵȴ��߳�
		this.notify();
	}
}
