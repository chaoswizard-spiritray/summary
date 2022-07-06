import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/*
 * ������һ��byte[]������һ���̶���ByteBufferʱ��ʹ��ByteBuffer.wrap()�ǳ�ʵ�á�
 */
public class BufferDemo2 {
	public static void main(String[] args) throws CharacterCodingException, UnsupportedEncodingException {
		StringBuilder sb=new StringBuilder();
		sb.append("Hello\r\n");
		sb.append("World\r\n");
		sb.append("���!\r\n");
		
		ByteBuffer buffer=ByteBuffer.wrap(sb.toString().getBytes());
		System.out.println(buffer);
	
	
		 while (buffer.position() < buffer.limit()) {
             //���ܽ�������
//             System.out.print((char) buffer.get());
             //�������ģ���֪���Ƿ��и������룬����պýض�utf8���ģ������ֽڣ���
             System.out.println(Charset.forName("GBK").newDecoder().decode(buffer).toString());
         }
		
	}

}
