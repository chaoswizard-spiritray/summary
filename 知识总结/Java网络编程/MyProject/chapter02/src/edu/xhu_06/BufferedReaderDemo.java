package edu.xhu_06;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * ��Ч���ַ������������ת�������������ַ����������
 * BufferedReader(Reader in);        ���ַ���������ȡ�ı��������ַ������ṩ�ַ���������еĸ�Ч��ȡ��
 * BufferedWriter(Writer out);		 ���ı�д���ַ�������������ַ������ṩ�����ַ���������ַ����ĸ�Чд�롣
 * InputStreamReader---FileInputStream---->FileReader----->Reader
 * OutputStreamWriter----FileOutputStream--->FileWriter---->Writer
 * 
 * BufferedReader���õ�read������
 * int read() ��һ���ַ� -1��ʾδ��������
 * int read(char[] cbuf);�������ַ����ַ����飬���ص�int��ʾʵ�ʶ������ַ�����-1��ʾδ��������
 * 
 * ���ⷽ�� 
 * String readLine() ��һ�����֡� �����������з�������null��ʾδ����
 * 
 *  
 * �ܽ᣺
 * 	�����ı��ļ� 8�ַ�ʽ ��  ��ͨ�ֽ�����һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ��������ֽ�����һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ�
 * 						  ��ͨ�ַ�������һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ��������ַ�����һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ�
 *  ���ƶ������ļ�4�ַ�ʽ����ͨ�ֽ�����һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ��������ֽ�����һ�ζ�һ���ֽڣ�һ�ζ����ɸ��ֽڣ�
 */
public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException  {
		//���������ַ����������
		//BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
		
		BufferedReader br=new BufferedReader(new FileReader("a.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("b.txt"));
		//���ַ�ʽ����һ�֣���һ���ַ����ڶ��֣���ȡ�ַ�����
		char[] cbuf=new char[1024];
		int len=0;
		while((len=br.read(cbuf))!=-1){
			bw.write(cbuf, 0, len);
		}
		br.close();
		bw.close();
		
	}

}
