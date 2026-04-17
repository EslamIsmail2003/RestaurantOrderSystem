import service.CustomerService;
import service.MenuService;
import utils.DatabaseConnection;

public class Main {
    public static void main(String[] args){
        MenuService menuService = new MenuService();
        CustomerService customerService = new CustomerService();
        DatabaseConnection.getConnection();
        menuService.getAllMenuItems();
        menuService.getMenuItemByCategory();


    }
}
