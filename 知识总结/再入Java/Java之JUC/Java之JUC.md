# Java之JUC

- JUC:java.util.concurrent

## 线程与进程

- 普通使用线程
  - Thread
  - Runnable接口，没有返回值，效率相比Callable较低
  - java.util.concurrent.Callable
- 进程：程序的机内运行的状态
  - 一个进程至少包含多个线程
  - Java默认有两个线程：主线程、GC线程
- 线程：
  - 程序执行的最小单位
  - Java中线程的六大状态：
    - new:新建
    - runnable：运行
    - blocked：阻塞
    - waiting:等待（死等）
    - timed_waiting：超时等待
    - terminated：终止、死亡
- Java不能开启线程：调用C++操作底层硬件进行线程开启。

### 并发与并行

- 并发：同个时间片，多个任务交互执行

  - 有限资源下模拟并行，宏观并行，微观并发。

  - 目的是想充分利用计算机cpu有限的资源

    ```java
    System.out.println(Runtime.getRuntime().availableProcessors())
    //获取cpu的核数
    ```

    

- 并行：同个时间片，多个任务同时进行

### wait和sleep区别

- 来自不同类，wait来自Object ;sleep来自Thread
- wait进入阻塞时释放锁，sleep进入阻塞时不释放锁。
- 使用范围
  - wait：只能在同步代码块中进行调用，有对象才能等。
  - sleep：只要有线程，线程本身只要想调用就调。
- wait不需要捕获异常
- sleep必须捕获异常

## Lock锁

- 锁是什么？

  - synchronized中生产者消费者中存在的问题

    - 线程可以在没有被通知，中断或超时的情况下唤醒 ，即所谓的*虚假唤醒* 。  虽然这在实践中很少发生，但应用程序必须通过测试应该导致线程被唤醒的条件来防范它，并且如果条件不满足则继续等待。（**意思就是说下次还可能是这个线程抢到这把锁，然后接着阻塞前的状态执行，假如之前有沉睡条件，那么这次就直接执行剩余代码，不会进行判断，所以这个最好醒的时候再判断一次，所以沉睡条件最好使用while进行判断。**）这个和双重锁检查有差不多的意思。

  - Lock代替waiti与notify的方法

    - lock.newCondition():创建以一个condition对象该对象提供的await（）代替wait()
    - condition.singnalAll()：唤醒全部

  - 总结下：Lock代替synchronized，condition代替锁对象。注意锁应该是依赖于锁对象的.

  - 所以既然只是封装，优势在哪里？

    - **Condition能精准通知和唤醒线程**

      ```java
      //有下面这个类
      class A{
          private Lock lock=new ReentrantLock();
          //实现方式就是主动创建多个锁对象，每个方法各自用一个锁对象，要唤醒谁上面的线程，直接将上面的线程进行清除即可。用notify也可以代替。
          //本质就是锁利用的是每个对象上的monitor的使用权，而这个使用权只有一个
          Condition condition1=lock.newCondition();
          Condition condition2=lock.newCondition();
          Condition condition3=lock.newCondition();
          //应该是一把锁维护了一个锁对象数组，创建一个就加入，删除之后，可以仅从指定对象上锁。
          public void a(){
              lock.lock();
              while(condition){
                  condition1.await()
              }
              try{
              //逻辑代码
              ....
              //唤醒锁对象2上
              condition2.singnalAll();
              }finally{
                  unlock
              }
          }
          public void b(){
              lock.lock();
              while(condition){
                  condition2.await()
              }
              try{
              //逻辑代码
              ....
              //唤醒锁对象2上
              condition3.singnalAll(); 
                      }finally{
                  lock.unlock();
              }
          }
          public void c(){
              lock.lock();
              while(condition){
                  condition3.await()
              }
              try{
              //逻辑代码
              ....
              //唤醒锁对象1上
              condition1.singnalAll();
                      }finally{
                  lock.unlock();
              }
          }
      }
      
      //有如下需求，a-b-c顺序执行
      public class Main{
          public static void main(String[]args){
              A a=new A();
              new Thread(()->{a.a()}).start();
              new Thread(()->{a.a()}).start();
              new Thread(()->{a.a()}).start();
          } 
      }
      ```

      

- 先说明一一些问题

  - 线程应该只是一个资源类，它仅仅进行用来运行一个代码逻辑，而实际代码逻辑不应该和线程本身耦合。
    - 比如：买票，提供一个买票类本身提供一个买票逻辑。
    - 这个不要去继承线程或者实现接口，线程是一个资源不要和任何的执行逻辑进行耦合
    - 当你要执行时逻辑时，只需要开启多个线程然后将这个逻辑进行注入执行即可。（比如创建一个专门的runnable对象，重写方法时进行调用即可。相当于抽象了一层专门用来连接线程和逻辑的类，这层可以复用。）

