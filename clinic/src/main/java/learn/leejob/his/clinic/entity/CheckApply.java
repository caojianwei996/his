package learn.leejob.his.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CheckApply {
    private Integer Count;
    private String StartTime;
    private String EndTime;
    private String RealName;
    private String DeptName;
    private Float SUM;
    private Integer ID;// ID 自增长类型
    private Integer MedicalID;// 病历 ID 指向 MedicalRecord(ID)
    private Integer RegistID;// 挂号 ID 指向 Register(ID)
    private Integer ItemID;// 项目 ID 指向 Fmeditem(ID)
    private String Name;// 项目名称
    private String Objective;// 目的要求
    private String Position;// 检查部位
    private Integer IsUrgent;// 是否加急 1 为加急 0 为不加急
    private Integer Num;// 数量

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CreationTime;// 开立时间
    private Integer DoctorID;// 开立医生ID 指向 User(ID)
    private Integer CheckOperID;// 检查人员ID 指向 User (ID)
    private Integer ResultOperID;// 结果录入人员 ID 指向 User (ID)
    private Integer CaseNumber;
    private Integer age;
    private Integer gender;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date CheckTime;// 检查时间
    private String Result;// 检查结果

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date ResultTime;// 结果时间
    private Integer State;// 状态1-暂存 2-已开立 3-已交费 4-已登记 5-执行完 6-已退费 0-已作废
    private Integer RecordType;// 记录类型 1-检查 2-检验 3-处置
}
