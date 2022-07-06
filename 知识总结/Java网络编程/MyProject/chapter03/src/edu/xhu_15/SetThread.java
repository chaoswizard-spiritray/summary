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
				if(s.flag){     //有数据，则等待消费
					try {
						s.wait(); //该方法的意思是让当前线程处于等待状态，同时释放锁，将来唤醒时
								//从这里开始执行
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(x%2==0){
					s.name="张三";
					s.age=18;
				}
				else
				{
					s.name="李四";   
					s.age=30;
				}
				x++;
				//生产了数据就修改标记，同时唤醒在s锁上等待的线程
				s.flag=true;
				s.notify();  //被唤醒的线程，不会立即执行，要重新去抢执行权
			}
		}
				

	}

}
