# Spring5源码级别讲解

## spring框架概述

- spring是一个轻量级的开源的JavaEE框架。其目的是减少企业级开发的复杂性。
  - 轻量级：spring的jar包很小、很少，且可独立使用（如你如果只是用aop包就只是用spring的aop包即可）。
- spring可以单独应用于构建完整的应用，也可以与其他web框架，如struts、Webwork等组合使用，**并且可以与spring等桌面应用程序api组合**。spring不仅仅能应用在web开发，还能应用在桌面应用程序、小应用程序中。
- spring主要由其部分组成：spring core、spring aop、spring orm（对象关系映射）、spring dao、spring context（spring对象容器）、spring web、spring web mvc。
- spring的核心是：ioc和aop

### IOC

- 控制反转，把传统的new创建对象的方式交给spring容器创建。spring容器创建对象是借助Java的反射进行创建。

### AOP

- 面向切面编程，**其目的在于不破坏源代码的情况下*增强功能***。比如在某个类的方法执行之前需要记录日志，那么就可通过aop的前置通知完成增强。

### spring特点

- 方便解耦(IOC)、简化开发
- 对aop编程支持
- 对Junity支持，方便测试
- 方便集成其他框架
- 降低了JavaEE的api使用难度（如jdbc）
- 声明式事务的支持

### spring组成部分

![20230226161251](D:\summary\summary\知识总结\再入spring\spring源码\img\20230226161251.jpg)

## 入门案例

- 先下载spring相关jar包
- 导入jar包（根据spring的架构图，我们得知核心容器需要使用beans、core、context、expression四个jar包，然后因为spring依赖于commons-logging日志jar）

### spring配置文件创建对象方式

- 创建bean的xml配置文件，该文件的约束需要自己指定。
- 配置bean：通过bean标签
  - ![20230226162915](D:\summary\summary\知识总结\再入spring\spring源码\img\20230226162915.jpg)

- 创建对象步骤

  - 加载spring配置文件（准确说是通过配置文件来构建一个spring的应用级别context，也就是常说的spring容器）

    ![20230226170453](D:\summary\summary\知识总结\再入spring\spring源码\img\20230226170453.jpg)

  - 通过容器实例获取对象

    ![20230226170755](D:\summary\summary\知识总结\再入spring\spring源码\img\20230226170755-16774024958841.jpg)

## IOC容器

- 控制反转时面向对象编程中的一种设计原则，可以用来降低计算机代码之间的**耦合度**，其中**最常见的实现方式**叫做**依赖注入**（DI-dependency injection，spring的实现方式就是这种），还有一种方式叫做“依赖查找”（DL--dependency lookup），两者差别就是前者在创建时主动。通过控制反转，对象在被创建的时候，由专门对象的系统（spring容器）将其所需的其他对象的引用给它，也可以说，依赖被注入到对象中。
- 由上面可见，spring容器可以说就是一个庞大对象创建中心（工厂模式），它能创建各种对象。
- **所以控制反转就是把对象一级对象之间的关系的创建都交给spring容器。首先，使用者不用再关注一个对象创建的整条依赖链，简化了对象创建的难度。其次，通过抽象类作为注入引用，当依赖的具体对象发生变化，只需要改变注入的对象，不用改变引用。**

### IOC底层原理

- xml解析技术
- 工厂模式（）
- Java反射

### 逐步解耦过程

- 原始依赖关系创建：直接通过new创建所需对象然后调用。
- 第一步解耦：在依赖双方之间加一层工厂，通过工厂创建对象，调用者通过工厂获取对象。解除了依赖双方的耦合，但是增加了新耦合（工厂耦合）。
- 第二步解耦：借助xml解析以及反射创建对象，在工厂中，我们不再直接通过new创建对象。xml文件中将所有的对象通过键值对的方式进行登记，然后工厂解析配置文件得到具体类的全限定名，然后通过全限定名得到对应的类，再通过类反射创建对象。（这一步将工厂和要创建的对象之间的耦合再次降低，变为工厂与xml文件之间的耦合，xml文件的修改是动态的。）
- 第三步解耦：依赖方将要使用的对象的id或者名字作为参数传递给工厂，工厂返回相应的对象。这样使用方不用依赖工厂的具体创建实例方法，工厂不用针对不同的类写多个方法。
- 以上三步将依赖双方的耦合降至最低。

### spring的IOC实现

- **spring实现IOC采用的DI方式实现**，在创建对象的过程中将对象的依赖关系一并创建。

- IOC思想基于IOC容器完成，IOC容器底层就是对象工厂。

