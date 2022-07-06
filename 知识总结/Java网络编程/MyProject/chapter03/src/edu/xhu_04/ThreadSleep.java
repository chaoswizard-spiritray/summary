package edu.xhu_04;

import java.util.Date;

public class ThreadSleep extends Thread {
	@Override
	public void run() {
		for(int i=0;i<10;i++)
		{
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println(getName()+":"+new Date());
		}
	}
}
