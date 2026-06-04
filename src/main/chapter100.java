package rvt;

import java.util.*;

public class Chapter100Ex1 {
    public static void main(String[] args) {
        while (true) {
            String numeratorInput = "";
            int divisor = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the numerator: ");
            numeratorInput = scanner.nextLine();
            if (numeratorInput.startsWith("q")) {
                scanner.close();
                break;                
            }

            int numerator;

            try {
                numerator = Integer.parseInt(numeratorInput);
            } catch (NumberFormatException nfe) { 
                System.out.println("You entered bad data.");
                System.out.println("Please try again.");
                continue;
            }
            System.out.print("Enter the divisor: ");
            try {
                divisor = Integer.valueOf(scanner.nextLine());
                System.out.println(numerator + " / " + divisor + " is " + numerator / divisor);
            } catch (ArithmeticException ae) {
                System.out.println("You can't divide " + numerator + " by " + divisor );
                continue;
            }
        }
    }

}