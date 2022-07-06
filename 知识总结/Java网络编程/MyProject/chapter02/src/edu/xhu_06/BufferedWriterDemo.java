package edu.xhu_06;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * 字符缓冲输出流：
 * BufferedWriter,是OutputStreamWriter的装饰流，
 * 在OutputStreamWriter流上增加缓冲区，而OutputStreamWriter又是OutputStream（字节流）的转换流
 * （字节流+编码表）
 * 构造方法：
 * BufferedWriter(Writer out) 创建使用默认大小（1024）的输出缓冲区的缓冲字符输出流。
 * 
 * 普通写数据方法：
 * void write(int c) 写一个字符  
 * void write(char[] cbuf, int off, int len) 写入字符数组的一部分。 
 * void write(String s, int off, int len) 写一个字符串的一部分 
 * 新增写方法：
 * void newLine() 写一行行分隔符。 根据不同操作系统，写换行符。Linux '\n' MacOs '\r',Windows '\r\n'
 * 刷新缓冲方法：
 * void flush();
 */
public class BufferedWriterDemo {

	public static void main(String[] args) throws IOException {
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bw.txt")));
		bw.write(65);
		bw.newLine();
		
		bw.write("中国");
		bw.newLine();
		bw.flush();
		bw.close();
		//从文件读，往文件写时

	}

}
