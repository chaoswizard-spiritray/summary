package edu.xhu_09;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 启动PrintWriter的自动刷新功能：
 * PrintWriter(OutputStream out, boolean autoFlush) 
 * 自动刷新：
 * println():
 * 相当于：
 * write();
 * newLine();
 * flush();
 */
public class PrintWriterDemo1 {

	public static void main(String[] args) throws IOException {
		//1、创建对象
		PrintWriter pw=new PrintWriter(new FileWriter("pw.txt"),true);
		//2、输出数据
		//pw.print("hello");  //不自动刷新
		pw.println("hello,world");  //自动刷新，三个方法可以自动刷新，println,printf,format
		pw.printf("%6.1f",123.564);

	}

}
