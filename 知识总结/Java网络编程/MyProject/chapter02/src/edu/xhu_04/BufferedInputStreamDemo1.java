package edu.xhu_04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * 既然通过定义数组的方式读取字节流比一次读一个字节流快，Java也提供了一种自带数组的读取字节流的方式
 * 称为缓冲区字节输入输出流（装饰设计模式）
 * BufferedInputStream类，
 * java.lang.Object -->
 * java.io.InputStream -->
 * java.io.FilterInputStream--> 
 * java.io.BufferedInputStream 
 * 构造方法： 
 * BufferedInputStream(InputStream in) 创建一个 BufferedInputStream并保存其参数，输入流 in ，供以后使用
 * BufferedInputStream(InputStream in, int size) 创建 BufferedInputStream具有指定缓冲区大小，并保存其参数，输入流 in。
 * 注意：它传入的参数是一个InputStream流，而不是文件，原因在于它只是在InputStream读取过程中提供了一个缓冲区，基本操作
 * 还是由基本的流对象来完成。它是对基本流对象的包装（装饰） 
 */
public class BufferedInputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("a.txt"));
		//读取数据，两种方式。与FileInputStream对象的读取方法一样，但使用了缓冲区
		//第一种：读一个字节（从缓冲区，内存）
//		int by=0;
//		while((by=bis.read())!=-1){
//			System.out.print((char)by);
//		}
//		bis.close();
		//第二种方式，读到字节数组
		byte[] bys=new byte[1024];
		int len=0;
		while((len=bis.read(bys))!=-1){
			System.out.print(new String(bys,0,len));
		}
		bis.close();
	}

}
