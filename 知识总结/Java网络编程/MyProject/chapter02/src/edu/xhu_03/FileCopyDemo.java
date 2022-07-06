package edu.xhu_03;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

import edu.xhu_02.FileInputStreamDemo1;

/*
 * ���󣺸���һ���ı��ļ�
 * ��������һ���ļ���ȡ���ݣ�д����һ���ļ�
 * ����Դ����ȡ����-------FileInputStream
 * Ŀ�ĵأ�д������-------FileOutputStream
 * 
 * �ļ������к��֣����������
 */
public class FileCopyDemo {

	public static void main(String[] args) throws IOException {
		//��������Դ����
		FileInputStream fis=new FileInputStream("hello2.txt");
		//����Ŀ�ĵ�Դ����
		FileOutputStream fos=new FileOutputStream("new.txt");
		
		//������д����
		int by=0;
		while((by=fis.read())!=-1){
			fos.write(by);
		}
		fis.close();
		fos.close();

	}

}
