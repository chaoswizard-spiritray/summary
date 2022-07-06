# Nosql概述

- 数据库的本质就是读写
- [redis应用场景_ Finn 的博客-CSDN博客_redis实际项目中的应用场景](https://blog.csdn.net/qq646040754/article/details/81077267)

## 为什么要用Nosql

- 大数据时代，一般数据库无法进行分析处理。
- 程序访问数据库框架发展

  - 原生访问数据库方式：安装好数据库服务之后，我们程序中需要通过设置访问所需要的参数，比如访问地址，用户名，密码，数据库名，时区等等.....，换句话说就是客户端命令行的方式访问，建立通信连接，这往往是通过各自协议的网络通信完成的。

    - 缺点：如果我要访问的数据库换了，那么这些代码就直接要全部替换，意味着修改源码，那么系统的可维护性下降。既然各种数据库的访问，都是网络请求完成，无非是协议不同，参数不同，不如将这些访问操作，增删改查操作封装为一些工具类，使用时仅设置不同的实例即可。这就促使jdbc诞生，被放在java.sql中。

  - jdbc:将原生态程序访问数据库的方式，调用数据库的操作封装为一些类供程序员使用，提高程序的扩展性，重用性。

    - 缺点：
      - 每次进行数据库访问操作我们都要自己去创建封装工具类，在工具类中加载驱动，建立连接，获取句柄。如果更换数据库这些代码需要进行拷贝，仅仅是修改数据而已，使得重用性降低。这个可以采用配置文件读取进行解决
      - 访问数据库执行的操作往往都差别不大，而这些重复的操作需要手工完成，工作量巨大。这个需要sql生成器减少sql编写量。
      - sql语句的拼接，sql语句的拼接需要大量的控制循环语句，这部分代码往往需要进行封装，使得整个程序类膨胀。这个需要sql解析器。
      - 数据库表往往需要与编程语言进行映射。

  - hibernate：将数据库相关操作，访问、操作、对象映射....全部封装起来，你不用写sql，这是一个重量级框架。提高你的程序的可移植性。

    - 全表关系映射

    - HQL可以去学一下
    - 但是这样一个重量级框架，它封装了很多不需要的东西，而有些东西我们还是需要自己去写。比如多表查询。不能支持存储过程，，不能优化sql。

  - mybatis:

    - 半自动映射
    - 需要手动配置pojo，sql和映射，这些可以通过逆向工程或者mybatisplus进行解决。
- 数据库使用场景发展

  - 90年代：基本访问量不会太大，更多的使用html-服务器。单个数据库完全足够。

    - 整个网站的瓶颈： 
      - 数据量，如果用户存储的数据过大，那么服务器磁盘本身承载不了
      - 数据索引（如果一个数据超过300万，那么就需要建立索引，索引通过大量的日志文件以及b+tree结构进行建立），要进行索引查询，必须要运行查询算法以及将索引读取到内存中，故服务器的内存难以承受。
      - 访问量（如mysql是读写混合），那么就需要与数据库服务器进行频繁交互，而交互式需要建立连接，通过连接进行会话。（可以看看mysql的架构图），连接的建立往往是开启线程，通过多线程进行处理，线程意味着占用内存资源，处理需求意味着需要占用cpu资源，服务器硬件资源必然支撑不住。
    - 一旦出现这三种问题其一，就意味着你的系统需要优化。至于怎么优化？硬件软件？自然从软角度才长远角度。
  - Memcached（缓存）+mysql+垂直拆分（将一台数据库服务器分解为多台服务器，提高负载量，减少每台的压力。分布式中的数据副本）

    - 发展过程：优化mysql本身底层的数据结构和索引——》文件缓存-》缓存
    - 有些数据是短时间不会发生变化，这部分不用再频繁请求。
    - 数据副本前提：要保证所有副本数据的一致性，如何保证？现在有中间件进行处理，如果我们自己处理，应该用什么思维？可以进行读写分离，部分数据库进行写，其他数据库进行内容同步以及供读取。
    - 互联网中80%都是在进行读取，那么意味着应用服务器需要频繁和数据库服务器进行交互，而有些数据往往是不会改变，或者即使改变对数据使用者没有什么影响，那么我们可以根据策略将一些数据在应用服务器中进行缓存起来。（缓存这个东西在OS以及计组方面同理，内存其实就是一种折中的缓存机制。但是它的主要目的是平衡硬件性能的差异性。互联网中式为了降低访问量。）
  - 分库分表+水平拆分+MySQL集群
    - 数据量越来越大，数据副本的数据量达到上限。数据副本不能将所有数据存起来。需要将数据库的数据分开存储就是分库分表。将每个部分数据按照缓存垂直拆分进行部署。
    - 主要目的解决写的压力
- 现在时代：
  - 数据量大，变化快，仅仅使用MySQL无法满足时代需求，因为关系型数据库主要是用于将数据以及数据之间的关系进行持久化，而现在有很多数据都是临时的，通过实时统计的，没有什么持久的关系，不用持久化，或是仅仅持久化部分的。
- 所以为何要用Nosql:
  - 处理用户个人信息，社交网络，地理位置，用户自己产生的数据，用户日志等等爆发式增长情形

## 什么是NoSQL

- Not Only sql(不仅仅是sql)，泛指非关系型数据库

- 关系型数据库：表格，行，列。在高并发的系统中暴露出许多问题。

- 很多的数据类型用户的个人信息，社交网络，地理位置，这些数据类型的犓不需要一个固定的格式。不需要多余的操作就可以横向扩展。

- 特点

  - 方便扩展：数据之间没有关系，可以便于扩展。比如要添加某些属性。
  - 大数据量高性能（redis一秒写8万次，读取11万次，nosql的缓存记录级是一种细粒度的缓存，性能会比较高）

  - 数据类型是多样型的，不需要事先设计数据库，随取随用

- 传统RDB与ｎｏｓｑｌ的区别

  - RDB
  
    - 结构化组织
    - sql
    - 数据和关系都存在单独的表中
    - DDL、DML
    - 严格的数据一致性
    - 基础事务
  
  - Nosql
  
    - 不仅仅是数据
  
    - 没有固定的查询语言
  
    - 键值对存储，列存储，文档存储，图形数据库（社交关系）
  
    - 最终一致性
  
    - CPA定理和BASE理论（异地多活）
  
    - 高性能，高可用，高扩展（大数据的３ｖ与３高）
  
      - 3v:海量，多样，实时
      
      - 3高：高并发，高可扩（随时可以水平拆分），高性能
  
- 实际开发中nosql与rdb一起使用才是最强的

## NoSQL分类

- KV键值对

  －新浪：ｒｅｄｉｓ（C）

  －美团：ｒｅｄｉｓ＋ｔａｉｒ

  －阿里、百度：ｒｅｄｉｓ＋ｍｅｍｃａｃｈｅｄ

  －应用场景：内容缓存，主要用于处理大量数据的恶搞访问负载，也用于一些日志系统

  －数据无结构，通常只被当作字符串或者二进制数据

- 文档型数据库

  －ｂｓｏｎ格式（和ｊｓｏｎ一样）

  －MongoDB（是一个基于分布式文件存储的数据库，C++）

  　－一个介于关系型数据库和非关系型数据库之间的产品，是非关系型数据库中最功能最丰富最像关系型数据库的。

  －ＣｏｎｔｈＤＢ

- 列存储数据库

  －　HBase

  －　分布式文件系统

- 图关系数据库

  －不是用来存储图形的，放的是数据之间的关系，比如关系网络

  －Ｎｅｏ４ｊ，InfoGrid

# Redis入门

- Remote Dictionary Server-远程字典服务。**是一个开源的c语言编写，支持网络，可基于内存亦可持久化的日志型、K-V数据库。并提供多种语言的API。也被称为结构化数据库**。它可以用作数据库，缓存和消息中间件。
- redis是一个key-value[存储系统](https://baike.baidu.com/item/存储系统)。和Memcached类似，它支持存储的value类型相对更多，包括string(字符串)、list([链表](https://baike.baidu.com/item/链表))、set(集合)、zset(sorted set --有序集合)和hash（哈希类型）。这些[数据类型](https://baike.baidu.com/item/数据类型)都支持push/pop、add/remove及取交集并集和差集及更丰富的操作，而且这些操作都是原子性的。在此基础上，redis支持各种不同方式的排序。与memcached一样，为了保证效率，数据都是缓存在内存中。区别的是redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。
- Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。 它支持多种类型的数据结构，如 [字符串（strings）](http://redis.cn/topics/data-types-intro.html#strings)， [散列（hashes）](http://redis.cn/topics/data-types-intro.html#hashes)， [列表（lists）](http://redis.cn/topics/data-types-intro.html#lists)， [集合（sets）](http://redis.cn/topics/data-types-intro.html#sets)， [有序集合（sorted sets）](http://redis.cn/topics/data-types-intro.html#sorted-sets) 与范围查询， [bitmaps](http://redis.cn/topics/data-types-intro.html#bitmaps)， [hyperloglogs](http://redis.cn/topics/data-types-intro.html#hyperloglogs) 和 [地理空间（geospatial）](http://redis.cn/commands/geoadd.html) 索引半径查询。 Redis 内置了 [复制（replication）](http://redis.cn/topics/replication.html)，[LUA脚本（Lua scripting）](http://redis.cn/commands/eval.html)， [LRU驱动事件（LRU eviction）](http://redis.cn/topics/lru-cache.html)，[事务（transactions）](http://redis.cn/topics/transactions.html) 和不同级别的 [磁盘持久化（persistence）](http://redis.cn/topics/persistence.html)， 并通过 [Redis哨兵（Sentinel）](http://redis.cn/topics/sentinel.html)和自动 [分区（Cluster）](http://redis.cn/topics/cluster-tutorial.html)提供高可用性（high availability）。
- Redis根本是使用内存[存储](https://baike.baidu.com/item/存储)，持久化的关键是这三条指令：SAVE BGSAVE LASTSAVE
- redis基本全是过程化函数的定义
- redis的事务不保证原子性，但是redis的mset保证
- redis能干嘛？
  - 内存存储，持久化，内存中是断电即失，所以说持久化很重要（rdb,aof）
  - 效率高，可以用于高速缓存
  - 发布订阅系统
  - 地图信息分析
  - 计时器，计数器（浏览量）
- 特征：
  - 多样数据类型
  - 持久化
  - 集群
  - 事务

## 下载安装

- 安装后默认端口号：6379

### Linux安装

- redis建议在Linux下安装使用
- 下载安装包
- 先要有c语言环境，因为redis是c开发的
- redis默认安装在根目录的/opt下（其实还是进行解压）
- 然后修改配置文件redis.config
  - 将daemonnize 的值设置为yes
- 启动redis服务
  - 进入redis-server所在目录，然后运行以下命令：redis-server  要加载的配置文件的路径
  - redis-server kconfig/redis.conf
- 启动redis客户端
  - 进入所在目录，执行命令：redis-cli -p 6379【这后面的参数指的是客户端连接的本地服务端口号】
- 查看redis服务
  - ps -ef|grep redis
- 关闭redis服务：
  - shutdown
  - exit

## redis-benchmark

- 压力测试工具
- 测试数据分析

## 基础知识

- redis默认有16个独立的数据库（配置文件中可以查看）默认使用的是第0个，可以使用select指令进行切换（你可以将每个数据库理解为一个Map）
  - select 3【使用第三个数据库】
  
- dbsize【查看数据库的大小】

- set 变量名 变量值【设置键值对】

- get 变量名 【获取键值】

- keys \*【查看数据库中所有的变量名】

- flushdb【清空当前数据库】

- flushall【清空所有数据库】

- exists 键名【看键名是否存在】，返回值0,1

- expire name 10【这个变量10秒种后过期，10秒后置空】
  - ttl name 可以查看变量剩余存活时间
  - 用来设置单点登录
  
- type name【查看变量类型】

- incr count【自增1】

- incrby k 2【k增加2】

- decr key【加一】

- decrby count 10【减10】

- auth 12434【密码验证权限】

- 建议使用unlink删除目标key

- 批量值设置和获取
  - mset(批量设置,注意是原子操作要么全部成功，要么全部失败)
    - mset k1 v1 k2 v2 k3 v3
  - mget(批量获取)
    - mget k1 k2 k3
  - msetnx【设置多个键值对，仅当keyke存在时生效】
  
- 单线程与多线程

  　　每个正在运行的程序（即进程），至少包括一个线程，这个线程叫主线程

    　　主线程在程序启动时被创建，用于执行main函数

    　　只有一个主线程的程序，称作单线程程序

    　　主线程负责执行程序的所有代码（UI展现以及刷新，网络请求，本地存储等等）。这些代码只能顺序执行，无法并发执行

    　　拥有多个线程的程序，称作多线程程序。

    　　iOS允许用户自己开辟新的线程，相对于主线程来讲，这些线程，称为子线程

    　　可以根据需要开辟若干子线程

    　　子线程和主线程都是独立的运行单元，各自的执行互不影响，因此能够并发执行

    　　单线程程序：只有一个线程，代码顺序执行，容易出现代码阻塞（页面假死）

    　　多线程程序：有多个线程，线程间独立运行，能有效地避免代码阻塞，并且提高程序的运行性能

    　　注意：iOS中关于UI的添加和刷新必须在主线程中操作

    　　同步应用程序的开发比较容易，但由于需要在上一个任务完成后才能开始新的任务，所以其效率通常比多线程应用程序低。如果完成同步任务所用的时间比预计时间长，应用程序可能会不响应。多线程处理可以同时运行多个过程。

    　　例如：文字处理器应用程序在您处理文档的同时，可以检查拼写（作为单独的任务）。由于多线程应用程序将程序划分成独立的任务，因此可以在以下方面显著提高性能：

    　　多线程技术使程序的响应速度更快，因为用户界面可以在进行其他工作的同时一直处于活动状态。

    　　当前没有进行处理的任务可以将处理器时间让给其他任务。

    　　占用大量处理时间的任务可以定期将处理器时间让给其他任务。

    　　可以随时停止任务。

    　　可以分别设置各个任务的优先级以优化性能。

    　　是否需要创建多线程应用程序取决于多个因素。在以下情况下，最适合采用多线程处理：

    　　耗时或大量占用处理器的任务阻塞用户界面操作。

    　　各个任务必须等待外部资源（如远程文件或 INTERNET 连接）。

    　　例如：用于跟踪 WEB 页上的链接并下载满足特定条件的文件的 INTERNET 应用程序“ROBOT”。这种应用程序可以依次同步下载各个文件，也可以使用多线程同时下载多个文件。多线程方法比同步方法的效率高很多，因为即使在某些线程中远程 WEB 服务器的响应非常慢，也可以下载文件。

    　　坏处：增加了调度和管理的开销，带来了一些不确定性，需要复杂的同步机制，避免死锁等等。

    　　好处：一定程度上提高响应速度，在多核的情况下还是更能充分利用CPU资源的。

    　　单线程的也就是程序执行时，所跑的程序路径（处理的东西）是连续顺序下来的，必须前面的处理好，后面的才会执行到。

    　　由于时间片很短，这样给用户的感觉是同时有好多线程在执行。但是线程切换是有代价的，因此如果采用多进程，那么就需要将线程所隶属的该进程所需要的内存进行切换，这时间代价是很多的。而线程切换代价就很少，线程是可以共享内存的。所以采用多线程在切换上花费的比多进程少得多。但是，线程切换还是需要时间消耗的，所以采用一个拥有两个线程的进程执行所需要的时间比一个线程的进程执行两次所需要的时间要多一些。即采用多线程不会提高程序的执行速度，反而会降低速度，但是对于用户来说，可以减少用户的响应时间。上述结果只是针对单CPU，如果对于多CPU或者CPU采用超线程技术的话，采用多线程技术还是会提高程序的执行速度的。因为单线程只会映射到一个CPU上，而多线程会映射到多个CPU上，超线程技术本质是多线程硬件化，所以也会加快程序的执行速度。

    　　线程相对于进程的优点：

    　　1、开销小

    　　2、资源共享性好。

    　　线程相对于进程的缺点：

    　　1、共享资源需要耗费一定的锁资源，同步相对复杂。

    　　2、一个线程崩溃可能导致整个进程崩溃，这个当然是自己的应用程序有问题

- redis是单线程的

  - redis是基于内存操作，cpu不是redis性能瓶颈，redis的瓶颈是根据机器的内存和网络带宽，既然可以使用单线程来实现，就使用单线程。（如果用户操作导致单线程进行阻塞使得cpu资源浪费，别的用户进行大量的等待，所以需要使用多线程进行cpu资源利用，且避免用户等待。）（redis的意思就说redis很快，可以对用户的请求立即响应，一个任务执行完成立即执行下一个，cpu资源完全由redis本身进行操作，用户的操作不会造成长时间的等待，cpu资源的浪费，如果用户产生了等待，那一定是网络带宽使得数据传输不够快，或是内存不足够大，需要频繁的进行数据切换的原因）

- redis为什么这么快？

  - 误区1：高性能的服务器一定是多线程?

  - 误区2：多线程（cpu上下文会切换）一定比单线程效率高？

  - 核心：redis是将所有的数据全部放在内存中的，所以用单线程操作效率就是最高的。对于内存系统来说，如果没有上下文切换效率就是最高的（上下文切换os需要介入，需要不断切换程序权限，内核和用户权限需要不断切换，且需要内存进行线程状态保存，内存的消耗也将给redis带来性能损伤）。多次读写都是在一个cpu上的，在内存情况，这个就是最佳方案。

  - Redis的高并发和快速原因很多，总结一下几点：

    - Redis是内存数据库，所以读取速度快；IO使用的是多路复用，使用一个线程来轮询描述符（Java网络编程中的轮询器），减少了线程上下文的切换，将数据库的开、关、读、写都转化成了时间，所以IO时也有加速。

    1. Redis是纯内存数据库，一般都是简单的存取操作，线程占用的时间很多，时间的花费主要集中在IO上，所以读取速度快。

    2. 再说一下IO，Redis使用的是非阻塞IO，IO多路复用，使用了单线程来轮询描述符，将数据库的开、关、读、写都转换成了事件，减少了线程切换时上下文的切换和竞争。

    3. Redis采用了单线程的模型，保证了每个操作的原子性，也减少了线程的上下文切换和竞争。

    4. 另外，数据结构也帮了不少忙，Redis全程使用hash结构，读取速度快，还有一些特殊的数据结构，对数据存储进行了优化，如压缩表，对短数据进行压缩存储，再如，跳表，使用有序的数据结构加快读取的速度。

    5. 还有一点，Redis采用自己实现的事件分离器，效率比较高，内部采用非阻塞的执行方式，吞吐能力比较大。

  - 这几点的根本出发点就是其操作本身就只有简单的内存存取，简单意味着快，快意味着某些多线程问题变得有最优解。

## 五大基本数据类型

- redis就是一个map形式的存储结构（就是不知道使用的hashmap还是treemap还是两者的复合使用），key的存储形式就是字符串，value的存储有各种类型，我们要学的就是这些值可以存储的数据结构形式。

### String字符串

- set设置值

- get获取值

- append key "dd"【追加dd到key后】

- strlen key【获取长度字符串】

- getrange key  0 3【获取索引0到3位置的字符串片段】

- getrange key  0 -1【获取全部字符串】

- setrange key  1 "ddd"【从索引1的位置开始替换】

- setex(set with expire)
  - setex key 30 ”hello“【设置一个key,值为hello，30秒后过期】 
  
- setnx(set if not exist)
  - setnx key "mogo"【如果存在一个同名变量不会生效，不存在时会创建这样一个变量然后设置值】
  - 分布式锁中常常使用
  
- 字符串取巧存储对象,将所有对象的值都当作字符串进行存储

  - 一般设计方式：mset 类名:实例编号：属性名 属性值 类名:实例编号：属性名 属性值......。这样编写存取很麻烦，那么我们直接采用存取json字符串即可**set 类名：编号： {json对象}**编号的作用就是为了类名可以重用

  - set user:1 {name:zhang,age:3}
  - get user:1

- getset 变量名 新值
  - 将变量设置为新值并返回旧值，不存在就返回nil
- 计数器，统计多单位的数量，粉丝数，对象缓存存储

### List

- 基本的数据类型，列表。
- 当作数据结构使用方式：栈、队列、阻塞队列

- 进行左边操作：l不是代表list而是代表左，有左就有右

  - lpush  list 2【往左边添加元素2，返回当前栈的长度】
  - lrange list 0 3【展示左边的0号位索引到3号位索引的元素】
  - lpop list 【从左取出元素并返回】
  - llen list【返回从左边开始统计的元素个数】
  - lindex list 1【获取从左开始的索引的1号索引的值】
  - lrem list 移除元素个数 移除元素的值【将指定的元素移除指定个数，从左开始遍历移除】
  - ltrim  list 1 2【从左边开始索引，将原list仅保存索引1号位到索引2号位的元素】
  - rpoplpush list newlist【原子性地返回并移除存储在 source 的列表的最后一个元素（列表尾部元素）， 并把该元素放入存储在 destination 的列表的第一个元素位置（列表头部）。返回被移除的元素。注意没有lpoprpush】

  - lset list 0 newv【将列表的从左开始的索引的0号位值更新为newv，如果不存在报错】
  - linsert list before/after "ca" value【从左开始遍历，在元素为ca的前面或者是后面添加value】

- redis底层实现是使用的双向链表进行实现

- 如果变量名不存在创建新的链表

- 如果存在，新增内容

- 如果移除了所有值，空链表，也代表不存在

- 在两边插入或者改动值，效率最高

###Set

- set中的值不能重复
- sadd  set "ddd"【向指定集合添加指定元素】
- smembers set【查看集合的所有成员，默认按照元素类型进行升序】
- sismember set 值【查看指定值是否在在set中】
- scard set【查看集合的成员的个数】
- srem set 值 值 值【移除一个或者多个元素】
- srandmember set【随机选取一个成员并返回】
- srandmember set 3【随机选取3个成员并返回】
- spop  set【随机删除一个元素】
- smove set newset 值【将一个指定值从旧的移除到新的集合中】

- sdiff  set1 set2【查看set1中不同于set2的元素，也叫做set1-set2】
- sinter set1 set2【查看两者的交集】
- sunion set1 set2【查看两者并集】

### Hash

- 哈希类型，哈希就是唯一键值对映射关系，唯一性。redis中就需要指定hash值（使用字符串指定即可）以及哈希映射值。（可以认为就是hashmap，这个数据类型可以用来存储复杂的数据类型，主要优点是能够一个hash存储多个对象，可以将用string来保存对象的方式采用hash保存）
- hset hash key val【添加一个键值对,如果这个key已经存在就直接覆盖掉】
- hget hash key【通过键获取值】
- hmset  hash key1 v1 k2 v2【设置多个键值对】
- hmget hash k1 k2【获取多个值】
- hgetall hash 【获取所有数据】
- hdel hash k1【删除指定key的键值对】
- hlen hash【获取hash键值对的数目】
- hexists hash k【判断hash中指定键是否存在】
- hkeys hash【获取hash键值对中所有的key】
- hvals hash【获取hash键值对中所有的值】

- hincrby key field 2【将hash中指定域的值增加2】【加法可做减法，故不提供decr】
- hincrbyfloat key field 2.3【将hash中指定域的值增加2.3】
- hsetnx key  f v【当f不存在时添加这个键值对，存在则不操作】

### Zset

- 有序集合，在set基础上，增加一个值，用于对值排序。就是需要给每个元素指定一个权值用来排序。
- zadd set 1 one【添加one元素其权值是1】[zadd 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/zadd.html)
  - zadd set 1 one 2 tow 3 dd【添加多个】
- zrange set 0 -1 withscores【展示所有元素,带上权重】
- zrangebyscore set -inf +inf withscores【返回有序集合中指定权重区间内的成员，分数由低到高排序,带上分数】
  - [zrangebyscore 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/zrangebyscore.html)
  - 这个要获取指定的是score的范围，且只能只能指定开区间以" ( ”开头
- zrangebylex set  [开始成员  [结束成员 limit 分页开始的起始条目 每页的数目【返回指定成员区间内的成员，按字典正序排列，分数必须相同且以起始元素的权重为标准】【主要目的就是用来对权值相同的元素进行字典序输出】
  - [zrangebylex 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/zrangebylex.html)
  - 可用于分页
  - 要获取
- zrem set 元素【移除指定元素】
- zcard set 【集合数目】
- zrevrange set  0  -1【将集合逆序输出】
- zcount set 1 3【获取指定区间元素数目】

- 重要消息、排行榜方面应用

## 三种特殊数据类型

### Geospatial地理位置

- redis3.2出来的

- 朋友定位，附近的人，打车距离计算。
- 它的方式就是将每个点的位置信息的经纬度存储起来，然后调用redis提供的计算工具（haversine-半正矢公式）将经纬度之差转换为长度单位（km）
- 这需要网上的地理位置数据信息
  - [IP 地理位置信息 - 开放管理员工具 (openadmintools.com)](https://ip.openadmintools.com/)

- 六个命令：

  - geoadd：

    - [geoadd 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/geoadd.html)

    - geoadd  变量名  经度 维度 地理位置名【本质上是一个集合，每个地理位置名是一个元素】

  - geopos:

    - geopos 变量名 地理位置名【获取地理位置的经纬度】

  - geodist:

    - 计算两个地理位置之间的距离
    - [geodist 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/geodist.html)
    - geodist china chendu chongqing  km 【返回成都和重庆之间的距离,以km为单位返回】

  - georadius:

    - [georadius 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/georadius.html)

    - 给定一个经纬度为中心，以及查询半径，返回集合中所有在查询半径内的元素。按照球形查询具体是haversine公式。

    - 

      ```redis
      GEORADIUS Sicily 15 37 200 km WITHDIST WITHCOORD
      返回集合sicily中所有距离经纬中心15 37， 200km的元素并携带距离，以及地理位置的经纬度。
      ```

  - georadiusbymember：

    - [georadiusbymember 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/georadiusbymember.html)
    - 直接指定中心元素，然后返回集合中复合条件的元素（所以相比之下georadius应用范围更大）

  - geohash:

    - 返回一个标准的地理空间的geohash字符串(意味着可以唯一确定地理位置)

- geo的底层实现原理就是Zset ,我们可以使用Zset来操作geo

### Hyperloglog基数统计

- 什么是基数?基本的数字

  - [HyperLogLog--统计用户访问量_rootlh的博客-CSDN博客_hyperloglog统计](https://blog.csdn.net/qq_17305249/article/details/89669053)

  - [基数（集合论概念）_百度百科 (baidu.com)](https://baike.baidu.com/item/基数/4260?fr=aladdin)
  - [Redis源码中hyperloglog结构的实现原理是什么？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/53416615)
- 用于基数统计(统计不重复的元素数目)的算法
- 它存储的
- 优点:占用的内存内存,2^64不同的元素,基数,只需要12KB内存.有0.81%错误率.
- 网页（U/V）中一个人访问一个网站多次,到那时还是算作一个人

  - 传统方式,用集合set保存用户id,然后就可以统计set中的元素数量作为标准判断.(如果保存大量用户id,占用大量的内存)
  - 那么只需要每次将用户id通过hash算法转换为一系列有规律的数，然后再存进set中，就能够保证存储数据少。但是hash算法必然会有hash冲突，所以，必然有误差。如果误差能够接收问题就不大。
- pfadd set ad f g h j k sd sd【添加多个元素】
- pfcount set 【统计集合基数数目】
- pfmerge s3 s1 s2【将s1、s2合并为s3】

### Bitmaps位图

- 位存储。都是操作二进制位来进行记录，就只有0和1两个状态。使用情景最好就是在大量数据但是仅仅只有最多2种状态的状态标识

- 统计疫情感染人数，统计用户信息（活跃，不活跃，登录，未登录，打卡）

- 统计周一到周天的打卡

  - setbit sign 0 1
  - setbit sign 1 1
  - setbit sign 2 1
  - setbit sign 3 1
  - setbit sign 4 1
  - setbit sign 5 1
  - setbit sign 6 1
  - 就是每次都指定一位的值

  - 如果要指定多个对象我们可以像字符串一样进行下标指定

- 查看某一位的值
  - getbit sign 0【查看0号位的值】

- 统计位图中1的个数
  - bitcount sign【统计1的个数】

## 事务

- 事务很多特性都是因为多线程并行、并发产生问题，导致数据产生一系列的数据逻辑上的错误。从而指定事务应该具备的一系列特性，来将这些错误尽量解决。而redis采用单线程就避免了多线程带来的数据一致性问题。

- mysql中ACID特性（原子性，隔离性，持久性，一致性）

- redis单条命令是保存原子性的，但是事务不保证原子性。（那如果执行一组命令中途失败了怎么办？）

- redis事务的本质：就是一组命令的集合。一个事务中所有命令都会被存放在队列中，在事务执行过程中，会按照顺序执行。

  ```redis
  --------队列-----------
  set 1
  set 2
  ......
  -----------------------
  redis这个队列执行的特性有三个：一次性（一次将所有指令执行完），顺序性（指令按照队列顺序执行），排他性（不允许别人干预）
  ```

  

- redis事务没有隔离性的概念！（那么就意味着redis没有四大隔离级别对应的问题，要采用多线程就必然有隔离性问题，唯一解决隔离性就只有采用单线程。）

### redis的事务

- 事务是在客户端进行指令集合并压缩的。然后转换为resp协议，然后整体提交。所以这个指令队列是在客户端完成的。所以为什么事务最好配合乐观锁使用。

- 开启事务

  - multi

- 命令入队

  - 就是我们平常学习的指令

- 执行事务,相当于提交事务

  - exec

- 放弃事务

  - discard

- 每次执行或是丢弃一个事务，下次都需要重新再开启事务

  

  ```redis
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379> set love xiao
  QUEUED
  127.0.0.1:6379> set me ling
  QUEUED
  127.0.0.1:6379> get love
  QUEUED
  127.0.0.1:6379> get me
  QUEUED
  127.0.0.1:6379> exec
  (error) EXECABORT Transaction discarded because of previous errors.
  #可以看到开启事务后，所有的执行命令都是将其放入指令执行队列中
  
  
  
  127.0.0.1:6379> multi
  OK
  127.0.0.1:6379> set love xiao
  QUEUED
  127.0.0.1:6379> set me lin
  QUEUED
  127.0.0.1:6379> mget love me
  QUEUED
  127.0.0.1:6379> exec
  1) OK
  2) OK
  3) 1) "xiao"
     2) "lin"
  ```

  

- 事务报错

  - [redis事务处理：(error) EXECABORT Transaction discarded because of previous errors._赶路人儿-CSDN博客](https://blog.csdn.net/liuxiao723846/article/details/26488525)
  - 语法错误
    - 有一个命令有错误所有命令集合都不会执行。
    - 看来这个命令集合整体编译，然后整体执行，redis。（一次性）
  - 运行错误
    - 运行时才能够检测到
    - 错误命令不会执行，正确命令照常执行，所以要逻辑清晰（也能够说明redis事务没有原子性）

###监控

- [redis的watch机制 - IamHzc - 博客园 (cnblogs.com)](https://www.cnblogs.com/IamHzc/p/15229701.html)
- redis事务提供监控功能，通过该功能能够实现乐观锁

- 悲观锁：无论加什么都加锁，将数据全部锁起来。
- 乐观锁：什么时候都不加锁。更新数据期间去判断下，在此期间是否有人修改了数据。（cas,mvcc比较版本）
- redis通过watch实现乐观锁
- 在单条指令执行期间，redis是不会加锁的，自然不能保证数据的一致性。虽然事务执行期间具有排他性，但是如果我们在提交指令集，但是还没有开始exec时，其他连接已经将数据提交了，进入执行队列，然后在执行期间将数据进行修改了。当前事务尽管因为redis读写是由一个线程完成而有排他性，但是未开始执行前数据的修改却不敢保证没有修改，所以需要一个监控保证在事务执行前数据未被修改。

- watch  out
  - 开启对键的监控（就是监控这些键的版本是否改变）
  - 当前连接一旦开始exec一个事务，如果watch发现这些键版本有改变，那么这个事务被**discard**
  - watch只针对当前连接的最近一个事务生效（相当于mysql中的transaction声明）
  - exec后或者unwatch或者连接断开后watch将失效
  - watch失效后要使用就需要声明新的监控（watch和事务一一配对）

#Jedis

- 我们练习都是使用redis的客户端建立来提交指令

- 现在需要用Java程序来调用redis，那么肯定不是直接能够调用指令的。Java也不提供访问方式，我们需要第三方提供jar工具包，来连接和与redis进行交互。Jedis就是这样的东西。（连接驱动以及交互接口）

- Jedis Client是Redis官网推荐的一个面向java客户端，库文件实现了对redis各类API进行封装调用,通过这些API我们会非常方便的操作我们的Redis数据库。

- 导入jedis包、json包

  ```xml
  <dependency>
  	<groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>3.2.0</version>
  </dependency>
  <dependency>
  	<groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.62</version>
  </dependency>
  ```

- 调用测试

  - 连接redis

    ```java
    public class Ping{
        public static void main(String[]args){
            //构造函数有多个，看源码
           Jedis jedis=new Jedis("localhost",6379); 
            //该对象可以调用所有的命令api
            jedis.ping();//测试连接
        }
    } 
    ```

    

  - 测试命令

    - 常用的api,通过对象调用即可

  - 断开连接

    - jedis.close();关闭连接

  - jedis理解事务

    ```java
    JSONObject jsonObject=new JSONObject();
    //添加键值对
    jsonObject.put("love","xiao");
    Transaction multi=jedis.multi();//开启事务
    multi.set("me",jsonObject.toJOSNString());//将字符串存入缓存
    try{multi.exec();}
    catch(Execption e){
        //如果失败，放弃事务，按理可以不做处理，可以提示其他信息，因为redis会自动丢弃
        multi.discard();
    }finally{
        //无论执行失败还是成功最后都将l关闭
        jedis.close();
    }
    
    ```
    
    

##springboot整合

- springboot操作数据：springdata jpa jdbc mongodb redis

- springdata是springboot的子项目，专门用于解决访问数据库问题，它可以集成nosql和关系型数据库，更易于与spring项目进行整合

- 在springboot2.x后，原来使用的jedis被替换为lettuce
  - jedis:采用的直连，多个线程操作的话，是不安全的，如果想要避免不安全的，使用jedis pool连接池。(BIO模型)
  - lettuce：采用netty,实例可以再多个线程中进行共享，不存在小城不安全的情况，可以减少线程数量，更像NIO。
  
- springboot所有配置类都有一个自动配置类，自动配置类都会绑定一个properties配置文件

  - [objectMapper、JsonNode、JsonObject常用方法 - Jomini - 博客园 (cnblogs.com)](https://www.cnblogs.com/Jomini/p/9695898.html)

  配置连接redis的信息

  ```properties
  # 使用数据库的索引编号，一个示例有16个数据库 0 到 15
  
  spring.redis.database=0 
  
  # 连接URL，如: redis://user:password@example.com:6379 
  
  spring.redis.url= 
  
   # redis所在主机
  
  spring.redis.host=localhost
  
   # Redis服务器的密码 
  
  spring.redis.password=
  
  # Redis服务器端口
  
  spring.redis.port=6379 
  
  # 连接池的最大活动连接数量，使用负值无限制
  
  spring.redis.pool.max-active=8 
  
   # 连接池的最大空闲连接数量，使用负值表示无限数量的空闲连接
  
  spring.redis.pool.max-idle=8
  
  # 连接池最大阻塞等待时间，使用负值表示没有限制
  
  spring.redis.pool.max-wait=-1ms 
  
  # 最小空闲连接数量，使用正值才有效果
  
  spring.redis.pool.min-idle=0 
  
  # 是否启用SSL连接.
  
  spring.redis.ssl=false 
  
  # 连接超时，毫秒为单位
  
  spring.redis.timeout= 
  
  # 集群模式下，集群最大转发的数量
  
  spring.redis.cluster.max-redirects= 
  
  # 集群模式下，逗号分隔的键值对（主机：端口）形式的服务器列表
  
  spring.redis.cluster.nodes= 
  
  # 哨兵模式下，Redis主服务器地址
  
  spring.redis.sentinel.master=
  
  # 哨兵模式下，逗号分隔的键值对（主机：端口）形式的服务器列表
  
  spring.redis.sentinel.nodes= 127.0.0.1:5050,127.0.0.1:5060
  
  ```

  

- 整合redis后，要操作redis就需要使用启动器提供的模板类与redis交互。使用RedisTemplate的实例。该类已经在启动器中的配置类进行注册。但是因为启动器提供的Template并不够用，我们需要自定义模板类。

- [Redis自定义模板、redis.conf配置文件详解 - 我永远喜欢石原里美 - 博客园 (cnblogs.com)](https://www.cnblogs.com/yuan-zhou/p/15902627.html)

  ```java
  //比如集成后，调用redistmplate进行数据存储，它采用序列化的方式是采用的jdk自带的序列化,要将一个对象进行序列化，其类必须或其祖先必须实现serializable接口，如果我们想用其他的序列化方式，那么就需要进行该类的自定义
  //先自定义一个配置类，因为要将这个自定义模板托管给spring
  @Configuration
  public class RedisConfig{
      //自定义模板
      @Bean//注册到spring
      public RedisTemplate<String,Object> redisTemplate(RedisConnectFactory factory){
          //先new一个官方提供的模板
          RedisTmplate<String,Object>template=new RedisTemplate<String,Object>();
          //指定连接工厂
          template.setConnectionFactory(factory);
          //json序列化配置
          Jackson2JsonRedisSerializer<Object> objectJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
          ObjectMapper objectMapper = new ObjectMapper();
          objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
          objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
          objectJackson2JsonRedisSerializer.setObjectMapper(objectMapper);
  
          //String的序列化
          StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
  
          //key采用String的序列化方式
          template.setKeySerializer(stringRedisSerializer);
          //hash采用String的序列化方式
          template.setHashKeySerializer(stringRedisSerializer);
          //value采用jackson
          template.setValueSerializer(objectJackson2JsonRedisSerializer);
          //hash的value序列化采用jackson
          template.setHashValueSerializer(objectJackson2JsonRedisSerializer);
          template.afterPropertiesSet();
  
          return template;
          
      }
      
  }
  ```
  
  



# Redis.conf详解

- 配置文件用于启动

  ```properties
  # 配置中的单位，大小写不敏感
  # 1k => 1000 bytes
  # 1kb => 1024 bytes
  # 1m => 1000000 bytes
  # 1mb => 1024*1024 bytes
  # 1g => 1000000000 bytes
  # 1gb => 1024*1024*1024 bytes
  
  # 用于导入其他的配置文件，这样可以用于配置文件的分块管理。类似spring主配置文件
  # include /path/to/local.conf
  # include /path/to/other.conf
  
  #redis是否以守护进程进行运行，默认no。守护进程，只要系统中存在一个非守护进程就会一直运行。一般设置为yes
  daemonize no
  
  # 当运行守护程序化时，Redis  在 /var/run/redis.pid  中写入一个 pid 文件
  # 默认值。您可以在此处指定自定义 pid 文件位置。
  pidfile /var/run/redis.pid
  
  #接收连接的特定端口，默认是6379.如果特定端口是0，redis就不会在TCP套接字上监听
  port 6379
  
  #在每秒请求数很大的环境中，需要使用大量积压以避免客户端连接慢的问题。
  tcp-backlog 511
  
  # 默认情况下 ，Redis 会侦听来自所有网络接口的连接
  #  在服务器上可用。可以只听一个或多个
  # 接口使用 "bind" 配置指令，后跟一个或
  # 更多 IP 地址。
  # bind 192.168.1.100 10.0.0.1
  # bind 127.0.0.1
  
  #是否启用保护模式
  protected-mode yes
  
  # 客户端空闲 N 秒后关闭连接（0 表示禁用）.心跳检测。
  timeouts 0
  
  #日志级别
  # 调试 （大量信息，对开发/测试有用）debug
  # 详细 （许多很少有用的信息，但不是像调试级别那样的混乱）verbose
  # 注意 （适度详细，你可能在生产中想要）notice
  # 警告 （仅记录非常重要/关键的消息）warning
  loglevel notice
  
  #日志文件位置
  logfile ""
  
  #数据库
  # 设置数据库数量。默认数据库为 DB 0，您可以选择
  # 基于每个连接的不同，使用 SELECT <dbid>其中
  # dbid 是介于 0 和 "databases"-1 之间的数字
  databases 16
  
  #是否显示运行图标
  always-show-logo yes
  
  ###############快照######################
  #快照(像照相一样将当前内存的数据拷贝一份,用于持久化，原来的继续用于用户访问)。将redis中数据持久化到硬盘。redis是内存数据库。断电即失。
  #持久化，在规定时间内，执行了多少此操作，则会持久化到文件.rdb、 .aof
  #如果900秒内，至少有一个key，就进行一次持久化操作。
  #如果300秒内，至少有10个key，就进行一次持久化操作。
  #如果60秒内，至少有10000个key，就进行一次持久化操作。
  #就是在考虑并发量，越是修改频繁说明数据丢失的可能性大。这个自己考虑使用环境。
  save 900 1 
  save 300 10
  save 60 10000
  
  #如果持久化失败是否仅需工作。
  stop-writes-on-bgsave-error yes
  #是否压缩rdb文件,这个会吃cpu
  rdbcompression yes
  #是否校验rdb文件
  #rdb校验和
  rdbchecksum yes
  #rdb文件保存的目录
  dir ./
  
  #############aof#################
  #另一种持久化模式：append only模式（只追加模式，默认不开启）
  #默认持久化是使用rdb，大部分情况rdb是够用的
  appendonly no
  #持久化文件的名字
  appendfilename "appendonly.aof"
  #持久化频率
  #always:每次修改都会同步追加到持久化文件中，耗性能
  #everysec:每秒执行一次，可能会丢失这1秒的数据
  #no:不同步
  appendfsync everysec
  
  ##############安全#####################
  #redis默认是没有密码的
  #对应客户端指令
  #获取密码config get requirepass
  #设置密码config set requirepass "sdf"
  #设置密码
  requirepass 123
  
  ##############客户端限制#############
  #设置最大连接数
  maxclients 1000
  #设置redis配置最大的内存容量
  maxmemory <bytes容量>
  #内存到达上限之后的处理策略
  # volatile-lru ->使用 LRU 算法删除具有过期集的密钥
  # allkeys-lru ->根据 LRU 算法删除任何密钥
  # volatile-random ->删除具有过期集的随机密钥
  # allkeys-random ->删除一个随机密钥，任何密钥
  # volatile-ttl ->删除过期时间最接近的密钥（次要 TTL）
  # noeviction ->根本不过期，只需在写入操作时返回错误
  maxmemory-policy noeviction
  ```
  

# 持久化

- 目的就是数据备份，以防意外

## RDB（Redis DataBase）持久化

- redis内存数据库，如果不将内存中的数据库状态保存到磁盘，那么一旦服务器进程退出，服务器中数据库状态也会消失。所以redis提供了持久化功能。
- 具体配置在配置文件中已经讲解,尽量使用默认配置。
- RDB持久化原理

  - 在指定的时间间隔内将内存中的数据集快照写入磁盘，也就是行话讲的snapshot快照，它恢复是将快照文件直接读到内存。
  - redis会单独创建（fork）一个子进程来进行持久化，会先将数据写入到一个临时文件中，待持久化过程都结束了，再用这个临时文件替换上次持久化好的文件。整个过程中，主进程是不仅从任何IO操作的，这就确保了极高的性能。如果需要进行大规模数据的恢复，且对于数据恢复的完整性不是非常敏感，那**RDB方式要比AOF方式更加的高效**。RDB的缺点是最后一次持久化后的数据可能丢失。
  - 也就是RDB是保留数据最近的一次备份。
  - 大部分情况使用。
  
- RDB保存的文件是dump.rdb都是在我们的配置文件中快照中进行配置

  - dbfilename dump.rdb

- RDB触发机制

  - 满足配置文件中的save配置
  - 执行flushall
  - 结束redisj进程

- RDB文件被破坏怎么修复（恶意删除或是添加）

  - redis-check-rdb dump.rdb
    用这个命令修复损坏的rdb文件

- 使用RDB文件恢复内存数据

  - 只要将该文件放在redis启动目录（dir）下，redis在启动时会自动读取该文件进行恢复。
  - config get dir查看启动目录

- 优缺点：

  - 优点
    - 适合大规模的数据恢复
    - 对数据的完整性要求不高
  - 缺点
    - 需要一定的时间间隔，在间隔的时间内如果发生意外，这期间的数据修改就会丢失。
    - 开启fork子进程会占用一定的内存空间。

- 流程图：

  ![](20220304164633.jpg)

- 注意：这个文件相当于内存数据最近一次备份，但是我们还可以将这个文件进行备份，用来保存一些重要节点的数据。



## AOF（Append Only File）

- 文件追加,记录每个数据执行节点。

- 原理：像mysql的日志文件

  - 将我们的所有命令都记录下来，作为历史，恢复的时候就把这个文件全部再执行一遍
  - 以日志的形式记录每个写操作，将redis执行过的所有指令记录下来（读操作不记录），只许追加文件但是不能改文件，redis启动之初会读取改文件重新构建数据，换言之，redis重启的话就更具日志文件的内容将写指令从前到后执行一次完成恢复。

- 流程图

  ![](20220304165437.jpg)

- 配置

  ```properties
  #设置开启AOF
  appendonly yes
  #同步频率
  appendfsync everysec
  # 当 AOF 异步 策略设置为"始终"或 "每秒"时，以及后台
  # 保存 过程（后台保存或AOF日志后台重写）是
  # 在某些 Linux 配置中，对磁盘执行大量 I/O
  # Redis 可能会在 fsync（） 调用上阻塞太长时间。请注意，没有修复程序
  # 目前，由于即使在  不同的线程中执行 fsync 也会阻塞
  # 我们的同步 写入（2）调用。
  #
  # 为了缓解 这个问题 ，可以使用以下选项
  # 将阻止在主进程中调用 fsync（）， 而
  # BGSAVE 或 BGREWRITEAOF 正在进行中。
  #
  # 这意味着，当另一个孩子在储蓄时，Redis 的耐用性是
  # 与"appendfsync none"相同。实际上，这意味着它是
  #  在最坏的情况下，可能会丢失长达 30 秒的日志（使用
  # 默认 的 Linux 设置）。
  #
  #如果您有延迟问题，请将其设置为"是"。否则，请将其保留为
  #从耐用性的角度来看，"不"是最安全的选择。
  no-appendfsync-on-rewrite no
  
  #自动重写触发条件，AOF文件很大，一个文件很可能装不下，需要开启新的文件继续进行记录，下面是触发条件
  #百分比触发
  auto-aof-rewrite-percentage 100
  #当文件大小到达这个时自动重写
  auto-aof-rewrite-min-size 64mb
  
  
  ```

- AOF文件被破坏怎么修复
  - redis-check-aof --fix appendonly.aof
    用这个命令可以检查并修复记录的错误的命令

-  AOF和RDB持久化可以同时启用，没有问题。

   如果在启动时启用了 AOF Redis 将加载 AOF，即文件

   具有 更好的耐用性保证。

- 触发该文件生成的机制和rdb类似

- 优点和缺点

  - 优点：
    - 每次修改操作都被同步到文件，文件的完整性提高
  - 缺点：
    - 相对于数据文件，aof远远大于rdb,修复速度也比rdb慢
    - aof运行效率也要比rdb慢，所以我们redis默认的配置就是RDB

- 扩展

  <img src="20220304172923.jpg" style="zoom:150%;" />

​    <img src="20220304173018.jpg" style="zoom:150%;" />

# Redis订阅发布

- **在网络编程的角度来说就是搭建了一个服务器广播网络，只不过现在是借助redis服务器，将P2P搭建成双向广播网络，每个用户都可以在redis中注册一个频道，其他用户都可以进行监听。这个可以做临时会话，最好不要做长期的，因为这个信息是实时拉取的，如果要做长期，还需要用东西将这些信息进行存起来才行**，而且维护过多的连接，会消耗redis性能（嘿嘿，是不是可以自己写个简单的临时聊天室了）

- redis发布订阅（pub/sub）是一种消息通信模式（**观察者模式**）：发送者（pub）发送消息，订阅者（sub）接收消息。微信微博。
- redis客户端可以订阅任意数量的频道。
- redis提供消息发布于订阅的命令
  - psubscribe:订阅一个或多个复合给定模式的频道
  - pubsub:查看订阅于发布的状态
  - publish 频道名 信息:将信息发送到指定的频道
  - punsubscribe:退订所有给定模式的频道
  - subscribe 频道名 频道名 频道名:订阅给定的一个或多个频道的信息
  - unsubscribe：指退订给定的频道
- 原理：（下面说的这个大概意思就是这个实现就是通过**拉链数据结构-邻接表进行实现的**）
  - 通过subscribe 命令订阅某频道后，redis.server里维护了一个字典，字典的键就是一个个频道！而字典的值则是一个链表，链表中保存了所有订阅这个channel的客户端，subscribe命令额关键，就是将客户端添加到给定channel的订阅链表中。
  - 通过publish命令向订阅者发送消息，redis-server会使用给定的频道作为键，在它所维护的channel字典中查找记录了订阅这个频道的所有客户端的链表，遍历这个链表，将消息发布给所有订阅者。
  - Pub/sub从字面上理解就是发布（publish）与订阅（subscribe），在redis中，你可以设定对某一个key值进行消息发布及消息订阅，当一个key值上进行了消息发布后，所有订阅它的客户端都会收到相应的消息，者一功能明显的用法就是用作实时消息系统，比如普通的即时聊天，群聊等功能。
- 使用场景：
  - 实时消息系统
  - 实时聊天
  - 订阅，关注系统
  - 稍微复杂点的就会使用消息中间件MQ

# Redis主从复制

- 主从复制：
  - 是指将一台redis服务器的数据，复制到其他的redis服务器。前者称为主节点（master/leader），后者称为从节点（slave/follower）。数据的复制是单向的，只能由主节点可以有多个从节点（或没有从节点），单一个从节点只能由一个主节点。
- 主从复制的作用
  - 数据冗余：主从复制实现了数据的热备份，是持久化之外的一种数据冗余方式
    - 数据冗余是数据备份的一种方式。
  - 故障恢复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复。实际上是一种服务的冗余。
  - 负载均衡：在主从复制的基础上，配合读写分离，可以由主节点提供些服务，由从节点提供读服务（即写redis数据时应用连接主节点，读redis数据时应用连接从节点），分担服务器负载，尤其是在写少读多的场景下，通过多个从节点分担读负载，可以大大提高redis服务器的并发量
  - 高可用（集群）基石：除了上述作用以外，主从复制还是哨兵模式和集群能够实施的基础，因此说主从复制时redi高可用的基础。
- 一般来说，要将redis运用于工程项目中，只是用一台redis服务器是不可能的，原因如下：
  - 从结构上，单个redis服务器会发生单点故障，并且一台服务器需要处理所有的请求负载，压力较大
  - 从容器上，单个redis服务器内存容量优先，就算一台redistribution服务器内存容量为256G ,也不能将所有内存用所redis存储内存，一般来说，**单台redis最大使用内存不应该超过20G**
- 电商网站上的商品，一般都是一次上传，无数次浏览的，说专业点也就是“读多写少”
  - 这种场景可以使用一台主服务器进行写，多台服务器提供数据读取，当主服务器完成写后，将数据同步到从服务器。从而实现读写分离。
  - 主从复制一般是一主二从
  - [互联网三高架构：高并发、高性能、高可用 - 简书 (jianshu.com)](https://www.jianshu.com/p/fd8ff58ba5d2)
  - [互联网行业3高及多线程分析_litlit023的专栏-CSDN博客](https://blog.csdn.net/litlit023/article/details/114602037)
  - [互联网三高 - 每日坚果的个人空间 - OSCHINA - 中文开源技术交流社区](https://my.oschina.net/u/4466912/blog/4667260)

## 环境配置

- 先通过info replication查看当前库的信息

- **默认情况下每个redis服务进程都是主节点**

  ```tex
  127.0.0.1:6379> info replication//主从复制信息
  # Replication
  role:master//主机
  connected_slaves:0//已连接的从机
  master_repl_offset:0//
  repl_backlog_active:0//
  repl_backlog_size:1048576//
  repl_backlog_first_byte_offset:0//
  repl_backlog_histlen:0//
  ```

  

- 先将配置文件进行复制多份，复制之后进行修改，然后调用redis-server命令进行指定配置文件启动，这样就能启动多台服务器。

  - 修改内容

  - 端口
  - pid文件路径
  - 日志log路径
  - dump.rdb路径

- 配从节点

  - slaveof  127.0.0.1(主机名)  6379(端口号)

  - **真实进行主从配置是在配置文件中进行，这样就是永久的配置，使用命令配置就是在redis服务进程运行期间有效，服务一旦停止失效**

    ```properties
    ################################# REPLICATION #################################
    
    # Master-Slave replication. Use slaveof to make a Redis instance a copy of
    # another Redis server. A few things to understand ASAP about Redis replication.
    #
    # 1) Redis replication is asynchronous, but you can configure a master to
    #    stop accepting writes if it appears to be not connected with at least
    #    a given number of slaves.
    # 2) Redis slaves are able to perform a partial resynchronization with the
    #    master if the replication link is lost for a relatively small amount of
    #    time. You may want to configure the replication backlog size (see the next
    #    sections of this file) with a sensible value depending on your needs.
    # 3) Replication is automatic and does not need user intervention. After a
    #    network partition slaves automatically try to reconnect to masters
    #    and resynchronize with them.
    #
    slaveof <masterip>主机地址 <masterport>主机端口
    
    # If the master is password protected (using the "requirepass" configuration
    # directive below) it is possible to tell the slave to authenticate before
    # starting the replication synchronization process, otherwise the master will
    # refuse the slave request.
    #
    masterauth <master-password>如果主机有密码配置密码
    ```
    
    

- 主机写，从机读，主机中所有信息和数据都会自动被从机保存

- 如果主机进程结束，那么从机信息仍然不会变(系统没有写功能)，如果主机重新启动，从机仍然能够从主机中获取数据。

## 复制原理

- [Redis主从复制原理总结 - 老虎死了还有狼 - 博客园 (cnblogs.com)](https://www.cnblogs.com/daofaziran/p/10978628.html)
  - 原理详解
  
- 从机启动成功，连接到主机后会向主机发送一个sync（同步）命令

- 主机接到命令，启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，在后台进程执行完毕之后，主机将传送整个数据文件到从机，并完成一次完全同步

- 全量复制：从机服务在接收到数据库文件数据后，将其存盘并加载到内存中

  - 就是一旦连接，就将主机中的数据刷新到rdb文件然后传递给从机

- 增量复制：主机继续将新的所有收集到的修改命令一次传给从机，完成同步

  - 就是全量复制后，主机一旦执行写命令后，就会让所有从机都执行一遍

- 只要是从新连接主机，全量复制就会被自动执行

- 问题：

  - 既然所有从机最后都要执行一遍，这样不是操作冗余了吗，而且数据很可能导致数据延迟？有什么意义？
  - 为了提升读取效率，一台服务器肯定不够用，那么需要多台，如果单独进行读写，那么每台服务器的将无法保证数据的一致性。写的压力较小，读的压力较大，但是经过分担后，压力比较合理。

  - 保证了数据的一致性，这样集群就是可取的，二集群还能带来一个好处就是系统的扩展性和可用性，要进行系统的更新，故障排除并不影响用户使用。

## 服务器宕机

- [slaveof 命令 -- Redis中国用户组（CRUG）](http://redis.cn/commands/slaveof.html)
- 如果主机宕机，那么从机能够提供读的服务，但是数据写将丢失。这个时候需要一台服务提供写。
- 只需要将在一台服务器上执行slaveof no one(不是奴隶)该从机就会变为主机，且内存中的数据不会丢失。
  - 如果你的主从架构是层式，那么我们需要将其他从机指向新的主机。
  - 如果你的主从架构是链式且指定链头为新主机，那么不用重新指定
- 这是没有哨兵模式时我们需要进行手动指定。

## 哨兵模式

- 主机宕机后自动在从机中选取主机的模式

- 没有哨兵模式时，我们需要将手动主从切换，费时费力，还会有段时间服务不可用。2.8后提供了哨兵（Sentinel）模式。该模式会监控主机是否故障，如果主机故障会根据投票数自动将从库转换为主库。

- 哨兵模式时一种特殊的模式，首先redis提供了sentinel的命令，sentinel是一个独立的进程，作为进程，它会独立运行，其原理是sentinel通过发送命令，等待redis服务器响应，从而监控运行的多个redis实例。

- sentinel的作用

  - 通过发送command，让redis服务器返回监控器运行状态，包括主服务器和从服务器。
  - 当sentinel监测到master宕机，会自动将slave切换成master，然后通过**发布订阅模式**通知其他的从服务器，修改配置文件，让他们切换主机。

- 然而一个哨兵进程对redis服务器进行监控，可能会出现问题，为此，我们可以使用多个哨兵进行监控，各个哨兵之间还会进行监控，这样就形成了多哨兵模式。

  - 目的1一个哨兵死后仍然能监控redis
  - 目的2能即时补充哨兵

  <img src="20220305144037.jpg" style="zoom:150%;" />

- 这意味着哨兵模式就是维护三个集合（它们的ip和端口号），一个主机集合，一个从机集合，一个哨兵集合。定期的进行状态获取，一旦主机意外，就会遍历哨兵集合，通知哨兵，每个哨兵持有一个主机被检测故障次数，，每次通知就加一，当到达指定数量是就开启选举从机作为主机（这个由redis算法决定）。

- 启用哨兵模式：

  - [Redis Sentinel配置文件 - 简书 (jianshu.io)](http://events.jianshu.io/p/908fc69b32d1)
  - [Sentinel哨兵监控详细解析，写得太好了_清朝程序猿的博客-CSDN博客_sentinel 检查状态](https://blog.csdn.net/weixin_45987961/article/details/121822890)

  - 在redis的安装主目录下，有一个redis-sentinel.exe，这个程序提供sentinel服务。哨兵默认端口26379

  - 在当前环境是一主二从，先建立一个sentinel配置文件:sentinel.conf

    ```properties
    sentinel monitor 监控器名(随便写)  监控的主机ip 端口号  1
    这个1标识有多少个哨兵检测到主机宕机时进行故障转移failover
    只用监测主机
    ```

    

  - 启动redis-sentinel kconfig/sentinel.conf

  - 当主机停止后，这个时候就会从从机中选取一个从机作为主机

  - 如果断开的主机被修复，该主机需要进行重新配置

- 优缺点

  - 哨兵集群，基于主从复制模式，主从复制优点它全都有
  - 主从可以切换，故障可以转移，系统的可用性更好
  - 哨兵模式就是主从模式的升级，手动到自动
  - 缺点：
    - redis不好在线扩容，集群容量一旦到达上限，在线扩容就十分麻烦。
    - 实现哨兵模式的配置其实非常麻烦，里面有很多选择

- **注意：用户需要先访问哨兵然后才能访问主机**

# Redis的缓存穿透和雪崩

- [mybatis缓存和redis缓存的区别_Redis缓存知识问题_weixin_39841640的博客-CSDN博客](https://blog.csdn.net/weixin_39841640/article/details/111700809)

- redis缓存的使用，极大的替身了应用程序的性能和效率，特别是数据查询方面。但同时，它也带来了一些问题。其中最要害的问题，就是数据的一致性问题，从严格意义上讲，这个问题无解。如果对数据的一致性要求很高，那么就不能使用缓存。
- 另外的一些典型的问题就是：缓存穿透，缓存雪崩和缓存击穿，目前，有流行的解决方案。

## 缓存穿透

- 场景:
  - 用户的读请求会先在缓存中读，如果没有就会去数据库中进行查询。如果数据库也没有，那么这次查询失败。当用户很多的时候，缓存都没有命中（秒杀），于是都去请求了持久层数据库。这会给持久层数据库造成很大的压力，这种情况就是缓存穿透。
  - 总结下意思：就是说缓存没有起到最初用它的作用，降低数据库本身的压力，那些热点数据应该被用户在缓存中迅速访问到。
- 解决方案：
  - 布隆过滤器
    - 布隆过滤器是一种数据结构，对所有可能查询的此参数以hash形式存储，在控制层先进行校验，不符合则丢弃，从而避免了对底层存储系统的查询压力。
  - 缓存空对象
    - 当存储层不命中后，即失返回的空对象也将其缓存起来，即时设置一个过期时间，之后再访问这个数据将会从缓存中获取，保护了后端数据源。
    - 存在的问题：
      - 如果控制能够缓存起来，这就i围着缓存需要更多的空间存储更多的键，因为这当中可能会有很多的空值的键
      - 即使对空值设置了过期时间，还是会存在缓存层和存储层的数据会有一段时间窗口的不一致，这对于需要保持性的业务会有影响。

## 缓存击穿

- 这里需要注意和缓存击穿的区别，缓存击穿，是指一个key非常热点，在不停的扛着大并发，大并发几种对这个点进行访问，当这个key在失效的瞬间，持续的大并发就穿破缓存，直接请求数据库，就像在一个屏障上开了一个洞。
- 当某个key在过期的瞬间，有大量的请求并发访问，这类数据一般是热点数据，由于缓存过期，会同时访问数据库来查询最新数据，并且回写缓存，会导致数据库瞬间压力过大。

- 总结下：缓存击穿是数据本身存在缓存中，但是设置了失效时间，当在失效的瞬间仍然有应用服务器的大量请求，这些请求都得到失效的回应，就都去请求数据库，压力在这瞬间作用到数据库，缓存层直接失效。
  - 这是一种很极端的请求，因为这个并发要集中在这个时间点的一定范围，可见需要的并发量是要有多大。
  - 缓存穿透与缓存击穿的主要区别就是，**要访问的数据在缓存中存不存在**。前者是一种持续压力，后者是一种瞬间压力。
  - 比如抢票
- 解决方案
  - 设置热点数据永不过期
    - 从缓存层面来看，没有设置过期时间，所以不会出现热点key过期后产生的问题。
  - 加互斥锁
    - **分布式锁**：使用分布式锁，保证对于每个key同时只有一个线程去查询后端服务。其他线程没有获得分布式锁的权限，因此只需要等待即可。这种方式将高并发的压力转移到了分布式锁，因此对分布式锁的考验很大。

## 缓存雪崩

- 缓存雪崩，是指在某一个时间段，缓存集中过期失效。redis集体宕机。**缓存雪崩是指在我们设置缓存时采用了相同的过期时间，导致缓存在某一时刻同时失效，请求全部转发到DB，DB瞬时压力过重雪崩。**
- 产生雪崩的原因之一，比如在12点是，很快会来一波抢购，这波商品时间比较集中的放入了缓存。那么到了1点，这批缓存过期。而对这批商品的访问查询，都落到了数据库上，对于数据库而言，就会产生周期性的压力波峰，于是所有的请求都会到达存储层，存储层的调用量会暴增，造成存储层也会挂掉的情况。
- 其实集中过期，倒不是非常致命的，比较指明的缓存雪崩，是缓存服务器某个节点宕机或断网。因为自然形成的缓存雪崩，一定是在某个时间段集中创建缓存，这个时候，数据库也是可以顶住压力的。无非就是对数据库产生周期性的压力而已。而缓存服务节点的宕机，对数据库服务器造成的压力是不预知的，很有可能瞬间就把数据库压垮。
- 总结下：就是说缓存会存热点数据，热点数据如果过期，但是访问量依旧很大，那么就直接访问数据库，这些数据又不能放在缓存中，那么数据库就是面临持续的压力，直到这些数据再次成为热点数据，又被放入缓存。
  - 根本原因就是热点数据在不断变化，无法确定，缓存有限，需要清空，大量并发使得整体热点数据变换不断，在变换为热点这个时间段要是访问很多，由于缓存中没有，数据库就要持续面临这种压力，当成为热点后，压力就会得到缓解。
- 双十一：停掉一些服务，保证主要服务运行
- 解决方案
  - redis高可用
    - 既然redis有可能挂掉，那我多增几台redis。就是搭建集群。（异地多活）
  - 限流降级
    - 在缓存失效后，通过加锁或者队列来控制读数据库写缓存的线程数量。比如对某个key只允许一个线程查询数据和写缓存，其他线程等待。
  - 数据预热
    - 数据加热的含义就是在正式部署之前，我先把可能的数据先预先访问一遍，这样部分数据可能大量访问的数据就会加载到缓存中。在即将发生大并发访问前手动触发加载缓存不同的key,设置不同的过期时间，让缓存失效的时间点尽量均匀。

# 关于redis的问题

## spring boot与redis整合问题

- 如果redis无法创建，先考虑是不是依赖包版本问题，一般不加依赖版本使用默认依赖版本就行。然后考虑springboot配置文件以及redis服务端配置的问题。

- [连redis这些概念都不知道，谁敢相信您曾搞过软件开发？ (baidu.com)](https://baijiahao.baidu.com/s?id=1684033975419490321&wfr=spider&for=pc)
- [Redis：黑马点评项目之商品缓存_m0_49569564的博客-CSDN博客_redis缓存商品信息](https://blog.csdn.net/m0_49569564/article/details/123753096)

- [redis的优缺点 - liu·bear - 博客园 (cnblogs.com)](https://www.cnblogs.com/liu-bear/p/10782244.html)

- [面试：redis 是单线程，是怎么解决高并发问题的 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1864889)（这篇文章非常的好，解答了很多疑惑）
  
  - redis为什么快
  - redis底层相关的数据结构（hashmap）
  - 多路复用的底层数据结构以及实现思路
  - [select模型中fd_set集合中存的是什么东西？-CSDN社区](https://bbs.csdn.net/topics/340037922)
  - 总结下redis解决高并发主要就凭借两点
    - 本身的高性能，快
    - 利用OS借用io通道封装出多路复用器的技术，循环请求队列。就是单线程排队处理。
  - 而安全性主要就是通过乐观锁进行实现
  
- [单线程redis是否真的单线程？ - jongty - 博客园 (cnblogs.com)](https://www.cnblogs.com/jongty/p/14135733.html)

- [redis的通讯协议_My_daidai的博客-CSDN博客](https://blog.csdn.net/My_daidai/article/details/107210474)

- 为什么Redis不支持回滚？

  官方给出了这样的解释：

  如果你有使用关系式数据库的经验， 那么 “Redis 在事务失败时不进行回滚，而是继续执行余下的命令”这种做法可能会让你觉得有点奇怪。

  以下是这种做法的优点：

  Redis 命令只会因为错误的语法而失败（并且这些问题不能在入队时发现），或是命令用在了错误类型的键上面：这也就是说，从实用性的角度来说，失败的命令是由编程错误造成的，而这些错误应该在开发的过程中被发现，而不应该出现在生产环境中。
  因为不需要对回滚进行支持，所以 Redis 的内部可以保持简单且快速。

- [数据库面试题：Redis如何保证数据一致性_我是方小磊的博客-CSDN博客_redis高并发保证数据一致性](https://blog.csdn.net/weixin_44844089/article/details/115494277)

- [Redis在实际应用中，是单独部署在一个服务器中，还是和项目跑在一个服务器中，还是跑在数据库服务器？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/454576102)

- [针对Redis服务我们应该避免哪些性能浪费-电子发烧友网 (elecfans.com)](http://www.elecfans.com/d/1723288.html)

  - 这篇文章和上面的文章都值得一读

- [36.redis的key过期时会被立即删除吗？删除机制是什么？嘻哈的简写笔记——Redis_嘻哈怪才的博客-CSDN博客_redis的key过期了会自动删除吗](https://blog.csdn.net/weixin_52578409/article/details/110258985)

- [Loopback接口_百度百科 (baidu.com)](https://baike.baidu.com/item/Loopback接口/18883959?fr=aladdin)

- [loopback_百科 (k4china.com)](http://www.k4china.com/article-32-94058-0.html)

- [2020-07-22：你觉得使用redis的主从复制的时候有什么点需要注意的吗？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/408291425)