- 传统的synchronized

  - 本质：就是提供一个绝对使用权标识，抢到标识的就能使用资源。

  - 有两种，锁对象和锁class

    - 锁对象：锁代码块或是锁非静态方法（以实例化对象作为锁）
    - 锁class：锁静态方法（因为没有实例化，通过class对象进行调用方法，所以锁class对象）

  - wait:释放锁（让该对象上的线程进行等待）

  - notify:唤醒锁（通知要这个锁的线程进行抢占）

  - 什么时候会释放锁：

    - 1、当前线程的同步方法、代码块执行结束的时候释放

      2、当前线程在同步方法、同步代码块中遇到break 、 return 终于该代码块或者方法的时候释放。

      3、出现未处理的error或者exception导致异常结束的时候释放

      4、程序执行了 同步对象 wait 方法 ，当前线程暂停，释放锁

- Lock本身是个接口，有三个子类

  - ReentrantLock(可重入锁/可以重复使用的锁)
    - 该类实例化是有两种类型，区别就是线程上锁的策略（游戏规则）
    - NonfairSync公平锁-排队，先来后到
    - FairSync非公平锁-默认实例化，抢占式/优先级
  - ReentrantReadWriteLock.ReadLock(读锁)
  - ReentrantReadWriteLock.WriteLock（写锁）

- 使用步骤

  ```java
  class A{
      private Lock lock;//先持有一把锁
      //要锁的方法
      public void L(){
         //将lock上锁
          lock.lock();
          try{
          //执行代码逻辑，runnable没法接收返回值
          }catch(){
              
          }
          finally{
             //释放锁
             lock.unlock()
          }
      }
  }
  ```

  

- 传统锁与Lock的区别:

  - 实际上可以这么理解，Lock将我们用synchronized常用的一些情况以及操作进行封装为一个类
  - 一个关键字，一个类
  - synchronized无法判断锁的状态，Lock可以判断是否获取到锁
  - synchronized自动释放锁，Lock必须手动释放，锁不释放很可能产生死锁。
  - synchronized，当一个线程阻塞时，如果不手动的调用wait方法，其他需要这个资源只有一直等待，资源就浪费了。而Lock的tryLock()可以尝试锁，不一定会等待。
  - synchronized可重入锁，不可以中断的，非公平（抢占式）。Lock,可重入，可以判断锁，非公平（可以自己选择）。Lock灵活高
  - synchronized适合锁少量的代码，Lock适合大量的同步代码。

- 使用TimeUnit代替传统线程的sleep();

## 安全的集合类

### 集合类不安全

- 我们将集合类作为存放资源的容器，要拿取资源必须通过容器操作。那么在容器作为公共资源时，如果不加锁，多线程并发情况下必然会产生问题。

- 并发修改异常
  - java.until.ConcurrentModificationExeception
- Collection的子类
  - List:列表
  - Set：集合
    - HashSet底层：就是hashmap的key。key是键唯一的无法重复
    - TreeSet
  - Map：图
    - HashMap:1.7之前，使用的是hash数组，后面使用的拉链模式，以及红黑树。
      - 常用含参构造：加载因子（0.75）、初始化容量(16)
    - TreeMap:
  - BlockingQueue：阻塞队列
- 不手动加锁的解决方案
  - 使用Vector
  - 使用List list=Collections.synchronizedList(new ArrayList());将不安全转换为安全的。
    - 这个函数的作用就是将不安全的list转换为CopyOnWriteArrayList()这个安全的的。Collections提供那个方法主要是为了保证你原来程序的扩展性。
- 其他同理

### 并发安全的集合类

- CopyOnWriteArrayList：写入时复制数组
  - CopyOnWrite：COW,计算机程序设计领域的一种优化策略
  - 多线程读取的资源的时候，直接源资源中进行读取。写入的时候拷贝一份（写完了再将原数据进行更新）。也叫做读写分离，就是mysql读写分离进行集群差不多。
  - 为什么用COWA不用Vector，Vector使用的是synchronized，而COWA使用的是Lock，效率高
  - 具体添加元素的逻辑：
    - 先创建一个局部的Lock进行上锁
    - 然后创建一个数组并将原数据进行拷贝一份
    - 然后将新数组交给list
    - 释放锁
- 同理有
- CopyOnWriteSet
- ConcurrentHashMap



##Callable接口

- 和runnable一样，其实例都可能作为线程的运行对象。

- 与runnable的区别：

  - 可以有返回值
  - 可以抛出被检查的异常
  - 重写方法不同，这里重写call

  ```java
  public class S implements Callable<String>{//泛型对应方法的返回值
      @Override
      public String call(){
          return ss;
      }
  }
  ```

