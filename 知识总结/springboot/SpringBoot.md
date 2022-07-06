#SpringBoot2简介

- [尚硅谷雷神SpringBoot2零基础入门springboot全套完整版（spring boot2）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV19K4y1L7MT?p=3&spm_id_from=pageDriver)

- 与spring的区别
  - spring微观就是指spring框架，宏观指的是spring构造的整个生态圈（解决应用程序一系列的方案）
  - sb的底层是spring框架，spring的底层是Java以及servlet，sb的意义就是解决配置地狱

- 简化ssm的配置，现在一般不打war包，而是打jar，就是让服务尽量独立起来。这就需要内嵌tomcat。springboot已经是进入了微服务架构。随着服务越来越多，服务之间产生的问题页越来越多，如何解决这些问题就需要springcloud。

  - https://www.jdon.com/53254|||内嵌tomcat的定义以及使用流程。这篇文章很重要

  - https://www.cnblogs.com/xiao-tao/p/10304174.html||||||内嵌tomcat最简便方式

  - [使用cargo实现jenkins自动部署远程tomcat_ouyida3的专栏-CSDN博客](https://blog.csdn.net/ouyida3/article/details/48089141)

  - 要深入理解内嵌执行的原理建议先学习tomcat执行以及热部署机制。

    ```xml
    </plugin>
    			<!-- tomcat运行插件，maven进行构建后，要部署到tomcat上的位置 -->
    			<plugin>
    				<groupId>org.codehaus.cargo</groupId>
    				<artifactId>cargo-maven2-plugin</artifactId>
    				<version>1.2.3</version>
    				<configuration>
    					<container>
    						<containerId>tomcat7x</containerId>
    						<home>D:\apache-tomcat-7.0.47</home>
    					</container>
    					<configuration>
    						<type>existing</type>
    						<home>D:\apache-tomcat-7.0.47</home>
    					</configuration>
    				</configuration>
    				<executions>
    					<execution>
    						<id>cargo-run</id>
    						<phase>install</phase>
    						<goals>
    							<goal>run</goal>
    						</goals>
    					</execution>
    				</executions>
    			</plugin>
    ```

    

- 约定大于配置

- 微服务就是因为单个业务逻辑太大，使得整个系统难以开发、维护。就将一个系统中的服务（service）进行分离作为单体单体应用系统。说白了还是就是模块划分问题，然后让不同模块进行组合，共同完成某些功能（前端的组件化开发）。显然目的：解耦、功能内聚、模块复用、组件化管理维护。

- 传统ssm单体服务系统，要面对大量的用户只需要将war包部署到多个服务器，然后做好负载均衡即可。

- 通常springboot用来构建服务单体；springcloud协调分布式之间的服务单体；spring cloud data flow做分布式中数据流处理。

- spring boot特性

  - 能够快速创建基于spring的应用程序

  - 能够直接使用Java main方法启动内嵌的tomcat服务器运行spring boot程序，不需要部署war包文件
    - 问题：**现在内嵌了tomcat，那么是spring容器先启动还是tomcat容器先启动？**
  - 提供约定的starter Pom来简化Maven配置。
  - 自动化配置，根据项目的Mavne依赖配置，Springboot自动配置
  - 提供了程序的健康检查等功能
  - 基本可以完全不适用xml配置文件，采用注解配置。

## spring boot 四大核心

- 自动配置
- 起步依赖（启动器）
- actuator（安全检测）
- 命令行界面

## springboot的两套技术栈

- 基于servlet技术栈--全栈技术
  - 比如servlet容器-servletAPI-spring安全机制-前端映射框架MVC-spring（进行服务控制）-springData（数据库访问层JDBC、JPA、NO SQL）
- Reactive Stack--响应式编程技术栈（用更小的代价的资源承载大量的并发）

### spring boot优点

- 创建独立spring应用---它将spring进行封装，避免大量的配置。同样可以向spring一样整合其他的框架
- 内嵌tomcat
- 使用启动器依赖，再次简化配置-----以前的配置我们需要进行大量的spring主文件配置maven依赖配置版本管理。
- 自动配置spring以及第三方功能
- 提供生产级别的监控，健康检查以及外部化配置
  - 运维需要根据实际的场景参数，通过自动监控可以得知应用的运行状况，并且调整参数不用重建源码，且不用重新构建部署应用。
- 无代码生成无需编写xml
- 总之就是解决配置地狱以及服务的部署
- 概述：spring boot是整合spring技术栈的一栈式框架、简化spring技术栈的快速开发脚手架
- 缺点：版本内部封装，迭代快、封装深，内部原理复杂

##第一个springboot程序

- 官方提供一个快速生成spring boot项目结构的网站，idea集成了这个网站（https://start.spring.io/）。

- 生成项目后，他会按照你的maven结构生成一个文件夹，最后这个文件夹就是你的项目所在地。这个文件夹中带有一个项目入口Java文件，这个项目启动就是运行这个文件。

  ```java
  package com.example.demo;
  
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  
  @SpringBootApplication
  public class DemoApplication {
  
  	public static void main(String[] args) {
  		SpringApplication.run(DemoApplication.class, args);
  	}
      //它运行run方法的目的其实就是解析这个文件上的注解内容，然后通过注解加载整个项目内容。
      //所以其实项目目录可以变，但是是相对于这个文件而言。
  
  }
  ```

  

- 原来我们构建一个spring项目，是创建一个maven项目，然后再pom配置文件中将各种依赖进行导入，导入完成之后进行spring配置文件的配置。再进行分成编写。而使用springboot将spring项目常常使用的依赖进行封装为自己的web依赖，而大量的配置文件也进行了简化，bean的注册管理更多的是采用了注解形式进行声明。可以说boot就是将常用的东西作为约定进行封装起来，减少开发者的不必要功夫。

- 现在依赖都是采用默认值封装起来，那么要修改依赖中的一些参数就只能使用boot提供的application配置文件进行配置。

- 参数修改

  ```application.properties
  # 修改项目端口
  server.port=8081
  
  ```

  - 修改启动图标，在resources文件夹中创建一个banner.txt文件，存入文本即可作为启动角标。

- boot推荐配置文件使用yml/yaml格式的文件进行编写。

##springboot自动装配原理

###依赖的自动配置

- 依赖配置同一管理，boot项目采用继承依赖的方式进行自动配置。这样解决绝大部分依赖的配置管理问题。

- 在项目的pom.xml配置文件中有如下内容

  - 

    ```pom.xml
    	所有的boot项目的maven库中都存在这个项目的拷贝，这个父项目是所有boot项目黑心依赖的管理者
    	<parent>
    		<groupId>org.springframework.boot</groupId>
    		<artifactId>spring-boot-starter-parent</artifactId>
    		<version>3.0.0-SNAPSHOT</version>
    		<relativePath/> <!-- lookup parent from repository -->
    	</parent>
    	<!--它是引入了一个父maven项目，这个项目的作用就是管理大量的依赖。这个项目也是一个依赖，它会被下载到你maven库中，我们知道maven库中的每个依赖都是maven项目-->
    ```
    
    

###启动器

- https://zhuanlan.zhihu.com/p/67484886
- boot单独依赖配置中引入了启动器
- 就是一个连接器，专门用于管理某个模块需要使用的大量依赖，这样应用的配置文件就显得便于管理，说白了目的就是依赖分组管理，然后把这些组引入到应用配置中，启动器就是这个组。

```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
```

- 这些启动器的有第三方或是官方提供或是自定义。

###主程序

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//标注这个应用是一个springboot程序
public class DemoApplication {

	public static void main(String[] args) {
        //将应用程序进行启动
		SpringApplication.run(DemoApplication.class, args);
        //这个方法主要做以下事情
        /**
        1.推断应用的类型是普通项目还是web项目
        2.查找并加载所有可用初始化器，设置到initializers属性中
        3.找出所有的应用程序监听器，设置到listeners属性中
        4.推断并设置main方法的定义类，找到运行的主类
        **/
	}
```

- 我们知道web启动流程：

  - 启动服务器：jre启动jvm，运行tomcat启动程序，这其中会加载setting.xml

  - 请求访问服务器目标资源：tomcat会加载web.xml,xml初始加载时，会触发首次启动监听器，监听器中初始化spring容器。

    ```xml
     <!--配置的监听器，初始化ioc容器-->
      <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
      </listener>
      <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
      </context-param>
    ```

    

  - 映射拦截配置：

    ```xml
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:springmvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
      </servlet>
      <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
      </servlet-mapping>
    ```

    

  - 综上：启动器的目的就是要将这些过程以及tomcat内嵌过程封装起来。springboot中的内置配置完全采用注解完成。所以核心就是注解扫描器以及启动器上的注解作用。

- 启动器注解关系解析：我们逐步点进注解类查看关键注解

  ```java
  @SpringBootApplication//标注一个类是启动器类
  	@ComponentScan//组件扫描器，指定扫描的组件路径-https://www.cnblogs.com/zhaixingzhu/p/12731642.html
  https://blog.csdn.net/huangjhai/article/details/104600328ura
  	@SpringBootConfiguration//表示是springboot的配置类
  		@Configuration//表示是一个spring配置类
  			@Component//表示是一个spring组件
  	@EnableAutoConfiguration//自动配置
  		@AutoConfigurationPackage//自动配置包
  			@Import(AutoConfigurationPackages.Register.class)//导入注册器
  		@Import(AutoConfigurationImportSelector.class)//自动导入选择器
  ```
  

# springboot配置

- 核心配置文件：application.properties。推荐使用yaml/yml语法

  ```properties
  #设置内嵌tomcat端口号
  server.port=8888
  #设置上下文根-context-(默认就是/)
  server.servlet.context-path=/forever
  
  ```

  

- yaml/yml语法:就是一种树状语法，可以用angular的路由树思想理解。（父子换行错位，键值之间要有空格），如果yml和properties文件同时存在最终采用properties。

  ```yaml
  server:
    port: 8888
    servlet:
      context-path: /love
  ```

  

- 多环境下核心配置文件的使用，工作中开发的环境：开发环境、测试环境、准生产环境、生产环境

  - 思路：不同环境下各自定义自己的配置文件，切换到目标环境时进行相应的配置文件激活使用即可。

  - 主配置文件：application.properties(这个文件名，不能变，因为在自动配置的配置文件中是写死了的)

  - 不同环境下的配置文件：这些配置文件各自写自己的配置

    - application-dev.properties
    - application-test.properties
    - application-ready.properties
    - application-product.properties

  - 在主配置文件中进行子配置文件激活

    ```properties
    #激活测试配置文件
    spring.profiles.active=test
    ```
    
    

- springboot配置文件中自定义配置键值对

  ```properties
  #自定义属性mylove,明显光自定义没有什么用，要将这个值取出来用才有意义
  mylove=xiao
  ```

  在任意一个类的属性中

  ```java
  @Value("${mylove}")
  private String mylove;//这个属性会被自动注入我们配置值
  ```

  

- springboot在配置文件将自定义配置属性映射到一个对象，这个相当于就只是给一个类指定一些初始值，和原来spring注册类基本一样.

  ```properties
  user.name=liming
  user.old=12
  ```

  创建一个类,就是通过set方法实例化后将值进行注入。一般同名时会进行这样添加前缀处理。

  注意：要进行对象映射必须要有前缀，没前缀不能使用。

  ```java
  @Component//将类托管给spring
  @ConfigurationProperties(profix="user")
  public class School{
      private String name;
      private int old;
      //然后提供get、set方法
      ......
      ......
  }
  ```
  
  

# 集成web开发

- [SpringBoot项目的前端+thymeleaf模板引擎 - 夏天的尾巴% - 博客园 (cnblogs.com)](https://www.cnblogs.com/mySummer/p/11068469.html)
- [springboot+jsp jsp页面在WEB-INF下_chunbichi4375的博客-CSDN博客](https://blog.csdn.net/chunbichi4375/article/details/100898108)
- [spring cloud，sping boot,微服务，tomcat容器关系及创建执行顺序详解_寻烟的衣袖的博客-CSDN博客_springcloud和tomcat的关系](https://blog.csdn.net/qq_22162093/article/details/106061697)
- springboot兼容springmvc

## springboot集成jsp

- 步骤

  - 在main目录下存放webapp文件夹并将其设置为web资源文件夹

  - 引入springboot内嵌tomcat对jsp的解析包的依赖，不添加解析不了

    ```xml
    <!--下面这个依赖仅仅只是用于展示jsp页面。如果要使用其他jsp功能就需要添加相应的依赖-->
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
    </dependency>
    ```

    

  - 指定jsp文件输出位置：

    - web项目部署打服务器后的目录结构：
      - 项目名
        - META-INF
        - WEB-INF
          - classes:类路径。编译后target中classes所有文件放在这里
          - web.xml
          - 其他文件
        - 其他
    - 前两者是必有的,只有web-inf访问受限，所以一般其他就是放静态资源

    - springboot默认使用thymeleaf解析引擎，resource文件夹中的资源文件按照这个引擎的解析部署方式进行输出部署。
    - jsp文件如果直接放在resource部署和传统的部署不一致，所以我们应该自定义输出位置，并配合前端视图解析器简化访问路径映射操作。

    ```xml
    <bulid>
        <resources>
        	<resource>
                <!--要编译的源文件夹，我们springboot项目结构中创建的文件-->
                <directory>src/main/webapp</directory>
                <!--源文件中资源编译后的部署的目标文件夹，保证资源的安全性，对于静态资源不用放这里-->
                <targetPath>WEB-INF/resources</targetPath>
                <!--要被编译输出的源文件-->
                <includes>
                    <!--所有文件-->
                	<include>*.*</include>
                </includes>
            </resource>
        </resources>
    </bulid>
    ```

    

  - 在核心配置文件中配置视图解析器:类似springmvc中的视图解析器

    ```yaml
    spring:
      mvc:
        view:
          prefix: /WEB-INF/resources
          suffix: .jsp
    ```
    
    


## springmvc常用注解

### 接口测试的专门工具

- Postman工具、jmeter

###@RestController

- 如果一个控制器的上所有方法返回的都是JSON数据,那么可以选择直接在该类上用该注解进行Controller声明。这样就不用在每个方法上添加@ResponseBody注解。

### @GetMapping(value="/queryStudent")

- 只接收get请求
- 等价于@RequestMapping（value="/queryStudent",method=RequestMethod.GET）
  - POST、DELETE、put......方式可以类似代替

## RESTful请求风格

- 一种互联网软件架构设计的风格，不是标准，他只是提出一组客户端和服务器交互时的架构理念和设计原则，基于这种理念和原则设计的接口可以更简洁，更有层次

- 例子

  ```url
  http://localhost:8080/boot/order?id=1000&user=ss
  
  RESTful请求风格就是
  http://localhost:8080/boot/order/1000/ss
  ```

  

- springboot写RESTful风格接口

  ```java
  @RestController
  public class StudentController{
      @RequestMapping(value="/student/detail/{id}/{name}")
      public queryStudentDetail(@PathVariable("id")Integet id,
                                @PathVariable("name")String name){      
      }
      
      @RequestMapping(value="/student/{id}/detail/{LL}")
      public queryStudentLL(@PathVariable("id")Integet id,
                                @PathVariable("LL")String LL){      
      }
  }
  ```

  

- 接口混淆：当同一个路径映射不同方法，必须要使用请求方式进行区分或者调整参数在接口中的位置，否则就会产生混淆。

- 请求路径尽量不要动词
- 分页、排序等操作（如果穿的参数不是数据库中的表字段使用传统风格传递），不需要使用该风格使用传统风格

# 集成数据库

## 集成mybatis

- 导入mysql驱动依赖

- 导入mybatis整合springboot框架的启动器：包括mybatis和mybatis于spring整合

  ```xml
  <dependency>
  	<groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>2.0.0</version>
  </dependency>
  ```

  

- 在主程序上加上注解：

  - [spring boot 不连接数据库启动，报错问题_q1035331653的博客-CSDN博客](https://blog.csdn.net/q1035331653/article/details/80418026)
  - [@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})注解作用_jinrucsdn的专栏-CSDN博客](https://blog.csdn.net/jinrucsdn/article/details/106539916)
  - [Spring Boot 中使用 @ConfigurationProperties 注解 - 我爱si搬砖 - 博客园 (cnblogs.com)](https://www.cnblogs.com/gxlaqj/p/10265682.html)

  ```java
  @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
  //这个会类会自动识别核心配置文件中的数据源，然后创建一个单数据源配置，然乎进行注入
  ```

  

- 使用mybatis提供的逆向工程生成实体bean以及mapper文件

  ```xml
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

  

- 配置resource，将配置文件进行编译导出

- 在核心配置文件中配置数据源

  ```yaml
  spring:
    datasource:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://localhost:3306/springboottest
      username: root
      password: 123
  ```

  

- mappper映射文件存放位置：如果要配置这个属性必须先配置数据源

  可以将mapper.xml配置文件放在资源文件中，然后指定配置文件映射路径，就是告诉mybatis如果要找mapper配置文件就到这个位置去找。注意还是要进行资源导出

  ```yaml
  #核心配置文件中指定编译后mpper配置存放的位置，以前是在springContext的sqlFactory中指定属性
  mybatis:
    mapper-locations: classpath:com/xhu/dao/mapper/*.xml
  ```

  如果你将资源文件放行在这个mapper接口位置那么就不用指定路径

  ```xml
  			<resource>
  				<directory>src/main/resources/mapper</directory>
  				<targetPath>myspring/boot/test1/mapper</targetPath>
  				<includes>
  					<include>*.*</include>
  				</includes>
  			</resource>
  ```

  或者resource目录下你创建一条和mapper接口相同的路径，经过编译后这些配置文件会和mapper接口放在一起，因为这些资源编译后在类路径中的路径是一样自然放在一起。

## springboot使用事务

- [mybatis深入理解(三)-----MyBatis事务管理机制 - 阿里-马云的学习笔记 - 博客园 (cnblogs.com)](https://www.cnblogs.com/alimayun/p/10946884.html)
- 事务管理的本质还是通过程序操作数据库，也就是说不是在程序中保证事务，而是通过事务管理器通知数据库中创建事务、执行事务、提交事务、回滚事务。是否开启事务的关键就是是否会影响数据库中的数据的一致性、完整性、正确性。
- 事务时一个完整的功能，也叫做完整的业务
- 事务只跟：DML有关系（sql分类：dml、dql、ddl、tcl、dcl）
- 只需要在service的事务方法上加上@Transactional
- 主程序上是否加上@Enable TransactionManagement注解都行

## springboot集成redis

- 步骤

  - 添加操作redis数据类型的依赖,这里添加springboot操作redis数据类型的启动器即可

    ```xml
    <dependency>
    	<groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <version></version>
    </dependency>
    ```

    

  - 在核心配置文件中添加redis的配置信息

    ```yaml
    spring:
      redis:
        host: 127.0.0.1
        prot: 6379
        password: 123456
    ```


# springboot依赖管理机制

- 继承父项目，父项目做依赖管理，父项目声明了几乎所有我们开发所需要的依赖版本号。
- 开发导入了starter场景启动器（spring-boot-starter*）
- 无需关注版本号，自动版本仲裁
- 可以修改版本号（在子项目中进行版本声明即可，子项目版本自动覆盖父项目）

# springboot自动配置

- 自动配好了tomcat
- 自动配好了springmvc,以前我们需要将自己配置前端拦截器、字符过滤器、视图解析器、组件扫描、文件上传解析器、静态资源放行
- 默认包结构
  - 主程序所在包及其下面的所有子包里面的组件都会被默认扫描
  - 无需以前的包扫描器进行配置
  - 如果要手动指定，只需要将@springbootapplication(scanBesePackages="")
  - @ComponentScan("")
  - 配置文件中的所有配置最终都被映射到一个类中
  - springboot有很多的自动配置类，每个类会导入一些对应的类，这些类往往是我们导入场景依赖中提供。自动配置也是按需加载

## 自动装配原理

- @SpringBootApplication

  - ```java
    @SpringBootConfiguration:代表当前是一个配置类
    @EnableAutoConfiguration：
    @ComponentScan()：扫描指定包下的类，注册到spring容器
    ```

    

  - @EnableAutoConfiguration：开启自动配置

    ```java
    @AutoConfigurationPackage：自动配置包，这个主要用来注册用户自定义的包
      - @Import({Registrar.class})：这个类将一个包下的组件注册到spring，这个包名的获取方式就是通过扫描主程序上的：@ComponentScan()，将这个包名封装在一个数组中然后进行注册。
    @Import({AutoConfigurationImportSelector.class})：这个类的作用是用来加载springboot本身提供的配置类（127个）
    ```

    

- 按需配置

  - 虽然127个**自动配置类**会默认全部加载，但是最终会按需配置，按照条件装配规则（@Conditional），因为配置时就是执行配置类的方法。
  - 配置类解决xml配置，自动配置解决我们手动配置。
  - 其实想想思路很简单，既然你不想配置xml，那么我们就用Java来配置嘛，但是配置的东西确实还是多，那么我们就来给你配置嘛，反正都是一些常用的配置信息，我们用常用的配置参数值进行配置，如果你要修改就在主配置文件中指定，我们自动配置的时候进行检测。想想这个web环境依赖这么复杂，要用的可用的一大堆，有的人用这个，有的人用那个，有的人还用不上，保险起见，我们肯定的全部的自动配置给你写上（礼多人不怪）。但是写是写上了，你用哪个是自己选择的，没选我怎么解析？那没法了，我得加上条件判断。当然这个条件用注解（@Conditional）标识最简便。但是这个条件怎么指定？嗯，反正我是老大，那我就直接指定规则：以后某种环境中得依赖，你们就不要单独导了，全部封装起来，然后根据我们自动配置类提供接口进行实现，最后将我们自动配置需要的类暴露出来，这样我的自动配置就做好了。
  - 核心实现注解：
    - @ConditionalOnBean、@ConditionalOnMissingBean:往往是需要一个外部依赖环境提供的相关类，如果没有，这个方法它就不会执行，那么就不会加载对应的组件。
      - 可以说这些写死的类就是springboot提供给外部依赖环境实现的接口，只要实现这个接口，springboot就能通过这个类实现调用外部实现。

  - 这个配置比较有意思

    ```java
     @Bean
            @ConditionalOnBean({MultipartResolver.class})
            @ConditionalOnMissingBean(
                name = {"multipartResolver"}
            )
            public MultipartResolver multipartResolver(MultipartResolver resolver) {
                return resolver;
            }
    //这个就是用来解决一个问题，如果已经配置了文件上传解析器到spring，但是名字并不是multipartResolver，就执行这个方法，找到这个组件并用这个方法将这个组件返回。说白了就是类似进行了重命名。
    ```

    - **所以还可以得出一点，按需加载且如果你进行配置了，那么我仅仅是找到它并返回，并不会重复配置，保证单例**
    - **springboot 默认配置所有常用的组件，默认按照用户配置的组件优先**
    - **每个自动配置类的所需的属性并不是硬编码，而是使用一个properties对象存储，然后进行范访问，而且每个properties对象默认绑定一个对应的properties配置文件，这样我们就能够通过主配置文件进行属性修改**

  - 定制化配置的两种方式：

    - 一是自定义配置类注册我们需要的bean
    - 二是通过自动配置类绑定的properties的属性在主配置文件中进行配置
    - 总结属性值拿取流程：自动配置类注册组件，组件向properties对象中拿值，这个对象有默认值，如果application.properties中有对应的属性就将其解析到properties对象中覆盖默认值。

# 底层注解

- @Configuration，标识一个类为spring配置类。本身也是组件。
  - proxyBeanMethods:调用该配置的方法是否使用代理实例执行
    - true===full模式：每次通过代理类检测容器中是否存在组件，如果有，就返回，没有就调用创建。如果组件需要单例（这个我们可以自己写CDL）或者组件之间的依赖关系是单例的就使用这种。
    - false===lite模式：直接执行这个方法，不通过代理类检测容器中是否有。多例或则组件之间没有依赖关系，使用这个，创建速度快，但是耗内存。
- @Bean,注册组件，默认方法名为注册组件方法名，注册的实例也是单例，即使你使用的是new
  - 外部无论调用这个注册方法多少遍得到的都是同一个实例
- @Import
  - @Import({User.class}):在容器中调用无参构造自动创建User类型的组件，**默认组件的名字就是全类名**
- @Conditional
  - 条件装配：满足Conditional指定的条件，则进行组件注入。
  - @ConditionalOnBean:当容器中有指定组件时执行某些行为
    - @ConditionalOnBean(name="tom"):当容器中有组件名为tom时执行这个下面方法
  - @ConditionalOnMissingBean:当容器中没有指定组件是执行某些行为
  - @ConditionalOnProperty:判断主配置文件中是否有指定属性
- @ImportResource：导入资源。主要用于ssm中bean配置文件迁移
  - @ImportResource("classpath:beans.xml")：解析beans.xml中注册bean，导入到声明该注解的配置类中
- @ConfigurationProperties:配置绑定
  - 传统Properties配置：如果是在spring的xml中使用标签引入即可。但是如果在内中我们需要手动解析。
  - @ConfigurationProperties(prefix="car"):自动将springboot的主配置文件中前缀为car的配置属性映射到当前使用该注解的类的属性中
  - 注意：这个是进行自动映射，所以需要spring自动解析配置文件。故只有spring容器中的组件才能进行自动映射。
- @EnableConfigurationProperties(Car.class)：将car注册到spring,并自动映射主配置文件中的配置。



