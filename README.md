# 🚨 FraudWatch AI

[![Maven Central](https://img.shields.io/maven-central/v/com.fdsai.realtime/fraudwatch.svg)](https://search.maven.org/artifact/com.fdsai.realtime/fraudwatch)
[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Build Status](https://github.com/YOUR_USERNAME/fraudwatch-ai/actions/workflows/ci.yml/badge.svg)](https://github.com/YOUR_USERNAME/fraudwatch-ai/actions)

FraudWatch AI is an enterprise-grade, real-time fraud detection pipeline leveraging Spring Boot, Apache Kafka (KRaft mode), MongoDB Atlas Vector Search, and OpenAI embeddings. It synthesizes customer profiles, streams transaction events, computes semantic embeddings, and applies vector similarity search via Change Streams to flag anomalies instantly.

---

## Features

* High-Throughput Streaming via Apache Kafka
* Semantic Embeddings with OpenAI’s text-embedding-3-small
* Vector Search on MongoDB Atlas for k-NN anomaly detection
* Change Stream Processing for sub-second fraud flagging
* Modular Architecture for extensibility and custom models
* Container-First with Docker Compose for local development

---

## Prerequisites

* Java 21 (LTS)
* Maven 3.9+
* Docker & Docker Compose (for KRaft-mode Kafka)
* MongoDB Atlas Cluster (M0 or higher)
* OpenAI API Key (set OPENAI\_API\_KEY)

---

## Quick Start

1. Clone the repository
   git clone [https://github.com/YOUR\_USERNAME/fraudwatch-ai.git](https://github.com/YOUR_USERNAME/fraudwatch-ai.git)
   cd fraudwatch-ai

2. Launch Kafka (KRaft mode)
   docker-compose up -d

3. Set environment variables
   export MONGODB\_URI="mongodb+srv://<user>:<pass>@cluster0.mongodb.net/fraud"
   export OPENAI\_API\_KEY="sk-..."

4. Build & Run
   mvn clean package -DskipTests
   mvn spring-boot\:run

5. Verify
   REST health: GET [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
   Kafka topic stats: kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic transactions

---

## Configuration

Configure critical properties in src/main/resources/application.properties:

spring.application.name=FraudWatch AI
spring.data.mongodb.uri=\${MONGODB\_URI}
spring.data.mongodb.database=fraud
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=fraud-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.properties.spring.json.trusted.packages=com.fdsai.realtime.fraudwatch.model
spring.ai.openai.api-key=\${OPENAI\_API\_KEY}
spring.ai.openai.embedding.options.model=text-embedding-3-small

Security Best Practice: Never commit secrets. Use environment variables or a managed secrets provider.

---

## Architecture & Data Flow

(flowchart described below instead of rendered)

CustomerSeeder -> TransactionSeeder -> Kafka Producer -> Kafka Broker -> Kafka Consumer -> MongoDB Transactions -> Change Stream Listener -> Vector Search Service -> Flagged Transactions / Alerts

1. Seeding: CustomerSeeder and TransactionSeeder generate profiles, history, and embeddings.
2. Streaming: TransactionProducer publishes events; TransactionConsumer persists them.
3. Detection: TransactionChangeStreamListener invokes TransactionVectorSearchService on each insert and marks isFraud=true for anomalies.

## Contributing

1. Fork ➜ git checkout -b feature/YourFeature
2. Code ➜ adhere to corporate-standard code conventions
3. Test ➜ unit and integration tests required
4. Commit ➜ use clear, imperative messages
5. PR ➜ open for review with description of changes

---

## License

Released under the MIT License. See LICENSE for full terms.
