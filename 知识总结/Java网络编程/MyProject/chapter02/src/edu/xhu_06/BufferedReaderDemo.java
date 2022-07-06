package edu.xhu_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 高效的字符输入输出流（转换流）：缓冲字符输入输出流
 * BufferedReader(Reader in);        从字符输入流读取文本，缓冲字符，以提供字符，数组和行的高效读取。
 * BufferedWriter(Writer out);		 将文本写入字符输出流，缓冲字符，以提供单个字符，数组和字符串的高效写入。
 * InputStreamReader---FileInputStream---->FileReader----->Reader
 * OutputStreamWriter----FileOutputStream--->FileWriter---->Writer
 * 
 * BufferedReader常用的read方法：
 * int read() 读一个字符 -1表示未读到数据
 * int read(char[] cbuf);读若干字符到字符数组，返回的int表示实际读到的字符数，-1表示未读到数据
 * 
 * 特殊方法 
 * String readLine() 读一行文字。 但不包含换行符。返回null表示未读到
 * 
 *  
 * 总结：
 * 	复制文本文件 8种方式 ：  普通字节流（一次读一个字节，一次读若干个字节），缓冲字节流（一次读一个字节，一次读若干个字节）
 * 						  普通字符流（（一次读一个字节，一次读若干个字节），缓冲字符流（一次读一个字节，一次读若干个字节）
 *  复制二进制文件4种方式：普通字节流（一次读一个字节，一次读若干个字节），缓冲字节流（一次读一个字节，一次读若干个字节）
 */
public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException  {
		//创建缓冲字符流输入对象
		//BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
		
		BufferedReader br=new BufferedReader(new FileReader("a.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("b.txt"));
		//两种方式，第一种：读一个字符，第二种：读取字符数组
		char[] cbuf=new char[1024];
		int len=0;
		while((len=br.read(cbuf))!=-1){
			bw.write(cbuf, 0, len);
		}
		br.close();
		bw.close();
		
	}

}
