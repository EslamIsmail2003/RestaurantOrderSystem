import service.CustomerService;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){

        CustomerService customerService = new CustomerService();
        DatabaseConnection.getConnection();

        customerService.registerCustomer();


    }
}
