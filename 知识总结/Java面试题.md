# Java面试题

- [2022年春招，Java后端最全面试攻略，吃透25个技术栈 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/446341767)
- [阿里技术三面：JVM+高并发性能+单点登录+微服务_java1856905的博客-CSDN博客_单点登录并发](https://blog.csdn.net/java1856905/article/details/89000945)

## 一、JavaOOP面试题

1、short s1 = 1; s1 = s1 + 1;有错吗? short s1 = 1; s1 += 1; 有错吗？

- 前者有，需要强制转换。因为Java中数值常量默认是int型。但是初始化时会自动转换（隐式转换）。

2、重载和重写的区别

- 一个类中的同名方法而不同参数（包括参数个数、参数类型）是方法重载。
- 一种是子类继承普通父类对父类同名方法进行二次实现叫做重写。第二种是继承抽象类必须进行抽象方法的重写。第三种实现接口必须对接口定义的方法进行重写。Java中明显的标识就是@Override注解。

3、数组实例化有几种方式？

- 三种：{}，new,反射--Array.newInstance()，new Object[]{}

4、Java中各种数据默认值

- 整数型：0
- 浮点数：0.0f，0.0d
- boolean:false
- char:默认值是\u0000 ，不初始化不会报错，但是每次使用前必须进行显式的赋值。
- 对象：默认值null

5、Object类常用方法有那些？

- wait
- notify、notifyall
- getClass
- equals()
- hashCode()
- 注意equals和hashCode以及==之间的区别和联系

6、java中是值传递引用传递？

- 如果是进行基本类型的值传递那么是使用值值传递。
- 如果是进行引用类型的值传递那么是使用的引用传递。

