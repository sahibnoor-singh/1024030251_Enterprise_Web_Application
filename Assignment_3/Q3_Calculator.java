// Q3 - Calculator with exception handling
import java.util.Scanner;

public class Q3_Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        String num1Str = sc.next();

        System.out.print("Enter second number: ");
        String num2Str = sc.next();

        System.out.print("Enter operator (+, -, *, /): ");
        String operator = sc.next();

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case "-":
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case "*":
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case "/":
                    try {
                        result = num1 / num2;
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed");
                        }
                        System.out.println("Result: " + result);
                    } catch (ArithmeticException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator entered: " + operator);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric input entered");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
