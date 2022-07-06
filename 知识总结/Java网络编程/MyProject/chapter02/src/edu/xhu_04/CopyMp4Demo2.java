package edu.xhu_04;
/*
 * 需求：把D:\\XXX.mp4文件拷贝到当前项目下，变成CopyX.mp4
 * 要求：用字节流四种方式
 * 		基本字节流一次读一个字节
 * 		基本字节流一次读多个字节（字节数组）
 * 		高效字节流一次读一个字节
 * 		高效字节流一次读多个字节（字节数组）
 */
public class CopyMp4Demo2 {

	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		method1("D:\\xxx.mp4","Copy1.mp4");
		//method2("D:\\xxx.mp4","Copy2.mp4");
		//method3("D:\\xxx.mp4","Copy3.mp4");
		//method4("D:\\xxx.mp4","Copy4.mp4");
		long end=System.currentTimeMillis();
		System.out.println("共耗时："+(end-start)+"毫秒");

	}

	private static void method4(String scr, String dest) {
		
		
	}

	private static void method3(String src, String dest) {
		
		
	}

	private static void method2(String src, String dest) {
		
		
	}

	private static void method1(String src, String dest) {
		
		
	}

}
