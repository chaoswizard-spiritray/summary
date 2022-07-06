package edu.xhu_15;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class UserThread implements Runnable {
	private Socket s;

	public UserThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			// ��װͨ���ڵ���
			BufferedInputStream  bis = new BufferedInputStream(	s.getInputStream());
			
			// Ϊ�˷�ֹ���Ƴ�ͻ
			String newName = System.currentTimeMillis() + ".java";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newName));

			byte[] buffer = new byte[1024];
			int len=0;
			while ((len = bis.read(buffer)) != -1) { // ����
				bos.write(buffer,0,len);
				bos.flush();
			}

			// ��������
			BufferedWriter bwServer = new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream()));
			bwServer.write("�ļ��ϴ��ɹ�");
			bwServer.newLine();
			bwServer.flush();

			// �ͷ���Դ
			bos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
