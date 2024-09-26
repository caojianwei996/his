package learn.leejob.his.clinic.service.impl;

import learn.leejob.his.clinic.dao.MedicalRecordMapper;
import learn.leejob.his.clinic.entity.MedicalRecord;
import learn.leejob.his.clinic.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordMapper dao;

    @Override
    public void save(MedicalRecord medicalrecord) {
        dao.updateMedicalRecord(medicalrecord);
    }

    @Override
    public MedicalRecord findById(Integer registerId) {
        List<MedicalRecord> list = dao.getMedicalRecordByRegisterId(registerId);
        return list.getLast();
    }

    @Override
    public void setMedicalRecord(MedicalRecord medicalRecord) {
        dao.insertMedicalRecord(medicalRecord);
    }
}
