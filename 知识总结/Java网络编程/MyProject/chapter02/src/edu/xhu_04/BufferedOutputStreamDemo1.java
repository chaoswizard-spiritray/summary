package edu.xhu_04;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//�������������������
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("b.txt"));
		//д����
		bos.write("hello java".getBytes());   //ʹ���˻�����д����
		//�ͷ���Դ
		bos.close();

	}

}
