package learn.caojw.his.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 药品实体
 *
 * @author 曹健伟
 */
@TableName("medicines")
@Data
public class Medicine {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
    private String format;
    private String manufacturer;
    private BigDecimal price;
    private BigDecimal number;
}
