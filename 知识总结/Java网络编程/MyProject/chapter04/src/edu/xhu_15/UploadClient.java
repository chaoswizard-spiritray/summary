package edu.xhu_15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class UploadClient {
	public static void main(String[] args) throws IOException {
		// 创建客户端Socket对象
		Socket s = new Socket("192.168.2.178", 11111);

		// 封装文件
		// BufferedReader br = new BufferedReader(new FileReader(
		// "InetAddressDemo.java"));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				"socket.jpg"));
		// 封装通道内流
		BufferedOutputStream  bos = new BufferedOutputStream(s.getOutputStream());
		
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len = bis.read(buffer)) != -1) { // 阻塞
			bos.write(buffer,0,len);
			
			bos.flush();
		}

		// Socket提供了一个终止，它会通知服务器你别等了，我没有数据过来了
		s.shutdownOutput();

		// 接收反馈
		BufferedReader brClient = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String client = brClient.readLine(); // 阻塞
		System.out.println(client);

		// 释放资源
		bis.close();
		s.close();
	}
}
