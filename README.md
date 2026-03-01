# Mathematical Expression Evaluator
A simple program that evaluates strings consisting of numbers and mathematical operators.
- Supports the 4 basic operators: Addition (+), Subtraction (-), Multiplication (*), and Division (/).
- Supports both positive and negative integers.

## Assumptions
- Expression length: 1 to 2147483647 characters
- Expressions are assumed valid (code checks for invalid start, end, or empty expressions only)
- Precision: 2 decimal places
- No support for parentheses or exponents

## Requirements
- Java 21
- Maven 3.6.0 or higher (or use the included Maven wrapper)

## Usage

**Build the project:**
```bash
./mvnw clean package
```

**Run the application:**

The application evaluates one or more mathematical expressions:

```bash
java -jar target/expression-evaluator-1.0-SNAPSHOT.jar "2 + 3"
```

```bash
java -jar target/expression-evaluator-1.0-SNAPSHOT.jar "2 + 3" "10 / 3" "-5 * 2"
```
