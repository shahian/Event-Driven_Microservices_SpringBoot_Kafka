# Event-Driven_Microservices_SpringBoot_Kafka
# Event-Driven Microservices with Spring Boot and Kafka

## Overview
Event-driven architecture (EDA) is a software design pattern in which decoupled applications can asynchronously publish and subscribe to events via an event broker/message broker. This project demonstrates the implementation of event-driven microservices architecture using Spring Boot and Apache Kafka. It consists of four microservices, each serving a specific purpose within the system:

### Microservices Overview:

#### 1. base-domains:
   - **Purpose:** Contains shared domain objects and DTOs used across multiple microservices.
   
#### 2. email-service:
   - **Purpose:** Responsible for sending email notifications based on events received from other services.
   
#### 3. order-service:
   - **Purpose:** Manages orders, generates order events, and publishes them to Kafka.
   
#### 4. stock-service:
   - **Purpose:** Listens for order events from Kafka and updates stock accordingly.

## Technologies Used
- Java
- Spring Boot
- Apache Kafka
- Lombok
- Maven

## Setup Apache Kafka
1. **Install and Setup Apache Kafka:**
   - [Download Apache Kafka](https://kafka.apache.org/downloads)
   - Extract the downloaded Kafka archive to a suitable location on your system.
2. **Navigate to the Installed Path:**
   ```bash
   cd path/to/extracted/kafka



<!--Event-driven architecture (EDA) is a software design pattern in which decoupled applications can asynchronously publish and subscribe to events via an event broker/message broker.

1-Install and Setup Apache Kafka.
2-go to the intalled Path
3-open command prompt in current directory
4- Start the ZooKeeper service in command prompt:
  .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
5-open new command prompt windows in current directory  
6- Start the Kafka broker service in new in command prompt:
 .\bin\windows\kafka-server-start.bat .\config\server.properties
 -----------------------------
 OrderService  is producer
 EmailService && StockService are consumers -->
 
