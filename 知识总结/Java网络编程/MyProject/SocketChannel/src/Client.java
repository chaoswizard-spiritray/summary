import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Client {

    public static void main(String[] args) throws Exception {
//        String url = "ifeve.com";
        String url = "www.baidu.com";
//        String url = "blog.csdn.net";
//        String url = "www.xhu.edu.cn";


        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(url, 80));
        System.out.println("conn..............");

        while (true) {
            System.out.println("whild.................");
            if (socketChannel.isConnected()) {
                System.out.println("pending.....................");
                //����http
                StringBuilder sendStr = new StringBuilder();
                sendStr.append("GET / HTTP/1.1\r\n");
                sendStr.append("Host: "+url+"\r\n");
                //gzipѹ��
//                sendStr.append("Accept-Encoding: gzip, deflate, br\r\n");
                //httpЭ�飬��Ҫ��һ��
                sendStr.append("\r\n");

                ByteBuffer buf = ByteBuffer.wrap(sendStr.toString().getBytes("UTF-8"));
                int bytesWrite = socketChannel.write(buf);
                System.out.println(bytesWrite);
                buf.clear();

                int read = 0;
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while ((read = socketChannel.read(buffer)) != -1) {
                    {
                        System.out.println("read " + read + ".....................");
                        buffer.flip();
                        while (buffer.position() < buffer.limit()) {
                            //���ܽ�������
//                            System.out.print((char) buffer.get());
                            //�������ģ���֪���Ƿ��и������룬����պýض�utf8���ģ������ֽڣ���
                            System.out.println(Charset.forName("UTF-8").newDecoder().decode(buffer).toString());
                        }
                        //#todo ��Ҫ����body���ȣ�����û����һ����������,html�ĵ�û����
                        if (buffer.limit() < buffer.capacity()) {
                            break;
                        }
                        buffer.clear();
                    }
                }
            }
            socketChannel.close();
            break;
        }
    }
}