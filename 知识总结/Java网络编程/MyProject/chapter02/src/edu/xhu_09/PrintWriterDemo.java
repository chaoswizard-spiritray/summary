package edu.xhu_09;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 打印流：PrintStream(字节流） PrintWriter(字符流),只能作输出流。
 * 特点：
 * 1、只能操作输出流，不能操作输入流
 * 2、可以操作任意类型数据
 * 3、若启动了自动刷新，则不需要手动刷新
 * 
 * 构造方法：
 * PrintWriter(String fileName) 使用指定的文件名创建一个新的PrintWriter，而不需要自动执行行刷新。 
 * PrintWriter(OutputStream out, boolean autoFlush) 
 * 从现有的OutputStream创建一个新的PrintWriter，并可以设置是否自动刷新
 * 
 */
public class PrintWriterDemo {

	public static void main(String[] args) throws IOException {
		//1、创建打印字符流
		PrintWriter pw=new PrintWriter("pw.txt");
		//2.1、写数据。它的父类是writer,应该有5种写方法。
		pw.write("Hello");
		pw.write("Java");
		//2.2 除了write方法外，新增了print方法，可以输出任意类型的数据
		pw.println();
		pw.print(true);
		pw.print(123.5);
		pw.println("hello,world");
		//3、刷新
		pw.flush();
		pw.close();
		
	}

}
