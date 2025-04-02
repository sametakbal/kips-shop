# Kips Shop

**Kips Shop** is a monolithic e-commerce backend application built with **Spring Boot**. It follows clean architecture principles and aims to provide a scalable and maintainable foundation for e-commerce platforms.

---

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=sametakbal_kips-shop&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=sametakbal_kips-shop)




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
