package learn.leejob.his.clinic.service.impl;

import learn.leejob.his.clinic.dao.DiagnosisMapper;
import learn.leejob.his.clinic.entity.Diagnosis;
import learn.leejob.his.clinic.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisMapper dao;

    @Override
    public void insert(Diagnosis diagnosis) {
        dao.insert(diagnosis);
    }
}
