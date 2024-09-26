package learn.leejob.his.clinic.service.impl;

import learn.leejob.his.clinic.dao.PrescriptionMapper;
import learn.leejob.his.clinic.entity.Prescription;
import learn.leejob.his.clinic.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrescriptionImpl implements PrescriptionService {
    private final PrescriptionMapper dao;

    @Override
    public void addPrescription(Prescription prescription) {
        dao.insertPrescription(prescription);
    }

    @Override
    public Prescription searchPrescription(Integer id) {
        return dao.getPrescriptionById(id);
    }

    @Override
    public void deletePrescription(Integer id) {
        dao.deletePrescription(id);
    }
}
