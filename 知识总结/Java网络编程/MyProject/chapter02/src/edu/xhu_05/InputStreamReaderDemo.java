package edu.xhu_05;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;

/*
 * �ֽ����������Ĳ��Ǻܷ��㡣ÿ�ζ�һ���ֽ�һ�������⣬�����ֽ�����������ܲ������⣬�����ܴ���������
 * �ֽ����������Ŀ��ܳ��ֵ����⣺
 *    ���ֽ�����ȡһ���������ĵ��ļ�˵���������ļ��е������ǡ�hello�й��������ֽ������ļ�������ʾ�����ݡ�
 * Java�ṩ����һ���ࣺReader��Writer�����ڶ�ȡ�ַ�����Ϊ�ַ���������һ��ת���������ֽ���+�������
 * �����
 * ASCII    '0'----48   'A'----64   'a'----96     �ַ���Ӧһ����ֵ 0-127
 * ISO-8859-1: latin���  һ���ֽ� 8λ 0-255 ��չASCII��
 * GB2312�����ֱ����
 * GBK:�������������
 * GB18030��GBKȡ����
 * BIG5:�����ֱ����
 * Unicode��������ʱ����ÿ���ַ��������ֽڣ�java���Բ���Unicode����
 * UTF-8���룺�䳤���룬���1���ֽڣ�������ֽڣ��ܺ��ں���ASCII,�ּ�����������ַ�
 * 
 * 
 * InputStreamReader(InputStream is):����Ĭ�ϱ����ȡ�ַ�
 * InputStreamReader(InputStream is,String charsetName):����ָ�������ȡ�ַ�
 * Reader�ķ�����
 * 		int read() ��һ���ַ� 
 * 		int read(char[] cbuf) ���ַ���������  
 */
public class InputStreamReaderDemo {

	public static void main(String[] args) throws IOException {
		//�����ַ�����������
		InputStreamReader isr =new InputStreamReader(new FileInputStream("osw1.txt"),"UTF-8");
		//��ȡ�ַ����ݣ�������1���ֽڣ�Ҳ�����Ƕ��ֽ�,����಻�ᳬ��3�ֽڣ���int��4�ֽڣ�
		//�㹻�洢һ���ַ��ı��룬������charȥ����Ϊʲô��
		int ch=0;
		ch=isr.read();
		System.out.print((char)ch);
		
		
		//�ͷ���Դ
		isr.close();
	}

}
