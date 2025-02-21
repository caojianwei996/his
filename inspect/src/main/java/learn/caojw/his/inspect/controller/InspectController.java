package learn.caojw.his.inspect.controller;

import learn.caojw.his.common.entity.Request;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.inspect.entity.Inspect;
import learn.caojw.his.inspect.service.IInspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inspect")
public class InspectController {
    private final IInspectService inspectService;

    @PostMapping
    public Response<Void> insert(@RequestBody Request<Inspect> request) {
        inspectService.insert(request.data());
        return Response.ok();
    }

    @DeleteMapping("/{id}")
    public Response<Void> delete(@PathVariable("id") Long id) {
        inspectService.delete(id);
        return Response.ok();
    }

    @GetMapping
    public Response<Collection<Inspect>> selectAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return Response.ok(inspectService.selectAll(page, size));
    }

    @GetMapping("/{code}")
    public Response<Collection<Inspect>> selectByCode(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @PathVariable("code") String code) {
        return Response.ok(inspectService.selectByCode(page, size, code));
    }
}
