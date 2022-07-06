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
			// 封装通道内的流
			BufferedInputStream  bis = new BufferedInputStream(	s.getInputStream());
			
			// 为了防止名称冲突
			String newName = System.currentTimeMillis() + ".java";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newName));

			byte[] buffer = new byte[1024];
			int len=0;
			while ((len = bis.read(buffer)) != -1) { // 阻塞
				bos.write(buffer,0,len);
				bos.flush();
			}

			// 给出反馈
			BufferedWriter bwServer = new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream()));
			bwServer.write("文件上传成功");
			bwServer.newLine();
			bwServer.flush();

			// 释放资源
			bos.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
