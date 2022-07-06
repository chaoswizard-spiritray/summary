import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {

	public static void main(String[] args) throws IOException {
		SocketChannel sc=SocketChannel.open(new InetSocketAddress("localhost", 8080));
		//sc.configureBlocking(true);
		ByteBuffer buffer=ByteBuffer.wrap("Hello Good 陈晓军公司股东是,chenxiaojun".getBytes("UTF-8"));
		sc.write(buffer);
		
		ByteBuffer readBuf=ByteBuffer.allocate(1024);
		
		String content="";
		int len=0;
		try {
			while((len=sc.read(readBuf))>0){
			
			
			readBuf.flip();
			System.out.println(readBuf);
			if(readBuf.position()<readBuf.limit()){
				content+=Charset.forName("UTF-8").newDecoder().decode(readBuf).toString();
				
			}
			
			readBuf.clear();
			
			}
			System.out.println(content);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.close();
		
	}
		



}
