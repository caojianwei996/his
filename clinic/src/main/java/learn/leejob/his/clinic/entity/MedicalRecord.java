package learn.leejob.his.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicalRecord {
    private Integer id;
    private Integer registerId;
    private String readme;
    private String present;
    private String presentTreat;  //现病治疗情况
    private String history;  //既往史治疗情况
    private String allergy;//过敏史
    private String physique;//体格检查
    private String proposal;//检查/检验建议
    private String careful;//注意事项
    private String diagnosis;//诊断结果
    private String cure; //处理意见
}
