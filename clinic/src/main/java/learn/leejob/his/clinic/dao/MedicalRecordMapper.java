package learn.leejob.his.clinic.dao;

import learn.leejob.his.clinic.entity.MedicalRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

@Mapper
public interface MedicalRecordMapper {

    // 插入病历记录
    @Insert("INSERT INTO medicalrecord (registerId, readme, present, presentTreat, history, allergy, physique, proposal, careful, diagnosis, cure) " +
            "VALUES (#{registerId}, #{readme}, #{present}, #{presentTreat}, #{history}, #{allergy}, #{physique}, #{proposal}, #{careful}, #{diagnosis}, #{cure})")
    int insertMedicalRecord(MedicalRecord medicalRecord);

    // 根据ID查询病历记录
    @Select("SELECT * FROM medicalrecord WHERE id = #{id}")
    MedicalRecord getMedicalRecordById(Integer id);

    // 根据挂号ID查询病历记录
    @Select("SELECT * FROM medicalrecord WHERE registerId = #{registerId}")
    List<MedicalRecord> getMedicalRecordByRegisterId(Integer registerId);

    // 更新病历记录
    @Update("UPDATE medicalrecord SET readme = #{readme}, present = #{present}, presentTreat = #{presentTreat}, history = #{history}, allergy = #{allergy}, physique = #{physique}, proposal = #{proposal}, careful = #{careful}, diagnosis = #{diagnosis}, cure = #{cure} WHERE id = #{id}")
    int updateMedicalRecord(MedicalRecord medicalRecord);

}