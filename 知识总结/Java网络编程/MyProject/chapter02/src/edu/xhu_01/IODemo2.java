package edu.xhu_01;
/*
 * �ֽ����������������
 * A:�����ֽ����������
 * B:write����
 * C:close����
 * 
 * write�����ַ���
 * void write(byte[] b) 
 * void write(byte[] b, int off, int len) 
 * void write(int b)   
 * 
 * 
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IODemo2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//1�������ֽ����������
		OutputStream os=new FileOutputStream("hello1.txt");  //��̬
		//2��д����
		os.write(97);  //�����ļ�����97��
		byte[] b={98,99,100,101};
		os.write(b);
		os.write(b, 2, 2);
		//3���ͷ���Դ
		os.close();
		
	}

}
