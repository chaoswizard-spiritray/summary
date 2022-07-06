package edu.xhu_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * IO流：完成设备之间数据传输
 * IO流分类：
 * 按流向分为输入和输出流
 * 按数据类型分为字节流和字符流。字节流是最基本的流，但不便于处理字符，因此又提供一种专门处理字符的流。
 * 用记事本打开看不懂的，用字节流，看得懂的既可以用字节流，也可以用字符流。
 * 什么都不知道，用字节流。
 * 因此总共需要掌握4个流，字节输入、输出流和字符输入、输出流
 * 
 * 字节流的超类：java.io包
 * 		两个抽象类：InputStream （输入流）OutputStream（输出流）
 * 字符流的超类：
 * 		Reader和Writer类
 * 
 * 需求：向文件写入Hello Java
 * 分析：
 * 		A:这个操作最好用字符流来实现，但字符流是在字节流后出现的，我们先用字节流来实现。
 * 		B:我们要向文件中写一句话，因此我们要使用字节输出流。
 * 通过上面分析，我们要使用OutputStream，但查看API，我们发现OutputStream类是一个抽象类，因此
 * 我们需要找到它的实现类（子类）。由于我们是要往文件里写内容，可以发现OutputStream类下
 * 有一个FileOutputStream类，该类是一个具体类。
 * 查看FileOutputStream类，找到该类的构造方法：
 *      FileOutputStream(File file)
 * 		FileOutputStream(String name)
 * 		
 * 总结：字节输出流操作步骤
 * 	    A:创建字节流输出对象
 * 		B:写数据
 * 		C:关闭输出流
 */
public class IODemo {
	public static void main(String[] args) throws IOException {
		//第一步：创建字节流对象
		//用第一种构造方法创建文件字节输出流
		//File file=new File("hello.txt");
		//FileOutputStream fos=new FileOutputStream(file);
		//用第二种构造方法
		FileOutputStream fos=new FileOutputStream("hello.txt");  
		//查看该构造方法的源码可知该方法是调用了File类生成一个对象，等价于第一种构造方法
		/*
		 * 创建字节流对象做了几件事？
		 * A: 调用操作系统的文件创建功能创建文件
		 * B: 创建fos对象
		 * C: 将fos对象与文件关联（指向该文件)
		 */
		//第二步：写数据
		fos.write("Hello java".getBytes()); 
		fos.write("我爱Java".getBytes());
		//注意抛出的异常变成IOException了，原来的FileNotFoundException没有了，
		//原因是IOException是FileNotFoundException的父类，子类异常对象自然可以用父类接收
		//第三步：释放资源
		//关闭输出流，释放与该输出流相关的系统资源
		fos.close();
		/*
		 * A:让流对象变成垃圾，虚拟机回收该对象占用的资源
		 * B:通知系统释放与该文件相关的资源
		 */
		
	}

}
