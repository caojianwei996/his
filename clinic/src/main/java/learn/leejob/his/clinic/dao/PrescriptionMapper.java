package learn.leejob.his.clinic.dao;

import learn.leejob.his.clinic.entity.Prescription;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PrescriptionMapper {

    // 插入处方记录
    @Insert("INSERT INTO prescription (CaseNumber, RealName, Gender, Age, PayTime, DrugsCode, DrugsName, DrugsFormat, DrugsUnit, Manufacturer, DrugsDosageID, DrugsTypeID, DrugsPrice, MnemonicCode, ID, SUM, MedicalID, RegistID, UserID, PrescriptionName, PrescriptionID, DrugsID, DrugsUsage, Dosage, Frequency, Amount, State) " +
            "VALUES (#{CaseNumber}, #{RealName}, #{Gender}, #{Age}, #{PayTime}, #{DrugsCode}, #{DrugsName}, #{DrugsFormat}, #{DrugsUnit}, #{Manufacturer}, #{DrugsDosageID}, #{DrugsTypeID}, #{DrugsPrice}, #{MnemonicCode}, #{ID}, #{SUM}, #{MedicalID}, #{RegistID}, #{UserID}, #{PrescriptionName}, #{PrescriptionID}, #{DrugsID}, #{DrugsUsage}, #{Dosage}, #{Frequency}, #{Amount}, #{State})")
    int insertPrescription(Prescription prescription);

    // 根据ID查询处方记录
    @Select("SELECT * FROM prescription WHERE ID = #{ID}")
    Prescription getPrescriptionById(Integer ID);

    // 根据病历ID查询处方记录列表
    @Select("SELECT * FROM prescription WHERE MedicalID = #{MedicalID}")
    List<Prescription> getPrescriptionsByMedicalId(Integer MedicalID);

    // 更新处方记录
    @Update("UPDATE prescription SET CaseNumber = #{CaseNumber}, RealName = #{RealName}, Gender = #{Gender}, Age = #{Age}, PayTime = #{PayTime}, DrugsCode = #{DrugsCode}, DrugsName = #{DrugsName}, DrugsFormat = #{DrugsFormat}, DrugsUnit = #{DrugsUnit}, Manufacturer = #{Manufacturer}, DrugsDosageID = #{DrugsDosageID}, DrugsTypeID = #{DrugsTypeID}, DrugsPrice = #{DrugsPrice}, MnemonicCode = #{MnemonicCode}, SUM = #{SUM}, MedicalID = #{MedicalID}, RegistID = #{RegistID}, UserID = #{UserID}, PrescriptionName = #{PrescriptionName}, PrescriptionID = #{PrescriptionID}, DrugsID = #{DrugsID}, DrugsUsage = #{DrugsUsage}, Dosage = #{Dosage}, Frequency = #{Frequency}, Amount = #{Amount}, State = #{State} WHERE ID = #{ID}")
    int updatePrescription(Prescription prescription);

    // 删除处方记录
    @Delete("DELETE FROM prescription WHERE ID = #{ID}")
    int deletePrescription(Integer ID);

    // 查询所有处方记录
    @Select("SELECT * FROM prescription")
    List<Prescription> getAllPrescriptions();
}