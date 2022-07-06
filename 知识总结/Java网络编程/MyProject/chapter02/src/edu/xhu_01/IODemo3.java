package edu.xhu_01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/*
 * 如何实现数据换行?
 * 问：写入\n是否可以呢？在eclipse里打开是可以的，用windows的记事本就出问题了。
 * 原因：不同操作系统里对回车符的识别是不同的
 * 		Windows：	识别\r\n
 * 		linux:		识别\n
 * 		Mac:     	识别\r
 *高级记事本软件会自动识别各种回车符。Eclipse带的记事本软件可以识别各种换行符。
 *而Windows带的记事本要识别\r\n 
 * 如何追加数据？
 * FileOutputStream(String name,boolean append)
 */



public class IODemo3 {

	public static void main(String[] args) throws IOException {
		//1、创建字节输出流对象
		FileOutputStream os=new FileOutputStream("hello2.txt");
		//2、写数据
		for(int x=0;x<10;x++){
			os.write(("hello"+x).getBytes());
			os.write("\r\n".getBytes());  //linux下又不能用了
		}
		os.close();

	}

}
