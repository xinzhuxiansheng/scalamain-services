该项目主要是学习Scala相关技术

Log

* 2022.12.25: 阅读《scala-with-cats》

## Scala 生态

Lightbend Stack：Akka、Akka Cluster、Akka Stream、Play、Lagom

Typelevel Stack：Cats、Cats-effect 以及以此为基础的各种上层生态，比如 http-server：Tapir /
Http4s，db-layer：Doobie、json：Circle、react-stream：Fs2

ZIO Stack：ZIO-runtime 为基础的各类生态，如 zio-http、zio-json、zio-gprc、zio-stream

## 模块介绍

### rock the jvm module

1.advanced-scala 2.zio-courses(SBT项目)

### scala for the impatient（快学scala）

### Functional Programing in Scala (Scala函数式编程)

### scalamain-learning-kafka（AKka入门与实践）

源仓库地址(该仓库版本非常老不建议直接clone): http://www.github.com/jasongoodwin/learning-akka

### akka-in-action(《Akka实战》)
请参考本仓库代码，做了版本升级
原仓库地址（依赖偏旧）：https://github.com/RayRoestenburg/akka-in-action


## 知识点

**引用透明**    
引用透明要求函数不论进行了任何操作都可以用它的返回值来代替。 理解引用透明的另一种方式是引用透明的表达式不依赖上下文，可以本地推到，而那些非引用透明的表达式是依赖上下文的，而且需要全局推导。

**异常**
1.异常破坏了引用透明并引入了上下文依赖 2.异常不是类型安全的

### Promise、Future和事件驱动的编程模型

使用多线程来处理阻塞式IO时会遇到一些问题：

* 代码没有在返回类型中明确表示错误；
* 代码没有在返回类型中明确表示延迟；
* 阻塞模型的吞吐量收到线程池大小的限制；
* 创建并使用许多线程会耗费额外的时间用于上下文切换，影响系统性能。

**Router**

* ## Round Robin 依次向Pool/Group 中的各个节点发送消息，循环往复。Random- --随机向各个节点发送消息。

* Smallest Mailbox 向当前包含消息数量最少的Actor 发送消息。由于远程Actor 的邮箱大小未知，因此假设它们的队列中已经有消息在排队。所以会优先将消息发送给空闲的本地Actor。

* Scatter Gather 向Group/Pool中的所有Actor都发送消息，使用接收到的第一一个响应，丢弃之后收到的任何其他响应。如果需要确保能够尽快收到一个响应，那么可以使用scatter/gather.

* Tail Chopping
  和Scatter/Gather类似，但是Router并不是一次性向Group/Pool中的所有Actor都发送一条消息，而是每向一个Actor发送消息后等待一小段时间。有着和Scatter/Gather类似的优点，但是相较而言有可能可以减少网络负载。

* Consistent Hashing 给Router提供一个key, Router
  根据这个key生成哈希值。使用这个哈希值来决定给哪个节点发送数据。想要将特定的数据发送到特定的目标位置时，就可以使用哈希。在下章中，我们将讨论更多有关一致性哈 希的问题。

* BalancingPool BalancingPool这个路由策略有点特殊。只可以用于本地Actor。多个Actor
  BalancingPool共享同一个邮箱，一有空闲就处理邮箱中的任务。这种策略可以确保所有Actor都处于繁忙状态。对于本地集群来说，经常会优先选择这个路由策略。

## 官方文档

### Scala with Cats

https://underscore.io/books/scala-with-cats/

### Cats Effect官方文档

https://typelevel.org/cats-effect/docs/getting-started

### ScalaTest
https://www.scalatest.org/user_guide/property_based_testing

### ScalaCheck
https://scalacheck.org/

### Akka
https://akka.io/



