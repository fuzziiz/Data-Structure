import java.util.Scanner;
import java.util.Stack;

class PostfixCalculator {

    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    private static int applyOperation(int a, int b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    private static int evaluatePostfix(String expression, boolean showSteps) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder steps = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                int result = applyOperation(val2, val1, c);
                stack.push(result);
                if (showSteps) {
                    steps.append("-> ").append(val2).append(val1).append(c).append("\n");
                    steps.append("-> ").append(stack.peek()).append(val2).append(val1).append(c).append("\n");
                }
            }
        }
        if (showSteps) {
            System.out.println("Postfix Solution: ");
            System.out.println(steps);
        }
        return stack.pop();
    }

    private static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() != '(') {
                    throw new IllegalArgumentException("Invalid Expression");
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter expression: ");
        String expression = scan.nextLine();

        try {
            String postfix = infixToPostfix(expression);
            System.out.println("Postfix Expression: " + postfix);
            int result = evaluatePostfix(postfix, true);
            System.out.println("Result: " + result);
        } catch (UnsupportedOperationException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
