package learn.caojw.his.register.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 级别实体
 *
 * @author 曹健伟
 */
@TableName("levels")
@Data
public class Level {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private BigDecimal fee;
}
