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
				if(!s.flag){   //没有数据，则等待，有数据就消费
					try {
						s.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(s.name+"----"+s.age);
				//消费了数据，修改标记，同时唤醒等待的线程
				s.flag=false;
				s.notify();
			}
		}
	}
}
