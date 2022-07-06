package edu.xhu_06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 字符缓冲流的特殊操作：
 * BufferedWriter: newLine()  写出行分隔符，根据系统决定写入\r\n
 * BufferedReader: readLine() 一次读入一行，但不包括换行符,读到文件尾为null。注意不是-1
 * 
 * 
 * 
 * 读文本文件代码：
 * String line=null;
 * while((line=br.readLine())!=null){
 *     System.out.println(line);
 * }
 * 
 * 
 * 
 */

public class BufferedDemo {

	public static void main(String[] args) throws IOException {
		//创建输出缓冲字符流对象
		BufferedWriter br=new BufferedWriter(new FileWriter("a.txt"));
		//写入数据
		for(int x=0;x<10;x++){
			br.write("hello"+x);
			br.newLine();
			br.flush();
		}
		br.close();

	}

}
