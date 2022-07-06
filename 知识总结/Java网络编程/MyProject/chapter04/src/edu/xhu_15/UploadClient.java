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
		// �����ͻ���Socket����
		Socket s = new Socket("192.168.2.178", 11111);

		// ��װ�ļ�
		// BufferedReader br = new BufferedReader(new FileReader(
		// "InetAddressDemo.java"));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				"socket.jpg"));
		// ��װͨ������
		BufferedOutputStream  bos = new BufferedOutputStream(s.getOutputStream());
		
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len = bis.read(buffer)) != -1) { // ����
			bos.write(buffer,0,len);
			
			bos.flush();
		}

		// Socket�ṩ��һ����ֹ������֪ͨ�����������ˣ���û�����ݹ�����
		s.shutdownOutput();

		// ���շ���
		BufferedReader brClient = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String client = brClient.readLine(); // ����
		System.out.println(client);

		// �ͷ���Դ
		bis.close();
		s.close();
	}
}
