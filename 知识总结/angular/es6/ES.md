#ES

- [4.3 ES6 Class 类 | 菜鸟教程 (runoob.com)](https://www.runoob.com/w3cnote/es6-class.html)
- [js 的单线程与异步 - 永不言退 - 博客园 (cnblogs.com)](https://www.cnblogs.com/netUserAdd/p/10930067.html)
- [java返回map数据_ES6解析后端返回的Map数据 - 百度文库 (baidu.com)](https://wenku.baidu.com/view/4dc9e3d2f51fb7360b4c2e3f5727a5e9856a2767.html?_wkts_=1670030024393&bdQuery=前端如何接收后端java返回的Map类型)
- ECMAScript

  - 是一种ECMA组织制定和发布的脚本语言规范,JS是其的一种实现。一般两者表达的是一个意思
  - JS核心：
  
    - 核心：ECMAScript
    - 扩展：BOM、DOM（B端）
    - 扩展：Node（服务端）
  - ES版本
    - ES5，2009，目前写js遵循的规范就是这个
    - ES6，2015
    - ES7，2016

## ES5

- 除了正常运行模式-混杂模式，ES5添加了第二种运行模式：严格模式，js运行要在严格的条件下：

- 目的消除js语法一些不合理不严谨之处，减少怪异行为，消除不安全处。

- 使用方式:

  - 在全局或函数的第一条语句定义为：‘use  strict’;

- 语法和行为改变：

  - 必须使用var声明变量
  - 禁止自定义的函数中的this指向window
  - 创建eval作用域，就是将eval函数中使用的变量新建为局部变量，与其外层隔开。
  - 对象不能有重名的属性

- ES5给Object扩展了一些静态方法，常用的2个，像继承：

  - Object.create(prototype,[descriptors])

    - 作用：以制定对象为原型创建新的对象

    - 为新的对象指定新的属性，并对属性进行描述

    - value:指定值

    - writable：表示当前属性值是否可修改，默认false(final与否)

    - configurable:表示当前属性是否可以被删除，默认false

    - enumerable：表示当前属性是否能用for in 默认为false

    - ```js
      var obj={};
      var obj1=Object.create(obj,
                             sex:{
                             value:'';
                             writable:true;
                             configurable:true;
                             enumerable:true;
                             }
                            );
      ```
      
      

  - Object.definePropertise(object,descriptors)

    - 作用：为指定对象定义扩展多个属性

    - get:用来获取当前属性值的回调函数,当获取属性时自动调用。

    - set:**修改当前属性值的触发回调函数**，并且实参即为修改后的值

    - ```js
      var obj={};
      Object.definePropertise(
          obj,{
            name:{
                get: function(){
                    return 'sss';
                },
                set:function(change){
                    this.name=change;
                }
            }  
          }
          );
      ```
      
      

  - 对象本身存在set、get

    - get 属性名(){},用来得到当前属性值的回调函数

    - set 属性名(){},用来监视当前属性值变化的回调函数

    - ```js
      get name(){
          return "";
      }
      set name(change){
          this.val=change;
      }
      ```
      
      

- 数组扩展

  - Array.prototype.indexOf(value)：得到值在数组中的第一个下标
  - Array.prototype.lastIndexOf(value):得到值在数组中最后一个下标
  - Array.prototype.forEach(function(item，index){})：遍历数组
  - Array.prototype.map(function(item，index){}):遍历数组返回一个新的数组，返回加工后的值
  - Array.prototype.filter(function(item，index){}：遍历过滤一个新的子数组，返回条件为true的值。

- 函数扩展

  - function.prototype.bind(obj):
    - 将函数内的this绑定为obj，并将函数返回
    - bind()与call()、apply()区别：
      - 都能指定函数中的this
      - call()、apply()是立即调用函数
      - bind()是将函数返回，将返回值作为函数调用即可。通常bind是用于回调函数的this绑定

## ES6

### let

- 用于变量声明，可同时声明一个或者多个
- 特点：
  - 变量不能重复声明
  - 块级作用域：全局、函数、eval。在块中有效。
  - 不存在变量提升（使用var声明变量，即使在使用后声明，这个变量也会存在，使用let会报错，不允许在使用位置后声明,**如果变量是一个函数则仍然能够使用**）
  - 不影响作用域链



### const

- 声明常量
- 特点
  - 一定要赋给初始值
  - 一般常量使用大写
  - 常量的值不能修改
  - 块级作用域
  - 对于数组和对象的元素修改，不算做对常量的修改，不会报错。如果对数组或对像常量的引用修改会报错。

### 变量解构赋值

- 允许按照一定模式从数组和对象中提取值，对变量进行赋值。相当于我们能够将数组和对象按照一定结构进行分解

- 数组的解构赋值

  - 用[]进行分解

    ```js
    const F4=['小沈阳','刘能','赵四','宋小宝'];
    let [xiao,liu,zhao,song]=F4;
    //这样做的意思是声明了四个变量，并赋给初值分别是数组中的对应位置的值。
    ```

- 对象解构

  - 用{}进行分解

    ```js
    const zhao={
        name:'赵本山',
        xiaop:function(){
            console.log("sdddd");
        }
    };
    let {name,xiaopin}=zhao;
    ```



### 模板字符串

- ES6引入新的声明字符串的方式``、‘ ’、“ ”

- 使用``，内容中可以直接出现换行符，之前我们需要使用+拼接以及转义字符

- 变量拼接

  - ```js
    let lovest='尾箱';
    let out=` ${lovest} 是sbbbbb|||`;
    ```



### 对象的简化写法

- ES6允许在大括号里面，直接写入变量和函数，作为对象的属性和方法。

  - ```js
    let name="sss";
    let ch=function(){
        console.log("sss");
    }
    const school={
        name,
        ch,
        improve(){
            console.log("ssss");
        }
    }
    ```

    

### 箭头函数

- ＥＳ６允许使用【箭头】＝＞定义函数

- 声明一个函数

  - ```js
    let fn=function(){};
    let sl=(p1,p2.....)=>{};
    ```
  
  - **使用箭头声明的函数中this是静态的，this始终指向函数声明时所在作用域下的this的值。即使使用bind、call、apply进行指定调用也不会改变。**
  
  - 不能作为构造函数实例化对象，所以不能用来定义构造函数。
  
  - 不能使用arguments变量
  
  - 箭头函数简写：
  
    - 省略（），当形参有且只有一个时
    - 省略{}，当方法体只有一行时，如果要使用return，return也必须省略，此时这行代码的执行结果就是返回值。
  
  - 箭头函数适合于this无关的回调，定时器，数组的方法回调
  
  - 箭头函数不适合与this有滚啊的回调，事件回调，对象方法

### 函数参数初始值

- 允许函数参数赋值初始值

  - ```js
    function add(a,b,c=10){return a+b+c;}
    ```

  - 具有默认值的参数，一般位置要靠后，因为传参时是前面的先接受值，避免覆盖。

  - 参数默认值与解构赋值结合

    ```js
    function cn({a="sss",b="ca",c}){};
    cn({
        a:'',
        b:"",
        c:""
    });
    ```

### rest

- 引入rest参数，用于获取函数的实参，用来代替arguments

  - ```js
    function da(...args){};
    da('sss','sa','ss');
    ```

    rest参数必须要放到参数最后

  - 其就是一个参数值数组的引用

### 扩展运算符

- 【...】扩展运算符能将数组转换为逗号分割的参数序列

  - ```js
    const tf=['ss','sss','ssss'];
    function cc(){
        console.log(arguments);
    }
    cc(...tf);
    将一个数组分割，和rest相比，一个分割一个合并，不过都是用于参数传递时
    ```

  - 应用

    - 数组的合并

    - ```js
      const array1=['ss','aa','cc'];
      const array2=['ass','aaa'];
      const array3=[...array1,...arrray2];
      ```

    - 数组的克隆(只能进行浅拷贝)

    - ```js
      const array1=['ss','aa','cc'];
      const array2=[...array1];
      ```

      

### Symbol

- ES6引入了一种新的原始数据类型Symbol，表示独一无二的值（整个程序中绝不会出现第二个），是JS的第七种数据类型，是一种类似于字符串的数据类型。

- 特点

  - Symbol值是唯一的，用来解决命名冲突问题。即使两个Symbol，传入相同的字符串，两者都不同。

  - symbol值不能于其他数据进行运算

  - symbol定义的对象属性不能使用for...in循环遍历，但是可以使用Reflect.ownKeys来获取对象的所有键名

  - 

    ```js
    //创建
    let s=Symbol();
    //Symbol.for创建
    
    
    ```

    

  - USONB：jscript的六种数据类型

- 应用:

  - 给对象中添加属性方法

  - ```js
    let game={};
    比如这个game对象添加一个m属性，我们不确定它有没有,就可以使用Symbol()
    便于后续取值，可以定义一个对象
    let mt={
        m:Symbol()
    }
    game[mt.m]=function(){}
    
    
    添加方法
    let mm={
        [(Symbol("say"))]:function(){}
    }
    ```

    

- Symbol内置值，这些值是Symbol的属性，但是这个整体能够作为对象的属性进行访问，就是一个全局属性扩展。
  - 除了定义自己使用Symbol值外，ES还提供11个内置的Symbol值，指向语言内部使用的方法
  - Symbol.hasInstance
    - 当其他对象使用instanceof运算符，判断是否为该对象的实例时，会调用**这个方法**
  - Symbol.isConcatSpreadable
    - 该属性等于的时一个布尔值，表示该对象用于Array.prototype.concat时，是否可以展开
  - Symbolunscopables
    - 该对象指定了使用with关键字时，哪些属性会被with环境排除
  - Symbol.match
    - 当执行str.match(myObject)时，如果该属性存在，会带哦用它，返回该方法的返回值
  - Symbol.replace
    - 当该对象被str.replace（myObject）方法调用时，会返回该方法的返回值
  - Symbol.search
    - 当该对象被str.search（myObject）方法调用时，会返回该方法的返回值
  - Symbol.split
    - 当该对象被str.split（myObject）方法调用时，会返回该方法的返回值
  - Symbol.iterator
    - 对象进行for....of循环时，会带哦用Symbol.iterator方法，返回该对象的默认遍历器。
  - Symbol.toPrimitive
    - 该对象被转换为元素类型的值时，会调用这个方法，返回该对象对应的原始类型值
  - Symbol.toStringTag
    - 在该对象上面调用toString方法时，返回该方法的返回值
  - Symbol.species
    - 创建衍生对象时，会使用该属性

### 迭代器

- 迭代器是一种接口，为各种不同的数据解构提供统一访问的机制。任何数据结构只要部署Iterator接口（这个接口在js中指的就是对象中的一个属性），就可以完成遍历操作。
- ES6创造了一种新的遍历命令for...of循环，Iterator接口主要供for...of消费
- 原生具备iterator接口的数据（可用for of 遍历）
  - Array
  - Arguments
  - Set
  - Map
  - String
  - TypedArray
  - NodeList
- 工作原理
  - 创建一个指针对象，指向当前数据结构的起始位置
  - 第一次调用对象的next方法，指针自动指向数据结构的第一个成员
  - 接下来不断调用next方法，指针一致往后移动，直到指向最后一个成员
  - 没调用next方法返回一个包含value和done（标识是否完成遍历）属性的对象。

### 生成器

- ,生成器函数是ES6提供的一种异步编程解决方案，语法行为于传统函数完全不同，就是一个特殊的函数。

  - 

    ```js
    function * gen(){
        代码块1
        yield 'dfd';
        代码块2
        yield 'ddd';
        .....
    };
    let interator=gen();
    iterator.next();//执行一次gen函数中的中代码段，遇见yield或代码块结束时结束。结束时返回yield后的字符串或者表达式执行结果。
    iterator.next();//再调用一次，从上次结束处接着执行。
    ```

- 参数

  - gen()本身可以传递参数
  - next（）也可以传递参数，且这个参数将会作为上一次调用next()时，执行的yield语句的返回结果。

- 应用实例

  - 首先我们知道js是单线程，所以会异步阻塞
  - 常见的编程：异步编程、文件操作、网络操作、数据库操作

  ```js
  1s后控制台输出111
  2s后输出222
  3s后输出333
  //非异步的做法
  setTimeout(()=>{
      console.log(111);
      setTimeout(()=>{
          console.log(222);
          setTimeout(()=>{
          console.log(333);
          },3000);
      },2000);
  },1000);
  //不停的回调，回调函数
  使用生成器解决
  function one(){
      setTimeout(()=>{
          console.log(111);
          iterator.next();
      }
      ,1000);
  }
  
  function tow(){
      setTimeout(()=>{
          console.log(222);
          iterator.next();
      }
      ,1000);
  }
  
  function three(){
      setTimeout(()=>{
          console.log(333);
          iterator.next();
      }
      ,1000);
  }
  function * gen(){
      yield one();
      yield tow();
      yield three();
  }
  let iterator=gen();
   iterator.next();
  ```



### Promise

- 是ES6引入的异步编程的新解决方案，语法上Promise是一个构造函数，用来封装异步操作并获取其成功或失败的结果。

- Promise构造函数：Promise（excutor）{};

- Promise.prototype.then方法

  - 用于指定一个promise对象执行异步后的回调函数，可根据返回的状态进行处理
  - 返回值
    - 该方法的返回值是一个Promise对象，该对象的status是调用该方法对象的执行异步后的状态（resolve、reject）。而值也是resolve、reject中指定的值。
    - 你也可以直接在回调函数中自定义返回值，如果这个返回值类型，不是Promise，返回的Promise对象的状态是resolve，且值就是你自定义的返回值。
    - 你也可以自己创建一个Promise对象进行返回
    - 你可以throw ，抛出一个异常，而返回的Promise对象的状态是reject，值是抛出的值。
  - 因为该方法返回的是一个Promise对象，所以该方法可以进行链式调用（责任链模式懂吧）

- Promise，prototype.catch方法

  - 该方法用于指定异步执行失败的回调。意思就是reject即可以用then捕获也可以用catch进行捕获

  - ```js
    p.cath(function(reason){
        console.warn(reason);
    });
    ```

  - 

- 演示

  ```js
  const p=new Promise(
      function(resolve,reject){//这两个参数由浏览器指定，我们仅仅只是进行命名
          //如果这段异步代码执行状态为成功你可以使用resolve返回执行成功的状态，如果执行失败，你可以使用reject返回执行失败状态。两者都可以附带数据
          setTimeout(function(){
              if(true){
                  resolve("hhhh");
              }else{
                  reject("......");
              }
          },1000);
      });
  //创建好一个Promise对象后，通过该对象的then方法进行执行异步块
  	p.then(
          function(value){
              //当执行成功后，会调用这个回调函数，这个形参就是异步块成功后返回到resolve中的值
          }，
          function(reason){
          //当执行失败后，会调用这个回调函数，这个形参就是传入到reject中的值。
      	}
      );
  ```

  

- Promise封装ajax（asynchronous JavaScript and xml）请求：

- [AJAX – 向服务器发送 | 菜鸟教程 (runoob.com)](https://www.runoob.com/ajax/ajax-xmlhttprequest-send.html)

  - 原生发送Ajax请求

    - 

      ```js
      存有 XMLHttpRequest 的状态。从 0 到 4 发生变化。
      0: 请求未初始化
      1: 服务器连接已建立
      2: 请求已接收
      3: 请求处理中
      4: 请求已完成，且响应已就绪
      
      //创建一个xmlhttprequest对象
      let xhr=new XmlHttpRequest();
      //初始化
      xhr.open('GET','localhost://...');
      //发送
      xhr.send();
      //给对象绑定一个状态改变时的回调函数,也就是说每次这个对象的状态转变时都会调用这个函数
      xhr.onreadystatechange=function(){
          if(xhr.readyState==4){//如果当前状态是4，说明服务器已经进行响应了
             //如果服务端已经响应，我们就检测响应的响应码
              if(xhr.status==200){//如果是正常响应，我们就能够获取响应数据：response、responseText、responseXml
                 console.log(xhr.response); 
              }
             }
      }
      ```



### Set

- ES6提供了新的数据结构Set(集合)它类似数组，但成员值都是唯一的，集合实现了iterator接口，所以可以使用扩展运算符加二for....of....进行遍历

- 属性

  - size,返回集合的元素个数

  - add,增加一个新元素，返回当前集合

  - delete，删除元素，返回Boolean

  - has，检测集合是否包含某个元素，返回Boolean值

  - ```js
    let s=new Set();//该构造函数能够传入一个可迭代的对象，所谓可迭代就是实现了iterator接口
    let ss=new Set(['as','saa','sfd']);
    //使用一个可迭代的对象作为参数后，如果这个对象中有重复的值，那么会将重复值去除。
    let ll=[...ss];
    ```

    

### Map

- Es6提供了Map，类似于对象，也是键值对的集合。但是键的范围不限于字符串，各种类型的值都可以当作键。Map也实现了iterator接口，所以可以使用扩展运算符、for...of进行遍历。
- 属性方法
  - size；返回元素个数
  - set；增加一个新元素，返回当前Map
  - get；返回键名对象的键值
  - has;检测Map中是否包含某个元素，返回Boolean值
  - clear；清空集合，返回undefined

### class

- ES6提供了Class概念，作为对象的模板。通过class故拿剪子，可以定义类。基本上，ES6的class可以看作只是一个语法糖，它的绝大部分功能，Es5都能做到，新的class写法只是让对象原型写法更加清晰，更像卖你想对象的编程语法而已。

  - class声明类

  - constructor定义构造函数初始化

  - extends继承父类

  - super调用父级贡藕早方法

  - static定义静态方法和属性

  - 父类方法可以重写

  - ```js
    class Phone{
        constructor(a,b){
            this.a=a;
            this.b=b;
        }
        //注意这里定义方法不能使用ES5的语法
        paly(){
            
        }
    }
    let sss=new Phone('1',12);
    ```
    
    

- 静态成员

  - 我们知道，如果在Phone这个构造函数上添加属性方法是不能通过实例化对象获取到的，因为实例化对象与Phone构造函数对象两者是两块不同的内存。所以两者不直接相通。

  - 但是两者的有一块公共区是相通的就是两者的原型是一致的。所以如果将一些公用方法属性定义在构造函数的原型中，那么每个实例都能够访问这些资源。

  - 引入class后，我们就不用这个麻烦的定义与访问资源了，通过static关键字实现即可

  - ```js
    class Phone{
        static name='sss';
    	static change(){
            console.log('ff');
        }
    }
    ```

    

- 构造函数的继承

  - [js继承的6种方式 - ranyonsue - 博客园 (cnblogs.com)](https://www.cnblogs.com/ranyonsue/p/11201730.html)

  - [别再被JS继承问倒了_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1BK41177hb?p=2)

  - ES5的构造函数继承，利用call进行this的修改以及参数传递
  
    ```js
      function A(m,n){
          this.m=m;
          this.n=n;
      }
    
      function B(m,n,c){
          //使得继承类每个实例拥有单独的私有属性
          A.call(this,m,n);
          this.c=c;
      }
    //创建一个新的空对象作为b的原型，这个对象中存放a的公有属性和方法，所以需要将这个对象的原型指向a的原型，因为原型中放公共的方法和属性，为什么不直接将b的原型指向a的原型，因为如果要重写方法，不会影响a。
     B.prototype=Object.create(A.prototype);
    ```
  
    
  
  - ```js
    class A{
        constructor(a,b){
            this.a=a;
            this.b=b;
        }
        play(){
            alter('dd');
        }
    }
    
    class B extends A{
        construcor(a,b,c){
            super(a,b);
            this.c=c;
        }
        play(){
            alter("sb");//注意我们只能够进行对父类完全重写，且是不能够调用父类中的方法，super也不行。
        }
    }
    ```
  
    
  
- get和set方法

  - ```js
    class Phone{
        	constructor(price){
        		this.price=price;
        	}
    		get price(){
    			console.log('ss');
    			return this._price;
    		}
    
    		set price(value){
    			console.log('sb');
    			this._price=value;
    		}
    
    	}
    	let aa=new Phone();
    	aa.price='sfs';
    	console.log(aa.price);
    ```

    当进行该类的属性进行获取或者设置值时会触发这两个两个函数，调用构造函数时也会触发。

### 数值

- Number.EPSILON： 2.2204460492503130808472633361816E-16，或者 2-52。
  - 这个数表示的时js表示的最小精度。
  - 就是说如果两个数之间的差值小于这个数那么就判定为相等。
  - 用于浮点数的相等判断
- js可使用二进制八进制进行初始化变量
- Number.isFinite
  - 检测一个数值是否为有限数
- Number.isNaN
  - 检测一个数值是否为NaN
- Number.parseInt、parseFloat字符串转为整数。
- Number.isInteger
  - 判断一个数是否时整数
- Math.trunc
  - 将数字的小数部分抹掉
- Math.sign
  - 判断一个数是正数还是负数还是0

### 对象方法的扩展

- Object.is
  - 判断两个值是否完全相等，不同于===
  - Object.is(NaN,NaN)，返回值true
- Object.assign（）:将多个对象复制到一个目标对象中。如果存在相同的属性，后面的覆盖前面的。
  - 该方法的属性拷贝是浅拷贝。
  - null和undefined不能转换为对象。
- [3.2.3 ES6 对象 | 菜鸟教程 (runoob.com)](https://www.runoob.com/w3cnote/es6-object.html)

- Object.setPrototypeOf（对象，原型对象）设置原型对象
- Object.getPrototypeOf获取原型对象

### 模块化

- 将一个大程序文件拆分成许多小的文件，然后将小文件组合起来
  - 防止命名冲突
  - 代码复用
  - 高维护性

- [4.4 ES6 模块 | 菜鸟教程 (runoob.com)](https://www.runoob.com/w3cnote/es6-module.html)

- ES6之前的模块化规范产品
  - CommonJS-》Node JS、Browserify
  - AMD》requireJS
  - CMD》seaJS

- 模块化语法

  - 模块功能主要由两个命令构成：export和import

  - export：用于规定模块的对外接口

  - import：用于输入其他模块提供的功能

  - 

    ```js
    //m1.js
    <script type="text/javascript">
       export let s="dsfs"; 
       export function a(){
           console.log();
       }
    //属性单一暴露
    </script>
    
    //批量暴露
    //m2.js
    <script type="text/javascript">
       let s="dsfs"; 
       function a(){
           console.log();
       }
       export{s,a};
    </script>
    
    //默认暴露,如果导入者需要调用这个默认暴露的对象，需要使用default作为对象名然后调用其方法。
    //m3.js
    <script type="text/javascript">
    export default{//暴露的默认是一个Object，下面是它的属性以及方法定义
    	a:'ss';
    }
    </script>
    ```

    

  - 导入

  - ```js
    //通用方式
    import * as m1 from "./src/m1.js";
    
    //解构赋值形式
    import {s,a} from "./src/m1.js";
    import {s as l,a} from "./src/m1.js";
    import {default as m3} from "./src/m3.js";//这里导入的是一个对象。
    
    //简便形式 只能针对默认暴露使用
    import m3 from "./src/m3.js";
    
    
    ```

    

- 模块化管理方式，一般是在单独js文件中完成模块之间的关系引入，而这个但密度的文件最后作为网页的入口文件引入到网页中即可。（但是引入入口文件时，需要将type属性设置为module）



## ES7

### 数组扩展

- Array.prototype.includes:检测数组中是否有某个元素，返回Boolean值
- x的y次方：x**y----Math.pow(2,10);



## ES8新特性

- async和await两种语法结合可以让异步代码像同步代码一样
- [5.3 ES6 async 函数 | 菜鸟教程 (runoob.com)](https://www.runoob.com/w3cnote/es6-async.html)

### async

- async函数的返回值为promise对象

- promise对象的结果由async函数执行的返回值决定。

  - 同样，如果直接返回一个非promise的值其返回的promise对象就是resolve的，如果是throw一个值，其就是reject的。如果是自定义的promise对象，就取决于对应返回的执行结果。

  - ```js
    async function fn(){};
    ```

  - 

### await表达式

- await必须写在async函数中

- await右侧的表达式一般为promise对象（如果是这个对象，它会执行这个对象的异步代码，并会等待这个对象的异步代码块执行完成才返回）；还可以是非promise对象，如字符串、数值、函数，则直接返回。

- await返回的是promise成功的值

- await的promise失败了，就会抛出异常，需要通过try...catch捕获处理 

- **说白了就是等待其后面的表达式执行完成后，这个函数才会继续向下继续执行，就是保证同步，也就是Java中的原子性**

- 

  ```js
  const p=new Promise(
      function(resolve,reject){
      resolve("dd");
  });
  async function s(){
  	try{
          let result=await p;
      }catch(e){
          
      }
  }
  ```

  

- 发送Ajax请求

  - ```js
    	//发送Ajax请求
    	function setA(url){
    		return new Promise(
    			(resolve,reject)=>{
    			 let xhr=new XMLHttpRequest();
    			 xhr.open("GET",url);
    			 xhr.send();
    			 xhr.onreadystatechange=function(){
    			 	if(xhr.readyState===4){
    			 		if(xhr.status==200){
    			 			resolve(xhr.response);
    			 		}
    			 		reject("失败");
    			 	}
    			 	reject("失败");
    			 }
    			}
    			);
    	}
    	async function main(){
    		try{
    		let result=await setA("https://api.apiopen.top/getJoke");
    		console.log(result);
    	}catch(e){
    		console.log(e);
    	}
    	}
    ```

    

### 对象方法扩展

- Object.keys（对象）
- Object.values(对象)
- Object.entries(对象):返回一个二维数组
- Object.getOwnPropertyDescriptors(对象)
  - 获取对象的完整描述，这个对象的每个属性都是一个一个对象具有特定的属性。

## ES9

### rest参数

- ```js
  function dd({a,b,...c}){
      console.log(a);
      console.log(b);
      console.log(c);
  }
  dd({
      a:'sss'，
      b:'ssss'，
      c:'vddvdv'，
      d:'sasa'
  });
  ```

- 通过...还能够将一个对象的所有属性进行分割，比如

  - ```js
    const s={
        a:'d'，
        b:'c'
    }
    
    const aa={
        m:'ss'，
        b:'vd'
    }
    const aa={...s,...aa};//将两者的属性合并为一个新的对象
    ```

    

### 正则扩展

- 提取url和标签文本
- 正向断言
- 反向断言
- dotAll模式
  - dot:元字符，除换行符意外的任意单个字符
  - 就是新添了一个匹配模式：s。添加了这个模式后，.就能匹配除换行任意的字符。

## ES10

### 对象扩展

- Object.fromEntries

  - 这个方法用于创建一个对象

  - 传入的参数：说白了就是键值对数组

    - 要么是一个二维数组

    - 要么是一个Map

    - ```js
      //二维数组
      	const a=Object.fromEntries(
      		[['name','zhi'],['price','sss,asa,df,g']]
      		);
      //Map
      const map=new Map();
      m.set('name','saf');
      const b=Object。fromEntries(map);
      ```

      

### 字符串方法扩展

- trimStart
  - 用以清除字符串左侧空白
- trimEnd
  - 用于清除字符串右侧空白

### 数组方法扩展

- 数组名.flat:将多维数组转换为低维数组
  - 参数：一个数值，该数组最高维度与目标维度之间的差值
- flatMap
  - 我们知道：
    - 数组.map(回调函数)，那么每个数组中的元素都会执行一次这个回调函数，最后将结果组成一个新的数组返回。
  - 如果map中的回调函数返回的是一个数组，那么返回的就是一个多维数组，如果你需要返回降维的数组就可以使用flatMap。



### Symbol扩展

- Symbol.prototype.description

  - 这个属性能够获取到Symbol对象创建时传入的字符串

  - ```js
    let s=Symbol("ddd");
    s.description;
    ```

    



## ES11

### 私有属性

- ```js
  class P{
      constructor(name,age){
          this.name=name;
          this.#age=age;
      }
      name;//公有属性
      #age;//私有属性
  }
  ```



### Promise扩展

- allsettled(promise对象数组)

  - 返回值：一个promise对象，这个对象的状态始终是resolve。而其值是一个数组：这个数组中的每个值对应着是promise数组中的每个对象的执行结果（状态和值，但是不是promise对象，仅仅是一个对象）

- all(promise对象数组)

  - 返回值：一个promise对象，这个对象的状态取决于每个promise对象执行的状态，如果都成功才会成功，一个失败就是失败。成功的时候返回的是一个结果数组，而失败的时候则返回最先被reject失败状态的值（也就是一个失败立即中断，但是前面执行了的不会回滚）。

  - 注：返回值的顺序和传入参数的顺序一致，即使服务器响应的快慢不同。

- Promise.race的使用

  - 顾名思义，Promse.race就是赛跑的意思，意思就是说，Promise.race([p1, p2, p3])里面哪个结果获得的快，就返回那个结果，不管结果本身是成功状态还是失败状态。

### 字符串扩展

- String.prototype.matchAll()
  - 用来得到正则批量匹配的结果
  - 也就是能够用来多次匹配一个字符串，将匹配结果返回为一个数组。

### 可选链操作符

- [尚硅谷Web前端ES6教程，涵盖ES6-ES11_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1uK411H7on?p=65)

- 语法：?.

- 说白了就是一个类似？:的运算符

  - ```js
    const s=对象1?.db?.nn
    //如果对象1存在就去读取它的db属性，否则就返回undefined，后续同理
    ```

### 动态import

- 静态导入不能实现按需加载，浪费资源。

- 动态导入就是在使用时通过import函数进行调用

  - ```js
    function a(){
        import('./n.js').then(modul=>{modul.l()});
    }
    //其返回值时一个promise对象，而这个对象成功的值就是导入文件的中暴露的对象，文件中暴露的属性和方法就是其属性和方法。
    ```

    

### BigInt

- 大整型
- 可以将一个整数直接转换为BigInt(整数)，对于运算结果可能越界的数我们可以将运算数转换为BigInt后再进行运算。
- 注：大整数不能只能和大整数进行运算。可以使用运算符。

### globalThis

- 绝对全局对象
- 意思就是这个对象始终指向全局对象，相当于就是你访问文件路径很深时，能够一键回到原点。直接操作操作全局。
