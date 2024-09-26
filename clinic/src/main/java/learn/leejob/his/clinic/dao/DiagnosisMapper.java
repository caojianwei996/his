package learn.leejob.his.clinic.dao;

import learn.leejob.his.clinic.entity.Diagnosis;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DiagnosisMapper {

    // 插入诊断记录
    @Insert("INSERT INTO diagnosis (deptID, userID, diagnosis, handling, delMark) " +
            "VALUES (#{deptID}, #{userID}, #{diagnosis}, #{handling}, #{delMark})")
    int insert(Diagnosis diagnosis);

    // 根据ID查询诊断记录
    @Select("SELECT * FROM diagnosis WHERE id = #{id}")
    Diagnosis getDiagnosisById(Integer id);

    // 查询所有诊断记录
    @Select("SELECT * FROM diagnosis")
    List<Diagnosis> getAllDiagnoses();

    // 根据科室ID查询诊断记录列表
    @Select("SELECT * FROM diagnosis WHERE deptID = #{deptID}")
    List<Diagnosis> getDiagnosesByDeptID(Integer deptID);

    // 根据医生ID查询诊断记录列表
    @Select("SELECT * FROM diagnosis WHERE userID = #{userID}")
    List<Diagnosis> getDiagnosesByUserID(Integer userID);

    // 更新诊断记录
    @Update("UPDATE diagnosis SET deptID = #{deptID}, userID = #{userID}, diagnosis = #{diagnosis}, handling = #{handling}, delMark = #{delMark} WHERE id = #{id}")
    int updateDiagnosis(Diagnosis diagnosis);

    // 删除诊断记录
    @Delete("DELETE FROM diagnosis WHERE id = #{id}")
    int deleteDiagnosis(Integer id);
}