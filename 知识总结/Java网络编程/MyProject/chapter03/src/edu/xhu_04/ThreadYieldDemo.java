package edu.xhu_04;
/*
 * yield():��ͣ��ǰִ�е��̣߳���ִ�������߳�
 * �ö���߳�ִ�бȽϺ�г�������ܱ�֤һ��һ�ε�ִ��˳��,��Ҫ��֤һ��һ�Σ�����ʹ�û��ѻ���
 */
public class ThreadYieldDemo {

	public static void main(String[] args) {
		ThreadYield ty1=new ThreadYield();
		ThreadYield ty2=new ThreadYield();
		ty1.setName("������");
		ty2.setName("�´��");
		ty1.start();
		ty2.start();
		

	}

}
