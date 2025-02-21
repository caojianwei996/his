package learn.caojw.his.register.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 部门实体
 *
 * @author 曹健伟
 */
@TableName("departments")
@Data
public class Department {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private String name;
}
