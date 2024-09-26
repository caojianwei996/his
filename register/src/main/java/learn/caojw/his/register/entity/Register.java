package learn.caojw.his.register.entity;

import lombok.Data;

import java.time.OffsetDateTime;

/**
 * 挂号实体
 *
 * @author 曹健伟
 */
@Data
public class Register {
    private Long id;
    private String name;
    private String gender;
    private OffsetDateTime birthday;
    private OffsetDateTime time;
    private String state;
    private Employee employee;
}
