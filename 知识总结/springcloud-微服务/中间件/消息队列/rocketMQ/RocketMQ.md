# RocketMQ

## RocketMQ概述

- [rocketmq/docs/cn at master · apache/rocketmq · GitHub](https://github.com/apache/rocketmq/tree/master/docs/cn)
- [Quick Start - Apache RocketMQ](https://rocketmq.apache.org/docs/quick-start/)

- [RocketMQ核心架构设计思想 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/449849768)
- [一文剖析RocketMQ的设计“灵魂” - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/438800389)
- [主从 主备 和集群_开森开心的博客-CSDN博客_主备和集群的区别](https://blog.csdn.net/weixin_44597836/article/details/106560172)
- [LVS详解 - 腾讯云开发者社区-腾讯云 (tencent.com)](https://cloud.tencent.com/developer/news/59467)
- [RocketMQ一（基本概念+集群注意点）_土拨鼠饲养员的博客-CSDN博客_rocketmq生产者组](https://blog.csdn.net/lxsxkf/article/details/120222140)

## MQ

- message queue，是一种提供消息队列服务的中间件，是一套提供了**消息生产、存储、消费全过程API的软件系统**

- 用途无非以下

- **限流削峰**

  - MQ可以**将系统的超量请求暂存其中**，以便系统后期可以慢慢进行处理，从而避免了请求的丢失或系统被压垮。

    ![20220622171847](.\img\20220622171847.jpg)

- **异步解耦**

  - 上游系统对下游系统的调用若为同步调用。则会大大降低系统的吞吐量与并发度，且系统耦合度太高。而异步调用则会解决这些问题。所以两层之间若要实现由同步到异步的转化，一般性做法就是，在这两层间添加一个MQ层。
    - 可以通过观察订阅的方式将同步任务转换为异步任务。通过MQ进行状态维持。
    - 比如在秒杀系统中，在一瞬间必然系统的并发量很大，肯定不可能让所有请求打到系统上，我们可以使用MQ将订单存储起来，然后排队让库存系统进行减少。如果减少完，就直接将这些订单拒绝。就是让订单排序。

- 数据收集

  - 分布式系统会产生海量级数据流，如：业务日志、监控数据、用户行为等。针对这些数据流进行实时或批量采集汇总，然后对这些数据流进行大数据分析，这时当前互联网平台的必备计数。通过MQ完成此类数据收集是最好的选择。

- 常见的MQ

  - activeMQ：

    - activeMQ是是哦那个Java语言开发一款MQ产品。现在公司用的很少。

  - RabbitMQ：

    - RabbitMQ使用ErLang语言开发的MQ。其吞吐量较kafaka与RocketMQ要低，且由于其不是Java开发，所以公司内部对其实现定制化开发难度较大。**对于Spring Cloud Netflix，其仅支持Rabbit MQ与Kafka**

  - Kafka

    - 是使用Java/Scala语言开发的一款MQ产品。其最大的特点就是高吞吐量。常用于大数据领域的实时计算、日志采集等场景。其没有遵循任何常见的MQ协议，二是使用自研协议。

  - Rocket

    - rocketMQ是使用Java语言开发的一款MQ产品。经过数年阿里双十一的考验，性能于稳定性非常高。其没有遵循任何常见的MQ协议，使用自研协议。SpringCloud Alibaba支持RabbitMQ、Kafka，减一使用RocketMQ

    ![20220622210240](.\img\20220622210240.jpg)

- **MQ常见协议**
  - 一般情况下MQ的实现是要遵循一些常规性协议的。
  - 常见协议
    - **JMS**
      - JMS,Java Messagsing Service。是Java平台上有关MOM（面向消息中间件）的技术规范，它便于消息系统中的Java应用程序进行消息交换，并且通过提供标准的产生、放、接收消息的接口，简化企业应用的开发。ActiveMQ是该协议的典型实现。
      - PO/OO/AO/MO(面向过程、面向对象、面向切面、面向消息)
    - **STOMP**
      - Streaming Text Orientated Message Protocol,是一种MOM设计的简单文本协议。STOMP提供一个客户操作的连接格式，允许客户端与任意STOMP消息代理（Broker）进行交互。ActiveMQ是该协议的典型实现，RabbitMQ通过插件可以支持该协议。
    - AMQP
      - Advanced Message Queuing Protocol，一个提供统一消息服务的应用标准，是应用层协议的一个开放标准，是一个MOM设计。基于此协议的客户端与消息中间件可传递消息，并不受客户端/中间件不同产品，不同开发语言等条件的限制。RabbitMQ是该协议的典型实现。
  
- RocketMQ发展历程

  ![20220623090438](.\img\20220623090438.jpg)

## RocketMQ的安装与启动

### 基本概念

[RocketMQ角色详解之Broker_喜鹊先生Richard的博客-CSDN博客_broker rocketmq](https://blog.csdn.net/Cactus_Lrg/article/details/86706457)

- **消息：（Message）**

  - 消息是指，消息系统**所传输信息的物理载体**，**生产和消费数据的最小单位**，每条消息必须属于一个主题(Topic)-即是消息种类。

- **主题：（Topic）**

  - 表示一类消息的集合，每条消息只能属于一个主题，是RocketMQ进行消息订阅的基本单位。
  - **一个生产者可以同时发送多种Topic的消息**，**而一个消费者只对某种特定的Topic感兴趣**，即只可以订阅和消费一种Topic的消息。（消息的产生是复杂的，根本不用区分，但是消息本身我们可以进行分类，然后按类别处理，这对于消息处理者-消费者是比较高效 的，因为它将消息种类的区分和消息的处理进行了解耦）

- **队列（Queue）**

  - **存储消息的物理实体**。**一个Topic中可以包含多个Queue**，每个Queue中存放的就是该Topic的消息。一个Topic的Queue也被称为一个Topic中**消息的分区**（Partition）。

  - **一个分区中的消息只能被*一个消费组中的消费者消费*，即是同一组的消费实例不能交叉访问分区。一个分区可以被不同的消费组中的某一个消费实例同时消费。**(注意：一个消费组中的消费者可以消费多个队列中的消息，但是一个队列只能被同一个组中的一个消费者消费，不同消费者组中的某一个消费者可以消费同一个队列。就是一个队列不能被同一组的消费者共享，可以被不同的进行共享。)

    ![20220623102351](.\img\20220623102351.jpg)

- **分片（Sharding）**

  - 分片不同于分区。在RocketMQ中，分片指的是存放响应Topic的Broker。每个分片中会创建出响应数量的分区，即Queue，**每个Queue的大小都是相同的**。

    ![20220623103802](.\img\20220623103802.jpg)

- **消息标识（MessageId/Key）**

  - [JAVA类的hashCode - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/164966897)

  - RocketMQ中**每个消息拥有唯一的MessageId（可能重复，在并发极大的情况下、时间篡改、计数器重置）**，且可以携带具有业务标识的**Key**。**以方便对消息的查询**。不过需要注意的是，MessageId有两个：在生产者send（）消息时会自动生成一个MessageId（msgId）,当消息到达Broker后，Broker也会自动生成一个MessageId（offsetMsgId）。msgId、offsetMsgId、key都被称为消息标识。

    - msgId:由producer端生成，其生成规则为：

      producerIp+进程pid+MessageClientIDSetter类的ClassLoader的hashCode+当前时间+AutomicInteger自增计数器

    - offsetMsgId:由broker端生成，其生成规则为：brokerIp+物理分区的offset（就是信息在队列中的偏移量）

    - key:由用户指定的业务相关的唯一标识
  
    - 通过这些ID我们可以对消息进行溯源。

### 系统架构

- [docs/cn/architecture.md · Apache/RocketMQ - Gitee.com](https://gitee.com/apache/rocketmq/blob/master/docs/cn/architecture.md)

- 主要分为四个部分：四个部分都是集群部署

  - **Producer**

    - 消息生产者，负责消息生产。Producer通过MQ的负载均衡模块选择响应的Broker集群队列进行消息投递，投递的过程支持快速失败并且低延迟。

    - RocketMQ中**消息生产者都是以生产者组**的形式出现的。生产者组是同一类生产者的集合，这类Producer发送相同Topic类型的消息。
    
  - **Consumer**
  
    - 消息消费者，负责消费消息。一个消息消费者会从**Broker服务器**中获取到消息，并对消息进行相关业务处理。
    - RocketMQ中的消息消费者都是以消费者组的形式出现的。消费组是同一类消费者的集合，**这类Consumer消费的是同一个Topic类型的消息**。消费者组使得在***消息消费方面***，实现负载均衡和容错的目标变得非常容易。
    - **负载均衡：就是指将一个Topic中的不同的Queue平均分配给同一个Consumer Group，不同的Consumer。并不是将消息负载均衡**
    - **容错：一个Consuemr挂了，该Consumer Group中的其他Consumer可以接着消费该Consuermer消费的Queue** 
    - 注：生产者是向Broker中放资源，所以是在访问Broker，所以是Broker端实现负载均衡。而消费者是要拿取消息，所以同样是访问Broker，同样是由Broker实现负载均衡。（**故有下：Broker既有面向生产端的负载均衡策略，又有面向消费端的负载均衡策略**）
    - 注：这里的消费端负载均衡（**回顾前面的消费规则**）仅仅只是说是队列的负载能够均匀分配，但实际消息是否被均匀分配是不确定的。
    - 注：**Consumer Group中的Consumer数量应该小于等于订阅Topic的Queue数量**。如果超出Queue数量，则多出的Consumer将不能消费消息。一个Topic类型的消息可以被多个消费者组同时消费。
    - 注：**一个消费者组中的消费者必须订阅完全相同的Topic，消费者组只能消费一个Topic的消息，不能同时消费多个Topic消息**
    
  - **NameServer**
    - NameServer是一个Broker与Topic路由的注册中心，支持Broker的动态注册与发现。
    
    - RocketMQ的思想来自于kafka，而kafka依赖于Zookeeper在MetaQv1.0与v2.0版本中，依赖的仍是Zookeeper。从MetaQv3.0,即RocketMQ去掉了Zookeeper依赖。（那么我们其实可以将ＭＱ理解为一个微服务系统。）
    
    - 主要包括两个功能：
      - Broker管理：接受Broker集群的注册信息并且保存下来作为路由信息的基本数据，提供心跳检测机制，检查Broker是否还存活。
      - 路由信息管理：每个NameServer中都保存着Broker集群的整个路由信息和用于客户端查询的队列信息。**Producer和Consumer通过NameServer可以获取整个Broker集群的路由信息，从而进行消息的投递和消费。**
      
    - **路由注册**
      - NameServer通常也是以集群的方式部署，不过，NameServer是**无状态的**，即NameServer集群中的各个节点间是无差异的，**各节点间相互不进行信息通讯**。那各节点中的数据是如何进行数据同步的呢？**在Broker节点启动时，轮询NameServer列表，与每个NameServer节点建立长连接，发起注册请求。在Name Server内部维护着一个Broker列表，用来动态存储Broker的信息。**（也就是说，每个NameServer是独立的监听者，假如Broker宕机，监听者之间不会相互传播，而是由自己通过心跳检测。）
      - 注：这个与zk、eureka、nacos等注册中心不同的地方
      - 优点：NameServer集群搭建简单
      - 缺点：NameServer扩展性差，因为对于每个Broker必须明确配置好已知的NameServer，对于新增的NameServer,Broker是不知道的，所以不能随便扩容，要扩容，Broker需要重新配置。
      - Broker节点为了证明自己是或者的，为了维护与NameServer间的长连接，会将最新的信息以心跳包的方式上报给NameServer，每30秒发送一次心跳。**心跳包中包含BrokerId、Broker地址（IP+Port）、Broker名称、Broker所属集群名称等等。NameServer在接收到心跳包后，会更新心跳时间戳，记录这个Broker的最新存活时间。**
      
    - **路由剔出**
      - 由于Broker关机、宕机或网络抖动等原因，NameServer没有收到Broker的心跳，NameServer可能会将其从Broker列表中剔出。
      
      - NameServer中有一个定时任务，每隔10秒就会扫描一次Broker表，**查看每一个Broker的最新心跳时间戳距离当前时间是否超过120秒，如果超过，则会判定Broker失效，然后将其从Broker列表中剔除。**
      
      - 这里没有自我保护机制。
      
        >
        >
        >扩展：对于RocketMQ日常运维工作，例如Broker升级，需要停掉Broker的工作。OP（运维工程师，也叫SRE－现场可靠性工程师）需要怎么做？
        >
        >*1.先停掉Broker的读写功能关闭，一旦client向broker发送消息，都会收到NO_PERMISSION响应，然后client会进行对其它Broker的重试。就是所有的生产者消费者的读写权限关闭。*
        >
        >*2.当OP观察到这个Broker没有流量后，再关闭它，实现Broker从NameServer的移除*
      
    - **路由发现**
      
      - RocketMQ的路由发现采用的是Pull模型。当Topic路由信息出现变化时，NameServer不会主动推送给客户端，而是客户端定时拉取主题最新的路由。默认客户端每30秒回拉取一次最新的路由。
      
        >*1.Push模型：推送模型，当被观察者发生变化时主动通知观察者，这种模型任务压力主要在被观察方，需要对消息的推送进行维护，如果消息推送失败就需要对失败的消息和观察者进行维护**这种模型，需要维护两者之间的一个长连接，长连接的维护同样是需要被观察者端的资源的，但其实时性较好***
        >
        >- 该模型适用场景：实时性要求较高，并且Client数量不多，被观察者方数据变化较为频繁的场景，因为过多需要维护连接的资源需要更多。
        >
        >*2.Pull模型：拉取模型。观察者定时循环检测被观察者的变化。这种模型，将任务的压力主要分布在观察者方，观察者需要耗费较多的性能进行消息的获取。并且实时性较差。*
        >
        >3.**Long Polling模型**：长轮询模型，同样是观察者定时检测数据是否变化，但是检测没有变化时，并不会立即将两者之间的连接销毁掉，而是维护一段时间，如果这段时间内数据发生变化，那么我们就直接将数据拉取。例如NacosConfig使用的就是这种模型。可以说这种模型就是一种折中的模型。对于突发数据有比较好的应对，又能不过分消耗被观察者端的资源。
      
    - **客户端NameServer选择策略**
    
      - 客户端（***这里客户端指的是Producer与Consumer***）在配置时必须要写上NameServer集群（NameServer服务端集群）的地址，那么客户端到底连接的是哪个NameSerevr节点呢？**客户端首先回生成一个随机数，然后再与NameServer节点数量取模，此时得到的就是索要连接的节点索引，然后就会进行连接**。如果连接失败，则会采用**round-robin**（轮询）策略，逐个尝试着去连接其他节点。（这可以说是客户端的负载均衡策略）
      
        >
        >
        >扩展：Zookeeper Clinent是如何选择Zookeeper的？
        >
        >*经过两次Shuffle(洗牌),然后选择第一台Zookeeper Server*
        >
        >详细说：将配置文件中的zk srever地址进行第一次shuffle,然后随机选择一个。这个选择出的一般都是一个**hostname**。然后获取到该**hostname**对应的所有ip，再对这些ip进行第二次shuffle.
    
  - **Broker**
  
    - Broker充当着**消息中转角色，负责存储消息，转发消息**。Broker在RocketMQ系统中负责**接收并存储从生产者发送来的消息**，同时**为消费者的拉取请求做准备**。Broker同时也**存储消息相关的元数据**，包括消费者组消费进度偏移offset、主题、队列等。
  
      >Kafka0.8版本之后，offset是存放在Broker中的，之前版本是存放在Zookeeper中
  
    - Broker模块组成
    
      ![20220723141507](img\20220723141507.jpg)
    
      - RemotingModule:整个Broker的实体，负责处理来自clients端的请求。而这个Broker实体则由一下模块构成。
    
        >**Client Manager:**客户端管理器。负责接收、解析客户端（Producer/Consumer ）请求，管理客户端。例如，维护Consumer的Topic订阅信息
        >
        >**Store Service:**存储服务。提供方便简单的API接口，处理消息存储到物理硬盘和消息查询功能。
        >
        >**HA Service:**高可用服务，提供Master Broker和Slave Broker之间的数据同步功能。
        >
        >**Index Service:**索引服务。根据特定的Message key，对投递到Broker的消息进行索引服务，同时也提供根据Message Key对消息进行快速查询的功能。
    
      - Broker集群
    
        >**主从**：“从机”的“从”可以理解为“仆从”，仆从是要帮主人干活的，“从机”是需要提供读数据的功能的；
        >**主备**：“备机”一般被认为仅仅提供备份功能，不提供访问功能。（往往需要人工干预才能提供正常主机的功能）
        >所以使用“主从”还是“主备”，是要看场景的，这两个词并不是完全等同。
        >一般”主从集群“和”主备集群“一起使用，**让备机也提供读的服务，当主机宕机时备机代替主机工作提供写服务，其他从机继续提供读服务。**
        
        - [RocketMQ学习笔记（2）----Broker的集群四种方式 (wjhsh.net)](http://www.wjhsh.net/Eternally-dream-p-9925747.html)
    
          ![img](img\96132eef6cee4474a4f6b8d57fb4df40.png)
          
        - 为了增强Broker性能与吞吐量，Broker一般都是以集群形式出现的。个集群节点中可能存放着相同Topic的不同Queue。不过，这里有个问题，如果Broker节点宕机，如何保证数据不丢失呢？其解决方案是，将每个就Broker集群节点进行横向扩展，即将Broker节点再建为一个HA集群，解决单点问题。
        
        - Broker分为Master与Slave，这里的Master和Slave之间的关系是主备关系，也就是说Slave仅仅进行数据备份，在Master存活期间不提供读负载，在Matser宕机后，Slave自动切换为Master进行工作。一个Matser可以对应多个Slave，一个Slave只能属于一个Matser。Master与Slave的对应关系是通过指定相同的BrokerName，不同的BrokerId来确定的。BrokerId为0标识Master，非0标识Slave。每个Broker与NameServer集群中的所有节点建立长连接，定时注册Topic信息到所有NameServer。（Slave会进行注册吗？应该会，类似redis的哨兵机制）
        
          [rocketMQ学习（2）：broker向nameserv注册 - 鱼尾 - 博客园 (cnblogs.com)](https://www.cnblogs.com/yuyuyuyuyu/articles/16149135.html)
    
  - **工作流程**
  
    - 启动NameServer,NameServer启动后开始监听端口，等待Broker、Producer、Consumer连接。
    - 启动Broker时，Broker会与所有的NameServer建立并保持长连接，然后每30秒向NameServer定时发送心跳包。
    - 收发消息前，可以先创建Topic，**创建Topic时需要指定该Topic要存储在那些Broker上**，当然，在创建Topic时也会将Topic与Broker的关系写入到NameServer中。不过，这步是可选的，也**可以在收发消息时自动创建Topic。**
    - Producer发送消息，**启动时先跟NameServer集群中的其中一台建立长连接**，并从Name Server中获取路由信息，**即当前发送的Topic的Queue与Broker的地址（IP+Port）的映射关系**。然后根据算法策略从队列选择一个Queue，**与队列所在的Broker建立*长连接*从而向roker 发消息**。当然，在获取到路由信息后，Producer会首先将路由信息缓存到本地，再每30秒从Name Server更新一次路由信息。
    - Consumer跟Produce类似，**跟其中一台Name Server建立长连接**，获取其所订阅Topic的路由信息，然后根据算法策略从路由信息中获取到其索要消费的Queue，然后直接跟Broker建立长连接，开始消费其中的消息。Consumer在获取到路由信息后，**同样也会每30秒从NameServer 更新一次路由信息。**不过不同于Producer的时，**Consumer还会像Broker发送心跳，以确保Broker的存活状态**。
    - 综上一下Producer与Consumer：两者需要NameServer提供Broker的路由信息，然后通过各自的负载算法进行Broker定位，然后与Broker建立**长连接**进行消息的生产与消费。（NameServer 仅仅是进行路由信息管理，提高系统的扩展性和可维护性。Broker的选取由消费和生产者通过路由信息确定，Broker与其他三个集群直接交互。）**NameServer是系统交互的核心，Broker是仓库，Producer是入口，Consumer是出口**
    - **注意：Producer与Consumer在发送和拉取消息之前，就通过算法运算路由信息得到了*具体唯一的Queue***
  
  - **Topic的创建模式**
  
    - 集群（这个集群指的是整个Broker集群，不是指单个的M-S集群）模式：该模式下创建，Boker集群中每个Broker的Topic所对应的Queue数量一致。
  
    - Broker模式：该模式下，每个Broker中的Queue的数量可以不一样。
  
    - **注意：收发消息前如果自动创建Topic，则默认采用的是Broker模式，且为每个Broker默认创建4个Queue。**
  
      >Topic创建时的参数：
      >
      >- clusterName：集群名称
      >- Broker_NAME：broker名
      >- topicName:主题名
      >- writeQueueNums:写队列数量
      >- readQueueNums:读队列数量
      >- perm:当前Topic的操作权限，一共26个值，每个值代表不同的权限，6代表Topic的读写权限都开放。2表示只写，4表示只读，6表示读写。
  
  - **读写队列**
  
    - 从物理上讲，读写对俄是统一队列。所以，不存在**读写队列同步问题**（就是两个队列之间数据同步）。读写队列是逻辑上进行区分的概念。一般情况下，读写队列数量是相同的。
  
    - 例如，创建Topic时设置的写队列数量为8，读数量为4，系统会创建8个queue，分别时0、1、2、3、4、5、6、7.Producer会将消息写入到这8个队列，但Consumer只会消费0、1、2、3这4个队列中的消息，4、5、6、7中消息是不会被消费到的。
  
    - 再如，创建Topic时设置的写队列数量为4，读数量为8。Producer会将消息写入到0、1、2、3，而Consumer会消费0-7这8个队列中的消息，但4-7是没有消息的。**此时假设ConsumerGroup中包含两个Consumer，Consumer1消费0-3，而Consuemr2消费4-7.但是实际情况是Consumer2没有消费消息。**
  
    - **当读写队列数量设置不同是，总是由问题，为什么要这样设计？？**
  
      >**这样设计的目的是为了，方便Topic的Queue的缩容**
      >
      >例如：原来创建的Topic中包含16个Quene，如何能够使其Queue缩容为8个，还不会丢失消息？可以动态修改写队列数量为8，读队列数量不变。此时新的消息只能写入到前8个队列，而消费者消费的确实16个队列中的数据。当发现后8个Queue中的消息消费完毕后，就可以再将都队列数量动态设置为8。整个缩容过程，没有丢失任何消息。

###单机安装与启动

- 

