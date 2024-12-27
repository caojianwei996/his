package learn.caojw.his.clinic.service;

import learn.caojw.his.clinic.entity.MedicalRecord;

public interface MedicalRecordService {
    void insert(MedicalRecord medicalRecord);

    void update(MedicalRecord medicalRecord);

    MedicalRecord selectByMedicalRecordId(Integer medicalRecordId);
}
