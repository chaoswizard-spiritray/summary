package edu.xhu_05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * 由于字节流在处理非英文字符时不是特别方便，特别是一次读一个字节在控制台输出时，用字节数组读取时，
 * 大部分时候可能正确，但也有可能存在问题隐患。
 * 因此，Java提供了一个转换流：字符流=字节流+编码表（一字节编码，二字节编码，三字节编码）
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
 * 
 * OutputStreamWriter(OutputStream out) 创建一个使用默认字符编码的OutputStreamWriter。  
 * OutputStreamWriter(OutputStream out, String charsetName) 创建一个使用命名字符集的OutputStreamWriter。  
 */
public class OutputStreamWriterDemo1 {

	public static void main(String[] args) throws IOException {
		//创建字符流输出流对象
		//OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw1.txt"));   //默认GBK编码
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw1.txt"),"UTF-8");   //使用UTF-8编码
		
		//写数据，有5种方法
		osw.write("中国");
		/*
		 * 字符流的写出是多字节，若需要把输出的流立即写入文件，需要刷新一下系统（操作系统）的缓冲区
		 */
		//osw.flush();
		osw.close();  //关闭功能自己也要先刷新缓冲区，然后再释放资源

	}

}
