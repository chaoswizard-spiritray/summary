package edu.xhu_09;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * ��ӡ����PrintStream(�ֽ����� PrintWriter(�ַ���),ֻ�����������
 * �ص㣺
 * 1��ֻ�ܲ�������������ܲ���������
 * 2�����Բ���������������
 * 3�����������Զ�ˢ�£�����Ҫ�ֶ�ˢ��
 * 
 * ���췽����
 * PrintWriter(String fileName) ʹ��ָ�����ļ�������һ���µ�PrintWriter��������Ҫ�Զ�ִ����ˢ�¡� 
 * PrintWriter(OutputStream out, boolean autoFlush) 
 * �����е�OutputStream����һ���µ�PrintWriter�������������Ƿ��Զ�ˢ��
 * 
 */
public class PrintWriterDemo {

	public static void main(String[] args) throws IOException {
		//1��������ӡ�ַ���
		PrintWriter pw=new PrintWriter("pw.txt");
		//2.1��д���ݡ����ĸ�����writer,Ӧ����5��д������
		pw.write("Hello");
		pw.write("Java");
		//2.2 ����write�����⣬������print��������������������͵�����
		pw.println();
		pw.print(true);
		pw.print(123.5);
		pw.println("hello,world");
		//3��ˢ��
		pw.flush();
		pw.close();
		
	}

}
