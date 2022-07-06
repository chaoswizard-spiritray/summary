# Java集合框架

##1、集合概念

- 概念：对象的容器，定义了对多个对象继续宁操作的常用方法。可实现数组的功能。

- 集合的三个特性：

  - 确定性：一个元素要么属于一个集合要么不属于
  - 无序性：任意改变集合中元素的排列次序，他们仍然表示同一个集合
  - 互异性：同一个集合中的元素是互不相同的

- 和数组的区别：

  - 数组长度固定，集合长度不固定
  - 数组可以存储基本类型和引用类型，集合只能存储引用类型（因为集合使用了泛型概念，而泛型都是使用的引用类型）
  - 位于java.util.*

  

## 2、Collection接口

- collection接口体系集合

  ![](.\图片\20211004210208.jpg)

  - Collection接口：该接口是集合接口的根接口，代表一组对象，称为集合（注意我们描述的集合的特性，还有就是子接口是对父接口的功能进行增强的，所以不要觉得List违背了集合的特性）
  - List接口：有序、有下标、元素可重复（这里的有无序指的是在遍历的顺序与添加时的顺序是否一致，也就是是否遵循时间先后顺序，并不是指按排序规则的有无序。Set的无序也是如此）
    - 实现类
      - ArrayList:数组
      - LinkedList：链表
      - Vector:向量
  - Set接口：无序、无下标、元素不可重复
    - 实现类
      - HashSet
    - 子接口
      - SortedSet（用于Set-集排序的接口）
        - 实现类：TreeSet

