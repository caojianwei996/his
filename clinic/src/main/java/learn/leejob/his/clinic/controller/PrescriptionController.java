package learn.leejob.his.clinic.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.leejob.his.clinic.entity.Prescription;
import learn.leejob.his.clinic.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 处方控制器类
 *
 * @author 李钊浦
 */
@RequiredArgsConstructor
@RestController
public class PrescriptionController {
    private final PrescriptionService service;

    /**
     * 添加处方
     *
     * @author leejob
     */
    @PostMapping("/prescription/add")
    public Response<Void> addPrescription(@RequestBody Request<Prescription> request) {
        Prescription prescription = request.data();
        try {
            service.addPrescription(prescription);
        } catch (Exception e) {
            String msg = "添加失败";
            throw new ServiceException(msg);
        }
        return Response.ok();
    }

    /**
     * 删除处方
     *
     * @author leejob
     */
    @DeleteMapping("/prescription/delete/{id}")
    public Response<Void> deletePrescription(@PathVariable("id") Integer id) {
        try {
            service.deletePrescription(id);
        } catch (Exception e) {
            String msg = "删除失败";
            throw new ServiceException(msg);
        }
        return Response.ok();
    }

    /**
     * 搜索处方
     *
     * @author leejob
     */
    @GetMapping("/prescription/serach/{id}")
    public Response<Prescription> searchPrescription(@PathVariable("id") Integer id) {
        Prescription prescription;
        try {
            prescription = service.searchPrescription(id);
        } catch (Exception e) {
            String msg = "搜索失败";
            throw new ServiceException(msg);
        }
        return Response.ok(prescription);
    }
    /**
     * 改变处方
     * @author leejob
     */

    /**
     * 开立处方
     * @author leejob
     */

    /**
     * 添加药品
     * @author leejob
     */

    /**
     * 删除药品
     * @author leejob
     */
}
