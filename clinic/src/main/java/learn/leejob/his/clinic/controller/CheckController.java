package learn.leejob.his.clinic.controller;


import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.leejob.his.clinic.entity.CheckApply;
import learn.leejob.his.clinic.service.CheckApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 检查控制器类
 *
 * @author 李钊浦
 */

@RequiredArgsConstructor
@RestController
public class CheckController {
    private final CheckApplyService service;

    //TODO 检查申请

    /**
     * @return {@link Response}
     * @author leejob
     * 开立患者的检查申请
     */
    @PostMapping("/outpatient/commitApply")
    public Response<Void> commitApply(@RequestBody Request<CheckApply> request) {
        CheckApply checkApply = request.data();
        System.out.println("即将开立的项目：" + checkApply);
        String msg;
        try {
            service.updateCheckApply(checkApply);
        } catch (Exception e) {
            msg = "开立失败";
            throw new ServiceException(msg);
        }

        return Response.ok();
    }

    //TODO 获取检查申请

    //TODO 暂存检查申请

    //TODO 删除检查申请

    //TODO 检查结果

    //TODO 获取检查结果

    /**
     * @return {@link Response}
     * @author leejob
     * 获得患者的检查结果
     */
    @GetMapping("/check/getApply/{id}")
    public Response<CheckApply> getApplyResult(@PathVariable("id") Integer id) {
        System.out.println("获得开立的项目：");
        String msg;
        CheckApply checkapply;
        try {
            checkapply = service.getCheckApplyResult(id);
        } catch (Exception e) {
            msg = "开立失败";
            throw new ServiceException(msg);
        }
        return Response.ok(checkapply);
    }
}
