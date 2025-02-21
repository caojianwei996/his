package learn.caojw.his.clinic.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import learn.caojw.his.common.api.RegisterApi;
import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.clinic.entity.MedicalRecord;
import learn.caojw.his.clinic.mapper.MedicalRecordMapper;
import learn.caojw.his.clinic.service.IClinicService;
import lombok.RequiredArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ClinicService implements IClinicService {
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
