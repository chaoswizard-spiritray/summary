package edu.xhu.MyTomcatV2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//Java ����С����ӿ�
public interface Servlet {
	//��ʼ��
	void init();
	//����
	void service(InputStream is,OutputStream ops) throws IOException;
	//����
	void destroy();

}
