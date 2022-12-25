# Go语言入门

##Go简介

- https://golang.google.cn
- c与c++可以做的golang都能做
- golang核心编程方向-就是使用的深入方向：
  - 区块链研发工程师：区块链就是一种分布式技术，核心思想是去中心化，让人人都可以参与数据库的记录，也就是说没有幕后操盘手，人人都是操盘手。
  - Go服务端/游戏软件工程师：做服务端的优势-数据处理，处理大并发比较厉害
  - Go分布式/云计算软件工程师
- golang应用领域：
  - **区块链技术**，简称BT，也被称为分布式账本技术，是一种护粮网数据库技术，其特点是去中心化，公开透明，让每个人均可参与数据库记录
    - [翻译翻译，什么叫区块链？_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV113411w7ic/?spm_id_from=333.337.search-card.all.click&vd_source=a832c31d95dfdfa3f6655d458c71158e)
  - **美团后台流量支撑**，支撑主站后台流量（排序，推荐，搜索-**高效率的运算**），提供**负载均衡，cache，容错，按条件分流，统计运行指标（处理大并发）**（qps,latency）等功能
  - **仙侠道**，应用范围，游戏服务端（通讯、逻辑、数据存储）
  - **盛大云CDN（内容分发网络）**，应用范围调度系统，分发系统、监控系统、短域名服务，CDN内部开放平台，运营报表系统以及其他一些小工具
  - **京东消息推送云服务/京东分布式文件系统**
- golang发展史

  - 2007-golang原型诞生
  - 2009.11.10，Google将golang以开放源代码的方式向全球发布
  - 2015.8.19，golang1.5
  - 17.2.17，1.8
  - 17.8.24，1.9
  - 18.2.16，1.10
  - 核心开发团队：ken tompson(Unix、C、B语言主要发明人) 、rob pike（贝尔实验室Unix团队） 、robert  griesemer（Javade hotspot）
  - Google为什么创建GO语言
    - 计算机硬件技术更新频繁，性能提高很快。目前主流的编程语言法阵明显落后于硬件，**不能合理利用多核多CPU的又是替身软件系统性能（也就是说GO合理调用CPU计算能力是有一套自己的优势）**
    - 软件系统复杂度越来越高，维护成本越来越高，目前缺乏一个足够**简洁高效**的编程语言。（现有的语言：风格不统一，计算能力不够，处理高并发不行）
    - **企业运行维护很多c/c++的项目，虽然c程序员运行速度很快，但是编译速度确很慢，同时还存在内存泄漏的一系列的困扰需要解决**
- golang的特性
  - [强、弱类型语言以及动态、静态语言_Sun990o的博客-CSDN博客](https://blog.csdn.net/sun9979/article/details/86609447)
  - golang保证了既能达到静态语言的安全和性能，又能达到了动态语言开发维护的高效率，GO=C+Python,说明GO语言既有C静态语言的运行速度又能达到Python动态语言的快速开发
  - go从C语言中集成了很多理念，包括表达式语法，控制结构，基础数据类型，调用参数传值，指针等等。也保留了和C语言一样的编译执行方式及弱化指针。
  - 引入包的概念，用于组织程序结构，go语言的一个文件都要归属于一个包，而不能单独存在。
  - 垃圾回收机制，内存自动回收，不需开发人员管理
  - **天然并发**
    - 从语言层面支持并发，实现简单
    - ***goroutine.轻量级线程，可实现大并发处理，高效利用多核***
    - 基于CSP（Communicating Sequential Processes）并发模型---[Concurrency in GO CSP并发模型 - 简书 (jianshu.com)](https://www.jianshu.com/p/447877e31c1b)
  - 吸收了管道通信机制，形成golang特有的管道channel
  - **golang的函数可以返回多个值**
  - 新的创新，比如**切片slice、延时执行defer**等

## Go学习网站

- [Golang技术栈，Golang文章、教程、视频分享！ (golang-tech-stack.com)](https://golang-tech-stack.com/)
- golang.org(golang官网)

## Go开发环境搭建

- 安装配置Go的SDK（软件开发工具包）
  - golangSDK下载地址：https://golang.google.cn或者https://www.golangtc.com/download
  - 目录结构
    - api：golang的接口文档
    - bin：go可执行命令exe文件(go-go语言的编译器，将go源码编译为可执行程序、gofmt-对我们代码格式化的程序)
    - blog:
    - doc：
    - lib:
    - misc:
    - pkg:
    - src:go源码
    - test:
  - 配置sdk环境变量
    - 添加GOROOT：SDK安装路径
    - Path:添加SDK的/bin目录
    - GOPATH：工作目录

##GO开发入门知识

### 程序目录结构

- go代码编写到扩展名为.go的文件中。
- 每个.go文件必须放在一个指定的包中并在文件中进行声明（如package main 指定文件在main包中）
- import “fmt”:表示引入一个叫fmt的包，引入包后就可以使用该包下的.go文件以及文件下的函数（go是没有类的概念，c++是在c上扩充了类的概念）

### 基本语法

- package声明.go文件所在包
- import引入一个包
- func用于声明一个函数
- main()是主函数，作为程序的入口，运行时最先执行这个函数。

- fmt.Println("hello")调用fmt下的Println函数输出hello
- 当程序开发完成之后，通过 **go build**命令对该go文件进行编译，生成.exe文件在dos命令下执行就可以看到运行效果
- **通过go run 命令可以直接运行hello.go程序，类似执行一个脚本文件**

- 
