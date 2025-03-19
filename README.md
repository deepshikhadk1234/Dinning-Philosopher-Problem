# Dining Philosopher Problem Implementation

This project implements the classic Dining Philosopher problem using Java and Spring Boot. The Dining Philosopher problem is a well-known synchronization problem that illustrates the challenges of resource allocation and deadlock prevention in concurrent programming.

## Problem Description

The Dining Philosopher problem involves:
- 5 philosophers sitting around a circular table
- 5 chopsticks placed between each philosopher
- Each philosopher needs two chopsticks to eat
- Philosophers alternate between thinking and eating

## Project Structure

The project is built using:
- Java 21
- Spring Boot 3.4.3
- Gradle as the build tool

## Key Components

- `DiningPhilosopherApplication.java`: Main application class that initializes the philosophers and chopsticks
- `Philosopher.java`: Represents a philosopher as a thread
- `Chopstick.java`: Represents the chopsticks as shared resources

## Implementation Details

The solution implements a deadlock-free approach by:
- Using a fixed thread pool to manage philosopher threads
- Implementing a strategy where philosophers pick up chopsticks in different orders based on their position
- Even-numbered philosophers pick up chopsticks in one order, while odd-numbered philosophers pick them up in the opposite order

## Building and Running

To build and run the project:

1. Ensure you have Java 21 and Gradle installed
2. Clone the repository
3. Navigate to the project directory
4. Run the following commands:

```bash
./gradlew build
./gradlew bootRun
```

## Dependencies

- Spring Boot Starter
- Lombok
- Spring Boot DevTools
- JUnit for testing

## License

This project is open source and available under the MIT License. 