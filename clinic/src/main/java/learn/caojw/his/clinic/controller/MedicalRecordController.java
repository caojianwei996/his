package learn.caojw.his.clinic.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.clinic.entity.MedicalRecord;
import learn.caojw.his.clinic.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 病历控制器类
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/clinic")
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<MedicalRecord> request) {
        medicalRecordService.insert(request.data());
        return Response.ok();
    }

    @PutMapping
    public Response<Void> update(@RequestBody Request<MedicalRecord> request) {
        medicalRecordService.update(request.data());
        return Response.ok();
    }

    @GetMapping("/{id}")
    public Response<MedicalRecord> selectByMedicalId(@PathVariable("id") Integer id) {
        return Response.ok(medicalRecordService.selectByMedicalRecordId(id));
    }
}