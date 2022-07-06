package edu.xhu_07;

/*
 * 数据操作流：操作基本数据类型的流。向流写入一个字符，写入一个整数，写入一个浮点数等等
 * 数据输出流：DataInputStream
 *
 * 构造方法：
 * DataOutputStream(OutputStream out) 创建一个新的数据输出流，以将数据写入指定的底层输出流。 
 * 
 * 写方法：
 *  void write(byte[] b, int off, int len) 写入 len从指定的字节数组起始于偏移 off基础输出流。 
 *  void write(int b) 将指定的字节（参数 b的低8位）写入底层输出流。  
 *  void writeBoolean(boolean v) 将 boolean写入底层输出流作为1字节值
 *  void writeByte(int v) 将 byte作为1字节值写入底层输出流。
 *  void writeBytes(String s) 将字符串作为字节序列写入基础输出流。
 *  void writeChar(int v) 将 char写入底层输出流作为2字节值，高字节优先。
 *  void writeChars(String s) 将字符串写入底层输出流作为一系列字符。
 *  void writeDouble(double v) 双参数传递给转换 long使用 doubleToLongBits方法在类 Double ，
 *                             然后写入该 long值基础输出流作为8字节的数量，高字节。  
 *  void writeFloat(float v) 浮子参数的转换 int使用 floatToIntBits方法在类 Float ，
 *                             然后写入该 int值基础输出流作为一个4字节的数量，高字节。  
 *  void writeInt(int v) 将底层输出流写入 int作为四字节，高位字节。                            
 *  void writeLong(long v) 将 long写入底层输出流，为8字节，高字节为首。                            
 *  void writeShort(int v) 将 short写入底层输出流作为两个字节，高字节优先。                             
 *  void writeUTF(String str) 使用 modified UTF-8编码以机器无关的方式将字符串写入基础输出流。                           
 *                             
 * 刷新方法：
 * void flush()   刷新此数据输出流。  
 * 关闭方法：
 * void close()
 */

public class DataOutputStreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
