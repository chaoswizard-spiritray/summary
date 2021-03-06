#  语言分类

- [Python 到底是强类型语言，还是弱类型语言？ - 豌豆花下猫 - 博客园 (cnblogs.com)](https://www.cnblogs.com/pythonista/p/13592043.html)

- [python是弱类型语言-Python的语言类型(详解)_weixin_37988176的博客-CSDN博客](https://blog.csdn.net/weixin_37988176/article/details/109371060)

- 对于解释型语言跨平台而编译型语言不能跨平台，本人查过很多解释，网上基本在讲什么编译器需依靠平台，而解释型语言依靠不同平台的解释器就可以实现跨平台，我就是想如果在不同平台上装对应的编译器，不也可以实现跨平台吗。

     首先，其实说编译型语言不能跨平台其实不准确或者就是错误理解。其实编译型语言比如c语言也是跨平台语言，理论上，在不同平台装上对应的编译器就可以实现跨平台。但是编译型语言虽然是高级语言，却与平台联系比较紧密（操作系统都是用编译型语言编写的），因此针对不同平台（操作系统）可能有其特定的编写规则，放到其他平台上面，自然编译执行不了。比如，在Windows用c写了调用Windows的API（只在windows系统使用）的程序，在Linux系统的编译器上编译自然会出错。如果能避免这些特殊的使用，编译型语言就可以跨平台了。而对于解释型语言就不存在这个问题，解释型语言先天就和平台联系很少，所以，用解释型语言写出来的一份同样的程序，只要通过不同的解释器解释，就可以在对应不同的平台上使用。

![](C:\Users\胡远灵\Desktop\知识总结\python\图片\20211127122735.jpg)

<img src="图片\20211127134108.jpg" style="zoom:50%;" />

# 简介及环境安装

- 创始人Guido van Rossum
- 面向对象的、弱类型的、由垃圾回收机制
- ![](图片\20211127135558.jpg)

![](图片\20211127135816.jpg)

- 环境安装
  - [Welcome to Python.org](https://www.python.org/)
  - [Python的安装以及环境配置教程_nianmumu的博客-CSDN博客](https://blog.csdn.net/nianmumu/article/details/95522987)
- 电脑三大件的关系：[让 CPU 告诉你硬盘和网络到底有多慢 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/24726196)
  - cpu:只负责计算
  - 内存：只负责数据临时存储
  - 硬盘：只负责数据的长期保存

#正文 （python是强类型动态语言）

## 变量

- [Python3 集合 | 菜鸟教程 (runoob.com)](https://www.runoob.com/python3/python3-set.html)

- 用于代码临时存储数据

- 内存像仓库，变量用于在内存中存放程序数据的容器

- 只能是字母、数字或下划线的任意组合，第一个字符不能是数字，也不能使用python的关键字

- 常用定义规范：驼峰命名法、下划线命名法

- 如果修改一个变量的值，并没有现在内存地址的数据进行覆盖而是新建一个区域存放值，然后将这块区域的地址给变量名，如果一块区域没有指针指向就会被垃圾回收。变量名比C中的指针更像指针。

- 如果删除一个变量del 变量名（当然删除的还是只是标识，实际的数据还是垃圾回收机制进行回收）

- python中没有显式类型定义，所以没有常量的定义，如果一个常量，变量名全部大写

- ```python
  i=10
  print(id(i))
  i=20
  print(id(i))
  j=i
  print(id(j))
  #python中显式值都是作为常量存储，两次i的id不同，说明确实是非值覆盖，而是地址修改。i与j的id相同，说明赋值也只是传址，但是问题是都是传址，共享需求怎么办，要统一修改值怎么办，难道还要去全部修改一次变量的地址？
  ```

  

- 

## 数据类型

- 数字类型：int（在64为系统上，整数为64位）\long（python3中没有long型，都是int）\float(小数)

- 字符串str：存储文字（只要加了“ ”、’ ‘ 的字符都被认为是字符串）

  - 不可修改（相当于Java中的字符串常量放在常量池中），所以放在在固定的位置如果字符串的值相同那么它们的内存地址相同。但是变量可以修改，python中变量就理解为C的void型指针。

  - 有索引，可切片：

    - 可以按照数组形式随机访问比如str[0]、str[2]
    - 可以访问连续片段：比如：str[0:2]、str[2:3]

  - 多行字符串：三个双引号或者三个单引号（“”“ ”“”或者’‘’ ‘’‘）

  - 字符串拼接：使用+即可

  - 字符串引用外部变量：

    - python2的写法

    - %s（如果在一个字符串中出现了%s那么这就是一个字符串占位符，在字符串结束引号后加上%(实际字符串，实际字符串......）,那么运行时就会使用实际字符串代替占位符）。同理可以引申出使用%d代替整数，%f代替浮点数。

    - python3的写法

    - ```python
      s="莲花"
      msg=f'''
      花间一壶酒，
      我爱你正如爱{s}
      '''
      ```

      
      
    - 

- 列表list

  - 创建：变量名=[值，值.....]
  - 增：变量名.append(值)；变量.insert(索引下标，值)
  - 删：del 变量名 ；del 变量名[索引] ；变量名.remove(值)（注意没有索引）
  - 改：变量名[索引]=值
  - 查：
    - list[1:2]（访问从索引1开始到索引2之前的元素，也就是不包括索引2，返回一个子数组）；
    - 变量名.index(值)-返回下标；
    - 变量名[索引]；
    - list[-1]（访问最后一个元素，可递推）；
    - list[2:]（返回从倒数第二个元素开始到最后一个元素-包括最后一个元素的子数组）
    - list[起始索引：结束索引：增量]（就是从起始索引开始遍历后下一次访问的索引值=当前索引+增量）（就是i+=增量）
  - 列表嵌套：list[[[],[],[]],[],[]]也就是列表中可以放任意类型

- 布尔bool：处理逻辑判断

  - 只有两个值True、False
  - 作用主要就是程序的逻辑判断
  - if  condition ：statement
  - else: statement

- 集合set:处理2组数据间的关系

- 字典dict:存储更多信息，加快查询速度

- 字节bytes：二进制数据，处理图片、视频、数据流等

## 运算符

- 算数运算：加、减、乘、除（python中除法都是返回浮点数）、取余 、**（返回x的y次幂）、//(返回商的整数部分)

- 移位运算：<<、>>

- 比较运算：>、>=、<、 <=、==、！=、<>

- 赋值运算：=、-=、+=、<<=、>>=、//=、**=

- 逻辑运算：and（&&）、or（||）、not（!）（注意python的与与或都是短路的，一旦能够确定最终答案直接退出，不会执行后面的代码）

- 成员运算：in、not in（判断某个序列中是否有某个元素，具体点应该iterable型-可迭代的中）（返回类型是bool）

  - iterable型有：
    - 字符串、列表、字典、元组、集合（没有数字类型）

  - print("晓晓" in list)

- 身份运算：type(x)-返回值类型是type；print()返回值类型是空None

- ()先算括号

## 读取用户指令

-  通过input()读取用户的输入信息

- 无论输入什么，input返回的类型始终是str

- 一题说明

  - ```python
    print(type(input()))
    name=input("name:")
    sex=input("sex:")
    hobbit=input("hobbit:")
    msg=f'''
    ------{name}MESSAGE-----------
    name:{name}
    sex:{sex}
    hobbit:{hobbit}
    ------------------------------
    '''
    print(msg)
    
    ```
    
  - 变量名.isdigit()，判断变量名是否属于数字类型
  
  - input().strip()，去掉字符串的两边的空格

## 流程控制

- python中通过：以及空格来指定是否在同一代码块中

- ```python
  #单体
  if 2:
  	print("xi")
  #双分支
  if 2<3:
  	print("2")
  else:
  	print("3")
  #多分支
  if 2>3:
  	print("2")
  elif 3>4:
  	print(3)
  else:
  	print(4)
  #嵌套分支(最好不超过4层)
  if 2<3:
  	if 3>4:
  		print("农化")
  	else:
  		print("绿色")
  ```

  

- pass 是一个空语句，为了保持程序结构的完整性。一般情况下，pass 不做任何事情，被用作占位符。

  它的作用如下：

  1. 空语句 do nothing

  2. 保证格式完整

  3. 保证语义完整

  pass语法格式：pass

  如果写了一个循环或者函数，尚未实现(暂未想好如何实现或者交付给其他人)，但是会在将来的某个时候实现。这时，如果循环体或者函数体为空，解释器就会报错。此时，可以使用 pass 语句构造一个不做任何事情的主体。

  pass 和注释之间的区别在于：解释器会完全忽略注释，但不会忽略 pass。然而，执行 pass 时什么都不会发生，导致无操作(NOP)



## 各种循环

- pycharm环境安装
  - [pycharm2019.3.1永久激活教程 pycharm2020年激活码（实时更新） - osc_i5oyb1xr的个人空间 - OSCHINA - 中文开源技术交流社区](https://my.oschina.net/u/4323755/blog/4449174)
  
- for  循环

- range( )是python中产生一个数的集合工具，基本结构为range(start,stop,step)，即产生从start数开始，以step为步长，至stop数结束的数字集合，不包含stop数，start可以省略，默认为0，step也可，默认值为1

- 

  ```python
  # 打印0-10不含10
  for i in range(10):
  	print(i)
  # 遍历列表，相当于fore
  for name in ["阿凤","晓晓"]:
  	print(name)
  # 遍历字符串
  for i in "abcdefg":
  	print(i)
  #综合体
  for x in range(1,10,2):
  	print(x)
  ```

  

- [几种素数判定方法_Geek-Tuzki-CSDN博客_素数判定定理](https://blog.csdn.net/yyyds/article/details/51729858)

## 函数库

- [python数字类型之math库使用 - 江武555 - 博客园 (cnblogs.com)](https://www.cnblogs.com/jackyfive/p/11603905.html)
