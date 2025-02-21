package learn.caojw.his.register.entity;

import lombok.Data;

/**
 * 员工实体
 *
 * @author 曹健伟
 */
@Data
public class Employee {
    private String id;
    private String name;
    private Boolean[] scheduling;
    private Department department;
    private Level level;
}
