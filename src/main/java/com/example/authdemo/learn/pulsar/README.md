# pulsar 学习

1. 批浪消费消息，确认流程
2. 租户是怎么区分的？

## 名词解释

分块：见一条消息拆分为多条分块儿消息，然后分开按照顺序发送到broker，在consumer接收到所有的分块儿消息之后在聚合

## producer

### 批处理

1. 由批最大消息树和最大发布延迟定义
2. 2.6.0之后引入索引确认

### 分块

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

