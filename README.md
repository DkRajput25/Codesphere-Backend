# CodeSphere Backend

## Overview

CodeSphere Backend is a production-level REST API built using Spring Boot.
It powers an online cloud-based code execution platform that supports multiple programming languages with secure sandboxing using Docker.

The backend handles code execution, AI-based assistance, authentication, and data persistence, acting as the core engine behind the CodeSphere platform.

---

## Core Responsibilities

* Execute user-submitted code in a secure environment
* Manage multi-language support (Java, Python, C, C++, JavaScript)
* Provide AI-powered endpoints for code explanation, debugging, and generation
* Handle user authentication and authorization using JWT
* Store and manage user code snippets
* Ensure secure and isolated execution using Docker containers

---

## Architecture

Client (React Frontend)
→ REST API (Spring Boot Backend)
→ Docker Sandbox (Code Execution)
→ Database (MySQL)

---

## Key Features

* Multi-language code execution via Docker
* RESTful API design
* AI integration for:

    * Code explanation
    * Bug detection and fixes
    * Code generation
* JWT-based authentication and authorization
* Snippet storage system (CRUD operations)
* Input/output handling for programs
* Modular layered architecture (Controller → Service → Repository)

---

## Tech Stack

### Backend

* Spring Boot
* Spring Web (REST APIs)
* Spring Security (JWT Authentication)
* Spring Data JPA

### Execution Layer

* Docker (sandboxed code execution)

### Database

* MySQL

### AI Integration

* External AI API (Gemini / OpenAI compatible)

---


## API Overview

### AI Endpoints

* POST /api/ai/explain
* POST /api/ai/fix
* POST /api/ai/generate

### Code Execution

* POST /api/code/run

### Authentication

* POST /api/auth/register
* POST /api/auth/login

### Snippets

* GET /api/snippets
* POST /api/snippets
* DELETE /api/snippets/{id}

---

## How It Works

1. User writes code in frontend
2. Request is sent to backend API
3. Backend:

    * Creates temporary file
    * Selects appropriate Docker container
4. Code executes inside isolated container
5. Output is captured and returned to frontend

---

## Setup Instructions

### Prerequisites

* Java (JDK 17+)
* Maven
* MySQL
* Docker

---

### 1. Clone Repository

git clone https://github.com/DkRajput25/codesphere-backend.git
cd codesphere-backend

---

### 2. Configure Database

Create database:

CREATE DATABASE codesphere_db;

---

### 3. Application Configuration

Create file:

src/main/resources/application.properties

Add:

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/codesphere_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

app.jwt.secret=YOUR_SECRET
app.jwt.expiration-ms=86400000

ai.api.key=YOUR_API_KEY

---

### 4. Run Backend

./mvnw spring-boot:run

Server starts at:
http://localhost:8080

---

### 5. Docker Setup

Build containers:

docker build -t java-runner ./docker/java
docker build -t python-runner ./docker/python
docker build -t cpp-runner ./docker/cpp
docker build -t c-runner ./docker/c
docker build -t js-runner ./docker/javascript

---

## Security Considerations

* Code execution is isolated using Docker containers
* No direct system-level access is provided
* JWT-based authentication secures protected endpoints
* Sensitive data should be managed using environment variables

---

## Important Notes

* Docker must be running before executing code
* Do not commit sensitive configuration (API keys, DB passwords)
* Ensure containers are built before testing execution endpoints

---

## Future Enhancements

* Execution timeout handling
* Resource limiting (CPU, Memory)
* Real-time execution logs
* Scalable container orchestration

---

## Author

Dikshant Chauhan
