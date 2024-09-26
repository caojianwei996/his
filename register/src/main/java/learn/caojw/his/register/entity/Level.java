package learn.caojw.his.register.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 级别实体
 *
 * @author 曹健伟
 */
@Data
public class Level {
    private Long id;
    private String name;
    private BigDecimal fee;
    private BigDecimal quota;
}
