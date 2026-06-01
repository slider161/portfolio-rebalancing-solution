# Portfolio Rebalancing Solution

## Overview

This project provides a Java solution for calculating the trades required to rebalance a portfolio from its current allocation to a target allocation.

The solution was developed as part of the Charles River Development (CRD) technical assessment.

## Technologies Used

- Java
- Maven
- TestNG
- OpenCSV
- Apache POI
- H2 Database

## Project Features

### Portfolio Rebalancing

The application:

- Calculates target values for each security
- Compares target and current allocations
- Determines whether shares should be bought or sold
- Calculates the number of shares required

### Automated Testing

The project includes:

- Unit Tests
- CSV Data-Driven Tests
- Excel Data-Driven Tests
- Database Data-Driven Tests

### Manual Testing

A separate document containing manual test cases has been included.

## Project Structure

```text
src
├── main
│   └── java
│       └── application code
│
└── test
    ├── java
    │   └── automated tests
    │
    └── resources
        ├── portfolio-data.csv
        └── portfolio-data.xlsx
```

## Running the Tests

Run all tests:

```bash
mvn test
```

Clean and run tests:

```bash
mvn clean test
```

## Assumptions

- Portfolio value is fixed at $100,000.
- Fractional shares are allowed.
- No transaction fees or taxes are considered.
- Security prices remain constant during calculation.
- Percentages are expected to total 100%.

## Author

David Cunningham