package edu.xhu_01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/*
 * ���ʵ�����ݻ���?
 * �ʣ�д��\n�Ƿ�����أ���eclipse����ǿ��Եģ���windows�ļ��±��ͳ������ˡ�
 * ԭ�򣺲�ͬ����ϵͳ��Իس�����ʶ���ǲ�ͬ��
 * 		Windows��	ʶ��\r\n
 * 		linux:		ʶ��\n
 * 		Mac:     	ʶ��\r
 *�߼����±�������Զ�ʶ����ֻس�����Eclipse���ļ��±��������ʶ����ֻ��з���
 *��Windows���ļ��±�Ҫʶ��\r\n 
 * ���׷�����ݣ�
 * FileOutputStream(String name,boolean append)
 */



public class IODemo3 {

	public static void main(String[] args) throws IOException {
		//1�������ֽ����������
		FileOutputStream os=new FileOutputStream("hello2.txt");
		//2��д����
		for(int x=0;x<10;x++){
			os.write(("hello"+x).getBytes());
			os.write("\r\n".getBytes());  //linux���ֲ�������
		}
		os.close();

	}

}
