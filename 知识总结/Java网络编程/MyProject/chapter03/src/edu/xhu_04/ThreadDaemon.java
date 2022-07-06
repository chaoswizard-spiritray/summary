package edu.xhu_04;

public class ThreadDaemon extends Thread {
	@Override
	public void run() {
		for(int i=0;i<50;i++){
			System.out.println(getName()+":"+i);
		}
	}
}
