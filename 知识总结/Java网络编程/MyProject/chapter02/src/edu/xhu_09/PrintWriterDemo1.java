package edu.xhu_09;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * ����PrintWriter���Զ�ˢ�¹��ܣ�
 * PrintWriter(OutputStream out, boolean autoFlush) 
 * �Զ�ˢ�£�
 * println():
 * �൱�ڣ�
 * write();
 * newLine();
 * flush();
 */
public class PrintWriterDemo1 {

	public static void main(String[] args) throws IOException {
		//1����������
		PrintWriter pw=new PrintWriter(new FileWriter("pw.txt"),true);
		//2���������
		//pw.print("hello");  //���Զ�ˢ��
		pw.println("hello,world");  //�Զ�ˢ�£��������������Զ�ˢ�£�println,printf,format
		pw.printf("%6.1f",123.564);

	}

}
