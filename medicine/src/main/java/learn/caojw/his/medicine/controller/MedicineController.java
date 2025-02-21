package learn.caojw.his.medicine.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.medicine.entity.Medicine;
import learn.caojw.his.medicine.entity.Record;
import learn.caojw.his.medicine.service.IMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 药库控制层
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@RequestMapping("/medicine")
@RestController
public class MedicineController {
    private final IMedicineService medicineService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Medicine> request) {
        medicineService.insert(request.data());
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        medicineService.delete(id);
        return Response.ok();
    }

    @GetMapping
    public Response<Page<Medicine>> selectMedicineByCode(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("code") String code) {
        return Response.ok(medicineService.select(page, size, code));
    }

    @GetMapping("/record")
    public Response<Page<Record>> selectRecord(@RequestParam("page") int page, @RequestParam("size") int size) {
        return Response.ok(medicineService.selectRecord(page, size));
    }

    @PostMapping("/sale/{id}/{number}")
    public Response<Void> sale(@PathVariable("id") Long id, @PathVariable("number") BigDecimal number) {
        medicineService.sale(id, number);
        return Response.ok();
    }

    @PostMapping("/back/{id}/{number}")
    public Response<Void> back(@PathVariable("id") Long id, @PathVariable("number") BigDecimal number) {
        medicineService.back(id, number);
        return Response.ok();
    }
}