- 如何运行callable实例，因为thread的构造函数只能接收runnable实例。

  - 我们知道runnable接口的实现类有

    - Thread
    - FutureTask
    - ....

  - 而FutureTask的构造函数有两个

    - 创建一个 `FutureTask` ，在运行时执行给定的 `Runnable`  ，并安排 `get`在成功完成后返回给定的结果。 

      参数 

      `runnable` - 可运行的任务 

      `result` - 成功完成后返回的结果。  如果您不需要特定结果，请考虑使用以下形式的结构： `Future<?> f = new  FutureTask<Void>(runnable, null)` 

    - 另一个就是传入一个Callable实例

  - 那么我们可以使用Callable对象构造FutureTask，然后将这个FurtureTask交给Thread进行运行（因为FutureTask也实现了runnable接口）。

    - 好，既然这样，说明最终运行的肯定是FutureTask自己重写的run(）。而这个类有runnable对象或者有callable对象，所以会有一个封装方法确定具体调用过程，重写的run就是调用这个方法。
    - 它其实就是运用了一个适配器模式，因为Thread无法直接调用Callable实例，但是这个对象适配器通过注入一个目标对象，并重写使用者的方法就能够进行调用。

- 获取返回结果

  - 通过FutureTask的get方法进行获取
  - **这个方法可能会产生阻塞**，因为它会一直等待处理结果，所以建议将其放在代码的最后或者使用异步通信来处理。
  - **注意这个结果会被缓存**
  - 异步通信：就是说直接新开一个线程然后执行等待，当前线程必须要这个结果时再去获取。
    - CompletableFuture

### JUC三大常用辅助类

#### CountDownLatch：减法计数器

- 允许一个或多个线程等待**直到在其他线程中执行的一组操作完成的**同步辅助类
- 可以说就是一个OS中的信号量，不过Java让它的操作具有原子性
- 那么就是说这个公共资源，它的操作是原子性，我们如果自己写信号量还需要考虑多线程问题
- 主要使用的方法
  - CountDownLatch(6):创建6个信号量
  - countDown（）：信号量减一
  - await（）：在当前线程中的当前调用位置等待计数器归零，归零后当前线程才能继续往下执行。（和join有些类似，但是join是要等待线程停止，这个更具有标识性）
- 当调用await()方法执行到await处时就会休眠，当其他线程执行countDown()直到为0时，await线程才会被唤醒继续执行。

#### CyclicBarrier：加法计数器

- 允许一组线程全部等待彼此到达共同屏障点的同步辅助，循环阻塞在涉及固定大小的线程方法的程序中很有用，这些线程必须偶尔等待彼此，屏障被称为循环，因为它可以在等待的线程被释放之后重新使用。
  - 解释下：**就是和赛跑组一样，终点就是一个屏障，只有全部人通过了才会才会终点才会解除**
- 一个CyclicBarrier支持一个可选的Runnable命令，每个屏障点运行一次，在派对中的最后一个线程到达之后，但在任何线程释放之前。在任何一方继续进行之前，此屏障操作对更新共享状态很有用。

- 使用方法：

  - CyclicBarrier(7,()->{System.out.println("sss")}):创建一个屏障，当有七个线程调用await时才会执行这个异步方法
  - await:该线程到达屏障，屏障计数加一

- 使用这个也可以进行简单的异步通知

  ```java
  public static void main(String[] args) {
  		//创建一个屏障
  		CyclicBarrier cyclicBarrier=new CyclicBarrier(7, ()->{System.out.println("haohao");});
  		//开启一组线程
  		for (int i = 0; i <7; i++) {
  			final int  j=i;
  			new Thread(()->{System.out.println(j);try {
  				cyclicBarrier.await();
  			} catch (InterruptedException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			} catch (BrokenBarrierException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}}).start();;
  		}
  		//剩余内容
  		System.out.println("caoacoacoa");
  	}
  /**
  运行结果：
  0
  2
  3
  caoacoacoa
  1
  6
  5
  4
  haohao
  //说明只有这组线程才会彼此等待，其他线程不会。
  **/
  ```

  

#### Semaphore

- 计数信号量。 从概念上讲，信号量保持一组许可。 如果有必要，每个[`acquire()`都会](#acquire())阻止，直到有许可证，然后接受。 每个[`release()`都](#release())添加了许可证，可能会释放阻止收购者。  但是，没有使用实际的许可对象; `Semaphore`只保留可用数量并相应地采取行动。 
- 解释下：**如果说上面两个类都只是用了信号量的一部分，那么这个类就是一个完整的信号量实现，可以加可以减**。完全用于一个资源使用权的分配与回收。有限资源分配标识。
- 使用：
  - Semaphore(6)：资源初始量
  - acquire()：请求资源，如果没有资源该线程就会进行等待直到有资源可以使用，如果请求到了该信号量就会减一
  - release()：释放资源，该线程会释放该资源的使用权，信号量加一
- 可以用于并发限流、多个并发资源互斥

## 读写锁

- **`ReadWriteLock维护一对关联的locks`**,一个用于只读操作，一个用于只写操作。read lock可以由多个阅读器线程同时进行，只要没有写入操作。write lock只能用于一个线程单独进行写操作。
- 用法与Lock一样，先创建一个读写锁；要读就加读锁，然后编写逻辑代码，在finally中进行解锁就行。
- 读锁与写锁之间是互斥的，如果有写锁就不能进行读取，要写完才能读取，反之亦然。
- 独占锁：写锁
- 共享锁：读锁
- 情况：
  - 读读：可以
  - 读写：不行
  - 写写：不行

## 阻塞队列BlockingQueue

- 就是一个队列进行资源存放，先进先出策略。但是没有资源时拿取操作会被阻塞，资源满时写入操作会被阻塞。

- 使用场景：

  - 生产者消费者、线程池、多线程并发 处理

- 队列架构

  ![](img\20220309132108.jpg)

### 阻塞队列四组API

| 操作         | 抛出异常  | 有返回值无异常 | 阻塞等待(等到死) | 超时等待，指定等待时间，超时返回 |
| ------------ | --------- | -------------- | ---------------- | -------------------------------- |
| 添加         | add()     | offer          | put              | offer                            |
| 删除         | remove()  | poll           | take             | poll                             |
| 判断队首元素 | element() | peek           | -                | -                                |

### SynchronousQueue同步阻塞队列

- 同步就是双方实时通信，没有容量进行缓存，进去一个元素，必须等待取出来之后，才能再往里面放一个元素。

## 线程池

>池化技术

- 计算机的硬件资源有限，而每种资源的申请和释放都会耗费额外的资源，为了提高效率以及节省资源，提前申请合适数量的资源，进行统一化管理（分配与回收），减少额外消耗，提高资源复用率，提高程序性能。池化技术就是这样。

- 如线程池、对象池、连接池、内存池......

- **线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这
  样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。**

- 线程池好处：
  - 降低资源的消耗（开启释放线程都需要资源）
  - 提高响应速度
  - 方便管理
  - 资源复用
  
- 线程池必会：3大方法、7大参数、4大拒绝策略

  - 三大方法：使用Executors创建一个线程池

    - newSingleThreadExecutor()：创建只有一个线程的线程池
    - newFixedThreadPool(6);创建指定数目线程的线程池
    - newCachedThreadPool():创建可变容量的线程池
    - shutdown():关闭线程池
    - 这三个创建函数本质都是通过ThreadPoolExecutor 进行创建。

  - 七大参数：ThreadPoolExecutor调用时可以传递的七个参数

    - `int corePoolSize`：核心线程，默认Integer.max
    - ` int maximumPoolSize` ：最大线程数
    - ` long keepAliveTime` ：空闲线程保持活跃时间
    - `TimeUnit unit`:活跃时间单位
    - `BlockingQueue<Runnable> workQueue`：任务队列
    - `ThreadFactory threadFactory`：线程工厂
    - ` RejectedExecutionHandler handler`：拒绝策略

    - 参数之间的关系：线程池中至少会保持核心线程池数目的线程活跃；当任务过多就会放入任务队列；如果任务队列已经满了；就开启新的线程，且这个时候线程过期计时开启，这时所有活跃的线程都会处理任务队列中的任务；如果任务还是很多，队列满的，那么就会持续开启线程，直到线程数目到最大值；当线程数到最大值时，如果还有任务提交，那么就会触发拒绝策略。

  - 四种阻塞队列

    - 1、ArrayBlockingQueue
      是一个基于数组结构的有界阻塞队列，此队列按 FIFO（先进先出）原则对元素进行排序。
      2、LinkedBlockingQueue
      一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，**吞吐量通常要高于ArrayBlockingQueue**。静态工厂方法Executors.newFixedThreadPool()使用了这个队列
      3、SynchronousQueue
      一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool（5）使用了这个队列。
      4、PriorityBlockingQueue
      一个具有优先级的无限阻塞队列。
      5、这几种队列都继承自AbstractQueue并且实现了BlockingQueue接口，所以我们也能自定义工作队列

  - 四种拒绝策略：

    - 1、 AbortPolicy ： 直接抛出异常，阻止系统正常运行。默认是该策略。(不断将任务提交到工作队列，当工作队列满时，停止提交，
          并立即抛出InterruptedException对象，如果没有进行try-cathch进行处理停止当前提交任务的线程正常运行。)

      2、 CallerRunsPolicy ： 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提交线程的性能极有可能会急剧下降。
         （比如从main线程中进行任务的提交，如果提交已经满了任务队列，就将这个任务交给main线程进行运行。）

      3、 DiscardOldestPolicy ： 丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
          （）

      4、 DiscardPolicy ： 该策略默默地丢弃无法处理的任务，不予任何处理。如果允许任务丢失，这是最好的一种方案。

    - 注意这四种拒绝策略都是ThreadPoolExecutor的内部类，所以需要通过线程执行者进行获取并创建实例。

## Cpu密集与IO密集

- [什么是CPU密集型、IO密集型？_Java技术栈，分享最主流的Java技术-CSDN博客_io密集型】](https://blog.csdn.net/youanyyou/article/details/78990156)
- 密集=大量
- 这文章大致是说
  - cpu bound:就是计算机大量都是在运行计算指令，很少需要外界进行数据的输入输出。或者是说输入输出被屏蔽掉。
    - 一个方法内部的逻辑，主要是需要序列化反序列化、加密解密、向量计算、大量循环判断和加减乘除运算，这些都是典型的CPU密集型场景。（Jvm垃圾回收器也是）
  - io bound：就是计算机大量都是在进行读写数据或者是说等待数据交互。基本不用对数据进行大量的复杂运算
- 对于cpu 密集程序，线程的数目最好等于cpu数目效率最高。（**目的就是减少线程之间的切换的损耗**）
- 对于io密集，线程数目

- Runtime.getRuntime().availableProcessors()

- **[如何理解IO密集和cpu密集？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/21276851/answer/17736231)**

  

## 四大函数式接口

- **有且仅有一个`抽象方法`的接口**,因为接口现在是可以指定方法体和属性的。
  - @FunctionalInterface
    放在接口定义的上方，表示该接口是函数式接口
    接口有且仅有一个抽象方法
  - 注意
    定义函数式接口的时候，@FunctionalInterface是可选的，就算不写这个注解，只要保证满足函数式接口定义的条件，也照样是函数式接口。
- 四种类型函数式接口
  - 函数型接口，有输入参数，和一个返回值
    - 如：Function：
  - 断定型接口，有输入参数，一个boolean返回值
    - 如Predicate：
  - 消费型接口：只有输入，没有返回值
    - 如Consumer
  - 供给型接口：没有输入，只有返回值
    - 如Supplier

- 函数式接口的作用就一个简化编程，简化代码量



## Stream流式计算

- 什么式Stream流式计算
  - 现在的程序就是做两件事：存储和计算
  - 我们知道使用面向对象可以比较好的划分系统和管理维护系统，但是面向对象使得代码的逻辑操作调用变得很复杂，而面向过程都是函数所以调用很方便。引入函数式接口就是简化Java代码的书写，提高开发效率。
- Java中::,相当于引用，可以理解为通过对象名/类名::方法名/静态方法名获得方法的内存地址

## ForkJoin

- [ForkJoinPool使用介绍 - myseries - 博客园 (cnblogs.com)](https://www.cnblogs.com/myseries/p/12582271.html)
- 有原理的疑问就看这篇

  - [介绍 ForkJoinPool 的适用场景，实现原理_大大肉包博客-CSDN博客_forkjoinpool](https://blog.csdn.net/m0_37542889/article/details/92640903)
- 分支合并，这个是一种任务处理方式
- 并行执行任务，提高效率，用于处理大数据量。（分治法）
- 先将大任务拆分为小任务后，最后将处理结果进行逐步合并得到最终结果
- 大致原理-任务窃取：

  - 在线程池中维护了多个并行的线程，每个线程中维护了一个双端任务队列，每个线程在执行任务时会尝试从其他线程的工作队列中或者线程池的任务队列中窃取一个任务。
  - 线程每次执行自己队列中的任务与分解任务进行存放时都是操作队尾。
- 主要涉及的类

  - ForkJoinPool:分支合并池-他是Executor的实现类，他会创建指定数目的**并行**线程。

  - ForkJoinTask：分支合并任务

    - 子类RecursionAction：没有返回值
    - RecursionTask:有返回值
    - 一般在池中的任务都是使用RecursionTask，要使用必须先继承这个类。
    - 该类的fork()方法是将该任务提交到当前线程的工作队列的队尾。
    - join会等待任务的结果，如果这个任务还没有执行完，那么该线程会先执行其他任务。
    - 当没有线程池中没有任务其他线程也没有能窃取的任务时就会进入休眠。
- 使用方法
  - 创建一个类继承ForkJoinTask的子类
  - 重写compute方法，在任务中你可以进行任务分解，然后每个子任务调用fork方法，将任务加入到自己的工作队列中（双端队列），或者是使用submit()将任务提交到当前使用的线程池中，然后使用join()方法异步接收子任务处理的结果。

## 异步回调

- 什么是回调函数？
  - **a->b->c->a（a调用b方法，并给b传入一个定义好的c方法，c这个方法往往是与a存在某种联系的。当b处于某种状态时，就会调用c，c会告诉a，b现在处于的情况），c就是一个回调函数**
  - 我们自己定义的函数，自己不调用，给别人调用。
  - 回调：俗话就是一个电话号码。术语就是--**服务调用者提供给服务提供者的一种反馈方式，服务提供者一定会执行自己的逻辑，但是执行中可能产生不确定情况需要和调用者反馈**
  - 比如：交互面板上请求登录验证，验证过程中，可能断网，可能错误，可能超时，这就需要服务调用展示面板反馈情况。
  - **回调可以是同步**（比如单线程运行，要你实时监控服务的执行过程，当执行到某一时，要你选择，根据不同选择服务运行不同的路线，**就像玩单机角色游戏样**），**可以是异步**(**比如主线程做饭，子线程烧水，主线程切菜，水冒烟了，主线程就知道可以下料了。**)。

- [Java基础(番外) 为什么匿名内部类只能访问final类型局部变量_戏流年的博客-CSDN博客](https://blog.csdn.net/xiliunian/article/details/101620786)

- [Future (icode9.com)](https://www.icode9.com/content-4-370408.html)
  
  - jdk8后匿名内部类访问的变量会被默认声明为final，一旦变化就会报错。
  
- 我们知道runnable接口是没有返回值和输入参数的，所以出现了future和callable接口。当线程池执行提交futuretask时，返回future对象。只有当future对象主动调用get方法时，当前线程才会主动去子线程中去取执行结果，如果子线程还没有执行完，就会一直等待。但是在没有调用get之前当前线程都可以做自己的事情。

- 可以知道future这个get方法不是异步回调，因为我们需要主动去调用get，我们更希望有一个异步回调的方式，当完成时，或者是线程出现了错误等等都能够按照我们预先定好的处理方式进行通知。

- Futrue接口
  - **cancel，取消Callable的执行，当Callable还没有完成时**
  - **get，获得Callable的返回值**
  - **isCanceled，判断是否取消了**
  - **isDone，判断是否完成**

- 回调实现的方式一
  - 使用Callback，CallbackHandler接口:实质就是形成一个闭包。
  - 使用方式：执行者实现Callback接口，然后在执行者的主执行逻辑中调用服务者（可以实现CallbackHandler接口，没啥大区别），在调用服务时将调用者传入执行者，执行者需要通知时调用accept方法就行了。
  - 但是这种方式没有返回值，所以在多线程基本没用（最多用于请求输入和输出文件网络通信，但是直接返回处理结果不行，要返回需要借助调用者其他方法或属性），或者用于单线程解耦。
  
- 回调方式二：

  - 使用Future的实现类

  - Future类本就是为线程异步通信，获取返回值而生。

    ```java
    //使用CompletableFuture,用于异步回调，类似Ajax
    //主要掌握三种
    //异步执行
    //成功回调
    //失败回调
    /**
    completableFuture.runAsync();有两种方法，一种由默认线程池执行一个runnable任务，一种将提交的任务交给线程池进行执行，两种返回均是一个新的completable对象。
    ----------------------------------------------
    completableFuture.get();//阻塞获取异步任务的返回值，这里相当于get方法是一个主动检测获取值，算不上异步回调。
    ----------------------------------------------
    completableFuture.supplyAsync();同样有两种方法，一种是传入一个Supplier实例整合到一个异步任务中使用默认线程池执行，或是交由指定线程池执行。
    supplyAsync()与runAsync()线程的执行逻辑差别不大，主要是前者Java为了避免使用后者，我们需要去创建FutureTask的麻烦，将供给型接口封装为一个Runnable对象。
    public interface Supplier<T> {
        T get();
        }
    Supplier实例是一个供给型接口
    ----------------------------------------------
    completableFuture.whenComplete((t,u)->{t是执行返回值;u是执行失败的错误信息});返回一个CompletableStage实例，这个方法相当于tyr-catch中的finally一样，就是只要这个线程结束了，不管是异常还是正常结束都会执行这个函数。
    ----------------------------------------------
    completableFuture.exceptionally(执行异常执行的函数，这是一个函数型接口，必须要有返回值);
    ----------------------------------------------
    是异步计算的阶段，在另一个CompletionStage完成时执行操作或计算阶段在其计算终止时完成，但这又可以触发其他依赖阶段。 此接口中定义的功能仅使用几种基本形式，这些形式扩展为更大的方法集以捕获一系列使用方式： 
    由stage执行的计算可以表示为Function，Consumer或Runnable（使用名称分别包括apply ， accept或run的方法 ），具体取决于它是否需要参数和/或产生结果。 例如： 
    stage.thenApply(x -> square(x)) .thenAccept(x -> System.out.print(x)) .thenRun(() -> System.out.println()); 
    **/ 
    CompletableFuture.supplyAsync(()->{int i=10/0;return "xiao";})
    	    .whenComplete((u,t)->{System.out.println("u"+u);System.out.println("t"+t.getMessage());})
    	    .exceptionally((e)->{System.out.println(e.getMessage());return null;});
    ```
    
    
    
  - **``CompletableFuture类实现了Future和CompletableStage接口。
    CompletableStage这个接口的作用就是定义一个异步任务执行的阶段所触发的事件。
    Future这个接口的作用就是定义线程的可操作标准。                                        一个CompletableFuture一旦开始执行指定任务（类或实例调用run*时）就会返回一个CompletableStage实例，CompletableStage实例在调用执行阶段触发的方法后返回的都是一个新的CompletableStage实例``**
  
  - 这个怎么进行线程之间的异步通信：直接在需要的开辟子线程的线程中使用这个类就，最后定义whenCompletable就行。

## JMM

- 什么是JMM?Java内存模型.是一种概念,一种约定,**屏蔽各种硬件和操作系统的内存访问差异，以实现让Java程序在各个平台下都达到一致的内存访问效果。**
- Java自身独立的内存模型使其可以实现在不同平台上正常并发，是其实现跨平台的关键。
- [JMM，从虚拟机的角度来解释并发 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/336497195)
- [JMM和JVM - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/157514355)
- [JMM内存模型是JVM的规范_sod5211314的博客-CSDN博客](https://blog.csdn.net/sod5211314/article/details/106358809)
- **JVM本身就是一种规范,JMM其中对内存模型的约定,不能说两者之间没有任何联系**而Hotspot/Jrockt/J9只是进行对JVM的实现
- 8条原子性指令,8条指令规则主要是解决主内存,工作内存,执行引擎之间交互逻辑产生的的数据不一致性问题
  - ![](img\20220311194242.jpg)
- **所有常量都放在常量池.当.java文件被前端编译器javac编译后,得到.class,那么我们所有的jvm执行指令以及数据已经确定了,**
- 同步约定
  - 线程解锁前,必须把线程工作内存中的共享变量 `` 立刻  ``刷回到主存中.
  - 线程加锁前,必须读取主存中的最新值到工作内存中
  - 加锁和解锁必须是同一把锁
- 8条原子指令以及8条执行规则：操作原子性
- happen-before原则：指令的有序性



## Volatile

- 匿名内部类可以访问外部类的成员属性，因为内部类实例会持有外部的引用（Outer.this），通过这个引用可以访问外部类；但是匿名内部类不能访问非final的局部变量，因为访问局部变量时，是创建一个副本在匿名内部类中，访问的一直是这个副本，如果访问非final，容易造成引起数据错误。（本质就是引用传递和值传递）
- volatile是Java提供的轻量级的同步机制，synchronized是重量级的锁（代价高）。
- 普通默认情况下的JVM中在内存不可见情形：线程修改自己工作内存中的变量时是不会更新到主内存中，而其他线程的工作内存中也不会进行变量值的更新。（**总之就是线程修改各自的工作内存对其他线程和主内存是不可见的**）
- volatile的特性
  - 保证可见性
  - 不保证代码原子性
  - 禁止指令重排序
    - 在程序的使用这个数据的代码会当发生指令重排时会进行屏障指令的插入。

##并发编程下的三个问题

- [导致并发程序出问题的根本原因是什么？_ConstXiong-CSDN博客_并发导致的问题](https://blog.csdn.net/meism5/article/details/100838116)
- [CPU 读、修改内存数据过程_leno的专栏-CSDN博客](https://blog.csdn.net/sunxianliang1/article/details/46988829?_t=t)

- 内存的可见性
  - 在多核cpu下，因为每个cpu内嵌多级缓存，当线程的使用共享资源时，每个cpu缓存上存在数据，如果某个cpu上的线程将数据进行修改，会先看缓存中是否存在数据，如果有就会先改缓存，但是并不会同步到内存中，只有当别的数据要占据这个缓存行时才会写回内存。
  - 这样的策略就会导致其他的cpu上的线程得到的数据很可能是自己cpu上没有修改的缓存或者是内存中没有修改的数据
  - 这就是内存不可见性,导致内存和各个cpu缓存之间的数据不一致性
  - 可以通过 volatile、synchronized、Lock接口、Atomic 类型保障可见性。
- 指令的原子性
  - 单条指令或者多条指令往往都是不具有原子性的.
  - 我们的写的代码往往都是不具有原子性的,它们在计算机底层往往是对应多条计算机指令进行执行.如果在执行到某条指令时,发生了线程切换,而这些代码又是涉及数据的读写,往往就会导致数据的逻辑错误.(常用的例子就是多线程求和100000000,导致数据被重复覆盖)
- 指令的有序性
  - [为什么匿名内部类和局部内部类只能访问final变量？_爱码者的博客-CSDN博客](https://blog.csdn.net/weixin_41043145/article/details/93781825)
  - 我们写的代码计算机并不是按照显式的执行，它会进行一系列的变化过程。
    - 源码-编译器优化重排-指令并行也可能重排-内存系统也会重排-执行
    - 指令重排不是随意的进行重排而是需要考虑数据之间的依赖性
  - 如何保证指令的有序性，不产生重排序，或者是说不会影响程序逻辑的重排。
    - 通过操作系统提供的**内存屏障指令**
      - 保证特定的操作执行顺序
      - 可以保证某些变量的内存可见性（volatile的的可见性、有序性就是通过这些指令保证的）

## 原子类

- Java提供了一组将基本一些类封装为操作具有原子性的类。
- 如AtomicInteger
- 底层使用的就是CAS

## 单例模式之volatile

- 如果要说volatile的典型例子就必须要提DCL（双重检查锁），而DCL的使用场景就是单例模式中的懒汉式创建。

- 单例的饿汉式和懒汉式

  - 饿汉式：如果这个类中含有一些属性，而这个实例又长期不使用的话，那么这些数据会长期占用内存。

  - 懒汉式

    ```java
    /**
    这是没有加锁的普通情况：
    问题：如果在多线程情况下，多个线程在某个时间片内去getInstance(),明显可能会在if出频繁切换
    1、导致lazy实例化多次
    2、lazy引用被更新多次
    3、每个线程得到的实例为多个，且有多个线程有的都是死对象。
    所以我们应该要保证这个方法应该是原子性方法。
    **/
    public class Lazy{
        private static Lazy lazy=null;
        private Lazy(){}
        public static Lazy getInstance(){
            //如果没有就新建
            if(lazy==null){
            	lazy=new Lazy();
            }
            return lazy;
        }
    } 
    
    
    /**
    如果我们将这个方法变为锁方法，那么性能会降低，所以锁的内容越少越好如下。
    但是这样不能解决多线程的安全性，仍可能出现上面的情况，所以还需要在锁内再次进行判断。
    为什么加两次判断，而不是直接最外面就行？
     先判断再锁是为了避免已经实例化却还加锁带来的性能损耗（不管并发量是否高，这都能是能提升性能的），这是必须的。而还要检查一次，是为了解决第一次判断的安全隐患。
    **/
    public class Lazy{
        private static Lazy lazy=null;
        private Lazy(){}
        public static Lazy getInstance(){
            //如果没有就新建
            if(lazy==null){
            //将创建对象这一步进行上锁
             synchronized(Laz.class){
               if(lazy==null){
            	 lazy=new Lazy();
                  }
                }
            }
            return lazy;
        }
    } 
    
    /**
    双向检查锁还存在问题，
    lazy=new Lazy();
    这一步分为三个指令：
    1、开辟内存空间
    2、实例化初始化
    3、返回内存地址并给引用
    虽然加上锁能够保证三个特性，synchronized似乎只能保证代码层面上的有序性，但是还是不能保证指令有序性，如果３先于２发生，那么有的线程在进行检测时就会直接取到没有被构造完全的对象。所以我们需要加上volatile保证指令有序性。
    **/
    ```
    
  - 静态内部类并没有实例化也没有执行它的静态代码块，至此我们可以得出第一个结论：静态内部类并不是一开始就创建的！它与静态成员不一样，并不能直接通过外部类名.内部类名的方式就可以直接访问并得到它的对象，通俗一点来说就是：静态内部类跟正常的一个外部类一样，它需要创建才能有！
    既然得出了上面的结论，那么第二个问题也是一样的道理，就是说：静态内部类并不会依赖于任何一个外部类实例，它可以在适当的时候被系统回收！所以这里自然也解答了第三个问题，并不会造成内存泄漏！
    所以，静态内部类对象的生命跟普通的对象一样，生命开始于开发者创建它，结束于系统回收它！
    
  - 我们知道任何一个类都是不安全的，因为我们可以通过反射对其私有性进行破坏，从而任意对其内部方法、属性进行访问和修改。Java告诉我们只要是一个枚举类，在调用其构造函数时，系统就会抛出异常，所以反射无法破坏枚举的权限。
  
    ```tex
    主要是因为Construct类中的newInstance方法的一个判断条件
    if ((this.clazz.getModifiers() & 16384) != 0) {
    throw new IllegalArgumentException("Cannot reflectively create enum objects");
    }
    如果是枚举类型，就会抛出异常，因此只有枚举才能避免被反射破坏
    反射在通过newInstance创建对象时，会检查该类是否ENUM修饰，如果是则抛出异常，反射失败
    ```
  
  - 什么是枚举，Java中定义的一种类，C++中定义的一个基本类型（这个类型是一组可以列举的标识符）。Java中将其定义为一个类，其属性值必须是大写的标识符，这个标识符是唯一的且不可更改的。
  
    - **枚举就是一个常量集，其每个成员都是对应着一个数字常量**
  
- 静态内部类实现单例为什么是线程安全的？

  - [【JVM】为什么静态内部类实现单例模式是线程安全？_cafe-BABE的博客-CSDN博客_静态内部类为什么是线程安全的](https://blog.csdn.net/qq_35590091/article/details/107348114)
  - 利用的是类加载机制，在类加载过程中的初始化阶段只会有一个线程执行clinic<>,其他线程被阻塞，当初始化完成后，其他线程被唤醒，但是不会在执行类的初始化过程。

## 深入理解CAS

- CAS(change and swap)，是CPU的并发原语
- Java封装的原子类为什么能够保证其操作的原子性
  - 其底层使用的就是CAS
