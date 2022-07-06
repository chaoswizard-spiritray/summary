package edu.xhu_18;

public class ColdDishThread extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);  //等待1秒完成
			System.out.println("凉菜做好了");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
