
# GaloServer

GaloServer is the backend component for [GaloJs](https://github.com/if-shouldrs/GaloJs), providing a robust and scalable REST API. Developed with Spring Boot and JPA, it serves as the core for game state management and player interactions. The server utilizes Gradle for its build system and includes Docker integration for easy deployment and scaling.

## Features

- REST API for game state management and player interactions.
- Developed with Spring Boot for simplicity and speed.
- JPA integration for database operations.
- Docker support for easy deployment and scalability.
- Gradle build system for efficient project management.

## Prerequisites

Before setting up GaloServer, ensure you have the following installed:

- Java JDK 17 or newer
- Docker
- Gradle

## Setting Up the Development Environment

To set up your development environment for GaloServer, follow these steps:

1. Clone the server repository to your local machine.
2. Ensure Docker is running on your system.
3. Use Gradle to manage dependencies and build tasks.

## Building and Running

GaloServer includes several Gradle tasks for building and running the server within Docker:

- **buildDockerImage**: Builds the Docker image for the server.
  ```bash
  gradle buildDockerImage
  ```
- **generateCompose**: Generates a `docker-compose.yml` file with the current software version defined in gradle.
  ```bash
  gradle generateCompose
  ```
- **runWithDocker**: Runs both `buildDockerImage` and `generateCompose` tasks, then starts the server using `docker-compose`.
  ```bash
  gradle runWithDocker
  ```

## Database Configuration

The server uses JPA for database interactions. Currently, the database is configured not to persist data, suitable for development and testing environments. For production environments, you may want to modify the database configuration to ensure data persistence.

## License

GaloJs Server is licensed under the GNU General Public License v3.0 or later (GPL-3.0-or-later). See the LICENSE file for the full license text.


Thank you for your interest in the GaloJs Server! Your contributions and feedback are greatly appreciated.
