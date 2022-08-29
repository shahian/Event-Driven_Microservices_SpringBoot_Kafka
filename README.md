# Event-Driven_Microservices_SpringBoot_Kafka

Event-driven architecture (EDA) is a software design pattern in which decoupled applications can asynchronously publish and subscribe to events via an event broker/message broker.

1-Install and Setup Apache Kafka.
2-go to the intalled Path
3-open comand prompt in current directory
4- Start the ZooKeeper service in command prompt:
  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
5- Start the Kafka broker service in new in command prompt:
 .\bin\kafka-server-start.bat .\config\server.properties
 -----------------------------
 OrderService  is producer
 EmailService && StockService are consumers
 
