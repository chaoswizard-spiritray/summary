package edu.xhu_16;
/*
 * ������������߳��У�ͨ��ѧ�������flag��־��ȷ���߳��Ƿ�ȴ�����ֱ�ӷ��������ݳ�Ա��
 * ���������ݳ�ԱΪ˽�����ԣ��߳����޷����ʵġ���ô��������д�أ�
 * ����������ڹ������Դ����������Ҫ��д�����ӿڣ����̵߳��á�
 * �����ܱ�д����ͨ������
 * 		���ö����ֵ�ͻ�ȡ�����ֵҪ��ͬ������ͬ��������ɷ�����ɣ��÷���Ӧ��Ϊͬ��������
 */
public class StudentDemo {

	public static void main(String[] args) {
		
		Student s=new Student();
		
		SetThread st=new SetThread(s);
		GetThread gt=new GetThread(s);
		
		Thread t1=new Thread(st);
		Thread t2=new Thread(gt);
		t2.start();
		t1.start();   
		

	}

}
