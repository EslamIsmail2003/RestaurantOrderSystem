package model;
import lombok.Getter;


import java.sql.Timestamp;

@Getter
public class Customer extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String city;

    public Customer(String id, Timestamp createdAt, String firstName, String lastName, String phone, String email, String city){
        super(id,createdAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }
    public void displayCustomer(){
            System.out.println("┌─────────────────────────────┐");
            System.out.printf("│ %-10s %-18s│%n", "Name:", getFirstName() + " " + getLastName());
            System.out.printf("│ %-10s %-18s│%n", "Email:", getEmail());
            System.out.printf("│ %-10s %-18s│%n", "Phone:", getPhone());
            System.out.printf("│ %-10s %-18s│%n", "City:", getCity());
            System.out.println("└─────────────────────────────┘");
    }
}
