package edu.xhu_15;

public class SetThread implements Runnable {
	private Student s;
	private int x=0;
	public SetThread(Student s) {
		this.s=s;
	}

	@Override
	public void run() {
		while(true){
			synchronized(s){
				if(s.flag){     //�����ݣ���ȴ�����
					try {
						s.wait(); //�÷�������˼���õ�ǰ�̴߳��ڵȴ�״̬��ͬʱ�ͷ�������������ʱ
								//�����￪ʼִ��
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(x%2==0){
					s.name="����";
					s.age=18;
				}
				else
				{
					s.name="����";   
					s.age=30;
				}
				x++;
				//���������ݾ��޸ı�ǣ�ͬʱ������s���ϵȴ����߳�
				s.flag=true;
				s.notify();  //�����ѵ��̣߳���������ִ�У�Ҫ����ȥ��ִ��Ȩ
			}
		}
				

	}

}
