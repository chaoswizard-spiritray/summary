package edu.xhu_06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * �ַ������������������
 * BufferedWriter: newLine()  д���зָ���������ϵͳ����д��\r\n
 * BufferedReader: readLine() һ�ζ���һ�У������������з�,�����ļ�βΪnull��ע�ⲻ��-1
 * 
 * 
 * 
 * ���ı��ļ����룺
 * String line=null;
 * while((line=br.readLine())!=null){
 *     System.out.println(line);
 * }
 * 
 * 
 * 
 */

public class BufferedDemo {

	public static void main(String[] args) throws IOException {
		//������������ַ�������
		BufferedWriter br=new BufferedWriter(new FileWriter("a.txt"));
		//д������
		for(int x=0;x<10;x++){
			br.write("hello"+x);
			br.newLine();
			br.flush();
		}
		br.close();

	}

}
