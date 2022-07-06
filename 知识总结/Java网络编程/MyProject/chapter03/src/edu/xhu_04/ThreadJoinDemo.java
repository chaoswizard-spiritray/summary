package edu.xhu_04;
/*
 * join():等待某线程终止，一定放在线程启动后。或理解为该线程加入，其他线程需要等待该线程终止后才能抢时间
 */
public class ThreadJoinDemo {

	public static void main(String[] args) {
		ThreadJoin tj1=new ThreadJoin();
		ThreadJoin tj2=new ThreadJoin();
		ThreadJoin tj3=new ThreadJoin();
		
		tj1.setName("李渊");     //李渊是父亲，应先出现，执行完后，才有兄弟
		tj2.setName("李世民");
		tj3.setName("李元霸");
		
		tj1.start();
		try {
			tj1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tj2.start();
		tj3.start();
		

	}

}
