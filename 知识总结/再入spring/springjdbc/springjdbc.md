# 简介

## spring JDBC和事务控制

- springjdbc化境搭建

  - 相关依赖坐标

    ```.xml
        <dependencies>
            <!--spring上下文容器-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>5.1.2.RELEASE</version>
            </dependency>
            <!--spring测试环境-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>3.1.4.RELEASE</version>
            </dependency>
            <!--aop-->
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.2</version>
            </dependency>
            <!--spring jdbc-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>5.1.2.RELEASE</version>
            </dependency>
            <!--spring事务-->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-tx</artifactId>
                <version>5.1.2.RELEASE</version>
            </dependency>
            <!--mysql驱动-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.46</version>
            </dependency>
            <!--数据源（也叫做连接池）-->
            <dependency>
                <groupId>com.mchange</groupId>
                <artifactId>c3p0</artifactId>
                <version>0.9.5.2</version>
            </dependency>
        </dependencies>
    ```

    

  - jdbc配置文件

    ```properties
    # 数据库驱动名
    jdbc.driver=com.mysql.jdbc.Driver
    # 数据库连接
    jdbc.url=jdbc:mysql://localhost:3306/test
    # 用户名
    jdbc.username=root
    # 密码
    jdbc.password=123
    # 一下默认值是对于c3p0的
    # 指定连接池的初始化连接数。取值在minPoolSize与maxPoolSize之间。默认3
    initialPoolSize=20
    # 指定连接池中保留的最大连接数，默认15
    maxPoolSize=100
    # 指定连接池中保留的最大连接数
    minPoolSize=10
    # 最大空闲时间，60秒内未使用则连接被丢弃，若为0则永不丢弃。默认0
    maxIdleTime=600
    # 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数，默认3
    acquireIncrement=5
    # jdbc的标准，用以控制数据源内加载的PreparedStatements数量。这个应该涉及
    #多线程问题
    maxStatements=5
    #每60秒检查所有的连接池中的空闲连接，默认0
    idleConnectionTestPeriod=60
    # jdbc访问数据库步骤：加载驱动、获取连接、获取执行句柄、执行sql
    # dbcp没有空闲回收策略
    #https://www.jianshu.com/p/30984012588e数据源的作用
    #https://www.cnblogs.com/zno2/p/4566512.html dbcp数据源参数意义 
    ```
    
    
    
    ```properties
     ######## DBCP配置文件 ##########
     # 驱动名
     driverClassName=com.mysql.jdbc.Driver
     # url-这是mysql8的请求路径
     url=jdbc:mysql://127.0.0.1:3306/springtest01?useUnicode=true&characterEncoding=utf-8
     # 用户名
     username=root
     # 密码
     password=root
     # 初始连接数
     initialSize=30
     # 最大活跃数
     maxTotal=30
     # 最大空闲数
     maxIdle=10
     # 最小空闲数
     minIdle=5
     # 最长等待时间(毫秒)
     maxWaitMillis=1000
     # 程序中的连接不使用后是否被连接池回收(该版本要使用removeAbandonedOnMaintenance和removeAbandonedOnBorrow)
     # removeAbandoned=true
     removeAbandonedOnMaintenance=true
     removeAbandonedOnBorrow=true
     # 连接在所指定的秒数内未使用才会被删除(秒)
     removeAbandonedTimeout=100
    ```
    
    
    
    ```tex
    序列化与反序列化：内存中的Java对象与字节流相互转换的过程（一般网络传输，会进行序列化的过程是先通过序列化工具将对象转化为本地对应格式文件-持久化，然后再通过网络传输）
    JDK中序列化和反序列化的API：
    
          ①java.io.ObjectInputStream：对象输入流。
    
              该类的readObject()方法从输入流中读取字节序列，然后将字节序列反序列化为一个对象并返回。
    
         ②java.io.ObjectOutputStream：对象输出流。
    
              该类的writeObject(Object obj)方法将将传入的obj对象进行序列化，把得到的字节序列写入到目标输出流中进行输出。
    https://blog.csdn.net/tree_ifconfig/article/details/82766587
    
    序列化是指把一个Java对象变成二进制内容，本质上就是一个byte[]数组。 为什么要把Java对象序列化呢？因为序列化后可以把byte[]保存到文件中，或者把byte[]通过网络传输到远程，这样，就相当于把Java对象存储到文件或者通过网络传输出去了。 有序列化，就有反序列化，即把一个二进制内容（也就是byte[]数组）变回Java对象。有了反序列化，保存到文件中的byte[]数组又可以“变回”Java对象，或者从网络上读取byte[]并把它“变回”Java对象。
    ```
    
    
    
    ```tex
    序列化兼容问题：（当一个对象进行序列化时，如果没有指定版本号，序列化工具会自动添加版本号，当进行反序列化时，如果两者版本号不一致则不能够反序列化成功）
    1、首先是保证的是新版本向下-旧版本兼容。
    当创建了一个类，进行序列化，但是现在我们的类添加了方法或者字段
    如果我们本地文件或者网络流中传入一个旧版本的对象，那么我们的无法将旧对象反序列化为新对象。为了保证能正常反序列化，我们可以指定相同的版本号就能够正常序列化
    2、那假如我们删除了字段或者方法呢？通过验证只要版本号相同就能够正常序列化。根据版本号来判别就行。
    3、版本号应该是在解析到同一个类信息时才会生效。
    4、desc.newInstance()方法原理是利用反射创建了一个对象，本质是调用非序列化父类的无参构造器。
    如果该类是Externalizable类型的，则调用它自身的访问权限是public无参构造方法。
    如果该类是Serializable类型的，则调用该类的第一个非Serializable类型的父类的无参构造方法。
    所以这就是为什么通过反序列化创建对象的时候，并不会执行被序列化对象的构造方法。
    对于实现Serializable接口的类，并不要求该类具有一个无参的构造方法, 因为在反序列化的过程中实际上是去其继承树上找到一个没有实现Serializable接口的父类(最终会找到Object)，然后构造该类的对象，再逐层往下的去设置各个可以反序列化的属性(也就是没有被transient修饰的非静态属性)。
    5、transient(瞬时的)的作用与序列化之间的关系
    6、实际上，是的，在实例化对象时，可以绕过构造函数，如果您使用墓穴为您实例化对象。它通过字节码操作来实现这一点。反序列化对象也将绕过构造函数。使用反射是不可能做到这一点的。
    7、Java中protected方法的访问权限：其对象可以访问、其子类中通过super或者this关键字能够进行访问，在同一包下--友元类可以通过其对象或者子类进行访问，其他的不能类不能够通过其对象或者子类进行访问。子类的友元类不能访问protected权限的方法;protected只能父子之间访问，即是孙子类只能继承以及访问其父类的protected，不能访问其爷爷辈的protected。虽然父辈也许并没有重写，也不允许跨界访问。
    8、Java反序列化对象创建过程：反序列化有一个迭代寻找未序列化父类的根过程。https://www.cnblogs.com/yxym2016/p/12920315.html
    9、哪些对象属性会被序列化：如果是实现了serializable则非static属性以及transient会被序列化，如果时实现了externalizable则所有属性都可以进行自行选择序列化，包括static、transient。（注意：final型成员变量在serializable中能够正常序列化前提是第一次初始化应该放在构造函数中，但是在externalizable中不能，因为常量只能够初始化一次，前者不用调用本身的构造方法，后者原因有二，一是本身会重写方法反序列化会进行二次赋值，二是反序列化会调用本身的构造函数，无论将final第一i初始化放在那个位置都无法避免无意义的作用）
    10、preparestatement与statement主要区别在于是否对sql进行预编译。前者会，后者不会。因为每次执行sql时，数据库管理系统都会进行编译，所以对于一条会执行多次的sql即使参数不同，使用前者会系统消耗会比较小。而对于单次执行使用后者会比较好因为前者耗费比较大。
    11、二级映像：外模式/模式映像（保证应用程序与数据之间的逻辑独立性）不会是对应的sql语句吧，而模式/内模式映像（保证程序与数据的之间的物理独立性）对应的不会是存储引擎吧。
    ```
    
    

