package edu.xhu_08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * �������������һ������ת��Ϊ�����������
 * ObjectOutputStream
 * ���췽����
 * ObjectOutputStream(OutputStream out) ����һ��д��ָ����OutputStream��ObjectOutputStream�� 
 * д������
 * 1�����Խ����������͵�����д��������У���DataOutputStream����ͬ��д����
 * 2��void writeObject(Object obj) ��ָ���Ķ���д��ObjectOutputStream��
 * ˢ�·�����
 * void flush();  
 */
public class ObjectOutputStreamDemo {

	public static void main(String[] args) throws IOException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		//write();
		read();
	}

	private static void read() throws ClassNotFoundException, IOException {
		ObjectInputStream os=new ObjectInputStream(new FileInputStream("student.txt"));
		Object s=new Student();
		
		s=os.readObject();
		System.out.println(s);
		
	}

	private static void write() throws IOException, IOException {
		ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("student.txt"));
		Student s=new Student();
		s.setSno("2016001");
		s.setName("Hello");
		s.setAge(18);
		os.writeObject(s);
		os.flush();
		
	}

}
