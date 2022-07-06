package edu.xhu_20;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.xhu_05.MyRunnable;

/*
 * �̳߳صĺô����̳߳����ÿһ���̴߳�������󣬲����������������ٴλص��̳߳��г�Ϊ����״̬���ȴ���һ��������ʹ�á�
 * 
 * ���ʵ���̵߳Ĵ�����?
 * 	A:����һ���̳߳ض��󣬿���Ҫ���������̶߳���
 * 			public static ExecutorService newFixedThreadPool(int nThreads)
 * 	B:�����̳߳ص��߳̿���ִ�У�
 * 			����ִ��Runnable�������Callable���������߳�
 * 	C:�������·�������
 * 			Future<?> submit(Runnable task)
 *			<T> Future<T> submit(Callable<T> task)
 *	D:�����̳߳�(��ѡ)
 *			
 * 
 */
public class ExecutorServiceDemo {

	public static void main(String[] args) {
		// ����һ���̳߳ض��󣬿���Ҫ���������̶߳���
		// public static ExecutorService newFixedThreadPool(int nThreads)
		ExecutorService pool = Executors.newFixedThreadPool(2);

		// ����ִ��Runnable�������Callable���������߳�
		pool.submit(new MyRunnable()); 
		pool.submit(new MyRunnable());

		// �����̳߳�
		pool.shutdown();
	}

}