- spring事务控制（事务是属于数据库层面的概念，类似操作系统层面的多线程，就是对数据的一组操作，这里的数据应该是指表中的记录。）

  - 数据库查询记录问题

    ```tex
    1、首先哪些数据操作会影响数据库中数据改变：添加、删除、修改。
    2、数据库的管理建立在什么上？数据库管理系统之上。而数据库管理系统也是一种软件运行在操作系统之上，所以我们能称之为一个进程。而这个进行可以同时未多个用户同时提供服务。具体实现就是使用操作系统提供的多线程并发执行机制。至于该程序是如何进行多个用户的访问进行管理的我们先暂且不论。
    3、数据库管理系统是如何与客户进行交互的？通过显式的sql语句以及相应的数据，它会执行该sql，并将执行结果返回给客户。实际上数据库表面的功能不就是这个吗？而我们普通Java多线程程序不也是这样吗？用户虽然没有显式的指出的要执行的代码（是因为这一切操作都已经设定好了，要执行的代码也已经隐式确定了），但是用户基本会提供数据。
    4、事务是什么？事务就是一组要执行的sql操作。相当于我们Java中的代码逻辑，当然我们不论逻辑的多与少，对与错。确定一点事务本身。Java作为一门语言它有解决多线程并发问题的方案：synchronized、volatile。而而mysql也有解决方案就是transaction。当然这只是它提供给你的接口，实际使用时它会做很多的工作。（其实有些片面，因为事务应该属于数据库层面的问题）
    5、好了。现在我们可以将一部分事务的问题转移到基本的多线程问题上了。事务也必然然是多线程引起的。
    6、Java多线程问题就三个：原子性、可见性、有序性。而这些问题最根本上就是共享资源在程序中的不同步不一致引起的。
    7、数据库的数据一致性问题,要注意一个问题就是数据究竟是在内存中读取还是在从数据库中的已经持久化的数据进行读取。如何保证两者之间的数据一致性。myslq使用的是mvcc与各种日志实现目的。
    ```

    

  - 事务ACID特性

    - atomicity(原子性)

      ```tex
      1、事务中的所有步骤要么都完成要么都不完成
      ```

      

    - consistency（一致性）

      ```tex
      1、事务执行前后，数据库中的数据要保持一致性状态，就是当其他客户在读取数据时，数据执行事务前是一种状态，执行后是一种状态，不能出现读取到中间状态。还有就是所有的数据在同一时间应该在所有正在读取它的地方出现的值是一样的。所以数据的一致性应该指两个方面：一是数据在事务中流动的中间状态不应该暴露，因为这应该是瞬时的。二是事务在同一时间读取当前状态的数据的结果应该一致。
      ```

      

    - isolation（隔离性、从多线程角度进行理解即可）

      ```tex
      1、多个并发事务与事务之间在执行时应该是互不影响的。但是事务之间应该是有通信以及共享资源的需求的，所以互不影响是可能的，但是事务之间互不影响的目的是保证数据的一致性。所以只要能够做到不影响数据的一致性，事务之间的隔离程度究竟做到怎么样都是能够忍受的。隔离的目的就是降低事务之间的相互影响，从而保证数据的一致性。
      2、数据库问题及原因：https://blog.csdn.net/qq_29501587/article/details/52135164
         -脏读：就是读到没有生效或者错误的数据。事务a修改数据，还没有持久化到数据库，事务b直接从内存中读。如果发生异常，该数据无效，或者再次修改，总之这个数据数据库不承认，因为压根没有持久化入数据库，则事务b就是进行了脏读。
         -不可重复读：事务a读取数据，事务b进行了修改并且这个数据已经进行了提交，数据库承认，事务a再次读取发现数据变了。（注意：这不是脏读，数据库承认）
         -幻读：事务a读取数据，事务b添加或者删除数据，闭关提交。事务a再次读取，发现数据多了或者少了，就像幻觉一样。（和不可重复读类似，但是引起的原因不同，做重点不同。）
         注意：（不可重复读与幻读都是重复读取出现发现都差异，但是前者强调的记录的更新操作的场景、后者强调的是记录的插入以及删除时的场景。）
         
      3、事务的隔离级别：
      	-读未提交（Read_Uncommitted）:隔离级别最低的的，该级别下，会引发事务之间的脏读、不可重复读、幻读。（相当于就是能够读取事务执行时数据的中间状态，那么就是无法保证一致性的。）也就是说允许直接从内存中拿取，而不是从持久化(数据库或者日志)中拿取。
      	-读已提交（Read_Committed）:读的数据都是事务完成后的值。脏读是不会发生但是其他两个仍然可能。
      	-可重复读（Repeatable_Read）:这种不可重复读也避免了。
      	-串行化（Serializable）：所有的事务照次序串行执行。幻读也不会出现了。
      4、自我感悟：
      	-事务想正确执行，其实就是要事务的执行（操作以及数据）符合时间与空间上的正确逻辑性。因为多个事务可能并发执行，所以存在时间差异问题，按照正常的执行逻辑，假设：
      	时间节点1：事务1开启
      	时间节点2：事务2开启
      	时间节点2：事务3开启，事务1结束，事务2结束
      	时间节点3：事务4开启
      	那么我们执行事务1时，读取到时间节点1的数据a，这时我们的逻辑应该是这时的数据我们已经
      
      ```
    
      
    
    - durability（持久性）
    
      ```tex
      事务一旦提交那么对数据库中数据的改变是永久的。
      ```
    
      

