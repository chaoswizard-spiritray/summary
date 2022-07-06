package edu.xhu.testBS;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient {

	public static void main(String[] args) throws Exception {
		Socket sc=null;
		InputStream is=null;
		OutputStream ops=null;
		try{
			//1������Socket,����localhost,�˿�8080
			sc=new Socket("www.baidu.com",80);
			//2 ����������is
			is=sc.getInputStream();
			//3 ���������ops
			ops=sc.getOutputStream();
			//4 �����������HTTP����
			//4.1 ������
			ops.write("GET / HTTP/1.1\n".getBytes());
			//4.2 ����ͷ
			ops.write("HOST:www.baidu.com\n".getBytes());
			//4.3 ����
			ops.write("\n".getBytes());
			//5 ��ȡ���Է�����������
			byte[] b=new byte[1024];
			int len;
			while((len=is.read(b))!=-1){
				System.out.print(new String(b,0,len,"utf-8"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6 �ر���Դ
			if(is!=null){
				is.close();
			}
			if(ops!=null){
				ops.close();
			}
			if(sc!=null){
				sc.close();
			}
			
		}

	}

}
