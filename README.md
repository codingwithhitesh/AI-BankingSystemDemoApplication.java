# Banking System Demo with Spring AI (RAG)

A robust backend REST API for a digital banking application built using **Spring Boot**, **Spring Data JPA**, and an **H2 In-Memory Database**. This project also features an advanced **AI Banking Assistant** powered by **Spring AI** and **Ollama (Llama 3.2)**, implementing Retrieval-Augmented Generation (RAG) to provide real-time, context-aware support based on live database values.

---

## 🚀 Key Features

* **Core Banking Engine:** Full CRUD operations for managing user accounts, checking live balances, handles deposits, and processing withdrawals.
* **Inheritance Structure:** Accounts use a Single Table inheritance strategy (`SINGLE_TABLE`) with polymorphic database mappings for different account types.
* **AI Integration (RAG):** A custom chatbot endpoint that fetches real-time banking data from the database, augments it into a system prompt, and utilizes a local LLM to deliver personalized, natural language support.
* **Custom Security Filter Chain:** Cleanly configured Spring Security layout to optimize local API development and testing via Postman.

---

## 🛠️ Architecture Overview

The system is split into distinct functional layers:
1.  **Presentation Layer (`@RestController`):** Handles standard HTTP workflows (`BankingController`) and decoupled AI processing intents (`AiController`).
2.  **Service Layer (`@Service`):** Orchestrates core transactional business logic (`BankingService`) and structures context payload injections for the LLM (`AiBankingAssistantService`).
3.  **Data Access Layer (`@Repository`):** Interfaces directly with Spring Data JPA (`UserRepository`, `AccountRepository`).

---

## 📋 Prerequisites

Before running the application, make sure you have the following installed:
* **Java 17** or higher
* **Maven 3.6+**
* **Ollama** (for running local AI models)
* **Postman** (for testing API endpoints)

---

## ⚙️ Getting Started

### 1. Setting Up the AI Model (Ollama)
Download and install Ollama from its official portal. Open your command prompt or terminal and pull the lightweight Llama 3.2 model:

```bash
ollama pull llama3.2

<img width="1579" height="835" alt="Capture13" src="https://github.com/user-attachments/assets/137b6ff8-e11b-4597-8f29-ef5d0bba2d1b" />
