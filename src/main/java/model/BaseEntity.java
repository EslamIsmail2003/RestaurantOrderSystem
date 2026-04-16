package model;

import lombok.Getter;
import java.sql.Timestamp;
@Getter

public class BaseEntity {
    private final String id;
    private final Timestamp createdAt;

 public BaseEntity(String id, Timestamp createdAt){
     this.id = id;
     this.createdAt = createdAt;
 }
}
