package edu.xhu_18;

public class ColdDishThread extends Thread {

	@Override
	public void run() {
		try {
			Thread.sleep(1000);  //�ȴ�1�����
			System.out.println("����������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
