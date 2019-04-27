package models;

import exceptions.DivideByZeroException;
import exceptions.SyntaxError;

import java.util.Stack;

/**
 * CalculatorModel.java : Concrete class using the stack data structure to evaluate infix math expressions.
 *
 * TODO: This file given just to get code to compile (method stubbed). Make sure to implement appropriately (and remove this).
 *
 * @author Nery Chapeton-Lamas
 * @version 1.0
 */
public class CalculatorModel implements CalculatorInterface {

    @Override
    public String evaluate(String expression)
    {

        String[] oper = {"+", "-", "*", "/", "x^"};

        for (String anOper : oper) {
            if (expression.charAt(0) == anOper.charAt(0)) {
                try {
                    throw new SyntaxError("Cannot start with operator");
                } catch (SyntaxError syntaxError) {
                    return "Cannot start with operator";
                }
            }
        }

        String[] stack = expression.split(" ");
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        for(String element : stack) {
            System.out.println(element);
        }

        for(int i = stack.length-1; i > -1; i--) {
            if(stack[i].equals(oper[0]) || stack[i].equals(oper[1]) || stack[i].equals(oper[2]) || stack[i].equals(oper[3]) || stack[i].equals(oper[4]) ) {
                operators.push(stack[i]);
            } else {
                operands.push(stack[i]);
            }
        }

        System.out.println();

        while(!operators.isEmpty()) {

            int a = Integer.parseInt(operands.pop());
            int b = Integer.parseInt(operands.pop());

            switch(operators.pop()) {
                case "+":
                    operands.push(add(a,b));
                    break;
                case "-":
                    operands.push(sub(a,b));
                    break;
                case "*":
                    operands.push(multiply(a,b));
                    break;
                case "x^":
                    operands.push(derivative(a, b));
                    break;

                case "/":
                    try {
                        operands.push(divide(a,b));
                    } catch (DivideByZeroException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
            }
        }

        return operands.pop();
    }

    private String add(int a, int b) {
        return (a + b) + "";
    }

    private String sub(int a, int b) {
        return (a - b) + "";
    }

    private String multiply(int a, int b) {
        return (a * b) + "";
    }

    //If string contain x^ return derivative
    private String derivative(int a, int b)
    {
        a = a * b ;
        b = b - 1 ;

        return new Term(a,b).toString() ;
    }

    private String divide(int a, int b) throws DivideByZeroException {

        if(b == 0) {
            throw new DivideByZeroException("Cannot divide by zero");
        }

        return (a / b) + "";
    }

}
