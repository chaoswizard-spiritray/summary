package edu.xhu_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * ���ֽ�����ȥ��ȡ��
 * int read(byte[] b)
 * 
 * ͨ��������ʾ������read����
 */
public class FileInputStreamDemo2 {

	public static void main(String[] args) throws IOException {
		// ��������������
		FileInputStream fis=new FileInputStream("a.txt");
//		byte[] b=new byte[5];
//		int len;
		//��һ�ζ�
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		fis.close();
		//�Ľ�
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b));
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.println(new String(b,0,len));
//		fis.close();
//		byte[] b=new byte[100];
//		int len;
//		len=fis.read(b);
//		System.out.println(len);
//		System.out.print(new String(b,0,len));  //��ʾһ�²���len�Ľ����ǿ��һ��Ҫ��0,len
//		fis.close();
		//���հ����,�����Сһ��ȡ1024��1024����������Ч�ʸ�
		byte[] b=new byte[1024];
		int len=0;
		while((len=fis.read(b))!=-1){
			System.out.print(new String(b,0,len));
		}
		fis.close();
	}

}
