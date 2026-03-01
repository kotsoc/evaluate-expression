# Mathematical expression Evaluator
A simple program that evaluates a string consiting of numbers and mathematical operators.
- Support the 4 basic operators: Addition (+), Subtraction (-), Multiplication (*), and Division (/).
- Support both positive and negative integers.

## Usage

**Build the project:**
```bash
./mvnw clean package
```

**Run the application:**
```bash
java -jar target/expression-evaluator-1.0-SNAPSHOT.jar "2 + 3"
```

## Assumptions
- Length of the expression should between 1 and 2147483647
- Expression has valid form, the code only check for valid start,end or empty expresisons
- Precision of 2 decimal points
- No support for parentheses or Expoments