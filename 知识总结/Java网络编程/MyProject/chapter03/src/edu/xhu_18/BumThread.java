package edu.xhu_18;

public class BumThread extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(3000); //�ȴ�3�룬���
			System.out.println("����������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
