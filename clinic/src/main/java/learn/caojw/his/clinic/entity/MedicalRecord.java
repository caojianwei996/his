package learn.caojw.his.clinic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 病历实体
 *
 * @author 曹健伟
 */
@TableName("medical_record")
@Data
public class MedicalRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer registerId;
    private String readme;
    private String present;
    private String presentTreat;
    private String history;
    private String allergy;
    private String physique;
    private String proposal;
    private String careful;
    private String diagnosis;
    private String cure;
}
