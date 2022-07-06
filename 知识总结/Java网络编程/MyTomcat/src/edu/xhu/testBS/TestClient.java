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
			//1、建立Socket,连接localhost,端口8080
			sc=new Socket("www.baidu.com",80);
			//2 建立输入流is
			is=sc.getInputStream();
			//3 建立输出流ops
			ops=sc.getOutputStream();
			//4 向服务器发出HTTP请求
			//4.1 请求行
			ops.write("GET / HTTP/1.1\n".getBytes());
			//4.2 请求头
			ops.write("HOST:www.baidu.com\n".getBytes());
			//4.3 空行
			ops.write("\n".getBytes());
			//5 读取来自服务器的内容
			byte[] b=new byte[1024];
			int len;
			while((len=is.read(b))!=-1){
				System.out.print(new String(b,0,len,"utf-8"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6 关闭资源
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
