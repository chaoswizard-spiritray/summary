package edu.xhu.MyTomcatV2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//Java 服务小程序接口
public interface Servlet {
	//初始化
	void init();
	//服务
	void service(InputStream is,OutputStream ops) throws IOException;
	//销毁
	void destroy();

}
