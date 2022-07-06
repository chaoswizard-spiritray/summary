package edu.xhu_05;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * �ַ������д���ݵ�5�ַ�����
 * void write(int c) ��							дһ���ַ�  
 * void write(char[] cbuf) ��					д��һ���ַ����顣  
 * void write(char[] cbuf, int off, int len) ��	д���ַ������һ���֡�  
 * void write(String str) ��						дһ���ַ���
 * void write(String str, int off, int len) ��	дһ���ַ�����һ���֡�    
 *
 *  flush()��close()������
 *  flush():��������������д�����У��������Լ���ʹ��
 *  close():��������������д�����У��ر�����
 *  
 *  �ַ��������Ķ��뷽�����֣�
 *  int read():   һ�ζ���1���ַ���-1��ʾû��������
 *  int read(char[] cbuf):   һ�ζ������ַ���ȡ�����ַ�����Ĵ�С��һ��Ϊ1024������ֵ��ʾʵ�ʶ������ַ�����-1��ʾδ��������
 *  ���ֽ��������Ķ��뷽�����ƣ�ע�⣬һ�����ֽ�(byte)���飬һ�����ַ�(char)����
 */
public class OutputStreamWriterDemo2 {

	public static void main(String[] args) throws IOException {
		//�����ַ������������
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw2.txt"));   //Ĭ��GBK����
		
		
		//1��дһ���ַ�
		osw.write('a');
		osw.write(97);
		osw.write('\n');
		osw.flush();
		//2��дһ���ַ�����
		char[] chs={'a','��','��','b'};
		System.out.println(chs);
		osw.write(chs);
		osw.write("\n");
		//3��дһ����
		osw.write(chs,1,2);
		osw.write("\n");
		//4��дһ����
		osw.write("Hello java\n");
		//5��дһ������һ����
		osw.close();  //�رչ����Լ�ҲҪ��ˢ�»�������Ȼ�����ͷ���Դ

	}

}
