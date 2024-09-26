package learn.leejob.his.clinic.service;

import learn.leejob.his.clinic.entity.Prescription;

public interface PrescriptionService {
    void addPrescription(Prescription prescription);

    Prescription searchPrescription(Integer id);

    void deletePrescription(Integer id);
}
