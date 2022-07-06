package edu.xhu_04;

import java.util.Date;

public class ThreadStop extends Thread{
	@Override
	public void run() {
		System.out.println("开始："+new Date());
		//休息10秒
		try {
			sleep(10000);
		} catch (InterruptedException e) {
			System.out.println("我的线程被中断了");
		}
		System.out.println("结束："+new Date());
	}
}
