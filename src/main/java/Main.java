import service.CustomerService;
import service.MenuService;
import service.OrderService;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        MenuService menuService = new MenuService();
        CustomerService customerService = new CustomerService();
        DatabaseConnection.getConnection();
        OrderService orderService = new OrderService();
        orderService.placeOrder();


    }
}
