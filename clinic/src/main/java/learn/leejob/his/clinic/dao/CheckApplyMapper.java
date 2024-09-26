package learn.leejob.his.clinic.dao;

import learn.leejob.his.clinic.entity.CheckApply;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CheckApplyMapper {

    // 插入检查申请记录
    @Insert("INSERT INTO checkapply (Count, StartTime, EndTime, RealName, DeptName, SUM, ID, MedicalID, RegistID, ItemID, Name, Objective, Position, IsUrgent, Num, CreationTime, DoctorID, CheckOperID, ResultOperID, CaseNumber, age, gender, CheckTime, Result, ResultTime, State, RecordType) " +
            "VALUES (#{Count}, #{StartTime}, #{EndTime}, #{RealName}, #{DeptName}, #{SUM}, #{ID}, #{MedicalID}, #{RegistID}, #{ItemID}, #{Name}, #{Objective}, #{Position}, #{IsUrgent}, #{Num}, #{CreationTime}, #{DoctorID}, #{CheckOperID}, #{ResultOperID}, #{CaseNumber}, #{age}, #{gender}, #{CheckTime}, #{Result}, #{ResultTime}, #{State}, #{RecordType})")
    int insertCheckApply(CheckApply checkApply);

    // 根据ID查询检查申请记录
    @Select("SELECT * FROM checkapply WHERE ID = #{ID}")
    CheckApply getCheckApplyById(Integer ID);

    // 根据病历ID查询检查申请记录列表
    @Select("SELECT * FROM checkapply WHERE MedicalID = #{MedicalID}")
    List<CheckApply> getCheckApplyByMedicalId(Integer MedicalID);

    // 更新检查申请记录
    @Update("UPDATE checkapply SET Count = #{Count}, StartTime = #{StartTime}, EndTime = #{EndTime}, RealName = #{RealName}, DeptName = #{DeptName}, SUM = #{SUM}, MedicalID = #{MedicalID}, RegistID = #{RegistID}, ItemID = #{ItemID}, Name = #{Name}, Objective = #{Objective}, Position = #{Position}, IsUrgent = #{IsUrgent}, Num = #{Num}, CreationTime = #{CreationTime}, DoctorID = #{DoctorID}, CheckOperID = #{CheckOperID}, ResultOperID = #{ResultOperID}, CaseNumber = #{CaseNumber}, age = #{age}, gender = #{gender}, CheckTime = #{CheckTime}, Result = #{Result}, ResultTime = #{ResultTime}, State = #{State}, RecordType = #{RecordType} WHERE ID = #{ID}")
    int updateCheckApply(CheckApply checkApply);

    // 删除检查申请记录
    @Delete("DELETE FROM checkapply WHERE ID = #{ID}")
    int deleteCheckApply(Integer ID);

    // 查询所有检查申请记录
    @Select("SELECT * FROM checkapply")
    List<CheckApply> getAllCheckApplies();
}