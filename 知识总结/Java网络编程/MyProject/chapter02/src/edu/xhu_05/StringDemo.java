package edu.xhu_05;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
 * 字符的编码和解码
 * 
 * String(byte[] bytes) 通过使用平台的默认字符集解码指定的字节数组来构造新的 String 。
 * 
 * String(byte[] bytes, Charset charset) 构造一个新的String由指定用指定的字节的数组解码charset 
 * 
 * byte[] getBytes(Charset charset) 使用给定的charset将该String编码为字节序列，将结果存储到新的字节数组中。  
 * 
 * 编码：将看得懂的变成看不懂的           String--------byte[]
 * 解码：把看不懂的变成看得懂的           byte[]--------String
 * 
 * 结论：编码和解码要用同一个编码表。
 */
public class StringDemo {

	public static void main(String[] args) throws IOException {
		String s="中国";
		//byte[] bys=s.getBytes();  // [-42, -48, -71, -6]  Windows简体中文用的GBK编码，Linux简体中文用的UTF-8 默认本地编码
		//byte[] bys=s.getBytes("GBK");  //[-42, -48, -71, -6]
		byte[] bys=s.getBytes("UTF-8");  //[-28, -72, -83, -27, -101, -67]
		System.out.println(Arrays.toString(bys));  
		
		String ss=new String(bys); //按默认的GBK解码出现乱码           解码成“涓浗”
		System.out.println(ss);
		
		String ss1=new String(bys,"UTF-8");   //按utf-8解码，解码成“中国” 
		System.out.println(ss1);

	}

}
