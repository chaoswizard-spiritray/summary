package edu.xhu_02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 字节输入流的操作步骤：
 * A:创建字节输入流对象
 * B:读数据
 * C:释放资源
 * 
 * 读数据的方式：
 * A: int read()   一次读一个字节
 * B: int read(byte[] b) 一次读若干字节
 * 
 */
public class FileInputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//1、创建字节输入流对象
		FileInputStream fis=new FileInputStream("hello.txt");
		//2、读一个字节
		//第一次读取
//		int a=fis.read();
//		System.out.println(a);
//		System.out.println((char)a);
//		//第二次读取
//		a=fis.read();
//		System.out.println(a);
//		System.out.println((char)a);
		/*
		 * 读取代码重复，循环读取，读取多少次？
		 * 读取值为-1时，表示读到文件结尾
		 */
		int by=0;
		while((by=fis.read())!=-1){
			System.out.print((char)by);   //读汉字有问题，因为它把汉字编码分成一个一个字节显示，以后可以用字符流读
		}
		
		//3、释放资源
		fis.close();
		
	}

}
