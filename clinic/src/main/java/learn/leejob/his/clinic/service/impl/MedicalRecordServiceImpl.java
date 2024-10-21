package learn.leejob.his.clinic.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.seata.spring.annotation.GlobalTransactional;
import learn.caojw.his.common.api.RegisterApi;
import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.leejob.his.clinic.entity.MedicalRecord;
import learn.leejob.his.clinic.mapper.MedicalRecordMapper;
import learn.leejob.his.clinic.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final RegisterApi registerApi;
    private final MedicalRecordMapper medicalRecordMapper;

    @GlobalTransactional
    @Override
    public void insert(MedicalRecord medicalRecord) {
        Response<Void> response = registerApi.update(new Request<>(Map.of("id", medicalRecord.getRegisterId(), "state", "已接诊")));
        if (response == null) {
            throw new ServiceException("");
        } else if (response.code() != 20000) {
            throw new ServiceException(response.message());
        } else {
            medicalRecordMapper.insert(medicalRecord);
        }
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        medicalRecordMapper.updateById(medicalRecord);
    }

    @Override
    public MedicalRecord selectByMedicalRecordId(Integer medicalRecordId) {
        return medicalRecordMapper.selectOne(Wrappers.<MedicalRecord>lambdaQuery().eq(MedicalRecord::getRegisterId, medicalRecordId));
    }
}
