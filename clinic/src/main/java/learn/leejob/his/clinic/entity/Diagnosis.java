package learn.leejob.his.clinic.entity;

public class Diagnosis {
    private Integer id;// ID 自增长类型
    private Integer deptID;
    private Integer userID;// 医生 ID 指向 User (ID)
    private String diagnosis;// 诊断结果
    private String handling;// 处理意见
    private Integer delMark;// 删除标记 1-正常 0-已删除
}
