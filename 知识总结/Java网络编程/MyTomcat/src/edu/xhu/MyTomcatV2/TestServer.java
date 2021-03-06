package edu.xhu.MyTomcatV2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TestServer {
	//定义静态变量WEB_ROOT获取WebContent的绝对路径
	public static String WEB_ROOT=System.getProperty("user.dir") +"\\WebContent";
	//定义变量url获取当前请求url
	private static String url="";
	//定义map，读取conf.properties文件
	private static Map<String,String> map=new HashMap<String,String>();
	//启动服务器时，需要将配置文件内容读取到map中
	static{
		//创建Properties对象
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(WEB_ROOT+"\\conf.properties"));
			Set set=prop.keySet();
			Iterator iterator=set.iterator();
			while(iterator.hasNext()){
				String key=(String)iterator.next();
				String value=prop.getProperty(key);
				map.put(key, value);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		
		System.out.println(map);
		//定义Socket监听器
		
		ServerSocket ssc=null;
		Socket sc=null;
		InputStream is=null;
		OutputStream ops=null;
		
		try{
			//创建Socket监听器，监听8080端口
			ssc=new ServerSocket(8080);
			while(true){    //循环监听是否有连接
				//若有连接，则获取套接字
				sc=ssc.accept();
				//获取输入流
				is=sc.getInputStream();
				//获取输出流
				ops=sc.getOutputStream();
				//获取HTTP请求行，得到客户端要访问的资源名称，将资源名称赋给url
				parse(is);
				//判断url是静态资源还是Java服务小程序
				if(url!=null){
					if(url.indexOf(".")!=-1){
						//静态资源请求
						//发送静态资源
						sendStaticResource(ops);
					}else{
						//Java服务小程序
						sendDynamicResource(is,ops);
					}
				}
				
				
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//释放资源
			if(is!=null){
				is.close();
				is=null;
			}
			if(ops!=null){
				ops.close();
				ops=null;
			}
			if(sc!=null){
				sc.close();
				sc=null;
			}
			//ssc不需要关闭
		}
		

	}
	
	//获取HTTP请求
	private static void parse(InputStream is) throws IOException {
		//定义字符缓冲区
		StringBuffer content=new StringBuffer(2048);
		//定义字节数组，用于输入流的read方法
		byte[] buffer=new byte[2048];
		//定义读到的实际字节长度
		int length=-1;
		//读取输入流（HTTP请求)
		length=is.read(buffer);
		//将读到的字节转换成字符
		for(int i=0;i<length;i++){
			content.append((char)buffer[i]);
		}
		//打印HTTP请求
		System.out.println(content);
		//解析要获取的资源名称
		parseUrl(content.toString());
	}
	private static void parseUrl(String content) {
		// 解析HTTP请求行中要获取的资源名称，即第一行的两个空格之间的内容
		int index1,index2;
		//获取第一个空格的位置
		index1=content.indexOf(" ");
		if(index1!=-1){
			//获取第二个空格的位置
			index2=content.indexOf(" ",index1+1);
			if(index2>index1){
				url=content.substring(index1+2,index2);
			}
		}
		//打印url
		System.out.println(url);
		
	}
	//将请求资源发送到客户端
	private static void sendStaticResource(OutputStream ops) throws IOException {
		// 定义字节数组，用于读文件
		byte[] buffer=new byte[2048];
		//定义文件输入流读文件内容
		FileInputStream fis=null;
		try{
			File file=new File(WEB_ROOT,url);
			if(file.exists()){			//若文件存在，则发送状态200 OK
				//向客户端发送响应头信息
				ops.write("HTTP/1.1 200 OK\n".getBytes());
				//ops.write("Server:apache-Coyote/1.1\n".getBytes());
				ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				ops.write("\n".getBytes());
				//向客户端发送响应文件内容
				//创建文件输入流
				fis=new FileInputStream(file);
				//读文件内容
				int length=fis.read(buffer);
				//一直读到文件结尾
				while(length!=-1){
					//写文件内容
					
					ops.write(buffer, 0, length);
					//继续读文件
					length=fis.read(buffer);
					
				}	
			}
			else{	//若文件不存在，则发送404
				//向客户端发送404 not found
				ops.write("HTTP/1.1 404 not found\n".getBytes());
				ops.write("Server:apache-Cayote/1.1\n".getBytes());
				ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
				ops.write("\n".getBytes());
				ops.write("file not found".getBytes());
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
	//发送动态页面
	private static void sendDynamicResource(InputStream is, OutputStream ops) throws  Exception {
		//向客户端发送响应头信息
		ops.write("HTTP/1.1 200 OK\n".getBytes());
		ops.write("Server:Apache\n".getBytes());
		ops.write("Content-Type:text/html;charset=utf-8\n".getBytes());
		ops.write("\n".getBytes());
		//向客户端发送响应文件内容
		//判断请求资源是否存在
		if(map.containsKey(url)){
			String value=map.get(url);  //得到Java小程序类
			//放射加载类
			Class clazz=Class.forName(value);
			//获取类对象
			Servlet servlet=(Servlet)clazz.newInstance();
			//执行对象方法
			servlet.init();
			servlet.service(is, ops);
			servlet.destroy();
			
		}
		
	}
}
