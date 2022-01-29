package converter;

import helperMethods.Priority;
import helperMethods.ZerosBeforeMinuses;

import java.util.Stack;

public class PostfixConverter implements IPostfixConverter, Priority, ZerosBeforeMinuses {
    @Override
    public String convert(String inputString) {
        String preparedString = addingZerosBeforeMinuses(inputString);
        return convertToRPN(preparedString);
    }

    public String convertToRPN(String expression) {
        StringBuilder current = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));

            if (priority == 0) current.append(expression.charAt(i));

            if (priority == 1) stack.push(expression.charAt(i));

            if (priority > 1) {
                current.append(" ");
                while (!stack.empty()) {
                    if (getPriority(stack.peek()) >= priority) current.append(stack.pop());
                    else break;
                }
                stack.push(expression.charAt(i));
            }
            if (priority == -1) {
                current.append(" ");
                while (getPriority(stack.peek()) != 1) current.append(stack.pop());
                stack.pop();
            }
        }
        while (!stack.empty()) current.append(stack.pop());
        return current.toString();
    }

    @Override
    public String addingZerosBeforeMinuses(String expresion) {
        StringBuilder preparedExpression = new StringBuilder();
        for (int token = 0; token < expresion.length(); token++) {
            char symbol = expresion.charAt(token);
            if (symbol == '-') {
                if (token == 0)
                    preparedExpression.append('0');
                else if (expresion.charAt(token-1) == '(')
                    preparedExpression.append('0');
            }
            preparedExpression.append(symbol);
        }
        return preparedExpression.toString();
    }

    @Override
    public int getPriority(char token) {
        if (token == '*' || token == '/') return 3;
        if (token == '+' || token == '-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }
}
