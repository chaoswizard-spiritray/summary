import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/*
使用缓冲区有这么两个好处：
1、减少实际的物理读写次数
2、缓冲区在创建时就被分配内存，这块内存区域一直被重用，可以减少动态分配和回收内存的次数

举个简单的例子，比如A地有1w块砖要搬到B地
由于没有工具（缓冲区），我们一次只能搬一本，那么就要搬1w次（实际读写次数）
如果A，B两地距离很远的话（IO性能消耗），那么性能消耗将会很大
但是要是此时我们有辆大卡车（缓冲区），一次可运5000本，那么2次就够了
相比之前，性能肯定是大大提高了。
而且一般在实际过程中，我们一般是先将文件读入内存，再从内存写出到别的地方
这样在输入输出过程中我们都可以用缓存来提升IO性能。

在旧I/O类库中（相对java.nio包）中的BufferedInputStream、BufferedOutputStream、
BufferedReader和BufferedWriter在其实现中都运用了缓冲区。
java.nio包公开了Buffer API，使得Java程序可以直接控制和运用缓冲区。

在Java NIO中,缓冲区直接为通道(Channel)服务，写入数据到通道或从通道读取数据，
这样的操利用缓冲区数据来传递就可以达到对数据高效处理的目的。
在NIO中主要有八种缓冲区类:除开bool类型外的7种基本类型+MappedByteBuffer，它是专门用于内存映射的一种ByteBuffer
 
所有缓冲区都有4个属性：capacity、limit、position、mark，
并遵循：mark <= position <= limit <= capacity
初始时：position = 0;limit = capacity;mark = -1; 
capacity:容量，即可以容纳的最大数据量；在缓冲区对象创建时被设定并且不能改变
limit:缓冲区读和写的极限位置，且极限是可以修改的。
position：下一个要被读或写的元素的位置，每次读写缓冲区数据时都会改变改值，为下次读写作准备
mark：标记，调用mark()来设置mark=position，再调用reset()可以让position恢复到标记的位置

1、实例化

java.nio.Buffer类是一个抽象类，不能被实例化。
Buffer类的直接子类，如ByteBuffer等也是抽象类，所以也不能被实例化。
但是ByteBuffer类提供了4个静态工厂方法来获得ByteBuffer的实例：

A:allocate(int capacity) 从堆空间中分配一个容量大小为capacity的byte数组作为缓冲区的byte数据存储器 
B:allocateDirect(int capacity) 是不使用JVM堆栈而是通过操作系统来创建内存块用作缓冲区，
	它与当前操作系统能够更好的耦合，因此能进一步提高I/O操作速度。
	但是分配直接缓冲区的系统开销很大，因此只有在缓冲区较大并长期存在，或者需要经常重用时，才使用这种缓冲区 
C:wrap(byte[] array) 这个缓冲区的数据会存放在byte数组中，byte数组或buff缓冲区任何一方中数据的改动
	都会影响另一方。其实ByteBuffer底层本来就有一个byte数组负责来保存buffer缓冲区中的数据，
	通过allocate方法系统会帮你构造一个byte数组 
D:wrap(byte[] array, int offset, int length) 
	在上一个方法的基础上可以指定偏移量和长度，这个offset也就是包装后byteBuffer的position，
	而length呢就是limit-position的大小，从而我们可以得到limit的位置为length+position(offset)

2、常用的操作方法：
A:clear() position = 0;limit = capacity;mark = -1;  有点初始化的味道，但是并不影响底层byte数组的内容
B:flip() limit = position;position = 0;mark = -1;  翻转，也就是让flip之后的position到limit这块区域
	变成之前的0到position这块，翻转就是将一个处于存数据状态的缓冲区变为一个处于准备取数据的状态  
C:hasRemaining() return position < limit 返回是否还有未读内容
D:get() 相对读，从position位置读取一个byte，并将position+1，为下次读写作准备 
E:get(byte[] dst, int offset, int length) 从position位置开始相对读，读length个byte，
	并写入dst下标从offset到offset+length的区域  
F:put(byte b) 相对写，向position的位置写入一个byte，并将postion+1，为下次读写作准备 
G:put(byte[] src, int offset, int length) 从src数组中的offset到offset+length区域
	读取数据并使用相对写写入此byteBuffer 
H:remaining() return limit - position;返回limit和position之间相对位置差 

*/
public class BufferDemo {

	public static void main(String[] args) throws IOException {
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		System.out.println("init status: " + buffer);
		
		buffer.put("abcd".getBytes());
		
		System.out.println("after put1: " + buffer);
		System.out.println("buffer content："+new String(buffer.array()));
		buffer.put("你好".getBytes("UTF-8"));
		System.out.println("after put2: " + buffer);
		System.out.println("buffer content："+new String(buffer.array()));
		
		buffer.flip();//转换成读模式
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
