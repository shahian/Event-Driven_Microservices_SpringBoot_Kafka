
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
 
# KafkaTopicConfig Class Explanation
 ```bash
@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    //spring bean for Kafka
    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topicName)
//              .partitions(3)
                .build();
    }
}
 ```

The `KafkaTopicConfig` class is a Spring `@Configuration` component responsible for configuring Kafka topics within the application.

## Key Points

- **Bean Declaration**: The class declares a bean method `newTopic()` annotated with `@Bean`, which returns a `NewTopic` instance.
  
- **Dynamic Configuration**: The name of the Kafka topic is injected from the application properties using the `@Value` annotation on the `topicName` field.
  
- **Topic Creation**: Inside the `newTopic()` method, a new Kafka topic is created using the `TopicBuilder` utility provided by Spring Kafka. The name of the topic is set dynamically based on the injected `topicName`.
  
- **Optional Configuration**: Additional configurations, such as specifying the number of partitions, can be added by uncommenting and modifying the `.partitions()` method.

## Overall Functionality

The `KafkaTopicConfig` class simplifies the configuration of Kafka topics within the application by providing a centralized mechanism. It dynamically creates Kafka topics based on application properties, ensuring consistency and flexibility in topic management. This promotes scalability and maintainability in the event-driven architecture.
# OrderController Class Explanation

The `OrderController` class is a Spring `@RestController` responsible for handling incoming HTTP requests related to order operations.

## Key Points

- **Mapping**: The class is mapped to the `/api` endpoint, indicating that all requests handled by this controller will have the `/api` prefix.

- **Dependencies Injection**: The `OrderController` class injects an instance of `OrderProducer` using the `@Autowired` annotation, allowing it to interact with the Kafka messaging system.

- **Constructor Injection**: The `OrderController` class defines a constructor that accepts an `OrderProducer` instance. This constructor injection ensures that the `OrderController` is properly initialized with the required dependencies.

- **Request Handling**: The `placeOrder` method is mapped to the `POST /v1/orders` endpoint. It receives an `OrderDTO` object as a request body, generates a unique order ID, creates an `OrderEvent` containing the order details, sets the status to "PENDING", and sends the order event to the Kafka topic using the `OrderProducer` instance.

- **Response**: The method returns a string indicating the success of the order placement operation.

## Overall Functionality

The `OrderController` class serves as the entry point for processing order-related requests in the application. It encapsulates the logic for creating and processing orders, interacting with the Kafka messaging system to publish order events. By handling order-related operations, it ensures seamless communication between clients and the underlying system, facilitating the event-driven architecture's functionality.
# OrderProducer Class Explanation

The `OrderProducer` class is a Spring `@Service` component responsible for producing messages to a Kafka topic within the application's event-driven architecture.

## Key Points

- **Spring Service**: This class is annotated with `@Service`, signifying that it's managed by the Spring framework as a service component.

- **Responsibility**: The primary responsibility of `OrderProducer` is to produce messages to a Kafka topic, facilitating asynchronous communication between microservices.

- **Dependencies**: 
  - `NewTopic`: Represents a Kafka topic and is injected into the class for configuration.
  - `KafkaTemplate<String, OrderEvent>`: This dependency is responsible for sending messages to Kafka topics.

- **Logger Usage**: The `logger` object, an instance of the SLF4J `Logger` class, is utilized for logging messages, aiding in debugging and monitoring.

- **Constructor**: The class constructor `OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate)` initializes the injected dependencies.

- **sendMessage Method**: This method (`sendMessage(OrderEvent orderEvent)`) sends an `OrderEvent` message to the configured Kafka topic:
  - It logs the order event using the logger.
  - Utilizes `MessageBuilder` to create a Kafka message with the specified payload and topic.
  - Sends the message to the Kafka topic using `kafkaTemplate.send`.

## Overall Functionality

The `OrderProducer` class abstracts away the complexity of interacting with Kafka, simplifying the process of producing messages within the event-driven architecture. Its encapsulation of Kafka-related functionalities allows other components to seamlessly send order events without the need for detailed Kafka-specific implementation.
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
 
