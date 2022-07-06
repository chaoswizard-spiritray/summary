package edu.xhu_05;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * 字符输出流写数据的5种方法：
 * void write(int c) ：							写一个字符  
 * void write(char[] cbuf) ：					写入一个字符数组。  
 * void write(char[] cbuf, int off, int len) ：	写入字符数组的一部分。  
 * void write(String str) ：						写一个字符串
 * void write(String str, int off, int len) ：	写一个字符串的一部分。    
 *
 *  flush()和close()的区别：
 *  flush():将缓冲区的内容写入流中，流还可以继续使用
 *  close():将缓冲区的内容写入流中，关闭流。
 *  
 *  字符输入流的读入方法两种：
 *  int read():   一次读入1个字符，-1表示没读到数据
 *  int read(char[] cbuf):   一次读入多个字符，取决于字符数组的大小，一般为1024，返回值表示实际读到的字符数。-1表示未读到数据
 *  与字节输入流的读入方法类似，注意，一个是字节(byte)数组，一个是字符(char)数组
 */
public class OutputStreamWriterDemo2 {

	public static void main(String[] args) throws IOException {
		//创建字符流输出流对象
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw2.txt"));   //默认GBK编码
		
		
		//1、写一个字符
		osw.write('a');
		osw.write(97);
		osw.write('\n');
		osw.flush();
		//2、写一个字符数组
		char[] chs={'a','中','国','b'};
		System.out.println(chs);
		osw.write(chs);
		osw.write("\n");
		//3、写一部分
		osw.write(chs,1,2);
		osw.write("\n");
		//4、写一个串
		osw.write("Hello java\n");
		//5、写一个串的一部分
		osw.close();  //关闭功能自己也要先刷新缓冲区，然后再释放资源

	}

}
