package model;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
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
        System.out.println("First Name: " + getFirstName() + " Last Name: " + getLastName() + " Customer Id: " + getId() +  " Phone: " + getPhone() + " Email: " + getEmail() + " City: " + getCity() + " Created At: " + getCreatedAt());
    }
}
