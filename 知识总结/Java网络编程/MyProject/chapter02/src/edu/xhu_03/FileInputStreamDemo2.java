package edu.xhu_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * 用字节数组去读取流
 * int read(byte[] b)
 * 
 * 通过程序演示，讲解read过程
 */
public class FileInputStreamDemo2 {

	public static void main(String[] args) throws IOException {
		// 创建输入流对象
		FileInputStream fis=new FileInputStream("a.txt");
//		byte[] b=new byte[5];
//		int len;
		//第一次读
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		fis.close();
		//改进
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b,0,len));
//		fis.close();
//		byte[] b=new byte[100];
//		int len;
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.print(new String(b,0,len));  //演示一下不带len的结果。强调一定要带0,len
//		fis.close();
		//最终版代码,数组大小一般取1024或1024的整数倍，效率高
		byte[] b=new byte[1024];
		int len=0;
		while((len=fis.read(b))!=-1){
			System.out.print(new String(b,0,len));
		}
		fis.close();
	}

}
