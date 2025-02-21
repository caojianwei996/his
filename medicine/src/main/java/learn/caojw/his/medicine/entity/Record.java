package learn.caojw.his.medicine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 记录实体
 *
 * @author 曹健伟
 */
@TableName("records")
@Data
public class Record {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long medicine;
    private BigDecimal change;
    private BigDecimal before;
    private BigDecimal after;
    private OffsetDateTime time;
}
