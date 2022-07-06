package edu.xhu_04;
/*
 * yield():暂停当前执行的线程，并执行其他线程
 * 让多个线程执行比较和谐，但不能保证一人一次的执行顺序,若要保证一人一次，则需使用唤醒机制
 */
public class ThreadYieldDemo {

	public static void main(String[] args) {
		ThreadYield ty1=new ThreadYield();
		ThreadYield ty2=new ThreadYield();
		ty1.setName("陈晓军");
		ty2.setName("陈大军");
		ty1.start();
		ty2.start();
		

	}

}
