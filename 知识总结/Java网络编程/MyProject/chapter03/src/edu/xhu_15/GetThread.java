package edu.xhu_15;

public class GetThread implements Runnable {
	private Student s;
	public GetThread(Student s) {
		this.s=s;
	}

	@Override
	public void run() {
		while(true){
			synchronized(s){
				if(!s.flag){   //û�����ݣ���ȴ��������ݾ�����
					try {
						s.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(s.name+"----"+s.age);
				//���������ݣ��޸ı�ǣ�ͬʱ���ѵȴ����߳�
				s.flag=false;
				s.notify();
			}
		}
	}
}
