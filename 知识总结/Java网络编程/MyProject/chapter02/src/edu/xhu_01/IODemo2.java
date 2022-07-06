package edu.xhu_01;
/*
 * 字节输出流操作三步：
 * A:创建字节输出流对象
 * B:write数据
 * C:close对象
 * 
 * write的三种方法
 * void write(byte[] b) 
 * void write(byte[] b, int off, int len) 
 * void write(int b)   
 * 
 * 
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IODemo2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//1、创建字节输出流对象
		OutputStream os=new FileOutputStream("hello1.txt");  //多态
		//2、写数据
		os.write(97);  //请问文件里是97吗？
		byte[] b={98,99,100,101};
		os.write(b);
		os.write(b, 2, 2);
		//3、释放资源
		os.close();
		
	}

}
