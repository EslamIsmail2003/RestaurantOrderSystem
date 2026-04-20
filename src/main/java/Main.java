import service.CustomerService;
import service.MenuService;
import service.OrderService;
import utils.DataSeeder;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        MenuService menuService = new MenuService();
        CustomerService customerService = new CustomerService();
        DataSeeder seeder = new DataSeeder();
        DatabaseConnection.getConnection();
        OrderService orderService = new OrderService();
        orderService.orderProcess();


    }
}
