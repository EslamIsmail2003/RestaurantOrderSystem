package model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
public class Staff  extends  BaseEntity{
    private String firstName;
    private String lastName;
    private String role;
    private String email;

public Staff(String id, Timestamp createdAt, String firstName, String lastName, String role, String email){
    super(id, createdAt);
    this.firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
    this.lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
    this.role = role.substring(0,1).toUpperCase() + role.substring(1).toLowerCase();
    this.email = email;
}
public void displayStaff(){
    System.out.println("|───────────────────────────────────────|");
    System.out.printf("| %-10s %-30s%n", "Name:", getFirstName() + " " + getLastName());
    System.out.printf("| %-10s %-30s%n", "Role:", getRole());
    System.out.printf("| %-10s %-30s%n", "Email:", getEmail());
    System.out.println("|───────────────────────────────────────|");
}
}
