# Mybatis

-[一篇文章讲清楚VO，BO，PO，DO，DTO的区别 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/102389552)

[java中的bo和do_阿里java开发手册中命名规约解读之DO/BO/DTO/VO/AO_掠食者无人姬的博客-CSDN博客](https://blog.csdn.net/weixin_34910679/article/details/113388273)

- 一门封装了jdbc的框架，半自动ORM
- 支持定制化sql,存储过程以及高级映射（自定义映射resultMap,自定义字段和属性之间的映射关系）的持久层框架
- 避免了几乎所有的JDBC代码和手动设置参数（参数拼接）以及获取结果集
- 通过xml/注解/原始映射将pojo映射到数据库中的记录
- DAO：数据传输对象

## 常用对象类型定义

- [详述 PO VO BO DTO DAO 和 POJO 的概念及区别_CG国斌的博客-CSDN博客_dto po vo](https://blog.csdn.net/qq_35246620/article/details/77247427)

- [PO,BO,VO和POJO的区别_π大新的博客-CSDN博客_po vo](https://blog.csdn.net/u011870547/article/details/81077153/)

- [关于sevice层和bo_guichao.dong的博客-CSDN博客_bo与service](https://blog.csdn.net/weixin_43671743/article/details/103925910)

- **[SpringBoot框架中各层（DTO、DAO、Service、Controller）理解 - 鳄鱼儿 - 博客园 (cnblogs.com)](https://www.cnblogs.com/tooyi/p/13340374.html)，这篇文章对系统理解有较大用处**

- **[vo、dto、bo、do、po的概念理解以及与controller、service、dao层的对应关系_Albertliuc的博客-CSDN博客](https://blog.csdn.net/m0_70651612/article/details/125833766)**这篇页非常值得一看

- 第 1 个：DAO-与数据库交流的对象

  　　DAO（Data Access Object）数据访问对象，它是一个面向对象的数据库接口，负责持久层的操作，为业务层提供接口，主要用来封装对数据库的访问，常见操作无外乎 CURD。我们也可以认为一个 DAO 对应一个 POJO 的对象，它位于业务逻辑与数据库资源中间，可以结合 PO 对数据库进行相关的操作。

  第 2 个：PO-在DAO与数据库之间传递的对象，往往就是数据库的实体表中的记录，Java中的实体类的实例

  　　PO（Persistent Object）持久层对象，它是由一组属性和属性的get和set方法组成，最简单的 PO 就是对应数据库中某个表中的一条记录（也就是说，我们可以将数据库表中的一条记录理解为一个持久层对象），多个记录可以用 PO 的集合，PO 中应该不包含任何对数据库的操作。PO 的属性是跟数据库表的字段一一对应的，此外 PO 对象需要实现序列化接口。**一个PO对应一张数据表，一个PO往往对应一个DAO(Mybatis中的Mapper对象，仅仅提供CRUD操作)**

  第 3 个：BO-执行业务逻辑的对象

  　　BO（Business Object）业务层对象，是简单的真实世界的软件抽象，通常位于中间层。BO 的主要作用是把业务逻辑封装为一个对象，这个对象可以包括一个或多个其它的对象。举一个求职简历的例子，每份简历都包括教育经历、项目经历等，我们可以让教育经历和项目经历分别对应一个 PO，这样在我们建立对应求职简历的 BO 对象处理简历的时候，让每个 BO 都包含这些 PO 即可。**意思就是BO不仅仅是说它具有具体数据，关键是它还具有一定的业务性质，是在某个具体的业务场景中适用，它应该具有在该业务场景下的一些固定的必须的业务逻辑。BO中的业务逻辑往往是对它所拥有的PO数据进行处理。**

  ```text
  //用例子对BO进行理解
  个人简历包括：教育经历、工作经历、社会关系。也就是说个人简历本质由这三者组成，所以我们可以将三者作为三个PO存储到数据库中并对应各自的DAO，而单从数据而言简历就作为一个VO，因为它仅仅只是作为前端展示。但是我们需要对这个VO进行一些逻辑操作，所以你可以将VO与DAO封装成一个-BO,当我们对简历进行CRUD时，其实就是通过DAO对三个Po进行操作.当然你可以省略封装VO这一过程直接将三个PO与DAO封装为BO，这样我们操作对象更加明确并且粒度也可以提升。
  //BO与service的关系
  往往service操作BO，BO操作DAO
  ```
  
  
  
  第 4 个：VO-value object 值对象 / view object 表现层对象
  
  　　VO（Value Object）值对象，主要用于页面中要展示的数据组装起来。
  
  第 5 个：DTO-控制层和前端页面传递的对象
  
  　　DTO（Data Transfer Object）数据传输对象，主要用于远程调用等需要大量传输对象的地方，比如我们有一个交易订单表，含有 25 个字段，那么其对应的 PO 就有 25 个属性，但我们的页面上只需要显示 5 个字段，因此没有必要把整个 PO 对象传递给客户端，这时我们只需把仅有 5 个属性的 DTO 把结果传递给客户端即可，而且如果用这个对象来对应界面的显示对象，那此时它的身份就转为 VO。使用 DTO 的好处有两个，一是能避免传递过多的无用数据，提高数据的传输速度；二是能隐藏后端的表结构。常见的用法是：将请求的数据或属性组装成一个 RequestDTO，再将响应的数据或属性组装成一个 ResponseDTO.**也就是说如果我们直接VO返回你可以说VO就是DTO。如果我们再抽象一下，VO仅仅只是前端请求需要的数据，而除了数据之外，我们需要对响应标注其他内容比如，code,msg，那我们就可以用code,msg，data构成一个DTO，VO只需要注入即可，这个DTO它的粒度更大，可使用范围更广。所以VO在直接作为数据返回的情况下被称为DTO并没有错**

## 与其他框架的对比

- JDBC
  - SQL夹杂在Java代码中耦合度高，导致硬编码
  - 硬编码导致程序的扩展性、可维护性低
  - 代码冗余（重复sql），开发效率低
- Hibernate和JPA
  - 操作简单，开发效率高
  - 程序中复杂的sql需要绕过框架
  - 内部自动生产的sql，不容易优化，且有些sql用不上
  - 基于全映射的全自动框架，大量字段的pojo映射困难
  - 发射操作太多，导致性能下降
- mybatis
  - 轻量级性能出色
  - sql和Java分离
  - 开发效率稍低，但也提供一定的自动化sql生成提高效率
  - 可以自己优化sql

- [Spring Data JPA 与 MyBatis对比_殇莫忆的博客-CSDN博客_springjpa和mybatis](https://blog.csdn.net/qq_28289405/article/details/83503343)

## 环境搭建

- 导入mybatis依赖、MySQL依赖
- 如果要整合就导入mybatis-spring

### 核心配置文件

- 为什么需要事务管理：
  - 事务是数据库中的定义，运行sql最好使用事务进行运行，保证数据的安全性，因为操作可回滚。
  - 事务对于程序来说是一种重复化的操作，所以可以进行封装起来，每次执行sql的时候进行执行即可。而我们程序就只关注写sql。
  - 所以将sql事务管理剔出为一个管理程序是有必要的。

```xml
<?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--mybatis的操作环境环境-->
    <environments default="master">
        <!--环境1-->
    	<enviroment id="master">
            <!--事务管理，使用JDBC的事务管理,JDBC的事务管理需要自己手动开启和提交
				MANAGED:被管理。就是mybatis本身不提供事务而是让程序容器实现事务的管理：入spring容器的管理事务
-->
        	<transactionManager type="JDBC/MANAGED"/>
            <!--数据源，数据库连接池,
				POOLED:使用数据库连接池
				UNPOOLED:不使用连接池
				JNDI:使用上下文数据源
			    数据源一般由spring进行管理
				还可以使用<properties resource="">引入配置文件
			-->
            <properties resource="jdbc.properties"/>
          	<datasource type="POOLED|UNPOOLED|JNDI">
                <property name="driverClass" value="${jdbc.driverClass}"/>
        		<property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
        		<property name="user" value="${jdbc.user}"/>
        		<property name="password" value="${jdbc.password}"/>
        		<property name="minPoolSize" value="${jdbc.miniPoolSize}"/>
        		<property name="maxPoolSize" value="${jdbc.maxPoolSize}"/>
        		<property name="initialPoolSize" value="${jdbc.initialPoolSize}"/>
        		<property name="maxIdleTime" value="${jdbc.maxIdleTime}"/>
        		<property name="checkoutTimeout" value="${jdbc.checkoutTimeout}"/>
            </datasource>
        </enviroment>
    </environments>
    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!--配置log4j2-->
        <setting name="logImpl" value="LOG4J2"/>
    </settings>
    <!--开启类型别名-->
    <typeAliases>
    	<typeAlias type="全限定名" alias="别名，不写默认就是类名">
        <packagr name="包名，为指定包设置默认的别名"></packagr>
    </typeAliases>
    <!--配置分页插件-->
    <plugins>
        <plugin interceptor="com.github.pagehelper.PageHelper">
            <property name="dialect" value="mysql"/>
        </plugin>
    </plugins>
    
    <!--引入映射文件,mybatis要解析sql的配置文件,这也说明接口和xml之间的映射是从配置到接口-->
    <mappers>
        <!--文件路径-->
        <mapper resource="/mapper/mapper.xml"></mapper>
        <!--通过扫描xml所在包进行注册，但是必须要保证xml名和接口名一致，且处于同一个包下-->
        <package name="com.mapper"></package>
        
    </mappers>
</configuration>
```

- 使用方式：

  - 不使用Mapper接口，直接使用session提供的统一化操作进行执行sql,调用时你只需要指定你要执行的sql的限定名（xml名和id名）以及所需的参数，session会将其进行组装交给执行引擎调用。这个在实际开发中我们还需要进行手动封装，所以用的少。
  - 使用Mapper接口，通过sqlSession获取指定的mapper动态代理实例。

- Mapper代理使用步骤

  - 创建Mapper接口

  - 创建Mapper的xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="mapper接口的全限定名">
        <!--ResponseJson add(Cart cart);-->
        <insert id="add">
            insert into cart (user_id,book_id,numb,total)values
            (#{userId},#{bookId},#{numb},#{total})
        </insert>
    <mapper/>
    ```

    

  - 上面两者的名字要一致，也不用一致，因为使用了命名空间指定，会进行自动映射（解析xml时创建的代理实例自动映射到接口）。但是保持一致方便。

    - 命名空间与接口保持一致、方法名与sql标签id一致，从而实现了两者之间的映射。从而保证代理类的正常生成。

- 获取sqlsession步骤：解析主配置文件，建造者用解析的字节流建造工厂，通过工厂创建sqlsession.

- [Log4j2配置详解_Hinbo的博客-CSDN博客_log4j2配置](https://blog.csdn.net/batter_hwb/article/details/83416522)

- [log4j2漏洞原理及修复方案_weixin_41701609的博客-CSDN博客_log4j2原理](https://blog.csdn.net/weixin_41701609/article/details/121916781)

### spring提供两种事务管理方式

- 编程式事务：通过spring提供的事务管理操作类，进行手动调用管理
  - 使用TransactionTemplate或者直接使用底层的PlatformTransactionManager。对于编程式事务管理，spring推荐使用TransactionTemplate。
- 声明式事务：通过aop增强需要进行事务管理的方法，执行方法前后进行一些事务声明提交操作。你只需要在需要使用事务的地方进行声明即可。
  - 是建立在AOP之上的。其本质是对方法前后进行拦截，然后在目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。声明式事务最大的优点就是不需要通过编程的方式管理事务，这样就不需要在业务逻辑代码中掺杂事务管理的代码，只需在配置文件中做相关的事务规则声明(或通过基于@Transactional注解的方式)，便可以将事务规则应用到业务逻辑中。

## mybatis获取参数的两种方式

- ${}:本质是通过字符串拼接来拼接sql，若为字符串类型或日期类型的字段进行拼接时，需要手动加单引号。

  - 借用el表达式进行理解，这个就是一个取值符号，所以字符串等，你需要进行引号声明
  - 相当于jdbc我们自己手动拼接

- \#{}：本质是通过占位符赋值进行sql拼接，若为字符串类型或日期类型的字段进行拼接时，自动加单引号。

  - 相当于jdbc句柄的?占位符

  - 常用方式

- 具体执行流程，先解析sql，然后将参数进行注入

### 获取参数的情况

- 获取单个基本类型（包括String）参数
  - 通过#{任意名称}进行获取。因为单个时，它是直接注入，根本不会进行相关检测。
- 获取多个基本类型（包括String）参数
  - 不能通过参数名直接获取
  - **原因：当解析到该接口有多个参数时，会将这些参数解析到一个map集合中，而这个map的键：1、以arg0,arg1......          2、以param1.......   3、通过参数名前的@Param("name")中的name为键。三者可以混合使用**
- 可以自定义map集合
  - 将参数在service层进行封装，然后作为参数传入，xml中通过#{}、${}指定键名就可以直接获取。那么意味着这里它不会将其解析到mybatis的map中
- 传入实体类对象作为参数
  - 同样通过属性名即可获取
  - 但是注意其获取的原理是通过get、set方法，即使你没有这个属性你有get方法仍然能获取到值。
- @Param(“参数名”)：解析参数并将其放在map中。
- 不知道怎么访问就使用@Param

## 查询结果

- 查询结果只有一条可以通过集合或者实体类进行接收
- 如果查询结果多条只能通过集合进行接收，否则TooManyExecption。同样只需要指定返回类型即可。
- 每种类型有mybatis的别名
- 对于查询结果没有具体的是类能对应，通常查询所谓map,使用map来映射字段和字段值。
  - 如果查询单条数据：返回Map,xml返回map即可。以字段为键以字段值为值
  - 如果查询多条数据：
    - 返回Map，xml返回map，需要在接口方法上加上@MapKey("字段名")。以某个字段的值为键，以查询记录为值。返回一个map
    - 返回List\<Map>，xml返回map,不用处理，每个map代表一个记录。

## 特殊sql

- 模糊查询： like "%llsas%"
  - 通常参数我们都使用#{}获取，自动加引号
  - 但是对于模糊查询，我们只需要值即可，不需要加引号:like "%${}%"
  - 如果你的模糊子句是外层自定义也可以使用#{}，加上引号:like #{}
  - 当然你还可以调用mysql的函数进行处理，如contact("%",#{sda},"%"),处理结果返回一个字符串。
  - 你还可以直接使用“%”#{dd}"%"直接进行字符串拼接，最常用这种

## 批量删除

- 对于数值型，可以直接传入String，将批量id进行组合。但是取值只能通过${}取。不要变成字符串。
- 对于字符串也可以进行拼接
- 总之一句话，注意sql与Java中的类型对象即可。



## 动态设置表名

- 可以将表名设置为参数，进行指定表名进行指定表查询。
  - 可以一定程度提高复用（基本忽略，用这个不如mp）
  - 可以对于**水平拆分的表**可以在逻辑层面表现为操作一张表，但是实际是操作多张表。
  - 因为mysql中只有日期、字符串、字段名可以加单引号，而表名不呢个所以取值用${}

## 获取自增主键

- 在mapper的xml中的插入属性开启获取自增主键,并且指定自增主键的值返回到的属性

- 注意普通的返回值，都只是返回受影响行数

  ```xml
  <insert id="" useGeneratedKeys="true" keyProperty="id">
  </insert>
  ```

  

## 自定义映射ResultMap

- 结果映射集
- 结果集映射逻辑：最终查询得到数据表得表头与类型得属性名进行映射。所以我们可以通过设置别名与属性名进行映射。

```xml
<!--在主配置文件中，设置全局得配置-->
<settings>
    <!--将字段名下划线命名映射为驼峰命名，像这种能够通过驼峰命名法直接解决的就不用再resultMap-->
	<setting name="mapUnderscoreToCamelCase" value="true"></setting>
</settings>
```

- resultMap进行字段（包括别名）映射属性映射,建议全表映射，以后用逆向工程生成

  ```xml
  <resultMap id="1" type="User===要进行映射的实体类型">
  	<id property="属性名" column="字段名"></id>
      <result property="" column=""></result>
  </resultMap>
  ```

  

### 通过resultMap解决一对多的关系

- 一种情况：一个类里面持有另一个类的实例一个引用。（一个对象中包含另一个对象）

  - 使用级联属性赋值

  - 就是逐步将这个内部对象的属性进行展开（通过其**引用名.属性名**即可），然后与对应的属性进行赋值。

    ```xml
    <resultMap id="1" type="User===要进行映射的实体类型">
    	<id property="属性名" column="字段名"></id>
        <result property="" column=""></result>
        <result property="p.age" column="p_age"></result>
    </resultMap>
    ```

    

- 一对一、多对一（统一特征就是一个对象中含有另一个对象的引用,将多对一转换为一对一）

  - 推荐处理方式使用\<association>进行映射

    ```xml
    <resultMap id="1" type="User===要进行映射的实体类型">
    	<id property="属性名" column="字段名"></id>
        <result property="" column=""></result>
        <!--映射：property指定User对象含有的另一个对象的引用名p,并指定类型，相当于给定一个标签进行拆分映射-->
        <association property="p" javaType="P">
        	<id property="属性名" column="字段名"></id>
         	<result property="" column=""></result>
        </association>
    </resultMap>
    ```

    

  - 用的最多的方式：**分步查询**，先查询某一个对象，再通过这个对象与另一个对象的关联字段进行第二次查询。

    - 用mysql的话就是嵌套子查询。

      ```xml
      <resultMap id="1" type="User===要进行映射的实体类型">
      	<id property="id" column="字段名"></id>
          <result property="" column=""></result>
          <association property="p"
                       select="子查询sql===一般写要调用的查询的方法的全限定名.方法名"
                       column="id====调用子查询时传入的条件参数，一般这个参数必然是两者关联的字段"
                       fetchType="设置该查询是否使用延迟加载==lazy/eager">
          </association>
      </resultMap>
      ```

      

    - **分步查询的优点**

      - *延迟加载*:默认不开启，对于业务逻辑比较复杂的情况节省内存有效，但是对于直接展示在前台其实作用并不明显,因为要就全要,不要我可以直接手动触发

        - lazyLoadingEnabled:延迟加载的全局开关，当开启时，所有关联对象都会延迟加载

        - aggressiveLazyLoading(侵略懒加载)：当开启时，任何方法的调用都会加载该对象的所有属性，否则，每个属性会按需加载。

        - 故前者开启，后者关闭

          ```xml
          <!--在主配置文件中，设置全局得配置-->
          <settings>
              <setting name="lazyLoadingEnabled" value="true">
              </setting>
              <setting name="aggressiveLazyLoading" value="false"></setting>
          </settings>
          ```

          

        - 可以通过fetchType进行控制单个查询的延迟加载的使用与否

      - *sql重用*：跨mapper调用sql,同样是缺点,增加xml之间的耦合.

- 一对多:一个对象中要持有多个同类对象(通常使用集合进行使用,将查询出来的多个对象放在集合中,如List)

  ```xml
  <resultMap id="m" type="user">
      <id property="id" column="字段名"></id>
      <result property="" column=""></result>
      <!--表明这个属性名是一个集合,查询的结果要构造一个集合然后返回-->
      <collection property="p" ofType="P">
      	<id property="id" column="字段名"></id>
      	<result property="" column=""></result>
      </collection>
  </resultMap>
  ```

  - 通过分步查询实现一对多:先查询用户,再定义resultMap调用第二步

    ```xml
    <resultMap id="m" type="user">
        <id property="id" column="字段名"></id>
        <result property="" column=""></result>
        <!--表明这个属性名是一个集合,查询的结果要构造一个集合然后返回-->
        <collection property="p" 
                    select="第二部查询"
                    column="关联字段-注意是字段不是属性,这里应该是可以指定多个字段的,毕竟本质是嵌套子查询"
                    fentchType="lazy">
    
        </collection>
    </resultMap>
    ```

- **注意分步查询的的sql返回的resultMap和第一步一致,因为两者实质上是一条sql**

## 动态SQL

- 目的解决sql拼接问题,如逗号,{},(),null判断等等,提高复用
- 我们一条sql不能写死,要复用,根据不同的条件进行执行

### if

- 满足条件就拼接

```xml
<if test="name!=null and name!=''">
	and .....
</if>
```

### where

- 对于一个条件都不用的时候where无用,sql报错.对于多余的and,or进行去除.没有条件where不生成.

  ```xml
  <where>
  
  </where>
  ```



### trim

- 标签中有内容时,下面的属性生效

- prefix:向trim标签中内容前面添加内容
- suffix:向trim标签中内容后面添加内容
- suffixOverrides:将trim标签中前面内容去掉
- prefixOverrides:将trim标签中后面内容去掉

```xml
<trim prefix="where(添加where前缀)" suffixOverrides="and|or">

</trim>
<!--在sql生成后进行添加片段和删除片段-->
```

### 连环判断

- choose....when.....otherwise(if....elseif.....else)

```xml
<choose>
	<when test="">
    </when>
    <when test="">
    </when>
    <otherwrise>
    </otherwrise>
</choose>
<!--if....elseif.....else-->
```



### foreach

- 比如批量删除.添加

  ```xml
  <foreach collection="userlist遍历集合的属性名" item="useri每个元素名,随便定" separator=",(每个遍历元素之间的分隔符)" open="(==遍历开始符" close=")==遍历结束符">
     #{useri.name},#{useri.id}
  </foreach>
  
  ```

  - inert into 表名 ()   values(),(),() .现在mysql能够进行批量添加

### sql

- sql片段.定义常用片段,进行复用

  ```xml
  <sql id="sqlid">sql语句定义</sql>
  
  <!--如何引用-->
  <include refid="sqlid"></include>
  ```



## mybatis缓存

- 对查询到的数据进行记录,下次直接拿,下次不再去访问数据库
- mybatis缓存只针对查询功能有效,这也标识了与redis的不同,redis虽然可以做缓存,但是它会可以进行一定时间内的某些数据的修改.可以说redis比较强大.
- **mybatis缓存仅仅是对一定时间的热点sql查询优化,它不会再次进行sql的请求.**

### 一级缓存(默认开启)-会话级别

- 是SQL session级别的缓存,通过同一个sqlsession查询的数据会被缓存,下次查询相同的数据,就会从缓存中直接取,不会从数据库重新访问
- 一级缓存失效的情况
  - 不同的sqlsession对应不同的一级缓存(就像不同的cpu内嵌自己的一级缓存)
  - 同一个sqlsession但是查询条件不同(也就是只针对同一条sql)
  - 同一个sqlsession两次查询期间执行了任何一次增删改查,*一旦执行就会将缓存清空*(**因为这些操作必然会直接访问数据库,而但是缓存并不会进行更改,所以数据库可能不一致,所以我们需要直接访问数据库获取最新的数据**)
  - 同一个sqlsession两次查询期间手动清空了缓存----**sqlsession.clearCache()**(没有数据可拿)
- 什么时候知道我们调用的是同一个sqlsession还是不同的sqlsesion??
  - 非spring托管情况下,我们可以自定义
  - spring情况下,sqlsession创建了几个我们都是不清楚的.
    - [关于Mybatis与Spring整合之后SqlSession与mapper对象之间数量的问题。 - 博学善思。。ljd - 博客园 (cnblogs.com)](https://www.cnblogs.com/ljdblog/p/7123430.html)
    - [你凭什么说Spring会导致MyBatis的一级缓存失效！ - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/157514732)
    - 由源码知,每次进行非事务sql执行获取的都是不同的sqlsession ,所以一级缓存失效.**只有在进行事务处理时,sqlsession才会被缓存起来,废话,事务原子性,难不成还开新的会话?**
- 总之mybatis缓存用处不大

### 二级缓存

- **[Mybatis 为什么不要用二级缓存 - 不会就问咯 - 博客园 (cnblogs.com)](https://www.cnblogs.com/KingIceMou/p/9389872.html)**
- [Mybatis 缓存机制详解 - 艾涕喵 - 博客园 (cnblogs.com)](https://www.cnblogs.com/iteacat/p/13306691.html)

- 二级缓存是sqlsessionfactory级别(就是只要是同一个工厂创建的sqlsession都可以获取),通过同一个SQL sessionfactory创建的sqlsession查询的结果会被缓存,此后若再次执行相同的查询语句,结果就会从缓存中获取.
- 二级缓存开启的条件
  - 在核心配置文件中,设置全局配置属性(在setting中cacheEnabled="true",默认为true,不需要设置)
  - 在映射文件中设置标签\<cache/>
  - 二级缓存必须在sqlsession关闭或提交之后有效,没有关闭或提交之前保存在一级缓存,关闭或提交后才会保存在二级缓存
  - 查询的数据所转换的实体类类型必须实现序列化的接口

- 二级缓存失效的情况
  - **两次查询之间执行了任意的增删改查,会使一级和二级缓存同时失效**
- 二级缓存可以与其他缓存进行整合实现,但是还是属于mybatis进行管理
  - ehcache
  - redis

## mybatis逆向工程

- 导入逆向工程插件

- 编写配置文件

  ```generatorConfig.xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE generatorConfiguration
    PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
  
  <generatorConfiguration>
      <context id="testTables" targetRuntime="MyBatis3Simple">
          <commentGenerator>
              <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
              <property name="suppressAllComments" value="true" />
          </commentGenerator>
          <!--数据库连接的信息：驱动类、连接地址、用户名、密码 -->
          <jdbcConnection driverClass="com.mysql.jdbc.Driver"
              connectionURL="jdbc:mysql://localhost:3306/test" userId="root"
              password="root">
          </jdbcConnection>
          
          <!-- <jdbcConnection driverClass="oracle.jdbc.OracleDriver" connectionURL="jdbc:oracle:thin:@127.0.0.1:1521:yycg" 
              userId="yycg" password="yycg"> </jdbcConnection> -->
  
          <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer，为 true时把JDBC DECIMAL 
              和 NUMERIC 类型解析为java.math.BigDecimal -->
          <javaTypeResolver>
              <property name="forceBigDecimals" value="false" />
          </javaTypeResolver>
  
          <!-- targetProject:生成PO类的位置 -->
          <javaModelGenerator targetPackage="com.wuhao.mt.domain"
              targetProject=".\src">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="false" />
              <!-- 从数据库返回的值被清理前后的空格 -->
              <property name="trimStrings" value="true" />
          </javaModelGenerator>
          <!-- targetProject:mapper映射文件生成的位置 -->
          <sqlMapGenerator targetPackage="com.wuhao.mt.mapper"
              targetProject=".\src">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="false" />
          </sqlMapGenerator>
          <!-- targetPackage：mapper接口生成的位置 -->
          <javaClientGenerator type="XMLMAPPER"
              targetPackage="com.wuhao.mt.mapper" targetProject=".\src">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="false" />
          </javaClientGenerator>
          <!-- 指定数据库表 -->
          <table tableName="user"></table>
          <table tableName="orders"></table>
          <table tableName="orderdetail"></table>
          <table tableName="items"></table>
      </context>
  </generatorConfiguration>
  ```

  对于条件查询：和mp差不多，自己创建一个条件器，然后按需要进行封装，最后把条件器传入。缺点，不好优化。

  ```xml
  可以试试spring-data-mybatis-mini[github地址](https://github.com/VonChange/spring-data-mybatis-mini)[gitee地址](https://gitee.com/vonchange/spring-data-mybatis-mini)[blog](http://www.vonchange.com/doc/mini.html)等同于spring data jdbc + mybatis 动态sql能力1. 抛弃繁琐的xml 只使用mybatis模版引擎即动态sql能力 sql写在markdown文件里更容易书写和阅读 sql能统一管理查看2. 底层基于springJdbc 而不是mybatis 更直接纯粹3. 提供单表增删改 批量更新插入等基础方法 支持分页 读写分离4. mybatis最大优点就是sql模版引擎 我也有且仅有使用这部分功能(对于使用过mybatis的无学习成本)5.  支持真的批量更新和流读相比mybatis-plus等扩展mybatis框架 他们做的越来越像hibernate,jpa 搞Criteria那套 基本脱离mybatis优点底层使用springJDBC才是王道
  ```

  

- 不做优化用起来其实还行

```xml
<!--分页插件依赖-->
<dependency>
  <groupId>com.github.pagehelper</groupId>
  <artifactId>pagehelper</artifactId>
  <version>3.7.5</version>
</dependency>
<dependency>
  <groupId>com.github.jsqlparser</groupId>
  <artifactId>jsqlparser</artifactId>
  <version>0.9.1</version>
</dependency>

<!--文件上传依赖-->
<dependency>
  <groupId>commons-fileupload</groupId>
  <artifactId>commons-fileupload</artifactId>
  <version>1.3.1</version>
</dependency>
<dependency>
  <groupId>commons-io</groupId>
  <artifactId>commons-io</artifactId>
  <version>2.3</version>
</dependency>
```

- 分页插件使用

  ​	PageHelper.startPage(要显示第几页,每页显示数据的条数)

