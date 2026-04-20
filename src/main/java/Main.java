import service.ChoiceHandler;
import service.CustomerService;
import service.MenuService;
import service.OrderService;
import utils.DataSeeder;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        DatabaseConnection.getConnection();
        processMainMenu();
    }


    public static void printOptions(){
        System.out.println("Welcome to the restaurant system! ");
        System.out.println("Please select from the following options: ");
    }

    public static void processMainMenu(){
        ChoiceHandler choiceHandler = new ChoiceHandler();
            printOptions();
            while (true){
                choiceHandler.runMainMenu();
            }

    }
}
