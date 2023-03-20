该项目主要是学习Scala相关技术

Log

* 2022.12.25: 阅读《scala-with-cats》


## Scala 生态
Lightbend Stack：Akka、Akka Cluster、Akka Stream、Play、Lagom

Typelevel Stack：Cats、Cats-effect 以及以此为基础的各种上层生态，比如 http-server：Tapir / Http4s，db-layer：Doobie、json：Circle、react-stream：Fs2

ZIO Stack：ZIO-runtime 为基础的各类生态，如 zio-http、zio-json、zio-gprc、zio-stream

## 模块介绍
### rock the jvm module
1.advanced-scala
2.zio-courses(SBT项目)        

### scala for the impatient（快学scala）

### Functional Programing in Scala (Scala函数式编程)  

### scalamain-learning-kafka（AKka入门与实践）
源仓库地址(该仓库版本非常老不建议直接clone): http://www.github.com/jasongoodwin/learning-akka


## 知识点


**引用透明**    
引用透明要求函数不论进行了任何操作都可以用它的返回值来代替。 理解引用透明的另一种方式是引用透明的表达式不依赖上下文，可以本地推到，而那些非引用透明的表达式是依赖上下文的，而且需要全局推导。 


**异常**
1.异常破坏了引用透明并引入了上下文依赖
2.异常不是类型安全的


### Promise、Future和事件驱动的编程模型

使用多线程来处理阻塞式IO时会遇到一些问题：
* 代码没有在返回类型中明确表示错误；
* 代码没有在返回类型中明确表示延迟；
* 阻塞模型的吞吐量收到线程池大小的限制；
* 创建并使用许多线程会耗费额外的时间用于上下文切换，影响系统性能。




## 官方文档

### Scala with Cats
https://underscore.io/books/scala-with-cats/

### Cats Effect官方文档
https://typelevel.org/cats-effect/docs/getting-started





