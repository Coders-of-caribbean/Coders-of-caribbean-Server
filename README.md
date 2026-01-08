# Coders of Caribbean - ASP Solver Server

This repository hosts the **server component** of the game **Coders of Caribbean**, which leverages **Answer Set Programming (ASP)** to control and compute the logic for game entities (such as ships, cannonballs, etc.). The server uses the [EmbASP](https://github.com/DeMaCS-UNICAL/EmbASP) framework to integrate ASP solvers like *DLV* or *Clingo* directly within its Java-based environment.

---

## 🧩 What is Coders of Caribbean?

*Coders of Caribbean* is a programming game where players develop autonomous agents to control ships and win sea battles. This server acts as the **decision engine**, running one or more logic-based solvers to determine optimal actions based on the current game state.

---

## 🧠 Project Overview

The server performs the following core functions:

- **Receives game state data** from clients or simulations.
- **Transforms** this data into ASP-compatible facts.
- **Invokes ASP solvers** using the EmbASP framework.
- **Parses solver outputs** to produce game actions.
- **Returns computed decisions** back to the game engine or client.

Thanks to its modular logic-driven design, this architecture allows rapid experimentation with new strategies and rules without hardcoding behavior.

---

## 🔄 Client–Server Communication

Communication is based on **REST APIs** and **JSON** messages.

All endpoints are exposed under:

/api/v1/

Cross-origin requests are enabled to allow external clients to connect.

---

## 📥 Solve Endpoint

**POST** `/api/v1/solve`

- The request body is deserialized into an `Input` Java Bean.
- The `Input` contains a map of solver names to solver-specific game data.
- Only solvers whose **bean name** appears in the input are executed.

Solvers are Spring-managed **Java Beans**, automatically injected into the controller.

---

## ⚙️ Solver Execution

- Each solver runs **concurrently** using `CompletableFuture`.
- Execution is handled by a shared `ExecutorService`.
- Results are collected in a shared `Output` object.
- Thread safety is ensured via synchronized access.
- The response is returned once all solvers have completed.

---

## 🔧 Technology Stack

- **Java** – Main server language.
- **EmbASP** – Logic programming integration framework.
- **ASP Solvers** – Compatible with [Clingo](https://potassco.org/clingo/) and [DLV](https://www.dlvsystem.com/).
- **Maven** – For dependency management and build automation.

---

## 🚀 Getting Started

### Prerequisites

- Java 11+
- Maven
- An ASP solver installed and accessible (e.g., Clingo or DLV)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Coders-of-caribbean/Coders-of-caribbean-Server.git
   cd Coders-of-caribbean-Server
2. Build the project:
   ```bash
   mvn clean package

3. Run the server:
   ```bash
   java -jar target/coders-server.jar

## 🧪 Running Solvers with EmbASP
EmbASP abstracts the complexity of invoking ASP solvers. It converts game facts into logic program inputs, executes the solver, and maps the answer sets into usable Java objects. You can modify the ASP logic files under the resources/ directory to test new behaviors or strategies.

##🔍 Development and Contributions
This server is part of a larger ecosystem and was developed for research, teaching, and competitive gameplay purposes. Contributions are welcome via pull requests or issues.

## 📫 Contact
For support, contributions, or questions:

- Open an issue on this repository
- Reach out to the Coders of Caribbean organization on GitHub
- Let us know if you want to include example inputs, outputs, or specific solver commands!
