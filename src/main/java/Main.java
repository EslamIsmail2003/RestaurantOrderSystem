import service.ChoiceHandler;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        DatabaseConnection.getConnection();
        processMainMenu();
    }

    public static void printOptions(){
        System.out.println("──────────────────────────────────");
        System.out.print("├Welcome to the restaurant system┤\n");
        System.out.print("Please select from the following options: \n");
    }

    public static void processMainMenu(){
        ChoiceHandler choiceHandler = new ChoiceHandler();
            printOptions();
            while (true){
                choiceHandler.runMainMenu();
            }
    }
}
