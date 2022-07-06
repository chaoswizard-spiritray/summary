import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/*
 * Selector һ��� Ϊѡ���� ����Ȼ��Ҳ���Է���Ϊ ��·������ ������Java NIO��������е�һ����
 * ���ڼ��һ������NIO Channel��ͨ������״̬�Ƿ��ڿɶ�����д��
 * ��˿���ʵ�ֵ��̹߳�����channels,Ҳ���ǿ��Թ������������ӡ�
 * ʹ��Selector�ĺô����ڣ� ʹ�ø��ٵ��߳����Ϳ���������ͨ���ˣ� ���ʹ�ö���̣߳��������߳��������л������Ŀ�����
 * 
 * 
 * ���裺
 * 1��Selector�Ĵ���
 * Selector selector = Selector.open();
 * 2��ע��Channel��Selector
 * channel.configureBlocking(false);  //Ҫ��channel�Ƿ�����ģʽ
 * SelectionKey key = channel.register(selector, Selectionkey.OP_READ);
 * register() �����ĵڶ�������������һ���� interest���� ������˼����ͨ��Selector����Channelʱ��ʲô�¼�����Ȥ��
 * ���Լ������ֲ�ͬ���͵��¼���
 * Connect
 * Accept
 * Read
 * Write
 * ͨ��������һ���¼���˼�Ǹ��¼��Ѿ�����������ĳ��Channel�ɹ����ӵ���һ����������Ϊ�� ���Ӿ��� ����
 * һ��Server Socket Channel׼���ý����½�������ӳ�Ϊ�� ���վ��� ����
 * һ�������ݿɶ���ͨ������˵�ǡ� ������ �����ȴ�д���ݵ�ͨ������˵�ǡ� д���� ����
 * �������¼���SelectionKey���ĸ���������ʾ��
 * SelectionKey.OP_CONNECT
 * SelectionKey.OP_ACCEPT
 * SelectionKey.OP_READ
 * SelectionKey.OP_WRITE
 * �����ע��ʱ�Բ�ֹһ���¼�����Ȥ��ʹ�û���������ɣ����£�
 * SelectionKey.OP_READ | SelectionKey.OP_WRITE;
 * 3��SelectionKey
 * һ��SelectionKey����ʾ��һ���ض���ͨ�������һ���ض���ѡ��������֮���ע���ϵ��
 * key.attachment(); //����SelectionKey��attachment��attachment������ע��channel��ʱ��ָ����
 * key.channel(); // ���ظ�SelectionKey��Ӧ��channel��
 * key.selector(); // ���ظ�SelectionKey��Ӧ��Selector��
 * key.interestOps(); //���ش�����ҪSelector��ص�IO������bit mask
 * key.readyOps(); // ����һ��bit mask����������Ӧchannel�Ͽ��Խ��е�IO������
 * key.readyOps():
 * ready ������ͨ���Ѿ�׼�������Ĳ����ļ��ϡ�JAVA�ж������¼����������������Щ�����Ƿ����.
 * //����ready���ϵķ���
 * int readySet = selectionKey.readyOps();
 * //�����Щ�����Ƿ�����ķ���
 * boolean isAcceptable();//�Ƿ�ɶ����Ƿ��� true
 * boolean isWritable()��//�Ƿ��д���Ƿ��� true
 * boolean isConnectable()��//�Ƿ�����ӣ��Ƿ��� true
 * boolean isAcceptable()��//�Ƿ�ɽ��գ��Ƿ��� true
 * 
 *   
 * 
 * 
 */
public class SelectorDemo {

	public static void main(String[] args) throws IOException  {
		ServerSocketChannel ssc=ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress("localhost", 8080));
		ssc.configureBlocking(false);
		Selector selector=Selector.open();
		SelectionKey sk=ssc.register(selector, SelectionKey.OP_ACCEPT);
		int i=0;
		while(true){
			if(selector.select()<=0)
				continue;
			Set<SelectionKey> sks=selector.selectedKeys();
			Iterator<SelectionKey> it=sks.iterator();
			while(it.hasNext()){
				SelectionKey key=it.next();
				it.remove();
				try{
					if(key.isAcceptable()){
						System.out.println(i++);
						SocketChannel sc=ssc.accept();
						System.out.println(sc);
						if (sc != null) {
							sc.configureBlocking(false);
							sc.register(selector, SelectionKey.OP_READ);
						}

					}
					if(key.isReadable()){
						doRead(key);
					}
				}catch(IOException e){
					e.printStackTrace();
				}
				
			
			
			}
		}
		
	}

	private static void doRead(SelectionKey key) {
		ByteBuffer readBuf=ByteBuffer.allocate(1024);
		SocketChannel sc=(SocketChannel) key.channel();
		String content="";
		int len=0;
		try {
			while((len=sc.read(readBuf))>0){
			
			
			readBuf.flip();
			System.out.println(readBuf);
			if(readBuf.position()<readBuf.limit())
				content+=Charset.forName("UTF-8").newDecoder().decode(readBuf).toString();
	         
			
			readBuf.clear();
			
			}
			System.out.println(content);
			ByteBuffer writeBuf=ByteBuffer.wrap(content.getBytes("UTF-8"));
			sc.write(writeBuf);
			sc.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		key.cancel();
		
	}

}
