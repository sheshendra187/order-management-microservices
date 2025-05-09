# order-management-microservices

## Spring Boot Microservices – User, Admin & Order Services

This project is a **JWT-secured microservices architecture** built with **Spring Boot 3**, featuring **User**, **Admin**, and **Order** services. It demonstrates scalable service interaction using **Eureka**, **OpenFeign**, and secure communication using **Spring Security with JWT and RBAC**.

---

## 🔧 Tech Stack

- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA**
- **PostgreSQL**
- **Eureka Discovery Server**
- **OpenFeign (Inter-service communication)**
- **Spring Boot Actuator (Monitoring)**
- **Spring Events (Async event handling)**
- **Docker (Containerization)**
- **Lombok, Validation, Swagger/OpenAPI**

---

## 📦 Microservices Overview

### ✅ User Service
- User registration and authentication (JWT-based)
- Role-based access (User/Admin)
- Exposes endpoints for user profile and login

### 🛠️ Admin Service
- Restricted to ADMIN role
- Admin-specific operations (e.g., managing users/orders via Feign)

### 📦 Order Service
- Create, fetch, and manage orders
- Uses `OrderDetails` (Embedded) with item details and amount
- Publishes events for order operations

---

## 🔐 Security

- Role-based access control (RBAC)
- JWT access and refresh token mechanism
- Spring Security filter chain with token validation

---

## 🧩 Features

- Modularized and loosely coupled services (SOLID principles)
- Service discovery with Eureka
- Feign client for inter-service communication
- DTOs with validation and exception handling
- Asynchronous order event processing with Spring Events
- Containerized with Docker
- Monitoring with Spring Boot Actuator

---

## ▶️ Run Locally

1. **Start Eureka Server**
2. **Run PostgreSQL (or use Docker)**
3. **Start services one by one**
   - `UserServiceApplication`
   - `AdminServiceApplication`
   - `OrderServiceApplication`

> Use Swagger (`/swagger-ui.html`) for API testing.

---

## 📁 Directory Structure

```plaintext
user-service/
admin-service/
order-service/
discovery-server/
common-utils/
