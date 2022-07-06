package edu.xhu_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import edu.xhu_02.FileInputStreamDemo1;

/*
 * 需求：复制一个文本文件
 * 分析：从一个文件读取内容，写入另一个文件
 * 数据源：读取数据-------FileInputStream
 * 目的地：写入数据-------FileOutputStream
 * 
 * 文件中若有汉字，会出问题吗？
 */
public class FileCopyDemo {

	public static void main(String[] args) throws IOException {
		//创建数据源对象
		FileInputStream fis=new FileInputStream("hello2.txt");
		//创建目的地源对象
		FileOutputStream fos=new FileOutputStream("new.txt");
		
		//读数据写数据
		int by=0;
		while((by=fis.read())!=-1){
			fos.write(by);
		}
		fis.close();
		fos.close();

	}

}
