import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
/*
 * Java NIO ServerSocketChannel
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
      

        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            System.out.println(socketChannel);
            System.out.println("conn.........."+socketChannel.getRemoteAddress().toString());
//
            ByteBuffer buf = ByteBuffer.allocate(50);
            buf.clear();
            int len=0;
            int count=0;
            while ((len = socketChannel.read(buf))!=-1)
            {	
                count +=len;
                buf.flip();
                while (buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                if(buf.limit()<buf.capacity())
                {
                	break;
                }
                buf.clear(); 
            }
            System.out.println("read from IE："+count+" 字节");
            buf.clear();

            System.out.println("输出html");

            StringBuilder sendStr = new StringBuilder();
            sendStr.append("Http/1.1 200 Ok\r\n");
            sendStr.append("Content-Type:text/html;charset=UTF-8\r\n");
            sendStr.append("\r\n");
            sendStr.append("<html><head><title>显示报文</title></head><body>你好，欢迎访问我的8080</body></html>");

            buf=ByteBuffer.wrap(sendStr.toString().getBytes("UTF-8"));
            socketChannel.write(buf);
            socketChannel.close();
        }
    }

}