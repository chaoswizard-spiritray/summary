package edu.xhu_18;
/*
 * 线程实现的第三种方式：实现Callable接口
 * 之前的多线程的两种种实现中，不管是继承thread类还是实现runnable接口，都无法保证获取到线程的执行结果。
 * 通过实现Callable接口，并用Future可以来接收多线程的执行结果。

Future接口:
	表示异步计算的结果，通过Future接口提供的方法，可以很方便的查询异步计算任务是否执行完成，
	获取异步计算的结果，取消未执行的异步任务，或者中断异步任务的执行


Future接口主要包括5个方法
get（）方法可以当任务结束后返回一个结果，如果调用时，工作还没有结束，则会阻塞线程，直到任务执行完毕
get（long timeout,TimeUnit unit）做多等待timeout的时间就会返回结果
cancel（boolean mayInterruptIfRunning）方法可以用来停止一个任务，
如果任务可以停止（通过mayInterruptIfRunning来进行判断），
则可以返回true,如果任务已经完成或者已经停止，或者这个任务无法停止，则会返回false.
isDone（）方法判断当前方法是否完成
isCancel（）方法判断当前方法是否取消

Future表示一个可能还没有完成的异步任务的结果，针对这个结果可以添加Callback
以便在任务执行成功或失败后作出相应的操作。

 举个例子：比如去吃早点时，点了包子和凉菜，包子需要等3分钟，凉菜只需1分钟，如果是串行的一个执行，
 在吃上早点的时候需要等待4分钟，但是因为你在等包子的时候，可以同时准备凉菜，所以在准备凉菜的过程中，
 可以同时准备包子，这样只需要等待3分钟。那Future这种模式就是后面这种执行模式。
 
 下面演示这两种模式
 演示第一种串行执行方式：
 需求场景：等早餐过程中，包子需要3秒，凉菜需要1秒，普通的多线程需要四秒才能完成。先等凉菜，再等包子，
 因为等凉菜时，普通多线程启动start()方法， 执行run()中具体方法时，没有返回结果，所以如果要等有返回结果，
 必须是要1秒结束后才知道结果。
 分析：
 A:先实现两个线程类 BumThread和ColdDishThread，分别等待3秒和1秒
 B:执行BumThread线程，完成后再执行ColdDishThread线程，要等待线程完成，用join方法控制
 C:计算时间
 */

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		
		// 等凉菜 -- 必须要等待返回的结果，所以要调用join方法
		Thread t1 = new ColdDishThread();
		t1.start();
		t1.join();
		
		// 等包子 -- 必须要等待返回的结果，所以要调用join方法
		Thread t2 = new BumThread();
		t2.start();
		t2.join();
		
		long end = System.currentTimeMillis();
		System.out.println("准备完毕时间："+(end-start));

	}

}
