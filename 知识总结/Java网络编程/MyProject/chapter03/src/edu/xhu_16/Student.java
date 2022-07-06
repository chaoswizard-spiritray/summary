package edu.xhu_16;
/*
 * 修改数据成员为私有成员
 * 提供同步方法
*/
public class Student {
	private String name;
	private int age;
	private boolean flag; //添加一个标记，为false表示没有数据,true表示有数据
	public synchronized void set(String name,int age){
		//同步方法使用this对象作为锁对象
		if(this.flag){  //有数据
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//没有数据，则设置数据
		this.name=name;
		this.age=age;
		//修改flag标记
		this.flag=true;  //已经准备好数据
		//唤醒在this锁上等待的线程
		this.notify();
	}
	public synchronized void get(){
		if(!this.flag){  //没有数据
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//有数据，输出数据
		System.out.println(this.name+"-----"+this.age);
		//修改标记
		this.flag=false;
		//唤醒等待线程
		this.notify();
	}
}
