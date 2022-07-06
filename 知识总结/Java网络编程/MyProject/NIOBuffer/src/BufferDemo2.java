import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/*
 * 当根据一个byte[]来生成一个固定的ByteBuffer时，使用ByteBuffer.wrap()非常实用。
 */
public class BufferDemo2 {
	public static void main(String[] args) throws CharacterCodingException, UnsupportedEncodingException {
		StringBuilder sb=new StringBuilder();
		sb.append("Hello\r\n");
		sb.append("World\r\n");
		sb.append("你好!\r\n");
		
		ByteBuffer buffer=ByteBuffer.wrap(sb.toString().getBytes());
		System.out.println(buffer);
	
	
		 while (buffer.position() < buffer.limit()) {
             //不能解析中文
//             System.out.print((char) buffer.get());
             //解析中文，不知道是否有个别乱码，缓存刚好截断utf8中文（三个字节）？
             System.out.println(Charset.forName("GBK").newDecoder().decode(buffer).toString());
         }
		
	}

}
