package edu.xhu_01;
/*
 * �쳣����
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IODemo4 {

	public static void main(String[] args) {
		//�ֿ����쳣����
//		FileOutputStream fos=null;
//		try {
//			fos = new FileOutputStream("hello4.txt");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			fos.write("java".getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			fos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//��������
//		try {
//			FileOutputStream fos = new FileOutputStream("hello4.txt");
//			fos.write("java".getBytes());
//			fos.close();          //��ǰ�����������⣬close()ִ�в���
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//�Ľ�
//		FileOutputStream fos=null;
//		try {
//			fos= new FileOutputStream("hello4.txt");
//			fos.write("java".getBytes());
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			//Ϊ�˱�֤close()һ����ִ�У��ŵ�finally��
//			try {
//				fos.close();		//��fosδ�����ɹ����þ�ᱨ��ָ���쳣
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		//���հ�
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("hello4.txt");
			fos.write("java".getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Ϊ�˱�֤close()һ����ִ�У��ŵ�finally�Ϊ�˲����ֿ�ָ���쳣
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
