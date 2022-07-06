#  Angular

- https://angular.cn

- angular中的指令、管道、组件、模块、服务都是类。还有function、value。

- 设计原则：

  - 不写不需要的代码
  - 不写重复的代码
  - 开闭原则
  - 高内聚，低耦合
  - 迪米特法则：一个对象、组件只负责必须的少量功能
  
- 23+1（MVC》MVP》MVVM）种设计模式

  －[软件设计模式_百度百科 (baidu.com)](https://baike.baidu.com/item/软件设计模式/2117635?fromtitle=设计模式&fromid=1212549)

- 09年Google开发的MVVM的中大型框架

  - angularJs是最初的版本
  - angular代表2.0到8.0，它不兼容angularJs
  - web worker做性能优化
  - 2.0以后的开发方式只有：脚手架方式
  
- 可以应用的平台

  - web
  - 移动web
  - 移动应用
  - 原生应用
  - 桌面原生应用
  
- 组成：

  - 数据绑定
  - 指令系统
  - 组件式编程
  - 路由和导航
  - 状态保持
  - 第三方组件库
  
- 环境搭建

  - 下载脚手架工具

    - 所有node相关软件都是在registry.npmjs.com

    - 使用第三方NPM下载仓库

      - 查看当前npm默认的下载仓库地址：

        - npm config get registry

      - 修改npm默认的下载仓库地址为国内镜像网站

        - npm config set registry

        - 例如：npm config set registry https://registry.npm.taobao.org

    - npm i -g @angular/cli

  - 运行脚手架创建空白项目

    - ng new myngapp01

  - 进入空白项目并运行开发服务器

    - cd myngapp01
    - npm start

  - 客户端访问测试

    - http://127.0.0.1:端口号
  
- 小知识
  - model：模型
  - module：模块
  - modal：模态对话框
  
- 注意一个根本点不要忘了，就是js就是操作网页行为以及与后端进行数据交互的。不要脱离这点，否则会觉得很抽象。

- angular中的路径访问特点，如果使用相对路径，当前目录（./）它会存在两个值，一个是当前组件所在的模块，一个是当前的组件。

## node.js一些工具

- npm：第三方模块维护工具
- npx:第三方可执行文件的执行工具（node package executor）,可用于执行当前项目中node_moudles/.bin目录下的可执行文件

## angular项目启动过程

- angular.json:ng项目的配置

  - index:./src/index.html
    - <app-root>
  - main:../src/main.ts

- main.ts>bootstrapModule(AppModule)---就是先加载主模块

- app.module.ts>bootstrap:[AppComponent]

- app.component.ts>selector:'app-root'

  ​								 >templateUrl:'app.component.html'

- app,componenthtml>html判断

## angular核心概念

- 一个模块具有自己的样式和行为，它的样式和行为会给这个模块中的组件进行应用。所以组件间公有的行为样式可以提升到模块中进行定义。

### 模块

- module，不同于node.js或ES6中的模块，**ng中的模块是一个抽象的容器，用于对组件、指令、管道、服务等对象进行分组**。（是不是很像spring容器）
- 整个应用初始时只有一个主组件(模块)：AppModule(就像最大的那个spring工厂，但是我们还能够使用多个spring配置文件进行不同bean进行管理，而这些spring文件就是一个个的模块，最后都会加载在spring这个大模块中)

### 组件

- 就像spring的中各种的bean，它们本质上都是类。区别就是它们自己封装的东西。

- 组件时一段可以反复使用的页面片段如页头、轮播、手风琴....

- angular认为组件是用来与用户交互的，与用户直接操作无关的东西不要放在组件中，可以用作工具类。

- 组件（compoent）=模板(template)+脚本(script)+样式（style）

- NG中，任何一个组件都必须声明在一个模块中！（自定义组件必须要进行在模块中进注册，就像bean的注册一样）

- 自定义组件步骤

  - （注：组件只能够放在ng项目的src/app目录下）

  - 定义一个类，进行导出（export）

  - 进行装饰器（---用于指定class的用途，这是ts的概念）的标识----@Component（路径-''@angular/core'）。（就是Java中的注解，TS语法和Java很像）

    - ```ts
      //组件=模板+脚本+样式
      //我们需要理解一个思想：样式和结构尽量分开，也就是html中有些样式尽量剔出来，这就作为了这里的样式产生的原因
      @Component({
          template:'ssss',//模板中的内容将会替代使用组件者的innerHTML（所以其中你可以写标签），这个就代表组件在html中的结构。通常是一个组件对应一个html文件
          templateUrl:'',//模板文件路径,指定一个html
          selector:'com1',//代表在html中的样式，样式的属性不止这些,定义使用组件使用的方式和名称。如果直接写一个com1，这个组件在html中作为一个元素标签使用；如果写[com1],作为标签的属性使用；.com1,作为class属性中的类名进行使用。
          styleUrls:[],//指定要使用的css文件路径，是一个数组
      })
      //脚本，代表这个组件在html中的具有的行为以及数据
      export class Com01{
          username="ss";
          age=22;
      }
      ```

      

  - 将这个组件在某个模块中进行注册

    ```ts
    //当前我们只有一个模块：app.moudle.ts。将路径进行导入，并且指定一个别名，然后将别名在declarations:[]数组中进行添加。
    import { AppComponent } from './app.component';
    
    @NgModule({
      declarations: [
        AppComponent
      ]
    ```

    

  - 使用自定义组件，随便在一个html文件中按照组件样式声明的使用方式进行使用即可。

    - 注意：不能组件自身调用自身
    - angular中模板没有根标签的数量限制

- Angular提供的创建组件的简化工具

  - ng generate/g component 组件名
  - npx ng generate/g component 组件名
  - 在项目的根目录下执行这两个命令

### 数据绑定

#### HTML绑定

- ng表达式：{{NG表达式}}
  - 可以执行的代码
    - 算术运算
    - 比较运算
    - 逻辑运算
    -  三目运算
    - 调用函数
    - 创建对象---不支持，不能出现new关键字
    - JSON序列化---不支持，ng中认为是undefined

#### 属性绑定

- 形式1：直接在属性上用{{}}

  - ```html
    <p title="{{uname}}">sss</p>
    ```

    

- 形式2：使用[]

  - ```html
    <p [title]="username">sss</p>
    <!-- 后面整体是一个表达式，不要看作字符串，可以用Linux中``进行理解，所以也能满足上述HTML绑定中的可执行代码逻辑-->
    <button [title]="'当前购买数：'+count">
        +
    </button>
    ```
    
    
    

#### 指令绑定

#####angular中的指令系统

- 注意，这些指令是angular自定义的组件，它们也分属与不同的模块中，所以要使用时我们需要将这些模块进行导入

  - 
    
    ```app.module.ts
    import { NgModule } from '@angular/core';
    import { BrowserModule } from '@angular/platform-browser';
    
    import { AppRoutingModule } from './app-routing.module';
    import { AppComponent } from './app.component';
    
    @NgModule({
      declarations: [
        AppComponent
      ],
      imports: [
        BrowserModule,//只要angular用于创建web项目，就需要导入这个浏览器模块，不然很多浏览器的功能你无法使用，而这个模块中导入了CommonModule和ApplicationModule.
        AppRoutingModule
      ],
      providers: [],
      bootstrap: [AppComponent]
    })
    export class AppModule { }
    ```
    
    
    
  - 

- angualr中的指令分为两种：

  - 预定义指令：

    - 组件：组件的父类是指令

    - 结构型指令：会影响DOM树的结构。这种指令使用**\***标识
    - 属性型指令：不会影响DOM的结构，只会影响元素的外观或行为。这种指令使用**[]**标识

  - 自定义指令

    - 创建指令class
    - 在模块中注册指令class
    - 使用指令

- 循环绑定：*ngfor

  - [Angular - NgForOf](https://angular.cn/api/common/NgForOf)

  - 
    
    ```ts
    class A{
        a=[1,2,3,4,5,6];
    }
    ```
    
    

  ```html
  //e是一个临时遍历元素，a是遍历数据
  <ul>
      <li *ngFor="let e of a">{{e}}</li>
  </ul>
  
  //index是ng每次循环产生的下标
  <ul>
      <li *ngFor="let e of a;let i=index">{{i}}--{{e}}</li>
  </ul>
  
  <ul>
      <li *ngFor="let e of a;index as i">{{i}}--{{e}}</li>
  </ul>
  ```

  

- 选择绑定：*ngIf

  - 
    
    ```html
    //意思是说这个指令在任意的标签中都可以使用，这个布尔表达式可以是模型中的数据也可以是方法调用，或者是自定义的表达式，反正最终要返回一个布尔值即可
    <any *ngIf="布尔表达式">
    //ng的实现方式，当布尔表达式的值是false就会将这个标签直接从组件对应的的html的DOM数上删除
    ```
    
    
    
  - 带上else的写法
  
    ```html
    <div *ngIf="age>18;else child">
    	成年人观看
    </div>
    <ng-template #child>//#chlid就是定义的一个透明容器的编号
    	这是未成年看
    </ng-template>
    注意，两者中间尽量不要加任何的执行语句
    <!--
    为什么必须要用<ng-template>,因为你的条件判断后肯定只会走一条分支，那么我就只需要一个显示区（*ngIf使用者），其后的条件执行结果就确定你最终在显示区显示的内容，我只需要使用显示内容代替这个标签中的innerHTML即可。但是这个内容最好用统一标识而不是随意标签，这样既容易识别又容易管理，这就是ng-template的作用，最后用它的innerHTML代替原有的即可。可以理解为一个透明容器，里面可以用任意合法的标签。
    -->
    从这里我们还能体会到一个好处，就是ts中数据的改变，我们只需要一个html模板就能得到多个不同表现的html
    ```
  
    
  
- 样式绑定

  - [ngStyle]

    - 用这个属性进行绑定，其值必须是一个对象，这个对象就是css的中样式表{}.

    - 

      ```html
      <button [ngStyle]="cssObj">加载更多</button>
      //这里你可以直接写一个对象：{'font-size':'18px','color':'#223'}
      ```

      

    - 

      ```ts
      //或者是在ts中进行属性绑定,注意区分ts中与css中的写法细节
      class C{
          cssObj={
              fontSize:'18px',//这里你也可以写'font-size',因为TS语法不允许-定义名称，所以需要''.这种''属性访问时就只能通过对象名[属性]进行访问
              color:'red'
          }
      }
      ```

      

    - 

  - [ngClass]

    - 使用ngStyle，那么css要么写在html中要么写在ts中，这样导致耦合。css应该写在css文件中进行定义。那么这就需要使用ngClass

    - ngClass绑定的值必须是一个对象，**对象属性**就是CSS中类选择器的名称，而属性值为布尔值，如果这个类选择器要使用的话就设置为true，不用设置为false。

      ```css
      .b1{
          font-size:100px,
          color:red
      }
      
      .b2-1{
          color:yellow
      }
      ```

      

      ```ts
      class C{
          cOj={
           b1:true,//启用b1样式
           'b2-1':flase,//禁用b2-1样式。注意这里只能使用烤串法则，不能使用驼峰。
          }
          
          change():void{
              this.cOj.b1=false;
              this.cOj['b2-1']=true;
          }
      }
      ```

      

      ```html
      <button [ngClass]="cOj" (click)="change()">
          laozi
      </button>
      //当ng处理后就会用这里绑定好的值放在class属性中。
      ```
      
      

- 特殊的选择绑定

  - ngSwitch

  - 
    
    ```ts
    <any [ngSwitch]="判断表达式">
        <any *ngSwitchCase="可选值1">
        </any>
    	.....
        <any *ngSwitchDefault>
        </any> 
    </any>
    ```
    
    

#### 事件绑定

- 事件属性要用()，事件名一般是js事件去除on，首字母小写。且事件执行的函数必须要带上()

  - 
    
    ```ts
    //组件的ts部分
    class Count{
        count=3;
        add():void{
            this.count++;
        }
        
        sub():void{
            this.count--;
        }
    }
    ```
    
    
  
  ```html
  //html
  <button (click)="sub()">
      - 
  </button>
  {{count}}
  <button (click)="add()">
      +
  </button>
  ```
  
  

#### 双向数据绑定指令

- 注：这个指令属于FormsModule，所以需要将其导入。

  - 在app.module.ts中导入

    ```ts
    import {FormsModule} from '@angular/forms'
    //在imports属性中添加这个模块
    imports:[
        BrowserModule,
        FormsModule
        ]
    ```

    

- [(ngModel)]
  
  - 方向1：Modle>>View,模型改变了，视图也随之改变。
  - 方向2：View>>Model,视图（主要是指表单元素，就是要与后端进行通信的元素才有用户操作改变的数据的机会）改变，模型也随之改变。
  
  - 
    
    ```html
    <input/select/textarea [(ngModel)]="">
    ```
  
    
    
  - Angular专用事件ngModelChange：“模型数据改变”

#### 自定义指令

- 指令的本质是什么？

  - 是一个类

- 如何自定义指令？
  - 创建一个指令的快捷方式：
    - ng g directive 指令名称

    - 创建好的一个指令其实就是一个ts文件中的一个类

      ```ts
      import{Directive}from '@angular/core'
      @Directive({
          selector:'[appC]'
      })
      export class C{
          
          constructor(e:ElementRef){//这个构造器会默认传入一个ElementRef类型的对象，这个对象是中有一个属性nativeElement，这个属性对应的就是当前使用这个指令的DOM元素。通过这个属性，就能通过ts操作这个对象。
          }
      }
      ```

      

    - 指令同样需要在模块中像组件一样被声明


### 过滤器

- filter：用于在View中呈现数据时显示为另一种格式，或者是过滤出我们想要的数据。其本质是一个函数。

- 使用过滤器：{{数据|过滤器名}}

- 以上概念是在angular1中使用的概念

- 到了angular2后过滤器更名为管道（Pipe）

- 定义管道类还可以实现PipeTransform接口

- 自定义管道的步骤

  - 创建的简便指令
    - ng g pipe 管道名
  
  - 创建管道class，实现转换功能
  
  - 在模块中注册管道（app.module.ts的declarations:[]中声明）
  
  - 在模板视图中使用管道
  
    - 在{{}}中使用
  
      - （ {{data|sex:参数1:参数2}} ）------使用：在管道名后传入除了数据外的参数
  
    - 在属性绑定后的表达式中使用
  
      - 

        ```html
        <p [title]="name|sex:参数：参数....">
            
        </p>
        ```
  
        
  
  ```pipe.ts
  import {Pipe} from '@angular/core';
  @Pipe({
  	name:'sex'//管道名
  })
  export class Sex{
  	//管道执行过滤任务是一个固定函数,就是一旦使用这个管道就只会调用这个函数。（就像servlet中的filter）
  	transform(val,参数1，参数2){//数据流,val就是流入的数据，如果想进行多重处理，你还能导入其他过滤器然后调用，但是这样过滤器之间的耦合高。所以建议直接在html中使用|进行连续过滤叠加就行，耦合底且可读性高。
  	//注意形参顺序和html中使用时实参传入的顺序。
  	}
  }
  ```
  
  

####angular中预定义的管道

- 有些常用的管道，我们不用自己写，angular自己提供了一些预定义的管道。
- lowercase-将文本全部转换为小写
- uppercase-将文本全部转换为大写
- titlecase-将文本转换为标题形式，把每个单词的第一个字母转换为大写形式，并把单词的其余部分转换为小写，单词之间用任意空白字符分割。
- slice-将一个Array或String从指定位置开始到指定位置结束进行片段截取。
  - {{uname|slice:0:3}}——end可以不指定，默认到尾部结束。指定后新子集中不包括end下标结束。
- json-把一个对象转换成JSON字符串格式。
- number-指定一个数字的展现格式
  - {{数据|number:'4.1-2':'en-US'}}
  - 将这个数据的整数部分保留4位小数部分保留1到2位，分组格式使用美语格式。
  - 前一个参数（digitsInfo）指定数字的保留形式默认3.3；后一个参数(locale)指定展示的分组语法格式，默认使用en-US。(参数类型都是字符串)
- currency-把数字转换为金额字符串，根据本地化规则进行格式化
  - {{数据|currency:'货币符号':'货币指标格式':'保留位数':'分组格式'}}
- date-将一个数值（到1970.1.1的毫秒数，就是时间戳）转换为日期格式
  - {{数据|date:'格式模板':'时区':'格式地区'}}

###服务和依赖注入

- 对象创建两种方式
  - new：自己手工创建
  - 依赖注入（DI-dependency injection）：无需new，只需要进行声明依赖。由angular给按照依赖自动创建实例进行放入。和spring一样。

#### 服务-Service

- angular认为组件与用户交互的一种对象，其中的内容都应该与用户操作有关系的，而与用户操作无关的内容都应该玻璃出去，放在“服务对象”中，为组件服务，例如：日志记录，计时统计，数据服务器访问

  - 换句话说就是我们后端常说的大家都要使用的工具类，用来为公众服务的。

- angular就是服务提供者

- 自定义服务：

  - 自定义一个服务后，应该需要指定两个部分的关系
    - 谁创建服务实例：有且只有angular
    - 谁使用服务实例：要么在服务中指定所有使用对象，要么在使用者上声明要使用的服务

  - 

    ```ts
    import{Injectable} from '@angular/core';
    
    //所有的服务对象都是“可被注入的”
    @Injectable({
        providedIn:'root'//指定当前服务对象字’根模块‘中提供-AppModule，且angular中只有这个提供者使用。
    })
    export class Service{
        actionM(){
            
        }
    }
    ```

    服务使用者进行服务使用声明

  - 
    
    ```ts
    export class User{
        service=null;
        constructor(service:Service){//为什么这里我们可以直接使用构造函数进行依赖注入，而不是使用注解形式？感觉很别扭...
            //如果我在这个构造函数上在声明其他非注入类，还能注入吗？不行，这也就是为什么服务能够注入的原因，如果你构造器中声明了一个可被注入的类，那么angular就会给你注入，否则是不行的。注意组件本身也是由angular创建的。
            this.service=service;
        }
    }
    ```

    
    
  - 创建步骤：
  
    - 创建服务对象并指定服务提供者
      - 服务声明三种方式
        - 6.0以后使用：proviedIn:'root'
        
          - **通过这种方式创建的服务对象是单例的，所有组件都用的是一个对象，所以要考虑数据一致性以及并发问题。对于只有操作的对象的用这种方式没问题，有属性的则需要仔细考虑**
        
        - 6.0之前是在app.module.ts的providers：[服务名,服务名]
        
          - 效果同一
        
        - 
          
          ```ts
          //服务声明时不指定服务提供者
          @Injectable()
          export class Service{
          }
          
          //在要使用服务的组件中进行服务提供者声明
          @Component(
          {
           .....
           providers:[服务名，服务名]//这种方式就是说，这个组件自己持有一个服务提供者实例，这个服务提供者就只提供组件声明的服务。因为每个服务提供者实例都是多例的，所以服务实例也是多例的，那么就能解决前两种单例问题。
              //我猜测它的实现方式应该就是providers-服务提供者的作用域问题，就是从组件，到模块，到大模块直到整个项目，如果在我组件这个作用域中要使用某个服务，先在自身中找，找到就使用，否则就逐层向外找，应该是到主模块就结束（找服务前提先找服务提供者）。
              //由此可以推测组件与模块之间的关系应该是使用js原型链来维护的
          }
          )
          export class Component{
              
          }
          ```
          
          注：**这种方式是给每个组件实例指定一个服务提供者实例，所以即使是同种组件的多个实例各自持有的也是自己单独服务实例。**
          
        - 
    - 在组件中声明依赖，服务提供者就会自动注入到使用者。
    - 自定义服务声明了服务提供者是AppModule所以这个服务属于这个模块中，所以不许哟啊进行注册，如果你采用其他的模块进行充当服务提供者，那么需要进行模块引入主模块。
    
  - 命令：ng g service 服务名

##### angular提供的服务

- HttpClient

  - 该服务用于向指定的URL发起异步请求。

  - 接触到的异步请求发送工具

    - 原生XHR
      - 本质：使用浏览器自带的XMLHttpRequest()
      - 优势：浏览器支持的原生技术。
      - 劣势：基于回调方式处理响应，可能面临回调地狱。
    - jQ.ajax()
      - 本质：使用浏览器自带的XMLHttpRequest()。只是进一步封装。
      - 优势：浏览器支持的原生技术。但比原生要简单。
      - 劣势：基于回调方式处理响应。
    - vue:Axios
      - 本质：使用浏览器自带的XMLHttpRequest()。只是进一步封装。
      - 优势：浏览器支持的原生技术。但比原生要简单。基于Promise处理响应，避免回调地狱。可以排队、并发、撤销。
      - 劣势：
    - angular:HttpClient
      - 本质：使用浏览器自带的XMLHttpRequest()。只是进一步封装。
      - 优势：浏览器支持的原生技术。但比原生要简单。基于“观察者模式”处理响应，可以排队、并发、撤销。
      - 劣势：
    - react:Fetch
      - 本质：不再是XHR，是W3C提出的新技术，有望取代XHR。
      - 优势：比XHR从根本上就更加先进，天然基于Promise。
  
  - 使用步骤
  
    - 在主模块中导入HttpClient服务所在的模块HttpClientModule（'@angular/common/http'）
  
    - 在需要使用异步请求的组件中声明依赖于HttpClient服务对象，然后就可以调用这个对象进行异步请求发送。
  
    - 注意这个对象由很多方法，详情见文档
  
    - 
      
      ```ts
      //一般使用这个方法
      this.http.get(url).subscribe((response)=>{})
      //调用httpClient对象向url发送get请求，当服务端响应时调用(response)=>{}这个回调函数。
      //因为使用的是观察者模式，get()函数返回的是一个Observable-可悲观察的对象（其实这个对象就是封装了下XHR，然后在本地配合观察者模式），我们知道观察者模式，需要进行注册，所以调用subscribe(),调用这个方法将定义一个属于this回调函数，并将其绑定在this上（=>），然后进行注册到注册表，当Observable对象收到请求响应时，就会调用这个回调函数，响应值传入作为实参传入response。
      ```
      
      

## 组件生命周期

- [Angular - 生命周期钩子](https://angular.cn/guide/lifecycle-hooks#lifecycle-event-sequence)
- [Angular全套实战教程，零基础入门前端框架_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1R54y1J75g?p=43)
- [关于angular：ngOnInit()，ngAfterViewInit()，ngafterContentInit()，ngAfterViewChecked()和构造函数之间有什么区别？ | 码农家园 (codenong.com)](https://www.codenong.com/46314734/)
- 就是组件的创建、更新、销毁，如果你想在一个组件的的这几个过程中或者之间让组件执行一些操作，那么就可以使用angular提供的钩子接口，一个组件实现了这些接口就能够在组件发生这些情况时执行一些想要操作。
- 着重点在于这些钩子函数调用的时机
- 调用顺序如下
  - constructor()：组件实例化时
  - ngOnChanges()：组件绑定的属性值发生改变（就是当组件上的属性绑定首次设置或者发生变化时就会调用这个函数。这个属性绑定可能是组件模板内部，也可能是使用组件本身时进行绑定。因此这个方法是会频繁调用的，影响性能，要慎用。）
    - 如果组件绑定过输入属性，那么在 `ngOnInit()` 之前以及所绑定的一个或多个输入属性的值发生变化时都会调用。
    - 注意，如果你的组件没有输入属性，或者你使用它时没有提供任何输入属性，那么框架就不会调用 `ngOnChanges()`。
  - ngOnInit()：在angular第一次显示数据绑定和设置指令/组件的输入属性之后，初始化指令/组件。
  - ngDoCheck()
  - ngAfterContentInit()
  - ngAfterContentCheck()
  - ngAfterViewInit()
  - ngAfterViewCheck()
  - ngOnDestroy()

##组件间数据传递

- [Angular - 在父子指令及组件之间共享数据](https://angular.cn/guide/inputs-outputs)

### 父组件传递子组件

- 父组件通过属性将数据传递给子组件的属性

- 原理：

  - 在子组件中预定好要接收父组件传递的属性，并将这个属性指定为输入型（@Input）。
  - 然后在父组件的模板中将要传递的属性与子组件的属性进行绑定。这样父组件的这个属性一旦发生改变，就会重新注入这个值到子组件中。

- 具体实现:就不写包的导入了

  - 父组件

    ```ts
    @Component({
    	selctor:'FFF',
        templateUrl:'f.template.ts',
        styleUrls:'[]'
    }
    )
    class F{
        private uname:string='xiao';
        constructor(){};
    }
    ```

    

  - 父组件模板

    ```html
    <div >
        <p>
            {{uname}}
        </p>
        <!--调用子组件,有意思的是，通常属性绑定是将后面表达式的值绑定给一个html或是ng属性，现在我们是自定义子组件专门的c属性uname属性然后将值绑定-->
        <CCC [cuname]="uname"></CCC>
    </div>
    ```

    

  - 子组件

    ```ts
    @Component({
        selector:'CCC',
        templateUrl:'',
        styleUrls:'[]'
    })
    class C{
        //预定义一个接收父组件传入的属性
        @Input()
        private cuname:string=null;//这里比较有趣的是即使这个属性是私有的，但是父组件依然能够直接在模板中进行注入。关键点就在于这个装饰器进行了一系列的处理，因为装饰器的本质就是一个函数
        constructor(){};
    }
    ```

    

  - 子组件模板

    ```html
    <p>
      <!--使用属性，父属性一旦变化，子属性也会变化-->
      {{cuname}}  
    </p>
    ```

    

### 子组件传递给父组件

- 子组件通过促发特定的事件（其中携带着数据），把数据传递给父组件。且父组件提供事件处理方法。

- 原理：

  - 子组件中提供一个输出型（@Output）属性。那么这个属性就被允许向父组件发送数据。这个属性的类型不能是普通类型，而是事件发射器**EventEmitter**（故这个属性上可以进行事件绑定，而这个绑定事件触发的时机就是当emit调用时），该类型对象有一个发送数据的方法，emit(数据)。一旦调用这个方法这个输出属性就会将发送的数据传入绑定在其上的事件上。

  - 父组件确定好要绑定在EventEmitter上的事件，并在父组件模板中的子组件标签上使用EventEmitter属性进行事件绑定。

  - 子组件ts

    ```ts
    @Component({
        selector:'CCC',
        templateUrl:'',
        styleUrls:'[]'
    })
    class C{
        @Output()//确定这个属性可以输出数据
        private cha:EventEmitter=new EventEmitter();//创建一个事件发送器
        constructor(){};
        //调用发送
        doChange(){
            this.cha.emit("ssss");//发送
        }
    }
    ```

    

  - 子组件模板
  
    ```html
    <buttton (click)="doChange()">
        
    </buttton>
    ```
    
  - 父组件
  
    ```ts
    @Component({
    	selctor:'FFF',
        templateUrl:'',
        styleUrls:'[]'
    }
    )
    class F{
        constructor(){};
        doGet(eventData){//父组件接收数据
            console.log(eventData);
        }
    }
    ```
  
  - 父组件模板
  
    ```html
    <div>
        <!--给子组件中事件发射器绑定父组件中的接收处理事件,使用$event将子组件发送的数据进行传参，注意不是事件本身-->
        <CCC (cha)="doGet($event)"></CCC>
    </div>
    ```
  
    

### 兄弟组件之间数据传递

- angular中兄弟节点之间的数据不能直接传递，只能先子递归向上传父，然后父递归向下传子。
  - 故兄弟之间数据传递往往要消耗父组件





### 父子组件之间数据传递的简便方法

- 原理

  - 只需要在父组件模型中持有子组件的引用即可，只要持有这个引用就能调用和访问这个对象的属性、方法。
  - **组件模型（ts）中的可以持有任意的标签对象的引用**（html、ng、自定义）

- 引入方式

  - 先通过ng的id定义方式定义标签id

  - 在模型中定义属性并使用装饰器【ViewChild('id名',{static:true})】导入引用

    - ViewChild:表明被这个装饰的属性是一个标签引用
    - 需要两个参数
      - 第一个指明要注入的标签对象在模板中的引用名
      - 第二个参数指明这个标签对象是静态的还是动态的，比如某些标签使用分支判断指令（*ngIf）可能因为某些原因，这个标签可能存在可能不存在。

  - 组件模板

    ```html
    <!--html标签-->
    <div #d>
        <!--自定义组件-->
        <CCC #c1></CCC>
        <CCC #c2></CCC>
    </div>
    ```

  - 组件模型

    ```ts
    class F{
        @ViewChild('c1',{static:true})//将模板中的c1对象注入到属性child中
        private child1;
        @ViewChild('d',{static:true})
        private div;
        //这两个传入的对象都是ElementRef,这个引用的nativeElement属性就是标签对象本身
    }
    ```

    

### 组件模板中可以出现的内容

- html标准元素及其属性
- 自定义组件或是ng提供的组件标签
- angular指令
- 模型属性、方法
- 事件对象（$event：进行事件绑定时，传入这个参数。如果这个事件是标准事件--html或是ng提供的那么这个指的是事件本身，如果这个事件是事件发射器，那么这个指的是事件发射器发送的数据）
- 模板变量
- angular元素识别符



## 路由和导航

- [Angular - 路由器参考手册](https://angular.cn/guide/router-reference#router-events)

- [Angular路由管理过程浅谈 - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/105102931)（这篇文章告诉你为什么要在根中进行注册路由数组，路由是对于组件的访问，而不是模块，组件是具有容器的作用，而模块只具有容器的作用。）

  - 理解思路描述很清晰

- 多页面应用：一个项目中有多个完整的html文件。

  - 页面跳转使用超链接：这种方式实际上就是**销毁**(不见得一定是销毁，因为可以在新窗口打开，但是一定会创建新的DOM树)一棵DOM树，同步请求另一棵DOM树。
  - 缺点：DOM树要反复重建，间隔中而页面一片空白

- 单页面应用：称为SPA（Single Page Application）

  - 整个项目中有且只有一个**完整的**html文件，其他的页面都是DIV片段。需要哪个页面就通过异步请求将其请求下来，然后将其插入到完整的html文件中。（这里也能够体现组件化的特点了，先处理组件，得到最终要插入的组件实例然后插入/替换）

  - 完美解决多页面问题

  - 优势：

    - 整个项目中客户端只需要下载一个html页面，创建一个完整的DOM树，页面跳转都是一个DIV替换另一个DIV。

  - 结构

    ```html
    <html>
        <head>
        </head>
        <body>
            <!--div插槽-->
            <router-outlet></router-outlet>
        </body>
    </html>
    ```
    
  - 缺点：头部信息消失，导致搜索引擎获取信息少，不利于SEO访问优化
  
  - 问题：对于单页面，我们如果直接请求片段如何插入，服务器如何响应页面

### angular中使用单页应用

- 步骤

  - 备好所需要的的路由组件

    - ng g component 组件名

  - 定义”路由词典“------[{URL-组件},{URL-组件}]

    - 在app.module.ts中为每个路由组件分配一个路由地址

      ```app.module.ts
      //声明路由词典-路由地址和路由组件的映射关系
      let router=[
      {path:'index',component:'IndexComponent'},//这里组件用的是组件的类名，不是选择器名
      ......
      ]
      ```

  - 注册”路由词典“

    ```app.module.ts
    //先将routermodule导入('@angular/router')
    imports:[
    RouterModule.forRoot(router)//将这个路由器注册在根模块中，意味着整个项目中都可以使用这个路由器
    ]
    //还可以在注册在子模块中
    RouterModule.forChild(router)
    
    ```

    

  - 创建路由组件的挂载点-路由出口

    - 在要使用路由功能的模板中进行路由挂载点的声明

      ```html
      比如在根模板中使用
      <div>
          <router-outlet></router-outlet>
      </div>
      非常像springMVC中的前端结构，拦截器拦截请求，然后映射，
      找到控制器（相当于这里的组件）完成操作，然后将页面数据进行绑定、视图渲染、然后返回。
      这里是拦截后，通过路由映射找到组件，然后实例化组件，实例替换路由插槽内容。
      是不是发现控制器和组件极其相似，MVVM、MVC。
      ```

      

  - 访问测试

- route：路由、路线

  - 两部分组成
    - 目标地址（请求地址）+处理过程（路由组件）

- router：路由器，内部包含着路由词典

### 路由细节

- 路由词典是像前端拦截器一样，使用的是过滤器模式。

- router=[{path:'',component:}]
  - path能否以/开头定义？不能，否则会报不合法配置错误
  - path中间能否有/，能。
  - path结尾能否有/，不能，无法匹配错误。
  - path:''.什么都不写，或是写空格，这个路径仍然能够用来匹配。
  - path:'**'。表示匹配所有的路径，要使用这个路径，最好放在最后，放在前面会优先匹配，然后使用。
  - 路径除了映射组件外还能映射什么？
    - redirectTo：'重定向目标路径'。当访问path时，重定向到新的目标路径。但是使用这个属性，必须要指定pathMatch：'full'.
      - pathMatch，这个属性用来指定路径进行匹配时的匹配方式。
        - full表示全名匹配，设置的path必须完全匹配。
        - prefix表示前缀匹配，设置的path仅仅是访问的前缀，只要访问的路径的前缀和path相同就都会映射到这里。默认值。

### 路由跳转（导航）

- 从一个网页地址跳转到另一个地址
- 跳转注意绝对路径和相对路径

#### 使用模板跳转

- 就是使用标签属性完成跳转

- 比如

  - 直接在组件模板中使用传统的\<a>

    ```html
    <a href="路由路径"></a>
    使用这种方式是整颗DOM树重建不是单页面应用
    ```

    

  - 使用\<a>，但是不使用href属性而是使用ng的routerLink属性（指令）。这个属性很强大可以写在任意的标签中，比如button、div.....
  
    ```html
    <a routerLink="路由地址"></a>
    ```
  
    

#### 使用脚本跳转

- 注：
  - Router类是RouterModule提供的一个服务类，声明依赖即可使用。
  
- 使用步骤

  - 在要使用的组件的模型中进行注入Router对象

    ```ts
    constructor(private router:Router){}
    ```

    

  - 在模型中要使用跳转的地方调用这个router的navigateByUrl(路由路径)即可。

- 面试题

  ```tex
  Vue.js中的路由跳转的机制有哪些？
  1.hash法：只需要修改url中的hash部分
    http://127.0.0.1/index.html#/ucenter
  2.history法：需要修改window.history对象，从而支持支持浏览器自带的后退按钮。
    http://127.0.0.1/ucenter 
  ```
  
  

###路由的参数传递

- 传统地址跳转时传递参数

  - 使用\<a>时，\<a href="地址?参数名=参数值&参数名=参数值">
  - 使用表单提交，参数名使用属性名name进行代替
  - 使用js跳转时，一般是发送异步请求或是使用local进行跳转，形式无非都是get和post

- ng中提供的自己的传递参数的形式

  - 先在路由词典中定义好参数

    ```ts
    let router=[{path:'index/:uid/:......',component:IndexComponent}]//这里的uid就是参数，同样，传递参数时url=http://localhost:1420/index/参数值
    ```

    

  - 一般是在组件的ngOnInit()中获取参数值，实际上看你的需求，因为它是一个观察者对象，所以可以异步获取。
  
    ```ts
    //要获取值需要先给组件注入一个服务ActivatedRoute-当前活动的路由对象
    constructor(private route:ActivatedRoute){}
    ngOnInit(){
       //调用活动路由对象，获取参数
        this.route.params.subscribe((data)=>{
            console.log(data.uid);
        })
        //因为这是一个params对象是一个观察者对象，所以需要提供一个回调函数，它会将客户传来的参数，封装为一个obj传入回调函数。
    }
    ```
    
    
  

### 路由嵌套

- 就是当需要达到一个路由出口中在嵌套一个路由出口的效果时。

  - 比如user是一个组件，它有自己的模板，但是其下有子组件，对应以下子路径。我们需要这些路径变化从而替换组件，但是不影响user组件的布局。

    - shop

    - register

    - 如果没有上述要求，那么我们可以直接定义路由path为user/shop---------user/register。

    - 现在我们就要在父路由中指定子路由

      ```ts
      let router=
          [
          {path:'user',
           component:'UserComponent',
           //通过child属性指定子路由
           children:[{path:'shop',component:'UserShopComponent'},{path:'register',component:'RegisterComponent'}]
          }
          ]
      ```

      

    - 我们知道这个路由是在主模块中进行注册的，那么一级路由的路由出口就只能在主模块的模板中进行使用。

    - 而二级路由的路由出口就只能在其父组件的模板中进行使用

### 路由守卫

- [Angular8路由守卫原理和使用方法_AngularJS_脚本之家 (jb51.net)](https://www.jb51.net/article/168754.htm)

- [Angular路由——路由守卫 - starof - 博客园 (cnblogs.com)](https://www.cnblogs.com/starof/p/9012193.html)

- 有些路由地址只能在特定条件下才能访问，比如购物必须先登录。spring的处理方式是使用前端拦截器完成。

- Angular提供了“路由守卫（Guard）”来实现访问路由组件前的检查功能。如果检查通过（return　true）就放行，如果检查不通过就住址访问。（但是这并不是能够解决接口访问安全的方法，这只是能够保证可视化界面的操作安全。这就是前后端分离开发还需要解决的问题）

  - 一般首页不需要检查，其他组件往往需要检测。
  - 其实能够感觉出来我们直接使用多个path="**"，路由也能够实现同样的目的
  
- 路由守卫接口

  （路由过程，树型匹配，匹配完成执行路由守卫，激活路由，激活组件）

  - CanActivate：当导航到某路由时需要执行的操作。这个确定当前组件是否会被激活
  - CanActivateChild：当导航到当前路由下的子路由时需要执行的操作。这个会决定子组件是否会被激活
  - CanDactivate：从当前路由离开需要执行的操作。
  - Resolve：在路由激活之前获取路由数据。在路由激活之前需要执行的操作。（使用这个路由可以获得路由的data属性值，以及可以通过这个守卫发起异步请求数据，然后再将数据交给激活路由，组件再通过激活路由获取数据绑定在模板上）
  - CanLoad:用于处理异步导航到某特性模块的情况

- 创建守卫
  - ng g guard 守卫名
  
  - （或者自建一个ts文件即可）
  
  - 指定这个类需要守卫的位置（这个需要实现ng提供的接口，才能在指定位置进行守卫），重写方法。重写方法返回true就通过这个守卫，返回false就不能通过。
  
    ```ts
    import{CanActivate}from '@angular/router'
    import{Injectable}from '@angular/core'
    //这个接口的作用是守卫某个路由的入口，其实可以感觉出来，守卫就是一段代码，其本质就是服务，所以这个也需要声明为能被注入的
    @Injectable({
        providedIn:'root'//不涉及数据，单例即可
    })
    export class LoginGuard implements CanActivate{
        canActivate(){
            return true;//返回true，表示会激活这个组件
        }
    }
    ```
  
    
  
  - 声明使用
  
    ```app.moudle.ts
    //在组模块中的路由数组中进行声明，每个路由其实是一个一个对象，那么自然在通过路由器进行路由匹配后会实例化路由，服务也在这个时候进行注入
    const routers=[
    {
    path:'',
    component:'',
    canActivate:[LoginGuard],//指明守卫类名。这是一个数组，过滤器模式。其他的守卫同样如此声明
    children:[]
    }
    ]
    ```

## angular的UI组件库-Ionic

- angular组件库资料：github搜awesome angular
- html中的标签不是所有的都能在ionic中使用，比如hr
- [Cross-Platform Mobile App Development: Ionic Framework](https://ionicframework.com/)
- 文档：[Open-Source UI Toolkit to Create Your Own Mobile or Desktop Apps (ionicframework.com)](https://ionicframework.com/docs)
- 是一个移动端UI组件库，可以与angular/vue/react组合应用，也可以单独使用（通过script直接引入）
- 使用步骤

  - 下载并安装全局的脚手架工具
    - npm i -g ionic
  - 运行脚手架工具，创建一个空白项目
    - ionic start 项目名 blank/tabs-桌面/sidemenu-侧边菜单
  - 进入空白项目，启动开发服务器
    - npm start
  - 使用客户端进行测试
- ionic组件在iOS和Android的效果不一样

  - iOS风格
  - md风格：Google提供的Meterial Design风格

  - 二者标题栏不同、弹出框不同、按钮、图片显示效果不同

###ionic九种主题色

- primary：蓝+白色

- secondary：青+白色

- tertiary：紫+白色

- success：绿+白色

- warning:黄+白色

- danger：红+白色

- dark：黑+白色

- medium：灰+白色

- light：白+黑色        bufferutil@^4.0.1        utf-8-validate@^5.0.2  request@^2.88.0    request@2.88.2 › har-validator@~5.1.3  request@2.88.2 › uuid@^3.3.2

  superagent@4.1.0 › formidable@^1.2.0

### ionic移动app页面结构

#### \<ion-app>

- 代表整个屏幕。保证始终铺满全屏的容器
- 所有组件的根组件

#### \<ion-header>

- 页头，没有内容时显示下阴影效果
- 根组件的一级子组件

#### \<ion-content>

- 页面主体内容
- 根组件一级子组件

#### \<ion-footer>

- 页脚
- 根组件一级子组件

#### \<ion-toolbar>

- 工具条

#### \<ion-title>

- 标题

### 响应式布局

- ionic提供了一套类似于bootstrap的响应式栅格布局系统

  - 
    
    ```html
    <ion-grid>
    	<ion-row>
        	<ion-col>内容</ion-col>
        </ion-row>
    </ion-grid>
    
    ```
    
    
    
  - 特点：
  
    - 列可以不指定宽度占比，在一行中的多个列的宽度会平均分配，一行中列的数量取决于屏幕的宽度
    - 可以使用size属性指定一列的宽度占比-如总分为12，设置size=4，那么这列的宽度就是4/12
    - 要使得一行中的某一列居中显示，我们只需要设置这一列的偏移量-offset属性，如offset=”3“，这一列相对于其左边的列左偏移量是整个宽度的3个单位（左边没有就是相对边界）。【这个底层具体会转换为margin-left实现。故会影星当前列以及后续列】
    - 可以使用push(向右推)和pull(向左拉)属性调整一列的在一行中出现的位置--底层是用绝对定位实现的，故不会 影响同一行的其他列

### ionic常用组件

- https://ionicframework.com/docs/components
- 这些组件不仅偶属性还有一些特定的事件（在html进行绑定）、方法（在ts中获取组件对象时进行使用）可以进行使用

####徽章\<ion-badge>

- \<ion-badge>23\<ion-badge>
- 一个带有颜色、圆角的矩形
- 默认颜色蓝色
- 调整颜色使用color属性，取值范围是ionic的九种主题色

#### 图标Icons

- 具体标签：

  \<ion-icon name="person" color="">\</ion-icon>

- 一个图标库，要使用指定其在库中的名字即可。字库。

#### 按钮\<ion-button>

- 按钮

  ```html
  <ion-button color="tertiray"></ion-button>
  ```

- 属性

  - color：可指定九种主题色
  - size:指定按钮大小
    - large：大号
    - 不写：默认
    - small：小号
  - expand：按钮是否带上圆角
    - block：块级按钮。带圆角
    - full:完全宽度按钮。不带圆角
  - shape：按钮的形状
    - round：圆形按钮
  - href:指定点击跳转
  - download：此属性指示浏览器下载一个URL，而不是导航到它，因此将提示用户将其保存为本地文件。如果属性具有值，则在保存提示符中用作预填充的文件名（如果用户需要，仍然可以更改文件名）。

- 带插槽的按钮：一个按钮上可以指定插槽，插槽可以用来插入图标。不指定插槽，图标也能够显示。插槽的主要作用是调整按钮文字和图标的位置。

  - slot="start",图标在按钮的最左侧出现。将按钮的内容放在其后。
  - slot="end",与start相反
  - 前两者图标大小会考虑到文字的大小
  - slot="icon-only",只放图标，且放在最左边的插槽。优先于start。如果出现内容。也会出现在右边。但是不会考虑大小。
  - 一个按钮中start与end插槽可以一起出现。icon-only与前两者一同出现大小会不一样。

#### 输入框\<ion-input>

- 一个默认没有边框的输入框

  - ```html
    <ion-input placeholder="请输入密码" type="password" value="ss"></ion-input>
    <!--带清除×的输入框-->
    <ion-input placeholder="请输入密码" clearInput></ion-input>
    ```

    

  - placeholder：空白占位内容
  - type:输入框类型
  - value：输入框值
  - clearInput：输入框带×
  - disabled:禁用的
  - readonly：只读的

#### 标签\<ion-label>

- 标签

- 属性

  - position：位置

    - 不写：默认值。内容固定在起始位置，且显示完全

    - floating：浮动。内容会浮动起来，具体表现是会和其兄弟内容重合，如果该标签下没有内容则标签内容也会消失；如果其下一个标签是一个输入框，则没有触发输入框时，其内容显示完全，触发时会缩小且上浮，将输入框位置留出。
    - fixed：固定。固定显示长度，多余的显示....
    - stacked：全栈的。文字缩小，显示全部。

#### 列表项\<ion-item>

- 默认效果，下划线。锁骨一只要有下划线的组件就想到列表项。

- 带有标签和下划线的输入框

  - 

    ```html
    <ion-item>
    <ion-label>
    	用户名：
    </ion-label>
    <ion-input placeholder="请输入"></ion-input>
    </ion-item>
    ```
    
    

- 属性
  - href="路径"。跳转
  - detail="true"。带“详情”指示器，就是一个三角形，实用性不是很大。
  - color

##### \<ion-avatar>

- 头像

##### \<ion-thumbnail>

- 缩略图

##### 简单的列表项

- 直接放文字，推荐文字放在\<ion-label>中
- 同样列表项上可以绑定事件，数据等。

##### 复杂列表项

- 比如一个列表项中有图标、有按钮、有文本、图片等。

- 列表项中也有插槽，但是只有两个：start、end

  ```html
  <ion-item>
      <!--缩略图-->
  	<ion-thumbnail slot="start">
      	<img src=""/>
      </ion-thumbnail>
  </ion-item>
  ```

  

#### 列表\<ion-list>

- 里面可以放多个\<ion-item>
- 这个标签没有什么特殊效果，用的少

##### \<ion-list-header>

- 列表头

#### 搜索框\<ion-searchbar>

- 带搜索符号的输入框

- 属性
  - showCancelButton:显示取消按钮。效果和×一样
    - always：一直显示
    - never：从不显示。默认值
    - focus：获得焦点时显示
  - color:颜色
  - value：输入内容
  - placeholder
  - inputmode:输入方式

#### 开关\<ion-toggle>

- 开关按钮

#### 卡片\<ion-card>

- 创建一个空白卡片

#####\<ion-card-header>

- 卡片头部

##### \<ion-card-subtitle>

- 卡片子标题

##### \<ion-card-title>

- 卡片标题

##### \<ion-card-content>

- 卡片内容

#### 轮播广告\<ion-slides>

- [webpack5打包图片报错——Module parse failed: Octal literal in strict mode && 图片不能正常显示_琹箐的博客-CSDN博客](https://blog.csdn.net/qq_45327886/article/details/114537880)

- 滑动元素组合，一个集合中可以包含多个滑动元素

- 默认情况下，只能手工滑动

- 属性

  - pager=“true”，分页指示器。默认false。

- 如何做到轮播内容自动播放？

  - 要通过这个组件在ts中的对象执行startAutoplay方法，才能进行自动播放。因为轮播的内容往往是在组件初始化是请求后端得到数据，没有内容时无法轮播，需要进行主动调用

    - 在模板中

      ```html
      <!--在模板中声明好对象标识-->
      <ion-slides #myAd>
      </ion-slides>
      ```

      

    - 在脚本中注入这个对象
    
      ```ts
      export class Ad implements OnInit{
          @ViewChild('myAd',{static:true})
          private myAd:IonSlides;
          ngOnInit(){
             //组件初始化完成，让轮播广告开始自动播放。 
              this.myAd.startAutoplay()
          }
      }
      ```
    
      

##### \<ion-slide>

- 轮播内容，可以是图片可以是文字

#### 无限滚动\<ion-infinite-scroll>

- 滚动条可以无限下拉

- 比如单页面显示所有数据，滚动条无限下拉。

- 原理：在滚动条滚动到距离底部指定的“临界值”（threshold）时，立即触发特定的事件【ionInfinite】（比如加载数据），并显出底部的预先指定的内容（比如：“加载中”提示符等等）。等到新的数据加载完成了，再次隐藏预占位内容，显示数据。

- 无穷滚动组件需要指示其加载完成才会停止加载，否则会一直显示加载中。

- 属性

  - threshold=“100px”.滚动条距离底部100px触发ionInfinite事件。

  ```html
  <!--这里需要将这个事件-->
  <ion-infinite-scroll threshold="100px" (ionInfinite)="load($event)"  #myScroll>
  <!--这里需要指定占位内容,以及加载样式，必须都写。注意是数据-->
  	<ion-infinite-scroll-content 
        loadingSpinner="bubbles/circle"
        loadingText="加载中"        >
      </ion-infinite-scroll-content>
  </ion-infinite-scroll>
  ```
  
  
  
  ```ts
  //在组件的脚本中通知无限滚动组件数据加载完成
  export class Load{
      @ViewChild('myScroll',{static:true})
      private myScroll:IonInfiniteScroll;
      load(event){
          event.target.complete();//这个是通过事件获取绑定事件的组件，然后将调用组件的方法。完成通知，本质就是要获取这个组件对象，所以也可以采用脚本持有一个私有无限滚动组件的对象，然后调用方法即可。
      }
  }
  ```
  
  

##### \<ion-infinite-scroll-content>

- 加载中时的占位内容

#### 弹出式窗口

- 这些对话框在DOM树上是找不到的，而是采用纯脚本实现的。故不用写模板，而是要引入对象，调用方法实现。

##### \<ion-alert>

- 依赖于服务对象AlertController，需要在组件中进行注入。然后给组件绑定点击事件。

  ```ts
  export class Component{
      constructor(private alterM:AlertController){  
      }
      
      //弹出对话框
      getAlert(){
        //先通过注入的控制器对象创建对话框,我们需要指定模板进行创建.这个方法什么时候执行结束是不确定的，所以返回的是一个Promise对象---异步对象。所以我们需要给它一个回调函数，当它创建完成后,它会将对话框这个对象作为成功的数据对象，我们可以在回调函数中将这个对话框进行展示。
          this.alterM.create(
              {
                header:'确认退出',
                message:"确定要退出？",
                buttons:['确认','退出']//按钮上往往还需要绑定处理函数
              }
          ).then((alterS)=>{
              alterS.present();
          });
      }
  }
  ```

  

- 其他两种对话框的使用方式一样只需要注入的服务进行修改进行。

####标签页式导航\<ion-tabs>

##### \<ion-tab-bar>

- 导航条
- 属性
  - slot：插槽，指定导航条插入标签页的位置。
    - top
    - bottom

###### \<ion-tab-button>

- 导航按钮。该组件中没有插槽，所以放图标时不用指定图标插槽属性。
- 属性
  - tab="路由地址"。添加这个按钮后就会跳转到指定路径。类似 超链接

## angular与ionic的路由系统对比

- ionic中默认不会注册组件

|              | angular路由系统                                              | ionic路由系统       |
| ------------ | ------------------------------------------------------------ | ------------------- |
| 创建路由词典 | 1、在app.module.ts中创建一个路由数组                         | 一样的              |
| 注册路由词典 | 1、引入路由器模块                                                                           2、将路由数组注册到路由器中 | 一样的              |
| 路由出口     | 模板中使用<router-outlet>                                    | <ion-router-outlet> |



# 移动app技术

- 原生开发（Native）

  - Android：Java/Kotlin
  - IOS:Object-C/Swift
  - 速度快，功能丰富
  - 两种设备不兼容，开发速度慢
- WebView:(H5/CSS/JS)

  - 这个需要使用浏览器内核做支撑，又叫做WebView组件
  - Vue+Mint-UI
  - 开发速度快
  - 运行慢，功能有限
- 混编开发

  - H5+原生
  - Angular+Ionic
  - Phonegap/Cordova
  - 开发速度快
  - 功能丰富
  - 运行速度慢
- JS Bridge

  - 编码是JS，运行的是原生代码。就像利用JVM的性质，可将非Java语言转换为Java语言在JVM上运行。
  - React＋Reactive
  - 开发速度快、运行快
  - 功能丰富
  - 兼容性可移植性差
- GPU绘图

  - Google Flutter/Dart---只要有显卡都能用这个进行开发运行
  - 运行速度快
  - 功能丰富
  - 兼容性可移植性好
  - 开发速度一般
  - 绘图库代码过大
  



# angular中遇见的问题

- [【AngularJS】Angularjs中 图片等链接 unsafe的问题解决方法_Mickey_于浩的博客-CSDN博客](https://blog.csdn.net/yu17310133443/article/details/79577235)

- angular的资源请求路径是以src为根目录。也就是src==/。一般我们将图片等静态资源文件放在assets目录下。

- 注意：静态网页是整体发送到浏览器端进行执行，比如图片请求，js，css等等都是通过浏览器请求进行完成，而浏览器访问资源应该使用http绝对路径不是，如果你请求的是服务器本地资源，你要么使用相对路径（当），要么就要使用文件服务器资源路径请求。

- ionic5的事件除了手势事件之外，其实还有DOM事件但是这个事件绑定名需要使用ion开头，但是有些事件是受限的，有的是有偏差的：

  **tap**   ionic5点击事件

  **press**   ionic5长按事件

  **pan**     ionic5滑动的时候触发的事件,滑动触发多次

  **swipe**   ionic5滑动事件 滑动触发一次

  **rotate**  ionic5旋转事件

  **pinch**  捏合(pinch)手势
  
  - 这些手势事件有的还有细分，具体查看hammerjs官网
  
- angular组件每次刷新、跳转都会进行重建，且执行初始化方法
