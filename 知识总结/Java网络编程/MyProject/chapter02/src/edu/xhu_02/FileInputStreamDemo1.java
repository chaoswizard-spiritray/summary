package edu.xhu_02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * �ֽ��������Ĳ������裺
 * A:�����ֽ�����������
 * B:������
 * C:�ͷ���Դ
 * 
 * �����ݵķ�ʽ��
 * A: int read()   һ�ζ�һ���ֽ�
 * B: int read(byte[] b) һ�ζ������ֽ�
 * 
 */
public class FileInputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//1�������ֽ�����������
		FileInputStream fis=new FileInputStream("hello.txt");
		//2����һ���ֽ�
		//��һ�ζ�ȡ
//		int a=fis.read();
//		System.out.println(a);
//		System.out.println((char)a);
//		//�ڶ��ζ�ȡ
//		a=fis.read();
//		System.out.println(a);
//		System.out.println((char)a);
		/*
		 * ��ȡ�����ظ���ѭ����ȡ����ȡ���ٴΣ�
		 * ��ȡֵΪ-1ʱ����ʾ�����ļ���β
		 */
		int by=0;
		while((by=fis.read())!=-1){
			System.out.print((char)by);   //�����������⣬��Ϊ���Ѻ��ֱ���ֳ�һ��һ���ֽ���ʾ���Ժ�������ַ�����
		}
		
		//3���ͷ���Դ
		fis.close();
		
	}

}