- Collection父接口

  - 特点：代表一组任意类型的对象，无序、无下标、不能重复（实际API文档中描述的是一部分是不能重复、一部分是可以重复的）

  - 方法：(详情见API文档)

    ![](.\图片\20211004212708.jpg)

  - 注意使用：一个迭代器获取之后只能够用来遍历一次。且Itr中含有一个expectedModCount，初始值为AbstrctList类中的modCount，每次添加或者删除时会将这个值进行加一操作,当使用迭代器进行遍历或者删除时，如果两者不相等就会报java.util.ConcurrentModificationException。
  
  - [Java ConcurrentModificationException异常原因和解决方法 - Matrix海子 - 博客园 (cnblogs.com)](https://www.cnblogs.com/dolphin0520/p/3933551.html)

## 3、List接口与实现类

- 特点：有序、有下标、元素可以重复
- 方法
  - ![](.\图片\20211005210002.jpg)
- 四种迭代方法
  - 使用for
  - 使用增强for
  - 使用Iterator
  - 使用ListIterator(比Iterator更强，允许用户逆向迭代，还允许用户使用该迭代器进行修改、添加、删除元素)
- 实现类
  - ArrayList:数组结构实现、查询快、增删慢、运行快、线程不安全。
  - Vector：数组结构实现，查询快、增删慢、运行效率慢、线程安全。Vector与数组类似，但是它的大小可以根据需要进行增大或者缩小。
  - LinkedList:链表结构实现、增删快、查询慢（实际就是一个双向链表）
  - 注意：使用删除如果要达到自己想要的效果需要重写equals方法。

## 4、泛型和工具类

- 泛型：本质是参数化类型，把类型作为参数进行传递。
- 常见形式：泛型类、泛型接口、泛型方法。
- 语法：<T，...>T称为类型占位符，表示一种引用类型。
- 好处：提高代码重用性，防止类型转换异常，提高代码的安全性
- 不同的泛型对象不能相互赋值。
- 类中的成员变量如果是静态常量不能使用类泛型或者是接口泛型，静态的都不行，因为泛型是动态的。细究一下原因：静态的代表一个类有一份，所有对象实例进行共享，而泛型是可以由对象进行各自指定的，如果不一样，那么共享的如何办？所以冲突不可避免，不能这么用。
- 静态方法我们可以使用泛型方法。泛型方法的泛型只能通过函数参数进行传入，也就是无参函数尽量不使用泛型方法。
- 泛型接口需要在创建实现类的时候就进行指定。
- Collections工具类：定义了除了存取以外的常用集合操作
  - ![](.\图片\20211009210651.jpg)
  - 注意：通过Arrays.asList()将数组转换为List后，是一个受限的List,不能进行删除和添加，也就是这个已经固定死了。从源码可知：转换后的实例是一个Arrays中的私有静态内部类。这个类继承自AbstractList,所以能强转为List，这个类中的含有一个常量数组，进行强转时，其实就是将数组赋给这个常量数组，所以自然是不能再对原数组进行增删改操作。

## 5、Set接口与实现类

- set中的方法全部继承自Collection中的方法。

- HashSet
  - 基于HashCode实现元素不重复（也就是是一个hash表实现的，那么必然会涉及到哈希函数，为了解决哈希冲突使用链表的方式解决）
  - 当存入元素的哈希码相同时，会调用equals进行确认，如结果为true，则拒绝后者进行，或者是进行更新。
  - 基于HashCode计算元素存放位置
  - 当存入元素的哈希码相同时，会调用equals进行确认，如果结果为true，则拒绝进入
  
- TreeSet
  - 实现的是SortedSet接口
  - 使用的是二叉树进行实现的
  - 基于排列顺序实现元素不重复，正因为使用这两种数据结构实现的，所以是时间上无序
  
- 3种遍历方式
  
  - 增强for
  - 使用Iterator
  - 使用Enumeration
  
- HashSet源码解析：

  - hashset内含一个hashmap，且其操作主要通过hashmap来完成
    - 以add操作为例，hashset调用的其实是hashmap的put操作。put操作调用的是其本身的putVal函数，该函数需要四个参数（hasn值，键，值，是否更新相同hash、key的value，是否驱逐），后面两个不懂。
      - hash值是通过其本身的hash函数进行获取，而这个hash函数的逻辑是将key传入，为空就返回0，如果不为空就调用key本身的hashCode(所以我们可以重写自己的hashCode函数,)获取到hash值并与这个值无符号右移16位后进行异或。
      - hashmap本身成员变量中有一个Node<K,V>数组，这个Node是hashmap的内部类，它含有hash、key、value 、next。也就意味着这个Node数组是一个数组配链表的结构，这个组合也就是我们所说的哈希表。也就是说这个数组才是我们最终存放数据的地方。
      - putVal的执行逻辑，先判断哈希表是否为空，如果为空，或者大小为0，就使用resize重新构造一个。接着再判断，如果通过hash值&上（n-1）的位置上没有元素，就在这个位置存放传入的值，然后执行修改次数加一，接着返回null。否则，就看这个位置的元素的hash值以及key值（key是使用equals进行比较的）是否与传入的一致，如果传入的一致，就根据onlyifabsent来替换这个元素的value，如果它本身value就为空，也必须更新，然后将返回该元素的oldValue的引用；如果不相等就遍历这个位置的链表，如果找到了与它相等的元素就执行前面说的操作，没有就将这个新元素添加到链表末尾。
      - 31是一个质数，使用质数可以减少散列冲突，31提高执行效率：31*i=(i<<5)-i
      - [Java中的<< 和 >> 和 >>> 详细分析 - 吹静静 - 博客园 (cnblogs.com)](https://www.cnblogs.com/chuijingjing/p/9405598.html)
      - 使用hash函数进行映射，底层使用的是非二叉树以及相应的进化数据结构，所以没有时间顺序没有大小顺序。

- TreeSet:（使用红黑树）

  - [(4条消息) 树（三）红黑树与平衡二叉树的区别_大海_sea的博客-CSDN博客_红黑树与平衡二叉树的区别](https://blog.csdn.net/y506798278/article/details/104275033)

  - 基于排列顺序实现元素不重复。
  
  - 实现了SortedSet接口。对集合元素自动排序。
  
  - 元素对象的类型必须实现Comparable接口。指定排序规则。
  
  - 通过CompareTo方法确定是否为重复元素。
  
  - 个人觉得Comparator灵活性比较好根据不同的集合实例可以采用不同比较规则，主要还是看需求。
  
    ![image-20211008105356990](\图片\image-20211008105356990.png)
    
  - [HashSet与TreeSet 区别 - 割肉机 - 博客园 (cnblogs.com)](https://www.cnblogs.com/williamjie/p/9099038.html)

## 6、Map接口与实现类

- 特点

  - 用于存储任意键值对

  - 键：无序、无下标、不允许重复

  - 值：无序、无下标、允许重复

    ![](.\图片\20211009132404.jpg)
    
    最后那个方法将键值对封装为一个整体，然后将所有的键值对组成一个set返回。
  
- HashMap：

  - jdk1.2，线程不安全，运行效率快，允许用null作为key或是value。

  - [(4条消息) Java源码解析HashMap成员变量_Pure Pleasure-CSDN博客](https://blog.csdn.net/li_canhui/article/details/85088659)

  - [(4条消息) 关于hashMap的扩容与红黑树与属性TREEIFY_THRESHOLD（树型阈值）与MIN_TREEIFY_CAPACITY（最小树容量）_qq810627883的博客-CSDN博客](https://blog.csdn.net/qq810627883/article/details/119210195)

  - 既然hashmap会在链表与红黑树之间进行转换，我们知道当使用hashCode计算出的hash值一样时，会进行比较相等，我们是使用equals方法，那么我们变成树之后需要进行大小比较，怎么办？查看源码知道，那我们仍然需要目标类实现Comparable接口，因为HashMap并没有提供使用比较器进行比较。

    ![](.\图片\20211009200642.jpg)

- HashTable

  - jdk1.0。线程安全、运行效率慢，不允许null作为key或者value。
  - 这个类基本不使用了。但是它有个子类Properties类。
  - 底层使用的是一个Entry数组

- Properties:HashTable的子类，要求key和value都是String。通常用于配置文件的读取。

- TreeMap:实现了SortedMap接口，可以对key自动排序。

  - TreeMap默认按键的升序进行排序,不允许null键和null值,效率比HashMap低,线程不安全

    TreeMap是桶+红黑树的实现方式.TreeMap的底层结构就是一个数组,数组中每一个元素又是一个红黑树.当添加一个元素(key-value)的时候,根据key的hash值来确定插入到哪一个桶中(确定插入数组中的位置),当桶中有多个元素时,使用红黑树进行保存;当一个桶中存放的数据过多,那么根据key查找的效率就会降低

    为了解决这个问题呢

    hash数组的默认大小是11,当hash数组的容量超过初始容量0.75时,增加的方式是old*2+1

  - [TreeMap底层实现原理_cyywxy的博客-CSDN博客_treemap底层实现原理](https://blog.csdn.net/cyywxy/article/details/81151104)

## 7、集合框架总体架构关系

- [面试常被问到的 Java 集合知识点（详细） - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1775528)
- [为什么阿里巴巴Java开发手册中不建议在循环体中使用+进行字符串拼接？ - 武培轩 - 博客园 (cnblogs.com)](https://www.cnblogs.com/wupeixuan/p/11729920.html)
  - 就是说+也是通过StringBuilder实现的，但是在循环体内每次都会创建一个StringBuilder，将要拼接String转换为StringBuilder，再执行append方法。而非循环体只会进行一次那么使用StringBuilder还是+都一样，+也会被优化为StringBuilder来使用append。

## 8、泛型Generic（亦称参数化类型ParameterizedType）

- 参考文章：
  
  - **[Java 不能实现真正泛型的原因是什么？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/28665443)**
  - **[java 泛型真的会被擦除么 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/292983882)**这篇文章很重要
  - [Java泛型擦除机制之答疑解惑 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/51452375)
  - [(11条消息) Java泛型三：通配符详解extends super_天将降大任于是人-CSDN博客](https://blog.csdn.net/claram/article/details/51943742)
  - [如何通过Java反射获取泛型类型信息 - 云+社区 - 腾讯云 (tencent.com)](https://cloud.tencent.com/developer/article/1447092)
  - [(12条消息) 泛型擦除和堆污染_花栗鼠先生的博客-CSDN博客](https://blog.csdn.net/mononoke111/article/details/90486117?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2~default~CTRLIST~default-1.no_search_link)
  - [(12条消息) JVM学习笔记-类型擦除机制_WD的博客-CSDN博客_jvm泛型的擦除机制](https://blog.csdn.net/qq_31793791/article/details/82186987)
  - [Java基础四---泛型、注解、异常、反射 - 码农教程 (manongjc.com)](http://www.manongjc.com/detail/25-wpvqnsvvzmzrbgt.html)
  - [Java中的泛型会被类型擦除，那为什么在运行期仍然可以使用反射获取到具体的泛型类型？ - 知乎 (zhihu.com)](https://www.zhihu.com/question/346911525/answer/830285753)
  - [(13条消息) 秒懂Java类型（Type）系统_ShuSheng007的程序人生-CSDN博客](https://blog.csdn.net/ShuSheng0007/article/details/89520530)
  - [(14条消息) 秒懂Java泛型_ShuSheng007的程序人生-CSDN博客](https://blog.csdn.net/ShuSheng0007/article/details/80720406)
  
- List<? extends Number>  list的含义是什么？

  - 代表list能够指向一个List，这个List只需要保证其中存储的元素是Number的子类即可
    - 比如：List<? extends Number>  list=new ArrayList<Number>()、List<? extends Number>  list=new ArrayList<Integer>()皆成立。

- 为什么List<? extends Number> list=new ArrayList<>()能通过编译，List<? extends Number> list=new ArrayList<Object>()不行？

  - 后者不能通过编译我们可以理解，因为Object不是Number的子类。在编译期进行类型检查时，必然不能通过。
  - 前者因为jdk的兼容性，允许无泛型引用与任意类型引用进行转换，所以能够通过编译。

- List<Number> list代表什么意思？能否与List<Integer>进行强转？

  - 这代表这个list只能存类型为Number的元素
  - 泛型之间不能直接进行强转，试想，如果泛型类要中使用Number进行强转为Long，结果传了个Integer。这必然报错。

- 如何在一个ArrayLis<Integer>中添加一个String？

  - 方式1：将其赋给一个无泛型指向的List，再使用这个引用进行操作。

    - 
      
      ```java
      List list=new ArrayList<Integer>();
      list.add("ss");
      ```
      
      
      
    
  - 方式2：通过反射进行操作

    - 通过list引用获取到类，再通过类获取到add方法（这里有个注意点-因为泛型擦除，泛型方法应该是无法进行直接获取的，所以我们可以通过遍历所有方法的的方式来获取到泛型方法）

  - **如何理解泛型擦除机制？**

    - 泛型擦除机制是Java为了保证强大兼容性的一种实现泛型的方式。

    - 对于泛型类、接口的流程：

      - 使用new进行泛型实例化时：编译器将检查实例化时传入的泛型参数是否符合泛型类中的定义规范。如果实例化会返回一个引用还会检查这个引用变量的泛型参数是否符合规范：1、要符合泛型类的规范。2、要符合实例化泛型与该变量的规范。

      - 

        ```java
        public class Generic<E extends Number> {
            private E s;
            public void pri(){
                System.out.println(s);
            }
        
            public static void main(String[] args) {
                Generic<Object> s=new Generic<Object>();
            }
        
        ```

        

      - 比如上面的Generic<Object> s=new Generic<Object>()。两者都不符合<E extends Number>，所以无法通过编译。

      - Generic<Number> s=new Generic<Object>()。后面步不符合规范，无法通过编译。

      - Generic<Integer> s=new Generic<Number>()。这里两者虽都符合规范。将Number引用直接赋给Integer是不合法的，强转也无用，这里面有运行时的不确定性，所以编译不通过。

      - 当这部分编译通过时，就会将new这边的泛型擦除掉，也就是变为new Generic（）。并且实际泛型类Generic进行泛型擦除，也就是将使用到T的成员变量，方法参数、返回值、局部变量被替换为边界值。

      - 注意：Generic<Object> s这个泛型并不会被擦除为边界值，**它的泛型信息会被存储在局部变量表中**。

  - **泛型擦除机制的作用域？**

    - 泛型类的成员变量、方法参数、返回值、局部变量。
    - 如果在一个方法中的实例化一个泛型类，那么实例化的构造函数的泛型会被擦除，而返回的局部变量引用不会，而会被存储在局部变量表中。

  - **如何理解泛型上下界引用与各种泛型引用之间的关系？**

  - 泛型引用转换问题

  - 无泛型引用能与任意泛型引用相互转换，为什么？

  - [(13条消息) Java集合泛型--无泛型、Object泛型、?泛型之间的区别_SunHongmin-CSDN博客](https://blog.csdn.net/qq_41788977/article/details/93890948)

  - 因为jdk1.5版本前集合并没有使用泛型，**为了保证兼容性，jdk仍然允许定义无泛型。且允许将让无泛型与任意泛型引用相互转换**。这不仅保证了向前兼容还保证了向后兼容。

  - 集合框架中的泛型元素在显式添加与拿取的时候会进行类型检查，也就是会添加checkcast字节码。这是在编译期完成。所以通过反射在运行期完成元素的插入是不会进行类型检查的。

  - 
    
    ```
    list2.get(0);
    ```
    
    - 如果仅仅是这样一条语句：那么是不会进行checkcast。
    - 但是如果返回的这个值会被放入局部变量表中那么就会进行两边的类型检测。就会插入checkcast。如：String s=list2.get(0);
    - 但是请问这为什么会进行checkcast？list2.get(0).getClass（）;因为它调用了getClass()方法，而要调用非静态方法，就需要明确的引用进行调用。那么也就是说这个需要将前面返回的元素暂存起来，虽然在局部变量表中没有看到，既然要存储需要一个存储位置，这个位置需要指定类型这就取决于泛型参数了，然乎就是进行checkcast。

