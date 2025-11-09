# War of Races

A Spring Boot application for managing creatures and races in a fantasy battle system.

## Application Overview

This application provides a REST API for managing fantasy creatures and races. Key features include:

### Creatures
- Create, read, update and delete creatures
- Each creature has:
  - Name
  - Race type
  - Health points
  - Attack power
  - Defense stats
  - Special abilities

### Races
- Predefined race types (e.g. Elf, Dwarf, Orc)
- Each race has unique characteristics
- Race-specific bonuses and penalties

### Battle System
- Simulate battles between creatures
- Calculate damage based on:
  - Attack power
  - Defense stats
  - Race bonuses
  - Special abilities

### API Endpoints
- `/api/creatures` - Creature management
- `/api/races` - Race information
- `/api/battles` - Battle simulation

## Screenshot
![War of Races screenshot](./creature.png)

## Project Structure

```
warofraces/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/russojacopo/warofraces/
│   │   └── resources/
│   └── test/
│       └── java/
│           └── dev/russojacopo/warofraces/
```

## Prerequisites

- Java 11 or higher
- Maven 3.6.x or higher

## Getting Started

1. Clone the repository
```bash
git clone <repository-url>
```

2. Navigate to the project directory
```bash
cd warofraces
```

3. Build the project
```bash
./mvnw clean install
```

4. Run the application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

API documentation is available at `http://localhost:8080/swagger-ui.html` when the application is running.

## Testing

To run the tests:
```bash
./mvnw test
```

## Built With

- Spring Boot
- Maven
- Java

## License

This project is licensed under the MIT License - see the LICENSE file for details.