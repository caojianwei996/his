package learn.caojw.his.register.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

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
    private LocalDateTime birthday;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private String state;
    private Employee employee;
}
