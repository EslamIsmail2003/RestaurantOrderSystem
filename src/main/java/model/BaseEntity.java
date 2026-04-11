package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class BaseEntity {
    private final String id;
    private final Timestamp createdAt;

 public BaseEntity(String id, Timestamp createdAt){
     this.id = id;
     this.createdAt = createdAt;
 }
}
