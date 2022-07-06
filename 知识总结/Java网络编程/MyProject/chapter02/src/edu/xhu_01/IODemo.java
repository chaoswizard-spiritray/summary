package edu.xhu_01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
 * IO��������豸֮�����ݴ���
 * IO�����ࣺ
 * �������Ϊ����������
 * ���������ͷ�Ϊ�ֽ������ַ������ֽ�����������������������ڴ����ַ���������ṩһ��ר�Ŵ����ַ�������
 * �ü��±��򿪿������ģ����ֽ��������ö��ļȿ������ֽ�����Ҳ�������ַ�����
 * ʲô����֪�������ֽ�����
 * ����ܹ���Ҫ����4�������ֽ����롢��������ַ����롢�����
 * 
 * �ֽ����ĳ��ࣺjava.io��
 * 		���������ࣺInputStream ����������OutputStream���������
 * �ַ����ĳ��ࣺ
 * 		Reader��Writer��
 * 
 * �������ļ�д��Hello Java
 * ������
 * 		A:�������������ַ�����ʵ�֣����ַ��������ֽ�������ֵģ����������ֽ�����ʵ�֡�
 * 		B:����Ҫ���ļ���дһ�仰���������Ҫʹ���ֽ��������
 * ͨ���������������Ҫʹ��OutputStream�����鿴API�����Ƿ���OutputStream����һ�������࣬���
 * ������Ҫ�ҵ�����ʵ���ࣨ���ࣩ������������Ҫ���ļ���д���ݣ����Է���OutputStream����
 * ��һ��FileOutputStream�࣬������һ�������ࡣ
 * �鿴FileOutputStream�࣬�ҵ�����Ĺ��췽����
 *      FileOutputStream(File file)
 * 		FileOutputStream(String name)
 * 		
 * �ܽ᣺�ֽ��������������
 * 	    A:�����ֽ����������
 * 		B:д����
 * 		C:�ر������
 */
public class IODemo {
	public static void main(String[] args) throws IOException {
		//��һ���������ֽ�������
		//�õ�һ�ֹ��췽�������ļ��ֽ������
		//File file=new File("hello.txt");
		//FileOutputStream fos=new FileOutputStream(file);
		//�õڶ��ֹ��췽��
		FileOutputStream fos=new FileOutputStream("hello.txt");  
		//�鿴�ù��췽����Դ���֪�÷����ǵ�����File������һ�����󣬵ȼ��ڵ�һ�ֹ��췽��
		/*
		 * �����ֽ����������˼����£�
		 * A: ���ò���ϵͳ���ļ��������ܴ����ļ�
		 * B: ����fos����
		 * C: ��fos�������ļ�������ָ����ļ�)
		 */
		//�ڶ�����д����
		fos.write("Hello java".getBytes()); 
		fos.write("�Ұ�Java".getBytes());
		//ע���׳����쳣���IOException�ˣ�ԭ����FileNotFoundExceptionû���ˣ�
		//ԭ����IOException��FileNotFoundException�ĸ��࣬�����쳣������Ȼ�����ø������
		//���������ͷ���Դ
		//�ر���������ͷ�����������ص�ϵͳ��Դ
		fos.close();
		/*
		 * A:������������������������ոö���ռ�õ���Դ
		 * B:֪ͨϵͳ�ͷ�����ļ���ص���Դ
		 */
		
	}

}
