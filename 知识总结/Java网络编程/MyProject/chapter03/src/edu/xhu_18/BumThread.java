package edu.xhu_18;

public class BumThread extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(3000); //等待3秒，完成
			System.out.println("包子做好了");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
