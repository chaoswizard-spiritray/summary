import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/*
ʹ�û���������ô�����ô���
1������ʵ�ʵ������д����
2���������ڴ���ʱ�ͱ������ڴ棬����ڴ�����һֱ�����ã����Լ��ٶ�̬����ͻ����ڴ�Ĵ���

�ٸ��򵥵����ӣ�����A����1w��שҪ�ᵽB��
����û�й��ߣ���������������һ��ֻ�ܰ�һ������ô��Ҫ��1w�Σ�ʵ�ʶ�д������
���A��B���ؾ����Զ�Ļ���IO�������ģ�����ô�������Ľ���ܴ�
����Ҫ�Ǵ�ʱ���������󿨳�������������һ�ο���5000������ô2�ξ͹���
���֮ǰ�����ܿ϶��Ǵ������ˡ�
����һ����ʵ�ʹ����У�����һ�����Ƚ��ļ������ڴ棬�ٴ��ڴ�д������ĵط�
����������������������Ƕ������û���������IO���ܡ�

�ھ�I/O����У����java.nio�����е�BufferedInputStream��BufferedOutputStream��
BufferedReader��BufferedWriter����ʵ���ж������˻�������
java.nio��������Buffer API��ʹ��Java�������ֱ�ӿ��ƺ����û�������

��Java NIO��,������ֱ��Ϊͨ��(Channel)����д�����ݵ�ͨ�����ͨ����ȡ���ݣ�
�����Ĳ����û��������������ݾͿ��Դﵽ�����ݸ�Ч�����Ŀ�ġ�
��NIO����Ҫ�а��ֻ�������:����bool�������7�ֻ�������+MappedByteBuffer������ר�������ڴ�ӳ���һ��ByteBuffer
 
���л���������4�����ԣ�capacity��limit��position��mark��
����ѭ��mark <= position <= limit <= capacity
��ʼʱ��position = 0;limit = capacity;mark = -1; 
capacity:���������������ɵ�������������ڻ��������󴴽�ʱ���趨���Ҳ��ܸı�
limit:����������д�ļ���λ�ã��Ҽ����ǿ����޸ĵġ�
position����һ��Ҫ������д��Ԫ�ص�λ�ã�ÿ�ζ�д����������ʱ����ı��ֵ��Ϊ�´ζ�д��׼��
mark����ǣ�����mark()������mark=position���ٵ���reset()������position�ָ�����ǵ�λ��

1��ʵ����

java.nio.Buffer����һ�������࣬���ܱ�ʵ������
Buffer���ֱ�����࣬��ByteBuffer��Ҳ�ǳ����࣬����Ҳ���ܱ�ʵ������
����ByteBuffer���ṩ��4����̬�������������ByteBuffer��ʵ����

A:allocate(int capacity) �Ӷѿռ��з���һ��������СΪcapacity��byte������Ϊ��������byte���ݴ洢�� 
B:allocateDirect(int capacity) �ǲ�ʹ��JVM��ջ����ͨ������ϵͳ�������ڴ��������������
	���뵱ǰ����ϵͳ�ܹ����õ���ϣ�����ܽ�һ�����I/O�����ٶȡ�
	���Ƿ���ֱ�ӻ�������ϵͳ�����ܴ����ֻ���ڻ������ϴ󲢳��ڴ��ڣ�������Ҫ��������ʱ����ʹ�����ֻ����� 
C:wrap(byte[] array) ��������������ݻ�����byte�����У�byte�����buff�������κ�һ�������ݵĸĶ�
	����Ӱ����һ������ʵByteBuffer�ײ㱾������һ��byte���鸺��������buffer�������е����ݣ�
	ͨ��allocate����ϵͳ����㹹��һ��byte���� 
D:wrap(byte[] array, int offset, int length) 
	����һ�������Ļ����Ͽ���ָ��ƫ�����ͳ��ȣ����offsetҲ���ǰ�װ��byteBuffer��position��
	��length�ؾ���limit-position�Ĵ�С���Ӷ����ǿ��Եõ�limit��λ��Ϊlength+position(offset)

2�����õĲ���������
A:clear() position = 0;limit = capacity;mark = -1;  �е��ʼ����ζ�������ǲ���Ӱ��ײ�byte���������
B:flip() limit = position;position = 0;mark = -1;  ��ת��Ҳ������flip֮���position��limit�������
	���֮ǰ��0��position��飬��ת���ǽ�һ�����ڴ�����״̬�Ļ�������Ϊһ������׼��ȡ���ݵ�״̬  
C:hasRemaining() return position < limit �����Ƿ���δ������
D:get() ��Զ�����positionλ�ö�ȡһ��byte������position+1��Ϊ�´ζ�д��׼�� 
E:get(byte[] dst, int offset, int length) ��positionλ�ÿ�ʼ��Զ�����length��byte��
	��д��dst�±��offset��offset+length������  
F:put(byte b) ���д����position��λ��д��һ��byte������postion+1��Ϊ�´ζ�д��׼�� 
G:put(byte[] src, int offset, int length) ��src�����е�offset��offset+length����
	��ȡ���ݲ�ʹ�����дд���byteBuffer 
H:remaining() return limit - position;����limit��position֮�����λ�ò� 

*/
public class BufferDemo {

	public static void main(String[] args) throws IOException {
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		System.out.println("init status: " + buffer);
		
		buffer.put("abcd".getBytes());
		
		System.out.println("after put1: " + buffer);
		System.out.println("buffer content��"+new String(buffer.array()));
		buffer.put("���".getBytes("UTF-8"));
		System.out.println("after put2: " + buffer);
		System.out.println("buffer content��"+new String(buffer.array()));
		
		buffer.flip();//ת���ɶ�ģʽ
		System.out.println("after flip: " + buffer);
		System.out.print((char) buffer.get());
		System.out.print((char) buffer.get());
		System.out.println((char) buffer.get());
		System.out.println("after three gets: " + buffer);
		System.out.println("buffer content: " + new String(buffer.array()));
		byte[] dst=new byte[100];
		while(buffer.hasRemaining()){
			buffer.get(dst,0,buffer.remaining());
			System.out.println("Remaining: "+new String(dst,0,dst.length,"UTF-8"));
		}
	}

}
