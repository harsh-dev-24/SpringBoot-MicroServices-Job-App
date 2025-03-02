# 🚀 Spring Boot Microservices Job App

## 🌟 About the Project

The **SpringBoot-MicroServices-Job-App** is a **scalable, flexible, and modular** job management platform built using **Spring Boot Microservices**. It enables users to **manage job listings, company profiles, and reviews** efficiently while leveraging **cloud-native** architecture.

🔹 **Why Microservices?**  
- ✅ **Scalability** - Scale services independently.  
- ✅ **Flexibility** - Modify or replace services without impacting the entire system.  
- ✅ **Resilience** - Fault tolerance with **Resilience4J Circuit Breaker**.  
- ✅ **Ease of Deployment** - Deploy services using **Docker & Kubernetes**.  

---

## 🏗 Microservices Overview

| Microservice      | Description |
|------------------|-------------|
| **API Gateway**  | Centralized entry point for handling requests & routing. |
| **Config Server** | Externalized configuration management for all services. |
| **Service Registry (Eureka)** | Service discovery and load balancing. |
| **Job Service** | Manage job listings (create, update, delete). |
| **Company Service** | Handles company profiles & details. |
| **Review Service** | Manages company/job reviews & ratings. |

---

## ⚙️ Tech Stack

| Technology  | Purpose |
|------------|---------|
| **Spring Boot** | Microservices development |
| **Spring Cloud** | Eureka, Config Server, API Gateway |
| **Spring Data JPA** | Database Management (PostgreSQL) |
| **RabbitMQ** | Message Queue for communication |
| **Resilience4J** | Circuit Breaker & Fault Tolerance |
| **Docker** | Containerization for easy deployment |

---

## 🚀 Getting Started

### ✅ Prerequisites
Ensure you have the following installed:
- **Java 17+**
- **Maven 3.6+**
- **IDEs (Eclipse, STS, IntelliJ)**
- **Docker & Docker Compose**
- **PostgreSQL/MySQL**

### 🔧 Setup & Run

#### 1️⃣ Clone the Repository
```sh
git clone https://github.com/harsh-dev-24/SpringBoot-MicroServices-Job-App.git
cd SpringBoot-MicroServices-Job-App
```

#### 2️⃣ Build the Project
```sh
mvn clean install
```

#### 3️⃣ Start Services
```sh
cd companyms && docker-compose up -d
cd config-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd jobms && mvn spring-boot:run
cd companyms && mvn spring-boot:run
cd reviewms && mvn spring-boot:run
```
---

## 👥 Contributors

👤 **Harshal Bafna**  
- 🚀 GitHub: [@harsh-dev-24](https://github.com/harsh-dev-24)  
- ✉️ Email: [harsh.223556@gmail.com](mailto:harsh.223556@gmail.com)  
- 🔗 LinkedIn: [Harshal Bafna](https://www.linkedin.com/in/harshal-bafna-61343a241/)  

💡 _Feel free to contribute! Fork, raise PRs, and let's make this project better together._  

---

## ⭐ Star & Share!

If you found this project useful, **please ⭐ star the repository** and share it with others! 🚀
