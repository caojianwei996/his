package learn.leejob.his.clinic.service;

import learn.leejob.his.clinic.entity.MedicalRecord;

public interface MedicalRecordService {


    public void save(MedicalRecord medicalrecord);

    public MedicalRecord findById(Integer registerId);

    public void setMedicalRecord(MedicalRecord medicalRecord);

}
