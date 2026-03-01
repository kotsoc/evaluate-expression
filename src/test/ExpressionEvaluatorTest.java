package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.ExpressionEvaluator;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {
    
    private ExpressionEvaluator evaluator;
    
    @BeforeEach
    public void setUp() {
        evaluator = new ExpressionEvaluator();
    }
    
    // Basic Addition Tests
    @Test
    public void testSimpleAddition() {
        String result = evaluator.evaluate("2 + 3");
        assertEquals(" 5 ", result);
    }
    
    @Test
    public void testMultipleAddition() {
        String result = evaluator.evaluate("1 + 2 + 3 + 4");
        assertEquals(" 10 ", result);
    }
    
    // Basic Subtraction Tests
    @Test
    public void testSimpleSubtraction() {
        String result = evaluator.evaluate("10 - 3");
        assertEquals(" 7 ", result);
    }
    
    @Test
    public void testMultipleSubtraction() {
        String result = evaluator.evaluate("10 - 2 - 3");
        assertEquals(" 5 ", result);
    }
    
    // Multiplication Tests
    @Test
    public void testSimpleMultiplication() {
        String result = evaluator.evaluate("4 * 5");
        assertEquals(" 20 ", result);
    }
    
    @Test
    public void testMultiplicationWithZero() {
        String result = evaluator.evaluate("5 * 0");
        assertEquals(" 0 ", result);
    }
    
    // Division Tests
    @Test
    public void testSimpleDivision() {
        String result = evaluator.evaluate("10 / 2");
        assertEquals(" 5 ", result);
    }
    
    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> {
            evaluator.evaluate("5 / 0");
        });
    }
    
    @Test
    public void testDivisionWithDecimal() {
        String result = evaluator.evaluate("10 / 3");
        assertEquals(" 3.33 ", result);
    }

    @Test
    public void testLongComplexExpression() {
        String result = evaluator.evaluate("2 + 3 * 4 - 6 / 2 + 5 * 2");
        // 3 * 4 = 12, 6 / 2 = 3, 5 * 2 = 10
        // 2 + 12 - 3 + 10 = 21
        assertEquals(" 21 ", result);
    }
    
    // Operator Precedence Tests (Multiplication and Division before Addition and Subtraction)
    @Test
    public void testComplexExpressionWithPrecedence() {
        String result = evaluator.evaluate("2 + 3 * 4 - 6 / 2");
        assertEquals(" 11 ", result);
    }
    
    @Test
    public void testChainedMultiplicationAndDivision() {
        String result = evaluator.evaluate("10 * 2 / 4");
        assertEquals(" 5 ", result);
    }
    
    // Negative Numbers Tests
    @Test
    public void testNegativeNumberAddition() {
        String result = evaluator.evaluate("-5 + 3");
        assertEquals(" -2 ", result);
    }
    
    @Test
    public void testNegativeNumberMultiplication() {
        String result = evaluator.evaluate("-4 * 3");
        assertEquals(" -12 ", result);
    }
    
    @Test
    public void testNegativeNumberDivision() {
        String result = evaluator.evaluate("-10 / 2");
        assertEquals(" -5 ", result);
    }
    
    // Invalid Expression Tests
    @Test
    public void testNullExpression() {
        String result = evaluator.evaluate(null);
        assertEquals("", result);
    }
    
    @Test
    public void testEmptyExpression() {
        String result = evaluator.evaluate("");
        assertEquals("", result);
    }
    
    @Test
    public void testExpressionStartingWithMultiplication() {
        String result = evaluator.evaluate("* 5 + 3");
        assertEquals("", result);
    }
    
    @Test
    public void testExpressionEndingWithDivision() {
        String result = evaluator.evaluate("10 + 5 /");
        assertEquals("", result);
    }
    
    // Edge Cases
    @Test
    public void testSingleNumber() {
        String result = evaluator.evaluate("42");
        assertEquals("42", result);
    }
    
    @Test
    public void testLargeNumbers() {
        String result = evaluator.evaluate("1000 * 2000");
        assertEquals(" 2000000 ", result);
    }
    
    @Test
    public void testZeroPlusZero() {
        String result = evaluator.evaluate("0 + 0");
        assertEquals(" 0 ", result);
    }
}
