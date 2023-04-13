# pulsar 学习

1. 批量消费消息，确认流程
2. 租户是怎么区分的？

## 名词解释

分块：见一条消息拆分为多条分块儿消息，然后分开按照顺序发送到broker，在consumer接收到所有的分块儿消息之后在聚合

## [Producer](https://pulsar.apache.org/docs/2.11.x/concepts-messaging/#producers)

### [发送模式](https://pulsar.apache.org/docs/2.11.x/concepts-messaging/#send-modes)

- 同步
- 异步 如果缓冲队列满了，会被阻塞或者失败

### [接入模式](https://pulsar.apache.org/docs/2.11.x/concepts-messaging/#access-mode)

| 发送模式 | 描述 |
| --- | --- |
| shared | 共享，多个producer可以发送一个topic，这是默认的发送模式 |
| Exclusive | 独占，如果已经有一个producer，则其他试图发布到这个topic的producer会被立即移除并失效 |
| ExclusiveWithFencing | 独占，如果已经存在一个生产者，那么它会被移除并失效 |
| WaitForExclusive | 独占，如果已经存在一个生产者，则新建的生产者会挂起，直到producer获取到单独的访问权限，可用于集群选主 |

### 批处理

消息批次大小由批最大消息数量和最大发布延迟定义

一批消息的所有消息被consumer确认后，这一批消息才会被确认，这导致一批消息中没有全部确认，则会重新传递这一批消息

2.6.0之后引入索引确认，borker会跟踪每个批次的确认状态，避免消息重新传递. 这个功能默认是关闭的，需要手动开启`acknowledgmentAtBatchIndexLevelEnabled=false`, 同时consumer也需要开启`.enableBatchIndexAcknowledgment(true);`，开启会导致占用更多的内存

### 分块Chunking

对于大的消息，可以使用分块操作，在producer分块，然后在consumer进行聚合处理


配置：

```yaml
# 需要禁用批处理 
enableBatching: false
chunkingEnabled: true
# 消费者未能在指定时间段内接收到消息的所有块，会使消息过期
expireTimeOfIncompleteChunkedMessage
```

## consumer

retry letter topic: 重试超过一定次数之后将转移到 dead letter topic；存储在特定topic中（默认值：<topicname>
-<subscriptionname>-RETRY）
Dead letter topic: 允许消息没有消费成功的消息，例如重试一定次数失败之后的消息；存储在特定的topic中（默认值：topicname>
-<subscriptionname>-DLQ）

## Subscription

### Subscription types

![](https://pulsar.apache.org/zh-CN/assets/images/pulsar-subscription-types-664733b68c7124129ca7d0e04dedcb96.png)

- Exclusive 独享: 只能有一个订阅者，如果存在多则则会报错，如果topic是多分区，则所有分区都会在一个订阅者消费
- Failover 故障转移:  允许有多个订阅者，如果master consuemr断开链接，则将按照优先级或者consumer字典顺序选择下一个master
  consumer
- chared 共享: 支持多个订阅者，消息将循环发送给consumer，如果一个consumer出现故障，未消费的消息会转移到其他consumer
- key_chared key共享: 在chared基础上保证key相同的msg发送到同一个cunsumer

> 需要注意：chared 类型无法使用acknowledgment；key_chared 类型需要禁用批处理

## Subscription modes

|Subscription mode | Description | Note |
|---|---| ---|
|Durable |游标是持久化的，故障之后支持从最后的游标开始消费| 默认值                                                       |
|tNonDurable|broker停止游标会丢失，无法从最后的游标开始消费|Reader’s subscription mode is NonDurable in nature and it does not prevent data in a topic from being deleted. Reader’s subscription mode can not be changed.|

## Multi-topic subscriptions

支持通过正则表达式方式订阅多个topic

## Partitioned topics

