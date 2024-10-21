package learn.caojw.his.inspect.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("record")
@Data
public class Record {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long inspect;
    private Long register;
    private String state;
    private String result;
}
