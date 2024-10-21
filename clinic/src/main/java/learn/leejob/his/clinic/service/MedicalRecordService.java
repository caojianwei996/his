package learn.leejob.his.clinic.service;

import learn.leejob.his.clinic.entity.MedicalRecord;

public interface MedicalRecordService {
    void insert(MedicalRecord medicalRecord);

    void update(MedicalRecord medicalRecord);

    MedicalRecord selectByMedicalRecordId(Integer medicalRecordId);
}
