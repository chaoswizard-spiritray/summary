package edu.xhu_05;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 字节流处理中文不是很方便。每次读一个字节一般会出问题，若用字节数组读，可能不出问题，但可能存在隐患。
 * 字节流处理中文可能出现的问题：
 *    用字节流读取一个含有中文的文件说明。比如文件中的内容是“hello中国”，用字节流读文件，并显示其内容。
 * Java提供了另一个类：Reader和Writer，用于读取字符，称为字符流，它是一个转换流，由字节流+编码表构成
 * 编码表：
 * ASCII    '0'----48   'A'----64   'a'----96     字符对应一个数值 0-127
 * ISO-8859-1: latin码表  一个字节 8位 0-255 扩展ASCII表
 * GB2312表：汉字编码表
 * GBK:汉字升级编码表
 * GB18030：GBK取代版
 * BIG5:繁体字编码表
 * Unicode编码表：国际编码表，每个字符用两个字节，java语言采用Unicode编码
 * UTF-8编码：变长编码，最短1个字节，最长三个字节，很好融合了ASCII,又兼顾其他语言字符
 * 
 * 
 * InputStreamReader(InputStream is):采用默认编码读取字符
 * InputStreamReader(InputStream is,String charsetName):采用指定编码读取字符
 * Reader的方法：
 * 		int read() 读一个字符 
 * 		int read(char[] cbuf) 将字符读入数组  
 */
public class InputStreamReaderDemo {

	public static void main(String[] args) throws IOException {
		//创建字符读入流对象
		InputStreamReader isr =new InputStreamReader(new FileInputStream("osw1.txt"),"UTF-8");
		//读取字符数据，可能是1个字节，也可能是多字节,但最多不会超过3字节，而int是4字节，
		//足够存储一个字符的编码，不能用char去读，为什么？
		int ch=0;
		ch=isr.read();
		System.out.print((char)ch);
		
		
		//释放资源
		isr.close();
	}

}
