package edu.xhu_04;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * ��Ȼͨ����������ķ�ʽ��ȡ�ֽ�����һ�ζ�һ���ֽ����죬JavaҲ�ṩ��һ���Դ�����Ķ�ȡ�ֽ����ķ�ʽ
 * ��Ϊ�������ֽ������������װ�����ģʽ��
 * BufferedInputStream�࣬
 * java.lang.Object -->
 * java.io.InputStream -->
 * java.io.FilterInputStream--> 
 * java.io.BufferedInputStream 
 * ���췽���� 
 * BufferedInputStream(InputStream in) ����һ�� BufferedInputStream������������������� in �����Ժ�ʹ��
 * BufferedInputStream(InputStream in, int size) ���� BufferedInputStream����ָ����������С��������������������� in��
 * ע�⣺������Ĳ�����һ��InputStream�����������ļ���ԭ��������ֻ����InputStream��ȡ�������ṩ��һ������������������
 * �����ɻ���������������ɡ����ǶԻ���������İ�װ��װ�Σ� 
 */
public class BufferedInputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("a.txt"));
		//��ȡ���ݣ����ַ�ʽ����FileInputStream����Ķ�ȡ����һ������ʹ���˻�����
		//��һ�֣���һ���ֽڣ��ӻ��������ڴ棩
//		int by=0;
//		while((by=bis.read())!=-1){
//			System.out.print((char)by);
//		}
//		bis.close();
		//�ڶ��ַ�ʽ�������ֽ�����
		byte[] bys=new byte[1024];
		int len=0;
		while((len=bis.read(bys))!=-1){
			System.out.print(new String(bys,0,len));
		}
		bis.close();
	}

}
