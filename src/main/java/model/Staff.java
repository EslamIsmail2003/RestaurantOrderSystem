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
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.email = email;
}
public void displayStaff(){
    System.out.println("First Name: " + getFirstName() + " Last Name: " + getLastName() + " Staff Id: " + getId() + " Role: " + getRole() + " Email: " + getEmail() + " Created At: " + getCreatedAt());
}
}
