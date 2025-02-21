package learn.caojw.his.inspect.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.inspect.entity.Record;
import learn.caojw.his.inspect.service.IRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/record")
public class RecordController {
    private final IRecordService recordService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Record> request) {
        recordService.insert(request.data());
        return Response.ok();
    }

    @PutMapping
    public Response<Void> update(@RequestBody Request<Record> request) {
        recordService.update(request.data());
        return Response.ok();
    }

    @GetMapping
    public Response<Collection<Record>> selectAll() {
        return Response.ok(recordService.selectAll());
    }

    @GetMapping("/{id}")
    public Response<Record> selectByMedicalId(@PathVariable("id") Long id) {
        return Response.ok(recordService.selectById(id));
    }
}
