package edu.xhu_04;
/*
 * 需求：线程本身需要执行10秒，太长，给你2秒，不结束就终止阻塞或等待。
 * 使用：
 * stop()方法，过时了，暴力停止一个线程。不建议使用。建议使用interrupt()方法
 * public void interrupt()：中断这个线程。
 * 1、 如果该线程阻塞的调用wait() ， wait(long) ，或wait(long, int)的方法Object类，或者在join() ，
 *  join(long) ， join(long, int) ， sleep(long) ，或sleep(long, int) ，这个类的方法，
 *  那么它的中断状态将被清除，并且将收到一个InterruptedException 。
 * 2、 如果该线程阻止在I / O操作InterruptibleChannel,则信道将被关闭，该线程的中断状态将被设置，
 *  并且将收到一个ClosedByInterruptException 。
 * 3、 如果该线程在Selector中被阻塞，则线程的中断状态将被设置，并且它将从选择操作立即返回，可能具有非零值
 *  就像调用了选择器的wakeup方法一样。
 * 4、 如果上面的条件都不成立，则该线程的中断状态将被设置。 
 *  
 */
public class ThreadStopDemo {

	public static void main(String[] args)  {
		ThreadStop ts=new ThreadStop();
		ts.start();
		try {
			Thread.currentThread().sleep(2000);
			//ts.stop();  //过时了的方法，可以用，但不提倡
			ts.interrupt();    //会发出一个Interrupt异常对象，被中断的线程会收到该异常，
							//在线程中可以捕获这个异常，做灵活的处理。
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
