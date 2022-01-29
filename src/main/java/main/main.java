package main;

import calc.Calculator;
import converter.PostfixConverter;
import validator.Validator;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello, dear user, welcome to my calculator!!!\n" +
                "To start write \"S\"\n" +
                "To finish write any string other than \"S\"");
        while (true) {
            String inputString = parsLine();
            while (true) {
                if (inputString.equals("S")) {
                    System.out.println("please write your expression");
                    inputString = parsLine();
                    checkAndCalculate(inputString);
                    System.out.println("Would you like to solve another expression?\n" +
                                           "(yes or no)");
                    while (true) {
                        inputString = parsLine();
                        if (inputString.equals("yes")) {
                            System.out.println("Write your expression");
                            inputString = parsLine();
                            checkAndCalculate(inputString);
                            System.out.println("Would you like to solve another expression\n" +
                                           "(yes or no)");
                        } else if (inputString.equals("no")) {
                            System.out.println("Thank you for using my calculator, have a nice day!!!");
                            break;
                        } else System.out.println("Please answer yes or no!");
                    }
                }else {
                    System.out.println("Good bye");
                }
                break;
            }break;
    }
    }

    private static String parsLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void checkAndCalculate(String ex) {
        if (new Validator().check(ex)) {
            String RPNExpression = new PostfixConverter().convert(ex);
            System.out.println("-> " + new Calculator().calculate(RPNExpression));
        }
    }

}