- spring提供两种IOC使用容器的方式（两种接口）：

  - BeanFactory：IOC容器基本实现，是spring内部的使用接口，一般开发人员不要使用，但是不限制使用。
  - ApplicationContext：是BeanFactory接口的子接口，提供更多更强大的功能，该接口面向开发人员使用。
  - 对比：
    - 两种方式都能通过加载配置文件获取bean，且加载配置文件的方式一致.
    - BeanFactory在加载配置文件时，采用的是懒汉式创建bean。就是说其在加载配置文件时，只会解析配置文件，不会创建bean。只有获取对应的bean时才创建。
    - ApplicationContext在加载配置文件时，采用饿汉式创建bean。就是加载配置文件时就创建对象。获取时直接取即可。

- ApplicationContext的实现类

  ![20230226185736](D:\summary\summary\知识总结\再入spring\spring源码\img\20230226185736.jpg)

  - 主要的两个实现类
    - FileSystemXmlApplicationContext
    - ClassPathXmlApplicationContext

### IOC操作Bean管理

- 什么是bean管理
  - spring创建对象
  - spring注入属性（DI）,DI是IOC的一种实现
- 管理方式
  - 基于xml配置
  - 基于注解

#### 基于xml配置

- 创建对象
  - 在配置文件中配置bean标签
    - bean标签常用属性
    - id：对象唯一标识
    - class：对象所属类的全限定名
    - name：不唯一标识
  - 创建对象时默认执行无参构造函数

- 注入属性

  - 默认执行set方法

    - xml配置方法（在创建对象的基础之上注入属性，先创建对象再注入属性）

      ![20230227151706](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227151706.jpg)

  - 使用有参构造函数进行注入

    ![20230227152632](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227152632.jpg)

  - 通过P命名空间注入，可简化xml配置

    - 添加p命名空间在配置文件中

      ![20230227153437](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227153437.jpg)

      ![20230227155355](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227155355.jpg)

  - 指定属性值为null值

    ![20230227160645](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227160645.jpg)

  - 注入属性值包含特殊符号的情形（通过转义符号<![CDATA[转义内容]]>进行解决，该表达式是xml的转义符号，它会将转义内容按照标签形式进行输出）

    ![20230227161514](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227161514.jpg)

  - 注入外部bean（就是注入已经在xml中声明了的对象）

    ![20230227162936](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227162936.jpg)

  - 注入内部bean（就是一个类的属性是另一个类的引用，比如一个员工类持有一个部门类的引用）

    ![20230227163700](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227163700.jpg)

  - 级联赋值

    ![20230227171709](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227171709.jpg)

    ![20230227172001](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227172001.jpg)

- **注入集合类型属性**

  - 数组、List类型使用property下的array、list标签

    ![20230227180912](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227180912.jpg)

  - map类型使用map标签

    ![20230227181110](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227181110.jpg)

  - set类型使用set标签

    ![20230227181256](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227181256.jpg)

  - 当集合组成元素是对象时

    ![20230227182013](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227182013.jpg)

  - **把集合注入部分提取出来作为公共资源**

    - 在spring配置文件中引入命名空间util

      ![20230227182818](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227182818.jpg)

      ![20230227183003](D:\summary\summary\知识总结\再入spring\spring源码\img\20230227183003.jpg)

####工厂Bean(FactoryBean)

- **spring有两种类型bean,一种普通bean,另一种是工厂bean**
  - 普通bean就是我们自定义的bean
  - 两者区别：前者class登记的是什么类型，就返回什么类型的实例。后者登记的类型不一定是具体返回的类型。
- FactoryBean类定义
  - 第一步：创建一个类，让这个类作为工厂bean,然后实现接口FactoryBean
  - 第二部：实现接口里的方法，在实现的方法中定义要生产的bean类型。三个方法分别是
    - getObject()：返回对象
    - getObjectType()：返回对象的类型
    - isSingleton()：返回对象是否是单例
  - 配置方式和自定义bean一致
- **BeanFactory与FactoryBean**
  - 两者都是接口
  - 前者代表是spring容器（大工厂，可以生产所有的bean）
  - 后者代表spring容器中的小容器（小工厂，可以生产一类bean）
  - 可以说使用FactoryBean可以对BeanFactory中的bean进行分块管理生产。

#### bean的作用域

- spring中一个bean创建可以是单例或是多例
- spring默认是单例
- 如何配置单例还是多例
  - 设置bean标签中的scope属性
  - singleton（单例）默认值（**该值在加载xml配置文件时，就会创建对象实例，饿汉式创建**）
  - prototype（多例-原型模型）（**该值在加载xml配置文件时，不会创建对象，当在调用getBean()时才会创建对象，懒汉式创建**）

#### bean的生命周期

