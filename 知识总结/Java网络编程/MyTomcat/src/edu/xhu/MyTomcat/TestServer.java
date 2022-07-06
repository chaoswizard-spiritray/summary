package edu.xhu.MyTomcat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
	//���徲̬����WEB_ROOT��ȡWebContent�ľ���·��
	public static String WEB_ROOT=System.getProperty("user.dir") +"\\WebContent";
	//�������url��ȡ��ǰ����url
	private static String url="";
	public static void main(String[] args) throws IOException {
		//����Socket������
		ServerSocket ssc=null;
		Socket sc=null;
		InputStream is=null;
		OutputStream ops=null;
		
		try{
			//����Socket������������8080�˿�
			ssc=new ServerSocket(8080);
			while(true){    //ѭ�������Ƿ�������
				//�������ӣ����ȡ�׽���
				sc=ssc.accept();
				//��ȡ������
				is=sc.getInputStream();
				//��ȡ�����
				ops=sc.getOutputStream();
				//��ȡHTTP�����У��õ��ͻ���Ҫ���ʵ���Դ���ƣ�����Դ���Ƹ���url
				parse(is);
				//���;�̬��Դ
				sendStaticResource(ops);
				//sc.shutdownOutput();
				sc.close();
				System.out.println("�������");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
//			if(is!=null){
//				is.close();
//				is=null;
//			}
//			if(ops!=null){
//				ops.close();
//				ops=null;
//			}
//			if(sc!=null){
//				sc.close();
//				sc=null;
//			}
			//ssc����Ҫ�ر�
		}
		

	}
	//��ȡHTTP����
	private static void parse(InputStream is) throws IOException {
		//�����ַ�������
		StringBuffer content=new StringBuffer(2048);
		//�����ֽ����飬������������read����
		byte[] buffer=new byte[2048];
		//���������ʵ���ֽڳ���
		int length=-1;
		//��ȡ��������HTTP����)
		length=is.read(buffer);
		//���������ֽ�ת�����ַ�
		for(int i=0;i<length;i++){
			content.append((char)buffer[i]);
		}
		//��ӡHTTP����
		System.out.println(content);
		//����Ҫ��ȡ����Դ����
		parseUrl(content.toString());
	}
	private static void parseUrl(String content) {
		// ����HTTP��������Ҫ��ȡ����Դ���ƣ�����һ�е������ո�֮�������
		int index1,index2;
		//��ȡ��һ���ո��λ��
		index1=content.indexOf(" ");
		if(index1!=-1){
			//��ȡ�ڶ����ո��λ��
			index2=content.indexOf(" ",index1+1);
			if(index2>index1){
				url=content.substring(index1+2,index2);
			}
		}
		//��ӡurl
		System.out.println(url);
		
	}
	//��������Դ���͵��ͻ���
	private static void sendStaticResource(OutputStream ops) throws IOException {
		// �����ֽ����飬���ڶ��ļ�
		byte[] buffer=new byte[2048];
		//�����ļ����������ļ�����
		FileInputStream fis=null;
		try{
			File file=new File(WEB_ROOT,url);
			if(file.exists()){			//���ļ����ڣ�����״̬200 OK
				//��ͻ��˷�����Ӧͷ��Ϣ
				ops.write("HTTP/1.1 200 OK\n".getBytes());
				ops.write("Server:apache-Coyote/1.1\n".getBytes());
				ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				ops.write("\n".getBytes());
				//��ͻ��˷�����Ӧ�ļ�����
				//�����ļ�������
				fis=new FileInputStream(file);
				//���ļ�����
				int length=fis.read(buffer);
				//һֱ�����ļ���β
				while(length!=-1){
					//д�ļ�����
					
					ops.write(buffer, 0, length);
					//�������ļ�
					length=fis.read(buffer);
					
				}	
			}
			else{	//���ļ������ڣ�����404
				//��ͻ��˷���404 not found
				ops.write("HTTP/1.1 404 Not Found\n".getBytes());
				ops.write("Server:apache-Cayote/1.1\n".getBytes());
				ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				ops.write("\n".getBytes());
				ops.write("File not found".getBytes());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				fis.close();
				fis=null;
			}
			
		}
	}
	

}
