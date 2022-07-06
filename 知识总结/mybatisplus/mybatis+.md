# MybatisPlus

- [简介 | MyBatis-Plus (baomidou.com)](https://baomidou.com/pages/24112f/#特性)
- mybaitis的增强工具，只是增强mybatis而不改变mybatis,简化开发，提高效率。在不编写任何sq的情况下，快速的实现对单表的CRUD、批量、逻辑删除、分页等操作。还能整合各种插件。（它可以代替逆向工程）
- **注意在使用时，因为我们还是需要用它提供的类来组装我们的sql，所以最好不要在service中进行直接使用，应该再封装一层（相当于xml层转到了这一层），service中应该进行调用即可**
- mp不依赖于mybatis,你可以完全像以前一样进行操作。

## 特性

- **无侵入**：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
- **损耗小**：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
- **强大的 CRUD 操作**：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
- **支持 Lambda 形式调用**：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
- **支持主键自动生成**：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
- **支持 ActiveRecord 模式**：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
- **支持自定义全局通用操作**：支持全局通用方法注入（ Write once, use anywhere ）
- **内置代码生成器**：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
- **内置分页插件**：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
- **分页插件支持多种数据库**：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
- **内置性能分析插件**：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
- **内置全局拦截插件**：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作

## 整合Springboot

- [Maven配置远程仓库_IT刘华强的博客-CSDN博客_maven配置远程仓库](https://blog.csdn.net/jiguquan3839/article/details/89287018)

- [maven配置远程仓库 - 海阔天空990 - 博客园 (cnblogs.com)](https://www.cnblogs.com/maxiaofang/p/5824220.html)

- [Maven配置文件的设置，远程仓库和本地仓库_xiaoWangZi89的博客-CSDN博客](https://blog.csdn.net/xiaoWangZi89/article/details/105749106/)

- 导入mybatis-plus的启动依赖以及mysql驱动即可。

  ```xml
  <dependency>
      <groupId>com.baomidou</groupId>
      <artifactId>mybatis-plus-boot-starter</artifactId>
      <version>3.5.1</version>
  </dependency>
  ```

  ```properties
  # springboot默认数据源
  spring.datasource.type= com.zaxxer.hikari.HikariDataSource
  # 设置数据源的属性
  # 设置mysql5驱动类
  spring.datasource.driver-class-name= com.myslq.jdbc.Driver
  # 连接地址
  spring.datasource.url= jdbc:mysql://localhost:3306/test
  spring.datasource.username=root
  spring.datasource.password=123456
  ```

  

- 定义一个Mapper接口

  ```java
  @Repository//标识位持久层组件，相当于是将这个用userMapper进行spring注册
  public interface UserMapper extends BaseMapper<User>{
      //继承BaseMapper，这个接口中定义很多基本操作的方法
  }
  
  /**
  这两种注解的区别在于：
      1、使用@mapper后，不需要在spring配置中设置扫描地址，通过mapper.xml里面的namespace属性对应相关的mapper类，spring将动态的生成Bean后注入到ServiceImpl中。
      2、@repository则需要在Spring中配置扫描包地址，然后生成dao层的bean，之后被注入到ServiceImpl中
  **/
  
  ```

  

- 在springboot启动类中添加@MapperScan("包路径")，将扫描器扫描下生成的Mapper代理类托管给Spring容器

  ```java
  //使用自动注入
  
  @Autowired 
  private UserMapper userMapper;
  ```

  

- mybatis的使用方法

  - [MyBatis使用步骤及原理_ForFuture Code的博客-CSDN博客_mybatis使用](https://blog.csdn.net/ganquanzhong/article/details/80197378)

  - 直接使用配置文件
    - 编写sql操作配置文件
    - 将配置文件注册到mybatis的主文件中
    - 在spring配置文件中注册sqlsessionfactory，将mybatis主配置文件引入作为属性
    - 使用时只能自己去封装一个自己的sqlSession 然后将其注册到spring中，再将其注入到需要使用的地方。这个要使用只能通过方法id进行调用。
  - 使用mapper代理
    - 编写接口，编写配置文件，配置文件注册到mybatis主配置文件
    - 将mybatis文件注册到spring主配置文件
  - 使用mapper代理
    - 不在单个注册
    - mybatis中整体包注册
  - 同样使用mapper代理
    - 在spring主配置文件中进行包扫描，并且指定mapper的xml位置（不配置默认和接口同一目录）
  - 使用注解实现
    - 不配置扫描包，只需要在每个接口指定mapper注解
    - 编译时扫描到注解就根据xml生成代理类并注册到spring
  - 使用repository+包扫描
    - 编译时，先扫描生成代理类进行注册。repository仅仅进行声明这个是spring组件。（加不加实际上不影响，主要作用是告诉spring这个已经被注册到spring中罢了）

  ## 日志输出

  ```yaml
  mybatis-plus:
    configuration:
      log-impl: 指定一个日志实现类的全限定名
  #配置类型别名
  type-aliases-package: pojo
  ```

  

- 执行分析

  - 扫描实体，反射抽取实体属性，然后根据属性和实体名，分析表名和表列。通过分析结果生成sql语句。然后将sql语句注入到mybatis的容器中。

## 常用操作

- insert:mybatisplus自己会生成id,并且会将其返回到插入对象的属性id中
- deleteByMap(Map<String,Object>):传入一个map,里面放条件键值对。
- deleteBatchIds(List):传入一个id数组，进行批量删除。其生成的sql语句是使用in进行实现。
- updateById(实体)：通过id进行更新
- selectById(id值)：通过id查询
- selectBatchIds(List)：传入id数组，批量查询
- selectByMap(Map<String,Object>):传入一个map,里面放条件键值对。查询给定条件的信息。返回一个List数组。
- selectList（条件构造器）：根据条件构造器查询，没有条件可以传入null.如果不指定条件就会进行全部查询，但是如果应用到更新和删除中也是更新删除所有。
- 自定义方法和使用maybatis一样进行定义。不过我们可以借鉴它的实现方式来封装条件。

## Service层

- [为什么不推荐使用存储过程？_zl1zl2zl3的博客-CSDN博客_mysql存储过程为什么不推荐使用](https://blog.csdn.net/zl1zl2zl3/article/details/92664078)

- 自定义service接口继承IService
- 在指定义实现类实现自定义的service,并且继承IService的实现类ServiceImpl
- count():查询表的总记录数。
- saveBatch(List<实体>)：批量添加（mapper中是没有批量添加的方法的，因为insert一次性执行只能添加一条记录。所以如果体现在mapper层，那么就会造成大量的iinsert冗余，我们应该在service层进行循环操作）

##plus存在的一些问题

- plus推断操作表完全是由mapper指定的泛型决定，如果你表名和实体类名不一致就在实体类上使用@TableName("表名")进行解决。

  - 你还可以使用全局配置进行解决表名同一前缀的问题

    ```yaml
    mybatis-plus:
      global-config:
        db-config:
        #设置实体表同一前缀
          table-prefix: t_
    ```

    

- mp默认使用实体的id属性作为主键，你如果不是使用这个作为名称就会报错。要解决就在实体类的主键上使用@TableId（告诉mp，这个属性对应的表中的字段是主键）

  - value属性：指定该属性对应的表中的主键字段。
  - type属性：主键自增策略
    - IdType.NONE（使用全局配置）、IdType.AUTO（自增，与数据是否设置自增有关系）、IdType.ASSIGN_ID（使用雪花算法，于数据库是否设置自增没有关系）
  - **注意如果主动指定了主键，那么策略不会生效，插入时使用指定的值**

- 全局配置主键生成策略：

  ```yaml
  mybatis-plus:
    global-config:
      db-config:
        #设置统一的生成策略
        id-type: auto
  ```

- 非主键也出现属性名和字段名不一致的情况，可以使用@TableFiled("字段名")

  - mybatis中我们使用的是resultMap进行映射

- **mp默认支持驼峰命名，属性用驼峰，字段用__**，驼峰命名可以通过全局配置进行取消

- 逻辑删除，指定一个字段，未删除是0，删除是1.在实体属性中使用@TableLogic("")，指明这个是逻辑删除字段，如果进行删除就是使用逻辑删除。且查询也只会查询字段值未0的数据

  - 如果我们要进行真实删除就只能使用自定义mapper配置文件执行sql。

## 雪花算法

- 背景

  需要选择合适的方案应对数据规模的增长，以应对主键增长的访问压力和数据量。

  数据库的扩展方式主要包括：业务分库（不同的业务逻辑建立不同的数据库；垂直拆分业务）、主从复制（提升单个数据库的负载：读写分裂，集群处理；水平扩展服务器）、

  数据库分表（对表中数据量太大）

  - 水平分表：将数据进行分段，某些行放在一个数据库，某些放在另一个数据库中（可以理解为分区处理）
  - 垂直分表：将某些不重要的字段且占用数据量很大的，进行分离单独成立数据库，减少数据量。
  - [数据库垂直拆分 水平拆分 - 有梦就能实现 - 博客园 (cnblogs.com)](https://www.cnblogs.com/firstdream/p/6728106.html)

- 水平拆分的问题：

  - 如何保证数据拆分均匀
    - 取模：扩展麻烦。需要重新进行分布
    - 定量
  - 如何保证全局的主键的唯一

## 条件构造器

- wrapper:用于封装我们的sql中涉及的条件
- 与Map的放条件的区别，Map主要是进行where子句进行条件组装。条件单一，wrapper可以有多种类型的条件拼接，比如like、group by、null.....
- 类继承图

  - Wrapper:条件构造抽象类，顶级父级

    - AbstractWrapper：用于查询条件封装，生成sql的where条件

      - QueryWrapper:查询条件封装，**这个查询条件往往不仅仅是指select，而是指where后面的查询筛选条件以及要映射的字段**

      - UpdateWrapper:update条件封装
      - AbstractLambdaWrapper:使用Lambda语法
        - LambdaQueryWrapper:用于Lambda语法使用的查询Wrapper
        - LambdaUpdateWrapper:Lammbda更新封装Wrapper
- 组装查询条件

  - QueryWrapper q=new QueryWrapper();
  - q.like('字段名','匹配字符串').between().isNotNull()........
  - userMapper.selectList(q)
  - userMapper.update(user,q);//user用于存储更新的实体信息，q是筛选条件
  - q.and(Consumer<Parm>):这里的参数是一个消费者函数式接口实例，参数类型也是一个条件包装器，注意一旦使用lambda表达式，这里面的条件就会优先执行。原因是会使得and会带上().or也有这种方法。
    - 这个and是带上()
  - q.select('字段名'，‘字段名’):
- 子查询：
  - q.insql()
- UpdateWrapper:主要作用就是进行字段更新设置。
  - set("字段名"，“字段值”)：更新字段

- 注意条件组装，在实际开发中需要进行判断再来进行组装。

###使用condition组装条件

- 就是可以不用自己创建条件进行判断，它直接合在一起进行组装：
  - q.like(age!=null,"name","a").ge(price!=null,"price",price)

- 就是说这种方式实际场景中使用的最多

### Lambda包装器

- **主要用于解决字段名容易写错的问题**，它的主要变化的就是将我们要使用字段名的地方采用函数式接口进行代替。
- q.like(age!=null,User::name,"%s%");//查询年龄不为空的名字含s的记录。

## mp提供的插件

- 分页插件

  - mp自带分页插件，只要简单配置即可使用分页
  - 实现的原理，创建一个拦截器，将我们的请求进行拦截，然后根据类型和参数进行动态添加分页子句。

  ```java
  //自定义一个spring配置类
  @Configuration
  @MapperScan("xhu.mapper")
  public class MpConfig{
      @Bean
      public MybatisPlusInterceptor mybatisPlusInterceptor(){
          MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();//创建一个拦截器
          interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//注入一个分页拦截器并指定分页的sql类型是MySQL。
          interceptor.addInnerInterceptor(new OptimisticLockInnerceptor());//可见这个拦截器处理方式是采用管道模式
          return interceptor;
      }
  }
  
  //使用
  public class Test{
      @Autowire
      private UserMapper userMapper;
      @Test
      test(){
          //创建一个分页对象
          Page<User> page=new Page<>(1,3);//要显示第几页，显示的记录条数
          userMapper.selectPage(page,条件包装器);
          //因为当前查询出来的数据就放在page中，你可以通过page获取当前查询的数据页信息，比如总记录数、所有数据、有没有上一页、下一页
      }
  }
  ```

  

- 如果我们有一条自定义的查询语句没有分页功能，现在我们想要通过分页插件为其添加分页功能

  - 实现原理，因为一旦配置了mp的拦截器，那么所有的sql请求经过mybatis时都会经过拦截器的处理，我们只需要保证我们定义的接口中传入的参数有分页对象并且返回分页对象即可，这样mp拦截器就会通过分页对象的参数进行sql处理，当执行结果返回时就能通过分页对像进行获取相关内容。

  - 这样的设计方式是否会产生性能瓶颈，通过拦截器的方式第一点就是我们的sql请求需要进行统一化执行一块代码，第二点返回需要二次封装，将其封装为Page对象。一是内存，二是操作的多了额外的操作。所以分页插件选择性使用。

    - 版本号的主要目的是解决ABA问题，如果是使用CAS这样的操作，那么是不用检测版本号的，乐观锁我们可只使用版本号进行解决。
  - [mybatis分页插件pageHelper效率问题 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/news/706973)
  
- mybatis乐观锁插件

  - 表中添加一个字段：version，作为每次检查的版本号
  - 在配置类中添加乐观锁插件，可以说这个插件的操作仅仅只是进行版本号检测，具体要彻底完成还需要我们自己处理。
  - 每次对这条记录进行操作时，就会修改这个版本号
  - 而实体的属性中同样也是需要一个version属性，并且可以指定@Version,这样sql中会添加条件进行版本检测，如果版本符合就能找到数据修改，进行修改时就会同时修改这个版本号。如果不匹配就不会执行，返回0.**所以要具体进行实现就需要实现一个循环进行直到更新成功，本质上还是我们自己实现的乐观锁-或者说偏向锁**

## 通用枚举

- 枚举类型可以有多个属性，我们常常将枚举作为常量集合，如果一个类中持有一个枚举成员，但是这个成员只是代表一个意思（比如男女），但是这个枚举类型对应到数据表中仅仅是一个基本类型，那么是无法进行映射。所以需要解决。**准确的说这个是多表查询的问题**

- 只不过mp提供了解决枚举类的方法。

- 具体使用：

  - 使用@EnumValue标注枚举类中与表字段实际对应的属性。

  - 然后在主配置中指定扫描枚举包：

    ```yaml
    type-enums-package: com.enumx
    ```

## MP 代码生成器

- [代码生成器配置旧 | MyBatis-Plus (baomidou.com)](https://baomidou.com/pages/061573/#数据库表配置)

- [Mybatis-plus逆向工程使用方法_学无止境小奇的博客-CSDN博客](https://blog.csdn.net/weixin_44096133/article/details/122946466)

  ```java
      @Test
      public void getGenerator() {
          //项目路径
          System.out.println(System.getProperty("user.dir"));
  
          //代码生成器
          AutoGenerator autoGenerator = new AutoGenerator();
  
          //1.全局配置 调用generator.config下的
          GlobalConfig gc = new GlobalConfig();
          //获取当前项目的路径
          String path = System.getProperty("user.dir");
          //设置是否开启AR
          gc.setAuthor("chz")
                  //文件输出路径
                  .setOutputDir(path + "/src/main/java")
                  //生成时是否打开文件
                  .setOpen(false)
                  //是否覆盖文件
                  .setFileOverride(true)
                  //设置主键自增策略
                  .setIdType(IdType.ASSIGN_UUID)
                  //DO中日期类的类型, 缺省值为TIME_PACK
                  .setDateType(DateType.TIME_PACK)
                  //是否开启resultMap,默认false
                  .setBaseResultMap(true)
                  //是否开启sql片段,默认false
                  .setBaseColumnList(true)
                  //去掉service接口首字母的I, 如DO为User则叫UserService
                  .setServiceName("%sService")
                  //开启Swagger2
                  .setSwagger2(true);
  
  
          //2.数据源配置
          DataSourceConfig dataSourceConfig = new DataSourceConfig();
          //设置数据源类型
          dataSourceConfig.setDbType(DbType.MYSQL)
                  .setDriverName("com.mysql.cj.jdbc.Driver")
                  .setUrl("jdbc:mysql://localhost:3306/gedu?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai")
                  .setUsername("root")
                  .setPassword("12345");
  
          //3.策略配置
          StrategyConfig strategyConfig = new StrategyConfig();
          //是否开启大写命名,默认不开启
          strategyConfig.setCapitalMode(false)
                  //生成的DO自动实现Serializable接口, 默认true
                  .setEntitySerialVersionUID(true)
                  //数据库 表 映射到实体类命名策略
                  .setNaming(NamingStrategy.underline_to_camel)
                  //数据库表 字段 映射到实体类的命名策略
                  .setColumnNaming(NamingStrategy.underline_to_camel)
                  //设置想要生成的表
                  .setInclude("edu_teacher")
                  //生成的dao,service,entity不再带tbl_前缀
                  .setTablePrefix("edu_")
                  //设置lombok, @Accessor(chain = true),@Data等
                  .setEntityLombokModel(true)
                  //controller使用@RestController
                  .setRestControllerStyle(true)
                  //Mapping驼峰转连字
                  .setControllerMappingHyphenStyle(true)
                  //自动填充字段
                  .setTableFillList(Arrays
                          .asList(new TableFill("gmt_create", FieldFill.INSERT),
                                  new TableFill("gmt_modified",FieldFill.INSERT_UPDATE)))
  //                .setVersionFieldName("")//乐观锁属性名
                  //表中字段为is_deleted,生成的DO中去掉is前缀
                  .setEntityBooleanColumnRemoveIsPrefix(true)
                .setLogicDeleteFieldName("deleted");//逻辑删除属性名
  
  
          //4.包配置
          PackageConfig packageConfig = new PackageConfig();
          //setParent设置统一的包路径
          //设置模块名,对应controller中使用servicedu作为url, 如@RequestMapping("/servicedu/teacher"), 所有生成的都会在以该模块名为的包下
          packageConfig.setModuleName("servicedu")
                  .setParent("com.chz")
                  .setMapper("mapper")
                  .setService("service")
                  .setController("controller")
                  .setEntity("entity")
                  .setXml("mapper");
  
          //整合配置
          autoGenerator.setPackageInfo(packageConfig)
                  .setDataSource(dataSourceConfig)
                  .setGlobalConfig(gc)
                  .setStrategy(strategyConfig);
          //执行
          autoGenerator.execute();
      }
  
  ```
  
  

## 多数据源

- [【SpringBoot DB 系列】Mybatis-Plus 多数据源配置 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/344986030)

- [常用的数据源_八座城的博客-CSDN博客_数据源有哪些](https://blog.csdn.net/weixin_37377718/article/details/69666627)

- [mybatis-plus中多数据源切换@DS注解到底放在哪一层合适？？？ - SegmentFault 思否](https://segmentfault.com/q/1010000041340273/)

- [面试官问你为什么做多数据源切换时，会使用到ThreadLocal (baidu.com)](https://baijiahao.baidu.com/s?id=1651506162577833204&wfr=spider&for=pc)

- 为什么一定要进行数据源切换，一个数据源（数据库的jdbc连接池，为什么应用程序管理连接池，因为因公用程序是主动连接方），往往对应一个数据库，应用程序的每个线程只能从一个数据源中获取到连接。所以你要访问不同的数据库就必须进行数据源的切换，来获取连接访问数据库。

- 目的就是同一个程序动态的访问不同的数据库

  配置模板:[MP多数据源_萌坑的博客-CSDN博客_mp多数据源](https://blog.csdn.net/DoAsOnePleases/article/details/107555319)

  ```yaml
  ```

  

- 读写分离：只需要通过@DS("数据源名")就能进行切换数据源，那么你可以在读操作上切换到读数据库，在写操作上切换到写数据库上，然后完成读写分离。

## MybatisX插件

- [MybatisX快速开发插件 | MyBatis-Plus (baomidou.com)](https://baomidou.com/pages/ba5b24/)

- mp只能进行简单的单表操作，但是对于复杂的sql，多表联查。
- 这个是基于idea的开发插件，直接idea安装插件即可。
- 功能1：切换配置文件和mapper接口
- 功能2：mybatis的逆向工程，不过不要你写配置文件，idea连接好数据库，选择使用插件，修改生成条件即可。
- 功能3:可以选择性的生成sql。
- 总体来说MP与mybatis区别不大

## mybatis缓存

- 

## mybatis缓存与redis

- 共同点：都是通过数据冗余减小数据库服务器的压力
- 不同点：
  - redis解决的数量大，针对的热点数据（这个数据往往不是结构化的）。
  - mybatis解决的是重复的sql查询。同一个连接和不同的连接之间。

##mybatis逆向工程

```xml
		<!--mybatis-->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.0.0</version>
		</dependency>


<!--逆向工程插件-->
<plugin>
   <groupId>org.mybatis.generator</groupId>
   <artifactId>mybatis-generator-maven-plugin</artifactId>
   <version>1.3.6</version>
   <configuration>
      <configurationFile>GeneratorMapper.xml</configurationFile>
      <verbose>true</verbose>
      <overwrite>true</overwrite>
   </configuration>
</plugin>
```

