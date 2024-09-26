package learn.leejob.his.clinic.controller;


import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.leejob.his.clinic.entity.Diagnosis;
import learn.leejob.his.clinic.entity.MedicalRecord;
import learn.leejob.his.clinic.service.DiagnosisService;
import learn.leejob.his.clinic.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门诊医生控制器类
 *
 * @author 李钊浦
 */
@RequiredArgsConstructor
@RestController
public class OutPatientController {
    private final MedicalRecordService service;
    private final DiagnosisService diagnosisservice;

    /**
     * @return {@link Response}
     * @author leejob
     * 填写患者病历
     */
    @PostMapping("/outpatient/set")
    public Response<Void> setMedicalRecord(Request<MedicalRecord> request) {

        try {
            service.setMedicalRecord(request.data());
        } catch (Exception e) {
            String msg = "设置失败";
            throw new ServiceException(msg);
        }
        return Response.ok();
    }

    /**
     * @return {@link Response}
     * @author leejob
     * 根据挂号ID获取患者病历
     */
    @GetMapping("/outpatient/find/{id}")
    public Response<MedicalRecord> findById(@PathVariable("id") Integer registerId) {
        System.out.println("根据挂号ID查询");
        MedicalRecord medicalRecord;
        try {
            medicalRecord = service.findById(registerId);
        } catch (Exception e) {
            String msg = "获取失败";
            throw new ServiceException(msg);
        }

        return Response.ok(medicalRecord);
    }

    /**
     * @return {@link Response}
     * @author leejob
     * 门诊确诊
     */
    public Response<Void> confirmDiagnosis(Request<Diagnosis> request) {
        Diagnosis diagnosis = request.data();
        try {
            // TODO: 2023/4/18 调用服务层方法，完成确诊操作
            diagnosisservice.insert(diagnosis);
        } catch (Exception e) {
            String msg = "确诊失败";
            throw new ServiceException(msg);
        }
        return Response.ok();

    }

}
