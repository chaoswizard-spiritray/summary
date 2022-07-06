package edu.xhu_05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
 * �ַ��ı���ͽ���
 * 
 * String(byte[] bytes) ͨ��ʹ��ƽ̨��Ĭ���ַ�������ָ�����ֽ������������µ� String ��
 * 
 * String(byte[] bytes, Charset charset) ����һ���µ�String��ָ����ָ�����ֽڵ��������charset 
 * 
 * byte[] getBytes(Charset charset) ʹ�ø�����charset����String����Ϊ�ֽ����У�������洢���µ��ֽ������С�  
 * 
 * ���룺�����ö��ı�ɿ�������           String--------byte[]
 * ���룺�ѿ������ı�ɿ��ö���           byte[]--------String
 * 
 * ���ۣ�����ͽ���Ҫ��ͬһ�������
 */
public class StringDemo {

	public static void main(String[] args) throws IOException {
		String s="�й�";
		//byte[] bys=s.getBytes();  // [-42, -48, -71, -6]  Windows���������õ�GBK���룬Linux���������õ�UTF-8 Ĭ�ϱ��ر���
		//byte[] bys=s.getBytes("GBK");  //[-42, -48, -71, -6]
		byte[] bys=s.getBytes("UTF-8");  //[-28, -72, -83, -27, -101, -67]
		System.out.println(Arrays.toString(bys));  
		
		String ss=new String(bys); //��Ĭ�ϵ�GBK�����������           ����ɡ�中国��
		System.out.println(ss);
		
		String ss1=new String(bys,"UTF-8");   //��utf-8���룬����ɡ��й��� 
		System.out.println(ss1);

	}

}
