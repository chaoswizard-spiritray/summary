package edu.xhu_05;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * �����ֽ����ڴ����Ӣ���ַ�ʱ�����ر𷽱㣬�ر���һ�ζ�һ���ֽ��ڿ���̨���ʱ�����ֽ������ȡʱ��
 * �󲿷�ʱ�������ȷ����Ҳ�п��ܴ�������������
 * ��ˣ�Java�ṩ��һ��ת�������ַ���=�ֽ���+�����һ�ֽڱ��룬���ֽڱ��룬���ֽڱ��룩
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
 * 
 * OutputStreamWriter(OutputStream out) ����һ��ʹ��Ĭ���ַ������OutputStreamWriter��  
 * OutputStreamWriter(OutputStream out, String charsetName) ����һ��ʹ�������ַ�����OutputStreamWriter��  
 */
public class OutputStreamWriterDemo1 {

	public static void main(String[] args) throws IOException {
		//�����ַ������������
		//OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw1.txt"));   //Ĭ��GBK����
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("osw1.txt"),"UTF-8");   //ʹ��UTF-8����
		
		//д���ݣ���5�ַ���
		osw.write("�й�");
		/*
		 * �ַ�����д���Ƕ��ֽڣ�����Ҫ�������������д���ļ�����Ҫˢ��һ��ϵͳ������ϵͳ���Ļ�����
		 */
		//osw.flush();
		osw.close();  //�رչ����Լ�ҲҪ��ˢ�»�������Ȼ�����ͷ���Դ

	}

}
