#Typescript

- 官网：[JSX · TypeScript中文网 · TypeScript——JavaScript的超集 (tslang.cn)](https://www.tslang.cn/docs/handbook/jsx.html)

- 简介：TS能够编译成任意版本的js

  - js存在的问题：
    - 动态弱类型，变量没有类型概念
    - 函数，参数、类型没有限制
  - 导致维护困难
- TS的目的就是要解决JS存在的问题
- TS以JS为基础构建的语言，是JS的超集，也就是完全支持JS且进行功能扩展。可以在任何支持JavaScript的平台中执行。TS不能被JS解析器直接执行，需要将TS编译转换为JS（所以需要一个TS编译器）。

  - 其实从这点我们就能看出TS应该就是添加一些类型检查限定，直接避免你可能的错误，让你在正确规范内使用JS，从而避免JS缺点。
- 最重要的一点在JS中引入了类型的概念，将js动态转换为静态。
- TS增加了什么：
  - 类型
  - 添加ES不具备的新特性
  - 支持ES的新特性
  - 丰富的配置选项
  - 强大的开发工具
- 安装TS
  - 先安装Node.js
  - 再使用npm i -g typescript
- 使用
  - 新建一个.ts文件
  - 使用tsc对ts文件进行编译
    - 进入命令行
    - 进入ts文件所在目录
    - 执行命令：tsc xx.ts

## 基本类型

### 类型声明

- 类型声明式TS非常重要的一个特点

- 通过类型声明可以指定TS中变量（参数、形参）的类型

- 指定类型后，当为变量赋值时，TS编译器会自动检查是否符合类型声明，符合则赋值，否则报错。

- 简而言之，类型声明给变量设置了类型，使得变量只能存储某类型的值。

- 语法：

  ```js
  let 变量：类型;
  let 变量:类型=值;
  function fn(参数:类型,参数:类型):类型{
      
  }
  ```

  

- 自动类型判断

  - TS拥有自动判断机制
  - 当对变量的声明和赋值是同时进行的，TS编译器会自动判断变量
  - 所以如果你的变量的声明和赋值是同时进行的，可以省略类型声明。（let a=10;）

- 类型

  - number

    - 任意数字

  - string

    - 任意字符串

  - boolean

    - 布尔值

  - 字面量（联合类型）

    - 限制变量的值就是该字面量的值，就是用一个值来声明类型。但是一旦声明之后，这个变量的值就只能是这个字面量.

    - 

      ```js
      let b:'mal'|'flag';//可以使用|进行多个值进行连接。
      let c:boolean|string;//同样这个可以声明多个类型，那么这个变量就能存储多个类型的值。
      c=true;
      c='sss';
      ```
  
      
  
    - 
  
  - any
  
    - 任意类型，默认类型（就是声明变量不指定类型时，默认就是这个）
    - 如果一个变量设置类型为any,相当于TS对其关闭了类型检测。
    - any可以接收任意类型变量，也能赋给任意类型变量。
    - 如果将any变量赋给其他类型的变量，会导致其他变量也变为any从而导致类型污染。

  - unknown

    - 类型安全的any。

    - 表示未知类型的值

    - 与any的区别是，unknown型变量不能赋给其他的变量

    - 如果一定要可以使用断言

      ```js
      let a:unknown;
      a='heoo';
      //类型断言
      s=a as string;//可以用来告诉解析器变量的实际类型。
      /*语法
        变量 as 类型
        <类型> 变量
        s=<string>a;
      */
      ```
  
      
  
  - void
  
    - 没有值或undefined
  
  - never
  
    - 没有值，不能是任何值
    - 表示永远不会返回结果
    - 与void的区别：
      -  void表示一定会执行return语句，只是返回的东西是null.
      - never表示永远不会执行return语句，返回是无，就是连空都没有了。
  
  - object
  
    - 任意JS对象
  
    - ```js
      let a:{name:string,price?:number};
      //用于指定对象中可以包含的属性.
      //问号表示属性可选
      //必须保证a指向的对象的结构与其限定完全相同
      let c:{name:string,[pro:string]:any};//表示这个对象一定有name属性，其他的属性可有可无，有的话，这个属性名一定是string，它的值类型是any.
      ```
  
    - 函数对象
  
      ```js
      //表示这个变量是一个函数对象，这个函数必须是两个参数，且参数类型、返回值类型都是number。
      let a:(v1:number,v2:number)=>number;
      ```
  
    - 数组对象
  
      ```js
      let a:string[];
      let b:number[];
      let c:Array<number>;
      ```
  
      
  
  - array
  
    - 任意JS数组
  
  - tuple
  
    - 元素、TS新增类型、固定长度数组
  
    - ```js
      let a:[string,string];
      ```
  
      
  
    - 
  
  - enum
  
    - 枚举、TS中新增类型
    
    - ```js
      enum G{
          Male=0;
          Female=1;
      }
      ```
    
      
    
  - &声明类型的用法
  
    - ```js
      //这个对象必须有两个属性，name值必须是string型且age必须是number。
      let j:{name:string}&{age:number}
      ```
  
      
  
  - 类型的别名(类似c中的结构体，但是更简洁)
  
    - ```js
      type mytype=1|2|3|4;
      let k=mytype;
      let j=mytype;
      let l=mytype;
      
      ```
  
      

##TS 编译选项

### TS配置文件

- tsconfig.json

  - 这个文件就是用来记录要应用TS命令的文件对象，也就是说ts命令的作用域，比如我们要执行tsc编译命令，那么就会先读取这个文件中的对象，从而对对象执行tsc命令。

  - 这个json文件中能够写注释

  - 这个文件是ts编译器的配置文件，ts编译器可以根据它的信息来对代码进行编译

  - 

    ```json
    {
     //这个属性用来指定需要被编译的ts文件，值是一个数组。如果不写默认项目中所有ts文件
     "include":[
         "./src/**/*",//**表示任意目录；*表示任意文件
         ""
     ],
      //不需要被编译的文件目录。默认值：["node.modules","bower_compoents","jspm_packages"]
      "exclude":[
        "./src/hellow/**/*"
        ],
        
      //extends:用于定义被继承的配置文件,相当于将下面指明路径下的json文件中的属性全部引入到当前文件中
      "extends":"./configs/base",
        
      //files指定别编译文件的列表，只有需要编译的文件少时才会用到.
        "files":[
            "core.ts",
            "type.ts"
        ]
    }
    ```

    

  - compilerOptions

    - 编译选项时配置文件中非常重要也比较复杂的配置选项

    - 在compilerOptions中包含多个子选项，用来完成对编译的配置

      - 项目选项

        - target

          - 设置ts代码编译的目前版本

          - 可选值

            - ES3(默认)、ES5、ES6/ES2015、ES7/ES2016、ES2017、ES2018、ES2019、ES2020、ESNext

            - 

              ```json
              "compilerOptions":{
                  "target":"ES6"
              }
              ```

              

        - lib

          - 指定代码运行时所包含的库（宿主环境）

          - 可选值

            - ES5、ES6/ES2015、ES7/ES2016、ES2017、ES2018、ES2019、ES2020、ESNext、DOM、WebWorker、ScriptHost......

            - 

              ```json
              "lib":["ES6","DOM"]
              ```

              

            - 

        - module

          - 指定要使用的模块化的规范
          - 可选值
            - none、commonjs、amd、system、umd、es6、es2015、es2020、esnext

        - outDir

          - 用来指定编译后js文件的输出所在的目录

          - 

            ```json
            "outDir":"./js"
            ```

            

        - outFile

          - 将所有ts文件中的全局作用域中的代码合并为一个js文件，这个文件名你指定。

          - 

            ```json
            "outFile":"./a/header.js"
            ```

            

        - allowJs

          - 是否对js文件进行编译，默认是false

          - 

            ```json
            "allowJs":true;
            ```

            

        - checkJs

          - 是否检查js代码是否符合语法规范，默认是false

        - removeComments

          - 编译后的文件中是否移除注释

        - noEmit

          - 是否不生成编译后的文件，默认false

        - noEmitOnError

          - 当有错误时不生成编译文件。默认false

        - alwaysStrict：

          - 用来设置编译后的文件是否使用严格模式，默认false

        - noImplicitAny

          - 不允许隐式的any类型；默认false 

        - noImplicitThis

          - 不允许不明确类型的this

        - strictNullChecks

          - 严格检查空值

        - strict

          - 所有严格检查的总开关，它为true，所有严格检查的子集都为true。

### 打包工具

- ts一般结合打包工具进行使用

#### webpack打包

- [尚硅谷TypeScript教程（李立超老师TS新课）_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Xy4y1v7S2?p=10&spm_id_from=pageDriver)

## 其他

### 类

- 类的用法与Java基本一致，比ES6的使用限制要少。

- 属性方法修饰符：

  - private
  - protected：只能在当前类以及该类的子类中进行访问
  - public(默认)

- 属性定义可以直接在构造函数的参数进行定义，不用单独拿出来定义

- ```ts
  class Person{
      readonly name:string="ss";//只读属性
  	age:number=12;
  	static l="sdfs";
  	constructor(name,age){
          this.name=name;
          this.age=age;
      }
  	add(a:number,b:number):void{
          
      }
  }
  //继承extends
  //super能够访问属性以及方法
  //抽象类 abstract
  abstract class A{
      ss:string;
  constructor(ss:string){
      this.ss=ss;
  }
  abstract play():void;
  }
  ```
  

### 接口

- 

  ```ts
  interface i{
      name:string;
      age:number;
      play():void;
  }
  //接口值用于限制结构
  class M implements i{
      name:string;
      age:number;
      constructor(name:string,age:number){
          ........
          ........
      }
      play(){
          console.log("ddd");
      }
  }
  ```

### 泛型

```ts
function f<T>(a:T):T{
    return "ddd";
}

let r1=f(10);//不指定泛型，ts自动进行推断
let r2=f<string>("ddd");//指定泛型

class D{
    
}
//要求T这个泛型必须是D的子类，同理还有super
function s<T extends D>():void{};
```

### JSX

- JavaScriptXml,允许在js中写html，这样js操作html就更加便捷
- 详情见文档：[JSX · TypeScript中文网 · TypeScript——JavaScript的超集 (tslang.cn)](https://www.tslang.cn/docs/handbook/jsx.html)

### 装饰器

- 与Java注解一样
- 详情见文档：[装饰器 · TypeScript中文网 · TypeScript——JavaScript的超集 (tslang.cn)](https://www.tslang.cn/docs/handbook/decorators.html)



