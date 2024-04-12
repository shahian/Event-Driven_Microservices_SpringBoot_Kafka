
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
3. **Start the ZooKeeper service:**
   - Open a command prompt in the Kafka directory.
   - Execute the following command to start the ZooKeeper service:
    ```bash
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
4. **Open a New Command Prompt Window:**
   - Navigate to the Kafka directory in a new command prompt window.
5. **Start the Kafka broker service:**
   - Execute the following command to start the Kafka broker service:
    ```bash
   .\bin\windows\kafka-server-start.bat .\config\server.properties
# How to Use

## Prerequisites
- JDK 11 or higher installed
- Apache Maven installed
- Apache Kafka installed and configured

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/Event-Driven_Microservices_SpringBoot_Kafka.git
# Configuration

## base-domains
- **Java version:** 11
- **Dependencies:** Spring Boot starter, Lombok

## email-service
- **Java version:** 11
- **Dependencies:** Spring Boot starter, Spring Kafka, base-domains
- **Kafka configuration:**
  - Bootstrap servers: localhost:9092
  - Consumer group ID: email
  - Topic name: order_topics

## order-service
- **Java version:** 11
- **Dependencies:** Spring Boot starter, Spring Kafka, base-domains
- **Kafka configuration:**
  - Bootstrap servers: localhost:9092
  - Producer key serializer: String
  - Producer value serializer: Json
  - Topic name: order_topics

## stock-service
- **Java version:** 11
- **Dependencies:** Spring Boot starter, Spring Kafka, base-domains
- **Kafka configuration:**
  - Bootstrap servers: localhost:9092
  - Consumer group ID: stock
  - Topic name: order_topics

# Request Flow through Microservices

1. **Client Request:**
   - A client initiates a request, such as placing a new order, by interacting with the order-service API.

2. **Order Creation:**
   - The order-service receives the request and creates a new order. It generates an order event containing the details of the order.

3. **Publishing to Kafka:**
   - The order-service publishes the order event to the Kafka topic.

4. **Consuming Order Event:**
   - The email-service and stock-service are both subscribed to the Kafka topic.
   - The email-service consumes the order event and sends an email notification to the customer regarding their order.
   - The stock-service consumes the order event and updates the stock levels based on the order details.

5. **Completion:**
   - Once the order event has been processed by both the email-service and stock-service, the request is considered complete.
   - The client receives a response indicating the successful processing of their request.

# Restarting the System

To restart the system:
1. Ensure that Kafka and all microservices are running.
2. If any component goes down, restart it by following the appropriate steps for each microservice.
3. Verify that Kafka is operational and that the microservices can connect to it.
4. Monitor the system for any issues or errors and address them as necessary.

# Conclusion

This architecture leverages event-driven communication to enable loose coupling between microservices, allowing for scalability, flexibility, and resilience. By breaking down the system into smaller, specialized services, each microservice can focus on its specific business domain, leading to easier maintenance and development. Additionally, the use of Apache Kafka as the event broker ensures reliable and efficient communication between services.










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
 
