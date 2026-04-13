package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private final static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    public static int getNumberInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number: ");
                scanner.next();
            }
        }
    }

    public static long getLongInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid long number: ");
                scanner.next();
            }
        }
    }

    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains(".") && email.contains("@");
    }

    public static String getValidatedInputs(String prompt, String warningMessage) {
        System.out.println(prompt);
        String input = Utils.getStringInput();
        if (!Utils.isValidString(input)) {
            logger.warn("{}: {}", warningMessage, input);
            System.out.println("Invalid format! ");
            return null;
        }
        return input;
    }
}
