package learn.caojw.his.register.entity;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private String name;
    private Boolean[] scheduling;
    private Department department;
    private Level level;
}
