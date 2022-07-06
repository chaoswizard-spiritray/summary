package edu.xhu_01;
/*
 * 异常处理
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IODemo4 {

	public static void main(String[] args) {
		//分开做异常处理
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
		//合起来做
//		try {
//			FileOutputStream fos = new FileOutputStream("hello4.txt");
//			fos.write("java".getBytes());
//			fos.close();          //若前两步出了问题，close()执行不到
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//改进
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
//			//为了保证close()一定会执行，放到finally里
//			try {
//				fos.close();		//若fos未创建成功，该句会报空指针异常
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		//最终版
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("hello4.txt");
			fos.write("java".getBytes());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 为了保证close()一定会执行，放到finally里，为了不出现空指针异常
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
