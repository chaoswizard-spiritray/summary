package edu.xhu_04;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo1 {

	public static void main(String[] args) throws IOException {
		//创建缓冲区输出流对象
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("b.txt"));
		//写数据
		bos.write("hello java".getBytes());   //使用了缓冲区写数据
		//释放资源
		bos.close();

	}

}