# Java动态字节码生成

## ASM字节码生成

- ```tex
  https://blog.csdn.net/luanlouis/article/details/24589193
  ```




# Java一些学习的知识点

```tex
一、数据库(例：MySQL)
	(1)增删改查(2)三范式(3)表设计(4)主外键
	(5)关联查询(left join、right join、inner join)
	(6)数据库函数使用(7)mysql的四大特性(8)四种隔离级别..
	二、数据库拔高
	(1)全局锁，表锁，行锁，死锁，乐观锁，悲观锁..
	(2)索引，索引底层实现原理，存储方式，如何创建索引，优化索引..
三、Java后端
	(1)Java基础(2)Java的集合: list,map(3)多线程，线程池
	(4)juc下的锁(5)io和nio(6)JavaWeb，重点是JavaEE的框架
	如:Spring, SpringMVC, SpringBoot, SpringSecurity, SpringData, Shiro, Hibernate, MyBatis等等
	提醒:springboot框架火，必学，了解spring的实现原理，如ioc&aop，struts公司用的少
四、缓存和操作糸统
	(1)redis基本数据类型和各种常用命令，数据持久化，持久化恢复
	(2)linux常用命令，重点学linux如何查看日志
五、前端方面
	JavaScript, Ajax, Jquery, html, css, 前后端的http通信方式
	如：restful风格，请求头，请求体，请求方式
六、编程工具
	idea, eclipse, maven, gradle, git, svn, navicat, sqlyog, postman, jemter, xshell, xftp, visualVM..
七、高级知识点
	(1)数据库主从复制，集群搭建，读写分离，分库分表，如：sharding-jdbc, mycat
	(2)对于上面的初中级知识点，学实现原理，阅读源玛
	(3)redis主从复制，哨兵机制，redis集群搭建
	(4)微服务框架spring cloud
	(5)rpc框架dubbo
	(6)JVM: 组成结构，垃圾回收，jvm优化
	(7)网络通信框架netty
	(8)分布式: zookeeper, elastic-search, kafaka，nginx, elastic-job, 分布式锁, 分布式事务..
	(9)高并发下的服务熔断，降级，限流，分布式缓存架构，缓存血崩，穿透
```



# Java十三本权威书籍

```tex
1、Java性能权威指南（Java Performance：The Definitive Guide）
2、Effective Java（介绍很多的Java的一些技巧）
3、Java并发编程
4、jdk8
5、Java核心技术卷
6、mysql必知必会
7、高性能mysql
8、高可用mysql
9、redis深度历险：核心原理和应用实践
10、深入理解Java虚拟机
11、深入理解Kafka
12、Java编程思想*
13、数据结构与算法分析 Java语言描述

```

