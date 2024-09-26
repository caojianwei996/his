package learn.caojw.his.register.entity;

import lombok.Data;

import java.time.OffsetDateTime;

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
