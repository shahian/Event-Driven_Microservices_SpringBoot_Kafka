# Event-Driven_Microservices_SpringBoot_Kafka

Event-driven architecture (EDA) is a software design pattern in which decoupled applications can asynchronously publish and subscribe to events via an event broker/message broker.
Install and Setup Apache Kafka

 Start the ZooKeeper service in command prompt:
  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
 Start the Kafka broker service in new in command prompt:
 .\bin\kafka-server-start.bat .\config\server.properties
 -----------------------------
 OrderService  is producer
 EmailService && StockService are consumers
 
