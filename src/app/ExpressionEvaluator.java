package app;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExpressionEvaluator {
    
    //Nested class to hold the operand value and its position in the expression
    class ArithmeticOperand {
        BigDecimal value;
        int startIndex;
        int endIndex;

        public ArithmeticOperand(BigDecimal value, int startIndex, int endIndex) {
            this.value = value;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

    // This method assumes the input is a valid mathematical expression in string
    // format.
    // In case of invalid or no input, it will return BigDecimal.ZERO
    public String evaluate(String expression) {
        if (isInvalidExpression(expression)) {
            return "";
        }
        // First evaluate all the multiplication/division operations and then evaluate the addition/subtraction operations
        expression = multipleDivide(expression);
        return addSubtract(expression).trim();
    }

    private ArithmeticOperand findLeftOperand(String expression, int i) {
        //Sanity check to ensure we don't go out of bounds
        if(i < 0) {
           throw new ArithmeticException("No left operand found for the operator at index " + (i+2));
        }

        BigDecimal leftOperand = BigDecimal.ZERO;
        int dec = 0;
        int endIndex = i;
        while (i >= 0 && expression.charAt(i) != ' ') {
            // Found the sign
            if (expression.charAt(i) == '-') {
                leftOperand = leftOperand.multiply(new BigDecimal(-1));
                break;
            } else if (expression.charAt(i) == '+') {
                break;
            } else {
                leftOperand = leftOperand.add(new BigDecimal(Character.getNumericValue(expression.charAt(i))).multiply(new BigDecimal(10).pow(dec)));
                dec++;
            }
            i--;
        }
        return new ArithmeticOperand(leftOperand, Math.max(i, 0), endIndex);
    }
    
    private ArithmeticOperand findRightOperand(String expression, int i) {
        //Sanity check to ensure we don't go out of bounds
        if(i >= expression.length()) {
           throw new ArithmeticException("No right operand found for the operator at index " + (i-2));
        }
        int startIndex = i;
        BigDecimal rightOperand = BigDecimal.ZERO;
        BigDecimal sign = BigDecimal.ONE;
        if (expression.charAt(i) == '-') {
            sign = BigDecimal.valueOf(-1);
            i++;
        }

        while (i < expression.length() && expression.charAt(i) != ' ') {
            rightOperand = rightOperand.multiply(new BigDecimal(10))
                    .add(new BigDecimal(Character.getNumericValue(expression.charAt(i))));
            i++;
        }
        return new ArithmeticOperand(rightOperand.multiply(sign), startIndex, Math.min(i, expression.length()-1));
    }

    // This method should check if the input expression is valid.
    // It will check for invalid starting/ending characters and null or empty
    // expression.
    // A complete check is out of scope for this example.
    boolean isInvalidExpression(String expression) {
        if (expression == null || expression.isEmpty() || expression.startsWith("*") || expression.endsWith("*")
                || expression.startsWith("/") || expression.endsWith("/")) {
            return true;
        }
        return false;
    }

    // This method will evaluate all the multiplication and division operations in the expression and return the result expression
    private String multipleDivide(String expression) {
        BigDecimal result = BigDecimal.ZERO;
        ArithmeticOperand leftOperand, rightOperand;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '*' || expression.charAt(i) == '/') {
                leftOperand = findLeftOperand(expression, i-2);
                rightOperand = findRightOperand(expression, i+2);
                if(expression.charAt(i) == '*') {
                    result = leftOperand.value.multiply(rightOperand.value);
                } else {
                    if(rightOperand.value.compareTo(BigDecimal.ZERO) == 0) {
                       throw new ArithmeticException("Division by zero is not allowed");
                    }
                    result = leftOperand.value.divideToIntegralValue(rightOperand.value);
                }
       
                //Take from the start to the space before the left operand, add the result and then add the rest of the expression after the right operand
                expression = expression.substring(0, leftOperand.startIndex) +" "+ result.toPlainString() + " " + expression.substring(rightOperand.endIndex + 1);
                i = leftOperand.startIndex + result.toPlainString().length() - 1; //It is ok to skip the space after the operand 
            }
        }
        return expression;
    }

    // This method will evaluate all the addition and subtraction operations in the expression and return the result expression
    private String addSubtract(String expression) {
        BigDecimal result = BigDecimal.ZERO;
        ArithmeticOperand leftOperand, rightOperand;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                if(i == 0 || expression.charAt(i-1) == ' ' && (i+1 < expression.length() && expression.charAt(i+1) != ' ')) {
                    continue; //This is a sign for the right operand, not an operator
                }

                leftOperand = findLeftOperand(expression, i-2);
                rightOperand = findRightOperand(expression, i+2);
                if(expression.charAt(i) == '+') {
                    result = leftOperand.value.add(rightOperand.value);
                } else {
                    result = leftOperand.value.subtract(rightOperand.value);
                }
                //Take from the start to the space before the left operand, add the result and then add the rest of the expression after the right operand
                expression = expression.substring(0, leftOperand.startIndex) +" "+ result.toPlainString() + " " + expression.substring(rightOperand.endIndex + 1);
                i = leftOperand.startIndex + result.toPlainString().length() - 1; //It is ok to skip the space after the operand 
            }
        }
        return expression;
    }
}
