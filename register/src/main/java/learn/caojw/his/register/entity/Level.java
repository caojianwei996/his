package learn.caojw.his.register.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Level {
    private Long id;
    private String name;
    private BigDecimal fee;
    private BigDecimal quota;
}