- **[Spring Bean的生命周期 (biancheng.net)](http://c.biancheng.net/view/4261.html)**
- **[bean的生命周期_Been Doing的博客-CSDN博客](https://blog.csdn.net/weixin_45723046/article/details/124546319)**
- **上面两篇文章看完足矣**

- 使用构造器创建实例

  - 无参函数进行创建实例
  - 有参函数创建实例

- 给实例属性注入值

  - 无参构造就调用set注入
  - 有参构造就继续执行有参的构造代码块

- 将创建的bean传递给bean后置处理器进行初始化前处理

  - bean的后置处理器BeanPostProcessor是一个接口，其有两个方法

  - postProcessBeforeInitialization

  - postProcessAfterInitialization

  - 自定义后置处理器，只需要实现该接口，并进行配置（配置只需要登记这个处理器，spring会进行自动检测装配处理器）

    ![20230228141235](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228141235.jpg)

- 调用bean的初始化方法（需要进行配置初始化方法-**使用前还需要执行的行为**）

- 将创建的bean传递给bean后置处理器进行初始化后处理

- bean使用阶段

- 当容器关闭时，调用bean的销毁方法（需要配置销毁方法-**也叫做销毁前执行的行为**）

- ![20230228140208](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228140208.jpg)

- **注意：所有的Bean的生命周期都是交由BeanFactory进行管理的，当一个Bean创建后会被缓存到BeanFactory中。其只管理单例Bean，多例Bean是不会管理的**

- servlet生命周期

  初始化阶段：

  容器(tomcat等)装载servlet

  servlet容器启动的时候,会自动的装载某些servlet
  在servlet容器启动之后，页面首次向servlet发送请求
  servlet类文件被更新后，重新装载servlet
  装载好Servlet后：

  容器创建一个servlet对象并调用servlet的init方法（在servlet生命周期内只能调用一次init方法).

  在接收到客户端发送请求时，servlet调用service方法时请求进行响应，servlce对该请求请求的过程进行匹配所要调用的

  方法是 doGet（）或者doPost（），然后进入对应的 doGet（）或者doPost（）方法中调用逻辑层的方法，实现相应的响应 （有点绕口…）。

  响应请求阶段：

  用户发送至servlet的请求，servlet容器会创建相应的请求响应对象 （Httpservletrequest Httpservletresponse）

  然后调用servlet对象的service方法，service方法从request对象中获取请求信息并通过response对象返回响应的信息。

  终止:

  当web应用终止或者容器关闭或容器重新装载servlet新实例的时候，容器调用servlet的destroy方法，在distroy方法中可以释放对应servlet资源。

####xml自动装配

- 手动装配：我们在xml登记bean时通过property标签给其属性指定确定的值。

- 自动装配：我们不给定确定的属性值，而是指定装配规则（属性的名或者属性的类型），spring自动将匹配的属性值进行注入

  - 配置时使用bean标签的autowire属性，该属性有两个值，一个是byName，一个是byType

  - byName，例如bean的属性名叫做dep，而同时spring容器中存在一个名为dep的bean，那么就会将这个bean注入到该属性作为值

    ![20230228143636](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228143636.jpg)

  - byType，就是比对属性以及spring容器中bean的全限定名，如果符合就注入为值

    ![20230228143817](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228143817.jpg)

  - **注意：byType注入不能定义多个同全限定名的bean，否则会报错。**

#### 外部属性文件

- 像数据库配置信息，如果直接写到bean配置的property中，看起来很长，修改也很麻烦。于是乎我们可以将bean的属性值写到专门的外部文件，然后进行读取注入。

- 直接注入值配置形式

  ![20230228144730](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228144730.jpg)

- 通过外部配置文件配置

  - 创建一个db.properties格式文件

    ![20230228144925](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228144925.jpg)

  - 将properties文件引入到bean配置文件中

    - 引入context命名空间

      ![20230228145122](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228145122.jpg)

  - 使用xml配置标签引入外部文件

    ![20230228145414](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228145414.jpg)

#### 基于注解操作Bean

- 注解是Java中一种类，用于对类或者属性、方法进行标记，其格式：@注解名（属性名=属性值，...）

- 使用注解能够简化我们的配置

- 创建bean

  - bean层主要注解

    - @Component
    - @Service
    - @Controller
    - @Repository

  - 1、引入aop的jar包

  - 2、开启组件扫描

    - 引入context命名空间

    - 配置开启扫描

      ![20230228151337](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228151337.jpg)

    - 配置例子1：扫描整个com.atguigu包，且扫描时不使用默认过滤器，使用自定义过滤原则，只扫描@Controller并将其登记到spring容器中

      ![20230228152307](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228152307.jpg)

    - 例2：扫描除了@Controller的注解，**不使用use-default-filter属性就会默认使用默认过滤器扫描所有注解**

      ![20230228152517](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228152517.jpg)

  - 3、给类添加组件注解

- 基于注解实现属性注入

  - 主要注解
    - @AutoWired:根据属性类型进行自动注入
    - @Qualifier：根据属性名称进行注入，需要和@AutoWired一起使用
    - @Resource：可以根据类型注入，可以根据名称注入
    - @Value:注入普通类型属性的值

- 配置类注解：

  - @Configuration标识一个类为配置类
  - @Component（backagePackages={”com.atguigu“}）自动扫描的包

## AOP-面向切面编程

- 通过预编译方式和运行期间动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是spring框架中的一个重要内容，是函数式编程的一种衍生范型。**利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，**提高程序的可重用性，同时提高了开发的效率。
- **在不修改源代码的情况下就能增加新功能，符合开闭原则**

#### 底层原理

- **spring使用的是动态代理进行实现AOP**
- 静态代理与动态代理
  - 静态代理是在编译前代理类就已经存在了，而动态代理是在运行时才产生代理类。那么静态代理你能找到代理类的源码，而动态代理你只能知道代理类生成逻辑，却无法找到其源码，它只会在运行产生后在内存中找到其实例。
  - 静态代理和动态代理的思路没有什么大区别，基本就是持有待代理类的实例达到原功能使用的目的。动态代理需要借助字节码生成技术

##### 两种动态代理

- jdk动态代理（需要实现接口）

  - 创建接口实现类代理**对象**，实现类实现要增强的方法，代理类持有一个待增强实现类的对象实例，代理过程中可以通过该对象调用原功能，达到不改变原功能而增强功能。

  ![20230228172840](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228172840.jpg)

- cglib动态代理（不需要实现接口）

  - 创建一个子类**对象**，子类重写父类方法，在重写方法中调用父类方法，达到不改变父类方法逻辑而增强功能。

  ![20230228172905](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228172905.jpg)

- JDK实现动态代理
  - 使用JDK动态代理创建对象，需要使用java.lang.reflect.Proxy类里面的方法创建代理对象。
  - 使用该方法`static Object`  `newProxyInstance(ClassLoader loader, 类<?>[] interfaces, InvocationHandler h)`  返回指定接口的代理实例，该接口将方法调用分派给指定的调用处理程序。
  - 假设存在一个接口UserDao，存在一个UserDaoImp是UserDao的实现类，现在要使用增强UserDaoImp的实例。
    - 创建一个UserDaoImp代理类UserDaoImpHandler，该类需要实现InvocationHandler接口，并持有一个UserDaoImp实例作为私有属性。
    - 然后在UserDaoImpHandler实现的invoke(Object proxy,method,Object[]args)中对UserDaoImp的实例的方法进行增强。
    - 预备工作做完，创建一个UserDaoImp实例，通过这个实例创建一个UserDaoImpHandler实例，再通过这个实例创建代理对象。
    - 代理对象是UserDao的实现类实例，但是在源码中不能指出具体的类，所以只能使用UserDao进行实例接收，调用时通过UserDao进行增强方法的调用。
  - **明显如果spring使用jdk代理，UserDaoImpHandler这个类是spring已经写了的，我们只需要写原UserDaoImp功能以及增强功能，然后交给spring，spring会在invoke中按照要求进行整合**

#### AOP术语

- 连接点：要增强类中能够被增强的方法都叫做连接点
- 切入点：连接点中确定实际要增强的点就是切入点，切入点是连接点的子集
- 通知（增强）：增加的逻辑（功能）
  - 前置通知
  - 后置通知（先当于finally，最终一定会执行）
  - 环绕通知（待增强的功能执行前会执行，执行后还会执行一次）
  - 异常通知（当要增强的部分出现异常时执行）
  - 返回通知（原功能返回后执行）
- 切面：把通知应用到切入点的**过程**。（也可以说aop是通过切面将通知放到切入点指定位置）

#### 使用AOP

- spring中通常使用AspectJ实现AOP操作

  - AspectJ不是spring组成部分，是独立于AOP的框架，可以进行单独使用。通过AspectJ于spring组合实现对spring的aop操作。
  - [Aspectj_百度百科 (baidu.com)](https://baike.baidu.com/item/Aspectj/4830848)
  - 简而言之，AspectJ实现了动态代理

- 引入依赖：aspectj的jar、cglib、aopalliance、aspectj.weaver

- **切入点表达式**

  - 作用：**指出在哪个类的哪个方法进行哪种增强**

  - **语法：execution(\[权限修饰符]\[返回类型]\[类全路径]\[方法名称][参数列表])**，这个表达式能够准确定位切入点

    ![20230228195542](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228195542.jpg)

    ![20230228195734](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228195734.jpg)

- 通过xml配置实现基于AspectJ操作AOP（基本不用）

  - 在spring配置文件中配置切入点

    - 大致就是配置切入点，然后配置切面，配置切面时除了指定切面是哪个bean外，还需要指定切面的哪个方法在什么位置切入到切入点。

    ![20230228210442](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228210442.jpg)

- **通过AspectJ注解实现AspectJ操作AOP**（最常用）

  - 1、创建一个待增强类（注册到spring）

  - 2、创建一个代表增强功能的类，并为该类下的方法**指定通知类型**（注册到spring，保证aspectj能够与spring整合。并增加@Aspect到类上，该注解用于被aspect扫描器扫描，被扫描到后会以这个类为标准生成代理对象。）

  - 3、在xml中开启注解扫描

  - 4、在xml中开启aspect自动生成代理对象（**如果使用配置类进行开启，就需要在配置类上使用@EnableAspectJAutoProxy(proxyTargetClass=true)**）

    ![20230228202233](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228202233.jpg)

  - 5、给不同类型的通知通过切入点表达式配置切入点

    ![20230228202646](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228202646.jpg)

    ![20230228202822](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228202822.jpg)

    ![20230228202952](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228202952.jpg)

    ![20230228205238](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228205238.jpg)

    原功能返回之后执行，并且会将返回值注入到该通知中

    [AOP(二)之AfterReturning增强处理_aop afterreturning_寒小韩_的博客-CSDN博客](https://blog.csdn.net/qq_40179479/article/details/103330879)

    ![20230228203234](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228203234.jpg)

  - 通知执行的顺序

    - around、before、原功能、around、after、afterreturning/afterthrowing

  - 相同切入点抽取

    ![20230228205511](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228205511.jpg)

  - 待增强类的方法要切入多个同类型通知（如两个before），如何设置同级别通知执行的优先级（**就是对通知所属类进行一个排序即可**）

    ![20230228205942](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228205942.jpg)

  - ![20230228211117](D:\summary\summary\知识总结\再入spring\spring源码\img\20230228211117.jpg)

##JdbcTemplate

- jdbctemplate是spring实现对jdbc进行的封装，它能更方便操作数据库，其可以单独作为spring jdbc使用，也可以整合其他数据库层框架（mybatis、hibernate）一起使用。

- 回顾原生jdbc

  - 获取类加载器
  - 加载mysql驱动
  - 建立mysql连接（以上三步是数据库连接池完成）
  - 通过连接获取操作句柄statement（以下三步就是jdbctemplate进行的封装）
  - 通过句柄执行sql
  - 解析封装句柄执行结果

- jdbc就如同原生的statement

- 引入依赖

  - druid数据库jar、mysql连接驱动jar、spring-jdbc、spring-tx、spring-orm(这个主要用于整合其他dao层框架时需要使用)

- 使用jdbctemplate

  - 1、注册数据库连接池实例

    ![20230301095359](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301095359.jpg)

  - 2、注册jdbctemplate实例

  - 3、创建dao层接口以及实现类，在实现类中注入JdbcTemplate实例（dao层操作就可以使用jdbctemplate进行操作）

    ![20230301120116](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301120116.jpg)

  - cud都使用jdbctemplate的update操作

  - 查询操作

    - 查询某个值

      ![20230301121003](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301121003.jpg)

      查询返回对象

      ![20230301121546](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301121546.jpg)

      ![20230301121621](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301121621.jpg)

      ![20230301121949](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301121949.jpg)

    - **通过以上使用对比，即使是使用mybatis，其也是按照spring-jdbc标准来的**

    - 实现批量添加操作

      ![20230301122339](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301122339.jpg)

      ![20230301122531](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301122531.jpg)

    - 实现批量修改、删除

      使用的都是batchUpdate()，传参方式也是一样，仅仅是sql有差别

## 事务操作

- 事物是数据库操作最基本单元，逻辑上的一组原子操作，要么都成功，要么都失败。（在Java中叫做同步锁代码块）
- [spring事务与数据库事务与锁之间的关系 - 百度文库 (baidu.com)](https://wenku.baidu.com/view/cc87962b51ea551810a6f524ccbff121dd36c5ee.html?_wkts_=1677652883562&bdQuery=spring的事务与Java锁之间的关系)
- 典型场景，银行转账
- **事务的四个特性（ACID)：这四个特性其实就是保证公共数据不产生错误，其目的就是保证*数据的一致性***
  - 原子性：事务中的数据库操作不可分割，要么全成功执行，要么全失败
  - 一致性：事务中的数据库操作后，要**保证数据一致性，就是操作的数据不应该发生逻辑错误**
  - 隔离性：多个事务在同时进行数据库操作时，事务彼此的行为不可见，事务之间不会产生影响。
  - 持久性：事务操作一旦确定提交（确认结束执行事务）后，其对数据库产生的影响是永久的。

### 事务的操作流程

- 1、开启事务
- 2、编写业务逻辑操作
- 3、提交事务/回滚事务

### spring中的事务操作

- 事务管理应该在service层还是dao层?
  - 都可以。但是最好应该加在service层，因为在dao层我们不会涉及业务逻辑，都是sql执行语句，加在这层，那么每个dao层的方法实现都要进行事务管理，比较麻烦。**加在service层**，那么业务整体控制比较清晰，而且不用针对所有的业务的数据库操作进行事务管理。
  - **再者，如果service层发生了错误，但是前面调用了dao层，那么前面已经执行了的数据库操作已经提交，无法回滚。所以要将注解加在service层**

- spring中两种事务管理操作：编程式事务管理（就是自己按照事务管理流程来编写业务，明显这种方式会产生多处冗余代码，不适合。）、声明式事务管理。通常使用后者。
- **梳理以下Java与数据库交互逻辑**
  - **本质上Java就如同mysql提供的客户端命令行界面**
  - 1、请求数据库服务器建立连接
  - 2、通过连接发送数据库操作指令
  - 3、数据库执行指令并将执行结果返回给发送者
  - **spring事务交互过程**
  - 1、spring发送**事务开启指令**
  - 2、数据库开启事务并等待连接剩余指令
  - 3、spring执行事务管理方法中的逻辑,如果逻辑中需要操作数据库，就通过连接发送指令
  - 4、直到该方法所有逻辑都执行完成了，spring发送提交事务指令
  - 5、假设在业务逻辑执行过程中发生了错误，那么spring就会发送事务回滚指令
  - 6、假设在方法中调用的其他方法仍开启了事务管理，spring根据配置信息确定如何处理事务，比如是关闭后重新开启，还是说不用再发送事务开启指令，直接执行逻辑。

#### 声明式事务管理

- 底层使用的是AOP，将事务管理作为通知，只需要声明要增强的切入点即可通过代理对象进行处理，减少冗余、耦合度。

- **spring事务管理api**

  - spring提供一个接口PlatformTransactionManager，代表事务管理器，**这个接口针对不同的框架提供不同的实现类。**

  - 对于jdbc，该接口的实现类是DataSourceTransactionManager

    ![20230301141358](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301141358.jpg)

- 基于注解实现

  - 1、spring配置文件中配置事务管理器，由于我们使用的是jdbc，就使用DataSourceTransactionManager

    ![20230301141631](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301141631.jpg)

  - 2、在spring配置文件，开启事务注解使用

    - 引入tx命名空间

      ![20230301141900](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301141900.jpg)

  - 3、在service层的实现类上或其方法上加上事务注解@Transactional，该注解可指定如下参数

    - propagation（事务的传播行为-比如假设在A方法上添加了事务管理，A方法内部调用了B方法，但是B并没有声明事务管理，那么B上是否应该做事务管理）：

      ![20230301143830](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301143830.jpg)

      - required:事务嵌套时，该值标识的事务，不用开启新事务，用最先开启的事务。
      - required_new:如果事务嵌套，该值标识的事务，必须开启新事务，新旧事务不相关，**假设旧事务中出现逻辑错误，新事务没有错误，新事务照常能提交成功**。注意，旧事务应该挂起，就是说，要等待新事务方法逻辑执行完后，才会接着执行旧事物方法逻辑。（**相当于spring开启了一个子线程，然后该线程获取数据库连接，然后开启事务，执行逻辑以及指令，当子线程将逻辑执行完后，唤醒父线程继续执行逻辑。**）
      - supports:该值标识的事务，如果有事务嵌套情况，就直接在旧事务中运行，没有嵌套情况，该事务方法可以不用开启事务。

    - isolation（事务的隔离级别）：（mysql默认隔离级别是可重复读）

      - [mysql、oracle默认事务隔离级别的说明 (wuyaogexing.com)](http://wuyaogexing.com/70/260383.html)

      - 事务操作之间影响的程度。不同的隔离界别，会导致数据出现三种情况：脏读、不可重复读、幻读
      - 脏读：一个未提交事务读取到了另一个未提交事务中修改后的数据。
      - 不可重复读：一个未提交事务读取到了另一个已经事务修改后的数据。
      - 幻读：一个未提交事务读取到另一个提交事务**添加的数据**
      - ![20230301170729](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301170729.jpg)

    - timeout（超时时间）

      - 事务需要在一定时间内进行提交，如果不提交，就执行事务回滚
      - 默认值是-1（单位是秒），-1表示不超时

    - readOnly

      - 表示该事务内是否只执行**查询sql，不做增删改**

      - 默认值是false

    - rollbackFor（回滚）

      - 设置事务注解的方法逻辑中出现哪些异常会执行回滚操作

    - rollbackForClassName

    - noRollbackFor（不回滚）

      - 设置事务注解的方法逻辑中出现哪些异常会不执行回滚操作

    - noRollbackForClassName

- 基于xml配置实现

  - 引入命名空间

  - 配置事务管理器

  - 配置通知（就是配置通知的相关参数，从aop角度说就是配置增强类的的属性，tx:attributes就是增强类）

  - 配置切入点和切面

    ![20230301172355](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301172355.jpg)

    ![20230301172936](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301172936.jpg)

- 完全注解实现

  - 创建配置类

  - 添加@EnableTransactionManagement，该注解开启事务注解扫描

  - 创建数据库连接池

    ![20230301173646](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301173646.jpg)

  - 创建jdbctemplate

    ![20230301173914](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301173914.jpg)

  - 创建事务管理器

    ![20230301173957](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301173957.jpg)

  - 测试（创建AnnotationConfigApplicationContext对象，用于加载配置类）

    ![20230301174229](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301174229.jpg)

## spring5新功能

- 整个spring5代码基于jdk8，运行时兼容JDK9，许多不建议使用的类和方法在代码库中删除。

- spring5自带通用日志封装

  - spring5已经移除Log4jConfigListener,建议使用Log4j2,如果要用1只能降低spring版本

  - spring5整合log4j2

  - 引入依赖

    - slf4j-impl、log4j-api、log4j-core、slf4j-api

      ![20230301175702](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301175702-16776646338151.jpg)

      ![20230301175916](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301175916.jpg)

- 容器新增功能

![20230301193524](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301193524.jpg)

### 核心容器新功能

#### 支持@Nullable

- 该注解可以使用在方法上面，属性上面，参数上面。表示方法的返回值、属性值、参数值可以为空。

####支持函数式风格GenericApplicationContext

- ![20230301194528](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301194528.jpg)

#### 支持整合Junit5

- 整合Junit4

  ![20230301195207](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301195207.jpg)

  省略了每次都需要手动创建ApplicationContext

  ![20230301194957](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301194957.jpg)

- 整合JUnit5

  ![20230301195548](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301195548.jpg)

  - 引入JUnit5的jar
  - ![20230301195518](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301195518.jpg)
  - ![20230301195727](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301195727.jpg)

### SpringWebFlux

- 什么是webflux

  - ![20230301200244](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301200244.jpg)

  - 该模块是spring5新增模块，用于web开发，功能与springmvc类似。**WebFlux使用当前一种比较流行的响应式编程出现的框架**

  - 使用传统web框架，比如SpringMVC，是基于servlet容器，WebFlux是一种**异步非阻塞的框架**，异步非阻塞的框架在servlet3.1后才支持，核心是基于Reactor（一种响应式编程框架）的相关API实现的。

  - **异步非阻塞**：

    - 异步与同步：**针对调用者**，调用者发送请求，如果等着对方回应之后才去做其他事情就是同步，如果发送请求后不等着对方回应就去做其他事就是异步。
    - 阻塞与非阻塞：**针对被调用者**，被调用者收到请求后，做完请求任务之后才给出反馈就是阻塞，收到请求后马上给出反馈然后去做事情就是非阻塞。

  - **响应式编程**（RP）：

    ![20230301211834](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301211834.jpg)

    有求必应，在远程服务调用（RFC）中使用能够大大提升服务的可用性。微服务中的限流、熔断就是采用这种方式，**所以我们构建微服务模块时*网关*使用webflux非常不错**。

    - Java8实现响应式编程主要是借助观察者模式
    - Java8提供两个类实现这种模式，Observer（观察者）与Observable（被观察者）
    - Java8以后，这两个类就被取代了，采用的是**Flow类**

- webflux的特点

  - 异步非阻塞：能够用有限的系统资源响应更多的请求，提高系统的吞吐量和伸缩性。
  - 函数式编程：能够使用函数式编程实现路由请求。

- 对比mvc

  ![20230301204808](D:\summary\summary\知识总结\再入spring\spring源码\img\20230301204808.jpg)

  - 1、两者都可以使用注解方式编程，都运行在Tomcat等servlet容器中
  - 2、mvc采用命令式编程（就是一行一行的指令进行执行），webflux采用异步响应式编程。

#### reactor相关响应式API

- 响应式编程操作中，Reactor是满足Reactive规范框架

- 可参考angular的rjx

- Reactor有两个核心类，**Mono和FLux**,这两个类实现接口**Publisher**，这两个类提供丰富的操作符。

  - Flux对象实现的发布者，可返回N个元素。
  - Mono实现的发布者，只可返回0或1个元素。

- Flux和Mono都是数据流的发布者，使用Flux和Mono都可以发出三种数据信号：数据值、错误信号、完成信号，错误信息和完成信息都代表终止信号，终止信号用于告诉订阅者数据流结束了（也说明本次请求的任务已经完成了），**错误信号除了终止信号的作用外，同时把错误信息信息传递给订阅者**。

  - 引入依赖

  ![20230302113743](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302113743.jpg)

  ![20230302114419](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302114419.jpg)

- 三种信号特点

  - 终止信号和完成信号不能共存。
  - 如果没有发送任何数据，而是直接发送错误或者完成信号，表示空数据流。
  - 如果没有错误信号，没有完成信号，表示无限数据流（准确说是本次发布订阅不会取消，结束通信）
  - 调用just或者其他方法知识声明数据流，数据流并没有发出，只有进行订阅之后才会触发数据流，不订阅什么都不会发生的（因为数据流根本没有发布对象）。

#### 操作符

- 对数据流进行一道道操作，称为操作符，比如工厂流水线。（**类比stream流的各种操作**）
- map（将数据流中的元素映射为新的元素）
- flatMap（将数据流中的每个元素映射为流，然后将转换后多个流合并为大的流返回。**就像是将每个元素进行分类，流水线上的分货，一个入口给很多货品，然后将不同类的货品放在不同线上，最后到终点输出**）

#### webflux执行流程

- webflux基于reactor，默认容器时netty，netty时高性能，NIO（异步非阻塞）框架。

  - 阻塞IO：BIO，一个专门监听请求的线程，监听到请求后创建一个线程进行请求处理。（为了管理资源就会使用线程池进行管理，如果某个请求需要传递大量数据，那么线程会一直处于被占用却没有使用，这样导致线程资源很快耗尽，大量请求无法处理）

    ![20230302134336](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302134336.jpg)

  - 非阻塞IO：NIO，就像观察者模式（通道就是被观察者，选择器就是观察者）。服务端有多个通道，每个注册到选择器，选择器订阅自己感兴趣的通道的事件（Connect、Accept、Read、Write），一旦通道发生该事件，就会通知选择器，选择器就会执行响应的行为。

    ![20230302134823](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302134823.jpg)

- springwebflux执行过程和springmvc相似

  - webflux的核心控制器是DispatchHandler，其实现了接口**WebHandler**,负责请求的处理，就是对请求进行映射，并且将处理结果进行返回。

    ![20230302140552](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302140552.jpg)

  - mvc的核心控制器是DispatchServlet

  - webflux的HandlerMapping（用于请求匹配，匹配到具体的类具体要执行的方法）

  - webflux的HandlerAdapter（真正用来进行请求处理）

  - webflux的HandlerResultHandler（用于响应结果处理）

- springwebflux实现函数式编程的两个接口：RouterFunction（路由处理）和HandlerFunction（具体方法的处理）。

#### webflux基于注解编程模型

- 和mvc基本相似，只需要将相关依赖配置到项目中，springboot自动配置相关运行容器，默认情况下使用Netty服务器

- 创建spring boot工程，引入webflu启动器

  ![20230302152319](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302152319.jpg)

- 配置netty启动端口号：server.port=8081

- 创建相关的包controller、service、entry

  - 编写service层接口以及实现类，实现类如下

    ![20230302153222](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302153222.jpg)

    ![20230302153559](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302153559.jpg)

  - controller层

    ![20230302153906](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302153906.jpg)

- 说明

  - mvc方式是通过同步阻塞的方式，基于mvc+servlet+tomcat
  - webflux方式实现，异步非阻塞方式，基于webflux+reactor+netty
  - 上面同阻塞与非阻塞主要是针对web服务器端的方法执行，不针对浏览器端和服务端的交互。

#### webflux基于函数式编程模型

- 在使用函数式编程模型操作webflux时，需要自己初始化服务器

- 基于函数式编程时，有两个接口：RouterFunction（路由处理）和HandlerFunction（具体方法的处理）

  - RouterFunction：将请求转发给对应的handler。

  - HandlerFunction：处理请求生成响应的函数。

  - 核心任务定义两个函数式接口的实现并启动需要的服务器

  - webflux的请求和响应不再是ServletRequest和ServletResponse，而是ServerRequest和ServerResponse。

    ![20230302165434](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302165434.jpg)

    ![20230302170622](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302170622.jpg)

- 初始化服务器，编写Router

  - 因为使用注解能够自动将请求路径与方法进行匹配，现在没有注解我们需要手动编程进行匹配

  - 我们上面只写了处理函数，没有将路由与函数映射起来（**参考mvc映射配置**）

  - **要将路由与函数之间使用编程映射起来，需要编写适配器**

  - 服务器、函数处理、路由、适配器之间的关系

    ![20230302172959](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302172959.jpg)

  - 创建路由

    ![20230302173529](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302173529.jpg)

    ![20230302173819](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302173819.jpg)

  - 创建服务器完成适配（**上面相当于仅仅创建了一个路由表，谁来使用这个路由表还没说明，使用路由表的是适配器，适配器根据路由表找到函数并执行**）

    - ![20230302174441](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302174441.jpg)

  - 调用服务器

    ![20230302174658](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302174658.jpg)

#### webclient访问webflux

![20230302175523](D:\summary\summary\知识总结\再入spring\spring源码\img\20230302175523.jpg)

