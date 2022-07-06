package edu.xhu_06;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * �ַ������������
 * BufferedWriter,��OutputStreamWriter��װ������
 * ��OutputStreamWriter�������ӻ���������OutputStreamWriter����OutputStream���ֽ�������ת����
 * ���ֽ���+�����
 * ���췽����
 * BufferedWriter(Writer out) ����ʹ��Ĭ�ϴ�С��1024��������������Ļ����ַ��������
 * 
 * ��ͨд���ݷ�����
 * void write(int c) дһ���ַ�  
 * void write(char[] cbuf, int off, int len) д���ַ������һ���֡� 
 * void write(String s, int off, int len) дһ���ַ�����һ���� 
 * ����д������
 * void newLine() дһ���зָ����� ���ݲ�ͬ����ϵͳ��д���з���Linux '\n' MacOs '\r',Windows '\r\n'
 * ˢ�»��巽����
 * void flush();
 */
public class BufferedWriterDemo {

	public static void main(String[] args) throws IOException {
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bw.txt")));
		bw.write(65);
		bw.newLine();
		
		bw.write("�й�");
		bw.newLine();
		bw.flush();
		bw.close();
		//���ļ��������ļ�дʱ

	}

}
