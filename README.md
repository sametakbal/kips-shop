# Kips Shop

**Kips Shop** is a monolithic e-commerce backend application built with **Spring Boot**. It follows clean architecture principles and aims to provide a scalable and maintainable foundation for e-commerce platforms.

---

## 🚀 Features

- 📦 Product & Category Management  
- 🛒 Cart Operations with Redis Caching  
- 🧾 Order Management (WIP)  
- 🗃️ PostgreSQL + Flyway for database versioning  
- ⚙️ RESTful API design with Spring Boot  
- 📄 Auto-generated API documentation with Swagger (SpringDoc)

---

## 🧱 Tech Stack

- Java 21  
- Spring Boot 3.4.x  
- Spring Data JPA  
- Redis (for caching)  
- PostgreSQL  
- Flyway (for DB migrations)  
- SpringDoc OpenAPI (Swagger UI)

---

## 📦 Modules (within monolith)

- `product` – product & category entities and logic  
- `cart` – Redis-based cart management  
- `order` – order and checkout (in progress)  
- `common` – shared DTOs, configs, and utilities

---

## ⚙️ Getting Started

### Prerequisites

- Java 21  
- Docker (for PostgreSQL and Redis)
- Gradle (wrapper included)

### Clone the repository

```bash
git clone https://github.com/sametakbal/kips-shop.git
cd kips-shop
