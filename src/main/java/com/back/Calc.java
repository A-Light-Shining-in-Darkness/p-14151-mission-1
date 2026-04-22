package com.back;

public class Calc {
    public static int run(String exp) {
        return evaluate(exp.replace(" ", ""));
    }

    private static int evaluate(String exp) {
        int splitIndex = findLastOperator(exp, '+', '-');

        if (splitIndex == -1) {
            splitIndex = findLastOperator(exp, '*', '/');
        }

        if (splitIndex == -1) {
            return Integer.parseInt(exp);
        }

        char operator = exp.charAt(splitIndex);
        String leftPart = exp.substring(0, splitIndex);
        String rightPart = exp.substring(splitIndex + 1);

        int leftValue = evaluate(leftPart);
        int rightValue = evaluate(rightPart);

        return switch (operator) {
            case '+' -> leftValue + rightValue;
            case '-' -> leftValue - rightValue;
            case '*' -> leftValue * rightValue;
            case '/' -> leftValue / rightValue;
            default -> 0;
        };
    }

    private static int findLastOperator(String exp, char op1, char op2) {
        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);
            if (c == op1 || c == op2) {
                if (i > 0 && !isOperator(exp.charAt(i - 1))) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}