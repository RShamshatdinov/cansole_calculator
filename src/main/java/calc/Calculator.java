package calc;

import helperMethods.Priority;

import java.util.Stack;

public class Calculator implements ICalculator, Priority {

    @Override
    public double calculate(String rnp) {
        StringBuffer operand;
        Stack<Double> stack = new Stack<>();

        for (int i = 0; i < rnp.length(); i++) {
            if (rnp.charAt(i) == ' ')
                continue;
            if (getPriority(rnp.charAt(i)) == 0) {
                operand = new StringBuffer();
                while (rnp.charAt(i) != ' ' && getPriority(rnp.charAt(i)) == 0) {
                    operand.append(rnp.charAt(i++));
                    if (i == rnp.length()) break;
                }
                stack.push(Double.parseDouble(operand.toString()));
            }
            if (getPriority(rnp.charAt(i)) > 1) {
                double a = stack.pop(), b = stack.pop();
                if (rnp.charAt(i) == '+') stack.push(b + a);
                if (rnp.charAt(i) == '-') stack.push(b - a);
                if (rnp.charAt(i) == '*') stack.push(b * a);
                if (rnp.charAt(i) == '/') {
                    if (a != 0)
                        stack.push(b / a);
                    else throw new ArithmeticException("division by zero");
                }
            }
        }
        return stack.pop();
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
