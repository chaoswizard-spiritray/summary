package edu.xhu_14;
/*
 * �̼߳�ͨ�ţ���ͬ�߳����ͬһ��Դ�Ĳ������Ƚϵ��͵İ������������̺߳������̶߳Բ�Ʒ�Ĳ���
 * ����ͨ�������̣߳������ߣ��ͻ�ȡ�̣߳������ߣ���ͬһ��ѧ��������в���
 * ������
 * 		A:��Դ��   Student
 * 		B:�����߳�   SetThread  (������)
 * 		C:��ȡ�߳�  GetThread  (������)
 * 		D:������  StudentDemo
 * ����1��ÿ���̶߳�����һ��ѧ�������ⲻ�Ƕ�ͬһ����Դ������
 * Ӧ������紴��һ����Դ������Դ���ݸ��߳�
 * ����2���������ݻ��ҵ����
 * ԭ���̰߳�ȫ����
 * ����취��������һ����ͬһ����������紫������ѧ�����������Ϳ����ˡ�
 * 
 */
public class StudentDemo {

	public static void main(String[] args) {
		
		Student s=new Student();
		
		SetThread st=new SetThread(s);
		GetThread gt=new GetThread(s);
		
		Thread t1=new Thread(st);
		Thread t2=new Thread(gt);
		t2.start();
		t1.start();   //����t2�̣߳������̣߳�����t1�̣߳������̣߳�ִ�еĻ�������Ľ������Ϊnull--0
		

	}

}
