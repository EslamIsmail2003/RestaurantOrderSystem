import service.ChoiceHandler;
import service.OrderService;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        DatabaseConnection.getConnection();
        OrderService orderService = new OrderService();
        orderService.printReceipt();
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
