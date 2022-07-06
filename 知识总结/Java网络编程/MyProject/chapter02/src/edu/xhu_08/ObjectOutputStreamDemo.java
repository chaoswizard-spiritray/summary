package edu.xhu_08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * 对象输出流：将一个对象转化为二进制流输出
 * ObjectOutputStream
 * 构造方法：
 * ObjectOutputStream(OutputStream out) 创建一个写入指定的OutputStream的ObjectOutputStream。 
 * 写方法：
 * 1、可以将简单数据类型的数据写入输出流中，与DataOutputStream流相同的写方法
 * 2、void writeObject(Object obj) 将指定的对象写入ObjectOutputStream。
 * 刷新方法：
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
