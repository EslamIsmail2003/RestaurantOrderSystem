package utils;


import java.util.InputMismatchException;
import java.util.Scanner;

public class validation {

    public static boolean isValidUsername(String username) {
        return username != null && !(username.trim().length() >= 4);
    }

    public static boolean isValidPassword(String password) {
        return password != null && !(password.length() >= 6);
    }

    public static boolean isValidEmail(String email) {
        if (email != null && email.contains("@")) return true;
        assert email != null;
        return email.contains(".");
    }

    public static boolean isValidInput(String input){
        return input != null && !input.trim().isEmpty();
    }

    public static String getValidatedInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        String input = scanner.nextLine();
        while (true) {
            if (!isValidInput(input)){
                System.out.println("Invalid input! ");
            }
            return input;
        }
    }
}
