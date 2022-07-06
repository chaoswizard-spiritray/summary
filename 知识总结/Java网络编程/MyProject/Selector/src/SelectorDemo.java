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
 * Selector 一般称 为选择器 ，当然你也可以翻译为 多路复用器 。它是Java NIO核心组件中的一个，
 * 用于检查一个或多个NIO Channel（通道）的状态是否处于可读、可写。
 * 如此可以实现单线程管理多个channels,也就是可以管理多个网络链接。
 * 使用Selector的好处在于： 使用更少的线程来就可以来处理通道了， 相比使用多个线程，避免了线程上下文切换带来的开销。
 * 
 * 
 * 步骤：
 * 1、Selector的创建
 * Selector selector = Selector.open();
 * 2、注册Channel到Selector
 * channel.configureBlocking(false);  //要求channel是非阻塞模式
 * SelectionKey key = channel.register(selector, Selectionkey.OP_READ);
 * register() 方法的第二个参数。这是一个“ interest集合 ”，意思是在通过Selector监听Channel时对什么事件感兴趣。
 * 可以监听四种不同类型的事件：
 * Connect
 * Accept
 * Read
 * Write
 * 通道触发了一个事件意思是该事件已经就绪。比如某个Channel成功连接到另一个服务器称为“ 连接就绪 ”。
 * 一个Server Socket Channel准备好接收新进入的连接称为“ 接收就绪 ”。
 * 一个有数据可读的通道可以说是“ 读就绪 ”。等待写数据的通道可以说是“ 写就绪 ”。
 * 这四种事件用SelectionKey的四个常量来表示：
 * SelectionKey.OP_CONNECT
 * SelectionKey.OP_ACCEPT
 * SelectionKey.OP_READ
 * SelectionKey.OP_WRITE
 * 如果你注册时对不止一种事件感兴趣，使用或运算符即可，如下：
 * SelectionKey.OP_READ | SelectionKey.OP_WRITE;
 * 3、SelectionKey
 * 一个SelectionKey键表示了一个特定的通道对象和一个特定的选择器对象之间的注册关系。
 * key.attachment(); //返回SelectionKey的attachment，attachment可以在注册channel的时候指定。
 * key.channel(); // 返回该SelectionKey对应的channel。
 * key.selector(); // 返回该SelectionKey对应的Selector。
 * key.interestOps(); //返回代表需要Selector监控的IO操作的bit mask
 * key.readyOps(); // 返回一个bit mask，代表在相应channel上可以进行的IO操作。
 * key.readyOps():
 * ready 集合是通道已经准备就绪的操作的集合。JAVA中定义以下几个方法用来检查这些操作是否就绪.
 * //创建ready集合的方法
 * int readySet = selectionKey.readyOps();
 * //检查这些操作是否就绪的方法
 * boolean isAcceptable();//是否可读，是返回 true
 * boolean isWritable()：//是否可写，是返回 true
 * boolean isConnectable()：//是否可连接，是返回 true
 * boolean isAcceptable()：//是否可接收，是返回 true
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
